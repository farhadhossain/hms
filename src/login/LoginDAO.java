package login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO
{
	public LoginDTO checkValidation(LoginDTO dto){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			pstmt = conn.prepareStatement("select id, role_id, (select ROLE_NAME from system_role where ROLE_ID=tbl_user.role_id), employee_name from tbl_user where user_name=? and user_pass=?");
	        pstmt.setString(1, dto.getuserName());
	        pstmt.setString(2, dto.getpassWord());
	        ResultSet rs = pstmt.executeQuery();
	        
	        if(rs.next()){
	        	dto.setUserID(rs.getInt("id"));
	        	dto.setRoleID(rs.getInt("role_id"));
	        	dto.setClientType(-1);
	        	dto.roleName=rs.getString(3);
	        	dto.employeeName=rs.getString(4);
	        }else{
	        	dto=null;
	        }
	        rs.close();
	        
		}catch(Exception e){
			e.printStackTrace();
			dto=null;
		}finally{
			try{pstmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dto;
	}
}
