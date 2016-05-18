package dashboard;

import utility.MyConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashBoardDAO {
	public DashBoardDTO getSystemInfo(){
		DashBoardDTO dto = new DashBoardDTO();
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        
			String sql="select count(*) from tbl_patient";
			System.out.println(sql);
	        ResultSet rs=stmt.executeQuery(sql);
	        if(rs.next()){
	        	dto.total=rs.getInt(1);
	        }
	        rs.close();
	        
	        sql="select count(DISTINCT p.id) from tbl_patient p join visit v on p.id=v.patient_id where DATE(v.date_of_rec)=DATE(NOW())";
			System.out.println(sql);
	        rs=stmt.executeQuery(sql);
	        if(rs.next()){
	        	dto.todaysTotal=rs.getInt(1);
	        }
	        rs.close();
	        
	        sql="SELECT count(*) FROM tbl_user where user_type="+MyConfig.userDoctor;
			System.out.println(sql);
	        rs=stmt.executeQuery(sql);
	        if(rs.next()){
	        	dto.totalDoctor=rs.getInt(1);
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
}
