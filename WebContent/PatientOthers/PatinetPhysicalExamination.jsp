<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="patientOthers.PatientOthersService"%>
<%@page import="patientOthers.PatientOthersDTO"%>
<%@page import="disease.DiseaseService"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<%
String userID = request.getParameter("userID");

PatientOthersService patOthersServ = new PatientOthersService();
DiseaseService disServ = new DiseaseService();

PatientOthersDTO patPhyExmDTO = patOthersServ.getPatientPhyExmInfoDTOByID(Integer.parseInt(userID)); 

HashMap<Integer, String> disInspectionList = disServ.getDiseaseDetailsByDisIDAndDisType(14, MyConfig.diseaseInspection);
HashMap<Integer, String> disPalpationList = disServ.getDiseaseDetailsByDisIDAndDisType(14, MyConfig.diseasePalpation);
String name="";
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
									  	<li role="presentation" class="active"><a href="../PatientOthers/PatinetPhysicalExamination.jsp?userID=<%=userID%>">Physical Examination</a></li>
									  	<li role="presentation"><a href="../PatientOthers/PreAnestheticEvaluation.jsp?userID=<%=userID%>">Pre-Anesthetic Evaluation</a></li>
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
                        			<h5>Physical Examination</h5>
                    			</div><!--/./ibox-title-->
                   				<div class="ibox-content">
									<html:form action="/PatientPreAnsEve">
										<div class="form-horizontal">
											<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												<div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingOne">
                              							<h4 class="panel-title">
                                						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                  							Vital Sign
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr>
																		<td>Temp:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getVsTemp()%>" name="vsTemp"> </td>
											 
																		<td>Pulse:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getVsPulse() %>" name="vsPulse"> </td>
																	</tr>
																	<tr>
																		<td>Rate:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getVsRate() %>" name="vsRate"> </td>
																	
																		<td>Blood Pressure:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getVsBloodPre() %>" name="vsBloodPre"></td>
																	</tr>
										                         </table>
									                         </div><!--/./form-group--> 
                           								 </div>
                           							 </div>
                       							 </div><!--/./panel panel-default-->
												 
												 <div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingTwo">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
						                                	Body Size
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<tr>
																		<td>Height:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getBsHeight() %>" name="bsHeight"> </td>
																	
																		<td>Weight:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getBsWeight() %>" name="bsWeight"> </td>
																	</tr>
																	<tr>
																		<td>BMI:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getBsBMI() %>" name="bsBMI"> </td>
																	
																		<td>WC:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getBsWC() %>" name="bsWC"> </td>
																	</tr>
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												 
												 
												 <div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingThree">
                              							<h4 class="panel-title">
                                						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                  							Chest & Lungs
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr>
																		<td>Inspection:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getClInspec() %>" name="clInspec"> </td>
																		<td>Palpitation:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getClPalpi() %>" name="clPalpi"> </td>
																	</tr>
																	<tr>
																		<td>Percussion:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getClPrec() %>" name="clPrec"> </td>
																		<td>Auscultation:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getClAusc() %>" name="clAusc"> </td>
																	</tr>
										                         </table>
									                         </div><!--/./form-group--> 
                           								 </div>
                           							 </div>
                       							</div><!--/./panel panel-default-->
												 
												 
												<div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingFour">
                              							<h4 class="panel-title">
                                						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                  							Cardiovascular
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr>
																		<td>JVP:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getJvp() %>" name="jvp"> </td>
																	
																		<td>Carotid Pulse:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getCarotidPulsCar() %>" name="carotidPulsCar"> </td>
																	</tr>
																	<tr>
																		<td>Apex Impulse:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getApexImp() %>" name="apexImp"> </td>
																	
																		<td>Parasternal Impulse:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getParastImp() %>" name="parastImp"> </td>
																	</tr>
																	<tr>
																		<td>Palpable HS</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getPalpaHs() %>" name="palpaHs"></td>
																		<td>Thrill</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getThrill() %>" name="thrill"></td>
																	</tr>
																	
																	<tr>
																		<td>HS</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getHs() %>" name="hs"></td>
																		<td>EC</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getEc() %>" name="ec"></td>
																	</tr>
																	
																	<tr>
																		<td>OS</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getOs() %>" name="os"></td>
																	</tr>
																	
																	<tr>
																		<td>Murmur</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getMurmur() %>" name="murmur"></td>
																	</tr>
																	<tr>
																		<td>Peripheral Pulse</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getPeripheralPul() %>" name="peripheralPul"></td>
																	</tr>
										                         </table>
									                         </div><!--/./form-group--> 
                           								 </div>
                           							 </div>
                       							</div><!--/./panel panel-default-->
												
												<div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingFive">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
						                                	Abdomen
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<tr>
																		<td>Inspection</td>
																		<td>
																			<table>
																				<tr>
																					<%
																					for(int key : disInspectionList.keySet()){
																						name=disInspectionList.get(key);%>
																						<td style="font-size: 13px; padding: 10px;">
																							<%if(key==21){%>
																								<%=name%> <input type="text" name="inspecOthers" value="<%=patPhyExmDTO.getInspecOthers()%>">
																							<%}else{%>
																								<input type="checkbox" name="inspectionId" value="<%=key%>" <%if(patPhyExmDTO.patInspectionId.contains(key)==true){%>checked="checked"<%}%>> <%=name%>
																							<%}%>
																						</td>
																					<%}%>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td>Palpation</td>
																		<td class="groupTable">
																			<table>
																				<tr>
																					<%
																					for(int key : disPalpationList.keySet()){
																						name=disPalpationList.get(key);%>
																						<td style="font-size: 13px; padding: 10px;">
																							<%if(key==15){%>
																								<%=name%> <input type="text" name="palpaOthers" value="<%=patPhyExmDTO.getPalpaOthers()%>">
																							<%}else{%>
																								<input type="checkbox" name="palpationId" value="<%=key%>" <%if(patPhyExmDTO.patPalpationId.contains(key)==true){%>checked="checked"<%}%>> <%=name%>
																							<%}%>
																						</td>
																					<%}%>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td>Percussion Note:</td>
																		<td> <input type="text" name="precusNote" value="<%=patPhyExmDTO.getPrecusNote()%>"> </td>
																	</tr>
																	
																	<tr>
																		<td>Auscultation</td>
																		<td> <input type="text" name="auscultion" value="<%=patPhyExmDTO.getAuscultion()%>"> </td>
																	</tr>
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												
												<div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingNine">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
						                                	Neck
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseNine" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNine">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<tr>
																		<td>Neck vein:</td>
																		<td>
																			<select name="neckVein" style="width: 90px;">
																				<option value="1" <%if(patPhyExmDTO.getNeckVein()==1){%>selected="selected"<%}%>>Normal</option>
																				<option value="2" <%if(patPhyExmDTO.getNeckVein()==2){%>selected="selected"<%}%>>Engorged</option>
																			</select>
																		</td>
																		
																	</tr>
																	<tr>
																		<td>Movement of Neck:</td>
																		<td>
																			<select name="movOfNeck" style="width: 90px;">
																				<option value="1" <%if(patPhyExmDTO.getMovOfNeck()==1){%>selected="selected"<%}%>>Normal</option>
																				<option value="2" <%if(patPhyExmDTO.getMovOfNeck()==2){%>selected="selected"<%}%>>Restricted</option>
																			</select>
																		</td>
																	</tr>
																	<tr>
																		<td>Thyroid Gland:</td>
																		<td>
																			<select name="thyroidGland" style="width: 90px;">
																				<option value="1" <%if(patPhyExmDTO.getThyroidGland()==1){%>selected="selected"<%}%>>Normal</option>
																				<option value="2" <%if(patPhyExmDTO.getThyroidGland()==2){%>selected="selected"<%}%>>Enlarged</option>
																			</select>
																		</td>
																	</tr>
																	<tr>
																		<td>Carotid Pulse: </td>
																		<td><input type="text" name="carotidPulse" value="<%=patPhyExmDTO.getCarotidPulse()%>"> </td>
																	</tr>
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												
												<div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingSix">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
						                                	Musculo-skeletal
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<tr>
																		<td>Upper Limb:</td>
																		<td>
																			<input type="checkbox" name="" value="Joint pain"> Joint Pain &nbsp;
																			<input type="checkbox" name="" value="Swelling"> Swelling &nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td>Finger Nail:</td>
																		<td> <input type="checkbox" name="" value="Clabbing"> Clabbing &nbsp;
																			<input type="checkbox" name="" value="Koilnychias"> Koilnychias &nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td>Lower Limb:</td>
																		<td><input type="checkbox" name="" value="Edema"> Edema &nbsp; 
																			<input type="checkbox" name="" value="Joint pain"> Joint Pain &nbsp;
																			<input type="checkbox" name="" value="Swelling"> Swelling &nbsp;
																		</td>
																	</tr>
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												
												<div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingSeven">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
						                                	Neurologic
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<tr>
																		<td>General Intellectual Function:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getGenIntFunc() %>" name="genIntFunc"> </td>
																	</tr>
																	<tr>
																		<td>Motor Function & Reflexes:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getMotorFunc() %>" name="motorFunc"> </td>	
																	</tr>
																	<tr>
																		<td>Sensory:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getSensory() %>" name="sensory"></td>
																	</tr>
																	<tr>
																		<td>Carnial Nerve:</td>
																		<td><input type="text" value="<%=patPhyExmDTO.getCarnialNerve() %>" name="carnialNerve"></td>
																	</tr>
																				</table>
																			</div>
																		</div>
																	</div>
																</div><!--/./panel panel-default-->
																
																<div class="panel panel-default">
																	<div class="panel-heading" role="tab" id="headingEight">
																		<h4 class="panel-title">
																		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
																			Other Significant Clinical Descriptors or Measures
																		</a>
																		</h4>
																	</div>
																	<div id="collapseEight" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEight">
																		<div class="panel-body">
																			<div class="table-responsive">
																				<table class="table" style="font-size: 13px;">
																					<textarea rows="2" cols="20" name="otherDescip"><%=patPhyExmDTO.getOtherDescip() %></textarea>	
																				</table>
																			</div>
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

