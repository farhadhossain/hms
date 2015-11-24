package pastSergHistory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pastSergHistory.PastSurgicalHistoryDTO;
import pastSergHistory.PastSurgicalHistoryService;
import pastSergHistory.form.PastSurgicalHistoryForm;
import utility.DAOResult;
import utility.SessionManager;

public class AddSurgicalHistoryAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response)
	{

		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		if(loginDTO==null){
			return (p_mapping.findForward("success"));
		}
		
		PastSurgicalHistoryForm form = (PastSurgicalHistoryForm) p_form;
		String target = "success";
		
		PastSurgicalHistoryDTO dto = new PastSurgicalHistoryDTO();
		
		dto.setUserID(form.getUserID());
		dto.setAccAndInjuries(form.getAccAndInjuries());
		dto.setCompliOfAnesthesia(form.getCompliOfAnesthesia());
		dto.setDateOfOp(form.getDateOfOp());
		dto.setNameOfOp(form.getNameOfOp());
		dto.setOthers(form.getOthers());
		dto.setPostOperativeCompli(form.getPostOperativeCompli());
		
		dto.setAnesthetistIDList(form.getAnesthetistIDList());
		dto.setAssistSurgIDList(form.getAssistSurgIDList());
		dto.setSurgeonIDList(form.getSurgeonIDList());
		
		PastSurgicalHistoryService service = new PastSurgicalHistoryService();
		DAOResult daoResult=service.NewSurgicalHistory(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientSurgicalHistory, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
		
}
