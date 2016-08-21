package patientOthers.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import patientOthers.PatientOthersDTO;
import patientOthers.PatientOthersService;
import patientOthers.form.PatientOthersForm;
import utility.DAOResult;
import utility.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatePreAnestheticEvaluationAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setInfoId(form.getInfoId());
		                                                                                                               		dto.setCurProb(form.getCurProb()==null?"":form.getCurProb());
		dto.setOtherKnownProb(form.getOtherKnownProb()==null?"":form.getOtherKnownProb());
		dto.setCurProbTreatment(form.getCurProbTreatment()==null?"":form.getCurProbTreatment());
		dto.setCurDose(form.getCurDose()==null?"":form.getCurDose());
		dto.setCurDoseDuration(form.getCurDoseDuration()==null?"":form.getCurDoseDuration());
		dto.setCurDoseEffectiveness(form.getCurDoseEffectiveness()==null?"":form.getCurDoseEffectiveness());
		dto.setCurDrugsUse(form.getCurDrugsUse()==null?"":form.getCurDrugsUse());
		dto.setCurDrugsReason(form.getCurDrugsReason()==null?"":form.getCurDrugsReason());
		dto.setCurDrugsDose(form.getCurDrugsDose()==null?"":form.getCurDrugsDose());
		dto.setCurDrugsDur(form.getCurDrugsDur()==null?"":form.getCurDrugsDur());
		dto.setCurDrugsEffectiveness(form.getCurDrugsEffectiveness()==null?"":form.getCurDrugsEffectiveness());
		dto.setCurDrugsSideEffect(form.getCurDrugsSideEffect()==null?"":form.getCurDrugsSideEffect());
		
		dto.setInfoId99(form.getInfoId99());
		dto.setInfoId100_1(form.getInfoId100_1());
		dto.setInfoId100_2(form.getInfoId100_2());
		dto.setInfoId101(form.getInfoId101());
		dto.setInfoId121(form.getInfoId121());
		dto.setInfoId125(form.getInfoId125());
		dto.setInfoId136(form.getInfoId136());
		dto.setInfoId137(form.getInfoId137());
		dto.setInfoId139(form.getInfoId139());
		dto.setInfoId146(form.getInfoId146());
		dto.setInfoId147(form.getInfoId147());
		dto.setInfoId149_1(form.getInfoId149_1());
		dto.setInfoId149_2(form.getInfoId149_2());
		dto.setInfoId150(form.getInfoId150());
		dto.setInfoId151(form.getInfoId151());
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.updatePatientPreAnestheticEvaluationDTO(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
