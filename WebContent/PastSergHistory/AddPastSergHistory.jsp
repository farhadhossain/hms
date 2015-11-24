<%@ include file="../includes/checkLogin.jsp"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="user.UserDTO"%>
<%@page import="user.UserService"%>
<%@page import="utility.MyConfig"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%

ArrayList<UserDTO> surgeonList = new UserService().getAllUsers("", MyConfig.SurgeonRole);
ArrayList<UserDTO> assistSurgeonList = new UserService().getAllUsers("", MyConfig.AssistantSurgeonRole);
ArrayList<UserDTO> anesthetistList = new UserService().getAllUsers("", MyConfig.AnesthetistRole);

String userID = request.getParameter("userID");
session.removeAttribute("userID");
session.setAttribute("userID", userID);

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
    <script type="text/javascript" src="../Assets/Scripts/mir-billing-script.js"></script>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../Assets/NewAssets/js/bootstrap.min.js"></script>
    <script src="../Assets/NewAssets/js/jquery.metisMenu.js"></script>
    <script src="../Assets/NewAssets/js/jquery.slimscroll.min.js"></script>

   
	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
 	<link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/font-awesome.css">
 	
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
	<script src="../Assets/Date/js/datepicker.js"></script>
    	
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

	$(function(){
		$('.datepicker').datepicker();
	});

	$(function(){
        $(document).ready(function() {
           
           $('#dateOfOp').datepicker({
                'format': 'yyyy-mm-dd'
            }).on('changeDate', function(e){
                $('#dateOfOp').datepicker('hide');
            });
            
       });
   });

   
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
										<li role="presentation" class="dropdown active">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Surgery<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PastSergHistory/SearchPastSergHistory.jsp?userID=<%=userID%>">Surgical History</a></li>
									      		<li role="presentation" class="active"><a href="../PastSergHistory/AddPastSergHistory.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  	<li role="presentation" class="dropdown">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Drugs<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PatientOthers/PatientDrugAnMedication.jsp?userID=<%=userID%>">Drug History</a></li>
									      		<li role="presentation"><a href="../PatientOthers/AddMedication.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  
									</ul>
				                </div>
				                
				            </nav><!--/./navbar navbar-static-top-->
		            
        				</div><!--/./row border-bottom-->
                
                <div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-content">
						<html:form action="/NewSurgicalHistory" onsubmit="return validationResult();">
							<div class="form-horizontal">
                    			
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label">Name of Operations: <span class="red">*</span></label>
                            		<div class="col-sm-6">
                            			<input name="nameOfOp" class="form-control" type="text">
                            		</div>
                            		<div class="col-sm-2"></div>
                    			</div>
                    			<div class="hr-line-dashed"></div>
                    
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label">Date of Operations: <span class="red">*</span></label>
                            		<div class="col-sm-6">
                            			<input type="text" class="form-control" name="dateOfOp" id="dateOfOp" style="width: 100px;">
                            		</div>	
                            		<div class="col-sm-2"></div>
                    			</div>
                    			<div class="hr-line-dashed"></div>
                    
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label">Accident and injuries:</label>
                            		<div class="col-sm-6">
                            			<input class="form-control" type="text" name="accAndInjuries">
                            		</div>
                    			</div>
                    			<div class="hr-line-dashed"></div>
                    			
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label">Complications of anesthesia:</label>
                            		<div class="col-sm-6">
                            			<input name="compliOfAnesthesia" class="form-control" type="text">
                            		</div>
                            		<div class="col-sm-2"></div>
                    			</div>
					
								<div class="form-group">
                            		<label class="col-sm-4 control-label">Post operative complication:</label>
			                        <div class="col-sm-6">
            			               <input name="postOperativeCompli" class="form-control" type="text">
                        		    </div>
                            		<div class="col-sm-2"></div>
                    			</div>
						
								<div class="form-group">
                		            <label class="col-sm-4 control-label">others</label>
                        		    <div class="col-sm-6">
                            			<input name="others" class="form-control" type="text">
                            		</div>
                            		<div class="col-sm-2"></div>
                    			</div>	
                    			
                    			<div class="form-group">
									<label class="col-sm-4 control-label">Select Surgeon</label>
									<div class="col-sm-4">
										<select name="surgeonIDList" multiple="multiple">
											<%
	                                		if(surgeonList.size()>0){
	                                			for(int i=0;i<surgeonList.size();i++){%>
	                                				<option value="<%=surgeonList.get(i).getUserID()%>"><%=surgeonList.get(i).getEmployeeName()%></option>
	                                			<%}
	                                		}%>
                             			</select>
									</div>
								</div>
                    			
                    			<div class="form-group">
									<label class="col-sm-4 control-label">Select Assistant Surgeon</label>
									<div class="col-sm-4">
										<select name="assistSurgIDList" multiple="multiple">
											<%
	                                		if(assistSurgeonList.size()>0){
	                                			for(int i=0;i<assistSurgeonList.size();i++){%>
	                                				<option value="<%=assistSurgeonList.get(i).getUserID()%>"><%=assistSurgeonList.get(i).getEmployeeName()%></option>
	                                			<%}
	                                		}%>
                             			</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Select Anesthetist</label>
									<div class="col-sm-4">
										<select name="anesthetistIDList" multiple="multiple">
											<%
	                                		if(anesthetistList.size()>0){
	                                			for(int i=0;i<anesthetistList.size();i++){%>
	                                				<option value="<%=anesthetistList.get(i).getUserID()%>"><%=anesthetistList.get(i).getEmployeeName()%></option>
	                                			<%}
	                                		}%>
                             			</select>
									</div>
								</div>
                    			
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label"></label>
                            		<div class="col-sm-6">
	                            		<input type="submit" value="Submit"/ class="btn btn-primary">
										<input type="hidden" name="userID" id="userID" value="<%=userID%>">
                            		</div>
                            		<div class="col-sm-2"></div>
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