package user;

import login.LoginDTO;
import utility.DAOResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDAO
{
	
	public DAOResult addUser(UserDTO dto) {
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("User: "+dto.getUserName()+" added Successfully");
		
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tbl_user WHERE user_name='"+dto.getUserName()+"'");
			ResultSet rs=pstmt.executeQuery();
		    if(rs.next()){
		    	daoResult.setValid(false);
				daoResult.setMessage("Error: User '"+dto.getUserName()+"' is already exists in the system");
				return daoResult;
		    }
		    
		    
		    
	        pstmt = conn.prepareStatement("INSERT INTO tbl_user(user_name, user_pass, role_id, employee_name, phone_number, user_type, designation, role_list, student_id, program, phase,block) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        pstmt.setString(1, dto.getUserName());
	        pstmt.setString(2, dto.getPassword());
	        pstmt.setInt(3, dto.getRoleID());
	        pstmt.setString(4, dto.getEmployeeName() == null ? "" : dto.getEmployeeName());
	        pstmt.setString(5, dto.getEmployeePhone());
	        pstmt.setInt(6, dto.getEmployeeType());
			pstmt.setString(7, dto.getDesignation());
			pstmt.setString(8, dto.getRoleList());
			pstmt.setString(9, dto.getStudentId());
			pstmt.setString(10, dto.getProgram());
			pstmt.setString(11, dto.getPhase());
			pstmt.setString(12, dto.getBlock());
	        pstmt.execute();
	        
	        pstmt.close();
	        conn.close();
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database error:"+e.toString());
			return daoResult;
		}
		return daoResult;
	}

	public ArrayList<UserDTO> getAllUsers(String userName, int roleType) {
		ArrayList<UserDTO> destDTOs = new ArrayList<UserDTO>();
		String sql=null;
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			sql="SELECT * FROM tbl_user where id>0 ";
			if(userName!=null){
				sql+=" and user_name LIKE '"+userName+"%'";
			}
			if(roleType>0){
				sql+=" and role_id="+roleType;
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs=pstmt.executeQuery();
	        while(rs.next()){
	        	UserDTO dto = new UserDTO();
	        	dto.setUserID(rs.getInt("id"));
	        	dto.setUserName(rs.getString("user_name"));
	        	dto.setPassword(rs.getString("user_pass"));
	        	dto.setEmployeeName(rs.getString("employee_name"));
	        	dto.setEmployeePhone(rs.getString("phone_number"));
	        	dto.setEmployeeType(rs.getInt("user_type"));
				dto.setDesignation(rs.getString("designation"));
				dto.setStudentId(rs.getString("student_id"));
				dto.setProgram(rs.getString("program"));
				dto.setPhase(rs.getString("phase"));
				dto.setBlock(rs.getString("block"));
	        	destDTOs.add(dto);
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return destDTOs;
	}
	

	public boolean dropUser(int userID) {
		boolean isValid=true;
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
	        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM tbl_user WHERE id=?");
	        pstmt.setInt(1, userID);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
		}catch(Exception e){
			isValid=false;
			e.printStackTrace();
		}
		return isValid;
	}
	
	public UserDTO getUserDTO(int userID) {
		UserDTO dto = new UserDTO();
		Connection conn=null;
		PreparedStatement pstmt =null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
	        pstmt = conn.prepareStatement("SELECT * FROM tbl_user WHERE id=?");
	        pstmt.setInt(1, userID);
	        ResultSet rs=pstmt.executeQuery();
	        if(rs.next()){
	        	dto.setUserID(rs.getInt("id"));
	        	dto.setUserName(rs.getString("user_name"));
	        	dto.setPassword(rs.getString("user_pass"));
	        	dto.setRoleID(rs.getInt("role_id"));
	        	dto.setEmployeeName(rs.getString("employee_name"));
	        	dto.setEmployeePhone(rs.getString("phone_number"));
				dto.setDesignation(rs.getString("designation"));
	        	dto.setEmployeeType(rs.getInt("user_type"));
				dto.setRoleList(rs.getString("role_list"));
				dto.setStudentId(rs.getString("student_id"));
				dto.setProgram(rs.getString("program"));
				dto.setPhase(rs.getString("phase"));
				dto.setBlock(rs.getString("block"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(conn!=null){conn.close();} }catch(Exception e){}
			try{ if(pstmt!=null){pstmt.close();} }catch(Exception e){}
		}
		return dto;
	}
	
	public DAOResult updateUserDTO(UserDTO dto) {
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("User: "+dto.getUserName()+" updated Successfully");
		Connection conn=null;
		PreparedStatement pstmt =null;
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			
			pstmt = conn.prepareStatement("SELECT * FROM tbl_user WHERE user_name='"+dto.getUserName()+"' AND id!="+dto.getUserID());
			ResultSet rs=pstmt.executeQuery();
		    if(rs.next()){
		    	daoResult.setValid(false);
				daoResult.setMessage("Error: User '"+dto.getUserName()+"' is already exists in the system");
				return daoResult;
		    }
		    
	        pstmt = conn.prepareStatement("UPDATE tbl_user SET user_pass=?, role_id=?, employee_name=?, phone_number=?, user_type=?, designation=?, role_list=? ,student_id=?, program=?, phase=?,block=? WHERE id=?");
	        pstmt.setString(1, dto.getPassword());
	        pstmt.setInt(2, dto.getRoleID());
	        pstmt.setString(3, dto.getEmployeeName());
	        pstmt.setString(4, dto.getEmployeePhone());
	        pstmt.setInt(5, dto.getEmployeeType());
			pstmt.setString(6, dto.getDesignation());
	        pstmt.setString(7, dto.getRoleList());
			pstmt.setString(8, dto.getStudentId());
			pstmt.setString(9, dto.getProgram());
			pstmt.setString(10, dto.getPhase());
			pstmt.setString(11, dto.getBlock());
			pstmt.setInt(12, dto.getUserID());
	        pstmt.executeUpdate();
	        
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database error:"+e.toString());
			return daoResult;
		}finally{
			try{ if(conn!=null){conn.close();} }catch(Exception e){}
			try{ if(pstmt!=null){pstmt.close();} }catch(Exception e){}
		}
		return daoResult;
	}

	public DAOResult updateProfile(LoginDTO loginDTO, String employeeName, String newPassword) {
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		Connection conn=null;
		Statement stmt =null;
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="update tbl_user set employee_name='"+employeeName+"', user_pass='"+newPassword+"' where id="+loginDTO.getUserID();
			if(loginDTO.getUserID()!=-1){
				sql="update tbl_employee set employee_name='"+employeeName+"', user_pass='"+newPassword+"' where id="+loginDTO.getUserID();
			}
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database error:"+e.toString());
			return daoResult;
		}finally{
			try{ if(conn!=null){conn.close();} }catch(Exception e){}
			try{ if(stmt!=null){stmt.close();} }catch(Exception e){}
		}
		return daoResult;
	}
}
