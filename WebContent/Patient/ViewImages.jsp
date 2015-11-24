<%@ include file="../includes/checkLogin.jsp"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="utility.SessionManager" %>
<%@ page import="patient.PatientDTO" %>
<%@ page import="patient.PatientService" %>
<%@ page import="status.StatusService" %>
<%@ page import="utility.MyConfig" %>
<%@ page import="utility.OthersInfo" %>

<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%

String accountID= request.getParameter("accountID");
PatientService service = new PatientService();
ArrayList<String> imageList= new ArrayList<String>();
if(loginDTO!=null){
	imageList=service.getPatientImagesList(Integer.parseInt(accountID));
}
int size=imageList.size();

%>


<html >
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title>

<link rel="stylesheet" type="text/css" href="../Assets/Styles/myStyle.css">
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
	<link href="../Assets/Date/datepicker.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="../Assets/Scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/sidebar-menu.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
    <script src="../Assets/Date/js/datepicker.js"></script>
    
    
   
</head>

<body>
	<div id="wrapper">
    
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->
	
        <div id="page-wrapper" class="gray-bg">
        
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
                                
        <div class="row wrapper border-bottom white-bg page-heading">
        	<div class="col-lg-10">
            	<h2>View All Patients</h2>
            </div>
            <div class="col-lg-2"></div>
        </div><!--/./row wrapper border-bottom white-bg page-heading-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                
	                <div style="clear:both"></div>
	   				<div class="ibox float-e-margins">
	   					<div class="ibox-content">
             				<%if(size>0){%>
				                <div style="clear:both"></div>
            					<div class="table-responsive">
					                <%for(int i=0;i<size;i++){%>
					                	<input type="image" src="<%=MyConfig.filePath+imageList.get(i)%>" height="400" width="400"><br>
					                <%}%>
					            </div>    
                			<%}%>
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