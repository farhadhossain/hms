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


public class UpdatePatientOthersInfoAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setExposureTo(form.getExposureTo());
		dto.setHabitsId(form.getHabitsId());
		dto.setMenstrualHistoryId(form.getMenstrualHistoryId());
		dto.setObstetricalHistoryId(form.getObstetricalHistoryId());
		dto.setRelevantDiseaseHistoryId(form.getRelevantDiseaseHistoryId());
		dto.setImmunizationHistoryId(form.getImmunizationHistoryId());
		
		dto.setCigarettesStick(form.getCigarettesStick());
		dto.setCigarettesYear(form.getCigarettesYear());
		dto.setSmokelessTobaccoTime(form.getSmokelessTobaccoTime());
		dto.setSmokelessTobaccoYear(form.getSmokelessTobaccoYear());
		dto.setAlcoholPack(form.getAlcoholPack());
		dto.setAlcoholYear(form.getAlcoholYear());
		dto.setOtherSubsAbuse(form.getOtherSubsAbuse());
		dto.setOtherSubsAbuseYear(form.getOtherSubsAbuseYear());
		dto.setMaritalStatus(form.getMaritalStatus());
		dto.setReligionId(form.getReligionId());
		dto.setSocialStatusId(form.getSocialStatusId());
		dto.setOccupation(form.getOccupation());
		
		dto.setFather(form.getFather());
		dto.setMother(form.getMother());
		dto.setBrother(form.getBrother());
		dto.setSister(form.getSister());
		dto.setWifeOrHusband(form.getWifeOrHusband());
		dto.setConsanguiOfMarri(form.getConsanguiOfMarri());
		dto.setFamlyDisOther(form.getFamlyDisOther());
		dto.setHobbies(form.getHobbies());
		
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.updatePatientOthersDTO(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
