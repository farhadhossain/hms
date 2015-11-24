package department.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import department.DepartmentDiseaseDTO;
import department.DepartmentDiseaseService;
import department.form.DepartmentDiseaseForm;

import utility.DAOResult;
import utility.SessionManager;


public class DepartmentDiseaseAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		DepartmentDiseaseForm form = (DepartmentDiseaseForm) p_form;
		String target = "success";
		DepartmentDiseaseDTO dto = new DepartmentDiseaseDTO();
		
		
		int[] initializeValue={0};
		dto.setUserId(form.getUserId());
		dto.setDepartmentId(form.getDepartmentId());
		dto.setDiseaseId(form.getDiseaseId());form.setDiseaseId(initializeValue);
		dto.setDiagnosis(form.getDiagnosis());
		dto.setAdvice(form.getAdvice());
		
		dto.setDiseaseId1_1(form.getDiseaseId1_1());
		dto.setDiseaseId1_2(form.getDiseaseId1_2());
		dto.setDiseaseId1_3(form.getDiseaseId1_3());
		dto.setDiseaseId1_4(form.getDiseaseId1_4());
			
		dto.setDiseaseId2_1(form.getDiseaseId2_1());
		dto.setDiseaseId2_2(form.getDiseaseId2_2());
		dto.setDiseaseId2_3(form.getDiseaseId2_3());
		dto.setDiseaseId2_4(form.getDiseaseId2_4());
			
		dto.setDiseaseId3_1(form.getDiseaseId3_1());
		dto.setDiseaseId3_2(form.getDiseaseId3_2());
		dto.setDiseaseId3_3(form.getDiseaseId3_3());
		dto.setDiseaseId3_4(form.getDiseaseId3_4());
			
		dto.setDiseaseId4_1(form.getDiseaseId4_1());
		dto.setDiseaseId4_2(form.getDiseaseId4_2());
		dto.setDiseaseId4_3(form.getDiseaseId4_3());
		dto.setDiseaseId4_4(form.getDiseaseId4_4());
			
		dto.setDiseaseId5_1(form.getDiseaseId5_1());
		dto.setDiseaseId5_2(form.getDiseaseId5_2());
		dto.setDiseaseId5_3(form.getDiseaseId5_3());
		dto.setDiseaseId5_4(form.getDiseaseId5_4());
			
		dto.setDiseaseId6_1(form.getDiseaseId6_1());
		dto.setDiseaseId6_2(form.getDiseaseId6_2());
		dto.setDiseaseId6_3(form.getDiseaseId6_3());
		dto.setDiseaseId6_4(form.getDiseaseId6_4());
			
		dto.setDiseaseId7_1(form.getDiseaseId7_1());
		dto.setDiseaseId7_2(form.getDiseaseId7_2() );
		dto.setDiseaseId7_3(form.getDiseaseId7_3());
		dto.setDiseaseId7_4(form.getDiseaseId7_4());
			
		dto.setDiseaseId8_1(form.getDiseaseId8_1());
		dto.setDiseaseId8_2(form.getDiseaseId8_2());
		dto.setDiseaseId8_3(form.getDiseaseId8_3());
		dto.setDiseaseId8_4(form.getDiseaseId8_4());
			
		dto.setDiseaseId9_1(form.getDiseaseId9_1());
		dto.setDiseaseId9_2(form.getDiseaseId9_2());
		dto.setDiseaseId9_3(form.getDiseaseId9_3());
		dto.setDiseaseId9_4(form.getDiseaseId9_4());
			
		dto.setDiseaseId10_1(form.getDiseaseId10_1());
		dto.setDiseaseId10_2(form.getDiseaseId10_2());
		dto.setDiseaseId10_3(form.getDiseaseId10_3());
		dto.setDiseaseId10_4(form.getDiseaseId10_4());
			
		dto.setDiseaseId11_1(form.getDiseaseId11_1());
		dto.setDiseaseId11_2(form.getDiseaseId11_2());
		dto.setDiseaseId11_3(form.getDiseaseId11_3());
		dto.setDiseaseId11_4(form.getDiseaseId11_4());
			
		dto.setDiseaseId12_1(form.getDiseaseId12_1());
		dto.setDiseaseId12_2(form.getDiseaseId12_2());
		dto.setDiseaseId12_3(form.getDiseaseId12_3());
		dto.setDiseaseId12_4(form.getDiseaseId12_4());
			
		dto.setDiseaseId13_1(form.getDiseaseId13_1());
		dto.setDiseaseId13_2(form.getDiseaseId13_2());
		dto.setDiseaseId13_3(form.getDiseaseId13_3());
		dto.setDiseaseId13_4(form.getDiseaseId13_4());
			
		dto.setDiseaseId14_1(form.getDiseaseId14_1());
		dto.setDiseaseId14_2(form.getDiseaseId14_2());
		dto.setDiseaseId14_3(form.getDiseaseId14_3());
		dto.setDiseaseId14_4(form.getDiseaseId14_4());
			
		dto.setDiseaseId15_1(form.getDiseaseId15_1());
		dto.setDiseaseId15_2(form.getDiseaseId15_2());
		dto.setDiseaseId15_3(form.getDiseaseId15_3());
		dto.setDiseaseId15_4(form.getDiseaseId15_4());
			
		dto.setDiseaseId16_1(form.getDiseaseId16_1());
		dto.setDiseaseId16_2(form.getDiseaseId16_2());
		dto.setDiseaseId16_3(form.getDiseaseId16_3());
		dto.setDiseaseId16_4(form.getDiseaseId16_4());
			
		dto.setDiseaseId17_1(form.getDiseaseId17_1());
		dto.setDiseaseId17_2(form.getDiseaseId17_2());
		dto.setDiseaseId17_3(form.getDiseaseId17_3());
		dto.setDiseaseId17_4(form.getDiseaseId17_4());
			
		dto.setDiseaseId18_1(form.getDiseaseId18_1());
		dto.setDiseaseId18_2(form.getDiseaseId18_2());
		dto.setDiseaseId18_3(form.getDiseaseId18_3());
		dto.setDiseaseId18_4(form.getDiseaseId18_4());
			
		dto.setDiseaseId19_1(form.getDiseaseId19_1());
		dto.setDiseaseId19_2(form.getDiseaseId19_2());
		dto.setDiseaseId19_3(form.getDiseaseId19_3());
		dto.setDiseaseId19_4(form.getDiseaseId19_4());

		dto.setDiseaseId20_1(form.getDiseaseId20_1());
		dto.setDiseaseId20_2(form.getDiseaseId20_2());
		dto.setDiseaseId20_3(form.getDiseaseId20_3());
		dto.setDiseaseId20_4(form.getDiseaseId20_4());
			
		dto.setDiseaseId21_1(form.getDiseaseId21_1());
		dto.setDiseaseId21_2(form.getDiseaseId21_2());
		dto.setDiseaseId21_3(form.getDiseaseId21_3());
		dto.setDiseaseId21_4(form.getDiseaseId21_4());
			
		dto.setDiseaseId22_1(form.getDiseaseId22_1());
		dto.setDiseaseId22_2(form.getDiseaseId22_2());
		dto.setDiseaseId22_3(form.getDiseaseId22_3());
		dto.setDiseaseId22_4(form.getDiseaseId22_4());
		
		
		DepartmentDiseaseService service = new DepartmentDiseaseService();
		DAOResult daoResult=service.updatePatientDepartmentDiseaseInfo(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
