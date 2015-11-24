package disease.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import disease.DiseaseService;
import utility.SessionManager;


public class AddNewFindingsAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		String target = "success";
		
		String newFindings = p_request.getParameter("newFindings");
		String newFindingsUserId = p_request.getParameter("newFindingsUserId");
		String newFindingsDiseaseId = p_request.getParameter("newFindingsDiseaseId");
		if(newFindings!=null && newFindings.length()>0){
			DiseaseService service = new DiseaseService();
			String message=service.addNewFollowUp(Integer.parseInt(newFindingsUserId), Integer.parseInt(newFindingsDiseaseId), newFindings);
			p_request.getSession(true).setAttribute(SessionManager.PatientNewFindings, message);
		}else{
			p_request.getSession(true).setAttribute(SessionManager.PatientNewFindings, "Invalid");
		}
		return (p_mapping.findForward(target));
	}
}
