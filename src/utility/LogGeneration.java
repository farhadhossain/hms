package utility;

import java.sql.Connection;
import java.sql.Statement;

public class LogGeneration {
	public static void insertIntoLogTable(String moduleName, String description, String loginUserName){
		Connection conn=null;
		Statement stmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into tbl_log(module_name, description, by_whom) values('"+moduleName+"', '"+description+"', '"+loginUserName+"')");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(stmt!=null){stmt.close();} }catch(Exception e){}
			try{ if(conn!=null){conn.close();} }catch(Exception e){}			
		}
	}
}
