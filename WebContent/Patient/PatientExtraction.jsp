<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="extraction.ExtractionService"%>
<%@page import="extraction.ExtractionDTO"%>
<%@page import="utility.MyConfig"%><html>
<%@page import="patient.PatientService"%>
<%@page import="patient.PatientDTO"%>
<%@ page import="java.util.HashMap" %>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
if(accountID==null){
	accountID=(String)session.getAttribute("accountID");
	if(accountID==null){
		accountID="-1";
	}
}else{
	session.setAttribute("accountID", accountID);
}
System.out.println("accountID: "+accountID);
PatientService patientServ = new PatientService();
PatientDTO patientDTO=patientServ.getPatientDTO(Integer.parseInt(accountID));
ExtractionService extracServ = new ExtractionService();
HashMap<Integer, String> extractionNameByID = extracServ.getExtractionListByID();
HashMap<Integer, Integer> extractionTakaByID = extracServ.getExtractionTakaByID();
ExtractionDTO extractionDTO = extracServ.getExtractionDTOByID(Integer.parseInt(accountID));

boolean isUncheckedHide=false;
if(loginDTO.getRoleID()==MyConfig.dutyNurseRole){
	isUncheckedHide=true;
}

System.out.println("isUncheckedHide: "+isUncheckedHide);

%>
<head>
 <link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/datepicker.css">
 <link rel="stylesheet" type="text/css" href="../Assets/NewAssets/css/font-awesome.css">
 <script type="text/javascript" src="../Assets/js/jquery-1.11.1.js"></script>
 <script type="text/javascript" src="../Assets/js/bootstrap.js"></script>
 <script type="text/javascript" src="../Assets/js/bootstrap-datepicker.js"></script>
	<script>
	function updateTotalAmount(checkbox, amount){
		var textbox = document.getElementById('totalTk').value;
		if (checkbox.checked){
			textbox=parseInt(textbox)+parseInt(amount);
	    }else{
	    	textbox=parseInt(textbox)-parseInt(amount);
	    }
		document.getElementById('totalTk').value=textbox;
	}

	function updateTotalAmountFromTextBox(name, amount, previousStore){
		var textbox = document.getElementById(name).value;
		var textboxPrevious = document.getElementById(previousStore).value;
		var lengthDiff = parseInt(textbox.length)-parseInt(textboxPrevious.length);
		var previousSum = document.getElementById('totalTk').value;
		document.getElementById('totalTk').value=parseInt(previousSum)+(parseInt(lengthDiff)*parseInt(amount));
		document.getElementById(previousStore).value=textbox;
	}
	
    </script>	
</head>
<body>
<div class="container" style="padding-top: 40px;">
  	<div class="row">
  		<div class="container-fluid">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
				   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					 <img class="img-responsive" src="../Assets/Images/banner.jpg">
				   </div>
				</div>
				<br>
				<div class="panel panel-default">
					<html:form action="/NewExtraction">
						<div class="panel-body_1 table-responsive ">
							<table>
								<tr><td>Extraction<br><br></td></tr>
								
								<%for(int key : extractionNameByID.keySet()){
									String value1="", value2="", value3="", value4="";
									String tkValue1="", tkValue2="", tkValue3="", tkValue4="";
									
									if(key==1){
										value1=extractionDTO.getExtractionId1_1();
										value2=extractionDTO.getExtractionId1_2();
										value3=extractionDTO.getExtractionId1_3();
										value4=extractionDTO.getExtractionId1_4();
									}else if(key==2){
										value1=extractionDTO.getExtractionId2_1();
										value2=extractionDTO.getExtractionId2_2();
										value3=extractionDTO.getExtractionId2_3();
										value4=extractionDTO.getExtractionId2_4();
									}else if(key==3){
										value1=extractionDTO.getExtractionId3_1();
										value2=extractionDTO.getExtractionId3_2();
										value3=extractionDTO.getExtractionId3_3();
										value4=extractionDTO.getExtractionId3_4();
									}
									
					     			if(key>3){
					     				break;
					     			}
					     			if(isUncheckedHide==true && extractionDTO.patCurExtractionList.contains(key)==false){
					     				continue;
					     			}
					     			%>
									<tr>
										<td>
											<input type="checkbox" <%if(extractionDTO.patCurExtractionList.contains(key)){%>checked="checked"<%}%> name="extractionId" value="<%=key %>" style="margin-left: 40px;">&emsp;<%=extractionNameByID.get(key) %>
												<table border="1" rules="all" frame="void" style="margin-left: 80px;">
													<tr><td style="margin: 2pt;"><input type="text" value="<%=value1 %>" class="input-small" name="extractionId<%=key%>_1" id="extractionId<%=key%>_1" style="text-align: right" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_1', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_1')"></td><td><input type="text" value="<%=value2 %>" class="input-small" name="extractionId<%=key%>_2" id="extractionId<%=key%>_2" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_2', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_2')"></td></tr>
													<tr><td style="margin: 2pt;"><input type="text" value="<%=value3 %>" class="input-small" name="extractionId<%=key%>_3" id="extractionId<%=key%>_3" style="text-align: right" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_3', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_3')"></td><td><input type="text" value="<%=value4 %>" class="input-small" name="extractionId<%=key%>_4" id="extractionId<%=key%>_4" onkeyup="updateTotalAmountFromTextBox('extractionId<%=key%>_4', <%=extractionTakaByID.get(key)%>, 'amount<%=key%>_4')"></td></tr>
												</table>	
											<input type="hidden" id="amount<%=key%>_1" value="<%=value1 %>">
											<input type="hidden" id="amount<%=key%>_2" value="<%=value2 %>">
											<input type="hidden" id="amount<%=key%>_3" value="<%=value3 %>">
											<input type="hidden" id="amount<%=key%>_4" value="<%=value4 %>">
										</td>
									</tr>
								<%}%>
								
								<tr><td><br>Excision<br><br></td></tr>
					     		<%for(int key : extractionNameByID.keySet()){
					     			if(isUncheckedHide==true && extractionDTO.patCurExtractionList.contains(key)==false){
					     				continue;
					     			}
					     			if(key<4){
					     				continue;
					     			}%>
									<tr>
										<td>
											<input type="checkbox" <%if(extractionDTO.patCurExtractionList.contains(key)){%>checked="checked"<%}%> name="extractionId" value="<%=key %>" onchange="updateTotalAmount(this, <%=extractionTakaByID.get(key)%>)" style="margin-left: 40px;">&emsp;<%=extractionNameByID.get(key) %>
										</td>
									</tr>
								<%}%>
								
								<tr><td><br><br>Please deposit <input type="text" readonly="readonly" value="<%=extractionDTO.getTotalTk() %>" name="totalTk" id="totalTk">Taka<br></td></tr>
								<tr><td>Payment registration number <input type="text" name="paymentRegNumber" value="<%=extractionDTO.getPaymentRegNumber() %>"><br></td></tr>
								<tr>
									<td>
										<input type="submit" value="Save"><input type="button" value="Print" onClick="window.print()">
										<input type="hidden" name="uniqIDExtraction" value="<%=extractionDTO.getUniqIDExtraction() %>">
										<input type="hidden" name="userId" value="<%=accountID %>">
									</td>
								</tr>
								
					     	</table>
					   	</div>
					</html:form>
				</div>
     		</div>
   		</div>
  	</div>
</div>
</body>
</html>