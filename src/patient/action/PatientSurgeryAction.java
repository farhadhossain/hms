package patient.action;

import login.LoginDTO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import patient.PatientSurgeryDTO;
import patient.PatientSurgeryService;
import patient.form.PatientSurgeryForm;
import utility.DAOResult;
import utility.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientSurgeryAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response)
	{

		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		if(loginDTO==null){
			return (p_mapping.findForward("success"));
		}
		
		PatientSurgeryForm form = (PatientSurgeryForm) p_form;
		String target = "success";
		
		PatientSurgeryDTO dto = new PatientSurgeryDTO();
		
		dto.setUserID(form.getUserID());
		dto.setOperationalNotes(form.getOperationalNotes());
		dto.setCompliOfAnesthesia(form.getCompliOfAnesthesia());
		dto.setDateOfOp(form.getDateOfOp());
		dto.setNameOfOp(form.getNameOfOp());
		dto.setOthers(form.getOthers());
		dto.setPostOperativeCompli(form.getPostOperativeCompli());
		
		dto.setAnesthetistIDList(form.getAnesthetistIDList());
		dto.setAssistSurgIDList(form.getAssistSurgIDList());
		dto.setSurgeonIDList(form.getSurgeonIDList());
		dto.setSurgicalStatus(form.getSurgicalStatus());

		DAOResult daoResult=null;
		PatientSurgeryService service = new PatientSurgeryService();
		if(dto.getSurgicalStatus()==2) {
			daoResult = service.addNewSurgery(dto);
			if(daoResult.isValid()==false){
				target="failure";
			}
		}else if(dto.getSurgicalStatus()==3){
			daoResult = service.makePostOperative(dto);
			if(daoResult.isValid()==false){
				target="failure";
			}
		}else if(dto.getSurgicalStatus()==4){
			daoResult = service.makeDischarge(dto);
			if(daoResult.isValid()==false){
				target="failure";
			}
		}


		p_request.getSession(true).setAttribute(SessionManager.PatientSurgicalHistory, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
		
}
