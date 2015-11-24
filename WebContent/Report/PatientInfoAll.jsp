<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@page import="patientOthers.PatientOthersService"%>
<%@page import="patientOthers.PatientOthersDTO"%>
<%@page import="utility.MyConfig"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
PatientService patientServ = new PatientService();
boolean editAndView=false;

PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));

PatientOthersService patOthersServ = new PatientOthersService();

PatientOthersDTO patCurInfoDTO = patOthersServ.getPatientOthersDTOByID(Integer.parseInt(accountID)); 
HashMap<Integer, String> diseaseList = new DiseaseService().getSysDiseaseInfo(-1);
HashMap<Integer, String> patExposureToList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoExposureTo);
HashMap<Integer, String> patHabitsList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHabits);
HashMap<Integer, String> patMenstrualHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoMenstrualHistory);
HashMap<Integer, String> patObstetricalHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoObstetricalHistory);
HashMap<Integer, String> patFamilyRelevantDiseaseHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoFamilyRelevantDiseaseHistory);
HashMap<Integer, String> patHistoryOfImmunizationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfImmunization);
HashMap<Integer, String> statusList = new StatusService().getAllStatus();
%>
<html lang="en">
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SessionManager.title%></title>
<!--<link rel="stylesheet" type="text/css" href="../Assets/Fonts/fontface.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/united-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/main.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/sidebar-menu.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/style.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/tableOfTanvir.css" />

    -->
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
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
    
</head>


<body>
    
    <div id="wrapper">
    
    	<div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
               
             	<div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    
                    	<div class="row">
                    		<div class="col-sm-6" style="font-family: ubuntu mono;">
                        		<h5>View Findings of <i><%=patientDTO.getName()%></i></h5> 
                       		</div>
                       		<div class="col-sm-6" style="text-align: right;">
                       			<input type="button" class="btn btn-primary" style="padding: 2px 12px;" value="Print" onClick="window.print()">
                       		</div>
                        </div>
                    </div><!--/./ibox-title-->
                    
                    <div class="ibox-content" id="print">
                    	<div class="form-horizontal">
							<div class="header">
								<div class="row" style="border: solid thin;">
									<div class="col-sm-1" style="padding-right: 0px 0px 0px 0px;">
										<img src="../Assets/NewAssets/images/BSMMU.png" alt="BSMMU" longdesc="http://www.bsmmu.edu.bd/" width="50">
									</div>
									<div class="col-sm-10" style="padding-right: 0px 0px 0px 0px; text-align: center;">
										<h1><strong>Oral and Maxillofacial Surgery Department</strong></h1>
										<h2><strong>Bangabandhu Sheikh Mujib Medical University</strong></h2>
										<h3><i><u>Patient Information</u></i></h3>
									</div>
									<div class="col-sm-1"></div>
									<div class="col-sm-12">
										<p>&nbsp; </p>
									</div>
									<div style="font-size: 15px; color: #0A141F;">
										
										<div class="col-sm-6">
										  	<label for="p1">Name of Patient: &nbsp;</label>
										  	<label style="color: #010203; font-style: italic;"><%= patientDTO.getName() %></label>
										</div>
										<div class="col-sm-2">
											<label>Age:</label>
											<label><%= patientDTO.getAge() %></label>
										</div>
										<div class="col-sm-2">
											<label>Sex:</label>
											<label><%=patientDTO.getSex() %></label>
										</div>
										<div class="col-sm-2">
											<label>Ticket No:</label>
											<label><%=patientDTO.getTicketNumber() %></label>
										</div>
										
										<div class="col-sm-2">
										  	<label for="p1">Ward No:  </label>
										  	<label style=""><%=patientDTO.getWordNumber()==null?"":patientDTO.getWordNumber() %></label>
										</div>
										<div class="col-sm-2">
											<label>Bed No:</label>
											<label><%= patientDTO.getBedNumber() %></label>
										</div>
										<div class="col-sm-2">
											<label>Cabin No: </label>
											<label><%=patientDTO.getCabinNumber() %></label>
										</div>
										<div class="col-sm-2">
											<label>Blood Group: </label>
											<label><%=patientDTO.getBloodGroup() %></label>
										</div>
										<div class="col-sm-4">
											<label>Contact No:</label>
											<label><%= patientDTO.getTelephoneNum() %></label>
										</div>
										<div class="col-sm-12">
											<p>&nbsp; </p>
										</div>
									</div>
									<div class="col-sm-12" style="text-align: center;">
										<h2><strong>Patient Disease Information</strong></h2>
										
									</div>
									
									<div class="col-sm-12" style="color: #0A141F;">
										<div class="row">
											<div classs="col-sm-12">
												<label style="font-size: 16px;">&nbsp;Admitted in:</label>
												<label style="font-size: 16px; color: red;"><%=statusList.get(patientDTO.getDeptId()) %></label>
											</div>
											<table class="col-sm-12" style="color: #0A141F;">
												<%if(patientDTO.getDeptId()==3){%>
				                        			<%Iterator<Integer> it = patientDTO.diseaseTypeHash.iterator();
				                                  	while(it.hasNext()) {
				                                  		int key=it.next();%>
				                    					<tr>
				                    						<td>
						                    					<%if(key==23){%>
						                    						<%@ include file="../ViewDiseaseOnly/OroFaciaInfection.jsp" %>
						                    					<%}else if(key==26){%>
						                    						<%@ include file="../ViewDiseaseOnly/BenignCystTumor.jsp" %>
						                    					<%}else{%>
						                    						<%@ include file="../ViewDiseaseOnly/CommonDisease.jsp" %>
						                    					<%}%>
						                    					<br>
						                    					
					                    					</td>
				                    					</tr>
				                    				<%}%>
				                        		<%}else{%>
				                        			<tr>
					                        			<td>
					                        				<%@ include file="../Department/DiseaseUpdateViewOnly.jsp" %>
					                        			</td>
				                        			</tr>                        			
				                        		<%}%>
											</table>
										</div>
									</div>
									
									
									
								</div>
							</div>
					   	</div>
                    
                    </div>
					
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
       
    </div><!--/./gray-bg-->


</body>

</html>