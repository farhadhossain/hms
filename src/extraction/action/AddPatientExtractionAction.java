package extraction.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import extraction.ExtractionDTO;
import extraction.ExtractionService;
import extraction.form.ExtractionForm;

import utility.DAOResult;
import utility.MyConfig;
import utility.SessionManager;


public class AddPatientExtractionAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		
		ExtractionForm form = (ExtractionForm) p_form;
		String target = "success";
		ExtractionDTO dto = new ExtractionDTO();
		
		
		int[] initializeValue={0};
		dto.setUniqIDExtraction(form.getUniqIDExtraction());
		dto.setUserId(form.getUserId());
		dto.setExtractionId(form.getExtractionId());//form.setExtractionId(initializeValue);
		
		//System.out.println("ExtractionId().length: "+dto.getExtractionId().length);
		
		dto.setExtractionId1_1(form.getExtractionId1_1()==null?"":form.getExtractionId1_1());
		dto.setExtractionId1_2(form.getExtractionId1_2()==null?"":form.getExtractionId1_2());
		dto.setExtractionId1_3(form.getExtractionId1_3()==null?"":form.getExtractionId1_3());
		dto.setExtractionId1_4(form.getExtractionId1_4()==null?"":form.getExtractionId1_4());
		
		
		dto.setExtractionId2_1(form.getExtractionId2_1()==null?"":form.getExtractionId2_1());
		dto.setExtractionId2_2(form.getExtractionId2_2()==null?"":form.getExtractionId2_2());
		dto.setExtractionId2_3(form.getExtractionId2_3()==null?"":form.getExtractionId2_3());
		dto.setExtractionId2_4(form.getExtractionId2_4()==null?"":form.getExtractionId2_4());
		
		dto.setExtractionId3_1(form.getExtractionId3_1()==null?"":form.getExtractionId3_1());
		dto.setExtractionId3_2(form.getExtractionId3_2()==null?"":form.getExtractionId3_2());
		dto.setExtractionId3_3(form.getExtractionId3_3()==null?"":form.getExtractionId3_3());
		dto.setExtractionId3_4(form.getExtractionId3_4()==null?"":form.getExtractionId3_4());
		
		dto.setExtractionId4_1(form.getExtractionId4_1()==null?"":form.getExtractionId4_1());
		dto.setExtractionId4_2(form.getExtractionId4_2()==null?"":form.getExtractionId4_2());
		dto.setExtractionId4_3(form.getExtractionId4_3()==null?"":form.getExtractionId4_3());
		dto.setExtractionId4_4(form.getExtractionId4_4()==null?"":form.getExtractionId4_4());
		
		dto.setTotalTk(form.getTotalTk());
		dto.setPaymentRegNumber(form.getPaymentRegNumber());
		
		ExtractionService service = new ExtractionService();
		DAOResult daoResult=service.addPatientExtractionInfo(dto, loginDTO);
		if(daoResult.isValid()==false){
			target="failure";
		}
		
		if(loginDTO.getRoleID()==MyConfig.dutyNurseRole){
			target="dutyNurseSuccess";
		}
		
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
