<%@ include file="../includes/checkLogin.jsp"%>
<%if(loginDTO!=null){
	if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.ReportView)==true){
		System.out.print("Permission Ok");
	}
}else{
	response.sendRedirect("../");
}%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="disease.DiseaseService" %>
<%@page import="utility.SessionManager"%>
<%@page import="java.util.Calendar" %>
<%@page import="utility.MyConfig"%>
<%@page import="utility.MyUtilityForReport"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String diseaseId = request.getParameter("diseaseId");
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
		startDate="01-04-2015";
	}
}else{
	session.setAttribute("startDate", startDate);
}
String endDate= request.getParameter("endDate");
if(endDate==null){
	endDate=(String)session.getAttribute("endDate");
	if(endDate==null){
		endDate=todays+"-"+(todaysMonth+1)+"-"+todaysYear;
	}
}else{
	session.setAttribute("endDate", endDate);
}

String dateType= request.getParameter("dateType");
if(dateType==null || dateType.length()==0){
	dateType="1";
}
DiseaseService disServ = new DiseaseService();

if(diseaseId==null){%>
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

    <script type="text/javascript">
    function changeThis(divID){
		  if(document.getElementById(divID).style.display=='none'){
			  document.getElementById(divID).style.display='block';
		  }else{
			  document.getElementById(divID).style.display='none';
		  }		  
	  }

	      
    function changeDiagnosis(diseaseId){
		xmlHttp=GetXmlHttpObject();
  	  	if (xmlHttp==null)
  	  	{
  	     	alert("Your browser does not support AJAX!");
  	     	return false;
  	  	}      
		var url = "../Report/DiseaseSpecificReportDiagnosis.jsp?diseaseId="+diseaseId;
  		xmlHttp.onreadystatechange = dataTableChanged;
  		xmlHttp.open("GET", url, true);
  		xmlHttp.send(null);
  		return true;
    }

    function GetXmlHttpObject(){
       var xmlHttp=null;
       try{
          xmlHttp=new XMLHttpRequest();
       }catch(e){
          try{
             xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
          }catch (e){
             xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
          }
        }
        return xmlHttp;
    }	

    function dataTableChanged(){ 
  	  	if (xmlHttp.readyState==4){
  	  	   document.getElementById("diagnosisOnly").innerHTML=xmlHttp.responseText;
  	    }
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
                        <h5>Report Based On Diagnosis</h5>
                    </div><!--/./ibox-title-->
					
                    <div class="ibox-content">
						
						<form action="DiseaseSpecificReportDiagnosisShow.jsp">
							<div class="form-horizontal">
								
								<div class="form-group">
									<%HashMap<Integer, String> diseaseList = disServ.getSysDiseaseInfo(-1);%>
									<label class="col-sm-4 control-label">Disease List</label>
									<div class="col-sm-4">
										<select onchange="changeDiagnosis(this.value)" name="diseaseID" id="diseaseID" class="form-control m-b" >
				                    		<%
				                    		int firstDisease=-1;
				                    		for(int key : diseaseList.keySet()){
				                    			if(firstDisease<0){
				                    				firstDisease=key;
				                    			}%>
				                    			<option value="<%=key %>"><%=diseaseList.get(key) %></option>
					                    	<%}%>
				                    	</select>
									</div>
								</div>
								
								<%
								HashMap<Integer, String> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(firstDisease, MyConfig.diseaseDiagnosis);
								HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(firstDisease, MyConfig.diseaseDiagnosis);
								%>
								<div class="form-group" id="diagnosisOnly">
									<%if(disDiagnosisList.size()>0){%>
										<label class="col-sm-4 control-label">Diagnosis</label>
										<div class="col-sm-4">
											<table class="table" style="font-size: 13px;">
												<%=MyUtilityForReport.generateHTMLWithOutTextBox(disDiagnosisList, disDiagnosisParentByChild, "diagnosisId")%>
											</table>
										</div>
									<%}%>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
										<input type="submit" value="Submit" class="btn btn-primary">
									</div>
								</div>
							</div>
						</form>
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
<%}else{
	HashMap<Integer, String> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(Integer.parseInt(diseaseId), MyConfig.diseaseDiagnosis);
	HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(Integer.parseInt(diseaseId), MyConfig.diseaseDiagnosis);
	if(disDiagnosisList.size()>0){%>
		<label class="col-sm-4 control-label">Diagnosis</label>
		<div class="col-sm-4">
			<table class="table table-hover" style="font-size: 13px;">
		        <%=MyUtilityForReport.generateHTMLWithOutTextBox(disDiagnosisList, disDiagnosisParentByChild, "diagnosisId")%>
			</table>
		</div>
	<%}
}%>