package pastSergHistory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pastSergHistory.PastSurgicalHistoryService;
import utility.SessionManager;

public class ChangeSurgicalStatusAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response)
	{

		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		if(loginDTO==null){
			return (p_mapping.findForward("success"));
		}
		
		
		String target = "success";
		String surgicalId=(String)p_request.getParameter("surgicalId");
		String currentStatus=(String)p_request.getParameter("currentStatus");
		
		PastSurgicalHistoryService service = new PastSurgicalHistoryService();
		
		String message=service.UpdateSurgicalHistoryStatus(Integer.parseInt(surgicalId), Integer.parseInt(currentStatus));
		p_request.getSession(true).setAttribute(SessionManager.PatientSurgicalHistory, message);
		return (p_mapping.findForward(target));
	}
		
}
