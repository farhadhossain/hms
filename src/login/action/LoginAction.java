package login.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDAO;
import login.LoginDTO;
import login.form.LoginForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import role.RoleRepository;
import utility.SessionManager;


public class LoginAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginForm form = (LoginForm) p_form;
		String target = "success";
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO dto = new LoginDTO();
		dto.setuserName(form.getuserName());
		dto.setpassWord(form.getpassWord());
		dto=loginDAO.checkValidation(dto);
		
		if(dto==null){
			target="failure";
			p_request.getSession(true).setAttribute(SessionManager.LoginStatus, "Login Id / Password do not match");
		}else{
			RoleRepository.reload();
			p_request.getSession(true).setAttribute(SessionManager.LoginStatus, dto);
		}
		
		return (p_mapping.findForward(target));
	}
}
