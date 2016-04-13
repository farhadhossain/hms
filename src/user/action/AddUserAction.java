package user.action;

import login.LoginDTO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import user.UserDTO;
import user.UserService;
import user.form.UserForm;
import utility.DAOResult;
import utility.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AddUserAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		LoginDTO loginDTO=(LoginDTO)p_request.getSession().getAttribute(SessionManager.LoginStatus);
		
		String target = "success";
		if(loginDTO!=null){
			String role_list = "";
			UserForm form = (UserForm) p_form;
			UserDTO dto = new UserDTO();
			dto.setUserName(form.getUserName().trim());
			dto.setPassword(form.getPassword().trim());
			dto.setRoleID(form.getRoleId());
			for(int i = 0; i<form.getRoleList().length;i++) {
				role_list += form.getRoleList()[i]+";";
				System.out.println("******************** Role List = " + form.getRoleList()[i] + " *******************");
			}
			if((role_list.contains(";"+dto.getRoleID()+";") || role_list.startsWith(dto.getRoleID()+";")) == Boolean.FALSE)
				role_list += dto.getRoleID()+";";
			dto.setRoleList(role_list);
			dto.setEmployeeName(form.getEmployeeName());
			dto.setEmployeePhone(form.getEmployeePhone());
			dto.setDesignation(form.getDesignation());
			dto.setEmployeeType(form.getEmployeeType());
			UserService service = new UserService();
			DAOResult daoResult=service.addUser(dto);
			if(daoResult.isValid()==false){
				target="failure";
			}else{
				//LogGeneration.insertIntoLogTable("Add User", "New User: "+dto.getUserName(), loginDTO.getuserName());
			}
			p_request.getSession(true).setAttribute(SessionManager.UserAdd, daoResult.getMessage());
			return (p_mapping.findForward(target));
		}else{
			p_request.getSession(true).setAttribute(SessionManager.UserAdd, "Invalid Login");
			return (p_mapping.findForward(target));
		}
	}
}
