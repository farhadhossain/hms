<%@ include file="../includes/checkLogin.jsp"%>
<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientSearch)==false){
		response.sendRedirect("../");
	}
}
%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.*" %>
<%@ page import="utility.MyConfig" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="utility.SessionManager" %>
<%@ page import="patient.PatientDTO" %>
<%@ page import="patient.PatientService" %>
<%@ page import="status.StatusService" %>
<%@ page import="disease.DiseaseService" %>
<%@ page import="utility.OthersInfo" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>

<%
int TOTAL_PAGES=1;
int PAGE_SIZE=30;

Calendar date = Calendar.getInstance();
String[] nameOfMonth = { "January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October",
        "November", "December" };
int numberOfMonth = date.get(Calendar.MONTH);
int todays=date.get(Calendar.DAY_OF_MONTH);
int todaysMonth=date.get(Calendar.MONTH);
int todaysYear=date.get(Calendar.YEAR);

String startDate= request.getParameter("startDate");
if(startDate==null){
	startDate=(String)session.getAttribute("startDate");
	if(startDate==null){
		startDate=todaysYear+"-"+(todaysMonth+1)+"-"+todays;
	}
}else{
	session.setAttribute("startDate", startDate);
}

String endDate= request.getParameter("endDate");
if(endDate==null){
	endDate=(String)session.getAttribute("endDate");
	if(endDate==null){
		endDate=todaysYear+"-"+(todaysMonth+1)+"-"+todays;
	}
}else{
	session.setAttribute("endDate", endDate);
}
String searchBy= request.getParameter("searchBy");
String nameTicketPhone= request.getParameter("nameTicketPhone");

PatientService service = new PatientService();
HashMap<Integer, String> statusList = new StatusService().getAllStatus();
HashMap<Integer, String> diseaseList = new DiseaseService().getSysDiseaseInfo(-1);
ArrayList<PatientDTO> dtoList= new ArrayList<PatientDTO>();
if(loginDTO!=null){
	dtoList=service.getPatientDTOs(startDate, endDate, searchBy, nameTicketPhone, loginDTO.getRoleID());
}
int size=dtoList.size();

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
    
    <script type="text/javascript">
    $(function(){
        $(document).ready(function() {
           
           $('#startDate').datepicker({
                'format': 'yyyy-mm-dd'
            }).on('changeDate', function(e){
                $('#startDate').datepicker('hide');
            });
            
       });
   });

    </script>
    <script type="text/javascript">
    $(function(){
        $(document).ready(function() {
           
           $('#endDate').datepicker({
                'format': 'yyyy-mm-dd'
            }).on('changeDate', function(e){
                $('#endDate').datepicker('hide');
            });
            
       });
   });

    </script>
    
   
</head>

<body>
	<div id="wrapper">
    
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->
	
        <div id="page-wrapper" class="gray-bg">
        
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
                                
        <div class="row wrapper border-bottom white-bg page-heading">
        	<div class="col-lg-10">
            	<h2>View All Patients
           	       	<%if(loginDTO!=null){
                    	if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientAdd)==true ){
                    		if(loginDTO.getRoleID()==MyConfig.frontDeskRole){%>
	                     		<a href='../Patient/NewPatient.jsp' class="btn-primary">
	                     			<button type="button" class="btn btn-primary navbar-right" style="margin-left:0px; margin-bottom:5px;">
		                 				Add New Patient
									</button>
								</a>
                     		<%}
                    	}
               		}%>		
			    </h2>
			    
            </div>
            <div class="col-lg-2"></div>
        </div><!--/./row wrapper border-bottom white-bg page-heading-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                
	                <%if(session.getAttribute(SessionManager.PatientAdd)!=null){%>
	                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientAdd)%></div>
	                        <%session.removeAttribute(SessionManager.PatientAdd);
	                    }%>
	                    <%if(session.getAttribute(SessionManager.PatientRespratory)!=null){%>
	                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientRespratory)%></div>
	                        <%session.removeAttribute(SessionManager.PatientRespratory);
	                    }%>
	                    <%if(session.getAttribute(SessionManager.PatientCardiovascular)!=null){%>
	                        <div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientCardiovascular)%></div>
	                        <%session.removeAttribute(SessionManager.PatientCardiovascular);
	                    }%>
	                
	                <div style="clear:both"></div>
	   				<div class="ibox float-e-margins">
	   					<div class="ibox-content">
	                		<div class="dataTables_length">
                                <div class="col-lg-12"  style="padding:0px;">
		                            <form class="navbar-form navbar-right" method="post" action="SearchPatient.jsp">
		                            	<label>From Date</label>
		                            	<div class="input-group">
		                            	    <input type="text" class="form-control" name="startDate" id="startDate" value="<%=startDate %>" style="width: 100px;">
		                                </div>
			                        	<label>To Date</label>
		                            	<div class="input-group">
		                            	    <input type="text" class="form-control" name="endDate" id="endDate" value="<%=endDate %>" style="width: 100px;">
		                                </div>
			                        	<label>Search By</label>
			                        	<div class="input-group">
			                        		<select class="form-control" name="searchBy">
						                        <option value="0">None</option>
						                        <option value="1">Patient Name</option>
						                        <option value="2">Ticket Number</option>
						                        <option value="3">Phone Number</option>
						                        <option value="4">Registration Number</option>
						                    </select>
				                        </div>
				                        <div class="input-group">
		                                    <input type="text" placeholder="Name, PIN, Phone..." class="form-control" name="nameTicketPhone" id="top-search">
		                                    <span class="input-group-btn">
		                                        <button class="btn btn-primary" type="submit"><span class="fa fa-search"></span></button>
		                                    </span>
		                                </div>   
		                            </form>
		                            
		                            
		                            
		                        </div>
		                        
		                        <div style="clear:both"></div>
                    	</div>   
            			<%if(size>0){%>
			                <div style="clear:both"></div>
           					<div class="table-responsive">
				                <table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
					                <thead>
					                    <tr>
					                    	<th>Sr. No</th>
					                    	<th>Patient</th>
					                    	<th>Image</th>
					                    	
					                    	<th>Status</th>
					                    	
					                    	<%if(loginDTO.getRoleID()!=MyConfig.frontDeskRole){%>
					                    		<th>Disease Images</th>
					                    	<%}%>
					                    	
					                    	<%if(loginDTO.getRoleID()==MyConfig.AnesthetistRole || loginDTO.getRoleID()==MyConfig.AssistantSurgeonRole || loginDTO.getRoleID()==MyConfig.SurgeonRole || loginDTO.getRoleID()==MyConfig.minorOTOrOutdoorRole){%>
					                    		<th>Referred By</th>
					                    	<%}else{%>
					                    		<th>Added By</th>
					                    	<%}%>
					                    	
					                    	<%if(loginDTO.getRoleID()!=MyConfig.frontDeskRole){%>
					                    		<th>Action</th>
					                    	<%}%>
					                    	
					                    </tr>
					                </thead>
					                <tbody>
										<%for(int i=0;i<size;i++){
					                        PatientDTO dto = dtoList.get(i);%> 	 	
					                    	<tr>
					                    		<td><%=(i+1) %></td>
					                    		<td>
					                    			Name: <a href="../Patient/EditPatient.jsp?accountID=<%=dto.getAccId()%>"><%=dto.getName()%></a><br>
					                    			Phone: <%=dto.getTelephoneNum()%><br>
					                    			Ticket: <%=dto.getTicketNumber()%><br>
					                    			Date: <%=dto.getDateOfRec() %>
					                    		</td>
					                    		<td>
					                    			<%if(dto.imageName!=null && dto.imageName.length()>0){%>
					                    				<input type="image" src="<%=MyConfig.filePath+dto.imageName%>" height="120" width="120">
					                    			<%}%>
					                    		</td>
							                    <td>
							                    	<%=statusList.get(dto.getDeptId())%>
							                    	<%if(dto.getDeptId()==MyConfig.deptIndoor){%>
														<%if(dto.getSurgicalStatus()==0){%>
															<br>[ Waiting for Admission ]
														<%}else if(dto.getSurgicalStatus()==1){%>
															<br>[ Pending Surgical Plan ]
														<%}else if(dto.getSurgicalStatus()==2){%>
															<br>[ Pre Operative ]
														<%}else if(dto.getSurgicalStatus()==3){%>
															<br>[ Post Operative ]
														<%}else if(dto.getSurgicalStatus()==4){%>
															<br>[ Discahrged ]
														<%}%>
													<%}%>
							                    	<%if(dto.getDeptId()==5){%>
							                    		<br><%=MyConfig.refToOtherDeptDetails.get(dto.getOtherDeptRefId())%>
							                    	<%}%>
							                    </td>
							                    
							                    <%if(loginDTO.getRoleID()!=MyConfig.frontDeskRole){%>
									            	<td>
								                    	<a href="ViewImages.jsp?accountID=<%=dto.getAccId()%>">View Images</a><br>
								                       	<a href="AddImage.jsp?accountID=<%=dto.getAccId()%>" style="color: blue; font-weight: bold;">(+)Add Images</a>
								                    </td>
							                    <%}%>
							                    
							                    <%if(loginDTO.getRoleID()==MyConfig.AnesthetistRole || loginDTO.getRoleID()==MyConfig.AssistantSurgeonRole || loginDTO.getRoleID()==MyConfig.SurgeonRole || loginDTO.getRoleID()==MyConfig.minorOTOrOutdoorRole){%>
						                    		<td><%=dto.referredBy %></td>
						                    	<%}else{%>
						                    		<td><%=dto.addedBy %></td>
						                    	<%}%>
							                    
							                    <%if(loginDTO.getRoleID()!=MyConfig.frontDeskRole){%>
									                <td>
									                	<%if(loginDTO.getRoleID()==MyConfig.DoctorForBed || loginDTO.getRoleID()==MyConfig.deptIndoor || loginDTO.getRoleID()==MyConfig.SurgeonRole || loginDTO.getRoleID()==MyConfig.AssistantSurgeonRole || loginDTO.getRoleID()==MyConfig.AnesthetistRole){%>
								                       		<a href="../PatientOthers/PatientPersonalHistory.jsp?userID=<%=dto.getAccId()%>">Systematic Evaluation</a><br>
								                       	<%}%>
								                    	
								                    	<%if(dto.getDeptId()==MyConfig.deptOutdoorOrMinorOT){%>
						                        			<a href="../Patient/PatientExtraction.jsp?accountID=<%=dto.getAccId()%>">Treatment Plan</a><br>
						                        		<%}else if(dto.getDeptId()==MyConfig.deptIndoor){%>
						                        			<a href="../Department/IndoorDiseaseUpdateOrView.jsp?accountID=<%=dto.getAccId()%>&generalOnly=true">General Diseases</a><br>
						                        			<a href="../Department/IndoorDiseaseUpdateOrView.jsp?accountID=<%=dto.getAccId()%>">Specific Diseases</a><br>
						                        		<%}else if(dto.getDeptId()==MyConfig.deptEmergency){%>
						                        			<a href="/Disease/MaxillofacialEmergency.jsp?userID=<%=dto.getAccId()%>&diseaseID=27">Emergency</a><br>
						                        		<%}%>
						                        		
						                        		<%if(loginDTO.getRoleID()==MyConfig.DoctorForBed || loginDTO.getRoleID()==MyConfig.deptIndoor || loginDTO.getRoleID()==MyConfig.SurgeonRole || loginDTO.getRoleID()==MyConfig.AssistantSurgeonRole || loginDTO.getRoleID()==MyConfig.AnesthetistRole){%>
								                       		<a target="_blank" href="../Report/PatientInfoAll.jsp?accountID=<%=dto.getAccId()%>">View Findings</a><br>
								                    	<%}%>

														<a target="_blank" href="../Patient/Prescription.jsp?accountID=<%=dto.getAccId()%>">Prescription</a><br>

						                        		<a href="../Patient/EditPatient.jsp?accountID=<%=dto.getAccId()%>">Referred To</a><br>
						                        		
						                        		<%if(loginDTO.getRoleID()==MyConfig.DoctorForBed || loginDTO.getRoleID()==MyConfig.deptIndoor || loginDTO.getRoleID()==MyConfig.SurgeonRole || loginDTO.getRoleID()==MyConfig.AssistantSurgeonRole || loginDTO.getRoleID()==MyConfig.AnesthetistRole){%>
								                       		<%if(dto.getSurgicalStatus()==0){%>
															  <a  href="../Patient/EditPatient.jsp?accountID=<%=dto.getAccId()%>&surgicalStatus=1">Admit the Patient</a><br>
														    <%}else if(dto.getSurgicalStatus()==1){%>
														  	  <a  href="../Patient/Surgery.jsp?userID=<%=dto.getAccId() %>&surgicalStatus=2">Make Pre-Operative</a>
															<%}else if(dto.getSurgicalStatus()==2){%>
																<a  href="../Patient/Surgery.jsp?userID=<%=dto.getAccId() %>&surgicalStatus=3">Make Post-Operative</a>
															<%}else if(dto.getSurgicalStatus()==3){%>
														        <a href="../Patient/Discharge.jsp?accountID=<%=dto.getAccId()%>&surgicalID=0">Make Discharge</a>
															<%}%>
									                    <%}%>
						                        	</td>
						                        <%}%>
					                    	</tr>
					                    <%}%>  
					                </tbody>
				                </table>
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