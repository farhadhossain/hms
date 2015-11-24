<%@ page import="utility.SessionManager, login.LoginDTO, role.RoleRepository, permission.PermissionDTO" %>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1);
LoginDTO loginDTO=(LoginDTO)session.getAttribute(SessionManager.LoginStatus);
if(loginDTO==null){
	response.sendRedirect("..");
}
%>