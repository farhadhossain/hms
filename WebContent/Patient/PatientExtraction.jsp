<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="extraction.ExtractionService"%>
<%@page import="extraction.ExtractionDTO"%>
<%@page import="utility.MyConfig"%><html>
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page import="java.util.HashMap" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
if(accountID==null){
	accountID=(String)session.getAttribute("accountID");
	if(accountID==null){
		accountID="-1";
	}
}else{
	session.setAttribute("accountID", accountID);
}
System.out.println("accountID: "+accountID);
PatientService patientServ = new PatientService();
PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));
ExtractionService extracServ = new ExtractionService();
HashMap<Integer, String> extractionNameByID = extracServ.getExtractionListByID();
HashMap<Integer, Integer> extractionTakaByID = extracServ.getExtractionTakaByID();
ExtractionDTO extractionDTO = extracServ.getExtractionDTOByID(Integer.parseInt(accountID));

boolean isUncheckedHide=false;
if(loginDTO.getRoleID()==MyConfig.dutyNurseRole){
	isUncheckedHide=true;
}

System.out.println("isUncheckedHide: "+isUncheckedHide);

%>
<head>
 	<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
 	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
 	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="../Assets/Styles/toastr.min.css">

	<script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="../Assets/js/toastr.min.js"></script>
 	<script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
 	<script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-tpls-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/module.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/controllers/treatmentPlanController.js"></script>
   <style>
	   .remove-margin{
		   margin-left: 0px;
		   margin-right: 0px;
	   }
   </style>
	<script>
	var isDutyNurse	= <%=loginDTO.getRoleID()==MyConfig.dutyNurseRole%>;
	function updateTotalAmount(checkbox, amount){
		var textbox = document.getElementById('totalTk').value;
		if (checkbox.checked){
			textbox=parseInt(textbox)+parseInt(amount);
	    }else{
	    	textbox=parseInt(textbox)-parseInt(amount);
	    }
		document.getElementById('totalTk').value=textbox;
	}

	function updateTotalAmountFromTextBox(name, amount, previousStore){
		var textbox = document.getElementById(name).value;
		var textboxPrevious = document.getElementById(previousStore).value;
		var lengthDiff = parseInt(textbox.length)-parseInt(textboxPrevious.length);
		var previousSum = document.getElementById('totalTk').value;
		document.getElementById('totalTk').value=parseInt(previousSum)+(parseInt(lengthDiff)*parseInt(amount));
		document.getElementById(previousStore).value=textbox;
	}
	
    </script>
	<style>
		.borders span{
			border-top:1px solid #999;
			border-right:1px solid #999;
			border-bottom:1px solid #999;
			width:20px;
			display: block;
			float: left;
			text-align: center;
		}

		.borders span:first-child{
			border-left:
			1px solid #999;
		}

		.borders span > span{
			border:0;
		}

		.col-lg-6{
			padding-right:0px;
			padding-left:0px;
		}

	</style>
</head>
<body ng-app="hms">
<div class="container" style="padding-top: 40px;width:1300px;"  ng-controller = "TreatmentPlanController">
  	<div class="row">
  		<div class="container-fluid">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
				   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					 <img class="img-responsive" src="../Assets/Images/banner.jpg">
				   </div>
				</div>
				<br>
				<!-- Patient info panel -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							Patient Details <a href="../Patient/EditPatient.jsp?accountID=<%=accountID %>" style="color: green;">[Referred To]</a>
						</h3>
					</div>
					<div class="panel-body_1 table-responsive ">
						<table class="table-bordered table">
							<thead>
							<tr class="success">
								<th>Ticket Number</th>
								<th>Patient ID</th>
								<th>Name</th>
								<th>Age</th>
								<th>Sex</th>
								<th>Registration Date</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td><%=patientDTO.getTicketNumber() %></td>
								<td><%=patientDTO.getAccId() %></td>
								<td><%=patientDTO.getName() %></td>
								<td><%=patientDTO.getAge() %></td>
								<td><%=patientDTO.getSex() %></td>
								<td><%=patientDTO.getDateOfRec() %></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- Patient info panel END-->
				<div class="row remove-margin well well-sm" ng-init="getPrescription('<%=accountID%>')" ng-show="!isDutyNurse">
					<div class="col-md-4" style="border-right: 1px solid gray;">
					    <div class="row"><div class="col-sm-9"><h4><i class="fa fa-comments"></i>&nbsp;&nbsp;Chief Complaint (C/C)</h4></div></div>
					    <div class="row" ng-repeat="cc in prescription.chiefComplain">
							<div ng-if="isString(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div>
							<div ng-if="isObject(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}.
								<span ng-repeat="(key, value) in cc">
									{{key}}
									<div class="row"><div class="col-sm-6" style="border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;text-align: right;">&nbsp;{{value.lt.join(', ')}}</div><div style="border-bottom: 1px solid #CCC;" class="col-sm-6">&nbsp;{{value.rt.join(', ')}}</div></div>
									<div class="row"><div class="col-sm-6" style="border-right: 1px solid #CCC;text-align: right;">&nbsp;{{value.lb.join(', ')}}</div><div class="col-sm-6">&nbsp;{{value.rb.join(', ')}}</div></div>
								</span>
							</div>
						</div>
						<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-hospital-o"></i>&nbsp;&nbsp;O/E</h4></div></div>
						<div class="row" ng-repeat="cc in prescription.onObservation">
							<div ng-if="isString(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div>
							<div ng-if="isObject(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}.
								<span ng-repeat="(key, value) in cc">
									{{key}}
									<div class="row"><div class="col-sm-6" style="border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;text-align: right;">&nbsp;{{value.lt.join(', ')}}</div><div style="border-bottom: 1px solid #CCC;" class="col-sm-6">&nbsp;{{value.rt.join(', ')}}</div></div>
									<div class="row"><div class="col-sm-6" style="border-right: 1px solid #CCC;text-align: right;">&nbsp;{{value.lb.join(', ')}}</div><div class="col-sm-6">&nbsp;{{value.rb.join(', ')}}</div></div>
								</span>
							</div>
						</div>
						<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-hospital-o"></i>&nbsp;&nbsp;Investigation</h4></div></div>
						<div class="row" ng-repeat="cc in prescription.investigation"><div class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div></div>
						<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-stethoscope"></i>&nbsp;&nbsp;Diagonsis</h4></div></div>
						<div class="row" ng-repeat="cc in prescription.onObservation">
							<div ng-if="isString(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div>
							<div ng-if="isObject(cc)" ng-if="isObject(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}.
						<span ng-if="!cc.other" ng-repeat="(key, value) in cc">
							{{key}}
							<table style="width: 100%;">
								<tr><td width="50%" style="border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lt.join(', ')}}</td><td width="50%" style="border-bottom: 1px solid #CCC;padding: 5px;">&nbsp;{{value.rt.join(', ')}}</td></tr>
								<tr><td width="50%" style="border-right: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lb.join(', ')}}</td><td width="50%" style="padding: 5px;">&nbsp;{{value.rb.join(', ')}}</td></tr>
							</table>
						</span>
								<span ng-if="cc.other">{{cc.other.text}}</span>
							</div>
						</div>
					</div>
					<div class="col-md-8">
						<div class="row"><div class="col-sm-9"><h4><i class="fa fa-medkit"></i>&nbsp;&nbsp;Rx</h4></div></div>
						<div class="row col-sm-offset-1" ng-repeat="med in prescription.medicines">
							<div class="col-sm-6">{{$index+1}}. {{med.medicineName}} ({{med.medicineType}})</div>
							<div class="col-sm-2">{{med.frequency}}</div>
							<div class="col-sm-2">{{med.afterMeal}}</div>
						</div>

						<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-h-square"></i>&nbsp;&nbsp;Advice</h4></div></div>
						<div class="row col-lg-offset-1">
							<div class="col-sm-11">
								<div class="row" ng-repeat="cc in prescription.advice">
									<div ng-if="isString(cc)" class="col-sm-9">{{$index+1}}. {{cc}}</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default" ng-init="getExtractionItems()">
					<div class="panel-heading">
						<h3 class="panel-title">
							Treatment Plan
						</h3>
					</div>

					<div class="panel-body table-responsive ">
						<html:form action="/NewExtraction">
						<div class="row">
							<div class="col-md-4 col-sm-6 col-xs-12">
								<table>
									<tr><td>Extraction<br><br></td></tr>

									<%--<%for(int key : extractionNameByID.keySet()){
										String value1="", value2="", value3="", value4="";
										String tkValue1="", tkValue2="", tkValue3="", tkValue4="";

										if(key==1){
											value1=extractionDTO.getExtractionId1_1();
											value2=extractionDTO.getExtractionId1_2();
											value3=extractionDTO.getExtractionId1_3();
											value4=extractionDTO.getExtractionId1_4();
										}else if(key==2){
											value1=extractionDTO.getExtractionId2_1();
											value2=extractionDTO.getExtractionId2_2();
											value3=extractionDTO.getExtractionId2_3();
											value4=extractionDTO.getExtractionId2_4();
										}else if(key==3){
											value1=extractionDTO.getExtractionId3_1();
											value2=extractionDTO.getExtractionId3_2();
											value3=extractionDTO.getExtractionId3_3();
											value4=extractionDTO.getExtractionId3_4();
										}

										if(key>3){
											break;
										}
										if(isUncheckedHide==true && extractionDTO.patCurExtractionList.contains(key)==false){
											continue;
										}
										%>
										<tr>
											<td>
												<input type="checkbox" <%if(extractionDTO.patCurExtractionList.contains(key)){%>checked="checked"<%}%> name="extractionId" value="<%=key %>" style="margin-left: 40px;" checklist-model="treatmentPlan.extraction">&emsp;<%=extractionNameByID.get(key) %>
													<table border="1" rules="all" frame="void" style="margin-left: 80px;width:100px;">
														<tr>
															<td style="margin: 2pt;"><input type="text" value="<%=value1 %>" class="input-small" name="extractionId<%=key%>_1" id="extractionId<%=key%>_1" style="text-align: right" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_1', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_1')"></td>
															<td><input type="text" value="<%=value2 %>" class="input-small" name="extractionId<%=key%>_2" id="extractionId<%=key%>_2" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_2', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_2')"></td>
														</tr>
														<tr>
															<td style="margin: 2pt;"><input type="text" value="<%=value3 %>" class="input-small" name="extractionId<%=key%>_3" id="extractionId<%=key%>_3" style="text-align: right" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_3', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_3')"></td>
															<td><input type="text" value="<%=value4 %>" class="input-small" name="extractionId<%=key%>_4" id="extractionId<%=key%>_4" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_4', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_4')"></td>
														</tr>
													</table>
												<input type="hidden" id="amount<%=key%>_1" value="<%=value1 %>">
												<input type="hidden" id="amount<%=key%>_2" value="<%=value2 %>">
												<input type="hidden" id="amount<%=key%>_3" value="<%=value3 %>">
												<input type="hidden" id="amount<%=key%>_4" value="<%=value4 %>">
											</td>
										</tr>
									<%}%>--%>

									<tr ng-repeat = "e in extractionItems track by e.extractionId" ng-if="e.id < 4">
										<td>
											<input type="checkbox" ng-disabled="{{isDutyNurse}}" checklist-model="treatmentPlan.extraction" checklist-value="e"  checklist-change="imChanged()">&emsp;{{e.name}}
											<div class="row" style="margin-left: 15px;">
												<div class="borders" style="text-align: right;">
													<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="checklistShow(e.name)" >
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num1.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num1.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getChecklistModel(e.name).num1" checklist-value="tooth" checklist-change="imChanged()"/></span>
													</span>
												</div>
												<div class="borders">
													<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="checklistShow(e.name)" >
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num2.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num2.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getChecklistModel(e.name).num2" checklist-value="tooth" checklist-change="imChanged()"/></span>
													</span>
												</div>
											</div>
											<div class="row" style="margin-left: 15px;">
												<div class="borders">
													<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="checklistShow(e.name)">
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num3.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num3.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getChecklistModel(e.name).num3" checklist-value="tooth" checklist-change="imChanged()"/></span>
													</span>
												</div>
												<div class="borders">
													<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="checklistShow(e.name)" >
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num4.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
														<span ng-click="toggleCheckBox($event)" ng-show = "getChecklistModel(e.name).num4.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getChecklistModel(e.name).num4" checklist-value="tooth" checklist-change="imChanged()"/></span>
													</span>
												</div>
											</div>
											<%--<table border="1" rules="all" frame="void" style="margin-left: 80px;width:100px;">
												<tr>
													<td style="margin: 2pt;"><input type="text" ng-model="e.num1" class="input-small" style="text-align: right" ></td>
													<td><input type="text" ng-model="e.num2" class="input-small"></td>
												</tr>
												<tr>
													<td style="margin: 2pt;"><input type="text" ng-model="e.num3" class="input-small"  style="text-align: right" ></td>
													<td><input type="text" ng-model="e.num4" class="input-small"></td>
												</tr>
											</table>--%>
										</td>
									</tr>

									<tr><td><br>Excision<br><br></td></tr>
									<%--<%for(int key : extractionNameByID.keySet()){
										if(isUncheckedHide==true && extractionDTO.patCurExtractionList.contains(key)==false){
											continue;
										}
										if(key<4){
											continue;
										}%>
										<tr>
											<td>
												<input type="checkbox" <%if(extractionDTO.patCurExtractionList.contains(key)){%>checked="checked"<%}%> name="extractionId" value="<%=key %>" onchange="updateTotalAmount(this, <%=extractionTakaByID.get(key)%>)" style="margin-left: 40px;">&emsp;<%=extractionNameByID.get(key) %>
											</td>
										</tr>
									<%}%>
--%>
									<tr ng-repeat = "e in extractionItems track by e.extractionId" ng-if="e.id > 4">
										<td>
											<input type="checkbox" ng-disabled="{{isDutyNurse}}"  style="margin-left: 40px;" checklist-value="e" checklist-model="treatmentPlan.extraction">&emsp;{{e.name}}
										</td>
									</tr>

								</table>
							</div>
							<div class="col-md-8 col-sm-6 col-xs-12">
								<div class="row" style="background-color: #f0f0f0;margin: 5px;padding: 15px;">
									<div class="col-md-6">Particulars</div>
									<div class="col-md-3">Payment Info</div>
									<div class="col-md-3">Status</div>
								</div>
								<div class="row" ng-repeat="item in treatmentPlan.extraction track by item.extractionId" style="padding: 20px;">
									<div class="col-md-6" ng-if="item.paymentRegNo!=''">Deposited {{calculatePaymentAmount($index,item)}} Taka for {{item.name}}</div>
									<div class="col-md-6" ng-if="item.paymentRegNo==''">Please deposit Taka {{calculatePaymentAmount($index,item)}} for {{item.name}}</div>
									<div class="col-md-3" ng-show="!isDutyNurse">Reg. No <input type="text" size="10" ng-model="item.paymentRegNo"/></div>
									<div class="col-md-3" ng-show="isDutyNurse">Reg. No : {{item.paymentRegNo}}</div>
									<div class="col-md-3" ng-show="!isDutyNurse"><input ng-model="item.done" type="checkbox">&nbsp;&nbsp;Done</div>
									<div class="col-md-3" ng-show="isDutyNurse"><input ng-model="item.instrumentProvided" type="checkbox">&nbsp;&nbsp;Instrument Provided</div>
								</div>
<%--								<div>Please deposit <input type="text" readonly="readonly" value="<%=extractionDTO.getTotalTk() %>" name="totalTk" id="totalTk">Taka<br></div>
								<div>Payment registration number <input type="text" name="paymentRegNumber" value="<%=extractionDTO.getPaymentRegNumber() %>"></div>   --%>
							</div>
						</div>
						<div class="row" ng-init="getTreatmentPlans()">
							<input class="btn btn-primary pull-right" type="button" value="Save" style="margin-right: 20px;margin-left: 20px;" ng-click="saveTreatmentPlan()"/>
							<input class="btn  pull-right" type="button" value="Print" onClick="window.print()"/>
							<input type="hidden" name="uniqIDExtraction" value="<%=extractionDTO.getUniqIDExtraction() %>">
							<input type="hidden" name="userId" value="<%=accountID %>">
						</div>
						</html:form>
					</div>

				</div>
     		</div>
   		</div>
  	</div>
</div>
</body>
</html>