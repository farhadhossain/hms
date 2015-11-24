<%@ include file="../includes/checkLogin.jsp"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.*" %>
<%@ page import="utility.MyConfig" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="utility.SessionManager" %>
<%@ page import="extraction.ExtractionDTO" %>
<%@ page import="extraction.ExtractionService" %>
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

ExtractionService service = new ExtractionService();
ArrayList<ExtractionDTO> dtoList= new ArrayList<ExtractionDTO>();
if(loginDTO!=null){
	dtoList=service.getPatientExtractionDTOList(startDate, endDate);
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
            	<h2>Duty Nurse</h2>
			    
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
                    
	                <div style="clear:both"></div>
	   				<div class="ibox float-e-margins">
	   					<div class="ibox-content">
	                		<div class="dataTables_length">
                                <div class="col-lg-12"  style="padding:0px;">
		                            <form class="navbar-form navbar-right" method="post" action="ExtractionList.jsp">
		                            	<label>From Date</label>
		                            	<div class="input-group">
		                            	    <input type="text" class="form-control" name="startDate" id="startDate" value="<%=startDate %>" style="width: 100px;">
		                                </div>
			                        	<label>To Date</label>
		                            	<div class="input-group">
		                            	    <input type="text" class="form-control" name="endDate" id="endDate" value="<%=endDate %>" style="width: 100px;">
		                                </div>
			                        	<div class="input-group">
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
					                    	<th>Patient Info</th>
					                    	<th>Status</th>
					                    	<th>Referred By(From MinorOT/Outdoor)</th>
					                    	<th>Action</th>
					                    </tr>
					                </thead>
					                <tbody>
										<%for(int i=0;i<size;i++){
					                        ExtractionDTO dto = dtoList.get(i);%> 	 	
					                    	<tr>
					                    		<td><%=dto.getUniqIDExtraction() %></td>
					                    		<td><%=dto.patientInfo%></td>
					                    		<td>MinorOT/Outdoor</td>
					                    		<td><%=dto.doctorInfo %></td>
					                    		<td><a href="../Patient/PatientExtraction.jsp?accountID=<%=dto.getUserId()%>">Payment Process</a></td>
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