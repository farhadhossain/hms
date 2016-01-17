<%@ include file="../includes/checkLogin.jsp"%>
<!doctype html>
<html ng-app="hms">
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
PatientService patientServ = new PatientService();
PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));
%>
<head>
	<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="../Assets/Styles/toastr.min.css">


	<script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
	<script type="text/javascript" src="../Assets/js/toastr.min.js"></script>
 	<script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-tpls-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/module.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/controllers/prescriptionController.js"></script>

	<script>

	$(document).ready(function(){
	    var i=3;
		var j=2;
		var k=1;
		var maxField = 100; //Input fields increment limitation
		var addButton = $('.repeat'); //Add button selector
		var wrapper = $('.field_wrapper'); //Input field wrappe
		var x = 1; //Initial field counter is 1
		$(wrapper).on('click', '.repeat', function(){ 		//Once add button is clicked
		    var fieldHTML ='<div class="field_wrapper_1"><div class="row"><div class="col-md-3 col-sm-8 col-xs-11"><input type="text" class="form-control" id="medicineInput" placeholder="Medicine" name="medicineInput[]"></div><div class="col-md-3 col-sm-8 col-xs-11"><select class="form-control input-sm" id="typeInput" name="typeInput[]" ><option value="0">Tab</option><option value="0">Cap</option><option value="0">Syrup</option><option value="0">Injection</option><option value="0">Insulin</option><option value="0">Inhaler</option><option value="0">Ointment</option></select></div><div class="col-md-3 col-sm-8 col-xs-11"><select class="form-control input-sm" id="select_timeInput" name="select_timeInput[]" ><option value="0">Select One</option></select></div><div class="col-md-3 col-sm-8 col-xs-11"><textarea class="form-control" rows="1" col="10" name="comments[]" Placeholder="comments.."></textarea></div></div><div class="row"><div class="col-md-5 col-sm-8 col-xs-11 form-inline"><label class="checkbox-inline"><input type="radio" name="optionsRadiosinline'+(k)+'['+(k)+']" id="optionsRadios-'+(i)+'" value="afterMeal-'+(j)+'"> After Meal</label><label class="checkbox-inline"><input type="radio" name="optionsRadiosinline'+(k)+'['+(k)+']" id="optionsRadios-'+(i+1)+'" value="beforeMeal-'+(j)+'"> Before Meal</label></div><div class="col-md-2 col-sm-4 col-xs-7" id="add_remove"><a href="javascript:void(0);" class="repeat" title="Add Medicine" id="add"><img class="img-responsive" src="../Assets/Images/add.png" style="display:inline;"></a><a href="javascript:void(0);" class="remove_button" title="remove Medicine" style="display:inline;"><img class="img-responsive" src="../Assets/Images/minus-sign.png" style="display:inline;"></a></div></div></div>';
			if(x < maxField){ //Check maximum number of input fields
				x++; //Increment field counter
				$(wrapper).append(fieldHTML); // Add field html
			i=i+2;
			k++;
			}
		});
		$(wrapper).on('click', '.remove_button', function(e){ //Once remove button is clicked
			e.preventDefault();
			 $(this).closest("div.field_wrapper_1").remove();
			//$(this).parent('div').remove(); //Remove field html
			x--; //Decrement field counter
		});
	});
	</script>
	<script>
	$(function(){
	$('.datepicker').datepicker();
	});
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
<body ng-controller = "PrescriptionController" ng-init="getPrescription('<%=accountID%>')">
<div class="container" style="padding-top: 40px;width:1300px">
  <div class="row">
  <div class="container-fluid">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<header class="banner" role="banner">
		
		<div class="row">
		   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			 <img class="img-responsive" src="../Assets/Images/banner.jpg">
		   </div>
		</div>
		
	</header>
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
				  <td><%= patientDTO.getDateOfRec() %></td>
			  </tr>
		   </tbody>
		</table>
	   </div>
	</div>
    <!-- Patient info panel END-->
	 <!-- Left Container-->
	 <div class="col-md-4 col-sm-6 col-xs-12" style="padding-left: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-comments"></i>&nbsp;&nbsp;Chief Complaint (C/C)
				</h3>
			</div>
		   	<div class="panel-body">
		     	<table  ng-init="getAllDisease()">
		     		<!--<tr ng-repeat="(key, value) in items"><td><input type="checkbox" value="{{key}}">&emsp;{{value}}</td></tr> -->
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Pain">&emsp;Pain</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Swelling">&emsp;Swelling</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Ulcer">&emsp;Ulcer</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Fistula">&emsp;Fistula</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Sinus">&emsp;Sinus</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Difficulty of eating">&emsp;Difficulty of eating</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Bleeding">&emsp;Bleeding</td></tr>

					<tr>
						<td>
							<input type="checkbox" checklist-model="prescription.chiefComplain" checklist-value="getValue('LooseTeeth', prescription.chiefComplain)" checklist-comparator="compareFn" checklist-change="imChanged()">&emsp;Loose teeth
							<div class="row" style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="show('LooseTeeth', prescription.chiefComplain)" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).lt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).lt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('LooseTeeth', prescription.chiefComplain).lt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('LooseTeeth', prescription.chiefComplain)" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).rt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).rt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('LooseTeeth', prescription.chiefComplain).rt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
							<div class="row" style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="show('LooseTeeth', prescription.chiefComplain)" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).lb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).lb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('LooseTeeth', prescription.chiefComplain).lb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('LooseTeeth', prescription.chiefComplain)" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).rb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('LooseTeeth', prescription.chiefComplain).rb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('LooseTeeth', prescription.chiefComplain).rb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Clicking Sunday">&emsp;Clicking Sunday</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Restricted mouth opening">&emsp;Restricted mouth opening</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Dryness of mouth">&emsp;Dryness of mouth</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Excessive salivation">&emsp;Excessive salivation</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" value="Foul smell">&emsp;Foul smell</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.chiefComplain" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.chiefComplain)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.chiefComplain).other.text"></td></tr>
		     	</table>
		    </div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-hospital-o"></i>&nbsp;&nbsp;O/E
				</h3>
			</div>
		   	<div class="panel-body">
				<table>
		     		<tr>
						<td>
							<input type="checkbox" checklist-model="prescription.onObservation" checklist-value="getValue('Caries')" checklist-comparator="compareFn" checklist-change="imChanged()">&emsp;Caries
							<div class="row" style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="show('Caries')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').lt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').lt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Caries').lt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('Caries')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').rt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').rt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Caries').rt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
							<div class="row" style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="show('Caries')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').lb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').lb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Caries').lb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('Caries')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').rb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Caries').rb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Caries').rb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="checkbox" checklist-model="prescription.onObservation" checklist-value="getValue('loose_teeth')" checklist-comparator="compareFn" checklist-change="imChanged()">&emsp;loose teeth
							<div class="row" style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]"  ng-show="show('loose_teeth')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').lt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').lt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('loose_teeth').lt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('loose_teeth')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').rt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').rt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('loose_teeth').rt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
							<div class="row"  style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="show('loose_teeth')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').lb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').lb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('loose_teeth').lb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]" ng-show="show('loose_teeth')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').rb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('loose_teeth').rb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('loose_teeth').rb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" checklist-model="prescription.onObservation" checklist-value="getValue('BDR')" checklist-comparator="compareFn" checklist-change="imChanged()">&emsp;BDR
							<div class="row"  style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]"  ng-show="show('BDR')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').lt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').lt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('BDR').lt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('BDR')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').rt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').rt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('BDR').rt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
							<div class="row"  style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]"  ng-show="show('BDR')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').lb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').lb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('BDR').lb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('BDR')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').rb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('BDR').rb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('BDR').rb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" checklist-model="prescription.onObservation" checklist-value="getValue('Impacted')" checklist-comparator="compareFn" checklist-change="imChanged()">&emsp;Impacted
							<div class="row"  style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]"  ng-show="show('Impacted')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').lt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').lt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Impacted').lt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('Impacted')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').rt.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').rt.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Impacted').rt" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
							<div class="row"  style="margin-left: 15px;">
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [8,7,6,5,4,3,2,1]" ng-show="show('Impacted')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').lb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').lb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Impacted').lb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
								<div class="col-lg-6 borders">
									<span ng-repeat="tooth in [1,2,3,4,5,6,7,8]"  ng-show="show('Impacted')" >
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').rb.indexOf(tooth)==-1" ><a ng-href='#here'>{{tooth}}</a></span>
										<span ng-click="toggleCheckBox($event)" ng-show = "getModel('Impacted').rb.indexOf(tooth)!=-1" ><input type="checkbox"  checklist-model="getModel('Impacted').rb" checklist-value="tooth" checklist-change="imChanged()"/></span>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Supernumerary">&emsp;Supernumerary</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Supplemental">&emsp;Supplemental</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Fracture">&emsp;Fracture</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Cleft lip/ palate">&emsp;Cleft lip/ palate</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Lacerated wound">&emsp;Lacerated wound</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Facial space infection">&emsp;Facial space infection</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" value="Ankylosis of tmj">&emsp;Ankylosis of tmj</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.onObservation" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.onObservation)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.onObservation).other.text"></td></tr>
		     	</table>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-hospital-o"></i>&nbsp;&nbsp;Investigation
				</h3>
			</div>
		   	<div class="panel-body">
				<table>
		     		<tr><td>
		     			<input type="checkbox" checklist-model="prescription.investigation" value="x-Ray">&emsp;x-Ray<br>
		     				<input type="checkbox" checklist-model="prescription.investigation" value="OPG" style="margin-left: 40px;">&emsp;OPG<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Periapical view" style="margin-left: 40px;">&emsp;Periapical view<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Occlusal view of mandible" style="margin-left: 40px;">&emsp;Occlusal view of mandible<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Occlusal view of maxilla" style="margin-left: 40px;">&emsp;Occlusal view of maxilla<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Lateral view of skull" style="margin-left: 40px;">&emsp;Lateral view of skull<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="PNS view" style="margin-left: 40px;">&emsp;PNS view<br>
		     		</td></tr>
		     		<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="CT Scan of maxillofacial region">&emsp;CT Scan of maxillofacial region</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="MRI of maxillofacial region">&emsp;MRI of maxillofacial region</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="Ultrasonogram">&emsp;Ultrasonogram</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="Sinus">&emsp;Sinus</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="FNAC">&emsp;FNAC</td></tr>
					<tr><td>
						<input type="checkbox" checklist-model="prescription.investigation" value="Biopsy">&emsp;Biopsy<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Fineniddle" style="margin-left: 40px;">&emsp;Fineniddle<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Core" style="margin-left: 40px;">&emsp;Core<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Brush" style="margin-left: 40px;">&emsp;Brush<br>
					</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="Bone scan">&emsp;Bone scan</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="Sinogram">&emsp;Sinogram</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.investigation)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.investigation).other.text"></td></tr>
		     	</table>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-stethoscope"></i>&nbsp;&nbsp;Diagonsis
				</h3>
			</div>
		   <div class="panel-body">
			   <table>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Lichen planus">&emsp;Lichen planus</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Leukoplakia">&emsp;Leukoplakia</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Candidaisis">&emsp;Candidaisis</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Herpes">&emsp;Herpes</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Trigeminal Neuralgia">&emsp;Trigeminal Neuralgia</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Apthous Ulcer">&emsp;Apthous Ulcer</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Burning Mouth Syndrome">&emsp;Burning Mouth Syndrome</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Ptyalsim">&emsp;Ptyalsim</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Xerostomia">&emsp;Xerostomia</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Bell' Palsy">&emsp;Bell' Palsy</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Traumatic Ulcer">&emsp;Traumatic Ulcer</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Geographic Tongue">&emsp;Geographic Tongue</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" value="Hyper pigmentation">&emsp;Hyper pigmentation</td></tr>
				   <tr><td><input type="checkbox" checklist-model="prescription.diagonosis" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.diagonosis)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.diagonosis).other.text"></td></tr>
			   </table>
			</div>
		</div>
	</div>
	 <!-- Left Container End-->
	 <!-- Right Container Start-->
	<div class="col-md-8 col-sm-6 col-xs-12" style="padding-left: 0px; padding-right: 0px;">

		   <div class="panel panel-default" ng-repeat = "medicine in medicines">
			   <div class="panel-body">
				   <div class="row">
					   <div class="col-sm-2">
						   <select class="form-control" style="width: 120px;" data-ng-model="medicine.medicineType">
							   <option value="">Select Formulation</option>
							   <option value="Tablet">Tablet</option>
							   <option value="Capsule">Capsule</option>
							   <option value="Injection">Injection</option>
							   <option value="Syrup">Syrup</option>
							   <option value="Suspension">Suspension</option>
							   <option value="Ointment/Gel">Ointment/Gel</option>
							   <option value="Suppository">Suppository</option>
							   <option value="Drop">Drop</option>
							   <option value="Inhaler">Inhaler</option>
							   <option value="Mouthwash">Mouthwash</option>
							   <option value="Others">Others</option>
						   </select>
					   </div>
					   <div class="col-sm-2">
						   <input type="text"
								  data-ng-model="medicine.medicineName"
								  data-typeahead-template-url="bind-node.html"
								  data-typeahead="medItem as medItem.medicineName for medItem in searchMedicine($viewValue, $index)"
								  data-typeahead-on-select="addMedicine($item, $model, $label, $index)"
								  class="form-control custom-search" placeholder="Medicine... " style="min-width: 120px;"/>
					   </div>
					   <div class="col-sm-2">
						   <select class="form-control" style="width: 120px;" data-ng-model="medicine.frequency">
							   <option value="">Select Frequency</option>
							   <option value="1+1+1">1+1+1</option>
							   <option value="1+0+1">1+0+1</option>
							   <option value="0+0+1">0+0+1</option>
							   <option value="1+0+0">1+0+0</option>
							   <option value="1+1+1+1">1+1+1+1</option>
							   <option value="1+1+1+1+1+1">1+1+1+1+1+1</option>
						   </select>
					   </div>
					   <div class="col-sm-2">
						   <select class="form-control" style="width: 120px;" data-ng-model="medicine.dose">
							   <option value="">Select Dose</option>
							   <option value="500">500</option>
							   <option value="250">250</option>
							   <option value="400">400</option>
							   <option value="100">100</option>
							   <option value="10">10</option>
							   <option value="20">20</option>
							   <option value="3">3</option>
							   <option value="8">8</option>
							   <option value="5">5</option>
						   </select>
					   </div>
					   <div class="col-sm-2">
						   <textarea  class="form-control" placeholder="Comments" cols="20" rows="1" data-ng-model="medicine.comment"></textarea>
					   </div>
					   <div class="col-sm-1">
						   <select class="form-control" style="width: 80px;" data-ng-model="medicine.afterMeal">
							   <option value="After Meal">After Meal</option>
							   <option value="Before Meal">Before Meal</option>
						   </select>
					   </div>
					   <div class="col-sm-1">
						   <a href="#"><span class="fa fa-plus fa-2x" style="margin-left: 20px;" ng-click="addMedicineRow()"></span></a>
					   </div>
				   </div>
			   </div>
		   </div>

			<script type="text/ng-template" id="bind-node.html">
				<div style="padding: 10px;width:220px;">
					<a href="#" class="row" style="width: 200px;margin-left: 0px;margin-right: 0px;padding: 0px;z-index: 1000;">
						<div class="col-sm-12">{{match.model.medicineName}}</div>
					</a>
				</div>
			</script>
	 	   <div class="panel panel-default">
	 	   		<%--<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-medkit"></i>&nbsp;&nbsp;Medicine[When In Hospital]
					</h3>
				</div>
			   	<div class="panel-body">
					<textarea class="form-control" rows="4" col="20"></textarea>
				</div>
				
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-medkit"></i>&nbsp;&nbsp;Medicine[When Outside Hospital]
					</h3>
				</div>
			   	<div class="panel-body">
					<textarea class="form-control" rows="4" col="20"></textarea>
				</div>--%>
	 	   
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-medkit"></i>&nbsp;&nbsp;Advice
					</h3>
				</div>
			   	<div class="panel-body">
					<table>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Take the medicine regularly.">&emsp;Take the medicine regularly.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Take the firm bite for 1/2 - 1 hour.">&emsp;Take the firm bite for 1/2 - 1 hour.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Don't take hot, hard and spicy food for 24 hour and avoid gargling.">&emsp;Don't take hot, hard and spicy food for 24 hour and avoid gargling.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Take the soft and normal diet.">&emsp;Take the soft and normal diet.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="If stitch is given than visit after 7 days for stitch removal.">&emsp;If stitch is given than visit after 7 days for stitch removal.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Minor swelling and bleeding may be observed in first day of surgery no need to worry. In case of severity immediately contact the OPD In department.">&emsp;Minor swelling and bleeding may be observed in first day of surgery no need to worry. In case of
							severity immediately contact the OPD In department.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="After 24 hour gargle with warm saline or mouthwash as adviced.">&emsp;After 24 hour gargle with warm saline or mouthwash as adviced.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Avoid  smoking, spicy food,  tea and coffee.">&emsp;Avoid  smoking, spicy food,  tea and coffee.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Liquid and semisolid diet.">&emsp;Liquid and semisolid diet.</td></tr>
						<tr><td><input type="checkbox" checklist-model="prescription.advice" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.advice)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.advice).other.text"></td></tr>
					</table>
				</div>
				
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-medkit"></i>&nbsp;&nbsp;Name of Doctor
					</h3>
				</div>
			   	<div class="panel-body">

				</div>
				
				
				<div class="panel-footer">
					<form class="form-inline">
						<div class="col-md-1 col-sm-6 col-xs-12">
							<div class="form-group" style="margin-top:3px;">
							<label>Next Visit</label>
							</div>
						</div>
						<div class="col-md-4 col-sm-6 col-xs-12">
						  <div class="form-group">
							<label for="dateInput">Date</label>
	                         <input type="text" class="datepicker form-control " id="dateInput" name="dateInput"/>       
						  </div>
						</div>
					   <div class="col-md-3 col-sm-6 col-xs-12">
						  <div class="form-group">
							<input type="text" class="form-control" id="timeInput" placeholder="Time after" name="timeInput">
						   </div>
						</div>
					   <div class="col-md-2 col-sm-4 col-xs-12">
						  <div class="form-group">
							<select class="form-control input-sm" id="dayInput" name="dayInput" >
							<option value="0">Day</option>
							</select>
						   </div>
						</div>
						<script>
							function loadPrintPage() {
								$("<iframe>")
										.hide()
										.attr("src", "/Patient/PrescriptionPrint.jsp?accountID=<%=accountID%>&printNow")
										.appendTo("body");

							}
						</script>
					  	<input type="button" class="btn btn-primary" onClick="loadPrintPage()" value="Print">
						<input type="button" class="btn btn-primary" ng-click="savePrescription()" value="Save">
					</form>
				</div>
			</div>
		</div>
	 </div>
	 <!-- Right Container  End-->
   </div>
   </div>
  </div>
</div>
</body>
</html>