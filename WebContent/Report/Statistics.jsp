<%@ include file="../includes/checkLogin.jsp"%>

<%if(loginDTO!=null){
	if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.ReportView)==true){
		System.out.print("Permission Ok");
	}
}else{
	response.sendRedirect("../");
}%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Calendar" %>
<%@page import="report.ReportDTO" %>
<%@page import="report.ReportService" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html lang="en">

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
		startDate="01-04-2015";
	}
}else{
	session.setAttribute("startDate", startDate);
}
String endDate= request.getParameter("endDate");
if(endDate==null){
	endDate=(String)session.getAttribute("endDate");
	if(endDate==null){
		endDate=todays+"-"+(todaysMonth+1)+"-"+todaysYear;
	}
}else{
	session.setAttribute("endDate", endDate);
}

String dateType= request.getParameter("dateType");
if(dateType==null || dateType.length()==0){
	dateType="1";
}

String sex = request.getParameter("sex");
sex=sex==null?"":sex;

String fromage = request.getParameter("fromage");
if(fromage==null || fromage.length()==0){
	fromage="0";
}
int fromAge=Integer.parseInt(fromage);

String toage = request.getParameter("toage");
if(toage==null || toage.length()==0){
	toage="60";
}
int toAge=Integer.parseInt(toage);

ReportService reportServ = new ReportService();
ReportDTO reportDTO= reportServ.HospitalPatientStatistics(startDate, endDate, dateType, sex, fromAge, toAge);

%>
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SessionManager.title%></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link href="../Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Font -->
    <link href="../Assets/NewAssets/css/font-awesome.css" type="text/css" rel="stylesheet">
    <!-- Animation -->
    <link href="../Assets/NewAssets/css/animate.css" type="text/css" rel="stylesheet">
    <!-- Custom css -->
    <link href="../Assets/NewAssets/css/style.css" type="text/css" rel="stylesheet">
    <link href="../Assets/Date/datepicker.css" type="text/css" rel="stylesheet">
    
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

   <script src="../Assets/Date/js/datepicker.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Department', 'Patients'],
          ['OutDoor',     <%=reportDTO.totalPatientoutdoor %>],
          ['Indoor',      <%=reportDTO.totalPatientIndoor %>],
          ['Minor OT',  <%=reportDTO.totalPatientMinorOT %>],
          ['Emergency', <%=reportDTO.totalPatientEmergency %>],
        ]);

        var options = {
          title: 'PG Patient Statistics',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
    $(function(){
        $(document).ready(function() {
           
           $('#startDate').datepicker({
                'format': 'dd-mm-yyyy'
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
                'format': 'dd-mm-yyyy'
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
        
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Patient Statistics</h5>
                    </div><!--/./ibox-title-->
					
                    <div class="ibox-content">
                    	
	                    	<div class="dataTables_length" align="center" style="top: -2em;">          	
	                			<div id="searchBoxOne">
	                			
									<form action="Statistics.jsp">
										<div class="row">
											<div class="col-lg-1" style="text-align: right;">
												<label>Sex</label>
											</div>
											<div class="col-lg-1" style="padding: 0px;">
												<select class="form-control input-sm" name="sex">
													<option value="" <%if(sex.length()==0){%>selected="selected"<%}%>>All</option>
													<option value="Male" <%if(sex.contentEquals("Male")){%>selected="selected"<%}%>>Male</option>
													<option Value="Female" <%if(sex.contentEquals("Female")){%>selected="selected"<%}%>>Female</option>
													<option value="Others" <%if(sex.contentEquals("Others")){%>selected="selected"<%}%>>Others</option>
												</select>
											</div>
											
											<div class="col-lg-6" style="text-align: right; padding-left: 0px;">
												<label>From Age</label>
												<input style="margin-left: 0px; width: 100px;" name="fromage" type="number" value="<%=fromAge%>" id="fromage" class="input-small" />

												<label>To Age</label>
												<input style="margin-left: 0px; width: 100px;" name="toage" type="number" value="<%=toAge%>" id="toage" class="input-small" />
											</div>
											<div class="col-lg-4">
											</div>
										</div>
										<div class="row">
											
											<div class="col-lg-10">
												<label style="">Date Filter </label>
												<label class="radio-inline" style="padding-top: 0px;"><input type="radio" name="dateType" <%if(dateType.equals("1")){%>checked="checked"<%}%> id="dateType1" value="1" style="color: blue;">Last 1 Hour</label>
												<label class="radio-inline" style="padding-top: 0px;"><input type="radio" name="dateType" <%if(dateType.equals("2")){%>checked="checked"<%}%> id="dateType2" value="2">Today</label>
												<label class="radio-inline" style="padding-top: 0px;"><input type="radio" name="dateType" <%if(dateType.equals("3")){%>checked="checked"<%}%> name="dateType" id="dateType3" value="3">From</label>
												
												
												<input class="" type="text" name="startDate" id="startDate" value="<%=startDate %>" style="color: black" />
												<label style="color:; font-weight: 0;">To</label>
												<input style="margin-left: 0px;" name="endDate" type="text" value="<%=endDate%>" id="endDate" class="input-small" />
												<input type="submit" value="Apply Filter" style="margin-bottom: 0" class="btn btn-primary"/>
											</div>
											
											
										</div>
										
									</form>
									<div class="hr-line-dashed"></div>
								</div>
							</div>
						
			        		
			        		<%if(reportDTO.totalPatient>0){%>
			        			<div class="row">
			        				<div class="col-lg-3">
					        			<table class="table table-hover" style="color: #1A4C33;">
					        				<tr>
					        					<td>Total Patient:<br> </td><td><%=reportDTO.totalPatient %></td>
					        				</tr>
					        				<tr>
					        					<td>Outdoor: </td><td><%=reportDTO.totalPatientoutdoor %></td>
					        				</tr>
					        				<tr>
					        					<td>Indoor: </td><td><%=reportDTO.totalPatientIndoor %></td>
					        				</tr>
					        				<tr>
					        					<td>Minor OT: </td><td><%=reportDTO.totalPatientMinorOT %></td>
					        				</tr>
					        				<tr>
					        					<td>Emergency: </td><td><%=reportDTO.totalPatientEmergency %></td>
					        				</tr>
					        			</table>
				        			</div>
				        			<div class="col-lg-6" id="piechart_3d" style="width: 700px; height: 500px;"></div>
			        			</div>
			    	        <%}else{%>
								<h2 style="color: red;" align="center">No Data</h2>
							<%}%>
						</div>
					
					
					</div><!-- /ibox-content -->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        

    </div><!--/./gray-bg-->
<!--/#/wrapper-->
</body>
</html>