package user.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import user.UserDTO;
import user.UserService;
import user.form.UserForm;
import utility.DAOResult;
import utility.LogGeneration;
import utility.SessionManager;


public class UpdateUserAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		String target = "success";
		if(loginDTO!=null){
			UserForm form = (UserForm) p_form;
			UserDTO dto = new UserDTO();
			dto.setUserID(form.getUserID());
			dto.setUserName(form.getUserName().trim());
			dto.setPassword(form.getPassword().trim());
			dto.setEmployeeName(form.getEmployeeName()==null?"":form.getEmployeeName());
			dto.setRoleID(form.getRoleId());
			dto.setEmployeeType(form.getEmployeeType());
			
			dto.setEmployeePhone(form.getEmployeePhone()==null?"":form.getEmployeePhone());
			UserService service = new UserService();
			DAOResult daoResult=service.updateUserDTO(dto);
			if(daoResult.isValid()==false){
				target="failure";
			}else{
				//LogGeneration.insertIntoLogTable("Update User", "Updated Name: "+dto.getUserName(), loginDTO.getuserName());
			}
			p_request.getSession(true).setAttribute(SessionManager.UserAdd, daoResult.getMessage());
			return (p_mapping.findForward(target));
		}else{
			p_request.getSession(true).setAttribute(SessionManager.UserAdd, "Invalid Login");
			return (p_mapping.findForward(target));
		}
	}
}
