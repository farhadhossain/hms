<%@ page import="java.util.Calendar" %>
<%@ page import="user.UserService" %>
<%@ page import="user.UserDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="../includes/checkLogin.jsp" %>

<%
    Calendar date = Calendar.getInstance();
    String[] nameOfMonth = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October",
            "November", "December"};
    int numberOfMonth = date.get(Calendar.MONTH);
    int todays = date.get(Calendar.DAY_OF_MONTH);
    int todaysMonth = date.get(Calendar.MONTH);
    int todaysYear = date.get(Calendar.YEAR);

    String startDate = request.getParameter("startDate");
    if (startDate == null) {
        startDate = (String) session.getAttribute("startDate");
        if (startDate == null) {
            startDate = todaysYear + "-" + (todaysMonth + 1) + "-" + todays;
        }
    } else {
        session.setAttribute("startDate", startDate);
    }

    String endDate = request.getParameter("endDate");
    if (endDate == null) {
        endDate = (String) session.getAttribute("endDate");
        if (endDate == null) {
            endDate = todaysYear + "-" + (todaysMonth + 1) + "-" + todays;
        }
    } else {
        session.setAttribute("endDate", endDate);
    }

    UserService service = new UserService();
    ArrayList<UserDTO> dtoList = service.getAllUsers("", -1);
%>

<html ng-app="hms">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <html:base/>
    <title>Log Book</title>

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


	<script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
	<script type="text/javascript" src="../Assets/js/toastr.min.js"></script>
 	<script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/ui-bootstrap-tpls-0.13.1.min.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/module.js"></script>
	<script type="text/javascript" src="../Assets/js/angular/controllers/logBookController.js"></script>
    <script src="../Assets/Date/js/datepicker.js"></script>

    <script type="text/javascript">
        $(function () {
            $(document).ready(function () {

                $('#startDate').datepicker({
                    'format': 'yyyy-mm-dd'
                }).on('changeDate', function (e) {
                    $('#startDate').datepicker('hide');
                });

            });
        });

    </script>
    <script type="text/javascript">
        $(function () {
            $(document).ready(function () {

                $('#endDate').datepicker({
                    'format': 'yyyy-mm-dd'
                }).on('changeDate', function (e) {
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
        <%@ include file="../includes/topNav.jsp" %>
        <!--top NavBar-->

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <br/>
                <h>Log Book for <%=loginDTO.employeeName%>
                </h>
            </div>
        </div>
        <!--/./row wrapper border-bottom white-bg page-heading-->
        <br/>

        <div class="row wrapper border-bottom white-bg page-heading" ng-controller="LogBookController">
            <div class="col-lg-10"><br/>

                <form class="navbar-form navbar-right" method="post" action="/Others/LogBookPrint.jsp" target="_blank">
                    <label>From Date</label>

                    <div class="input-group">
                        <input type="text" class="form-control" name="startDate" id="startDate" value="<%=startDate %>"
                               style="width: 150px;">
                    </div>
                    <label>To Date</label>

                    <div class="input-group">
                        <input type="text" class="form-control" name="endDate" id="endDate" value="<%=endDate %>"
                               style="width: 150px;">
                    </div>
                    <label>Program</label>
                    <div class="input-group">
                        <select class="form-control" name="program" ng-model="programSelected" ng-change="programChanged(programSelected)">
                            <option value="Residency">Residency</option>
                            <option value="Medical Officer">Medical Officer</option>
                            <option value="PGT">PGT</option>
                            <option value="DDS">DDS</option>
                            <option value="FCPS">FCPS</option>
                        </select>
                    </div>
                    <label>Employee</label>

                    <div class="input-group">
                        <select class="form-control" name="userId">
                            <option value="">Select One</option>
                            <option ng-repeat="user in users" value="{{user.userID}}">{{user.employeeName}}[{{user.studentId}}]</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <%--<input type="text" placeholder="Name, PIN, Phone..." class="form-control" name="nameTicketPhone" id="top-search">--%>
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="submit"><span class="fa fa-print"></span>
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>