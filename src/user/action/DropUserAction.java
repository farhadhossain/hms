package user.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import user.UserService;
import utility.LogGeneration;
import utility.SessionManager;

public class DropUserAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		String target = "success";
		if(loginDTO!=null){
			String userID=(String)p_request.getParameter("userID");
			UserService service = new UserService();
			String userName=service.getUserDTO(Integer.parseInt(userID)).getUserName();
			boolean isSuccess=service.dropUser(Integer.parseInt(userID));
			if(isSuccess==true){
				//LogGeneration.insertIntoLogTable("Drop User", "Drop User: "+userName, loginDTO.getuserName());
				p_request.getSession(true).setAttribute(SessionManager.UserAdd, "User deleted Successfully");
			}else{
				p_request.getSession(true).setAttribute(SessionManager.UserAdd, "Failure in deleting user ");
			}
			
			return (p_mapping.findForward(target));
		}else{
			p_request.getSession(true).setAttribute(SessionManager.UserAdd, "Invalid Login");
			return (p_mapping.findForward(target));
		}
	}
}
