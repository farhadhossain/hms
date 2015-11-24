package patient.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import patient.PatientDTO;
import patient.PatientService;
import patient.form.PatientForm;


import utility.DAOResult;
import utility.SessionManager;


public class MakeDischargeAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		
		String target = "success";
		if(loginDTO!=null){
			String accountID=p_request.getParameter("accountID");
			
			PatientService service = new PatientService();
			String message=service.DischargePatientByID(Integer.parseInt(accountID));
			p_request.getSession(true).setAttribute(SessionManager.PatientAdd, message);
		}else{
			p_request.getSession(true).setAttribute(SessionManager.PatientAdd, "Invalid Login");
		}
		return (p_mapping.findForward(target));
		
	}
}
