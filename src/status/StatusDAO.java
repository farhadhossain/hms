package status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class StatusDAO {

	public HashMap<Integer, String> getAllStatus() {
		HashMap<Integer, String> statusList = new HashMap<Integer, String>();
		String sql=null;
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			sql="select * from tbl_status";
			
	        stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        while(rs.next()){
	        	statusList.put(rs.getInt("id"), rs.getString("name"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return statusList;
	}

}
