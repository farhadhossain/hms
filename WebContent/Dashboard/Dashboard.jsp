<%@ include file="../includes/checkLogin.jsp"%>
<%@ page language="Java" %>
<%@page import="dashboard.DashBoardService"%>
<%@page import="dashboard.DashBoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html lang="en">
<%
DashBoardDTO dto = new DashBoardService().getSystemInfo();
%>
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dashboard :: <%=SessionManager.title%></title>
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

	<!-- Plugin of this page -->
	<script type='text/javascript' src='../Assets/NewAssets/dashboardJS/bootstrap/bootstrap-datepicker.js'></script>                
        <script type="text/javascript" src="../Assets/NewAssets/dashboardJS/owl/owl.carousel.min.js"></script>                 
        
        <script type="text/javascript" src="../Assets/NewAssets/dashboardJS/daterangepicker/daterangepicker.js"></script>
    <!-- Page-Level Scripts -->
    <script>
       $('#myModal').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  });
	  $('#myModal2').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  })
    </script>
    
    <script type="text/javascript"
          src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }">
   </script>
   
   <script src="assets/js/jquery-1.11.0.min.js"></script>
	<script>$.noConflict();</script>
	<!-- Bottom scripts (common) -->
	<script src="assets/js/gsap/main-gsap.js"></script>
	<script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/neon-api.js"></script>
	<script src="assets/js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>


	<!-- Imported scripts on this page -->
	<script src="assets/js/jvectormap/jquery-jvectormap-europe-merc-en.js"></script>
	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/rickshaw/vendor/d3.v3.js"></script>
	<script src="assets/js/rickshaw/rickshaw.min.js"></script>
	<script src="assets/js/raphael-min.js"></script>
	<script src="assets/js/morris.min.js"></script>
	
	


	<!-- JavaScripts initializations and stuff -->
	<script src="assets/js/neon-custom.js"></script>
	
   
   <style type="text/css">
   .wid {
   		background: transparent linear-gradient(to bottom, #1B1E24 0%, #101215 100%) repeat scroll 0% 0%;
   		font-family: "Open Sans",sans-serif;
		font-size: 12px;
   
   }
   .widget.widget-item-icon {
    padding: 10px 0px 5px;
        padding-top: 10px;
        padding-right: 0px;
        padding-bottom: 5px;
        padding-left: 0px;
}
	.widget {
    width: 100%;
    float: left;
    margin: 0px 0px 20px;
    list-style: outside none none;
    text-decoration: none;
    box-shadow: 0px 1px 1px 0px rgba(0, 0, 0, 0.2);
    color: #FFF;
    border-radius: 0px;
    
    min-height: 120px;
    position: relative;
    
}

.widget.widget-item-icon .widget-item-left {
    border-right: 1px solid rgba(0, 0, 0, 0.1);
    margin-right: 10px;
    padding-right: 10px;
}
.widget.widget-item-icon .widget-item-left, .widget.widget-item-icon .widget-item-right {
    width: 70px;
    padding: 20px 0px;
    text-align: center;
}
.widget .widget-item-left {
    margin-left: 10px;
    float: left;
    width: 100px;
}
.widget {
    list-style: outside none none;
    color: #FFF;
}

.widget.widget-item-icon .widget-data {
    padding-left: 90px;
}
.widget .widget-data .widget-title, .widget .widget-data-left .widget-title, .widget .widget-data .widget-subtitle, .widget .widget-data-left .widget-subtitle, .widget .widget-data .widget-int, .widget .widget-data-left .widget-int, .widget .widget-data .widget-big-int, .widget .widget-data-left .widget-big-int {
    text-align: left;
}
.widget .widget-int {
    font-size: 32px;
    line-height: 40px;
    font-weight: bold;
    font-family: arial;
}
.widget .widget-title, .widget .widget-subtitle, .widget .widget-int, .widget .widget-big-int {
    width: 100%;
    float: left;
   
}

.widget .widget-data .widget-title, .widget .widget-data-left .widget-title, .widget .widget-data .widget-subtitle, .widget .widget-data-left .widget-subtitle, .widget .widget-data .widget-int, .widget .widget-data-left .widget-int, .widget .widget-data .widget-big-int, .widget .widget-data-left .widget-big-int {
    text-align: left;
}
.widget .widget-title {
    font-size: 16px;
    font-weight: 600;
    margin-bottom: 5px;
    line-height: 20px;
    text-transform: uppercase;
}
.widget .widget-title, .widget .widget-subtitle, .widget .widget-int, .widget .widget-big-int {
    width: 100%;
    float: left;
    
}

.widget.widget-info {
    background: #1CAF9A none repeat scroll 0% 0% !important;
}
.widget.widget-padding-sm, .widget.widget-item-icon {
    padding: 10px 0px 5px;
}
.widget {
    width: 100%;
    float: left;
    margin: 0px 0px 20px;
    list-style: outside none none;
    text-decoration: none;
    box-shadow: 0px 1px 1px 0px rgba(0, 0, 0, 0.2);
    color: #FFF;
    border-radius: 0px;
    padding: 15px 10px;
    min-height: 120px;
    position: relative;
}
html * {
    outline: medium none !important;
}
* {
    box-sizing: border-box;
}
.widget .widget-big-int {
    font-size: 42px;
    line-height: 45px;
    font-weight: 300;
}
.widget .widget-title, .widget .widget-subtitle, .widget .widget-int, .widget .widget-big-int {
    width: 100%;
    float: left;
    text-align: center;
}
html * {
    outline: medium none !important;
}
* {
    box-sizing: border-box;
}
.widget {
    list-style: outside none none;
    color: #FFF;
}
element {
}
.widget .widget-big-int {
    font-size: 42px;
    line-height: 45px;
    font-weight: 300;
}
.widget .widget-title, .widget .widget-subtitle, .widget .widget-int, .widget .widget-big-int {
    width: 100%;
    float: left;
    text-align: center;
}
.widget .widget-subtitle {
    font-size: 12px;
    font-weight: 400;
    margin-bottom: 5px;
    line-height: 15px;
    color: #EEE;
}
.widget .widget-title, .widget .widget-subtitle, .widget .widget-int, .widget .widget-big-int {
    width: 100%;
    float: left;
    text-align: center;
}
.widget .widget-controls a.widget-control-right {
    right: 10px;
    top: 10px;
}
.widget .widget-controls a {
    position: absolute;
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 17px;
    color: #FFF;
    border: 1px solid #FFF;
    border-radius: 20%;
    transition: all 200ms ease 0s;
    opacity: 0.4;
}
html * {
    outline: medium none !important;
}
a {
    color: #428BCA;
    text-decoration: none;
}
a {
    background-color: transparent;
}
* {
    box-sizing: border-box;
}
.widget .widget-buttons {
    float: left;
    width: 100%;
    text-align: center;
    padding-top: 3px;
    margin-top: 5px;
    border-top: 1px solid rgba(0, 0, 0, 0.1);
}
   
   </style>
   
   <style type="text/css">
   
   .tile-stats.tile-red {
    background: #00A65A none repeat scroll 0% 0%;
}
.tile-stats {
    position: relative;
    display: block;
    
    padding: 20px;
    margin-bottom: 10px;
    overflow: hidden;
    border-radius: 5px;
    transition: all 300ms ease-in-out 0s;
}
*, *::before, *::after {
    box-sizing: border-box;
}
.tile-stats.tile-red .icon {
    color: white;
}
.page-container .tile-stats .icon {
    bottom: 35px;
}
.tile-stats .icon {
    color: white;
    position: relative;
	right: -55%;
	bottom: -45px;
    z-index: 1;
}
.tile-stats .icon i {
    font-size: 100px;
    line-height: 0;
    margin: 0px;
    padding: 0px;
    vertical-align: bottom;
}
*, *::before, *::after {
    box-sizing: border-box;
}
.tile-stats.tile-red .num, .tile-stats.tile-red h3, .tile-stats.tile-red p {
    color: #FFF;
}
.tile-stats .num {
    font-size: 38px;
    font-weight: bold;
}
.tile-stats .num, .tile-stats h3, .tile-stats p {
    position: relative;
    
    z-index: 5;
    margin: 0px;
    padding: 0px;
}
   .tile-stats .icon i {
    font-size: 100px;
    line-height: 0;
    margin: 0px;
    padding: 0px;
    vertical-align: bottom;
}
*, *::before, *::after {
    box-sizing: border-box;
}
*, *::before, *::after {
    box-sizing: border-box;
}
*, *::before, *::after {
    box-sizing: border-box;
}
*, *::before, *::after {
    box-sizing: border-box;
}
.tile-stats.tile-red .icon {
    color: rgba(0, 0, 0, 0.1);
}
.tile-stats .icon {
    color: rgba(0, 0, 0, 0.1);
}
body {
    font-family: "Helvetica Neue",Helvetica,"Noto Sans",sans-serif,Arial,sans-serif;
    font-size: 12px;
    line-height: 1.42857;
    color: #949494;
}
body {
    font-family: "Helvetica Neue",Helvetica,"Noto Sans",sans-serif,Arial,sans-serif;
    font-size: 12px;
    line-height: 1.42857;
    color: #949494;
}
body {
    font-family: "Noto Sans",sans-serif;
}
body {
    font-family: "Helvetica Neue",Helvetica,"Noto Sans",sans-serif,Arial,sans-serif;
    font-size: 12px;
    line-height: 1.42857;
    color: #949494;
}
body {
    font-family: "Helvetica Neue",Helvetica,"Noto Sans",sans-serif,Arial,sans-serif;
    font-size: 12px;
    line-height: 1.42857;
    color: #949494;
}
html {
    font-size: 62.5%;
}
html {
    font-size: 62.5%;
}
html {
    font-size: 62.5%;
}
html {
    font-size: 62.5%;
}
html {
    font-family: sans-serif;
}
   </style>
   
</head>


<body>

	<!--*********************Modal Starts from here*****************************-->
    <!--************************************************************************-->
    
    
    
    
    
    
    <!--*********************Body part Starts from here************************-->
    <!--***********************************************************************-->
    <div id="wrapper">
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->

        <div id="page-wrapper" class="gray-bg">
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
        
        <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>Department of Oral & Maxillofacial Surgery, BSMMU</h2>
                    <ol class="breadcrumb">
                        <li class="active">
                            <strong>Dashboards</strong>                        
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">                
                </div>
        </div><!--/./row wrapper border-bottom white-bg page-heading-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">

             
                
             	<div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Dashboard</h5>
                    </div><!--/./ibox-title-->
                    <div class="ibox-content">
	                   <div class="row">
	                 		<div class="col-sm-3 col-xs-6">
								<a href="<%=request.getContextPath()+"/Patient/SearchPatient.jsp"%>">
									<div class="tile-stats tile-red" style="background: #303641;">
										<div class="icon"><i class="fa fa-user fa-5x"></i></div>
										<div class="num" data-start="0" data-end="30" data-duration="1500" data-delay="0"><%=dto.todaysTotal %></div>
							
										<h3>Admitted Today</h3>
										<p>&nbsp;</p>
									</div>
								</a>
							</div>
	                 		
							<div class="col-sm-3 col-xs-6">
								<a>
									<div class="tile-stats tile-red">
										<div class="icon"><i class="fa fa-wheelchair fa-5x"></i></div>
										<div class="num" data-start="0" data-end="150" data-duration="1800" data-delay="600"><%=dto.total %></div>
							
										<h3>Total Patient</h3>
										<p>&nbsp;</p>
									</div>
								</a>
							</div>
							
							<div class="col-sm-3 col-xs-6">
								<a>
									<div class="tile-stats tile-red">
										<div class="icon"><i class="fa fa-user-md fa-5x"></i></div>
										<div class="num" data-start="0" data-end="50" data-postfix="" data-duration="1500" data-delay="1100"><%=dto.totalDoctor %></div>
							
										<h3>Total Doctor</h3>
										<p>&nbsp;</p>
									</div>
								</a>
							</div>
							
							<div class="col-sm-3 col-xs-6">
								<a href="credentials.jsp">
									<div class="tile-stats tile-red">
										<div class="icon"><i class="fa fa-heartbeat fa-5x"></i></div>
										<div class="num">&nbsp;</div>
							
										<h3>Credentials</h3>
										<p>&nbsp;</p>
									</div>
								</a>
							</div>		
	  												
						
             
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