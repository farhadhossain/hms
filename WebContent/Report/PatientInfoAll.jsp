<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="patient.PatientDTO"%>
<%@page import="patient.PatientService"%>
<%@page import="patientOthers.PatientOthersDTO"%>
<%@page import="patientOthers.PatientOthersService"%>
<%@ page import="prescription.VisitDTO" %>
<%@ page import="prescription.VisitDAO" %>
<%@ page import="utility.*" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
String visitID = request.getParameter("visitID")==null?"0":request.getParameter("visitID");
PatientService patientServ = new PatientService();
boolean editAndView=false;

PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));
int currentVisitId = new VisitDAO().getCurrentVisitId(Integer.parseInt(accountID));
VisitDTO visitDTO = new VisitDAO().getVisitById(Integer.parseInt(visitID) == 0 ? currentVisitId : Integer.parseInt(visitID));
StringUtil.removeNullFromObject(visitDTO);
StringUtil.removeNullFromObject(patientDTO);

PatientOthersService patOthersServ = new PatientOthersService();

PatientOthersDTO patCurInfoDTO = patOthersServ.getPatientOthersDTOByID(Integer.parseInt(accountID)); 
HashMap<Integer, String> diseaseList = new DiseaseService().getSysDiseaseInfo(-1);
HashMap<Integer, DiseaseMetaData> patExposureToList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoExposureTo);
HashMap<Integer, DiseaseMetaData> patHabitsList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHabits);
HashMap<Integer, DiseaseMetaData> patMenstrualHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoMenstrualHistory);
HashMap<Integer, DiseaseMetaData> patObstetricalHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoObstetricalHistory);
HashMap<Integer, DiseaseMetaData> patFamilyRelevantDiseaseHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoFamilyRelevantDiseaseHistory);
HashMap<Integer, DiseaseMetaData> patHistoryOfImmunizationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfImmunization);
HashMap<Integer, String> statusList = new StatusService().getAllStatus();
%>
<html lang="en" ng-app="hms">
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

	<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="../Assets/Styles/toastr.min.css">
	<link href="../Assets/NewAssets/css/style.css" type="text/css" rel="stylesheet">

    
   <script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
	<script type="text/javascript" src="../Assets/js/toastr.min.js"></script>
 	<script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-tpls-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/module.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/controllers/viewFindingsController.js"></script>
	<script>

	</script>
</head>


<body>
    
    <div id="wrapper">
    
    	<div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
               
             	<div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-title" ng-controller = "ViewFindingsController" ng-init="getVisits(<%=accountID%>);">
                    
                    	<div class="row">
                    		<div class="col-sm-6" style="font-family: ubuntu mono;">
                        		<h5>View Findings of <i><%=patientDTO.getName()%></i></h5> 
                       		</div>
                       		<div class="col-sm-6" style="text-align: right;">
                       			<input type="button" class="btn btn-primary" style="padding: 2px 12px;" value="Print" onClick="window.print()">
								<ul role="presentation" class="dropdown pull-right" ng-show="<%=visitID.equals("0")%>">
									<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
										Previous Visits <span class="caret"></span>
									</a>
									<ul class="dropdown-menu">
										<li ng-repeat="v in visits | limitTo:visits.length-1" ><a target="_blank" href="/Report/PatientInfoAll.jsp?accountID=<%=accountID%>&visitID={{v.id}}">{{$index+1}}) on {{v.visitDate | date:'dd MMM yyyy'}}</a></li>
									</ul>
								</ul>
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
											<label><%=visitDTO.getTicketNumber() %></label>
										</div>
										
										<div class="col-sm-2">
										  	<label for="p1">Ward No:  </label>
										  	<label style=""><%=visitDTO.getWordNumber()==null?"":visitDTO.getWordNumber() %></label>
										</div>
										<div class="col-sm-2">
											<label>Bed No:</label>
											<label><%= visitDTO.getBedNumber() %></label>
										</div>
										<div class="col-sm-2">
											<label>Cabin No: </label>
											<label><%=visitDTO.getCabinNumber() %></label>
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
												<label style="font-size: 16px; color: red;"><%=statusList.get(visitDTO.getCurrentStatus()) %></label>
											</div>
											<table class="col-sm-12" style="color: #0A141F;">
												<%if(patientDTO.getDeptId()==3){%>
				                        			<%Iterator<Integer> it = visitDTO.diseaseTypeHash.iterator();
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