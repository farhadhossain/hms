<%@ include file="../includes/checkLogin.jsp"%>


<%@page import="user.UserDTO"%>
<%@page import="user.UserService"%>
<%@page import="utility.MyConfig"%>
<%@ page import="patient.PatientSurgeryService" %>
<%@ page import="patient.PatientSurgeryDTO" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.ArrayUtils" %>
<%@ page import="utility.StringUtil" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%

	String accountID= request.getParameter("userID");
	String visitID = request.getParameter("visitID")==null?"0":request.getParameter("visitID");
	PatientSurgeryService patientServ = new PatientSurgeryService();
	PatientSurgeryDTO patientSurgeryDTO=patientServ.getSurgeryByPatientID(Integer.parseInt(accountID), Integer.parseInt(visitID));
	StringUtil.removeNullFromObject(patientSurgeryDTO);

	ArrayList<UserDTO> surgeonList = new UserService().getAllUsers("", MyConfig.SurgeonRole);
	ArrayList<UserDTO> assistSurgeonList = new UserService().getAllUsers("", MyConfig.AssistantSurgeonRole);
	ArrayList<UserDTO> anesthetistList = new UserService().getAllUsers("", MyConfig.AnesthetistRole);

	String userID = request.getParameter("userID");
	session.removeAttribute("userID");
	session.setAttribute("userID", userID);

	int surgicalStatus = request.getParameter("surgicalStatus")==null?0: Integer.parseInt(request.getParameter("surgicalStatus"));

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
                <div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-content">
						<html:form action="/UpdatePatientSurgery" onsubmit="return validationResult();">
							<div class="form-horizontal">
                    			
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label">Name of Operations: <span class="red">*</span></label>
                            		<div class="col-sm-6">
                            			<input name="nameOfOp" class="form-control" type="text" value="<%=patientSurgeryDTO.getNameOfOp()==null?"":patientSurgeryDTO.getNameOfOp()%>">
                            		</div>
                            		<div class="col-sm-2"></div>
                    			</div>
                    			<div class="hr-line-dashed"></div>
                    
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label">Date of Operations: <span class="red">*</span></label>
                            		<div class="col-sm-6">
                            			<input type="text" class="form-control" name="dateOfOp" id="dateOfOp" style="width: 100px;" value="<%=patientSurgeryDTO.getDateOfOp()==null?"":patientSurgeryDTO.getDateOfOp()%>">
                            		</div>	
                            		<div class="col-sm-2"></div>
                    			</div>
                    			<div class="hr-line-dashed"></div>
                    			
                    			<div class="form-group" <%if(surgicalStatus==2){%>style="display: none;"<%}%>>
                            		<label class="col-sm-4 control-label">Complications of anesthesia:</label>
                            		<div class="col-sm-6">
                            			<textarea name="compliOfAnesthesia" class="form-control"> <%=patientSurgeryDTO.getCompliOfAnesthesia()%></textarea>
                            		</div>
                            		<div class="col-sm-2"></div>
                    			</div>
					
								<div class="form-group" <%if(surgicalStatus==2){%>style="display: none;"<%}%>>
                            		<label class="col-sm-4 control-label">Post operative complication:</label>
			                        <div class="col-sm-6">
            			               <textarea name="postOperativeCompli" class="form-control"><%=patientSurgeryDTO.getPostOperativeCompli()%></textarea>
                        		    </div>
                            		<div class="col-sm-2"></div>
                    			</div>
						
								<div class="form-group" <%if(surgicalStatus==2){%>style="display: none;"<%}%>>
                		            <label class="col-sm-4 control-label">others</label>
                        		    <div class="col-sm-6">
                            			<textarea name="others" class="form-control"><%=patientSurgeryDTO.getOthers()%></textarea>
                            		</div>
                            		<div class="col-sm-2"></div>
                    			</div>

								<div class="form-group" <%if(surgicalStatus==2){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Operational Notes</label>
									<div class="col-sm-6">
                            			<textarea name="operationalNotes" class="form-control"><%=patientSurgeryDTO.getOperationalNotes()%></textarea>
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
	                                				<option value="<%=surgeonList.get(i).getUserID()%>" <%=ArrayUtils.contains(patientSurgeryDTO.getSurgeonIDList(), surgeonList.get(i).getUserID())?"selected":""%>><%=surgeonList.get(i).getEmployeeName()%></option>
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
	                                				<option value="<%=assistSurgeonList.get(i).getUserID()%>" <%=ArrayUtils.contains(patientSurgeryDTO.getAssistSurgIDList(),assistSurgeonList.get(i).getUserID())?"selected":""%>><%=assistSurgeonList.get(i).getEmployeeName()%></option>
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
	                                				<option value="<%=anesthetistList.get(i).getUserID()%>" <%=ArrayUtils.contains(patientSurgeryDTO.getAnesthetistIDList(),anesthetistList.get(i).getUserID())?"selected":""%>><%=anesthetistList.get(i).getEmployeeName()%></option>
	                                			<%}
	                                		}%>
                             			</select>
									</div>
								</div>
                    			
                    			<div class="form-group">
                            		<label class="col-sm-4 control-label"></label>
                            		<div class="col-sm-6">
	                            		<input type="submit" value="Submit" class="btn btn-primary">
										<input type="hidden" name="surgicalID" id="surgicalID" value="<%=patientSurgeryDTO.getSurgicalID()%>">
										<input type="hidden" name="userID" id="userID" value="<%=userID%>">
										<input type="hidden" name="surgicalStatus" id="surgicalStatus" value="<%=surgicalStatus%>">
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