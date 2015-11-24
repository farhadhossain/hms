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


public class UpdatePatientDrugAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setDrugHistoryId(form.getDrugHistoryId());
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.updatePatientDrugDTO(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientDrugAndMedication, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
