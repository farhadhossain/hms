<%@ page import="java.util.Calendar" %>
<%@ include file="../includes/checkLogin.jsp"%>

<%
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
%>

<html>
    <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
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
                       <br />
                       <h>Log Book for <%=loginDTO.employeeName%></h>
                    </div>
                </div><!--/./row wrapper border-bottom white-bg page-heading-->
                <br />
                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10"><br />
                        <form class="navbar-form navbar-right" method="post" action="/Others/LogBookPrint.jsp">
                            <label>From Date</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="startDate" id="startDate" value="<%=startDate %>" style="width: 150px;">
                            </div>
                            <label>To Date</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="endDate" id="endDate" value="<%=endDate %>" style="width: 150px;">
                            </div>
                            <%--<label>Search By</label>
                            <div class="input-group">
                                <select class="form-control" name="searchBy">
                                    <option value="0">None</option>
                                    <option value="1">Patient Name</option>
                                    <option value="2">Ticket Number</option>
                                    <option value="3">Phone Number</option>
                                    <option value="4">Registration Number</option>
                                </select>
                            </div>--%>
                            <div class="input-group">
                                <%--<input type="text" placeholder="Name, PIN, Phone..." class="form-control" name="nameTicketPhone" id="top-search">--%>
                                <span class="input-group-btn">
                                    <button class="btn btn-primary" type="submit"><span class="fa fa-print"></span></button>
                                </span>
                            </div>
                        </form>
                    </div>
                 </div>
            </div>
        </div>
    </body>
</html>