<%@ include file="../includes/checkLogin.jsp"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="patient.PatientService" %>
<%@page import="utility.SessionManager"%>
<%@page import="java.util.Calendar" %>
<%@page import="utility.MyConfig"%>
<%@page import="utility.MyUtilityForReport"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String nameOfYear = request.getParameter("nameOfYear");
Calendar date = Calendar.getInstance();
int todaysYear=date.get(Calendar.YEAR);
if(nameOfYear==null || nameOfYear.length()<4){
	nameOfYear=todaysYear+"";
}
HashMap<Integer, String> listInfo = new PatientService().getPatientInfoAllDeptByYear(Integer.parseInt(nameOfYear));
%>
<html >
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title>

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

  	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
  	
  	
  	<script type="text/javascript">
	  	google.load('visualization', '1', {packages: ['corechart', 'bar']});
		google.setOnLoadCallback(drawMultSeries);
	
	function drawMultSeries() {
	      var data = new google.visualization.DataTable();
	      data.addColumn('string', 'Month');
	      data.addColumn('number', 'Indoor');
	      data.addColumn('number', 'Outdoor');
	      data.addColumn('number', 'Emergency');
	      data.addColumn('number', 'Minor OT');
	
	      data.addRows([ 
	        ['Jan', <%=listInfo.get(1)==null?"0,0,0,0":listInfo.get(1) %>],
	        ['Feb', <%=listInfo.get(2)==null?"0,0,0,0":listInfo.get(2) %>],
	        ['Mar', <%=listInfo.get(3)==null?"0,0,0,0":listInfo.get(3) %>],
	        ['Apr', <%=listInfo.get(4)==null?"0,0,0,0":listInfo.get(4) %>],
	        ['May', <%=listInfo.get(5)==null?"0,0,0,0":listInfo.get(5) %>],
	        ['Jun', <%=listInfo.get(6)==null?"0,0,0,0":listInfo.get(6) %>],
	        ['Jul', <%=listInfo.get(7)==null?"0,0,0,0":listInfo.get(7) %>],
	        ['Aug', <%=listInfo.get(8)==null?"0,0,0,0":listInfo.get(8) %>],
	        ['Sep', <%=listInfo.get(9)==null?"0,0,0,0":listInfo.get(9) %>],
	        ['Oct', <%=listInfo.get(10)==null?"0,0,0,0":listInfo.get(10) %>],
	        ['Nov', <%=listInfo.get(11)==null?"0,0,0,0":listInfo.get(11) %>],
	        ['Dec', <%=listInfo.get(12)==null?"0,0,0,0":listInfo.get(12) %>]
	      ]);
	
	      var options = {
	        title: 'Patient vs Unit',
	        hAxis: {
	          title: 'Month'
	        },
	        vAxis: {
	          title: 'No. of Patients'
	        }
	      };
	
	      var chart = new google.visualization.ColumnChart(
	        document.getElementById('bar_chart'));
	
	      chart.draw(data, options);
	    }
    </script>
</head>



<body>

    <div id="wrapper">
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->

        <div id="page-wrapper" class="gray-bg">
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
        
        
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Disease Specific Report</h5>
                    </div><!--/./ibox-title-->
					
                    <div class="ibox-Content">
						<div class="dataTables_length">
                      		<div class="col-lg-12"  style="padding:0px;">
		                    	<form class="navbar-form navbar-right" method="post" action="AllDeptCompareReport.jsp">
		                        	<label>Select Year</label>
		                        	<div class="input-group">
		                        		<select name="nameOfYear">
					                        <%for(int i=2014;i<=todaysYear;i++){%>
					                        	<option <%if(i==Integer.parseInt(nameOfYear)){%>selected="selected"<%}%> value="<%=i %>"><%=i %></option>
					                        <%}%>
					                    </select>
			                        </div>
			                        <div class="input-group">
			                        	<button type="submit" class="btn btn-primary">Search</button>
		                        	</div>
	                            </form>
		                   	</div>
                        	<div style="clear:both"></div>
                    	</div>   
						<div class="row">
							<div class="col-lg-12">
								<div id="bar_chart" style="height: 600px; width: 100%;"></div>
							</div>
						</div>
					
					</div>
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
    </div><!--/./gray-bg-->
	</div><!--/#/wrapper-->
  </body>
</html>