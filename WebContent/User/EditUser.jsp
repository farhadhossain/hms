<%@ include file="../includes/checkLogin.jsp"%>

<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1){
		response.sendRedirect("../");
	}
	if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.UserEdit)==false){
		response.sendRedirect("../");
	}
}
%>
<%@ page import="java.util.ArrayList" %>
<%@page import="user.UserService"%>
<%@page import="user.UserDTO"%>
<%@ page import="role.RoleService"%>
<%@ page import="role.RoleDTO"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String userID=(String)request.getParameter("userID");
UserService service = new UserService();
UserDTO dto=service.getUserDTO(Integer.parseInt(userID));
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
		
    	if(f.userName.value==null || f.userName.value==''){
          alert("Please enter a valid User Name");
          return false;
        }
    	if(f.password.value==null || f.password.value==''){
          alert("Please enter a valid password");
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
                        <h5>Edit New User</h5>
                    </div><!--/./ibox-title-->
					
                    <div class="ibox-content">

						<html:form action="/UpdateUser" onsubmit="return validationResult();">
							<div class="form-horizontal">
						`		<div class="form-group">
									<label class="col-sm-4 control-label">User Name <span class="red">*</span></label>
									<div class="col-sm-4">
										<input name="userName" type="text" readonly="readonly" value="<%=dto.getUserName()%>" id="userName" class="input-small  input-right" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Password <span class="red">*</span></label>
									<div class="col-sm-4">
										<input name="password" type="password" value="<%=dto.getPassword()%>" id="password" class="input-small  input-right" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Employee Name</label>
									<div class="col-sm-4">
										<input name="employeeName" type="text" value="<%=dto.getEmployeeName()%>" id="employeeName" class="input-small  input-right" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Employee Phone Number</label>
									<div class="col-sm-4">
										<input name="employeePhone" type="text" value="<%=dto.getEmployeePhone()%>" id="employeePhone" class="input-small  input-right" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-4 control-label">Designation</label>
									<div class="col-sm-4">
										<input name="designation" type="text" value="<%=dto.getDesignation()%>" id="designation" class="input-small  input-right" />
									</div>
								</div>

								<div class="form-group" style="display: none;">
									<label class="col-sm-4 control-label">Employee Type</label>
									<div class="col-sm-4">
										<select name="employeeType" style="margin-left: 26px;">
				                    		<option <%if(dto.getEmployeeType()==1){%>selected="selected"<%}%> value="1">User Admin</option>
					                    	<option <%if(dto.getEmployeeType()==2){%>selected="selected"<%}%>  value="2">Doctor</option>
				                    	</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Primary Role:</label>
									<div class="col-sm-4">
										<select name="roleId" style="margin-left: 26px;">
				                    		<option value="-1">None</option>
					                    	<%
					                    	RoleService roleService = new RoleService();
					                    	ArrayList<RoleDTO> dtoList= roleService.getAllRoles(null);
					                    	for(int i=0; i<dtoList.size(); i++){
					                    		RoleDTO roleDTO=dtoList.get(i);%>
					                    		<option <%if(dto.getRoleID()==roleDTO.getRoleID()){%>selected="selected"<%}%> value="<%=roleDTO.getRoleID()%>"><%=roleDTO.getRoleName()%></option>
					                    	<%}%>
				                    	</select>
										<%--
										<br />
										<%
											for(int i=0; i<dtoList.size(); i++){
												RoleDTO roleDTO=dtoList.get(i);%>
										<input name="roleList[]" type="checkbox" value="<%=roleDTO.getRoleID()%>" <%if(dto.getRoleList() != null && (dto.getRoleList().contains(";"+roleDTO.getRoleID()+";") || dto.getRoleList().startsWith(roleDTO.getRoleID()+";"))){%> checked <%}%>><%=" "+roleDTO.getRoleName()%><br />
										<%}%>
										--%>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Role Lists:</label>
									<div class="col-sm-4">&nbsp;</div>
									<%
										for(int i=0; i<dtoList.size(); i++){
											RoleDTO roleDTO=dtoList.get(i);%>
									<div class="col-sm-4">&nbsp;</div>
									<div class="col-sm-8">
										<input name="roleList[]" type="checkbox" value="<%=roleDTO.getRoleID()%>" <%if(dto.getRoleList() != null && (dto.getRoleList().contains(";"+roleDTO.getRoleID()+";") || dto.getRoleList().startsWith(roleDTO.getRoleID()+";"))){%> checked <%}%>><%=" "+roleDTO.getRoleName()%><br />
									</div>
									<%}%>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
										<input type="submit" value="Update" class="btn btn-primary">
										<input type="hidden" name="userID" id="userID" value="<%=userID %>">
									</div>
								</div>
								
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