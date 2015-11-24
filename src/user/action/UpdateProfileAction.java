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


public class UpdateProfileAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		String target = "success";
		String employeeName=(String)p_request.getParameter("employeeName");
		String newPassword=(String)p_request.getParameter("newPassword");
		if(loginDTO!=null && newPassword!=null && newPassword.length()>0){
			UserService service = new UserService();
			DAOResult daoResult=service.updateProfile(loginDTO, employeeName, newPassword);
			if(daoResult.isValid()==false){
				target="failure";
			}else{
				//LogGeneration.insertIntoLogTable("Update User", "Updated Name: "+loginDTO.getuserName(), loginDTO.getuserName());
			}
			p_request.getSession(true).setAttribute(SessionManager.ProfileUpdate, daoResult.getMessage());
			return (p_mapping.findForward(target));
		}else{
			p_request.getSession(true).setAttribute(SessionManager.ProfileUpdate, "Invalid Login");
			return (p_mapping.findForward(target));
		}
	}
}
