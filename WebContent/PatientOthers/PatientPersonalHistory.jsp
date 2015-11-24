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

<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<%
String userID = request.getParameter("userID");

PatientOthersService patOthersServ = new PatientOthersService();

PatientOthersDTO patCurInfoDTO = patOthersServ.getPatientOthersDTOByID(Integer.parseInt(userID)); 

HashMap<Integer, String> patExposureToList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoExposureTo);
HashMap<Integer, String> patHabitsList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHabits);
HashMap<Integer, String> patMenstrualHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoMenstrualHistory);
HashMap<Integer, String> patObstetricalHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoObstetricalHistory);
HashMap<Integer, String> patFamilyRelevantDiseaseHistoryList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoFamilyRelevantDiseaseHistory);
HashMap<Integer, String> patHistoryOfImmunizationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfImmunization);
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
			            <nav class="nav navbar navbar-static-top navbar-inverse" role="navigation" style="margin-bottom: 0">
			                <div class = "container-fluid">
			                	<ul class="nav nav-tabs">
			                	
			               			<li role="presentation" class="active"><a href="../PatientOthers/PatientPersonalHistory.jsp?userID=<%=userID%>">Social & Personal</a></li>
							  		<li role="presentation"><a href="../PatientOthers/Investigation.jsp?userID=<%=userID%>">Investigation</a></li>
								  	<li role="presentation"><a href="../PatientOthers/PatinetPhysicalExamination.jsp?userID=<%=userID%>">Physical Examination</a></li>
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
                        			<h5>Social and Personal History</h5>
                    			</div><!--/./ibox-title-->
                   				<div class="ibox-content">
									<html:form action="/PatientSocialAndPersonalHistory">
										<div class="form-horizontal">
											<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												<div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingOne">
                              							<h4 class="panel-title">
                                						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                  							Exposure to
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr>
										                    		<%for(int key : patExposureToList.keySet()){%>
						                    							<td class="tableData2">
							                    							<input type="checkbox" name="exposureTo" value="<%=key%>" <%if(patCurInfoDTO.patExposureToId.contains(key)==true){%>checked="checked"<%}%>> <%=patExposureToList.get(key)%>
							                    						</td>
							       									<%}%>
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
						                                	Hobbies
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<input type="text" name="hobbies" value="<%=patCurInfoDTO.getHobbies()%>">
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												 
												 
												 <div class="panel panel-default">
                            						<div class="panel-heading" role="tab" id="headingThree">
                              							<h4 class="panel-title">
                                						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                  							Habits
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr>
																	<td>
							                    						<input type="checkbox" name="habitsId" value="4" <%if(patCurInfoDTO.patHabitsId.contains(4)==true){%>checked="checked"<%}%>> <%=patHabitsList.get(4)%>
							                    					</td>
							                    					<td>
							                    						Each day <input type="text" name="cigarettesStick" value="<%=patCurInfoDTO.getCigarettesStick()%>"> Stick
							                    					</td>
							                    					<td>
							                    						How Long <input type="text" name="cigarettesYear" value="<%=patCurInfoDTO.getCigarettesYear()%>"> year
							                    					</td>
									                    		</tr>
									                    		<tr>
																	<td>
							                    						<input type="checkbox" name="habitsId" value="5" <%if(patCurInfoDTO.patHabitsId.contains(5)==true){%>checked="checked"<%}%>> <%=patHabitsList.get(5)%>
							                    					</td>
							                    					<td>
							                    						Each day <input type="text" name="smokelessTobaccoTime" value="<%=patCurInfoDTO.getSmokelessTobaccoTime()%>"> Time
							                    					</td>
							                    					<td>
							                    						How Long <input type="text" name="smokelessTobaccoYear" value="<%=patCurInfoDTO.getSmokelessTobaccoYear()%>"> year
							                    					</td>
									                    		</tr>
									                    		<tr>
																	<td>
							                    						<input type="checkbox" name="habitsId" value="6" <%if(patCurInfoDTO.patHabitsId.contains(6)==true){%>checked="checked"<%}%>> <%=patHabitsList.get(6)%>
							                    					</td>
							                    					<td>
							                    						Each day <input type="text" name="alcoholPack" value="<%=patCurInfoDTO.getAlcoholPack()%>"> Pack
							                    					</td>
							                    					<td>
							                    						How Long <input type="text" name="alcoholYear" value="<%=patCurInfoDTO.getAlcoholYear()%>"> year
							                    					</td>
									                    		</tr>
									                    		<tr>
																	<td>
							                    						<input type="checkbox" name="habitsId" value="7" <%if(patCurInfoDTO.patHabitsId.contains(7)==true){%>checked="checked"<%}%>> <%=patHabitsList.get(7)%>
							                    					</td>
							                    					<td>
							                    						<input type="text" name="otherSubsAbuse" value="<%=patCurInfoDTO.getOtherSubsAbuse()%>">
							                    					</td>
							                    					<td>
							                    						How Long <input type="text" name="otherSubsAbuseYear" value="<%=patCurInfoDTO.getOtherSubsAbuseYear()%>"> year
							                    					</td>
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
                                  							Personal Information:
                                						</a>
                              							</h4>
                            						</div>
                            						<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                              							<div class="panel-body">
															<div class="table-responsive">
                                 								<table class="table" style="font-size: 13px;">
																	<tr>
																		<td>Marital Status</td>
																		<td>
													                    	<select name="maritalStatus" id="maritalStatus" style="">
													                    		<option <%if(patCurInfoDTO.getMaritalStatus()==1){%>selected="selected"<%}%> value="1">Single</option>
													                    		<option <%if(patCurInfoDTO.getMaritalStatus()==2){%>selected="selected"<%}%> value="2">Married</option>
													                    		<option <%if(patCurInfoDTO.getMaritalStatus()==3){%>selected="selected"<%}%> value="3">Divorced</option>
													                    		<option <%if(patCurInfoDTO.getMaritalStatus()==4){%>selected="selected"<%}%> value="4">Unknown</option>
													                    	</select>
													                    </td>
													                </tr>
													                <tr>
													                	<td>Religion</td>
													                	<td>
													                		<select class="form-control m-b" name="religionId" id="religionId">
																				<%for (int key : OthersInfo.ReligionByID.keySet()) {%>
																					<option <%if(patCurInfoDTO.getReligionId()==key){%>selected="selected"<%}%> value="<%=key%>"><%=OthersInfo.ReligionByID.get(key)%></option>
																				<%}%>
																			</select>
													                	</td>
													                </tr>
													                <tr>
													                	<td>Social Status</td>
													                	<td>
													                		<select class="form-control m-b" name="socialStatusId" id="socialStatusId">
																				<%for (int key : OthersInfo.SocialStatByID.keySet()) {%>
																					<option <%if(patCurInfoDTO.getSocialStatusId()==key){%>selected="selected"<%}%> value="<%=key%>"><%=OthersInfo.SocialStatByID.get(key)%></option>
																				<%}%>
																			</select>
													                	</td>
													                </tr>
													                <tr>
													                	<td>Occupation</td>
													                	<td><input class="form-control" value="<%=patCurInfoDTO.getOccupation() %>" type="text" name="occupation"></td>
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
						                                	Menstrual history
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<%for(int key : patMenstrualHistoryList.keySet()){%>
					                    							<tr>
									        							<td class="tableData2"> 
							                    							<input type="checkbox" name="menstrualHistoryId" value="<%=key%>" <%if(patCurInfoDTO.patMenstrualHistoryId.contains(key)==true){%>checked="checked"<%}%>> <%=patMenstrualHistoryList.get(key)%>
							                    						</td>
						                    						</tr>
							       								<%}%>	
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												
												
												
												<div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingSix">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
						                                	Obstetrical History
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<%for(int key : patObstetricalHistoryList.keySet()){%>
					                    							<tr>
									        							<td class="tableData2">
							                    							<input type="checkbox" name="obstetricalHistoryId" value="<%=key%>" <%if(patCurInfoDTO.patObstetricalHistoryId.contains(key)==true){%>checked="checked"<%}%>> <%=patObstetricalHistoryList.get(key)%>
							                    						</td>
						                    						</tr>
																	<%}%>	
						                                		</table>
						                           			</div>
														</div>
					                           		</div>
					                         	</div><!--/./panel panel-default-->
												
												<div class="panel panel-default">
						                         	<div class="panel-heading" role="tab" id="headingSeven">
						                            	<h4 class="panel-title">
						                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
						                                	Family History
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<tr>
																		<td>Relevant disease history</td>
																		<td class="groupTable">
																			<table>
																				<tr>
																					<%
																					int count=1;
																					for(int key : patFamilyRelevantDiseaseHistoryList.keySet()){
																						if(count%2==0){%>
																								</tr>
																							
																								<tr>
																						<%}
																						count++;%>
																						<td class="tableData2">
																							<input type="checkbox" name="relevantDiseaseHistoryId" value="<%=key%>" <%if(patCurInfoDTO.patRelevantDiseaseHistoryId.contains(key)==true){%>checked="checked"<%}%>> <%=patFamilyRelevantDiseaseHistoryList.get(key)%>
																						</td>
																					<%}%>
																				</tr>
																					
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td class="tableData2">Father</td>
																		<td><input type="text" name="father" value="<%=patCurInfoDTO.getFather()%>"></td>
																	</tr>
																	<tr>
																		<td class="tableData2">Mother</td>
																		<td><input type="text" name="mother" value="<%=patCurInfoDTO.getMother()%>"></td>
																	</tr>
																	<tr>
																		<td class="tableData2">Brother</td>
																		<td><input type="text" name="brother" value="<%=patCurInfoDTO.getBrother()%>"></td>
																	</tr>
																	<tr>
																		<td class="tableData2">Sister</td>
																		<td><input type="text" name="sister" value="<%=patCurInfoDTO.getSister()%>"></td>
																	</tr>
																	<tr>
																		<td class="tableData2">Wife/Husband</td>
																		<td><input type="text" name="wifeOrHusband" value="<%=patCurInfoDTO.getWifeOrHusband()%>"></td>
																	</tr>
																	<tr>
																		<td class="tableData2">Consanguinity of marriage</td>
																		<td><input type="text" name="consanguiOfMarri" value="<%=patCurInfoDTO.getConsanguiOfMarri()%>"></td>
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
						                                	History of immunization
						                               	</a>
						                             	</h4>
						                           	</div>
						                           	<div id="collapseEight" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEight">
					                             		<div class="panel-body">
						                        			<div class="table-responsive">
						                                		<table class="table" style="font-size: 13px;">
						                                			<%for(int key : patHistoryOfImmunizationList.keySet()){%>
					                    							<tr>
									        							<td class="tableData2">
							                    							<input type="checkbox" name="immunizationHistoryId" value="<%=key%>" <%if(patCurInfoDTO.patImmunizationHistoryId.contains(key)==true){%>checked="checked"<%}%>> <%=patHistoryOfImmunizationList.get(key)%>
							                    							<%if(key==41){%>
							                    								<input type="text" name="famlyDisOther" value="<%=patCurInfoDTO.getFamlyDisOther()%>">
							                    							<%}%>
							                    						</td>
						                    						</tr>
																	<%}%>	
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

