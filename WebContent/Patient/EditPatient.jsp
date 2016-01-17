<%@ include file="../includes/checkLogin.jsp"%>
<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientEdit)==false){
		response.sendRedirect("../");
	}
}
%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@page import="utility.MyConfig"%>
<%@page import="user.UserDTO"%>
<%@page import="user.UserService"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%

String accountID= request.getParameter("accountID");
PatientService patientServ = new PatientService();
PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));
String surgicalStatus = request.getParameter("surgicalStatus")==null?"":request.getParameter("surgicalStatus");
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

   

    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>

    <!-- Page-Level Scripts -->
    <script>
    function showReferedDeptDetails(deptId){
       	if(deptId==5){
       		document.getElementById('otherDeptRefDetails').style.display = 'block';
       	}else{
       		document.getElementById('otherDeptRefDetails').style.display = 'none';
       	}	
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
                
                <div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Edit Patient Info</h5> 
                    </div><!--/./ibox-title-->
					
					<div class="ibox-content">
						<html:form action="/EditPatient" >
							<div class="form-horizontal">
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Name <span class="red">*</span></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="name" value="<%=patientDTO.getName()%>"/>
									</div>
								</div>
								
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Age <span class="red">*</span></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="age" value="<%=patientDTO.getAge() %>"/>
									</div>
								</div>
								
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Sex <span class="red">*</span></label>
									<div class="col-sm-4">
										<input class="form-control" name="sex" type="text" readonly="readonly" value="<%=patientDTO.getSex() %>"/>
									</div>
								</div>
								
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Ticket Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="ticketNumber" value="<%=patientDTO.getTicketNumber()==null?"":patientDTO.getTicketNumber()%>">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Admitted Registration Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="regNumber" value="<%=patientDTO.getRegNumber()==null?"":patientDTO.getRegNumber() %>">
									</div>
								</div>
								
								<div class="form-group" <%if(patientDTO.getDeptId()!=3){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Ward Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="wordNumber" value="<%=patientDTO.getWordNumber()==null?"":patientDTO.getWordNumber() %>">
									</div>
								</div>
								
								<div class="form-group" <%if(patientDTO.getDeptId()!=3){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Bed Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="bedNumber" value="<%=patientDTO.getBedNumber()==null?"":patientDTO.getBedNumber() %>">
									</div>
								</div>
								
								<div class="form-group" <%if(patientDTO.getDeptId()!=3){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Cabin Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="cabinNumber" value="<%=patientDTO.getCabinNumber()==null?"":patientDTO.getCabinNumber() %>">
									</div>
								</div>
								
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Blood group</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="bloodGroup" value="<%=patientDTO.getBloodGroup()==null?"":patientDTO.getBloodGroup() %>">
									</div>
								</div>
								
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Telephone Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="telephoneNum"value="<%=patientDTO.getTelephoneNum()==null?"":patientDTO.getTelephoneNum() %>" />
									</div>
								</div>
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Present Address</label>
									<div class="col-sm-4">
										<textarea class="form-control" rows="2" name="presentAdd" value="<%=patientDTO.getPresentAdd() %>" ></textarea>
									</div>
								</div>
							
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Permanent Address</label>
									<div class="col-sm-4">
										<textarea class="form-control" rows="2" name="permanentAdd" value="<%=patientDTO.getPermanentAdd() %>" ></textarea>
									</div>
								</div>
								<%
								ArrayList<UserDTO> doctorForBedList = new UserService().getAllUsers(null, MyConfig.DoctorForBed);
							 	%>
							 	<div class="form-group" <%if(patientDTO.getDeptId()==MyConfig.deptIndoor){%>style="display: block;"<%}else{%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Select Bed Doctor</label>
									<div class="col-sm-4">
										<select name="bedDoctorID">
											<option value="-1"><%="None"%></option>
											<%
											if(doctorForBedList.size()>0){
	                                			for(int i=0;i<doctorForBedList.size();i++){%>
	                                				<option value="<%=doctorForBedList.get(i).getUserID()%>"><%=doctorForBedList.get(i).getEmployeeName()%></option>
	                                			<%}
	                                		}%>
                             			</select>
									</div>
								</div>
							 	
								<div class="form-group" <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Refered To</label>
									<div class="col-sm-4">
										<select class="form-control m-b" name="deptId" id="deptId" onchange="showReferedDeptDetails(this.value)">
											<%
											HashMap<Integer, String> statusList = new StatusService().getAllStatus();
											for (int key : statusList.keySet()) {%>
												<option <%if(patientDTO.getDeptId()==key){%>selected="selected"<%}%> value="<%=key%>"><%=statusList.get(key)%></option>
											<%}%>	
										</select>
									</div>
								</div>
								
								<div class="form-group" id="otherDeptRefDetails" <%if(patientDTO.getDeptId()==5){%>style="display: none;"<%}%> <%if(surgicalStatus.equals("1")){%>style="display: none;"<%}%>>
									<label class="col-sm-4 control-label">Refered To Other Department Details</label>
									<div class="col-sm-4">
										<select class="form-control m-b" name="otherDeptRefId" id="otherDeptRefId">
											<%for (int key : MyConfig.refToOtherDeptDetails.keySet()) {%>
												<option <%if(patientDTO.getOtherDeptRefId()==key){%>selected="selected"<%}%> value="<%=key%>"><%=MyConfig.refToOtherDeptDetails.get(key)%></option>
											<%}%>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
										<input type="submit" value="Submit" class="btn btn-primary">
										<input type="hidden" name="accId" id="accId" value="<%=accountID%>">
										<input type="hidden" name="surgicalStatus" id="surgicalStatus" value="<%=request.getParameter("surgicalStatus")%>">
									</div>
								</div>
						   	</div>
						</html:form>
					</div><!-- /ibox-content -->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
    </div><!--/./gray-bg-->
</div><!--/#/wrapper-->


</body>

</html>