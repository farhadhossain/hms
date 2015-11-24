package permission;
import java.util.HashMap;

public class LoadPermission {
	private static final HashMap<Integer, String> allPermissionNameAndID = new HashMap<Integer, String>(){
		{
			put(PermissionDTO.PatientAdd, "Patient Add");
			put(PermissionDTO.PatientSearch, "Patient Search");
			put(PermissionDTO.PatientEdit, "Patient Edit");
			
			put(PermissionDTO.ReportView, "Report View");

			put(PermissionDTO.UserAdd, "User Add");
			put(PermissionDTO.UserSearch, "User Search");
			put(PermissionDTO.UserEdit, "User Edit");
			put(PermissionDTO.UserDelete, "User Delete");
			
			put(PermissionDTO.RoleAdd, "Role Add");
			put(PermissionDTO.RoleSearch, "Role Search");
			put(PermissionDTO.RoleEdit, "Role Edit");
			put(PermissionDTO.RoleDelete, "Role Delete");
			
		}
	};
	
	public static String getPermissionModuleNameByID(int id){
		return allPermissionNameAndID.get(id);
	}
	
}
