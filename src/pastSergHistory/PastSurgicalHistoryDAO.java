package pastSergHistory;

import prescription.VisitDAO;
import utility.DAOResult;
import utility.MyConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PastSurgicalHistoryDAO {
		
	public ArrayList<PastSurgicalHistoryDTO> getPatientStatusLists(int fromStatusID, String startDate, String endDate) {
		ArrayList<PastSurgicalHistoryDTO> dtoList = new ArrayList<PastSurgicalHistoryDTO>();
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
	        	PastSurgicalHistoryDTO dto = new PastSurgicalHistoryDTO();
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
		        	sql="select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id in (select doctor_id from tbl_surgical_history_doctor_mapping where surgical_history_id="+rs.getInt("id")+" and role_id="+MyConfig.SurgeonRole+")";
		        	System.out.println(sql);
		        	ResultSet rs2 = stmt2.executeQuery(sql);
			        while(rs2.next()){
			        	dto.surgeonDescripton+=rs2.getString(1)+"<br>";
			        }
			        rs2.close();
	        	}
	        	
	        	{
		        	dto.assistSurgeonDescripton="";
		        	sql="select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id in (select doctor_id from tbl_surgical_history_doctor_mapping where surgical_history_id="+rs.getInt("id")+" and role_id="+MyConfig.AssistantSurgeonRole+")";
		        	System.out.println(sql);
		        	ResultSet rs2 = stmt2.executeQuery(sql);
			        while(rs2.next()){
			        	dto.assistSurgeonDescripton+=rs2.getString(1)+"<br>";
			        }
			        rs2.close();
	        	}
	        	
	        	
	        	{
		        	dto.anesthetistDescripton="";
		        	sql="select CONCAT('Name: ', employee_name, '<br>Phone Number: ', phone_number) from tbl_user where id in (select doctor_id from tbl_surgical_history_doctor_mapping where surgical_history_id="+rs.getInt("id")+" and role_id="+MyConfig.AnesthetistRole+")";
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
	
	public DAOResult NewSurgicalHistory(PastSurgicalHistoryDTO dto) {
		Connection conn =null;
		Statement stmt =null;
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Added Successfully");
		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserID());
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String[] id={"id"};
			String sql="INSERT INTO tbl_surgical_history(patient_id, visit_id, accident_and_injuries, name_of_op, date_of_op, compli_of_anesthesia, post_operative_comp, others) " +
					"VALUES ("+dto.getUserID()+","+currentVisitId+",'"+dto.getAccAndInjuries()+"','"+dto.getNameOfOp()+"','"+dto.getDateOfOp()+"','"+dto.getCompliOfAnesthesia()+"','"+dto.getPostOperativeCompli()+"','"+dto.getOthers()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql, id);
			
			int accountId=-1;
			ResultSet rs=stmt.getGeneratedKeys();
        	if(rs.next()){
        		accountId=rs.getInt(1);
        	}
        	rs.close();
			
        	for(int i=0;i<dto.getSurgeonIDList().length;i++){
        		sql="insert into tbl_surgical_history_doctor_mapping(surgical_history_id, doctor_id, role_id) values("+accountId+", "+dto.getSurgeonIDList()[i]+", "+MyConfig.SurgeonRole+")";
        		stmt.executeUpdate(sql);
			}
        	
        	for(int i=0;i<dto.getAssistSurgIDList().length;i++){
        		sql="insert into tbl_surgical_history_doctor_mapping(surgical_history_id, doctor_id, role_id) values("+accountId+", "+dto.getAssistSurgIDList()[i]+", "+MyConfig.AssistantSurgeonRole+")";
        		stmt.executeUpdate(sql);
			}
        	
        	for(int i=0;i<dto.getAnesthetistIDList().length;i++){
        		sql="insert into tbl_surgical_history_doctor_mapping(surgical_history_id, doctor_id, role_id) values("+accountId+", "+dto.getAnesthetistIDList()[i]+", "+MyConfig.AnesthetistRole+")";
        		stmt.executeUpdate(sql);
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
	
	public PastSurgicalHistoryDTO getPastSurgicalHistoryDTOBySurgicalID(int surID) {
		PastSurgicalHistoryDTO dto = new PastSurgicalHistoryDTO();
		String sql=null;
		Connection conn =null;
		Statement stmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			sql="SELECT * from tbl_surgical_history where id="+surID;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
	        if(rs.next()){
	        	dto.setSurgicalID(rs.getInt("id"));
	        	dto.setUserID(rs.getInt("patient_id"));
	        	
	        	dto.setAccAndInjuries(rs.getString("accident_and_injuries"));
	        	
	        	dto.setCompliOfAnesthesia(rs.getString("compli_of_anesthesia"));
	        	
	        	dto.setDateOfOp(rs.getString("date_of_op"));
	        	
	        	dto.setNameOfOp(rs.getString("name_of_op"));
	        	dto.setOthers(rs.getString("others"));
	        	dto.setPostOperativeCompli(rs.getString("post_operative_comp"));
	        	dto.setSurgicalStatus(rs.getInt("surgical_status"));
	        	dto.updatePerform=rs.getString("update_perform");
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
	
	public ArrayList<PastSurgicalHistoryDTO> getSurgicalHistory(int userID) {
		ArrayList<PastSurgicalHistoryDTO> dtoList = new ArrayList<PastSurgicalHistoryDTO>();
		String sql=null;
		Connection conn =null;
		Statement stmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			sql="SELECT * from tbl_surgical_history where patient_id="+userID;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()){
	        	PastSurgicalHistoryDTO dto = new PastSurgicalHistoryDTO();
	        	
	        	dto.setSurgicalID(rs.getInt("id"));
	        	dto.setUserID(rs.getInt("patient_id"));
	        	
	        	dto.setAccAndInjuries(rs.getString("accident_and_injuries"));
	        	
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
			
			stmt.executeUpdate("update tbl_surgical_history SET surgical_status="+updatedStatus+" WHERE id="+surgicalId);
			
			
		}catch(Exception e){
			message=e.toString();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		
		return message;
	}
	

}
