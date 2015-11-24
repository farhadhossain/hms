package role.action;

import java.util.ArrayList;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import permission.PermissionDTO;
import role.RoleDTO;
import role.RoleRepository;
import role.RoleService;
import role.form.RoleForm;

import utility.DAOResult;
import utility.LogGeneration;
import utility.SessionManager;


public class UpdateRoleAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		String target = "success";
		if(loginDTO!=null){
			RoleForm form = (RoleForm) p_form;
			RoleDTO dto = new RoleDTO();
			dto.setRoleName(form.getRoleName());
			dto.setRoleID(form.getRoleID());
			
			ArrayList<Integer> permissionIDList= new ArrayList<Integer>();
			
			if(form.isPatientAdd()){
				permissionIDList.add(PermissionDTO.PatientAdd);
				form.setPatientAdd(false);
			}
			if(form.isPatientEdit()){
				permissionIDList.add(PermissionDTO.PatientEdit);
				form.setPatientEdit(false);
			}
			if(form.isPatientSearch()){
				permissionIDList.add(PermissionDTO.PatientSearch);
				form.setPatientSearch(false);
			}
			
			if(form.isReportView()){
				permissionIDList.add(PermissionDTO.ReportView);
				form.setReportView(false);
			}
					
			if(form.isUserAdd()){
				permissionIDList.add(PermissionDTO.UserAdd);
				form.setUserAdd(false);
			}
			if(form.isUserEdit()){
				permissionIDList.add(PermissionDTO.UserEdit);
				form.setUserEdit(false);
			}
			if(form.isUserSearch()){
				permissionIDList.add(PermissionDTO.UserSearch);
				form.setUserSearch(false);
			}
			if(form.isUserDelete()){
				permissionIDList.add(PermissionDTO.UserDelete);
				form.setUserDelete(false);
			}
			
			if(form.isRoleAdd()){
				permissionIDList.add(PermissionDTO.RoleAdd);
				form.setRoleAdd(false);
			}
			if(form.isRoleEdit()){
				permissionIDList.add(PermissionDTO.RoleEdit);
				form.setRoleEdit(false);
			}
			if(form.isRoleSearch()){
				permissionIDList.add(PermissionDTO.RoleSearch);
				form.setRoleSearch(false);
			}
			if(form.isRoleDelete()){
				permissionIDList.add(PermissionDTO.RoleDelete);
				form.setRoleDelete(false);
			}
			
			RoleService service = new RoleService();
			DAOResult daoResult=service.updateRole(dto, permissionIDList);
			if(daoResult.isValid()==false){
				target="failure";
			}else{
				RoleRepository.reload();
				//LogGeneration.insertIntoLogTable("Update Role", "Updated Role: "+dto.getRoleName(), loginDTO.getuserName());
			}
			p_request.getSession(true).setAttribute(SessionManager.RoleAdd, daoResult.getMessage());
			return (p_mapping.findForward(target));
		}else{
			p_request.getSession(true).setAttribute(SessionManager.RoleAdd, "Invalid Login");
			return (p_mapping.findForward(target));
		}
	}
}
