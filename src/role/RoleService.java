package role;

import java.util.ArrayList;

import utility.DAOResult;

public class RoleService {
	
	public DAOResult addRole(RoleDTO dto, ArrayList<Integer> permissionIDList) {
		RoleDAO dao = new RoleDAO();
		return dao.addRole(dto, permissionIDList);
	}
	
	public ArrayList<RoleDTO> getAllRoles(String roleName) {
		RoleDAO dao = new RoleDAO();
		return dao.getAllRoles(roleName);
	}
		
	public RoleDTO getRoleDTO(int roleID) {
		RoleDAO dao = new RoleDAO();
		return dao.getRoleDTO(roleID);
	}

	public DAOResult updateRole(RoleDTO dto, ArrayList<Integer> permissionIDList) {
		RoleDAO dao = new RoleDAO();
		return dao.updateRole(dto, permissionIDList);
	}
}
