<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.*"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="patientOthers.PatientOthersService"%>
<%@page import="patientOthers.PatientOthersDTO"%>

<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%@page import="disease.DiseaseDTO"%><html>
<%
String userID = request.getParameter("userID");

PatientOthersService patOthersServ = new PatientOthersService();

PatientOthersDTO patCurInfoDTO = patOthersServ.getPatientOthersDTOByID(Integer.parseInt(userID)); 

HashMap<Integer, String> patPreAnestheticEvaluationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfPreAnestheticEvaluation);
HashMap<Integer, String> disPreAnestheticEvaluationListParentByChild = patOthersServ.getParentByChildWithInfoId(MyConfig.infoHistoryOfPreAnestheticEvaluation);

HashMap<Integer, String> patIntraOralExaminationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfPreAnEvIntraOralExamination);
HashMap<Integer, String> disIntraOralExaminationListParentByChild = patOthersServ.getParentByChildWithInfoId(MyConfig.infoHistoryOfPreAnEvIntraOralExamination);

HashMap<Integer, String> patExtraOralExaminationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfPreAnEvExtraOralExamination);
HashMap<Integer, String> disExtraOralExaminationListParentByChild = patOthersServ.getParentByChildWithInfoId(MyConfig.infoHistoryOfPreAnEvExtraOralExamination);


String name="";
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
	  })
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
            			<div class="row border-bottom">
				            <nav class="navbar navbar-static-top navbar-inverse" role="navigation" style="margin-bottom: 0">
				                <div class = "container-fluid">
				                	<ul class="nav nav-tabs">
				                	
				               			<li role="presentation"><a href="../PatientOthers/PatientPersonalHistory.jsp?userID=<%=userID%>">Social & Personal</a></li>
								  		<li role="presentation"><a href="../PatientOthers/Investigation.jsp?userID=<%=userID%>">Investigation</a></li>
									  	<li role="presentation"><a href="../PatientOthers/PatinetPhysicalExamination.jsp?userID=<%=userID%>">Physical Examination</a></li>
									  	<li role="presentation" class="active"><a href="../PatientOthers/PreAnestheticEvaluation.jsp?userID=<%=userID%>">Pre-Anesthetic Evaluation</a></li>
									  	<li role="presentation"><a href="../PatientOthers/GeneralSurvey.jsp?userID=<%=userID%>">General Survey</a></li>
										<li role="presentation" class="dropdown">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Surgery<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PastSergHistory/SearchPastSergHistory.jsp?userID=<%=userID%>">Surgical History</a></li>
									      		<li role="presentation"><a href="../PastSergHistory/AddPastSergHistory.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  	<li role="presentation" class="dropdown">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Drugs<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PatientOthers/PatientDrugAnMedication.jsp?userID=<%=userID%>">Drug History</a></li>
									      		<li role="presentation"><a href="../PatientOthers/AddMedication.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  
									</ul>
				                </div>
				                
				            </nav><!--/./navbar navbar-static-top-->
		            
        				</div><!--/./row border-bottom-->
            		
                			<div class="ibox float-e-margins">
                    			<div class="ibox-title">
                        			<h5>Pre-anesthetic evaluation</h5>
                    			</div><!--/./ibox-title-->
                   				<div class="ibox-content">
									<html:form action="/PatientPreAnsEve">
										<div class="form-horizontal">
											<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												<div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingOne">
                              							<h4 class="panel-title">
                                						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                  							History
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr><td style="border: none;">Current problem <input type="text" name="curProb" value="<%=patCurInfoDTO.getCurProb()==null?"":patCurInfoDTO.getCurProb()%>"></td></tr>
																	<tr><td style="border: none;">Other known problem <input type="text" name="otherKnownProb" value="<%=patCurInfoDTO.getOtherKnownProb()%>"></td></tr>
																	<tr><td style="border: none;">Treatment/medicines for the problem <input type="text" name="curProbTreatment" value="<%=patCurInfoDTO.getCurProbTreatment() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp;Dose <input type="text" name="curDose" value="<%=patCurInfoDTO.getCurDose() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp;Duration <input type="text" name="curDoseDuration" value="<%=patCurInfoDTO.getCurDoseDuration() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp;Effectiveness <input type="text" name="curDoseEffectiveness" value="<%=patCurInfoDTO.getCurDoseEffectiveness() %>"></td></tr>
																	
																	<tr><td style="border: none;">Current drugs use <input type="text" name="curDrugsUse" value="<%=patCurInfoDTO.getCurDrugsUse() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp; Reasons <input type="text" name="curDrugsReason" value="<%=patCurInfoDTO.getCurDrugsReason() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp; Dose <input type="text" name="curDrugsDose" value="<%=patCurInfoDTO.getCurDrugsDose() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp; Duration <input type="text" name="curDrugsDur" value="<%=patCurInfoDTO.getCurDrugsDur() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp; Effectiveness <input type="text" name="curDrugsEffectiveness" value="<%=patCurInfoDTO.getCurDrugsEffectiveness() %>"></td></tr>
																	<tr><td style="border: none;">&emsp;&emsp; Side effect <input type="text" name="curDrugsSideEffect" value="<%=patCurInfoDTO.getCurDrugsSideEffect() %>"></td></tr>
																	<%=MyUtility.generateHTML(patPreAnestheticEvaluationList, disPreAnestheticEvaluationListParentByChild, "infoId", patCurInfoDTO.patInfoId, patCurInfoDTO)%>
										                         </table>
									                         </div><!--/./form-group--> 
                           								 </div>
                           							 </div>
                       							 </div><!--/./panel panel-default-->
												 
												 <div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingTwo">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
						                                	Intra oral examination:
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<%=MyUtility.generateHTML(patIntraOralExaminationList, disIntraOralExaminationListParentByChild, "infoId", patCurInfoDTO.patInfoId, patCurInfoDTO)%>
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												 
												 
												 <div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingThree">
                              							<h4 class="panel-title">
                                						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                  							Extra oral examination:
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<%=MyUtility.generateHTML(patExtraOralExaminationList, disExtraOralExaminationListParentByChild, "infoId", patCurInfoDTO.patInfoId, patCurInfoDTO)%>
										                         </table>
									                         </div><!--/./form-group--> 
                           								 </div>
                           							 </div>
                       							</div><!--/./panel panel-default-->
												 
												 
												
											
                          					<div style="clear:both;"></div>    
                       					</div>
                       					
										<input type="submit" value="Submit" class="btn btn-primary">
										<input type="hidden" name="userId" id="userId" value="<%=userID%>">
			                        <!--  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			            			<button type="button" class="btn btn-primary">Save changes</button>-->
          						</div>
               				</html:form>
                			<div style="color:both">&nbsp;</div>
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

