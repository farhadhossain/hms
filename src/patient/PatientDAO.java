package patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import login.LoginDTO;

import org.apache.commons.lang.RandomStringUtils;
import utility.DAOResult;
import utility.MyConfig;


public class PatientDAO
{
	public DAOResult addNewPatient(PatientDTO dto, int userID) {
		
		Connection conn=null;
		Statement stmt=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("New Patient '"+dto.getName()+"' added Successfully");
		String sql="SELECT * FROM tbl_patient WHERE name='"+dto.getName()+"' and tel_num='"+dto.getTelephoneNum()+"'";
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				daoResult.setValid(false);
				daoResult.setMessage("Already exists in the system");
				return daoResult;
			}
			rs.close();
			
			String pin=MyConfig.pinPrefix+RandomStringUtils.random(MyConfig.lengthOfPINCode, true, true);
			
			for(int i=0; i<3; i++){
				rs = stmt.executeQuery("SELECT * FROM tbl_patient where pin='"+pin+"'");
				if(rs.next()) {
					i--;
				}else{
					i=20;
				}
				rs.close();
			}
			
			sql="insert into tbl_patient(pin, current_status, name, age, sex, tel_num, present_add, permanent_add, ticket_number, reg_number, word_number, bed_number, cabin_number, blood_group, added_by, image_name, other_dept_ref_id) " +
					"values('"+pin+"', "+dto.getDeptId()+", '"+dto.getName()+"', "+dto.getAge()+", '"+dto.getSex()+"', '"+dto.getTelephoneNum()+"', '"+dto.getPresentAdd()+"', '"+dto.getPermanentAdd()+"', '"+dto.getTicketNumber()+"', '"+dto.getRegNumber()+"', '"+dto.getWordNumber()+"', '"+dto.getBedNumber()+"', '"+dto.getCabinNumber()+"', '"+dto.getBloodGroup()+"', "+userID+", '"+dto.imageName+"', "+dto.getOtherDeptRefId()+")";
			System.out.println(sql);
			stmt.execute(sql);
			
			rs = stmt.executeQuery("SELECT * FROM tbl_patient where pin='"+pin+"'");
			if(rs.next()) {
				dto.setAccId(rs.getInt("id"));
			}
			rs.close();
			
			
			sql="insert into tbl_patient_log(patient_id, work) values("+dto.getAccId()+", 'Admitted Today')";
			System.out.println(sql);
			stmt.execute(sql);
			
			/*for(int i=0; i<dto.getDiseaseType().length; i++){
				sql="insert into tbl_patient_disease(patient_id, disease_id) values("+dto.getAccId()+", "+dto.getDiseaseType()[i]+")";
				System.out.println(sql);
				stmt.execute(sql);
			}*/
			
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: " + e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public ArrayList<PatientDTO> getPatientDTOs(String startDate, String endDate, String searchBy, String nameTicketPhone, int roleID) {
		Connection conn=null;
		Statement stmt=null;
		Statement stmt2=null;
		ArrayList<PatientDTO> patientDTOs = new ArrayList<PatientDTO>();
		String sql="SELECT (select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id=added_by), id, pin, current_status, surgical_status, name, age, sex, tel_num, present_add, permanent_add, date_of_rec, ticket_number,reg_number, image_name, is_discharged, date_of_adm, date_of_disch, other_dept_ref_id, (select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id=referred_by_form_diagnosis) FROM tbl_patient where id>0";
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			
			if(startDate!=null && startDate.length()>0){
				sql+=" and DATE(date_of_rec)>='"+startDate+"'";
			}
			if(endDate!=null && endDate.length()>0){
				sql+=" and DATE(date_of_rec)<='"+endDate+"'";
			}
			if(searchBy!=null && searchBy.length()>0){
				if(Integer.parseInt(searchBy)==1){
					sql+=" and name like '%"+nameTicketPhone+"%'";
				}else if(Integer.parseInt(searchBy)==2){
					sql+=" and ticket_number like '%"+nameTicketPhone+"%'";
				}else if(Integer.parseInt(searchBy)==3){
					sql+=" and tel_num like '%"+nameTicketPhone+"%'";
				}else if(Integer.parseInt(searchBy)==4){
					sql+=" and reg_number like '%"+nameTicketPhone+"%'";
				}
			}

			if(roleID==MyConfig.diagnosisRoomRole){
				sql+=" and (current_status="+MyConfig.deptDiagnosisRoom;
				sql+=" or current_status="+MyConfig.deptEmergency +")";
			}else if(roleID==MyConfig.minorOTOrOutdoorRole){
				sql+=" and (current_status="+MyConfig.deptOutdoorOrMinorOT;
				sql+=" or current_status="+MyConfig.deptEmergency +")";
			}else if(roleID==MyConfig.SurgeonRole || roleID==MyConfig.AssistantSurgeonRole || roleID==MyConfig.AnesthetistRole || roleID==MyConfig.DoctorForBed){
				sql+=" and (current_status="+MyConfig.deptIndoor;
				sql+=" or current_status="+MyConfig.deptEmergency +")";
			}

			sql+=" order by date_of_rec desc";
			
			System.out.println(sql);
			
	        ResultSet rs=stmt.executeQuery(sql);
	        while(rs.next()){
	        	PatientDTO dto = new PatientDTO();
	        	
	        	dto.addedBy=rs.getString(1);
	        	dto.setAccId(rs.getInt("id"));
	        	dto.setPin(rs.getString("pin"));
	        	dto.setDeptId(rs.getInt("current_status"));
				dto.setSurgicalStatus(rs.getInt("surgical_status"));
	        	dto.setName(rs.getString("name"));
	        	dto.setAge(rs.getInt("age"));
	        	dto.setSex(rs.getString("sex"));
	        	dto.setTelephoneNum(rs.getString("tel_num"));
	        	dto.setPresentAdd(rs.getString("present_add"));
	        	dto.setPermanentAdd(rs.getString("permanent_add"));
	        	dto.setDateOfRec(rs.getString("date_of_rec"));
	        	dto.setTicketNumber(rs.getString("ticket_number"));
				dto.setRegNumber(rs.getString("reg_number"));
	        	dto.imageName = rs.getString("image_name");
	        	dto.dischargeStatus = rs.getInt("is_discharged");
	        	dto.date_of_adm = rs.getString("date_of_adm");
	        	dto.date_of_disch = rs.getString("date_of_disch");
	        	dto.setOtherDeptRefId(rs.getInt("other_dept_ref_id"));
	        	dto.referredBy=rs.getString(18);
	        	
	        	/*dto.diseaseTypeHash = new HashSet<Integer>();
	        	if(dto.getDeptId()==MyConfig.deptIndoor){
		        	sql="select * from tbl_patient_disease where patient_id="+dto.getAccId();
		        	System.out.println(sql);
		        	ResultSet rsInn = stmt2.executeQuery(sql);
		        	while(rsInn.next()){
		        		dto.diseaseTypeHash.add(rsInn.getInt("disease_id"));
		        	}
		        	rsInn.close();
	        	}*/
	        	
	        	patientDTOs.add(dto);
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{stmt2.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return patientDTOs;
	}
	
	public PatientDTO getPatientDTObyId(int accId) {
		Connection conn=null;
		Statement stmt=null;
		PatientDTO patientDTO = new PatientDTO();
		String sql="SELECT * FROM tbl_patient where id="+accId;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			System.out.println(sql);
			
	        ResultSet rs=stmt.executeQuery(sql);
	        if(rs.next()){
	        	patientDTO.setAccId(rs.getInt("id"));
	        	patientDTO.setPin(rs.getString("pin"));
	        	patientDTO.setDeptId(rs.getInt("current_status"));
				patientDTO.setSurgicalStatus(rs.getInt("surgical_status"));
	        	patientDTO.setName(rs.getString("name"));
	        	patientDTO.setAge(rs.getInt("age"));
	        	patientDTO.setSex(rs.getString("sex"));
	        	patientDTO.setTelephoneNum(rs.getString("tel_num"));
	        	patientDTO.setPresentAdd(rs.getString("present_add"));
	        	patientDTO.setPermanentAdd(rs.getString("permanent_add"));
	        	patientDTO.setDateOfRec(rs.getString("date_of_rec"));
	        	patientDTO.setTicketNumber(rs.getString("ticket_number"));
	        	patientDTO.setRegNumber(rs.getString("reg_number"));
	        	patientDTO.setWordNumber(rs.getString("word_number"));
	        	patientDTO.setBedNumber(rs.getString("bed_number"));
	        	patientDTO.setCabinNumber(rs.getString("cabin_number"));
	        	patientDTO.setBloodGroup(rs.getString("blood_group")); 
	        	patientDTO.setOtherDeptRefId(rs.getInt("other_dept_ref_id"));
	        	patientDTO.imageName=rs.getString("image_name");
	        	patientDTO.date_of_adm=rs.getString("date_of_rec");
	        }
	        rs.close();
	        
	        sql="select * from tbl_patient_disease where patient_id="+patientDTO.getAccId();
        	System.out.println(sql);
        	rs = stmt.executeQuery(sql);
        	patientDTO.diseaseTypeHash = new HashSet<Integer>();
        	while(rs.next()){
        		patientDTO.diseaseTypeHash.add(rs.getInt("disease_id"));
        	}
        	rs.close();
        	
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return patientDTO;
	}

	public DAOResult updatePatientDTO(PatientDTO dto, LoginDTO loginDTO) {
		
		Connection conn=null;
		Statement stmt=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="";
			
			PatientDTO patientOldDTO=getPatientDTObyId(dto.getAccId());
			if(patientOldDTO.getDeptId()!=dto.getDeptId()){
				System.out.println("Department Change Request for id "+dto.getAccId());
				if(patientOldDTO.getDeptId()==MyConfig.deptDiagnosisRoom){
					sql="update tbl_patient set referred_by_form_diagnosis="+loginDTO.getUserID()+" where id="+dto.getAccId();
					System.out.println(sql);
					stmt.executeUpdate(sql);
				}
			}
			
			sql="update tbl_patient set ticket_number='"+dto.getTicketNumber()+"', reg_number='"+dto.getRegNumber()+"', word_number='"+dto.getWordNumber()+"', bed_number='"+dto.getBedNumber()+"', cabin_number='"+dto.getCabinNumber()+"', blood_group='"+dto.getBloodGroup()+"', current_status="+dto.getDeptId()+", name='"+dto.getName()+"', age="+dto.getAge()+", sex='"+dto.getSex()+"', tel_num='"+dto.getTelephoneNum()+"', present_add='"+dto.getPresentAdd()+"', permanent_add='"+dto.getPermanentAdd()+"', other_dept_ref_id="+dto.getOtherDeptRefId()+", bed_doctor_id="+dto.getBedDoctorID()+" where id="+dto.getAccId();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			sql="insert into tbl_patient_log(patient_id, work) values("+dto.getAccId()+", 'Update Today')";
			System.out.println(sql);
			stmt.execute(sql);

			if(dto.getSurgicalStatus()>0){
				sql="update tbl_patient set surgical_status = "+dto.getSurgicalStatus()+" where id="+dto.getAccId();
				System.out.println(sql);
				stmt.execute(sql);
			}
			if(dto.getSurgicalStatus()==1){
				sql="update tbl_patient set date_of_adm = now() where id="+dto.getAccId();
				System.out.println(sql);
				stmt.execute(sql);
			}
			/*ResultSet rs = null;
			for(int i=0; i<dto.getDiseaseType().length; i++){
				sql="select * from tbl_patient_disease where patient_id="+dto.getAccId()+" and disease_id="+dto.getDiseaseType()[i];
				boolean isNew=true;
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					isNew=false;
				}
				rs.close();
				if(isNew){
					sql="insert into tbl_patient_disease(patient_id, disease_id) values("+dto.getAccId()+", "+dto.getDiseaseType()[i]+")";
					System.out.println(sql);
					stmt.execute(sql);
				}
			}*/
			
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: " + e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	
	public String DischargePatientByID(int patientID) {
		
		Connection conn=null;
		Statement stmt=null;
		
		String message="Operation Successfull";
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="update tbl_patient set is_discharged=1, date_of_disch=NOW() where id="+patientID;
			System.out.println(sql);
			stmt.executeUpdate(sql);
						
			sql="insert into tbl_patient_log(patient_id, work) values("+patientID+", 'Discharged Today')";
			System.out.println(sql);
			stmt.execute(sql);
			
		}catch(Exception e){
			message="Database Error: "+e.toString();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return message;
	}

	public DAOResult UpdateIndoorDiseaseInfo(PatientDTO dto) {
		Connection conn=null;
		Statement stmt=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="";
			
			ResultSet rs = null;
			for(int i=0; i<dto.getDiseaseType().length; i++){
				sql="select * from tbl_patient_disease where patient_id="+dto.getAccId()+" and disease_id="+dto.getDiseaseType()[i];
				boolean isNew=true;
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					isNew=false;
				}
				rs.close();
				if(isNew){
					sql="insert into tbl_patient_disease(patient_id, disease_id) values("+dto.getAccId()+", "+dto.getDiseaseType()[i]+")";
					System.out.println(sql);
					stmt.execute(sql);
				}
			}
			
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}

	public ArrayList<String> getPatientImagesList(int accountID) {
		Connection conn=null;
		Statement stmt=null;
		ArrayList<String> list = new ArrayList<String>();
		String sql="SELECT * FROM tbl_patient_images where patient_id="+accountID;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			System.out.println(sql);
			
	        ResultSet rs=stmt.executeQuery(sql);
	        while(rs.next()){
	        	list.add(rs.getString("file_name")); 
	        }
	        rs.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return list;
	}

	public HashMap<Integer, String> getPatientInfoAllDeptByYear(int year) {
		Connection conn=null;
		Statement stmt=null;
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		String sql="select IFNULL(sum(CASE WHEN current_status=1 THEN 1 END), 0) AS Outdoor, IFNULL(sum(CASE WHEN current_status=2 THEN 1 END), 0) AS MinorOT, IFNULL(sum(CASE WHEN current_status=3 THEN 1 END), 0) AS Indoor, IFNULL(sum(CASE WHEN current_status=4 THEN 1 END), 0) AS Emergency, MONTH(date_of_rec) from tbl_patient where YEAR(date_of_rec)="+year+" group by MONTH(date_of_rec)";
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			System.out.println(sql);
			
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	list.put(rs.getInt(5), rs.getString(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4));
	        }
	        rs.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return list;
	}


	public ArrayList<PatientDTO> getCurrentWardStatus() {
		Connection conn=null;
		Statement stmt=null;

		ArrayList<PatientDTO> patientDTOs = new ArrayList<PatientDTO>();
		String sql="SELECT (select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id=added_by), id, pin, current_status, surgical_status, name, age, sex, tel_num, present_add, permanent_add, date_of_rec, ticket_number,reg_number, image_name, is_discharged, date_of_adm, date_of_disch, other_dept_ref_id, (select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id=bed_doctor_id) as bedDoc, word_number,bed_number,cabin_number FROM tbl_patient where id>0";
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();

			sql+=" and surgical_status in (1,2,3) order by date_of_rec desc";

			System.out.println(sql);

			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				PatientDTO dto = new PatientDTO();

				dto.addedBy=rs.getString(1);
				dto.setAccId(rs.getInt("id"));
				dto.setPin(rs.getString("pin"));
				dto.setDeptId(rs.getInt("current_status"));
				dto.setSurgicalStatus(rs.getInt("surgical_status"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setSex(rs.getString("sex"));
				dto.setTelephoneNum(rs.getString("tel_num"));
				dto.setPresentAdd(rs.getString("present_add"));
				dto.setPermanentAdd(rs.getString("permanent_add"));
				dto.setDateOfRec(rs.getString("date_of_rec"));
				dto.setTicketNumber(rs.getString("ticket_number"));
				dto.setRegNumber(rs.getString("reg_number"));
				dto.imageName = rs.getString("image_name");
				dto.dischargeStatus = rs.getInt("is_discharged");
				dto.date_of_adm = rs.getString("date_of_adm");
				dto.date_of_disch = rs.getString("date_of_disch");
				dto.setOtherDeptRefId(rs.getInt("other_dept_ref_id"));
				dto.setWordNumber(rs.getString("word_number"));
				dto.setCabinNumber(rs.getString("cabin_number"));
				dto.setBedNumber(rs.getString("bed_number"));
				dto.referredBy=rs.getString("bedDoc");

	        	/*dto.diseaseTypeHash = new HashSet<Integer>();
	        	if(dto.getDeptId()==MyConfig.deptIndoor){
		        	sql="select * from tbl_patient_disease where patient_id="+dto.getAccId();
		        	System.out.println(sql);
		        	ResultSet rsInn = stmt2.executeQuery(sql);
		        	while(rsInn.next()){
		        		dto.diseaseTypeHash.add(rsInn.getInt("disease_id"));
		        	}
		        	rsInn.close();
	        	}*/

				patientDTOs.add(dto);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return patientDTOs;
	}
}
