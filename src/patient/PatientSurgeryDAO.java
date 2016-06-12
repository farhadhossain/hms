package patient;

import prescription.VisitDAO;
import user.UserDTO;
import utility.DAOResult;
import utility.MyConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PatientSurgeryDAO {
		
	public ArrayList<PatientSurgeryDTO> getPatientStatusLists(int fromStatusID, String startDate, String endDate) {
		ArrayList<PatientSurgeryDTO> dtoList = new ArrayList<PatientSurgeryDTO>();
		String sql=null;
		Connection conn =null;
		Statement stmt =null;
		Statement stmt2 =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			sql="select id, (select name from tbl_patient where id=patient_id), (select CONCAT('AGE: ', age, '<br>Ward: ', word_number, '<br>Bed: ', bed_number, '<br>Cabin: ', cabin_number) from tbl_patient where id=patient_id), (select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id=(select bed_doctor_id from tbl_patient where id=patient_id)), accident_and_injuries, compli_of_anesthesia, date_of_op, name_of_op, surgical_status, patient_id from tbl_surgical_history where surgical_status>="+fromStatusID;
			if(startDate!=null && startDate.length()>0){
				sql+=" and DATE(date_of_op)>='"+startDate+"'";
			}
			if(endDate!=null && endDate.length()>0){
				sql+=" and DATE(date_of_op)<='"+endDate+"'";
			}
			
			System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()){
				PatientSurgeryDTO dto = new PatientSurgeryDTO();
	        	dto.setSurgicalID(rs.getInt(1));
	        	dto.patientName=rs.getString(2);
	        	dto.patientDetails=rs.getString(3);
	        	dto.responsibleDoctorBed=rs.getString(4);
	        	dto.setCompliOfAnesthesia(rs.getString("compli_of_anesthesia"));
	        	dto.setDateOfOp(rs.getString("date_of_op"));
	        	dto.setNameOfOp(rs.getString("name_of_op"));
	        	dto.setSurgicalStatus(rs.getInt("surgical_status"));
	        	dto.setUserID(rs.getInt("patient_id"));
	        	
	        	{
		        	dto.surgeonDescripton="";
		        	sql="select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id in (select doctor_id from tbl_patient_surgery_doctor where surgical_history_id="+rs.getInt("id")+" and role_id="+MyConfig.SurgeonRole+")";
		        	System.out.println(sql);
		        	ResultSet rs2 = stmt2.executeQuery(sql);
			        while(rs2.next()){
			        	dto.surgeonDescripton+=rs2.getString(1)+"<br>";
			        }
			        rs2.close();
	        	}
	        	
	        	{
		        	dto.assistSurgeonDescripton="";
		        	sql="select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id in (select doctor_id from tbl_patient_surgery_doctor where surgical_history_id="+rs.getInt("id")+" and role_id="+MyConfig.AssistantSurgeonRole+")";
		        	System.out.println(sql);
		        	ResultSet rs2 = stmt2.executeQuery(sql);
			        while(rs2.next()){
			        	dto.assistSurgeonDescripton+=rs2.getString(1)+"<br>";
			        }
			        rs2.close();
	        	}
	        	
	        	
	        	{
		        	dto.anesthetistDescripton="";
		        	sql="select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id in (select doctor_id from tbl_patient_surgery_doctor where surgical_history_id="+rs.getInt("id")+" and role_id="+MyConfig.AnesthetistRole+")";
		        	System.out.println(sql);
		        	ResultSet rs2 = stmt2.executeQuery(sql);
			        while(rs2.next()){
			        	dto.anesthetistDescripton+=rs2.getString(1)+"<br>";
			        }
			        rs2.close();
	        	}
	        	
	        	dtoList.add(dto);
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{stmt2.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dtoList;		
	}
	
	public DAOResult NewSurgery(PatientSurgeryDTO dto) {
		Connection conn =null;
		Statement stmt =null;
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Added Successfully");
		int currentVisit = new VisitDAO().getCurrentVisitId(dto.getUserID());
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String[] id={"id"};
			String sql="INSERT INTO tbl_patient_surgery(patient_id, name_of_op, date_of_op, visit_id) " +
					"VALUES ("+dto.getUserID()+",'"+dto.getNameOfOp()+"','"+dto.getDateOfOp()+"',"+currentVisit+")";
			System.out.println(sql);
			stmt.executeUpdate(sql, id);
			
			int accountId=-1;
			ResultSet rs=stmt.getGeneratedKeys();
        	if(rs.next()){
        		accountId=rs.getInt(1);
        	}
        	rs.close();
			
        	for(int i=0;i<dto.getSurgeonIDList().length;i++){
        		sql="insert into tbl_patient_surgery_doctor(surgical_history_id, doctor_id, role_id) values("+accountId+", "+dto.getSurgeonIDList()[i]+", "+MyConfig.SurgeonRole+")";
        		stmt.executeUpdate(sql);
			}
        	
        	for(int i=0;i<dto.getAssistSurgIDList().length;i++){
        		sql="insert into tbl_patient_surgery_doctor(surgical_history_id, doctor_id, role_id) values("+accountId+", "+dto.getAssistSurgIDList()[i]+", "+MyConfig.AssistantSurgeonRole+")";
        		stmt.executeUpdate(sql);
			}
        	
        	for(int i=0;i<dto.getAnesthetistIDList().length;i++){
        		sql="insert into tbl_patient_surgery_doctor(surgical_history_id, doctor_id, role_id) values("+accountId+", "+dto.getAnesthetistIDList()[i]+", "+MyConfig.AnesthetistRole+")";
        		stmt.executeUpdate(sql);
			}


			sql="update tbl_patient set surgical_status = 2 where id="+dto.getUserID();
			System.out.println(sql);
			stmt.execute(sql);

        	
		}catch(Exception e){
			System.out.println(e.toString());
			daoResult.setValid(false);
			daoResult.setMessage("Error: Database error: " + e.toString());
			return daoResult;
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public PatientSurgeryDTO getSurgeryByPatientID(int patientId) {
		PatientSurgeryDTO dto = new PatientSurgeryDTO();
		String sql=null;
		Connection conn =null;
		Statement stmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			sql="SELECT * from tbl_patient_surgery where patient_id="+patientId;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
	        if(rs.next()){
	        	dto.setSurgicalID(rs.getInt("id"));
	        	dto.setUserID(rs.getInt("patient_id"));
	        	
	        	dto.setOperationalNotes(rs.getString("operational_notes"));
	        	
	        	dto.setCompliOfAnesthesia(rs.getString("compli_of_anesthesia"));
	        	
	        	dto.setDateOfOp(rs.getString("date_of_op"));
	        	
	        	dto.setNameOfOp(rs.getString("name_of_op"));
	        	dto.setOthers(rs.getString("others"));
	        	dto.setPostOperativeCompli(rs.getString("post_operative_comp"));
	        	dto.setSurgicalStatus(rs.getInt("surgical_status"));
	        	dto.updatePerform=rs.getString("update_perform");
	        }
	        rs.close();
			rs = stmt.executeQuery("select d.doctor_id,u.* from tbl_patient_surgery_doctor d join tbl_user u on d.doctor_id = u.id where d.surgical_history_id="+dto.getSurgicalID()+" and d.role_id="+MyConfig.SurgeonRole);
			rs.last();
			dto.setSurgeonIDList(new int[rs.getRow()]);
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
			  dto.getSurgeonIDList()[i]=rs.getInt("doctor_id");
			  UserDTO userDTO= new UserDTO();
			  userDTO.setEmployeeName(rs.getString("employee_name"));
			  dto.getSurgeonList().add(userDTO);
			  i++;
			}
			rs.close();
			rs = stmt.executeQuery("select d.doctor_id,u.* from tbl_patient_surgery_doctor d join tbl_user u on d.doctor_id = u.id where d.surgical_history_id="+dto.getSurgicalID()+" and d.role_id="+MyConfig.AssistantSurgeonRole);
			rs.last();
			dto.setAssistSurgIDList(new int[rs.getRow()]);
			rs.beforeFirst();
			i = 0;
			while(rs.next()){
				dto.getAssistSurgIDList()[i]=rs.getInt("doctor_id");
				UserDTO userDTO= new UserDTO();
				userDTO.setEmployeeName(rs.getString("employee_name"));
				dto.getAssistSurgList().add(userDTO);
				i++;
			}
			rs.close();
			rs = stmt.executeQuery("select d.doctor_id,u.* from tbl_patient_surgery_doctor d join tbl_user u on d.doctor_id = u.id where d.surgical_history_id="+dto.getSurgicalID()+" and d.role_id="+MyConfig.AnesthetistRole);
			rs.last();
			dto.setAnesthetistIDList(new int[rs.getRow()]);
			rs.beforeFirst();
			i= 0;
			while(rs.next()){
				dto.getAnesthetistIDList()[i]=rs.getInt("doctor_id");
				UserDTO userDTO= new UserDTO();
				userDTO.setEmployeeName(rs.getString("employee_name"));
				dto.getAnesthetistList().add(userDTO);
				i++;
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dto;		
	}
	
	public ArrayList<PatientSurgeryDTO> getSurgicalHistory(int userID) {
		ArrayList<PatientSurgeryDTO> dtoList = new ArrayList<PatientSurgeryDTO>();
		String sql=null;
		Connection conn =null;
		Statement stmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			sql="SELECT * from tbl_patient_surgery where patient_id="+userID;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()){
				PatientSurgeryDTO dto = new PatientSurgeryDTO();
	        	
	        	dto.setSurgicalID(rs.getInt("id"));
	        	dto.setUserID(rs.getInt("patient_id"));
	        	
	        	dto.setOperationalNotes(rs.getString("operational_notes"));
	        	
	        	dto.setCompliOfAnesthesia(rs.getString("compli_of_anesthesia"));
	        	
	        	dto.setDateOfOp(rs.getString("date_of_op"));
	        	
	        	dto.setNameOfOp(rs.getString("name_of_op"));
	        	dto.setOthers(rs.getString("others"));
	        	dto.setPostOperativeCompli(rs.getString("post_operative_comp"));
	        	dto.setSurgicalStatus(rs.getInt("surgical_status"));
	        	dto.updatePerform=rs.getString("update_perform");
	        	
	        	dtoList.add(dto);
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dtoList;		
	}

	public String UpdateSurgicalHistoryStatus(int surgicalId, int currentStatus) {
		String message="Successfull";
		Connection conn =null;
		Statement stmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			int updatedStatus=(currentStatus+1);
			if(currentStatus==2){
				updatedStatus=-1;
			}

			stmt.executeUpdate("update tbl_patient_surgery SET surgical_status="+updatedStatus+" WHERE id="+surgicalId);
		}catch(Exception e){
			message=e.toString();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		
		return message;
	}


	public DAOResult makePostOperative(PatientSurgeryDTO dto) {
		Connection conn =null;
		Statement stmt =null;
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();


			String sql="update tbl_patient_surgery set compli_of_anesthesia='"+dto.getCompliOfAnesthesia()+"', post_operative_comp='"+dto.getPostOperativeCompli()+"', others='"+dto.getOthers()+"', operational_notes='"+dto.getOperationalNotes()+"' where id="+dto.getSurgicalID();
			System.out.println(sql);
			stmt.executeUpdate(sql);

			stmt.executeUpdate("delete from tbl_patient_surgery_doctor where surgical_history_id="+dto.getSurgicalID());


        	for(int i=0;i<dto.getSurgeonIDList().length;i++){
        		sql="insert into tbl_patient_surgery_doctor(surgical_history_id, doctor_id, role_id) values("+dto.getSurgicalID()+", "+dto.getSurgeonIDList()[i]+", "+MyConfig.SurgeonRole+")";
        		stmt.executeUpdate(sql);
			}

        	for(int i=0;i<dto.getAssistSurgIDList().length;i++){
        		sql="insert into tbl_patient_surgery_doctor(surgical_history_id, doctor_id, role_id) values("+dto.getSurgicalID()+", "+dto.getAssistSurgIDList()[i]+", "+MyConfig.AssistantSurgeonRole+")";
        		stmt.executeUpdate(sql);
			}

        	for(int i=0;i<dto.getAnesthetistIDList().length;i++){
        		sql="insert into tbl_patient_surgery_doctor(surgical_history_id, doctor_id, role_id) values("+dto.getSurgicalID()+", "+dto.getAnesthetistIDList()[i]+", "+MyConfig.AnesthetistRole+")";
        		stmt.executeUpdate(sql);
			}


			sql="update tbl_patient set surgical_status = 3 where id="+dto.getUserID();
			System.out.println(sql);
			stmt.execute(sql);

			int size = dto.getAssistSurgIDList().length;

			System.out.println("********************* from MakePostOperative with size = "+size+" *********************");
			for(int i = 0; i < size; i++){

				int user = dto.getAssistSurgIDList()[i];
				int role = MyConfig.AssistantSurgeonRole;

				String upDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
				sql = "insert into tbl_logbook(userId, patientId, updateDate, roleId) " + "values('" + user + "','" + dto.getUserID() + "','" + upDate + "','" + role + "')";
				System.out.println(sql);
				stmt.execute(sql);

				//System.out.println("********************* from MakePostOperative with id = "+dto.getAssistSurgIDList()[i]+" *********************");
			}

		}catch(Exception e){
			System.out.println(e.toString());
			daoResult.setValid(false);
			daoResult.setMessage("Error: Database error: "+e.toString());
			return daoResult;
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}

	public DAOResult makeDiscahrge(PatientSurgeryDTO dto) {
		Connection conn =null;
		Statement stmt =null;
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();

			String sql="update tbl_patient_surgery set compli_of_anesthesia='"+dto.getCompliOfAnesthesia()+"', post_operative_comp='"+dto.getPostOperativeCompli()+"', others='"+dto.getOthers()+"', operational_notes='"+dto.getOperationalNotes()+"' where patient_id="+dto.getUserID();
			System.out.println(sql);
			//stmt.executeUpdate(sql);

			sql="update tbl_patient set surgical_status = 4, is_discharged=1, date_of_disch=NOW() where id="+dto.getUserID();
			System.out.println(sql);
			stmt.execute(sql);

		}catch(Exception e){
			System.out.println(e.toString());
			daoResult.setValid(false);
			daoResult.setMessage("Error: Database error: "+e.toString());
			return daoResult;
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	

}
