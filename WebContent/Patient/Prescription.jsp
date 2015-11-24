<!doctype html>
<html>
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
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
 <script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
 <script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
 <script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
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
</head>
<body>
<div class="container" style="padding-top: 40px;">
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
				 <td><%=patientDTO.getDateOfRec() %></td>
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
		     	<table>
		     		<tr><td><input type="checkbox" >&emsp;Pain</td></tr>
		     		<tr><td><input type="checkbox" >&emsp;Swelling</td></tr>
					<tr><td><input type="checkbox" >&emsp;Ulcer</td></tr>
					<tr><td><input type="checkbox" >&emsp;Fistula</td></tr>
					<tr><td><input type="checkbox" >&emsp;Sinus</td></tr>
					<tr><td><input type="checkbox" >&emsp;Difficulty of eating</td></tr>
					<tr><td><input type="checkbox" >&emsp;Bleeding</td></tr>
					<tr>
						<td>
							<input type="checkbox" >&emsp;Loose teeth
							<table border="1" rules="all" frame="void" style="margin-left: 20px;">
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
							</table>
						</td>
					</tr>
					<tr><td><input type="checkbox" >&emsp;Clicking Sunday</td></tr>
					<tr><td><input type="checkbox" >&emsp;Restricted mouth opening</td></tr>
					<tr><td><input type="checkbox" >&emsp;Dryness of mouth</td></tr>
					<tr><td><input type="checkbox" >&emsp;Excessive salivation</td></tr>
					<tr><td><input type="checkbox" >&emsp;Foul smell</td></tr>
					<tr><td><input type="checkbox" >&emsp;Othesr [if any] <input type="text"></td></tr>
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
							<input type="checkbox">&emsp;Caries
							<table border="1" rules="all" frame="void" style="margin-left: 20px;">
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="checkbox">&emsp;loose teeth
							<table border="1" rules="all" frame="void" style="margin-left: 20px;">
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right;"></td><td><input type="text" class="input-small"></td></tr>
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox">&emsp;BDR
							<table border="1" rules="all" frame="void" style="margin-left: 20px;">
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox">&emsp;Impacted
							<table border="1" rules="all" frame="void" style="margin-left: 20px;">
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
								<tr><td style="margin: 2pt;"><input type="text" class="input-small" style="text-align: right" style="text-align: right"></td><td><input type="text" class="input-small"></td></tr>
							</table>
						</td>
					</tr>
					<tr><td><input type="checkbox">&emsp;Supernumerary</td></tr>
					<tr><td><input type="checkbox">&emsp;Supplemental</td></tr>
					<tr><td><input type="checkbox">&emsp;Fracture</td></tr>
					<tr><td><input type="checkbox">&emsp;Cleft lip/ palate</td></tr>
					<tr><td><input type="checkbox">&emsp;Lacerated wound</td></tr>
					<tr><td><input type="checkbox">&emsp;Facial space infection</td></tr>
					<tr><td><input type="checkbox">&emsp;Ankylosis of tmj</td></tr>
					<tr><td><input type="checkbox">&emsp;Othesr [if any] <input type="text"></td></tr>
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
		     			<input type="checkbox">&emsp;x-Ray<br>
		     				<input type="checkbox" style="margin-left: 40px;">&emsp;OPG<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Periapical view<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Occlusal view of mandible<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Occlusal view of maxilla<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Lateral view of skull<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;PNS view<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Othesr [if any] <input type="text">
		     		</td></tr>
		     		<tr><td><input type="checkbox">&emsp;CT Scan of maxillofacial region</td></tr>
					<tr><td><input type="checkbox">&emsp;MRI of maxillofacial region</td></tr>
					<tr><td><input type="checkbox">&emsp;Ultrasonogram</td></tr>
					<tr><td><input type="checkbox">&emsp;Sinus</td></tr>
					<tr><td><input type="checkbox">&emsp;FNAC</td></tr>
					<tr><td>
						<input type="checkbox">&emsp;Biopsy<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Fineniddle<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Core<br>
							<input type="checkbox" style="margin-left: 40px;">&emsp;Brush<br>
					</td></tr>
					<tr><td><input type="checkbox">&emsp;Bone scan</td></tr>
					<tr><td><input type="checkbox">&emsp;Sialogram</td></tr>
					<tr><td><input type="checkbox">&emsp;Sinogram</td></tr>
					<tr><td><input type="checkbox">&emsp;Othesr [if any] <input type="text"></td></tr>
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
			 <textarea class="form-control" rows="3" col="20"></textarea>
			</div>
		</div>
	</div>
	 <!-- Left Container End-->
	 <!-- Right Container Start-->
	<div class="col-md-8 col-sm-6 col-xs-12" style="padding-left: 0px; padding-right: 0px;">
		
	 	   <div class="panel panel-default">
	 	   		<div class="panel-heading">
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
				</div>
	 	   
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-medkit"></i>&nbsp;&nbsp;Advice
					</h3>
				</div>
			   	<div class="panel-body">
				 <textarea class="form-control" rows="4" col="20"></textarea>
				</div>
				
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-medkit"></i>&nbsp;&nbsp;Name of Doctor
					</h3>
				</div>
			   	<div class="panel-body">
				 	Dr. Md. Wares Uddin
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
					  	<input type="button" class="btn btn-primary" onClick="window.print()" value="Print">
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