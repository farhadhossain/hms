package extraction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import login.LoginDTO;

import utility.DAOResult;
import utility.MyConfig;

public class ExtractionDAO {

	public HashMap<Integer, String> getExtractionListByID() {
		HashMap<Integer, String> deptDisList = new HashMap<Integer, String>();
		String sql="select * from tbl_extraction_list";
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	deptDisList.put(rs.getInt("id"), rs.getString("name"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return deptDisList;
	}


	public List<ExtractionItem> getExtractionItems() {
		List<ExtractionItem> deptDisList = new ArrayList<ExtractionItem>();
		String sql="select * from tbl_extraction_list";
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);

			while(rs.next()){
				ExtractionItem item = new ExtractionItem();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setTaka(rs.getInt("taka"));
				deptDisList.add(item);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return deptDisList;
	}

	public HashMap<Integer, Integer> getExtractionTakaByID() {
		HashMap<Integer, Integer> deptDisList = new HashMap<Integer, Integer>();
		String sql="select * from tbl_extraction_list";
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	deptDisList.put(rs.getInt("id"), rs.getInt("taka"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return deptDisList;
	}
	
	public int getMaxRegNum(boolean isUpdate, int totalTk){
		int regNum=0;
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select max(registration_number) from tbl_current_payment_reg_num");
			if(rs.next()){
				regNum=rs.getInt(1)+1;
			}
			rs.close();
			
			if(isUpdate){
				stmt.executeUpdate("update tbl_current_payment_reg_num set registration_number="+regNum+" and total_taka="+totalTk);
			}
		}catch(Exception e){
			regNum=-1;
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return regNum;
	}

	public DAOResult addPatientExtractionInfo(ExtractionDTO dto, LoginDTO loginDTO) {
		DAOResult daoRes = new DAOResult();
		daoRes.setMessage("Added Successfully");
		int sizeOfArray=0;
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
					
			String sql=null;
			
			if(dto.getUniqIDExtraction()>0){
				sql="update tbl_current_payment_reg_num set registration_number='"+dto.getPaymentRegNumber()+"', refered_by_from_minor_ot="+loginDTO.getUserID()+" where id="+dto.getUniqIDExtraction();
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}else{
				sql="insert into tbl_current_payment_reg_num(patient_id, registration_number, total_taka) values("+dto.getUserId()+", '"+dto.getPaymentRegNumber()+"', "+dto.getTotalTk()+")";
				System.out.println(sql);
				stmt.execute(sql);
				
				rs = stmt.executeQuery("SELECT id FROM tbl_current_payment_reg_num where patient_id="+dto.getUserId()+" and total_taka="+dto.getTotalTk());
				if(rs.next()) {
					dto.setUniqIDExtraction(rs.getInt("id"));
				}
				rs.close();
			}
			
			{
				stmt.execute("delete from tbl_extraction_patient where payment_id="+dto.getUniqIDExtraction());
				
				try{
					sizeOfArray=dto.getExtractionId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				String tail_2="", tail_1="";
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getExtractionId()[i]==1 || dto.getExtractionId1_1().length()>0 || dto.getExtractionId1_2().length()>0 || dto.getExtractionId1_3().length()>0 || dto.getExtractionId1_4().length()>0){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getExtractionId1_1()+"', '"+dto.getExtractionId1_2()+"', '"+dto.getExtractionId1_3()+"', '"+dto.getExtractionId1_4()+"'";
						dto.setExtractionId1_1("");
						dto.setExtractionId1_2("");
						dto.setExtractionId1_3("");
						dto.setExtractionId1_4("");
					}else if(dto.getExtractionId()[i]==2 || dto.getExtractionId2_1().length()>0 || dto.getExtractionId2_2().length()>0 || dto.getExtractionId2_3().length()>0 || dto.getExtractionId2_4().length()>0){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getExtractionId2_1()+"', '"+dto.getExtractionId2_2()+"', '"+dto.getExtractionId2_3()+"', '"+dto.getExtractionId2_4()+"'";
						dto.setExtractionId2_1("");
						dto.setExtractionId2_2("");
						dto.setExtractionId2_3("");
						dto.setExtractionId2_4("");
					}else if(dto.getExtractionId()[i]==3 || dto.getExtractionId3_1().length()>0 || dto.getExtractionId3_2().length()>0 || dto.getExtractionId3_3().length()>0 || dto.getExtractionId3_4().length()>0){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getExtractionId3_1()+"', '"+dto.getExtractionId3_2()+"', '"+dto.getExtractionId3_3()+"', '"+dto.getExtractionId3_4()+"'";
						dto.setExtractionId3_1("");
						dto.setExtractionId3_2("");
						dto.setExtractionId3_3("");
						dto.setExtractionId3_4("");
					}
					
					stmt.execute("insert into tbl_extraction_patient(patient_id, payment_id, extraction_id"+tail_1+") " +
							"values("+dto.getUserId()+", "+dto.getUniqIDExtraction()+", "+dto.getExtractionId()[i]+tail_2+")");
					tail_1="";
					tail_2="";
				}
			}
			
			
		}catch(Exception e){
			daoRes.setMessage("Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}	
		return daoRes;
	}
	
	public ExtractionDTO getExtractionDTOByID(int accountID) {
		ExtractionDTO dto = new ExtractionDTO();
		Connection conn = null;
		Statement stmt = null; 
		dto.patCurExtractionList = new HashSet<Integer>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="SELECT * FROM tbl_current_payment_reg_num where patient_id="+accountID;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				dto.setUniqIDExtraction(rs.getInt("id"));
				dto.setUserId(rs.getInt("patient_id"));
				dto.setTotalTk(rs.getInt("total_taka"));
				dto.setPaymentRegNumber(rs.getString("registration_number"));
			}
			rs.close();
				
			sql="select * from tbl_extraction_patient where payment_id="+dto.getUniqIDExtraction();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				dto.patCurExtractionList.add(rs.getInt("extraction_id"));
				
				if(rs.getInt("extraction_id")==1){
					dto.setExtractionId1_1(rs.getString("input_value")); dto.setExtractionId1_2(rs.getString("input_value_2"));
					dto.setExtractionId1_3(rs.getString("input_value_3")); dto.setExtractionId1_4(rs.getString("input_value_4"));
				}else if(rs.getInt("extraction_id")==2){
					dto.setExtractionId2_1(rs.getString("input_value")); dto.setExtractionId2_2(rs.getString("input_value_2"));
					dto.setExtractionId2_3(rs.getString("input_value_3")); dto.setExtractionId2_4(rs.getString("input_value_4"));
				}else if(rs.getInt("extraction_id")==3){
					dto.setExtractionId3_1(rs.getString("input_value")); dto.setExtractionId3_2(rs.getString("input_value_2"));
					dto.setExtractionId3_3(rs.getString("input_value_3")); dto.setExtractionId3_4(rs.getString("input_value_4"));
				}
				
			}
			rs.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dto;
	}

	public ArrayList<ExtractionDTO> getPatientExtractionDTOList(String startDate, String endDate) {
		Connection conn = null;
		Statement stmt = null; 
		ArrayList<ExtractionDTO> dtoList = new ArrayList<ExtractionDTO>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="select (SELECT CONCAT('Name: ', name, '<br>Phone Number: ', tel_num, '<br>Ticket Number: ', ticket_number) FROM tbl_patient where id=patient_id), (select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id=refered_by_from_minor_ot), id, patient_id, registration_number, total_taka from tbl_current_payment_reg_num where registration_number is not null and registration_number!=''";
			
			if(startDate!=null && startDate.length()>0){
				sql+=" and DATE(date_of_entry)>='"+startDate+"'";
			}
			if(endDate!=null && endDate.length()>0){
				sql+=" and DATE(date_of_entry)<='"+endDate+"'";
			}
			
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ExtractionDTO dto = new ExtractionDTO();
				
				dto.patientInfo=rs.getString(1);
				dto.doctorInfo=rs.getString(2);
				dto.setUniqIDExtraction(rs.getInt("id"));
				dto.setUserId(rs.getInt("patient_id"));
				dto.setPaymentRegNumber(rs.getString("registration_number"));
				dto.setTotalTk(rs.getInt("total_taka"));
				
				dtoList.add(dto);
			}
			rs.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dtoList;
	}
	
	/*
	public ExtractionDTO getPatientDepartmentDiseaseInfo(int accountID, int departmentID) {
		ExtractionDTO dto = new ExtractionDTO();
		Connection conn = null;
		Statement stmt = null; 
		dto.patCurDepartDisList = new HashSet<Integer>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			String sql="select * from tbl_patient_department_disease_info where patient_id="+accountID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				dto.patCurDepartDisList.add(rs.getInt("disease_info_id"));
				
				if(rs.getInt("disease_info_id")==1){
					dto.setDiseaseId1_1(rs.getString("input_value")); dto.setDiseaseId1_2(rs.getString("input_value_2"));
					dto.setDiseaseId1_3(rs.getString("input_value_3")); dto.setDiseaseId1_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==2){
					dto.setDiseaseId2_1(rs.getString("input_value")); dto.setDiseaseId2_2(rs.getString("input_value_2"));
					dto.setDiseaseId2_3(rs.getString("input_value_3")); dto.setDiseaseId2_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==3){
					dto.setDiseaseId3_1(rs.getString("input_value")); dto.setDiseaseId3_2(rs.getString("input_value_2"));
					dto.setDiseaseId3_3(rs.getString("input_value_3")); dto.setDiseaseId3_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==4){
					dto.setDiseaseId4_1(rs.getString("input_value")); dto.setDiseaseId4_2(rs.getString("input_value_2"));
					dto.setDiseaseId4_3(rs.getString("input_value_3")); dto.setDiseaseId4_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==5){
					dto.setDiseaseId5_1(rs.getString("input_value")); dto.setDiseaseId5_2(rs.getString("input_value_2"));
					dto.setDiseaseId5_3(rs.getString("input_value_3")); dto.setDiseaseId5_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==6){
					dto.setDiseaseId6_1(rs.getString("input_value")); dto.setDiseaseId6_2(rs.getString("input_value_2"));
					dto.setDiseaseId6_3(rs.getString("input_value_3")); dto.setDiseaseId6_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==7){
					dto.setDiseaseId7_1(rs.getString("input_value")); dto.setDiseaseId7_2(rs.getString("input_value_2"));
					dto.setDiseaseId7_3(rs.getString("input_value_3")); dto.setDiseaseId7_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==8){
					dto.setDiseaseId8_1(rs.getString("input_value")); dto.setDiseaseId8_2(rs.getString("input_value_2"));
					dto.setDiseaseId8_3(rs.getString("input_value_3")); dto.setDiseaseId8_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==9){
					dto.setDiseaseId9_1(rs.getString("input_value")); dto.setDiseaseId9_2(rs.getString("input_value_2"));
					dto.setDiseaseId9_3(rs.getString("input_value_3")); dto.setDiseaseId9_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==10){
					dto.setDiseaseId10_1(rs.getString("input_value")); dto.setDiseaseId10_2(rs.getString("input_value_2"));
					dto.setDiseaseId10_3(rs.getString("input_value_3")); dto.setDiseaseId10_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==11){
					dto.setDiseaseId11_1(rs.getString("input_value")); dto.setDiseaseId11_2(rs.getString("input_value_2"));
					dto.setDiseaseId11_3(rs.getString("input_value_3")); dto.setDiseaseId11_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==12){
					dto.setDiseaseId12_1(rs.getString("input_value")); dto.setDiseaseId12_2(rs.getString("input_value_2"));
					dto.setDiseaseId12_3(rs.getString("input_value_3")); dto.setDiseaseId12_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==13){
					dto.setDiseaseId13_1(rs.getString("input_value")); dto.setDiseaseId13_2(rs.getString("input_value_2"));
					dto.setDiseaseId13_3(rs.getString("input_value_3")); dto.setDiseaseId13_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==14){
					dto.setDiseaseId14_1(rs.getString("input_value")); dto.setDiseaseId14_2(rs.getString("input_value_2"));
					dto.setDiseaseId14_3(rs.getString("input_value_3")); dto.setDiseaseId14_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==15){
					dto.setDiseaseId15_1(rs.getString("input_value")); dto.setDiseaseId15_2(rs.getString("input_value_2"));
					dto.setDiseaseId15_3(rs.getString("input_value_3")); dto.setDiseaseId15_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==16){
					dto.setDiseaseId16_1(rs.getString("input_value")); dto.setDiseaseId16_2(rs.getString("input_value_2"));
					dto.setDiseaseId16_3(rs.getString("input_value_3")); dto.setDiseaseId16_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==17){
					dto.setDiseaseId17_1(rs.getString("input_value")); dto.setDiseaseId17_2(rs.getString("input_value_2"));
					dto.setDiseaseId17_3(rs.getString("input_value_3")); dto.setDiseaseId17_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==18){
					dto.setDiseaseId18_1(rs.getString("input_value")); dto.setDiseaseId18_2(rs.getString("input_value_2"));
					dto.setDiseaseId18_3(rs.getString("input_value_3")); dto.setDiseaseId18_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==19){
					dto.setDiseaseId19_1(rs.getString("input_value")); dto.setDiseaseId19_2(rs.getString("input_value_2"));
					dto.setDiseaseId19_3(rs.getString("input_value_3")); dto.setDiseaseId19_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==20){
					dto.setDiseaseId20_1(rs.getString("input_value")); dto.setDiseaseId20_2(rs.getString("input_value_2"));
					dto.setDiseaseId20_3(rs.getString("input_value_3")); dto.setDiseaseId20_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==21){
					dto.setDiseaseId21_1(rs.getString("input_value")); dto.setDiseaseId21_2(rs.getString("input_value_2"));
					dto.setDiseaseId21_3(rs.getString("input_value_3")); dto.setDiseaseId21_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==22){
					dto.setDiseaseId22_1(rs.getString("input_value")); dto.setDiseaseId22_2(rs.getString("input_value_2"));
					dto.setDiseaseId22_3(rs.getString("input_value_3")); dto.setDiseaseId22_4(rs.getString("input_value_4"));
				}
				
			}
			rs.close();
			
			sql="select * from tbl_patient_department_disease_others where patient_id="+accountID+" and department_id="+departmentID;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				dto.setAdvice(rs.getString("advice"));
				dto.setDiagnosis(rs.getString("diagnosis"));
			}
			rs.close();
			
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dto;
	}*/


	public DAOResult saveTreatmentPlan(TreatmentPlanDTO dto) {
		DAOResult daoRes = new DAOResult();
		daoRes.setMessage("Added Successfully");
		int sizeOfArray=0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		System.out.println("***************************** From save treatment plan ***********************************");
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			if(dto.getId()>0){
				stmt.execute("delete from tbl_extraction_patient where id="+dto.getId());
			}

			String sql="insert into tbl_extraction_patient(patient_id, extraction_id, input_value, input_value_2, input_value_3, input_value_4, payment_amount, payment_reg_no, is_instrument_provided, is_done) values("+dto.getPatientId()+", "+dto.getExtractionId()+", '"+dto.getInputValue1()+"', '"+dto.getInputValue2()+"', '"+dto.getInputValue3()+"', '"+dto.getInputValue4()+"',"+dto.getPaymentAmount()+",'"+dto.getPaymentRegNo()+"',"+dto.isInstrumentProvided()+","+dto.isDone()+")";
			System.out.println(sql);
			stmt.execute(sql);

			//if(dto.isDone()) {
				sql="SELECT * FROM tbl_logbook where userId="+MyConfig.userID+" and patientId="+dto.getPatientId()+" and roleId="+MyConfig.roleID;
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()==Boolean.FALSE) {
					String upDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
					sql = "insert into tbl_logbook(userId, patientId, updateDate, roleId) " + "values('" + MyConfig.userID + "','" + dto.getPatientId() + "','" + upDate + "','" + MyConfig.roleID + "')";
					System.out.println(sql);
					stmt.execute(sql);
				}
			//}

		}catch(Exception e){
			daoRes.setMessage("Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoRes;
	}

	public List<TreatmentPlanDTO> getTreatmentPlan(int patientId) {
		List<TreatmentPlanDTO> dtos = new ArrayList<TreatmentPlanDTO>();
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();

			String sql="SELECT * FROM tbl_extraction_patient where patient_id="+patientId;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				TreatmentPlanDTO dto = new TreatmentPlanDTO();
				dto.setId(rs.getInt("id"));
				dto.setPatientId(rs.getInt("patient_id"));
				dto.setExtractionId(rs.getInt("extraction_id"));
				dto.setInputValue1(rs.getString("input_value"));
				dto.setInputValue2(rs.getString("input_value_2"));
				dto.setInputValue3(rs.getString("input_value_3"));
				dto.setInputValue4(rs.getString("input_value_4"));
				dto.setPaymentAmount(rs.getString("payment_amount"));
				dto.setPaymentRegNo(rs.getString("payment_reg_no"));
				dto.setInstrumentProvided(rs.getBoolean("is_instrument_provided"));
				dto.setDone(rs.getBoolean("is_done"));
				dtos.add(dto);
			}
			rs.close();

		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dtos;
	}

}
