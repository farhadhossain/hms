<!DOCTYPE html>
<html lang="en">

<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page import="pastSergHistory.PastSurgicalHistoryService"%>
<%@ page import="pastSergHistory.PastSurgicalHistoryDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.Date" %>
<%@ page import="patient.PatientSurgeryService" %>
<%@ page import="patient.PatientSurgeryDTO" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
String surgicalID= request.getParameter("surgicalID");
PatientDTO patientDTO=new PatientService().getPatientDTO(Integer.parseInt(accountID));
PatientSurgeryDTO surgeryDTO= new PatientSurgeryService().getSurgeryByPatientID(Integer.parseInt(accountID));
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
	<script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-tpls-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/module.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/controllers/treatmentPlanController.js"></script>
	<style>
		.control-label { padding-top: 5px; }
		.heading { font-size: 16px; text-transform: uppercase; }
	</style>
	<script type="text/javascript">
		$(document).ready(function(e) {
			$('.datepicker').datepicker({
				dateFormat: 'yy-mm-dd',
				year: true,
				month: true
			});
		});
	</script>
	<style>
		.col-md-12,.col-md-6,.col-md-4,.col-md-3,.col-md-2{
			padding-left: 0px;
			padding-right: 0px;
		}
	</style>
</head>

<body ng-app="hms">
	<html:form action="/MakeDischarge">
		<div class="container"  ng-controller = "TreatmentPlanController">

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
						Patient Details
					</h3>
				</div>
				<div class="panel-body_1 table-responsive ">
					<table class="table-bordered table">
						<thead>
						<tr class="success">
							<th>Patient ID</th>
							<th>Bed Number</th>
							<th>Name</th>
							<th>Age</th>
							<th>Sex</th>
							<th>Address</th>
							<th>Tel:</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td><%=patientDTO.getTicketNumber() %></td>
							<td><%=patientDTO.getBedNumber() %></td>
							<td><%=patientDTO.getName() %></td>
							<td><%=patientDTO.getAge() %></td>
							<td><%=patientDTO.getSex() %></td>
							<td><%=patientDTO.getPresentAdd() %></td>
							<td><%=patientDTO.getTelephoneNum() %></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- Patient info panel END-->

			<table style="width: 100%;" ng-init="getPrescription('<%=accountID%>')">
				<tr>
					<td width="50%"><label class="control-label">Date of Admission: </label>&nbsp;&nbsp;  <%=patientDTO.date_of_adm %></td>
					<td width="50%" style="text-align: right;"><label class="control-label">Date of Discharge:&nbsp;&nbsp; </label><%=new Date() %></td>
				</tr>
				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Diagnosis:</label></td></tr>
				<tr><td colspan="2">
					<div class="row" ng-repeat="cc in prescription.onObservation">
						<div ng-if="isString(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div>
						<div ng-if="isObject(cc)" ng-if="isObject(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}.
							<span ng-if="!cc.other" ng-repeat="(key, value) in cc">
								{{key}}
								<table style="width: 20%;">
									<tr><td width="50%" style="border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lt.join(', ')}}</td><td width="50%" style="border-bottom: 1px solid #CCC;padding: 5px;">&nbsp;{{value.rt.join(', ')}}</td></tr>
									<tr><td width="50%" style="border-right: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lb.join(', ')}}</td><td width="50%" style="padding: 5px;">&nbsp;{{value.rb.join(', ')}}</td></tr>
								</table>
							</span>
							<span ng-if="cc.other">{{cc.other.text}}</span>
						</div>
					</div>
				</td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Hospital Course:</label></td></tr>
				<tr><td colspan="2">
					<div class="col-sm-9 col-lg-offset-1">
						<table style="width: 100%;">
							<tr ng-repeat="med in prescription.medicines">
								<td class="col-sm-6">{{$index+1}}. {{med.medicineName}} ({{med.medicineType}})</td>
								<td class="col-sm-1">{{med.dose}}</td>
								<td class="col-sm-2">{{med.frequency}}</td>
								<td class="col-sm-3">{{med.afterMeal}}</td>
							</tr>
						</table>
					</div>
				</td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Investigations:</label></td></tr>
				<tr><td colspan="2">
					<div class="row" ng-repeat="cc in prescription.investigation"><div class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div></div>
				</td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Operation/Confinement/Procedure Note:</label></td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Time and Date of Operation/Procedure:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=surgeryDTO.getDateOfOp() %></td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Name of the Operation/Procedure:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=surgeryDTO.getNameOfOp() %></td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Indication Of Operation/Procedure:</label></td></tr>
				<tr><td colspan="2">
				</td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Findings And Procedure:</label></td></tr>
				<tr><td colspan="2">
				</td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Name of surgeon:</label></td></tr>
				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Name of assistant surgeon:</label></td></tr>
				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Name of anesthesiologist:</label></td></tr>


				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">TREATMENT ON DISCHARGE:</label></td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Advice on Discharge with Recommendation (if any):</label></td></tr>
				<tr><td colspan="2">
					<div class="col-sm-9 col-lg-offset-1">
					<table style="width: 100%;">
						<tr ng-repeat="cc in prescription.advice">
							<td ng-if="isString(cc)" class="col-sm-9">{{$index+1}}. {{cc}}</td>
						</tr>
					</table>
					</div>
				</td></tr>

				<tr><td colspan="2" style="padding-top: 20px;"><label class="control-label">Follow up Advice:</label></td></tr>
				<tr><td colspan="2">
					<div class="col-sm-9 col-lg-offset-1">
						<textarea class="form-control" name="follow_up_advice"></textarea>
					</div>
				</td></tr>

				<tr>
					<td style="padding-top: 20px;" colspan="2"><label class="control-label">Next Visit Date:</label>&nbsp;&nbsp;&nbsp;<input type="text" /></td>
				</tr>

			</table>

			<div class="col-md-12" >
				<div class="form-body">

						<div class="form-group col-md-12" style="border-bottom: 2px solid;">
							&nbsp;
						</div>

						<div class="form-group col-md-12">
							<label class="col-md-4 control-label">Counter Sign By: </label>
							<div class="col-md-8">

							</div>
						</div>

						<div class="form-group col-md-12">
							<label class="col-md-3 control-label">Unit in Charge/Consultant: </label>
							<div class="col-md-6">

							</div>
							<label class="col-md-3 control-label">Resident/Medical Officer: </label>
						</div>

						<div class="form-group col-md-12">
							<label class="col-md-2 control-label">Signature: </label>
							<div class="col-md-7">

							</div>
							<label class="col-md-3 control-label">Signature: </label>
						</div>

						<div class="form-group col-md-12">
							<label class="col-md-2 control-label">Seal/Name: </label>
							<div class="col-md-7">

							</div>
							<label class="col-md-3 control-label">Seal/Name: </label>
						</div>

						<div class="row">
							<input type="hidden" name="surgicalStatus" value="4"/>
							<input type="hidden" name="userID" id="userID" value="<%=accountID%>">
							<input class="btn btn-primary pull-right" type="submit" value="Save" style="margin-right: 20px;margin-left: 20px;"/>
							<input class="btn  pull-right" type="button" value="Print" onClick="window.print()"/>
						</div>


				</div>
			</div>
		</div>
	</html:form>
</body>
</html>