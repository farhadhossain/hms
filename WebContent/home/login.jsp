<%@ page language="Java" %>
<%@ page import="role.RoleDTO"%>
<%@ page import="role.RoleService"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="utility.SessionManager"%>
<%@page import="java.util.ArrayList"%>

<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1);
%>
<html>
<head><html:base/>
<title>Login :: <%=SessionManager.title%></title>

<link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 

<!-- Bootstrap -->
    <link href="Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font -->
    <link href="Assets/NewAssets/css/font-awesome.css" rel="stylesheet">
    <!-- Animation -->
    <link href="Assets/NewAssets/css/animate.css" rel="stylesheet">
    <!-- Custom css -->
    <link href="Assets/NewAssets/css/style.css" rel="stylesheet">
	
	
	<script type="text/javascript">
    function validationResult(){
    	var f = document.forms[0];
    	
    	if(f.userName.value==null || f.userName.value==''){
            alert("Please enter a valid user name");
            return false;
        }
    	if(f.passWord.value==null || f.passWord.value==''){
            alert("Please enter a valid passward");
            return false;
        }
    	return true;
    }
    
    </script>
    
    <script>var __links = document.querySelectorAll('a');function __linkClick(e) { parent.window.postMessage(this.href, '*');} ;for (var i = 0, l = __links.length; i < l; i++) {if ( __links[i].getAttribute('data-t') == '_blank' ) { __links[i].addEventListener('click', __linkClick, false);}}</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>$(document).ready(function(c) {
	$('.alert-close').on('click', function(c){
		$('.message').fadeOut('slow', function(c){
	  		$('.message').remove();
		});
	});	  
});
</script>
    

</head>

<body class="gray-bg">

    <div class="text-center loginscreen  animated fadeInDown">
        <div class="signin">
        	<p>&nbsp;</p>
            <p>&nbsp;</p>
            
             <p class="signin-logo">
               <a href="http://www.bsmmu.edu.bd" target="_blank">
               <img src="Assets/NewAssets/images/BSMMU.png" alt="BSMMU" longdesc="http://www.bsmmu.edu.bd/" width="50"> 
              </a>
            </p>
            <h4 class="signin-heading">Hospital Management System</h4>
            	<html:form action="/login" onsubmit="return validationResult();" >
            	<div class="m-t form-signin">
		
					<%if(session.getAttribute(SessionManager.LoginStatus)!=null && (session.getAttribute(SessionManager.LoginStatus).equals("Login Id / Password do not match") ||session.getAttribute(SessionManager.LoginStatus).equals("You are not authorized to log in"))){%><div class="alert alert-error"><%=session.getAttribute(SessionManager.LoginStatus)%></div>
						       	<%session.removeAttribute(SessionManager.LoginStatus);}%>
		
			
			<div class="alert alert-danger" role="alert">Login Id / Password do not match</div>
                <div class="form-group">
                    <input type="text" class="form-control" name="userName" id="userName"  placeholder="Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control"  name="passWord" id="passWord" placeholder="Password">
                </div>
                <div class="form-group">
                    <label>Log_in Role</label>
                    <select name="roleId">
                            <%
                                RoleService service = new RoleService();
                                ArrayList<RoleDTO> dtoList= service.getAllRoles(null);
                                for(int i=0; i<dtoList.size(); i++){
                                    RoleDTO dto=dtoList.get(i);%>
                         <%--
                                <%
                                    if(dto.getRoleName().trim().equalsIgnoreCase("admin") || dto.getRoleName().trim().equalsIgnoreCase("Front Desk") || dto.getRoleName().trim().equalsIgnoreCase("Duty Nurse") || dto.getRoleName().trim().equalsIgnoreCase("Doctor (for Bed)"))
                                        continue;
                                %>
                         --%>
                            <option value="<%=dto.getRoleID()%>"><%=dto.getRoleName()%></option>
                            <%}%>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b" value="Sign in">Login</button>

                <a href="#"><small>Forgot password?</small></a>
                <p class="text-muted text-center"><small>Do not have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
				</div>
		</html:form>

            <p class="m-t"> <small>Copyright &copy; 2015, <a href="http://www.bsmmu.edu.bd" target="_blank">BSMMU</a>, All Rights Reserved.</small> </p>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
