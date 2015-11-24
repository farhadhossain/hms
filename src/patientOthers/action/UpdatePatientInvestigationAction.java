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


public class UpdatePatientInvestigationAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setInfoId(form.getInfoId());
		
		dto.setInfoId75(form.getInfoId75());
		dto.setInfoId76(form.getInfoId76());
		dto.setInfoId86(form.getInfoId86());
		dto.setInfoId87(form.getInfoId87());
		dto.setInfoId92(form.getInfoId92());
		dto.setInfoId93(form.getInfoId93());
		dto.setInfoId94(form.getInfoId94());
		dto.setInfoId95(form.getInfoId95());
		dto.setInfoId96(form.getInfoId96());
		dto.setInfoId97(form.getInfoId97());		
		dto.setInfoId98_1(form.getInfoId98_1());
		dto.setInfoId98_2(form.getInfoId98_2());
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.updatePatientInvestigationDTO(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
