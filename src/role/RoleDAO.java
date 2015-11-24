package role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import permission.PermissionDTO;
import utility.DAOResult;


public class RoleDAO
{
	
	public DAOResult addRole(RoleDTO dto, ArrayList<Integer> permissionIDList) {
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Role "+dto.getRoleName()+" added Successfully");
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM system_role WHERE ROLE_NAME='"+dto.getRoleName()+"'");
			ResultSet rs=pstmt.executeQuery();
		    if(rs.next()){
		    	daoResult.setValid(false);
				daoResult.setMessage("Error: Role '"+dto.getRoleName()+"' is already exists in the system");
				return daoResult;
		    }
		    
		    String[] ROLE_ID={"ROLE_ID"};
		    pstmt = conn.prepareStatement("INSERT INTO system_role(ROLE_NAME) VALUES (?)", ROLE_ID);
	        pstmt.setString(1, dto.getRoleName());
	        pstmt.execute();
	        rs=pstmt.getGeneratedKeys();
        	if(rs.next()){
        		dto.setRoleID(rs.getInt(1));
        	}
        	
        	for(int i=0;i<permissionIDList.size(); i++){
	        	pstmt = conn.prepareStatement("INSERT INTO role_permission(ROLE_ID, MENU_ID) VALUES (?, ?)");
		        pstmt.setInt(1, dto.getRoleID());
		        pstmt.setInt(2, permissionIDList.get(i));
		        pstmt.execute();
        	}
	        
	        pstmt.close();
        	conn.close();
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Error: Database error: "+e.toString());
			return daoResult;
		}
		return daoResult;
	}

	public ArrayList<RoleDTO> getAllRoles(String roleName) {
		ArrayList<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		String sql=null;
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			sql="SELECT ROLE_ID, ROLE_NAME FROM system_role";
			if(roleName!=null){
				sql+=" WHERE ROLE_NAME LIKE '"+roleName+"%'";
			}
			
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs=pstmt.executeQuery();
	        while(rs.next()){
	        	RoleDTO dto = new RoleDTO();
	        	dto.setRoleID(rs.getInt("ROLE_ID"));
	        	dto.setRoleName(rs.getString("ROLE_NAME"));
	        	roleDTOs.add(dto);
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return roleDTOs;
	}
	
	public RoleDTO getRoleDTO(int roleID) {
		RoleDTO dto = new RoleDTO();
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
	        PreparedStatement pstmt = conn.prepareStatement("SELECT ROLE_NAME FROM system_role WHERE ROLE_ID=?");
	        pstmt.setInt(1, roleID);
	        ResultSet rs=pstmt.executeQuery();
	        if(rs.next()){
	        	dto.setRoleName(rs.getString("ROLE_NAME"));
	        }
	        
	        pstmt = conn.prepareStatement("SELECT MENU_ID FROM role_permission WHERE ROLE_ID=?");
	        pstmt.setInt(1, roleID);
	        rs=pstmt.executeQuery();
	        while(rs.next()){
	        	if(rs.getInt(1)==PermissionDTO.PatientAdd){
	        		dto.setPatientAdd(true);
	        	}else if(rs.getInt(1)==PermissionDTO.PatientSearch){
	        		dto.setPatientSearch(true);
	        	}else if(rs.getInt(1)==PermissionDTO.PatientEdit){
	        		dto.setPatientEdit(true);
	        	}
	        	
	        	else if(rs.getInt(1)==PermissionDTO.ReportView){
	        		dto.setReportView(true);
	        	}
	        	
	        	else if(rs.getInt(1)==PermissionDTO.UserAdd){
	        		dto.setUserAdd(true);
	        	}else if(rs.getInt(1)==PermissionDTO.UserEdit){
	        		dto.setUserEdit(true);
	        	}else if(rs.getInt(1)==PermissionDTO.UserSearch){
	        		dto.setUserSearch(true);
	        	}else if(rs.getInt(1)==PermissionDTO.UserDelete){
	        		dto.setUserDelete(true);
	        	}   
	        	
	        	else if(rs.getInt(1)==PermissionDTO.RoleAdd){
	        		dto.setRoleAdd(true);
	        	}else if(rs.getInt(1)==PermissionDTO.RoleEdit){
	        		dto.setRoleEdit(true);
	        	}else if(rs.getInt(1)==PermissionDTO.RoleSearch){
	        		dto.setRoleSearch(true);
	        	}else if(rs.getInt(1)==PermissionDTO.RoleDelete){
	        		dto.setRoleDelete(true);
	        	} 				
	        }	        
	        
	        rs.close();
	        pstmt.close();
	        conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	public DAOResult updateRole(RoleDTO dto, ArrayList<Integer> permissionIDList) {
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Role "+dto.getRoleName()+" update Successfully");
		try{
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM system_role WHERE ROLE_NAME='"+dto.getRoleName()+"' AND ROLE_ID!="+dto.getRoleID());
			ResultSet rs=pstmt.executeQuery();
		    if(rs.next()){
		    	daoResult.setValid(false);
				daoResult.setMessage("Error: Role '"+dto.getRoleName()+"' is already exists in the system");
				return daoResult;
		    }
		    
		    pstmt = conn.prepareStatement("UPDATE system_role SET ROLE_NAME=? WHERE ROLE_ID=?");
	        pstmt.setString(1, dto.getRoleName());
	        pstmt.setInt(2, dto.getRoleID());
	        pstmt.executeUpdate();
	        
	        pstmt = conn.prepareStatement("DELETE FROM role_permission WHERE ROLE_ID=?");
	        pstmt.setInt(1, dto.getRoleID());
	        pstmt.executeUpdate();
        	
        	for(int i=0;i<permissionIDList.size(); i++){
	        	pstmt = conn.prepareStatement("INSERT INTO role_permission(ROLE_ID, MENU_ID) VALUES (?, ?)");
		        pstmt.setInt(1, dto.getRoleID());
		        pstmt.setInt(2, permissionIDList.get(i));
		        pstmt.execute();
        	}
	                
	        pstmt.close();
        	conn.close();
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Error: Database error: "+e.toString());
			return daoResult;
		}
		return daoResult;
	}
}
