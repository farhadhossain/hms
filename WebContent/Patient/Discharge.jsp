<!DOCTYPE html>
<html lang="en">

<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page import="pastSergHistory.PastSurgicalHistoryService"%>
<%@ page import="pastSergHistory.PastSurgicalHistoryDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
String surgicalID= request.getParameter("surgicalID");
PatientDTO patientDTO=new PatientService().getPatientDTO(Integer.parseInt(accountID));
PastSurgicalHistoryDTO surgicalHistoryDTO= new PastSurgicalHistoryService().getPastSurgicalHistoryDTOBySurgicalID(Integer.parseInt(surgicalID));;
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
	<script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
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
</head>

<body>
	<div class="wrapper">
			<div class="container-fluid">
				<div class="row">
					<h4 class="form_heading" align="center"><span>Discharge Certificate</span></h4>
					<br><br><br>
					<div class="col-md-12">
						<div class="form-body">
							<form action=""  method="post" enctype="multipart/form-data">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-label">Patient ID: </label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="patient_id" value="<%=patientDTO.getAccId()%>" />
										<span class="help-block">&nbsp;</span>
									</div>
									<label class="col-md-2 control-label">Word/Cabin No: </label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="word_cabin_no" value="<%=patientDTO.getWordNumber() %>"  />
										<span class="help-block">&nbsp;</span>
									</div>
									<label class="col-md-2 control-label">Bed No: </label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="bed_no" value="<%=patientDTO.getBedNumber() %>"  /> 
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-2 control-label">Patient Name: </label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="patient_name" value="<%=patientDTO.getName() %>"  />
										<span class="help-block">&nbsp;</span>
									</div>
									<label class="col-md-1 control-label">Age: </label>
									<div class="col-md-1">
										<input type="text" class="form-control" name="patient_age" value="<%=patientDTO.getAge() %>"  />
										<span class="help-block">&nbsp;</span>
									</div>
									<label class="col-md-1 control-label">Sex: </label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="patient_sex" value="<%=patientDTO.getSex() %>"  /> 
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-2 control-label">Registration No: </label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="registration_no" value="<%=patientDTO.getRegNumber() %>"  />
										<span class="help-block">&nbsp;</span>
									</div>
									<label class="col-md-1 control-label">Address: </label>
									<div class="col-md-4">
										<textarea class="form-control" name="patient_address"><%=patientDTO.getPresentAdd() %></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
									<label class="col-md-1 control-label">Tel: </label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="patient_tel" value="<%=patientDTO.getTelephoneNum() %>" /> 
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-3 control-label">Date of Admission: </label>
									<div class="col-md-2">
										<input type="text" class="form-control datepicker" name="date_admission" value="<%=patientDTO.date_of_adm %>"  />
										<span class="help-block">&nbsp;</span>
									</div>
									<div class="col-md-1">&nbsp;</div>
									<label class="col-md-3 control-label">Date of Discharge: </label>
									<div class="col-md-2">
										<input type="text" class="form-control datepicker" name="date_discharge" value="<%=surgicalHistoryDTO.updatePerform %>"  />
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-2 control-label">Diagnosis: </label>
									<div class="col-md-10">
										<textarea class="form-control" name="patient_diagnosis"></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-2 control-label">Hospital Course: </label>
									<div class="col-md-10">
										<textarea class="form-control" name="hospital_course"></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-2 control-label">Investigations: </label>
									<div class="col-md-10">
										<textarea class="form-control" name="investigations"></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-8 control-label heading">Operation/Confinement/Procedure Note: </label>
									
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-1 control-label">&nbsp;</label>
									<label class="col-md-4 control-label">Time and Date of Operation/Procedure: </label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="operation_date_time" value="<%=surgicalHistoryDTO.getDateOfOp() %>" />
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-1 control-label">&nbsp;</label>
									<label class="col-md-4 control-label">Name of the Operation/Procedure: </label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="operation_name" value="<%=surgicalHistoryDTO.getNameOfOp() %>" />
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-1 control-label">&nbsp;</label>
									<label class="col-md-4 control-label">Indication Of Operation/Procedure: </label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="operation_indication" value="<%=surgicalHistoryDTO.getAccAndInjuries() %>" />
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label">Findings And Procedure: </label>
									<div class="col-md-8">
										<textarea class="form-control" name="findings_procedure"><%=surgicalHistoryDTO.getPostOperativeCompli() %></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label heading">Treatment on Discharge: </label>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label">Advice on Discharge with Recommendation (if any): </label>
									<div class="col-md-8">
										<textarea class="form-control" name="advice_on_discharge"></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label">Follow up Advice: </label>
									<div class="col-md-8">
										<textarea class="form-control" name="follow_up_advice"></textarea>
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label">Next Visit Date: </label>
									<div class="col-md-2">
										<input type="text" class="form-control datepicker" name="date_next_visit" value="" />
										<span class="help-block">&nbsp;</span>
									</div>
								</div>
								
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
								
							</form>
						</div>
					</div>
				</div>
			</div>
	</div>
</body>
</html>