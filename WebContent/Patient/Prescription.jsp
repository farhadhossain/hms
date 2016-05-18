<%@ include file="../includes/checkLogin.jsp"%>
<!doctype html>
<html ng-app="hms">
<%@page import="patient.PatientDTO"%>
<%@page import="patient.PatientService"%>
<%@ page import="utility.MyConfig" %>
<%@ page import="prescription.VisitDTO" %>
<%@ page import="prescription.VisitDAO" %>
<%@ page import="utility.StringUtil" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
String visitID = request.getParameter("visitID")==null?"0":request.getParameter("visitID");
PatientService patientServ = new PatientService();
PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));

int currentVisitId = new VisitDAO().getCurrentVisitId(Integer.parseInt(accountID));
VisitDTO visitDTO = new VisitDAO().getVisitById(Integer.parseInt(visitID) == 0 ? currentVisitId : Integer.parseInt(visitID));
StringUtil.removeNullFromObject(visitDTO);
StringUtil.removeNullFromObject(patientDTO);
	/*MyConfig.userID = loginDTO.getUserID();
	MyConfig.roleID = loginDTO.getRoleID();*/
	/*System.out.println("*********************** userId = "+loginDTO.getUserID()+"*************************");*/
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
<body ng-controller = "PrescriptionController" ng-init="getVisits(<%=accountID%>);getPrescription(<%=accountID%>,<%=visitID%>);">
<div class="container" style="padding-top: 40px;width:1300px">
  <div class="row">
  <div class="container-fluid">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<header class="banner" role="banner">
		
		<div class="row">
		   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			 <img width="100%" src="../Assets/Images/banner.jpg">
		   </div>
		</div>
		
	</header>
    <!-- Patient info panel -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title pull-left">
				Patient Details
		    </h3>
			<ul role="presentation" class="dropdown pull-right" ng-show="<%=visitID.equals("0")%>">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
					Previous Visits <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li ng-repeat="v in visits  | limitTo:visits.length-1 "><a target="_blank" href="/Patient/Prescription.jsp?accountID=<%=accountID%>&visitID={{v.id}}">{{$index+1}}) on {{v.visitDate | date:'dd MMM yyyy'}}</a></li>
				</ul>
			</ul>
			<div class="clearfix"></div>
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
			     <th>Visit Date</th>
			  </tr>
			</thead>
		   <tbody>
			  <tr>
			  	 <td><%=visitDTO.getTicketNumber() %></td>
				  <td><%=patientDTO.getAccId() %></td>
				 <td><%=patientDTO.getName() %></td>
				 <td><%=patientDTO.getAge() %></td>
				 <td><%=patientDTO.getSex() %></td>
				  <td><%= patientDTO.getDateOfRec() %></td>
				  <td>{{currentVisit.visitDate | date:'dd MMM yyyy'}}</td>
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
					 <i class="fa fa-comments"></i>&nbsp;&nbsp;(H/O)
				 </h3>
			 </div>
			 <div class="panel-body">
				 <table>
					 <!--<tr ng-repeat="(key, value) in items"><td><input type="checkbox" value="{{key}}">&emsp;{{value}}</td></tr> -->
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Diabetes">&emsp;Diabetes</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Heart disease">&emsp;Heart disease</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Blood disorder">&emsp;Blood disorder</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Hypertension">&emsp;Hypertension</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Anticoagulant therapy">&emsp;Anticoagulant therapy</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Asthma">&emsp;Asthma</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Hyper thyroidism">&emsp;Hyper thyroidism</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Hypo thyroidism">&emsp;Hypo thyroidism</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Trauma">&emsp;Trauma</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Stroke">&emsp;Stroke</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Chronic kidney disease">&emsp;Chronic kidney disease</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Liver disease">&emsp;Liver disease</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Autoimmune disease">&emsp;Autoimmune disease</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Rheumatic fever">&emsp;Rheumatic fever</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Hepatitis-  B">&emsp;Hepatitis-  B</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Hepatitis-  C">&emsp;Hepatitis-  C</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" value="Steroid therapy">&emsp;Steroid therapy</td></tr>
					 <tr><td><input type="checkbox" checklist-model="prescription.ho" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.ho)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.ho).other.text"></td></tr>

				 </table>
			 </div>
		 </div>
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
		     			<input type="checkbox" disabled value="x-Ray">&emsp;x-Ray<br>
		     				<input type="checkbox" checklist-model="prescription.investigation" value="X-Ray - OPG" style="margin-left: 40px;">&emsp;OPG<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="X-Ray - Periapical view " style="margin-left: 40px;">&emsp;Periapical view<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="X-Ray - Occlusal view of mandible" style="margin-left: 40px;">&emsp;Occlusal view of mandible<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="X-Ray - Occlusal view of maxilla" style="margin-left: 40px;">&emsp;Occlusal view of maxilla<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="X-Ray - Lateral view of skull" style="margin-left: 40px;">&emsp;Lateral view of skull<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="X-Ray - PNS view" style="margin-left: 40px;">&emsp;PNS view<br>
		     		</td></tr>
		     		<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="CT Scan of maxillofacial region">&emsp;CT Scan of maxillofacial region</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="MRI of maxillofacial region">&emsp;MRI of maxillofacial region</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="Ultrasonogram">&emsp;Ultrasonogram</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="Sinus">&emsp;Sinus</td></tr>
					<tr><td><input type="checkbox" checklist-model="prescription.investigation" value="FNAC">&emsp;FNAC</td></tr>
					<tr><td>
						<input type="checkbox" disabled value="Biopsy">&emsp;Biopsy<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Biopsy - Fineniddle" style="margin-left: 40px;">&emsp;Fineniddle<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Biopsy - Core" style="margin-left: 40px;">&emsp;Core<br>
							<input type="checkbox" checklist-model="prescription.investigation" value="Biopsy - Brush" style="margin-left: 40px;">&emsp;Brush<br>
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
					<i class="fa fa-stethoscope"></i>&nbsp;&nbsp;Diagnsis
				</h3>
			</div>
		   <div class="panel-body">
			   <table>
				   <tr ng-repeat="cc in prescription.onObservation"><td colspan="2">
					   <div ng-if="isString(cc)"><input type="checkbox" disabled/>&emsp;{{cc}}</div>
					   <div ng-if="isObject(cc)" ng-if="isObject(cc)"><input type="checkbox" disabled/>&emsp;
						<span ng-if="!cc.other" ng-repeat="(key, value) in cc">
							{{key}}
							<table style="width: 100%;">
								<tr><td width="50%" style="border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lt.join(', ')}}</td><td width="50%" style="border-bottom: 1px solid #CCC;padding: 5px;">&nbsp;{{value.rt.join(', ')}}</td></tr>
								<tr><td width="50%" style="border-right: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lb.join(', ')}}</td><td width="50%" style="padding: 5px;">&nbsp;{{value.rb.join(', ')}}</td></tr>
							</table>
						</span>
					    <span ng-if="cc.other">{{cc.other.text}}</span>
					   </div>
				   </td></tr>
				   <tr><td style="padding-top: 10px;">Disease&emsp;</td><td>
					   <select class="form-control" style="width: 200px;" data-ng-model="prescription.diagonosis[0].disease">
						   <option value="">Select Disease</option>
						   <option value="Aphthous ulcer">Aphthous ulcer</option>
						   <option value="Angular cheilitis">Angular cheilitis</option>
						   <option value="Angular stomatitis">Angular stomatitis</option>
						   <option value="Amalgam tattoo">Amalgam tattoo</option>
						   <option value="Acute pseudomembranous candidosis">Acute pseudomembranous candidosis</option>
						   <option value="Atrophic form Lichen planus">Atrophic form Lichen planus</option>
						   <option value="Angioneurotic edema">Angioneurotic edema</option>
						   <option value="Anodontia">Anodontia</option>
						   <option value="Amelogenesi imperfect">Amelogenesi imperfect</option>
						   <option value="Angina">Angina</option>
						   <option value="Alteration of Occlusion">Alteration of Occlusion</option>
						   <option value="Acromegaly">Acromegaly</option>
						   <option value="Addison's disease">Addison's disease</option>
						   <option value="Alveolar cleft">Alveolar cleft</option>
						   <option value="Apert's syndrome">Apert's syndrome</option>
						   <option value="Adenomatoid odontogenic tumour">Adenomatoid odontogenic tumour</option>
						   <option value="Asymmetry">Asymmetry</option>
						   <option value="Atrial fibrillation">Atrial fibrillation</option>
						   <option value="Aortic stenosis">Aortic stenosis</option>
						   <option value="Ankle swelling">Ankle swelling</option>
						   <option value="Asthma">Asthma</option>
						   <option value="Acromegaly">Acromegaly</option>
						   <option value="Apex syndrome">Apex syndrome</option>
						   <option value="Anterior open bite">Anterior open bite</option>
						   <option value="Apical abscess">Apical abscess</option>
						   <option value="AIDS">AIDS</option>
						   <option value="Alveolar oseitis">Alveolar oseitis</option>
						   <option value="Abscess">Abscess</option>
						   <option value="ActinomycoticOsteomyeliti">ActinomycoticOsteomyeliti</option>
						   <option value="Acute dislocation">Acute dislocation</option>
						   <option value="Actinomycosi">Actinomycosi</option>
						   <option value="Aspergillosis">Aspergillosis</option>
						   <option value="Acute sinusitis">Acute sinusitis</option>
						   <option value="Acinic cell carcinoma">Acinic cell carcinoma</option>
						   <option value="Adenocarcinoma">Adenocarcinoma</option>
						   <option value="Adenoid cystic carcinoma">Adenoid cystic carcinoma</option>
						   <option value="Acini cell carcinoma">Acini cell carcinoma</option>
						   <option value="Anterior disk displacement with reduction">Anterior disk displacement with reduction</option>
						   <option value="Anterior disk displacement without reduction">Anterior disk displacement without reduction</option>
						   <option value="Arthritis">Arthritis</option>
						   <option value="Angle fracture">Angle fracture</option>
						   <option value="Ankylosis">Ankylosis</option>
						   <option value="Ameloblastic fibroma">Ameloblastic fibroma</option>
						   <option value="Aneurismal bone cyst">Aneurismal bone cyst</option>
						   <option value="Ameloblastoma">Ameloblastoma</option>
						   <option value="Bilateral complete cleft of primary palate">Bilateral complete cleft of primary palate</option>
						   <option value="Bilateral complete cleft of primary and secondary palate">Bilateral complete cleft of primary and secondary palate</option>
						   <option value="Basal cell carcinoma">Basal cell carcinoma</option>
						   <option value="Bony ankykosis">Bony ankykosis</option>
						   <option value="BenignTumour">BenignTumour</option>
						   <option value="Basal cell adenoma">Basal cell adenoma</option>
						   <option value="Brain abscess">Brain abscess</option>
						   <option value="Blindness">Blindness</option>
						   <option value="Blowout fracture">Blowout fracture</option>
						   <option value="Blow in fracture">Blow in fracture</option>
						   <option value="Bifid uvula">Bifid uvula</option>
						   <option value="Boil">Boil</option>
						   <option value="Buccal space infection">Buccal space infection</option>
						   <option value="Bell's palsy">Bell's palsy</option>
						   <option value="Black hairy tongue">Black hairy tongue</option>
						   <option value="Burning mouth syndrome">Burning mouth syndrome</option>
						   <option value="Cleidocranial dysplasia">Cleidocranial dysplasia</option>
						   <option value="Congenital syphilis Conical shape lateral incisor">Congenital syphilis Conical shape lateral incisor</option>
						   <option value="Cardiomyopathies">Cardiomyopathies</option>
						   <option value="Circulatory failure hypertension">Circulatory failure hypertension</option>
						   <option value="Cardiac arrhythmias">Cardiac arrhythmias</option>
						   <option value="Congestive heart failure">Congestive heart failure</option>
						   <option value="Chest pain">Chest pain</option>
						   <option value="Cherubism">Cherubism</option>
						   <option value="Cretinism">Cretinism</option>
						   <option value="Cryptococcosis">Cryptococcosis</option>
						   <option value="Cancrum oris">Cancrum oris</option>
						   <option value="Carotid sheath invasion">Carotid sheath invasion</option>
						   <option value="Cavernous sinus thrombosis">Cavernous sinus thrombosis</option>
						   <option value="Clear cell odontogenic tumor">Clear cell odontogenic tumor</option>
						   <option value="Compound odontom">Compound odontom</option>
						   <option value="Complex odontom Calcifying odontogenic cyst">Complex odontom Calcifying odontogenic cyst</option>
						   <option value="Condylar hyperplasia">Condylar hyperplasia</option>
						   <option value="Condylar hypoplasia">Condylar hypoplasia</option>
						   <option value="Condylar aplasia">Condylar aplasia</option>
						   <option value="Claudications">Claudications</option>
						   <option value="Cough">Cough</option>
						   <option value="Ch. Bronchitis">Ch. Bronchitis</option>
						   <option value="Chronic sinusitis">Chronic sinusitis</option>
						   <option value="Chondrosarcoma">Chondrosarcoma</option>
						   <option value="Cutaneous malignant melanoma">Cutaneous malignant melanoma</option>
						   <option value="Chronic Osteomyelitis">Chronic Osteomyelitis</option>
						   <option value="Chronic focal sclerosing Osteomyelitis">Chronic focal sclerosing Osteomyelitis</option>
						   <option value="Cavernous sinus thrombosis">Cavernous sinus thrombosis</option>
						   <option value="Chronic habitual subluxation">Chronic habitual subluxation</option>
						   <option value="Canine space infection">Canine space infection</option>
						   <option value="Cyst">Cyst</option>
						   <option value="Coronoid process fracture">Coronoid process fracture</option>
						   <option value="Condylar Neck fracture">Condylar Neck fracture</option>
						   <option value="Complete cleft of secondary palate">Complete cleft of secondary palate</option>
						   <option value="Celiac disease">Celiac disease</option>
						   <option value="Canalicular adenoma">Canalicular adenoma</option>
						   <option value="Carbuncle">Carbuncle</option>
						   <option value="Crouzon's syndrome">Crouzon's syndrome</option>
						   <option value="Calcifyin epithelial odontogenic tumour">Calcifyin epithelial odontogenic tumour</option>
						   <option value="Chondroma">Chondroma</option>
						   <option value="Chemical injury">Chemical injury</option>
						   <option value="Cherubism">Cherubism</option>
						   <option value="Candidosis">Candidosis</option>
						   <option value="Chronic hyperplastic Candidosis">Chronic hyperplastic Candidosis</option>
						   <option value="Cytomegalovirus ulceration">Cytomegalovirus ulceration</option>
						   <option value="Delayed eruption">Delayed eruption</option>
						   <option value="Dentine defect">Dentine defect</option>
						   <option value="Down's syndrome">Down's syndrome</option>
						   <option value="Dentinogenesis imperfect">Dentinogenesis imperfect</option>
						   <option value="Dentinal dysplasia">Dentinal dysplasia</option>
						   <option value="Dilacerations">Dilacerations</option>
						   <option value="Dens invaginatus">Dens invaginatus</option>
						   <option value="Diabetes Type-I">Diabetes Type-I</option>
						   <option value="Diabetes Type-II">Diabetes Type-II</option>
						   <option value="Dermatomyositis">Dermatomyositis</option>
						   <option value="Odontogenic fibrodentinoma">Odontogenic fibrodentinoma</option>
						   <option value="Odontoameloblastoma">Odontoameloblastoma</option>
						   <option value="Ductal papiloma Dry mouth">Ductal papiloma Dry mouth</option>
						   <option value="Deep temporal space infection">Deep temporal space infection</option>
						   <option value="Dacryocystis Double vision Dento-alveolar fracture">Dacryocystis Double vision Dento-alveolar fracture</option>
						   <option value="Diffuse sclerosing Osteomyelitis">Diffuse sclerosing Osteomyelitis</option>
						   <option value="Dry socket">Dry socket</option>
						   <option value="Deep mycosis">Deep mycosis</option>
						   <option value="MPDS">MPDS</option>
						   <option value="Dish face deformity">Dish face deformity</option>
						   <option value="Dermoid cyst">Dermoid cyst</option>
						   <option value="Dentigerous cyst">Dentigerous cyst</option>
						   <option value="Discoid lupous erythematosus">Discoid lupous erythematosus</option>
						   <option value="Dysplastic leukoplakia">Dysplastic leukoplakia</option>
						   <option value="Dysplasia">Dysplasia</option>
						   <option value="Drug induced gingival hyperplasia">Drug induced gingival hyperplasia</option>
						   <option value="Epithelioma Emphysema">Epithelioma Emphysema</option>
						   <option value="Extra capsular fracture">Extra capsular fracture</option>
						   <option value="Enamel defect">Enamel defect</option>
						   <option value="Ehlers-Danlos syndrome">Ehlers-Danlos syndrome</option>
						   <option value="Epidermolysis bullosa">Epidermolysis bullosa</option>
						   <option value="Extra articular ankykosis">Extra articular ankykosis</option>
						   <option value="Ewing sarcoma">Ewing sarcoma</option>
						   <option value="Eosinophilic granuloma">Eosinophilic granuloma</option>
						   <option value="Epistaxis">Epistaxis</option>
						   <option value="Eruption cyst">Eruption cyst</option>
						   <option value="Exposing of palate">Exposing of palate</option>
						   <option value="Erythroplakia">Erythroplakia</option>
						   <option value="Erythema multiforme">Erythema multiforme</option>
						   <option value="Epulis">Epulis</option>
						   <option value="Fluorosis">Fluorosis</option>
						   <option value="Floppy joint syndrome">Floppy joint syndrome</option>
						   <option value="Familial adenomatous polyposis">Familial adenomatous polyposis</option>
						   <option value="Fetal alcohol syndrome">Fetal alcohol syndrome</option>
						   <option value="False ankykosis">False ankykosis</option>
						   <option value="Fibrous ankykosis">Fibrous ankykosis</option>
						   <option value="Frey's syndrome">Frey's syndrome</option>
						   <option value="Flattening of zygotic complex">Flattening of zygotic complex</option>
						   <option value="Flattening of zygotic arch">Flattening of zygotic arch</option>
						   <option value="Flattening of facial profile">Flattening of facial profile</option>
						   <option value="Facial Space Infection">Facial Space Infection</option>
						   <option value="Furuncle">Furuncle</option>
						   <option value="Fibromatoses">Fibromatoses</option>
						   <option value="Fibrosarcoma">Fibrosarcoma</option>
						   <option value="Fore Head fracture">Fore Head fracture</option>
						   <option value="Frontal deformity">Frontal deformity</option>
						   <option value="Fatty liver">Fatty liver</option>
						   <option value="Fronto - Nose ethimoidal fracture">Fronto - Nose ethimoidal fracture</option>
						   <option value="Frictional keratosis">Frictional keratosis</option>
						   <option value="Furred tongue">Furred tongue</option>
						   <option value="Fordyns granules">Fordyns granules</option>
						   <option value="Fibrous epulis">Fibrous epulis</option>
						   <option value="Ghost teeth">Ghost teeth</option>
						   <option value="Gardner's syndrome">Gardner's syndrome</option>
						   <option value="Gingival cyst of newborn">Gingival cyst of newborn</option>
						   <option value="Gingival cyst of adult">Gingival cyst of adult</option>
						   <option value="Graves' disease">Graves' disease</option>
						   <option value="Garre's Osteomyelitis">Garre's Osteomyelitis</option>
						   <option value="Geographic tongue">Geographic tongue</option>
						   <option value="Giant cell epulis">Giant cell epulis</option>
						   <option value="Glossophryngeal neuralgia">Glossophryngeal neuralgia</option>
						   <option value="Glossitis">Glossitis</option>
						   <option value="Gingival recession">Gingival recession</option>
						   <option value="Hyperplasic coronoid process">Hyperplasic coronoid process</option>
						   <option value="Hypodontia">Hypodontia</option>
						   <option value="Hyerdontia ">Hyerdontia </option>
						   <option value="Hereditary gingival fibromatosis">Hereditary gingival fibromatosis</option>
						   <option value="Hereditary enamel hypoplasia ">Hereditary enamel hypoplasia </option>
						   <option value="Hereditary enamel hyocalcification">Hereditary enamel hyocalcification</option>
						   <option value="Isolated hypodontia">Isolated hypodontia</option>
						   <option value="Hutchinson's incisors">Hutchinson's incisors</option>
						   <option value="Hepatitis-A">Hepatitis-A</option>
						   <option value="Hepatitis-B">Hepatitis-B</option>
						   <option value="Hepatitis-C ">Hepatitis-C </option>
						   <option value="Hepatitis-D">Hepatitis-D</option>
						   <option value="Hepatitis-E">Hepatitis-E</option>
						   <option value="Hemophilia">Hemophilia</option>
						   <option value="Haemoptysis">Haemoptysis</option>
						   <option value="Thalassaemia">Thalassaemia</option>
						   <option value="Hemimandibular elongatin">Hemimandibular elongatin</option>
						   <option value="Hemimandibula hypertrophy">Hemimandibula hypertrophy</option>
						   <option value="Hemifacial hypertrophyr">Hemifacial hypertrophyr</option>
						   <option value="Hemifacial atrophy">Hemifacial atrophy</option>
						   <option value="Hemifacial mcrosomia">Hemifacial mcrosomia</option>
						   <option value="Heamangiolipoma ">Heamangiolipoma </option>
						   <option value="Heamangioma">Heamangioma</option>
						   <option value="Histoplasmosis">Histoplasmosis</option>
						   <option value="Hodgking Lymphomas ">Hodgking Lymphomas </option>
						   <option value="Hashimoto's thyroiditis">Hashimoto's thyroiditis</option>
						   <option value="Hypophosphatasia">Hypophosphatasia</option>
						   <option value="Hemimandibular elongatin">Hemimandibular elongatin</option>
						   <option value="Hemimandibula hypertrophy">Hemimandibula hypertrophy</option>
						   <option value="Hemifacial hypertrophyr">Hemifacial hypertrophyr</option>
						   <option value="Hemifacial atrophy">Hemifacial atrophy</option>
						   <option value="Hemifacial mcrosomia">Hemifacial mcrosomia</option>
						   <option value="Heamangiolipoma ">Heamangiolipoma </option>
						   <option value="Heamangioma">Heamangioma</option>
						   <option value="Histoplasmosis">Histoplasmosis</option>
						   <option value="Hodgking Lymphomas ">Hodgking Lymphomas </option>
						   <option value="Hashimoto's thyroiditis">Hashimoto's thyroiditis</option>
						   <option value="Herpetiform ulcer">Herpetiform ulcer</option>
						   <option value="Herpetic stomatitis">Herpetic stomatitis</option>
						   <option value="Herpetic gingivostomatitis">Herpetic gingivostomatitis</option>
						   <option value="Herpes simplex">Herpes simplex</option>
						   <option value="Herpes simplex type-1">Herpes simplex type-1</option>
						   <option value="Herpes simplex type-2">Herpes simplex type-2</option>
						   <option value="Herpes zoster ">Herpes zoster </option>
						   <option value="Homogeneous leukoplakia ">Homogeneous leukoplakia </option>
						   <option value="Hand foot and mouth disease">Hand foot and mouth disease</option>
						   <option value="Hairy leukoplakia">Hairy leukoplakia</option>
						   <option value="Herpes labialis">Herpes labialis</option>
						   <option value="Isolated anodontia ">Isolated anodontia </option>
						   <option value="Infra temporal space tumor ">Infra temporal space tumor </option>
						   <option value="Incomplete cleft of secondary palate">Incomplete cleft of secondary palate</option>
						   <option value="Infective endocarditis">Infective endocarditis</option>
						   <option value="Infratemporal space infection">Infratemporal space infection</option>
						   <option value="Intra articular ankykosis   ">Intra articular ankykosis   </option>
						   <option value="Intra capsular fracture ">Intra capsular fracture </option>
						   <option value="Isolated Nasal fracture">Isolated Nasal fracture</option>
						   <option value="Idiopathic thrombocytopenic purpura">Idiopathic thrombocytopenic purpura</option>
						   <option value="Infraorbital space infection">Infraorbital space infection</option>
						   <option value="Internal derangment ">Internal derangment </option>
						   <option value="Leukoplakia">Leukoplakia</option>
						   <option value="Lichen planus ">Lichen planus </option>
						   <option value="Leukoedema ">Leukoedema </option>
						   <option value="Lupous erythematosus">Lupous erythematosus</option>
						   <option value="Juvenile rheumatoid arthritis">Juvenile rheumatoid arthritis</option>
						   <option value="Kidney transplant    ">Kidney transplant    </option>
						   <option value="Rickets">Rickets</option>
						   <option value="Liver abscess,              ">Liver abscess,              </option>
						   <option value="Liver cirrhosis, ">Liver cirrhosis, </option>
						   <option value="Lymphocytic thyroiditis      ">Lymphocytic thyroiditis      </option>
						   <option value="Le-Fort 1 fracture">Le-Fort 1 fracture</option>
						   <option value="Le-Fort 2 fracture">Le-Fort 2 fracture</option>
						   <option value="Le-Fort 3 fracture ">Le-Fort 3 fracture </option>
						   <option value="Loosening/displacement of screw">Loosening/displacement of screw</option>
						   <option value="Left unilateral incomplete cleft of primary palate  ">Left unilateral incomplete cleft of primary palate  </option>
						   <option value="Left unilateral complete cleft of primary palate ending at the incisive foramen">Left unilateral complete cleft of primary palate ending at the incisive foramen</option>
						   <option value="Left complete cleft of primary and secondary palate">Left complete cleft of primary and secondary palate</option>
						   <option value="Left incomplete cleft of primary palate and incomplete cleft of secondary palate">Left incomplete cleft of primary palate and incomplete cleft of secondary palate</option>
						   <option value="Long face deformity Ludwig’s angina Lipoma">Long face deformity Ludwig’s angina Lipoma</option>
						   <option value="Lateral odontogenic cyst ">Lateral odontogenic cyst </option>
						   <option value="Long face syndrome">Long face syndrome</option>
						   <option value="Long standing dislocation">Long standing dislocation</option>
						   <option value="Lower lip space infection">Lower lip space infection</option>
						   <option value="Lateralpharyngea space infection">Lateralpharyngea space infection</option>
						   <option value="Leukemia ">Leukemia </option>
						   <option value="Langerhans Cell histocytosis  ">Langerhans Cell histocytosis  </option>
						   <option value="Lateral madibular cleft ">Lateral madibular cleft </option>
						   <option value="Myocardial infraction  ">Myocardial infraction  </option>
						   <option value="Moon's molars ">Moon's molars </option>
						   <option value="Mulberry molars ">Mulberry molars </option>
						   <option value="Mottled enamel ">Mottled enamel </option>
						   <option value="Mitral stenosis  ">Mitral stenosis  </option>
						   <option value="Multiple sclerosis">Multiple sclerosis</option>
						   <option value="Myasthenia gravis ">Myasthenia gravis </option>
						   <option value="Maxillary tubaocity fracture">Maxillary tubaocity fracture</option>
						   <option value="Mucoepidermoid carcinoma">Mucoepidermoid carcinoma</option>
						   <option value="Malignant fibrous histocytoma">Malignant fibrous histocytoma</option>
						   <option value="Mucosal malignant melanoma">Mucosal malignant melanoma</option>
						   <option value="Median madibular cleft">Median madibular cleft</option>
						   <option value="Mandibular Body fracture">Mandibular Body fracture</option>
						   <option value="Mandible fracture">Mandible fracture</option>
						   <option value="Mucormycosis">Mucormycosis</option>
						   <option value="Misshape nose">Misshape nose</option>
						   <option value="Mucous retention cyst">Mucous retention cyst</option>
						   <option value="Mucous extravasation cyst">Mucous extravasation cyst</option>
						   <option value="Mumps ">Mumps </option>
						   <option value="Mucocele ">Mucocele </option>
						   <option value="Monomorphic Adenoma   ">Monomorphic Adenoma   </option>
						   <option value="Myoepithlioma">Myoepithlioma</option>
						   <option value="Medistanitis">Medistanitis</option>
						   <option value="Meningitis">Meningitis</option>
						   <option value="Myxoma">Myxoma</option>
						   <option value="Malformation lymphatic">Malformation lymphatic</option>
						   <option value="Malformation capillary">Malformation capillary</option>
						   <option value="Malformation venous ">Malformation venous </option>
						   <option value="Malformation arterial">Malformation arterial</option>
						   <option value="Mandibular anterior-posterior deficiency (class- )">Mandibular anterior-posterior deficiency (class- )</option>
						   <option value="Mandibular anterior-posterior excess ( class- )">Mandibular anterior-posterior excess ( class- )</option>
						   <option value="Mandibular asymmetry ( class- )">Mandibular asymmetry ( class- )</option>
						   <option value="Maxillary anterior-posterior deficiency ( class- ) ">Maxillary anterior-posterior deficiency ( class- ) </option>
						   <option value="Maxillary anterior-posterior excess ( class- )">Maxillary anterior-posterior excess ( class- )</option>
						   <option value="Macrogenia ">Macrogenia </option>
						   <option value="Microgenia ">Microgenia </option>
						   <option value="Malignant Pleomorphic Adenoma">Malignant Pleomorphic Adenoma</option>
						   <option value="Mucocele ">Mucocele </option>
						   <option value="Major aphthae">Major aphthae</option>
						   <option value="Minor aphthae">Minor aphthae</option>
						   <option value="Myofacial pain dysfunction syndrome ">Myofacial pain dysfunction syndrome </option>
						   <option value="Median rhomboid glossitis">Median rhomboid glossitis</option>
						   <option value="Mucous membrane pemphigoid ">Mucous membrane pemphigoid </option>
						   <option value="Nose-ethmoidal fracture  ">Nose-ethmoidal fracture  </option>
						   <option value="Nasal deformity">Nasal deformity</option>
						   <option value="Non-Hodgking Lymphomas ">Non-Hodgking Lymphomas </option>
						   <option value="Non-suppurative Osteomyelitis ">Non-suppurative Osteomyelitis </option>
						   <option value="NOMA">NOMA</option>
						   <option value="Neonatal Osteomyelitis ">Neonatal Osteomyelitis </option>
						   <option value="Nasopalatine cyst ">Nasopalatine cyst </option>
						   <option value="Nasolabial cyst ">Nasolabial cyst </option>
						   <option value="Non-union">Non-union</option>
						   <option value="Nerve damage ">Nerve damage </option>
						   <option value="Nephrotic syndrome">Nephrotic syndrome</option>
						   <option value="Orbito - Nose ethimoidal fracture">Orbito - Nose ethimoidal fracture</option>
						   <option value="Orbital fracture">Orbital fracture</option>
						   <option value="Oblique  madibular cleft  ">Oblique  madibular cleft  </option>
						   <option value="Oronasal fistula">Oronasal fistula</option>
						   <option value="Ophthalmic complication ">Ophthalmic complication </option>
						   <option value="Oroantral communication">Oroantral communication</option>
						   <option value="Oronasal communication ">Oronasal communication </option>
						   <option value="Osteosarcoma">Osteosarcoma</option>
						   <option value="Oral submucous fibrosis ">Oral submucous fibrosis </option>
						   <option value="Osteoblastoma ">Osteoblastoma </option>
						   <option value="Osteoma">Osteoma</option>
						   <option value="Odontogenic kerotocyst">Odontogenic kerotocyst</option>
						   <option value="Open bite ">Open bite </option>
						   <option value="Over bite ">Over bite </option>
						   <option value="Osteomyelitis">Osteomyelitis</option>
						   <option value="Osteoarthritis">Osteoarthritis</option>
						   <option value="Orbital deformity">Orbital deformity</option>
						   <option value="Osteoradionecrosis">Osteoradionecrosis</option>
						   <option value="Osteoarthritis ">Osteoarthritis </option>
						   <option value="Oral thrush ">Oral thrush </option>
						   <option value="Osteopetrosis">Osteopetrosis</option>
						   <option value="Osteoma">Osteoma</option>
						   <option value="Oral warts">Oral warts</option>
						   <option value="Plunging Ranula">Plunging Ranula</option>
						   <option value="Parotid duct injury ">Parotid duct injury </option>
						   <option value="Pleomorphic Adenoma">Pleomorphic Adenoma</option>
						   <option value="Papiloma ">Papiloma </option>
						   <option value="Pierre-robin syndrome">Pierre-robin syndrome</option>
						   <option value="Parry Rombeg syndrome">Parry Rombeg syndrome</option>
						   <option value="Parotid fistula">Parotid fistula</option>
						   <option value="Parotid duct strictures">Parotid duct strictures</option>
						   <option value="Ptyalism ">Ptyalism </option>
						   <option value="Papillary obstruction of duct">Papillary obstruction of duct</option>
						   <option value="Prosthetic heart value     ">Prosthetic heart value     </option>
						   <option value="Peace maker">Peace maker</option>
						   <option value="Pulmonary shunt">Pulmonary shunt</option>
						   <option value="Prosthetic in cardiovascular system ">Prosthetic in cardiovascular system </option>
						   <option value="Purpura">Purpura</option>
						   <option value="Palatal fracture ">Palatal fracture </option>
						   <option value="Ptosis">Ptosis</option>
						   <option value="Panfacial fracture">Panfacial fracture</option>
						   <option value="Para Symphysis fracture">Para Symphysis fracture</option>
						   <option value="Periodontal abscess Parotid space infection">Periodontal abscess Parotid space infection</option>
						   <option value="Pterogoid space infection">Pterogoid space infection</option>
						   <option value="Parapharyngeal space infection ">Parapharyngeal space infection </option>
						   <option value="Palatel space infection">Palatel space infection</option>
						   <option value="Periapical granuloma">Periapical granuloma</option>
						   <option value="Pernicious anemia">Pernicious anemia</option>
						   <option value="Peripheral vascular disease">Peripheral vascular disease</option>
						   <option value="Pemphigus vulgaris">Pemphigus vulgaris</option>
						   <option value="Pemphigus">Pemphigus</option>
						   <option value="Plaque form Lichen planus">Plaque form Lichen planus</option>
						   <option value="Popular form Lichen planus">Popular form Lichen planus</option>
						   <option value="Pulp polyp">Pulp polyp</option>
						   <option value="Paraesthesia">Paraesthesia</option>
						   <option value="Pget's disease">Pget's disease</option>
						   <option value="Papiloma">Papiloma</option>
						   <option value="Pregnancy epulis Pyogenic arthritis">Pregnancy epulis Pyogenic arthritis</option>
						   <option value="Regional odontodysplasia ">Regional odontodysplasia </option>
						   <option value="Rheumatic fever">Rheumatic fever</option>
						   <option value="Respiratory tract infection">Respiratory tract infection</option>
						   <option value="Reactive arthritis ">Reactive arthritis </option>
						   <option value="Renal failure">Renal failure</option>
						   <option value="Rheumatoid arthritis">Rheumatoid arthritis</option>
						   <option value="Rotated upper arch">Rotated upper arch</option>
						   <option value="Rhabdomyosarcoma">Rhabdomyosarcoma</option>
						   <option value="Recurrent tumor">Recurrent tumor</option>
						   <option value="Residual tumor">Residual tumor</option>
						   <option value="Radiation sarcoma">Radiation sarcoma</option>
						   <option value="Retromandibular space infection">Retromandibular space infection</option>
						   <option value="Retropharyngea space infection">Retropharyngea space infection</option>
						   <option value="Rheumatoid arthritis ">Rheumatoid arthritis </option>
						   <option value="Ranula">Ranula</option>
						   <option value="Rhabdomyoma">Rhabdomyoma</option>
						   <option value="Residual cyst">Residual cyst</option>
						   <option value="Radicular cyst">Radicular cyst</option>
						   <option value="Ranula">Ranula</option>
						   <option value="Retrobulbular cellulitis">Retrobulbular cellulitis</option>
						   <option value="Restricted mouth opening">Restricted mouth opening</option>
						   <option value="Ramus fracture">Ramus fracture</option>
						   <option value="Recurrent aphthae">Recurrent aphthae</option>
						   <option value="Reticular form Lichen planus">Reticular form Lichen planus</option>
						   <option value="Racial pigmentation">Racial pigmentation</option>
						   <option value="Rheumatoid arthritis">Rheumatoid arthritis</option>
						   <option value="Retained root">Retained root</option>
						   <option value="Supernumerary teeth">Supernumerary teeth</option>
						   <option value="Supplemental teeth">Supplemental teeth</option>
						   <option value="Shell teeth">Shell teeth</option>
						   <option value="Submucous cleft palate">Submucous cleft palate</option>
						   <option value="Subacute thyroiditis">Subacute thyroiditis</option>
						   <option value="Sjogren syndrome">Sjogren syndrome</option>
						   <option value="Systemic lupus erythematosus">Systemic lupus erythematosus</option>
						   <option value="Spenoid fracture">Spenoid fracture</option>
						   <option value="Skull bone fracture">Skull bone fracture</option>
						   <option value="Superior orbital fissure syndrome">Superior orbital fissure syndrome</option>
						   <option value="Symphysis  fracture">Symphysis  fracture</option>
						   <option value="Sub condylar fracture">Sub condylar fracture</option>
						   <option value="Submucous palatal cleft">Submucous palatal cleft</option>
						   <option value="Scar">Scar</option>
						   <option value="Sq cell carcinoma">Sq cell carcinoma</option>
						   <option value="Scar">Scar</option>
						   <option value="Sjogren's syndrome">Sjogren's syndrome</option>
						   <option value="Syphilitic Osteomyelitis">Syphilitic Osteomyelitis</option>
						   <option value="Sialadenitis">Sialadenitis</option>
						   <option value="suppurative Osteomyelitis">suppurative Osteomyelitis</option>
						   <option value="Secondary tumor">Secondary tumor</option>
						   <option value="Sialorrhea">Sialorrhea</option>
						   <option value="Sialolithiasis">Sialolithiasis</option>
						   <option value="Schwanoma">Schwanoma</option>
						   <option value="Squamous odontogenic tumor">Squamous odontogenic tumor</option>
						   <option value="Solitary bone cyst">Solitary bone cyst</option>
						   <option value="Short face syndrome">Short face syndrome</option>
						   <option value="Sebaceous cyst">Sebaceous cyst</option>
						   <option value="Sub mental space infection">Sub mental space infection</option>
						   <option value="Sub mandibular space infection">Sub mandibular space infection</option>
						   <option value="Sub lingual space infection">Sub lingual space infection</option>
						   <option value="Sub massetric space infection">Sub massetric space infection</option>
						   <option value="Superficial temporal space infection">Superficial temporal space infection</option>
						   <option value="Systemic sclerosis">Systemic sclerosis</option>
						   <option value="Scleroderma">Scleroderma</option>
						   <option value="Sore tongue">Sore tongue</option>
						   <option value="Squamous odontogenic tumor">Squamous odontogenic tumor</option>
						   <option value="Squamous cell papilloma">Squamous cell papilloma</option>
						   <option value="Solitary eosinophilic granuloma">Solitary eosinophilic granuloma</option>
						   <option value="Solitary plasmacytoma">Solitary plasmacytoma</option>
						   <option value="Solitary bone cyst">Solitary bone cyst</option>
						   <option value="Sinusitis">Sinusitis</option>
						   <option value="Scurvy">Scurvy</option>
						   <option value="Speckled leukoplakia">Speckled leukoplakia</option>
						   <option value="Systemic lupous erythematosus">Systemic lupous erythematosus</option>
						   <option value="Toxic multinodular goiter">Toxic multinodular goiter</option>
						   <option value="Temporomandibular joint fracture">Temporomandibular joint fracture</option>
						   <option value="Temporal space infection">Temporal space infection</option>
						   <option value="Tuberculos Osteomyelitis">Tuberculos Osteomyelitis</option>
						   <option value="Trismus">Trismus</option>
						   <option value="Treacher-collins syndrome">Treacher-collins syndrome</option>
						   <option value="Thyroglossal duct cyst">Thyroglossal duct cyst</option>
						   <option value="True ankykosis">True ankykosis</option>
						   <option value="Turner teeth">Turner teeth</option>
						   <option value="Tetracycline pigmentation">Tetracycline pigmentation</option>
						   <option value="Tumor of condylar head">Tumor of condylar head</option>
						   <option value="Traumatic ulcer">Traumatic ulcer</option>
						   <option value="Traumatic neuroma">Traumatic neuroma</option>
						   <option value="Tori">Tori</option>
						   <option value="Torus mandibularis">Torus mandibularis</option>
						   <option value="Torus palatines">Torus palatines</option>
						   <option value="Temporal artiritis">Temporal artiritis</option>
						   <option value="Trigeminal neuralgia">Trigeminal neuralgia</option>
						   <option value="Upper lip space infection">Upper lip space infection</option>
						   <option value="Undifferentiated carcinoma">Undifferentiated carcinoma</option>
						   <option value="Ulcerative form Lichen planus">Ulcerative form Lichen planus</option>
						   <option value="Valopharyngeal insufficiency">Valopharyngeal insufficiency</option>
						   <option value="Verricous carcinoma">Verricous carcinoma</option>
						   <option value="Ventricular septal defect">Ventricular septal defect</option>
						   <option value="Varicose veins">Varicose veins</option>
						   <option value="Verrucous leukoplakia">Verrucous leukoplakia</option>
						   <option value="Verruca vulgaris">Verruca vulgaris</option>
						   <option value="Verrucous carcinoma">Verrucous carcinoma</option>
						   <option value="Warthin's tumour">Warthin's tumour</option>
						   <option value="White sponge naevus">White sponge naevus</option>
						   <option value="White lesion">White lesion</option>
						   <option value="Warts">Warts</option>
						   <option value="Xerostomia">Xerostomia</option>
						   <option value="Goldenha syndrome">Goldenha syndrome</option>
						   <option value="Zygomatic fracture">Zygomatic fracture</option>
						   <option value="Zygomatic Arch fracture">Zygomatic Arch fracture</option>
					   </select>
				   </td></tr>
				   <tr><td style="padding-top: 10px;" colspan="2"><input type="checkbox" checklist-model="prescription.diagonosis" checklist-comparator="compareFn" checklist-value="getOtherCheckboxValue(prescription.diagonosis)">&emsp;Others [if any] <input type="text" ng-model="getOtherTextModel(prescription.diagonosis).other.text"></td></tr>
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
						   <div class="col-sm-3">
							   <input type="text"
									  data-ng-model="medicine.medicineName"
									  data-typeahead-template-url="bind-node.html"
									  data-typeahead="medItem as medItem.medicineName for medItem in searchMedicine($viewValue, $index)"
									  data-typeahead-on-select="addMedicine($item, $model, $label, $index)"
									  class="form-control custom-search" placeholder="Medicine... " style="min-width: 120px;"/>
						   </div>
						   <div class="col-sm-3">
							   <select class="form-control" style="width: 200px;" data-ng-model="medicine.dose">
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
							   <select class="form-control" style="width: 120px;" data-ng-model="medicine.doseUnit">
								   <option value="mg">mg</option>
								   <option value="ml">ml</option>
							   </select>
						   </div>
						   <div class="col-sm-2">
							   <textarea  class="form-control" placeholder="Total" cols="20" rows="1" data-ng-model="medicine.totalNumber"></textarea>
						   </div>
				   </div>
				   <div class="row" style="margin-top: 5px;">
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
						   <div class="col-sm-3">
							   <textarea  class="form-control" placeholder="Duration" cols="20" rows="1" data-ng-model="medicine.duration"></textarea>
						   </div>
						   <div class="col-sm-3">
							   <select class="form-control" style="width: 200px;" data-ng-model="medicine.afterMeal">
								   <option value="After Meal">After Meal</option>
								   <option value="Before Meal">Before Meal</option>
							   </select>
						   </div>
						   <div class="col-sm-3">
							   <textarea  class="form-control" placeholder="Comments" cols="20" rows="1" data-ng-model="medicine.comment"></textarea>
						   </div>
						   <div class="col-sm-1" ng-if="$index==0">
							   <a href="#"><span class="fa fa-plus fa-2x" style="margin-left: 20px;" ng-click="addMedicineRow()"></span></a>
						   </div>
						   <div class="col-sm-1" ng-if="$index!=0">
							   <a href="#"><span class="fa fa-minus fa-2x" style="margin-left: 20px;" ng-click="removeMedicineRow($index)"></span></a>
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
						<tr><td><input type="checkbox" checklist-model="prescription.advice" value="Don&#8217;t take hot, hard and spicy food for 24 hour and avoid gargling.">&emsp;Don't take hot, hard and spicy food for 24 hour and avoid gargling.</td></tr>
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


				<div class="panel-footer">
					<div class="row form-inline">
						<div class="col-md-2">
							<div class="form-group" style="margin-top:3px;">
							<label>Next Visit</label>
							</div>
						</div>
						<div class="col-md-5">
						  <div class="form-group">
							 <label for="dateInput">Date</label>
							 <input type="text" class="datepicker form-control " id="dateInput"  data-ng-model="prescription.nextVisitDate"/>
						  </div>
						</div>
					   <div class="col-md-3">
						  <div class="form-group">
							<input type="text" class="form-control" id="timeInput" placeholder="Time after" name="timeInput">
						   </div>
						</div>
					   <div class="col-md-2">
						  <div class="form-group">
							<select class="form-control input-sm" id="dayInput" name="dayInput" >
							<option value="0">Day</option>
							</select>
						   </div>
						</div>
					</div>
				</div>
			</div>

		    <div class="panel panel-default">
				<div class="panel-heading">
					  <div class="row">
						  <div class="col-md-2">Refer to</div>
						  <div class="col-md-10">
							  <select class="form-control" style="width: 120px;" data-ng-model="prescription.referTo">
								  <option value="Anesthesia, Analgesia and Intensive Care Medicine">Anesthesia, Analgesia and Intensive Care Medicine</option>
								  <option value="Biochemistry">Biochemistry</option>
								  <option value="Conservative Dentistry and Endodontics">Conservative Dentistry and Endodontics</option>
								  <option value="Cardiology">Cardiology</option>
								  <option value="Cardiac Surgery">Cardiac Surgery</option>
								  <option value="Community Ophthalmology">Community Ophthalmology</option>
								  <option value="Clinical Pathology">Clinical Pathology</option>
								  <option value="Dermatology and Venereology">Dermatology and Venereology</option>
								  <option value="Endocrinology">Endocrinology</option>
								  <option value="Gastroenterology">Gastroenterology</option>
								  <option value="Hematology">Hematology</option>
								  <option value="Hepatology">Hepatology</option>
								  <option value="Internal Medicine">Internal Medicine</option>
								  <option value="Microbiology and Immunology">Microbiology and Immunology</option>
								  <option value="Neuro Surgery">Neuro Surgery</option>
								  <option value="Neonatology">Neonatology</option>
								  <option value="Nephrology">Nephrology</option>
								  <option value="Neurology">Neurology</option>
								  <option value="Orthodontics">Orthodontics</option>
								  <option value="Oncology">Oncology</option>
								  <option value="Obstetrics and Gynecology">Obstetrics and Gynecology</option>
								  <option value="Ophthalmology">Ophthalmology</option>
								  <option value="Orthopedic Surgery">Orthopedic Surgery</option>
								  <option value="Otolaryngology">Otolaryngology</option>
								  <option value="Prosthodontics">Prosthodontics</option>
								  <option value="Preventive and children dentistry">Preventive and children dentistry</option>
								  <option value="Paediatric Gastroenterology">Paediatric Gastroenterology</option>
								  <option value="Paediatric Hematology and oncology">Paediatric Hematology and oncology</option>
								  <option value="Paediatric nephrology">Paediatric nephrology</option>
								  <option value="Physicl medicine and rehabilitation">Physicl medicine and rehabilitation</option>
								  <option value="Phychiatry">Phychiatry</option>
								  <option value="Pediatric Surgery">Pediatric Surgery</option>
								  <option value="Pathology">Pathology</option>
								  <option value="Pharmacology">Pharmacology</option>
								  <option value="Rheumatology">Rheumatology</option>
								  <option value="Radiology and Imaging">Radiology and Imaging</option>
								  <option value="Surgery">Surgery</option>
								  <option value="Transfusion Medicine">Transfusion Medicine</option>
								  <option value="Urology">Urology</option>
								  <option value="Vascular Surgery">Vascular Surgery</option>
							  </select>
						  </div>
					  </div>
				</div>
				<div class="panel-footer">
					<form class="form-inline">
						<script>
							function loadPrintPage() {
								$("<iframe>")
										.hide()
										.attr("src", "/Patient/PrescriptionPrint.jsp?accountID=<%=accountID%>&printNow")
										.appendTo("body");

							}
						</script>
						<input type="button" class="btn btn-primary" onClick="loadPrintPage()" value="Print" ng-show="<%=visitID.equals("0")%>">
						<input type="button" class="btn btn-primary" ng-click="savePrescription()" value="Save" ng-show="<%=visitID.equals("0")%>">
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