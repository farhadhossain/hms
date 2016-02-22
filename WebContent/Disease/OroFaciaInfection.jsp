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
<%@page import="disease.FollowUpDTO"%>
<%@page import="disease.DiseaseDTO"%>
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

HashMap<Integer, DiseaseMetaData> disOtherList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseOthers);
HashMap<Integer, String> disOtherParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseOthers);

DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(Integer.parseInt(userID), Integer.parseInt(diseaseID));
ArrayList<FollowUpDTO> followUpList=disServ.getFollowUpReport(Integer.parseInt(userID), Integer.parseInt(diseaseID));

HashMap<Integer, DiseaseMetaData> disHistoryList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseHistory);
HashMap<Integer, String> disHistoryParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseHistory);

HashMap<Integer, DiseaseMetaData> disInspectionList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseInspection);
HashMap<Integer, String> disInspecParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseInspection);

HashMap<Integer, DiseaseMetaData> disPalpationList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseasePalpation);
HashMap<Integer, String> disPalpationParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseasePalpation);

HashMap<Integer, DiseaseMetaData> disTreatmentPlanList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseTreatmentPlan);
HashMap<Integer, String> disTreatmentPlanParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseTreatmentPlan);

HashMap<Integer, DiseaseMetaData> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseDiagnosis);
HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseDiagnosis);


HashMap<Integer, String> disSpecialCaseList = disServ.getSpecialCaseIdNameList(Integer.parseInt(diseaseID));

int id = 1;
String actionNameFollowUp="/NewFindingsOroFaciaInfecPatient";
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
                        			<h5>Oro-facia Infection   </h5>
                    			</div><!--/./ibox-title-->
                   				<div class="ibox-content">
									<html:form action="/PatientOroFacialInfection">
										<div class="form-horizontal">
											<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												
												<%@ include file="../includeDiseaseParts/includeHistory.jsp"%>
												<%id++;%>
												 
												<%if(editAndView==true || (editAndView==false && patCurDisDTO.patInspId.isEmpty()==false)){%>
													 <div class="panel panel-default">
							                         	<div class="panel-heading" role="tab" id="headingTwo">
							                            	<h4 class="panel-title">
							                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							                                	Extra Oral Examination
							                               	</a>
							                             	</h4>
							                           	</div>
							                           	<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
						                             		<div class="panel-body">
							                        			<div class="table-responsive">
							                                		<table class="table" style="font-size: 13px;">
							                                			<%=MyUtility.generateHTML(disInspectionList, disInspecParentByChild, "inspectionId", patCurDisDTO.patInspId, patCurDisDTO, editAndView)%>
							                                		</table>
							                           			</div>
															</div>
						                           		</div>
						                         	</div><!--/./panel panel-default-->
												<%}%>
												<%id++;%>
												
												 <%int key=12; 
								                  HashMap<Integer, DiseaseMetaData> disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(Integer.parseInt(diseaseID), key);
								                  HashMap<Integer, String> disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(Integer.parseInt(diseaseID), key);
												 boolean isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(Integer.parseInt(userID), Integer.parseInt(diseaseID), key);%>
                                         	 	 <%@ include file="../includeDiseaseParts/includeSpecialCaseIdOnly.jsp"%>
												 <%id++;%>
												 
												<%@ include file="../includeDiseaseParts/includePalpation.jsp"%>
												<%id++;%>
												
												<%key=17; 
								                  disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(Integer.parseInt(diseaseID), key);
								                  disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(Integer.parseInt(diseaseID), key);
								                  isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(Integer.parseInt(userID), Integer.parseInt(diseaseID), key);%>
                          
                                         	 	 <%@ include file="../includeDiseaseParts/includeSpecialCaseIdOnly.jsp"%>
				                         	 	 
												<%id++;
												%>
												
												<%@ include file="../includeDiseaseParts/includeDiagnosis.jsp"%>
												
												<%id++;%>
												
												<%@ include file="../includeDiseaseParts/includeTreatmentPlan.jsp"%>
					                        	
					                        	<%id++;%>
												
												<%--<%@ include file="../Disease/specialNoteAndComplication.jsp"%>--%>

												<%if(editAndView==true || (editAndView==false && patCurDisDTO.getComplications()!=null && patCurDisDTO.getComplications().length()>0)){%>

													<div class="panel panel-default">
														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
																<h4 class="panel-title">
																	<a data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
																		Complications
																	</a>
																</h4>
															</div>
															<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
																<div class="panel-body">
																	<div class="table-responsive">
																		<table class="table" style="font-size: 13px;">
																			<%=MyUtility.generateHTML(disOtherList, disOtherParentByChild, "othersId", patCurDisDTO.patOthersId, patCurDisDTO, editAndView)%>
																		</table>
																	</div>
																</div>
															</div>
													</div>

												<%}%>

												<%id++;%>

												<%@ include file="../Disease/SpecialNote.jsp"%>
												
											    <%@ include file="../includeDiseaseParts/includeNAD.jsp"%> 
											    
                       					</div>
                       					
			                        <input type="submit" value="Submit" class="btn btn-primary" <%if(editAndView==false){%>style="display: none;"<%}%>>
									<input type="hidden" name="userId" id="userId" value="<%=userID%>">
								    <input type="hidden" name="diseaseId" id="diseaseId" value="<%=diseaseID%>">
								    
			                        <!--  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			            			<button type="button" class="btn btn-primary">Save changes</button>-->
          						</div>
               				</html:form>
                			<div style="color:both">&nbsp;</div>
                			
                			<%@ include file="../Disease/followup.jsp"%>
                			
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

