package patientOthers.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import patientOthers.PatientOthersDTO;
import patientOthers.PatientOthersService;
import patientOthers.form.PatientOthersForm;


import utility.DAOResult;
import utility.SessionManager;


public class AddPatientMedicationAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setDrugs(form.getDrugs());
		dto.setDateStarted(form.getDateStarted());
		dto.setDose(form.getDose());
		dto.setADR(form.getAdr());
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.AddPatientMedication(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientDrugAndMedication, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
