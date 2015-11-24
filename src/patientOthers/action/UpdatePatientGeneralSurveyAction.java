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


public class UpdatePatientGeneralSurveyAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setInfoId(form.getInfoId());
		
		dto.setInfoId154(form.getInfoId154());
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.updatePatientGeneralSurveyDTO(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
