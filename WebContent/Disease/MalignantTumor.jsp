<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="disease.DiseaseDTO"%>
<%@page import="disease.FollowUpDTO"%>
<%@ page import="utility.MyUtility" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%@page import="java.util.HashSet"%>
<%@ page import="disease.form.DiseaseMetaData" %>
<html>
<%

String userID = request.getParameter("userID");
if(userID==null){
	userID=(String)session.getAttribute("userID");
}else{
	session.setAttribute("userID", userID);
}

String diseaseID = request.getParameter("diseaseID");
if(diseaseID==null){
	diseaseID=(String)session.getAttribute("diseaseID");
}else{
	session.setAttribute("diseaseID", diseaseID);
}

boolean editAndView=true;

DiseaseService disServ = new DiseaseService();

DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(Integer.parseInt(userID), Integer.parseInt(diseaseID)); 
ArrayList<FollowUpDTO> followUpList=disServ.getFollowUpReport(Integer.parseInt(userID), Integer.parseInt(diseaseID));


HashMap<Integer, DiseaseMetaData> disInspectionList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseInspection);
HashMap<Integer, String> disInspecParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseInspection);

HashMap<Integer, DiseaseMetaData> disOtherList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseOthers);
HashMap<Integer, String> disOtherParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseOthers);

HashMap<Integer, DiseaseMetaData> disDiagonosisList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseDiagnosis);
HashMap<Integer, String> disDiagonosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseDiagnosis);

HashMap<Integer, DiseaseMetaData> disTreatmentPlanList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseTreatmentPlan);
HashMap<Integer, String> disTreatmentPlanParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseTreatmentPlan);

HashMap<Integer, String> disSpecialCaseList = disServ.getSpecialCaseIdNameList(Integer.parseInt(diseaseID));
int id = 1;

String actionNameFollowUp="/NewFindingsMalignantTumorPatient";

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

	<!--AngularJs-->
	<script type="text/javascript" src="../Assets/js/angular/angular.min.js"></script>

	<script>
		var app = angular.module('malignantApp', []);
		app.controller('malignantCtrl', function($scope) {
			/*$scope.tnmGenerator = function(item){
				$scope.inspecDescripId355 = item;
			}
			$scope.tList = ["TX", "T0", "Tis", "T1", "T2", "T3", "T4a", "T4b"];
			$scope.nList = ["NX", "N0", "N1", "N2", "N2a", "N2b", "N2c", "N3"];
			$scope.mList = ["MX", "M0", "M1"];*/
		});
	</script>
   

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
                   				<h5>Malignant Tumor </h5>
                   			</div><!--/./ibox-title-->
                    			
               				<div class="ibox-content">
								<div role="tabpanel">
								  	<!-- Nav tabs -->
							  		<ul class="nav nav-tabs" role="tablist">
							    		<li role="presentation" class="active"><a href="#history" aria-controls="history" role="tab" data-toggle="tab">History</a></li>
								    	<li role="presentation"><a href="#clinicalExamination" aria-controls="clinicalExamination" role="tab" data-toggle="tab">Clinical Examination</a></li>
								    	<li role="presentation"><a href="#specificInvestigation" aria-controls="specificInvestigation" role="tab" data-toggle="tab">Specific Investigation</a></li>
								    	<li role="presentation"><a href="#diagnosis" aria-controls="diagnosis" role="tab" data-toggle="tab">Diagnosis</a></li>
								    	<li role="presentation"><a href="#treatmentPlan" aria-controls="treatmentPlan" role="tab" data-toggle="tab">Treatment Plan</a></li>
								    	<li role="presentation"><a href="#complications" aria-controls="complications" role="tab" data-toggle="tab">Complications</a></li>
										<li role="presentation"><a href="#specialNotes" aria-controls="specialNotes" role="tab" data-toggle="tab">Special Notes</a></li>
							  		</ul>
									
									<!-- Tab panes -->
							  		<html:form action="/PatientMalignantTumor">
						        		<div class="tab-content" style="overflow: auto;" ng-app="malignantApp" ng-controller="malignantCtrl">
								    		<div role="tabpanel" class="tab-pane active" id="history" >
									    		<div class="panel-body">
											    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												    	<%
														for(int key: disSpecialCaseList.keySet()){
										                    	HashMap<Integer, DiseaseMetaData> disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(Integer.parseInt(diseaseID), key);
										                    	HashMap<Integer, String> disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(Integer.parseInt(diseaseID), key);
										                    	boolean isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(Integer.parseInt(userID), Integer.parseInt(diseaseID), key);%>

																	<%@ include file="../includeDiseaseParts/includeSpecialCaseIdOnly.jsp"%>

														<%}%>
													</div>
										    	</div>
									    	</div>
										    <%id++;%>

										     <div role="tabpanel" class="tab-pane" id="clinicalExamination" style="">
										    	<div class="panel-body">
													<%if(editAndView==true || (editAndView==false && patCurDisDTO.patInspId.isEmpty()==false)){%>
													<div class="table-responsive">
														<table class="table" style="font-size: 13px;">
															<%=MyUtility.generateHTML(disInspectionList, disInspecParentByChild, "inspectionId", patCurDisDTO.patInspId, patCurDisDTO, editAndView)%>
														</table>
													</div>
													<%}%>
										    	</div><!--/./form-group-->
											 </div>
											<%id++;%>

						    	   			 <div role="tabpanel" class="tab-pane" id="specificInvestigation">
									    	 	<div class="panel-body">
											 		<table class="table" style="font-size: 13px;">
														<%
															int key=29;
															HashMap<Integer, DiseaseMetaData> disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(Integer.parseInt(diseaseID), key);
															HashMap<Integer, String> disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(Integer.parseInt(diseaseID), key);
															//boolean isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(Integer.parseInt(userID), Integer.parseInt(diseaseID), key);
														%>
														<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
													</table>
										    	</div><!--/./form-group-->
				    	 					 </div>
										     <%id++; %>

										     <div role="tabpanel" class="tab-pane" id="diagnosis">
							    		 		<%if(editAndView==true || (editAndView==false && patCurDisDTO.patDiagonosisId.isEmpty()==false)){%>
											    	<div class="panel-body">
														<div class="table-responsive">
															<table class="table" style="font-size: 13px;">
																<%=MyUtility.generateHTML(disDiagonosisList, disDiagonosisParentByChild, "diagnosisId", patCurDisDTO.patDiagonosisId, patCurDisDTO, editAndView)%>
															 </table>
														 </div><!--/./form-group-->
													 </div>
												 <%}%>
									    	</div>
									    	<%id++; %>

									    	<div role="tabpanel" class="tab-pane" id="treatmentPlan">
										    	<%if(editAndView==true || (editAndView==false && patCurDisDTO.patTrtmntId.isEmpty()==false)){%>
											    	<div class="panel-body">
														<div class="table-responsive">
															<table class="table" style="font-size: 13px;">
																	<%=MyUtility.generateHTML(disTreatmentPlanList, disTreatmentPlanParentByChild, "treatmentId", patCurDisDTO.patTrtmntId, patCurDisDTO, editAndView)%>
															 </table>
														 </div><!--/./form-group-->
													 </div>
												 <%}%>
									    	</div>

											<%id++; %>
										  	 <div role="tabpanel" class="tab-pane" id="complications">
								    		 	<%if(editAndView==true || (editAndView==false && patCurDisDTO.getComplications()!=null && patCurDisDTO.getComplications().length()>0)){%>
										    		<div class="panel-body">
														<div class="table-responsive">
															<table class="table" style="font-size: 13px;">
																<%=MyUtility.generateHTML(disOtherList, disOtherParentByChild, "othersId", patCurDisDTO.patOthersId, patCurDisDTO, editAndView)%>
															</table>
														 </div>
													 </div>
												 <%}%>
										 		</div>

												<%id++; %>
												<div role="tabpanel" class="tab-pane" id="specialNotes">
													<%if(editAndView==true || (editAndView==false && patCurDisDTO.getSpecialNotes()!=null && patCurDisDTO.getSpecialNotes().length()>0)){%>
													<div class="panel-body">
														<div class="table-responsive">
															<table class="table" style="font-size: 13px;">
																<textarea class="form-control" rows="2" name="specialNotes"><%=patCurDisDTO.getSpecialNotes()%></textarea>
															</table>
														</div>
													</div>
													<%}%>
												</div>

												<input type="submit" value="Submit" class="btn btn-primary" <%if(editAndView==false){%>style="display: none;"<%}%>>
												<input type="hidden" name="userId" id="userId" value="<%=userID%>">
											    <input type="hidden" name="diseaseId" id="diseaseId" value="<%=diseaseID%>">

									    	
									  		</div>
							  			</html:form>
										<%@ include file="../Disease/followup.jsp"%>
									</div>
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

