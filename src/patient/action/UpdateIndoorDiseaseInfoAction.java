package patient.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import patient.PatientDTO;
import patient.PatientService;
import patient.form.PatientForm;


import utility.DAOResult;
import utility.SessionManager;


public class UpdateIndoorDiseaseInfoAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		
		String target = "success";
		if(loginDTO!=null){
			PatientForm form = (PatientForm) p_form;
			PatientDTO dto = new PatientDTO();
			
			dto.setAccId(form.getAccId());
			dto.setDiseaseType(form.getDiseaseType());
			
			PatientService service = new PatientService();
			DAOResult daoResult=service.UpdateIndoorDiseaseInfo(dto);
			if(daoResult.isValid()==false){
				target="failure";
			}
			p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		}else{
			p_request.getSession(true).setAttribute(SessionManager.PatientAdd, "Invalid Login");
		}
		return (p_mapping.findForward(target));
		
	}
}
