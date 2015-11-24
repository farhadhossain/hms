<%@ include file="../includes/checkLogin.jsp"%>

<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.RoleEdit)==false){
		response.sendRedirect("../");
	}
}
%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="role.RoleService"%>
<%@ page import="role.RoleDTO"%>
<%@ page import="permission.*" %>
<%@page import="utility.SessionManager"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%
String roleID=(String)request.getParameter("roleID");

RoleService service = new RoleService();
RoleDTO dto=service.getRoleDTO(Integer.parseInt(roleID));
%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title>
<link href="../Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Font -->
    <link href="../Assets/NewAssets/css/font-awesome.css" type="text/css" rel="stylesheet">
    <!-- Animation -->
    <link href="../Assets/NewAssets/css/animate.css" type="text/css" rel="stylesheet">
    <!-- Custom css -->
    <link href="../Assets/NewAssets/css/style.css" type="text/css" rel="stylesheet">
    
    <script type="text/javascript" src="../Assets/Scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/sidebar-menu.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap-timepicker.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/mir-billing-script.js"></script>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../Assets/NewAssets/js/bootstrap.min.js"></script>
    <script src="../Assets/NewAssets/js/jquery.metisMenu.js"></script>
    <script src="../Assets/NewAssets/js/jquery.slimscroll.min.js"></script>

   

    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>
    <script type="text/javascript">
    
    function validationResult(){
    	var f = document.forms[0];
		
    	if(f.roleName.value==null || f.roleName.value==''){
          alert("Please enter a valid Role Name");
          return false;
        }
    	return true;
    }
    </script>
    
</head>


<body>
    <div id="wrapper">
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->

        <div id="page-wrapper" class="gray-bg">
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
        
        
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Add Role</h5>
                    </div><!--/./ibox-title-->
					<%if(session.getAttribute(SessionManager.RoleAdd)!=null){%>
			        	<div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.RoleAdd)%></div>
			        	<%session.removeAttribute(SessionManager.RoleAdd);
			        }%>
					
                    <div class="ibox-content">
		        			<html:form action="/UpdateRole" onsubmit="return validationResult();">
		                    	<table class="imagetable" style="margin-left: 75px;" width="500px">
	        						<tr>
	        							<th colspan="2" ><p style="margin-right: 20px; ">Role Name <input style="background-color:#FCF5D8;" name="roleName" value="<%=dto.getRoleName()%>" type="text" id="roleName" class="input-small  input-left" /></p> </th>
	        						</tr>
	        						<tr>
	        							<th width="150px">Role Name</th>
	        							<th width="200px">Role Permission</th>
	        						</tr>
	        								
	        						<tr>
	        							<td align="center">Patient Module</td>
	        							<td>
	        								<table class="imagetable" align="center" width="280px">
	        									<tr>
	        										<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.PatientAdd)%></td>
	        										<td width="20px"><input type="checkbox" <%if(dto.isPatientAdd()){%>checked="checked"<%}%> id="patientAdd" name="patientAdd"></td>
	        									</tr>
	        									<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.PatientEdit)%></td>
				        							<td width="20px"><input type="checkbox" <%if(dto.isPatientEdit()){%>checked="checked"<%}%> id="patientEdit" name="patientEdit"></td>
				        						</tr>
				        						<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.PatientSearch)%></td>
				        							<td width="20px"><input type="checkbox" <%if(dto.isPatientSearch()){%>checked="checked"<%}%> id="patientSearch" name="patientSearch"></td>
				        						</tr>
				        					</table>
	        							</td>
	        						</tr>
	        									        						
	        						<tr>
	        							<td align="center">Report Module</td>
	        							<td>
	        								<table class="imagetable" align="center" width="280px">
	        									<tr>
	        										<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.ReportView)%></td>
	        										<td width="20px"><input type="checkbox" <%if(dto.isReportView()){%>checked="checked"<%}%> id="reportView" name="reportView"></td>
	        									</tr>
				        					</table>
	        							</td>
	        						</tr>
	        						
	        						<tr>
	        							<td align="center">User Management</td>
	        							<td>
	        								<table class="imagetable" align="center" width="280px">
	        									<tr>
	        										<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.UserAdd)%></td>
	        										<td width="20px"><input <%if(dto.isUserAdd()){%>checked="checked"<%}%> type="checkbox" name="userAdd" id="userAdd" ></td>
	        									</tr>
	        									<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.UserEdit)%></td>
				        							<td width="20px"><input <%if(dto.isUserEdit()){%>checked="checked"<%}%> type="checkbox" name="userEdit" id="userEdit" ></td>
				        						</tr>
				        						<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.UserSearch)%></td>
				        							<td width="20px"><input <%if(dto.isUserSearch()){%>checked="checked"<%}%> type="checkbox" name="userSearch" id="userSearch" ></td>
				        						</tr>
				        						<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.UserDelete)%></td>
				        							<td width="20px"><input <%if(dto.isUserDelete()){%>checked="checked"<%}%> type="checkbox" name="userDelete" id="userDelete"></td>
				        						</tr>
	        								</table>
	        							</td>
	        						</tr>
	        						<tr>
	        							<td align="center">Role Management</td>
	        							<td>
	        								<table class="imagetable" align="center" width="280px">
	        									<tr>
	        										<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.RoleAdd)%></td>
	        										<td width="20px"><input <%if(dto.isRoleAdd()){%>checked="checked"<%}%> type="checkbox" name="roleAdd" id="roleAdd"></td>
	        									</tr>
	        									<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.RoleEdit)%></td>
				        							<td width="20px"><input <%if(dto.isRoleEdit()){%>checked="checked"<%}%> type="checkbox" name="roleEdit" id="roleEdit"></td>
				        						</tr>
				        						<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.RoleSearch)%></td>
				        							<td width="20px"><input <%if(dto.isRoleSearch()){%>checked="checked"<%}%> type="checkbox" name="roleSearch" id="roleSearch"></td>
				        						</tr>
				        						<tr>
				        							<td width="260px"><%=LoadPermission.getPermissionModuleNameByID(PermissionDTO.RoleDelete)%></td>
				        							<td width="20px"><input <%if(dto.isRoleDelete()){%>checked="checked"<%}%> type="checkbox" name="roleDelete" id="roleDelete"></td>
				        						</tr>
	        								</table>
	        							</td>
	        						</tr>
	        						
	        					</table>
		        					
		                    <div class="form-actions">
		                        <input type="submit" value="Submit" class="btn btn-primary">
		                        <input type="hidden" value="<%=roleID%>" name="roleID" id="roleID"/>
		                    </div>
						</html:form>
		              </div><!-- /ibox-content -->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
    </div><!--/./gray-bg-->
</div><!--/#/wrapper-->


</body>
</html>