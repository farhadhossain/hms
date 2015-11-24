<%@ include file="../includes/checkLogin.jsp"%>
<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientSearch)==false){
		response.sendRedirect("../");
	}
}
%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.*" %>
<%@ page import="utility.SessionManager" %>
<%@ page import="pastSergHistory.PastSurgicalHistoryDTO" %>
<%@ page import="pastSergHistory.PastSurgicalHistoryService" %>
<%@ page import="status.StatusService" %>
<%@ page import="disease.DiseaseService" %>
<%@ page import="utility.OthersInfo" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%

Calendar date = Calendar.getInstance();
String[] nameOfMonth = { "January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October",
        "November", "December" };
int numberOfMonth = date.get(Calendar.MONTH);
int todays=date.get(Calendar.DAY_OF_MONTH);
int todaysMonth=date.get(Calendar.MONTH);
int todaysYear=date.get(Calendar.YEAR);

String startDate= request.getParameter("startDate");
if(startDate==null){
	startDate=(String)session.getAttribute("startDate");
	if(startDate==null){
		startDate=todaysYear+"-"+(todaysMonth+1)+"-"+todays;
	}
}else{
	session.setAttribute("startDate", startDate);
}

String endDate= request.getParameter("endDate");
if(endDate==null){
	endDate=(String)session.getAttribute("endDate");
	if(endDate==null){
		endDate=todaysYear+"-"+(todaysMonth+1)+"-"+todays;
	}
}else{
	session.setAttribute("endDate", endDate);
}

int TOTAL_PAGES=1;
int PAGE_SIZE=30;

PastSurgicalHistoryService service = new PastSurgicalHistoryService();
ArrayList<PastSurgicalHistoryDTO> dtoList= new ArrayList<PastSurgicalHistoryDTO>();
if(loginDTO!=null){
	dtoList=service.getPatientStatusLists(-2, startDate, endDate);
}
int size=dtoList.size();

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
	<link href="../Assets/Date/datepicker.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="../Assets/Scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/sidebar-menu.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
    <script src="../Assets/Date/js/datepicker.js"></script>
    
   <script type="text/javascript">
    $(function(){
        $(document).ready(function() {
           
           $('#startDate').datepicker({
                'format': 'yyyy-mm-dd'
            }).on('changeDate', function(e){
                $('#startDate').datepicker('hide');
            });
            
       });
   });

    </script>
    <script type="text/javascript">
    $(function(){
        $(document).ready(function() {
           
           $('#endDate').datepicker({
                'format': 'yyyy-mm-dd'
            }).on('changeDate', function(e){
                $('#endDate').datepicker('hide');
            });
            
       });
   });

    </script>
    
</head>

<body>
	<div id="wrapper">
    
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->
	
        <div id="page-wrapper" class="gray-bg">
        
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
                                
        <div class="row wrapper border-bottom white-bg page-heading">
        	<div class="col-lg-10">
            	<h2>View All Patients</h2>
            </div>
            <div class="col-lg-2"></div>
        </div><!--/./row wrapper border-bottom white-bg page-heading-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                	
	                <%if(session.getAttribute(SessionManager.PatientAdd)!=null){%>
                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientAdd)%></div>
                        <%session.removeAttribute(SessionManager.PatientAdd);
                    }%>
                    <%if(session.getAttribute(SessionManager.PatientRespratory)!=null){%>
                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientRespratory)%></div>
                        <%session.removeAttribute(SessionManager.PatientRespratory);
                    }%>
                    <%if(session.getAttribute(SessionManager.PatientCardiovascular)!=null){%>
                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientCardiovascular)%></div>
                        <%session.removeAttribute(SessionManager.PatientCardiovascular);
                    }%>
	                
	                <div style="clear:both"></div>
	   				<div class="ibox float-e-margins">
	   					<div class="ibox-content">
	                		<div class="dataTables_length">
                                <div class="col-lg-12"  style="padding:0px;">
		                            <form class="navbar-form navbar-right" method="post" action="PresentStatus.jsp">
		                            	<label>From Date</label>
		                            	<div class="input-group">
		                            	    <input type="text" class="form-control" name="startDate" id="startDate" value="<%=startDate %>" style="width: 100px;">
		                                </div>
			                        	<label>To Date</label>
		                            	<div class="input-group">
		                            	    <input type="text" class="form-control" name="endDate" id="endDate" value="<%=endDate %>" style="width: 100px;">
		                                </div>
			                        	<div class="input-group">
		                                    <span class="input-group-btn">
		                                        <button class="btn btn-primary" type="submit"><span class="fa fa-search"></span></button>
		                                    </span>
		                                </div>   
		                            </form>
		                        </div>
		                	</div>
		                    <div style="clear:both"></div>
                    	</div>
	                </div>
	                
	   				<div class="ibox float-e-margins">
	                	<div class="ibox-content">
             				<%if(size>0){%>
				                <div style="clear:both"></div>
            					<div class="table-responsive">
					                <table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
						                <thead>
						                    <tr>
						                    	<th>Serial</th>
						                    	<th>Patient Name</th>
						                    	<th>Patient Info</th>
						                    	<th>Disease Images</th>
						                    	<th>Status</th>
						                    	<th>Responsible Doctor(Bed)</th>
						                    	<th>Name Of Surgery</th>
						                    	<th>Date Of Surgery</th>
						                    	<th>Doctor Details</th>
						                    	<th>Action</th>
						                    </tr>
						                </thead>
						                <tbody>
											<%
								        	for(int i=0;i<size;i++){
												PastSurgicalHistoryDTO dto = dtoList.get(i);
												String buttonValue="Make Pre-operative";
												if(dto.getSurgicalStatus()==1){
													buttonValue="Make Post-operative";
												}else if(dto.getSurgicalStatus()==2){
													buttonValue="Make Discharged";
												}
												%>
						                    	<tr>
								                    <td><%=dto.getSurgicalID()%></td>
								                    <td><%=dto.patientName%></td>
								                    <td><%=dto.patientDetails%></td>
								                    <td>
								                    	<a href="ViewImages.jsp?accountID=<%=dto.getUserID()%>">View Images</a><br>
								                       	<a href="AddImage.jsp?accountID=<%=dto.getUserID()%>" style="color: blue; font-weight: bold;">(+)Add Images</a>
								                    </td>
								                    <td><%=dto.getSurgicalStatus()==0?"Pending Surgical Plan":(dto.getSurgicalStatus()==1?"Pre-operative":(dto.getSurgicalStatus()==2?"Post-operative":"Discharged"))%></td>
								                    <td><%=dto.responsibleDoctorBed %></td>
								                    
								                    <td><%=dto.getNameOfOp()%></td>
								                    <td><%=dto.getDateOfOp()%></td>
								                    <td>
								                    	<%="Surgeon :<br>"+dto.surgeonDescripton %><br>
								                    	<%="Asst. Surgeon :<br>"+dto.assistSurgeonDescripton %><br>
								                    	<%="Anesthetist :<br>"+dto.anesthetistDescripton %>
								                    </td>
								                    
								                    <td>
								                    	<a target="_blank" href="../Report/PatientInfoAll.jsp?accountID=<%=dto.getUserID()%>">View Findings</a><br>
								                    	<%if(dto.getSurgicalStatus()==-1){%>
								                    		<a target="_blank" href="../Patient/Discharge.jsp?accountID=<%=dto.getUserID()%>&surgicalID=<%=dto.getSurgicalID()%>">Discharge Note</a><br>
								                    	<%}else{%>
								                    		<a href="../ChangeSurgicalStatus.do?surgicalId=<%=dto.getSurgicalID()%>&currentStatus=<%=dto.getSurgicalStatus()%>"><input onclick="return confirm('Are you sure?')" type="button" value="<%=buttonValue%>"></a>
								                    	<%}%>
								                    </td>
						                    	</tr>
						                    <%}%>  
						                </tbody>
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