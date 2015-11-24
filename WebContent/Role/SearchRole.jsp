<%@ include file="../includes/checkLogin.jsp"%>

<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.RoleSearch)==false){
		response.sendRedirect("../");
	}
}
%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="role.RoleService"%>
<%@ page import="role.RoleDTO"%>
<%@page import="utility.SessionManager"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%

String roleName=(String)request.getParameter("roleName");
if(roleName==null){
	roleName="";
}
int TOTAL_PAGES=1;
RoleService service = new RoleService();
ArrayList<RoleDTO> dtoList= service.getAllRoles(roleName);
int size=dtoList.size();
int ROW_PER_PAGE=10;
%>


<html >
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title>

<link rel="stylesheet" type="text/css" href="../Assets/Styles/myStyle.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="../Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font -->
    <link href="../Assets/NewAssets/css/font-awesome.css" rel="stylesheet">
    <!-- Animation -->
    <link href="../Assets/NewAssets/css/animate.css" rel="stylesheet">
    <!-- Custom css -->
    <link href="../Assets/NewAssets/css/style.css" rel="stylesheet">

    <script type="text/javascript" src="../Assets/Scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/sidebar-menu.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
    <script type="text/javascript">

    function navigate(nav)
    {
      var totalPage = document.getElementById("totalPage");
      var page = document.getElementById("pageCount");
      
      if(nav == "f")
      {
    	page.value = "1";
      }
      else if(nav == "l")
      {
    	page.value = totalPage.value;
      }
      else if(nav == "p")
      {
        if(page.value != "1")
        {
        	var val = parseInt(page.value);
        	page.value = "" +(val-1);
        }
      }
      else if(nav == "n")
      {
        if(page.value != totalPage.value)
        {
          var val = parseInt(page.value) + 1;
          page.value = ""+val;
        }
      }
      showData();
      return false;
    }

    function showData(){
    	var page = document.getElementById("pageCount");
    	var currentPage = parseInt(page.value);
    	var totalPage = document.getElementById("totalPage");
    	var tPage = parseInt(totalPage.value);
    	
    	for(var i = 1; i <= tPage; i++){
    		if(i!=currentPage)
    			document.getElementById(i).style.display = "none";
    		else
    			document.getElementById(i).style.display = "block";
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
                                
        <div class="row wrapper border-bottom white-bg page-heading">
        	<div class="col-lg-10">
            	<h2>View All Employee</h2>
            	
            </div>
            <div class="col-lg-2"></div>
        </div><!--/./row wrapper border-bottom white-bg page-heading-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                
	                <%if(session.getAttribute(SessionManager.RoleAdd)!=null){%>
	                	<div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.RoleAdd)%></div>
	                    <%session.removeAttribute(SessionManager.RoleAdd);
	                }%>
	               
	               
	   				<div class="ibox float-e-margins">
	   					<div class="ibox-title">
	   						<h5>System Role</h5>
	   						
	   					</div>
	                	<div class="ibox-content">
	                		<div class="dataTables_length">
		                      <div class="col-lg-8 navbar-left" style="padding:0px;"> 
		                      	<form class="navbar-form navbar-left form-inline" action="SearchRole.jsp">
		                        	<div class="input-group">
	                                    <input type="text" placeholder="Role Name..." class="form-control" name="userName" <%if(roleName!=null){%>value="<%=roleName%>"<%}%> id="top-search"/>
	                                    <span class="input-group-btn">
	                                        <button class="btn btn-primary" type="submit" style="padding-top: 12px; margin-bottom: -1px;"><span class="fa fa-search" style=""></span></button>
	                                    </span>
	                                </div>                        
	                            </form>
		                        
		                        </div>
		                        <div class="col-lg-1"  style="padding:0px;">
		                        
		                        </div>
		                        <div class="col-lg-1"  style="padding:0px;"></div>
		                        <div class="col-lg-2"  style="padding:0px;">
		                        	<%
		                        	if(loginDTO!=null){
		                        		if(loginDTO.getClientType()==-1){%>
	                                	<a href='../Role/AddRole.jsp' class="btn-primary">
											<button type="button" class="btn btn-primary navbar-right" style="margin-left:0px; margin-bottom:5px;">
												New Role
											</button>	
										</a>
										<%}
									}%>
		                        </div>
		                        <div style="clear:both"></div>
		                    </div>       
	                	
	             				<%if(size>0){%>
					                <div style="clear:both"></div>
	            					<div class="table-responsive">
						                <div id="1" align="center">
							                <table class="table table-bordered table-striped table-hover" style="font-size: 12px; width: 400px;">
								                <thead>
								                    <tr align="center">
								                    	<th>Role Name</th>
								                    	<th>role Edit</th>
								                    </tr>
								                </thead>
								                <tbody>
													<%for(int i=0, j=1; i<size; i++,j++){
														RoleDTO dto = dtoList.get(i);%> 	 	
								                    	<tr>
								                    		<td align="center"><%=dto.getRoleName()%></td>
												  			<td align="center">
												  				<%if(loginDTO!=null && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.RoleEdit)==true){%>
																	<a href="../Role/EditRole.jsp?roleID=<%=dto.getRoleID()%>">Edit</a>
																<%}%>
										            		</td>
								                    	</tr>
								                    	
								                    	<%if(j%ROW_PER_PAGE==0 && i<size-1){%>
								                    		</tbody>
											     			</table>
														 	</div>
											 				<div id="<%=++TOTAL_PAGES%>" style="display: none;" align="center">
											 				<table class="table table-bordered table-striped table-hover" style="font-size: 12px; width: 400px;">
												 				<thead>
												                    <tr>
												                    	<th>Role Name</th>
								                    					<th>role Edit</th>
												                    </tr>
												                </thead>
											                <tbody>
												        <%}%>
								                    <%}%>  
								                </tbody>
							                </table>
						                </div>
									    <table align="center">
						    	            <tr>
						    	              <td >
						    	                <a href="#" onClick="return navigate('f');"><img border="0" src="../Assets/Images/Paging/first.gif" alt="First" width="15" height="15"/></a>
						    	                <a href="#" onClick="return navigate('p');"><img border="0" src="../Assets/Images/Paging/prev.gif" alt="Previous" width="15" height="15"/></a>
						    	                <a href="#" onClick="return navigate('n');"><img border="0" src="../Assets/Images/Paging/next.gif" alt="Next" width="15" height="15"/></a>
						    	                <a href="#" onClick="return navigate('l');"><img border="0" src="../Assets/Images/Paging/last.gif" alt="Last" width="15" height="15"/></a>
						    	              </td>
						    	              <td >Page</td>
						    	              
						    	              <td >
						    	                <input type="text" name="pageCount" id="pageCount" class="input-mini" value = '1' size="2"  >
						    	                <input type="hidden" value="<%=TOTAL_PAGES %>" name="totalPage" id="totalPage"/>
						    	              </td>
						    	              <td >
						    	                <input class="btn" type="button" value="Go" onClick="return showData();" />
						    	              </td>
						    	              <td >Total <%=TOTAL_PAGES%> Page</td>
						    	              
						    	            </tr>
						    	        </table>
			    	        
						            </div>    
	                			<%}%>
	                
	                    </div><!--/./ibox-content-->
	                </div><!--/./ibox float-e-margins-->
            	</div><!--/./col-lg-12-->
			</div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->

    </div><!--/./gray-bg-->
</div><!--/#/wrapper-->


</body>


</html>