<%@ page import="login.LoginDTO, permission.PermissionDTO, role.RoleRepository, utility.SessionManager" %>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1);
LoginDTO loginDTO=(LoginDTO)session.getAttribute(SessionManager.LoginStatus);
if(loginDTO==null){
	response.sendRedirect("..");
}
%>