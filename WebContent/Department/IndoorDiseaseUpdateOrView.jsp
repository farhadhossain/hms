<%@ include file="../includes/checkLogin.jsp"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.HashMap" %>
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
String accountID= request.getParameter("accountID");
String generalOnly= request.getParameter("generalOnly");

if (accountID == null) {
	//accountID = SessionManager.params.get("accountID");
	//generalOnly = SessionManager.params.get("generalOnly");
	accountID = (String)session.getAttribute("accountID");
	generalOnly = (String)session.getAttribute("generalOnly");
}
else{
	//SessionManager.params.put("accountID", accountID);
	//SessionManager.params.put("generalOnly", generalOnly);
	session.setAttribute("accountID", accountID);
	session.setAttribute("generalOnly", generalOnly);
}

boolean viewGeneral=false;
if(generalOnly!=null && generalOnly.length()>0){
	viewGeneral=true;
}

PatientService service = new PatientService();
HashMap<Integer, String> specificDiseaseList = new DiseaseService().getSysDiseaseInfo(1);
HashMap<Integer, String> generalDiseaseList = new DiseaseService().getSysDiseaseInfo(2);

PatientDTO patDTO = service.getPatientDTO(Integer.parseInt(accountID));
boolean isAnyGeneralDisease=false;
boolean isAnySpecificDisease=false;
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

    <script type="text/javascript" src="../Assets/Scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/sidebar-menu.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
    
   
</head>

<body>
	<div id="wrapper">
    
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->
	
        <div id="page-wrapper" class="gray-bg">
        
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
                                
        <div class="row wrapper border-bottom white-bg page-heading">
        	<div class="col-lg-10">
            	<h2>Update/View Disease Info</h2>
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
	             			<div style="clear:both"></div>
	            			<div class="table-responsive">
	            	
	            				<html:form action="/UpdateIndoorDiseaseInfo">
	            					<div <%if(viewGeneral){%>style="display: block;"<%}else{%>style="display: none;"<%}%>>
		            					<label style="color: #081810;"><h4><b>General Disease:</b></h4></label>
			            				<table class="table" style="font-size: 13px;">
											<tr>
												<%
												int count=0;
												for (int key : generalDiseaseList.keySet()) {
													if(count%3==0){%>
															</tr>
															<tr>
													<%}%>
													<td style="border: none;">
														<input <%if(patDTO.diseaseTypeHash.contains(key)){%>checked="checked"<%}%> type="checkbox" name="diseaseType" value="<%=key%>"> 
															<%if(patDTO.diseaseTypeHash.contains(key)){%>
																<a href="<%=OthersInfo.getPageNameByID(Integer.parseInt(accountID), key)%>"><%=generalDiseaseList.get(key)%> </a>
															<%}else{%> 
																<%=generalDiseaseList.get(key)%>
															<%}%>
													</td>
													<%count++;
												}%>
											</tr>
										</table>
									</div>
									
		            				<div <%if(viewGeneral==false){%>style="display: block;"<%}else{%>style="display: none;"<%}%>>
			            				<div style="clear:both"></div>
			            				<label style="color: #081810;"><h4><b>Specific Disease:</b></h4></label>
			            				<table class="table" style="font-size: 13px;">
											<tr>
												<%count=0;
												for (int key : specificDiseaseList.keySet()) {
													if(count%3==0){%>
															</tr>
															<tr>
													<%}%>
													<td style="border: none;">
														<input <%if(patDTO.diseaseTypeHash.contains(key)){%>checked="checked"<%}%> type="checkbox" name="diseaseType" value="<%=key%>"> 
														<%if(patDTO.diseaseTypeHash.contains(key)){%>
																<a href="<%=OthersInfo.getPageNameByID(Integer.parseInt(accountID), key)%>"><%=specificDiseaseList.get(key)%> </a>
															<%}else{%> 
																<%=specificDiseaseList.get(key)%>
															<%}%>
													</td>
													<%count++;
												}%>
											</tr>
										</table>
									</div>
									
									<div style="clear:both"></div>
									<div>
										<input type="submit" value="Update" class="btn btn-primary">
										<input type="hidden" name="accId" value="<%=accountID %>">
									</div>
	            				</html:form>
					        </div>
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