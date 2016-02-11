<%@ include file="../includes/checkLogin.jsp"%>
<!doctype html>
<html ng-app="hms">
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="role.RoleService" %>
<%@ page import="role.RoleDTO" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
PatientService patientServ = new PatientService();
PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));
UserDTO  userDTO = new UserService().getUserDTO(loginDTO.getUserID());
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
	<script type="text/javascript" src="../Assets/js/angular/controllers/treatmentPlanController.js?v=1"></script>

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
		/* style sheet for "A4" printing */
		@media print and (width: 21cm) and (height: 29.7cm) {
			@page {
				margin: 3cm;
			}
			#header {display:none}
		}

	</style>
</head>
<body ng-controller = "TreatmentPlanController" ng-init="getPrescription('<%=accountID%>')">
<div class="container" style="padding-top: 40px;width:1000px;height: 1240px;">

	<header class="banner" role="banner">
		
		<div class="row">
		   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			 <img class="img-responsive" src="../Assets/Images/banner.jpg">
		   </div>
		</div>
		
	</header>
    <!-- Patient info panel -->
	<div class="panel panel-default">
		<div class="panel-heading" style="background: white;">
			<h3 class="panel-title">
				Patient Details
		    </h3>
        </div>
	   <div class="panel-body_1 table-responsive ">
		 <table class="table-bordered table">
			<thead>
			  <tr>
			  	 <th>Ticket Number</th>
				 <th>Patient ID</th>
				 <th>Name</th>
				 <th>Age</th>
				 <th>Sex</th>
				 <th>e-mail</th>
			  	 <th>Phone</th>
			     <th>Reg: Date</th>
			  </tr>
			</thead>
		   <tbody>
			  <tr>
			  	 <td><%=patientDTO.getTicketNumber() %></td>
				 <td><%=patientDTO.getAccId() %></td>
				 <td><%=patientDTO.getName() %></td>
				 <td><%=patientDTO.getAge() %></td>
				 <td><%=patientDTO.getSex() %></td>
			     <td></td>
			     <td><%=patientDTO.getTelephoneNum() %></td>
				 <td><%=patientDTO.getDateOfRec() %></td>
			  </tr>
		   </tbody>
		</table>
	   </div>
	</div>
	<div class="panel panel-default">
		<table style="width: 100%;">
		<tr>
			<td width="35%" style="vertical-align: top;border-right: 1px solid #999;padding-left: 10px;">
				<div class="row"><div class="col-sm-9"><h4><i class="fa fa-comments"></i>&nbsp;&nbsp;H/O</h4></div></div>
				<div class="row" ng-repeat="h in prescription.ho">
					<div ng-if="!h.other" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{h}}</div>
					<div ng-if="h.other"  class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{h.other.text}}</div>
				</div>
				<div class="row"><div class="col-sm-9"><h4><i class="fa fa-comments"></i>&nbsp;&nbsp;Chief Complaint (C/C)</h4></div></div>
				<div class="row" ng-repeat="cc in prescription.chiefComplain">
					<div ng-if="isString(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div>
					<div ng-if="isObject(cc)" class="col-sm-9 col-lg-offset-1">{{$index+1}}.
						<span  ng-if="!cc.other" ng-repeat="(key, value) in cc">
							{{key}}
							<table style="width: 100%;">
								<tr><td width="50%" style="border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lt.join(', ')}}</td><td width="50%" style="border-bottom: 1px solid #CCC;padding: 5px;">&nbsp;{{value.rt.join(', ')}}</td></tr>
								<tr><td width="50%" style="border-right: 1px solid #CCC;text-align: right;padding: 5px;">&nbsp;{{value.lb.join(', ')}}</td><td width="50%" style="padding: 5px;">&nbsp;{{value.rb.join(', ')}}</td></tr>
							</table>
						</span>
						<span ng-if="cc.other">{{cc.other.text}}</span>
					</div>
				</div>
				<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-hospital-o"></i>&nbsp;&nbsp;O/E</h4></div></div>
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
				<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-hospital-o"></i>&nbsp;&nbsp;Investigation</h4></div></div>
				<div class="row" ng-repeat="cc in prescription.investigation">
					<div ng-if="cc.other" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc.other.text}}</div>
					<div ng-if="!cc.other" class="col-sm-9 col-lg-offset-1">{{$index+1}}. {{cc}}</div>
				</div>
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
				<div class="row" ng-repeat="cc in prescription.diagonosis">
					<div ng-if="cc.disease" class="col-sm-9 col-lg-offset-1">{{prescription.onObservation.length+$index+1}}. Disease : {{cc.disease}}</div>
					<div ng-if="cc.other" class="col-sm-9 col-lg-offset-1">{{prescription.onObservation.length+$index+1}}. {{cc.other.text}}</div>
				</div>
			</td>
			<td width="65%" style="vertical-align: top;padding-left: 10px;">
				<div class="row"><div class="col-sm-9"><h4><i class="fa fa-medkit"></i>&nbsp;&nbsp;Rx</h4></div></div>
				<table style="width: 100%;">
				<tr ng-repeat="med in prescription.medicines">
					<td class="col-sm-12" ng-if="med.medicineName!='null'">
						{{$index+1}}. {{med.medicineType}}: {{med.medicineName}} {{med.dose}} {{med.doseUnit}}.................{{med.totalNumber}}
						<br/>&nbsp;&nbsp;&nbsp; {{med.frequency}} {{med.duration!='null'?med.duration:''}} {{med.afterMeal}}  {{med.comment!='null'?med.comment:''}}
					</td>
				</tr>
				</table>
				<div class="row" style="margin-top: 20px;"><div class="col-sm-9"><h4><i class="fa fa-h-square"></i>&nbsp;&nbsp;Advice</h4></div></div>
				<table style="width: 100%;">
					<tr ng-repeat="cc in prescription.advice">
						<td ng-if="isString(cc)" class="col-sm-9">{{$index+1}}. {{cc}}</td>
					</tr>
				</table>
				<div class="row" style="margin-top: 20px;"></div>
				<table style="margin-top: 10px;">
					<tr style="margin-top: 10px;">
						<td style="margin-top: 10px;">Next Visit:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>{{prescription.nextVisitDate!='null'?prescription.nextVisitDate:''}}</td>
					</tr>
					<tr style="margin-top: 10px;">
						<td style="margin-top: 10px;">Refer to:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>{{prescription.referTo!='null'?prescription.referTo:''}}</td>
					</tr>
				</table>

			</td></tr>
			</table>
		</table>
	</div>
</div>
<div style="position: absolute;right: 20px;bottom: 50px;width: 250px;">
	<table style="border-top: 1px solid gray;width: 100%">
		<tr><td style="margin-top: 20px;">Name</td><td><%=userDTO.getEmployeeName()%></td></tr>
		<%
			RoleService service = new RoleService();
			RoleDTO dto=service.getRoleDTO(userDTO.getRoleID());
		%>
		<tr><td>Designation</td><td><%=dto.getRoleName()%></td></tr>
	</table>
</div>
</body>
</html>