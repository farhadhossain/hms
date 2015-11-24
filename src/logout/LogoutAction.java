package logout;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import utility.SessionManager;


public class LogoutAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		String target="success";
		p_request.getSession(true).removeAttribute(SessionManager.LoginStatus);
		return (p_mapping.findForward(target));
	}
}
