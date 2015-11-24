<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<html lang="en">
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SessionManager.title%></title>
<!--<link rel="stylesheet" type="text/css" href="../Assets/Fonts/fontface.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/united-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/main.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/sidebar-menu.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/style.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/tableOfTanvir.css" />

    -->
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link href="../Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Font -->
    <link href="../Assets/NewAssets/css/font-awesome.css" type="text/css" rel="stylesheet">
    <!-- Animation -->
    <link href="../Assets/NewAssets/css/animate.css" type="text/css" rel="stylesheet">
    <!-- Custom css -->
    <link href="../Assets/NewAssets/css/style.css" type="text/css" rel="stylesheet">
    
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
    <script type="text/javascript" src="../Assets/Scripts/mir-billing-script.js"></script>
    
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
    
    
	<style type="text/css">
		.drug input{
			width: 35%; 
			border: 0; 
			border-bottom: 1px dotted; 
			text-align: center;
		
		}
		select option{
			border: 0;
			border-bottom: 1px solid;
			text-align: center;
		}
	
	</style>
	
</head>


<body>

	<!--*********************Modal Starts from here*****************************-->
    <!--************************************************************************-->
    
    
    
    
    
    
    <!--*********************Body part Starts from here************************-->
    <!--***********************************************************************-->
    <div id="wrapper">
       

        
        
        
        
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                
                <input type="button" value="Print" onClick="window.print()">
                
                <div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	
                        <h5>Drug List</h5> 
                        
                    </div><!--/./ibox-title-->
					
                    <div class="ibox-content" id="print">
						<div class="form-horizontal">
							<div class="header">
								<div class="row">
									<div class="col-sm-1" style="padding-right: 0px 0px 0px 0px;">
										<img src="../Assets/NewAssets/images/BSMMU.png" alt="BSMMU" longdesc="http://www.bsmmu.edu.bd/" width="50">
									</div>
									<div class="col-sm-10" style="padding-right: 0px 0px 0px 0px; text-align: center;">
										<h1><strong>Oral and Maxillofacial Surgery Department</strong></h1>
										<h2><strong>Bangabandhu Sheikh Mujib Medical University</strong></h2>
										<h3><i><u>List of Drugs, Materials and Instruments for Operation</u></i></h3>
									</div>
									<div class="col-sm-1"></div>
									<div class="col-sm-12">
										<p>&nbsp; </p>
									</div>
									<div class="col-sm-6">
									  	<label for="p1">Name of Patient</label>
									  	<input type="text" id="p1" style="width: 60%;">
									</div>
									<div class="col-sm-2">
										<label>Age</label>
										<input type="text" style="width: 60%;">
									</div>
									<div class="col-sm-2">
										<label>Sex</label>
										<select style="width: 60%">
											<option>Male</option>
											<option>Female</option>
										</select>
									</div>
									<div class="col-sm-2">
										<label>Bed No</label>
										<input type="text" style="width: 60%;">
									</div>
									
									<div class="col-sm-12">
										<p>&nbsp; </p>
									</div>
									<div class="col-sm-12" style="text-align: center;">
										<h2><strong>DRUGS</strong></h2>
									</div>
									
									<div class="col-sm-6">
										<ol class="drug">
											<li style=";">Antibiotics:-
												<ol type="a">
													<li style="">
														Inj.
														<input type="text" style="width: 35%; border: 0; border-bottom: 1px dotted; text-align: center;">
														mg IV/1M 
														<input type="text" style="width: 35%;">
														vial
													</li>
													<li>
														Inj.
														<input type="text" style="width: 35%;">
														mg IV/1M 
														<input type="text" style="width: 35%;">
														vial
													</li>
												</ol>
											</li>
											<li>
												Inj.
												<input type="text" style="width: 40%"> mg <input type="text" style="width: 40%"> bottle
											</li>
											<li style=";">Anaigesics:-
												<ol type="a">
													<li style="">
														Inj.
														<input type="text" style="width: 35%; border: 0; border-bottom: 1px dotted; text-align: center;">
														mg 
														<input type="text" style="width: 35%;">
														Amp, IM/IV
													</li>
													<li>
														Inj.
														<input type="text" style="width: 35%;">
														mg
														<input type="text" style="width: 35%;">
														Amp
													</li>
												</ol>
											</li>
											<li>
												Inj.
												<input type="text" style="width: 40%"> mg <input type="text" style="width: 40%"> Amp
											</li>
											<li>
												Inj. Oradexon
												<input type="text" style="width: 60%"> Amp
											</li>
											<li>
												Inj. Dormicum 5mg/5ml
												<input type="text" style="width: 60%"> Amp
											</li>
											<li>
												Eye Drops
												<input type="text" style="width: 80%">
											</li>
										</ol>
									</div>
									<div class="col-sm-6">
										<ol class="drug" start="8">
											<li style=";">
												Nasal Drop:-<input type="text" style="width: 80%;">
												
											</li>
											
											<li style=";">I/v Infusions:-
												<ol type="a">
													<li style="">
														0.9% Normal Saline,
														<input type="text" style="width: 28%;">
														ml 
														<input type="text" style="width: 28%;">
														bag
													</li>
													<li>
														5% DA
														<input type="text" style="width: 35%;">
														ml
														<input type="text" style="width: 35%;">
														bag
													</li>
													
													<li>
														5% DNS
														<input type="text" style="width: 35%;">
														ml
														<input type="text" style="width: 35%;">
														bag
													</li>
													<li>
														Hartman's soln,
														<input type="text" style="width: 32%;">
														ml
														<input type="text" style="width: 32%;">
														bag
													</li>
												</ol>
											</li>
											<li>
												Chlorohexidine Hand rub,
												<input type="text" style="width: 25%"> ml <input type="text" style="width: 25%"> bottle
											</li>
											<li>
												Povidone Iodine 10% soln
												<input type="text" style="width: 25%"> ml <input type="text" style="width: 25%"> bottle
											</li>
											<li>
												Others
												<input type="text" style="width: 60%">
											</li>
											
										</ol>
									</div> <!--  -->
									
									<div class="col-sm-12" style="text-align: center;">
										<h2><strong>MATERIALS</strong></h2>
									</div>
									
									<div class="col-sm-6">
										<ol class="drug">
											<li style=";">Sterile Surgical Hand gloves:-
												<ol type="a">
													<li style="">
														Size
														<input type="text" style="width: 40%; border: 0; border-bottom: 1px dotted; text-align: center;">
														Qnty
														<input type="text" style="width: 40%;">
													</li>
													<li style="">
														Size
														<input type="text" style="width: 40%; border: 0; border-bottom: 1px dotted; text-align: center;">
														Qnty
														<input type="text" style="width: 40%;">
													</li>
													<li style="">
														Size
														<input type="text" style="width: 40%; border: 0; border-bottom: 1px dotted; text-align: center;">
														Qnty
														<input type="text" style="width: 40%;">
													</li>
													<li style="">
														Size
														<input type="text" style="width: 40%; border: 0; border-bottom: 1px dotted; text-align: center;">
														Qnty
														<input type="text" style="width: 40%;">
													</li>
												</ol>
											</li>
											<li style=";">B.P. blade:-
												<ol type="a">
													<li style="">
														No.
														<input type="text" style="width: 40%; border: 0; border-bottom: 1px dotted; text-align: center;">
														Qnty
														<input type="text" style="width: 40%;">
													</li>
													<li style="">
														No.
														<input type="text" style="width: 40%; border: 0; border-bottom: 1px dotted; text-align: center;">
														Qnty
														<input type="text" style="width: 40%;">
													</li>
													
												</ol>
											</li>
											<li style=";">Structure Materials
												<ol type="a">
													<li style="">
														Vicryl
														<ol type="1">
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
														</ol>
													</li>
													<li style="">
														Proline
														<ol type="1">
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
														</ol>
													</li>
													<li style="">
														Silk
														<ol type="1">
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
															<li>
															Size <input type="text" style="width: 9%">Round/Cutting body <input type="text" style="width: 12%"> Circle <input type="text" style="width: 9%"> mm,Qnty
															</li>
														</ol>
													</li>
												</ol>
											</li>
										</ol>
									</div>
									<div class="col-sm-6">
										<ol class="drug" start="4">
											<li style=";">
												Viola:-<input type="text" style="width: 55%;"> Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												Bone Wax:-<input type="text" style="width: 48%;"> Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												Spongeostan/Gelatin Sponge<input type="text" style="width: 15%;"> Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												Micropore<input type="text" style="width: 15%;"> inch, <input type="text" style="width: 15%"> Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												S.S. Wire<input type="text" style="width: 15%;"> Gauge <input type="text" style="width: 20%"> Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												Arch bar, Size,<input type="text" style="width: 20%;"> Length <input type="text" style="width: 20%">
											</li>
											<li style=";">
												S.S. Mini plate<input type="text" style="width: 15%;"> Hole,  Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												Mini plate Screw, Length:<input type="text" style="width: 25%;">mm, &nbsp;<br>Diameter: <input type="text" style="width: 40%"> Qnty <input type="text" style="width: 15%">
											</li>
											<li>
												Reconstruction Plate:- 
												<select>
													<option>Rt</option>
													<option>Lt</option>
												</select>
												<select>
													<option>Straight</option>
													<option>Angular</option>
												</select>
												<select>
													<option>With condylar head</option>
													<option>Without condylar head</option>
												</select>
											</li>
											<li style=";">
												Screw for reconstruction plate, thickness<input type="text" style="width: 15%;">mm, Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
												Nichi Strip<input type="text" style="width: 80%;">
											</li>
											<li style=";">
												Gauge<input type="text" style="width: 20%;">	
												<select>
													<option>Than</option>
													<option>Sterile pack </option>
												</select>
												Qnty <input type="text" style="width: 15%">
											</li>
											<li style=";">
													
												<select>
													<option>D</option>
													<option>Syrings </option>
												</select>-
												<select>
													<option>50cc</option>
													<option>20cc </option>
													<option>10cc</option>
													<option>5cc </option>
													<option>3cc </option>
												</select>
												Qnty <input type="text" style="width: 15%">
											</li>
											<li>
												Surgical Cap, <input type="text" style="width: 35%">
												Qnty <input type="text" style="width: 15%">
											</li>
											<li>
												Mask(Elastic Type), <input type="text" style="width: 35%">
												Qnty <input type="text" style="width: 15%">
											</li>
											<li>
												Removac Suction <input type="text" style="width: 35%">
												Qnty <input type="text" style="width: 15%">
											</li>
											<li>
												Others <input type="text" style="width: 35%">
												Qnty <input type="text" style="width: 15%">
											</li>
										</ol>
									</div> <!--  -->
									<div class="col-sm-12" style="text-align: center;">
										<h2><strong>INSTRUMENTS</strong></h2>
									</div>
									
									<div class="col-sm-6">
										<ol class="drug">
											<li style=";">Antibiotics:-
												<ol type="a">
													<li style="">
														Inj.
														<input type="text" style="width: 35%; border: 0; border-bottom: 1px dotted; text-align: center;">
														mg IV/1M 
														<input type="text" style="width: 35%;">
														vial
													</li>
													<li>
														Inj.
														<input type="text" style="width: 35%;">
														mg IV/1M 
														<input type="text" style="width: 35%;">
														vial
													</li>
												</ol>
											</li>
											<li>
												Inj.
												<input type="text" style="width: 40%"> mg <input type="text" style="width: 40%"> bottle
											</li>
											<li style=";">Anaigesics:-
												<ol type="a">
													<li style="">
														Inj.
														<input type="text" style="width: 35%; border: 0; border-bottom: 1px dotted; text-align: center;">
														mg 
														<input type="text" style="width: 35%;">
														Amp, IM/IV
													</li>
													<li>
														Inj.
														<input type="text" style="width: 35%;">
														mg
														<input type="text" style="width: 35%;">
														Amp
													</li>
												</ol>
											</li>
											<li>
												Inj.
												<input type="text" style="width: 40%"> mg <input type="text" style="width: 40%"> Amp
											</li>
											<li>
												Inj. Oradexon
												<input type="text" style="width: 60%"> Amp
											</li>
											<li>
												Inj. Dormicum 5mg/5ml
												<input type="text" style="width: 60%"> Amp
											</li>
											<li>
												Eye Drops
												<input type="text" style="width: 80%">
											</li>
										</ol>
									</div>
									<div class="col-sm-6">
										<ol class="drug" start="8">
											<li style=";">
												Nasal Drop:-<input type="text" style="width: 80%;">
												
											</li>
											
											<li style=";">I/v Infusions:-
												<ol type="a">
													<li style="">
														0.9% Normal Saline,
														<input type="text" style="width: 28%;">
														ml 
														<input type="text" style="width: 28%;">
														bag
													</li>
													<li>
														5% DA
														<input type="text" style="width: 35%;">
														ml
														<input type="text" style="width: 35%;">
														bag
													</li>
													
													<li>
														5% DNS
														<input type="text" style="width: 35%;">
														ml
														<input type="text" style="width: 35%;">
														bag
													</li>
													<li>
														Hartman's soln,
														<input type="text" style="width: 32%;">
														ml
														<input type="text" style="width: 32%;">
														bag
													</li>
												</ol>
											</li>
											<li>
												Chlorohexidine Hand rub,
												<input type="text" style="width: 25%"> ml <input type="text" style="width: 25%"> bottle
											</li>
											<li>
												Povidone Iodine 10% soln
												<input type="text" style="width: 25%"> ml <input type="text" style="width: 25%"> bottle
											</li>
											<li>
												Others
												<input type="text" style="width: 60%">
											</li>
											
										</ol>
									</div> <!--  -->
									
								</div>
							</div>
					   	</div>
					</div><!-- /ibox-content -->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        
    
</div><!--/#/wrapper-->


</body>

</html>