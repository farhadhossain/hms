package patient.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.io.output.*;

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


public class AddNewPatientAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		
		String target = "success";
		if(loginDTO!=null){
			
			PatientForm form = (PatientForm) p_form;
			PatientDTO dto = new PatientDTO();
			
			dto.setAge(form.getAge());
			System.out.println(dto.getAge());
			dto.setDeptId(form.getDeptId());
			dto.setName(form.getName());
			dto.setPresentAdd(form.getPresentAdd());
			dto.setPermanentAdd(form.getPermanentAdd());
			dto.setSex(form.getSex());
			dto.setTelephoneNum(form.getTelephoneNum());
			dto.setTicketNumber(form.getTicketNumber());
			dto.setRegNumber(form.getRegNumber());
			dto.setWordNumber(form.getWordNumber());
			dto.setBedNumber(form.getBedNumber());
			dto.setCabinNumber(form.getCabinNumber());
			dto.setBloodGroup(form.getBloodGroup());
			
			PatientService service = new PatientService();
			DAOResult daoResult= service.addNewPatient(dto, loginDTO.getUserID());
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
