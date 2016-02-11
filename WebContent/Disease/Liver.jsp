<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="disease.DiseaseDTO"%>
<%@page import="disease.FollowUpDTO"%>
<%@ page import="utility.MyUtility" %>
<%@ page import="disease.form.DiseaseMetaData" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<%
String userID = request.getParameter("userID");
if(userID==null){
	userID=(String)session.getAttribute("userID");
}else{
	session.setAttribute("userID", userID);
}

String diseaseID = request.getParameter("diseaseID");
if(diseaseID==null){
	diseaseID=(String)session.getAttribute("diseaseID");
}else{
	session.setAttribute("diseaseID", diseaseID);
}

boolean editAndView=true;

DiseaseService disServ = new DiseaseService();

DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(Integer.parseInt(userID), Integer.parseInt(diseaseID)); 
ArrayList<FollowUpDTO> followUpList=disServ.getFollowUpReport(Integer.parseInt(userID), Integer.parseInt(diseaseID));

HashMap<Integer, DiseaseMetaData> disHistoryList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseHistory);
HashMap<Integer, String> disHistoryParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseHistory);

HashMap<Integer, DiseaseMetaData> disSymptomList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseID), MyConfig.diseaseSymptom);
HashMap<Integer, String> disSymptomParentByChild = new HashMap<Integer, String> ();

int id = 1;

String actionNameFollowUp="/NewFindingsLiverPatient";

%>

<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title><!--

<link rel="stylesheet" type="text/css" href="../Assets/Fonts/fontface.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/united-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/main.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/sidebar-menu.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/style.css" />

    -->
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
	  });

	  function changeThis(divID){
		  if(document.getElementById(divID).style.display=='none'){
			  document.getElementById(divID).style.display='block';
		  }else{
			  document.getElementById(divID).style.display='none';
		  }		  
	  }
	  
    </script>
    
</head>

<body>
    <!--*********************Body part Starts from here************************-->
    <!--***********************************************************************-->
    <div id="wrapper">
    	<%@ include file="../includes/leftNav.jsp"%><!--sidebar-->
		<div id="page-wrapper" class="gray-bg">
        	<%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->

        	<div class="wrapper wrapper-content animated fadeInRight">
            	<div class="row">
            		<div class="col-lg-12">
                		<%if(session.getAttribute(SessionManager.PatientNewFindings)!=null){%>
	                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientNewFindings)%></div>
	                        <%session.removeAttribute(SessionManager.PatientNewFindings);
	                    }%>
						<div style="clear:both"></div>
   				 			<div class="ibox float-e-margins">
                    			<div class="ibox-title">
                        			<h5>Liver </h5>
                    			</div><!--/./ibox-title-->
                   				<div class="ibox-content">
									<html:form action="/PatientLiver">
										<div class="form-horizontal">
											<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												
												<%@ include file="../includeDiseaseParts/includeHistory.jsp"%>
                          						<%id++;%>
                          						
                          						<%@ include file="../includeDiseaseParts/includeSymptom.jsp"%>
                          						<%id++;%>
				                         	 				                        
					                        	<%@ include file="../Disease/specialNoteAndComplication.jsp"%>
					                        
					                            <%@ include file="../includeDiseaseParts/includeNAD.jsp"%>  
                       						</div>
                       					
			                        		<input type="submit" value="Submit" class="btn btn-primary" <%if(editAndView==false){%>style="display: none;"<%}%>>
											<input type="hidden" name="userId" id="userId" value="<%=userID%>">
										    <input type="hidden" name="diseaseId" id="diseaseId" value="<%=diseaseID%>">
          								</div>
               						</html:form>
                					<div style="color:both">&nbsp;</div>
                			
                					<%@ include file="../Disease/followup.jsp"%>
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
