<%@ include file="../includes/checkLogin.jsp"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%
String userID = request.getParameter("userID");
%>
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
	
	<script type="text/javascript">
    function isNumber(s)
    {
      for(var i=0;i<s.length;i++)
      {  
          if(s.charAt(i)=='1'||s.charAt(i)=='2'|| s.charAt(i)=='3'||s.charAt(i)=='4'|| s.charAt(i)=='5'||s.charAt(i)=='6'|| s.charAt(i)=='7'||s.charAt(i)=='8'|| s.charAt(i)=='9'|| s.charAt(i)=='0'|| s.charAt(i)=='.')
            continue;
          else 
            break;   
      }
      if(i==s.length)
          return true;
      else
          return false;      
    }
    function validationResult(){
    	var f = document.forms[0];
    	if(f.accAndInjuries.value==null || f.accAndInjuries.value==''){
          alert("Please enter Accident and injuries");
          return false;
        }
    	if(f.nameOfOp.value==null || f.nameOfOp.value==''){
          alert("Please enter Name of Operations");
          return false;
        }
    	if(f.dateOfOp.value==null || f.dateOfOp.value==''){
          alert("Please enter Date of Operation");
          return false;
        }
    	
    	
        return true;
    }
    
    </script>
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
        
        
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
            		<div class="row border-bottom">
				            <nav class="navbar navbar-static-top navbar-inverse" role="navigation" style="margin-bottom: 0">
				                <div class = "container-fluid">
				                	<ul class="nav nav-tabs">
				                	
				               			<li role="presentation"><a href="../PatientOthers/PatientPersonalHistory.jsp?userID=<%=userID%>">Social & Personal</a></li>
								  		<li role="presentation"><a href="../PatientOthers/Investigation.jsp?userID=<%=userID%>">Investigation</a></li>
									  	<li role="presentation"><a href="../PatientOthers/PatinetPhysicalExamination.jsp?userID=<%=userID%>">Physical Examination</a></li>
									  	<li role="presentation"><a href="../PatientOthers/PreAnestheticEvaluation.jsp?userID=<%=userID%>">Pre-Anesthetic Evaluation</a></li>
									  	<li role="presentation"><a href="../PatientOthers/GeneralSurvey.jsp?userID=<%=userID%>">General Survey</a></li>
										<li role="presentation" class="dropdown">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Surgery<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PastSergHistory/SearchPastSergHistory.jsp?userID=<%=userID%>">Surgical History</a></li>
									      		<li role="presentation"><a href="../PastSergHistory/AddPastSergHistory.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  	<li role="presentation" class="dropdown active">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Drugs<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PatientOthers/PatientDrugAnMedication.jsp?userID=<%=userID%>">Drug History</a></li>
									      		<li role="presentation" class="active"><a href="../PatientOthers/AddMedication.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  
									</ul>
				                </div>
				                
				            </nav><!--/./navbar navbar-static-top-->
		            
        				</div><!--/./row border-bottom-->
            		
                 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Add New Medication</h5>
                    </div><!--/./ibox-title-->
                    <div class="ibox-content">
						<html:form action="/NewMedication" onsubmit="return validationResult();">
							<div class="form-horizontal">
                    	        <div class="form-group">
		                            <label class="col-sm-4 control-label">Drugs <span class="red">*</span></label>
		                            <div class="col-sm-6">
		                	            <input name="drugs" class="form-control" type="text">
		                            </div>
		                    	</div>
		                    
		                    	<div class="form-group">
		                            <label class="col-sm-4 control-label">Date Started <span class="red">*</span></label>
		                            <div class="col-sm-6">
		                        	    <input name="dateStarted" class="form-control" type="text">
		                            </div>
		                    	</div>
		                    
		                    	<div class="form-group">
		                            <label class="col-sm-4 control-label">Dose</label>
		                            <div class="col-sm-6">
		                    	        <input name="dose" class="form-control" type="text">
		                            </div>
		                        </div>
							
								<div class="form-group">
		                            <label class="col-sm-4 control-label">ADR if any</label>
		                            <div class="col-sm-6">
		                        	    <input name="adr" class="form-control" type="text">
		                            </div>
		                    	</div>
							
								<div class="form-group">
		                            <label class="col-sm-4 control-label">others</label>
		                            <div class="col-sm-6">
		                        	    <input name="others" class="form-control" type="text">
		                            </div>
		                    	</div>
		                    
		                    	<div class="form-group">
		                            <label class="col-sm-4 control-label"></label>
		                            <div class="col-sm-6">
			                            <input type="submit" value="Submit" class="btn btn-primary">
										<input type="hidden" name="userId" id="userId" value="<%=userID%>">
		                            </div>
		                    	</div>
                   			</div>
                		</html:form>
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