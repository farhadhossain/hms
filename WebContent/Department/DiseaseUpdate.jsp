<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="department.DepartmentDiseaseService"%>
<%@page import="department.DepartmentDiseaseDTO"%>
<%@ page import="utility.MyUtility" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="java.util.HashSet"%><html>
<%
boolean editAndView=true;
String accountID = request.getParameter("accountID");
if(accountID==null){
	accountID=(String)session.getAttribute("accountID");
}else{
	session.setAttribute("accountID", accountID);
}

String departmentId = request.getParameter("departmentId");
if(departmentId==null){
	departmentId =(String)session.getAttribute("departmentId");
}else{
	session.setAttribute("departmentId", departmentId);
}
int departmentID = Integer.parseInt(departmentId);

DepartmentDiseaseService deptDisServ = new DepartmentDiseaseService();

DepartmentDiseaseDTO patDeptDisDTO = deptDisServ.getPatientDepartmentDiseaseInfo(Integer.parseInt(accountID), departmentID); 

HashMap<Integer, String> disGeneralList = deptDisServ.getDepartmentDiseaseListByID(departmentID, 0);
HashMap<Integer, String> disHistoryList = deptDisServ.getDepartmentDiseaseListByID(departmentID, 1);
HashMap<Integer, String> disClinicalExmList = deptDisServ.getDepartmentDiseaseListByID(departmentID, 2);
HashMap<Integer, String> disTreatmentList = deptDisServ.getDepartmentDiseaseListByID(departmentID, 3);

int id = 1;

%>

<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title>
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
    <script type="text/javascript" src="../Assets/Scripts/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap-timepicker.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../Assets/NewAssets/js/bootstrap.min.js"></script>
    <script src="../Assets/NewAssets/js/jquery.metisMenu.js"></script>
    <script src="../Assets/NewAssets/js/jquery.slimscroll.min.js"></script>

   

    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>

    <!-- Page-Level Scripts -->
    <script>
       $('#myModal').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  });
	  $('#myModal2').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  });

	  function changeThis(divID){
		  if(document.getElementById(divID).style.display=='none'){
			  document.getElementById(divID).style.display='block';
		  }else{
			  document.getElementById(divID).style.display='none';
		  }		  
	  }
	  
    </script>
    
</head>


<body>
    <!--*********************Body part Starts from here************************-->
    <!--***********************************************************************-->
    <div id="wrapper">
    	<%@ include file="../includes/leftNav.jsp"%><!--sidebar-->
		<div id="page-wrapper" class="gray-bg">
        	<%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->

        	<div class="wrapper wrapper-content animated fadeInRight">
            	<div class="row">
            		<div class="col-lg-12">
            		
            			<%if(session.getAttribute(SessionManager.PatientNewFindings)!=null){%>
	                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientNewFindings)%></div>
	                        <%session.removeAttribute(SessionManager.PatientNewFindings);
	                    }%>
	                    
                		<div style="clear:both"></div>
			 			<div class="ibox float-e-margins">
                   			<div class="ibox-title">
                   				<h5>
                   					<%if(departmentID==1){%>Outdoor
                   					<%}else if(departmentID==2){%>Minor OT
                   					<%}else if(departmentID==4){%>Emergency
                   					<%}%>
                   				</h5>
                   			</div><!--/./ibox-title-->
                    			
               				<div class="ibox-content">
								<html:form action="/UpdateDepartmentDiseaseInfo">
			        				<%if(disGeneralList.size()>0){%>
				        				<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
														General
													</a>
												</h4>
											</div>
											<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
												<div class="panel-body">
													<div class="table-responsive">
														<table class="table" style="font-size: 13px;">
															<%=MyUtility.generateHTMLForDeptDisList(disGeneralList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
														 </table>
													 </div>
												 </div>
											</div>
										</div>
										<%id++; %>
									<%}%>
									
									<%if(disHistoryList.size()>0){%>
				        				<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
														History
													</a>
												</h4>
											</div>
											<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
												<div class="panel-body">
													<div class="table-responsive">
														<table class="table" style="font-size: 13px;">
															<%=MyUtility.generateHTMLForDeptDisList(disHistoryList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
														 </table>
													 </div>
												 </div>
											</div>
										</div>
										<%id++; %>
									<%}%>
									
									
									<%if(disTreatmentList.size()>0){%>
				        				<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
														Treatment Plan
													</a>
												</h4>
											</div>
											<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
												<div class="panel-body">
													<div class="table-responsive">
														<table class="table" style="font-size: 13px;">
															<%=MyUtility.generateHTMLForDeptDisList(disTreatmentList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
														 </table>
													 </div>
												 </div>
											</div>
										</div>
										<%id++; %>
									<%}%>
									
									
									<%if(disClinicalExmList.size()>0){%>
				        				<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
												<h4 class="panel-title">
													<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
														Clinical Examination
													</a>
												</h4>
											</div>
											<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
												<div class="panel-body">
													<div class="table-responsive">
														<table class="table" style="font-size: 13px;">
															<%=MyUtility.generateHTMLForDeptDisList(disClinicalExmList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
														 </table>
													 </div>
												 </div>
											</div>
										</div>
										<%id++; %>
									<%}%>
							    	
							    	
							    	<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
											<h4 class="panel-title">
												<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
													Diagnosis
												</a>
											</h4>
										</div>
										<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
											<div class="panel-body">
												<div class="table-responsive">
													<textarea class="form-control" rows="2" name="diagnosis"><%=patDeptDisDTO.getDiagnosis()%></textarea>
												 </div>
											 </div>
										</div>
									</div>
									<%id++; %>
									
									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
											<h4 class="panel-title">
												<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
													Advice
												</a>
											</h4>
										</div>
										<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
											<div class="panel-body">
												<div class="table-responsive">
													<textarea class="form-control" rows="2" name="advice"><%=patDeptDisDTO.getAdvice()%></textarea>
												 </div>
											 </div>
										</div>
									</div>
									<%id++; %>
										
										
									    	
									<input type="submit" value="Submit" class="btn btn-primary">
									<input type="hidden" name="userId" id="userId" value="<%=accountID %>">
								    <input type="hidden" name="departmentId" id="departmentId" value="<%=departmentId %>">
								</html:form>
							</div>
	                	</div>   	
	                </div><!--/./col-lg-12-->
	
	   			</div> <!--/./row-->
	       	</div><!--/./wrapper wrapper-content animated fadeInRight-->
	        
	        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
	        
	    </div><!--/./gray-bg-->
	</div><!--/#/wrapper-->




</body>

</html>

