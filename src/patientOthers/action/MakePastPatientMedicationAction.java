package patientOthers.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import patientOthers.PatientOthersService;


import utility.DAOResult;
import utility.SessionManager;


public class MakePastPatientMedicationAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		String id = p_request.getParameter("id");
		String target = "success";
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.makePastPatientMedication(Integer.parseInt(id));
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientDrugAndMedication, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
