<%@ page import="patient.PatientService" %>
<%@ page import="status.StatusService" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="patient.PatientDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="prescription.VisitDAO" %>
<%@ page import="prescription.VisitDTO" %>
<%@ page import="utility.StringUtil" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="disease.DiseaseDTO" %>
<%@ page import="disease.form.DiseaseMetaData" %>
<%@ page import="utility.MyUtility" %>
<%@ page import="disease.DiseaseService" %>
<%@ include file="../includes/checkLogin.jsp"%>
<%if(loginDTO!=null){
  if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.ReportView)==true){
    System.out.print("Permission Ok");
  }
}else{
  response.sendRedirect("../");
}%>


<%

  PatientService service = new PatientService();
  HashMap<Integer, String> statusList = new StatusService().getAllStatus();
  ArrayList<PatientDTO> dtoList= new ArrayList<PatientDTO>();
  if(loginDTO!=null){
    dtoList=service.getCurrentWardStatus();
  }
  int size=dtoList.size();
  HashMap<Integer, String> diseaseList = new DiseaseService().getSysDiseaseInfo(-1);
  DiseaseService disServ = new DiseaseService();
%>
<%--
  Created by IntelliJ IDEA.
  User: macintosh
  Date: 1/8/16
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
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


    function changeTreatmentPlan(diseaseId){
      xmlHttp=GetXmlHttpObject();
      if (xmlHttp==null)
      {
        alert("Your browser does not support AJAX!");
        return false;
      }
      var url = "../Report/DiseaseSpecificReportTreatmentPlan.jsp?diseaseId="+diseaseId;
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
        document.getElementById("treatmentPlanOnly").innerHTML=xmlHttp.responseText;
      }
    }

  </script>
  <style>
    .my_disease td{
      font-size: 11px;
    }
  </style>
</head>
<body>
<div id="wrapper">
  <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->

  <div id="page-wrapper" class="gray-bg">
    <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->



    <div class="wrapper wrapper-content animated fadeInRight">
      <table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
        <thead>
        <tr>
          <th>Sr. No</th>
          <th>Ward /<br/> Cabin</th>
          <th>Bed no /<br/> Cabin no</th>
          <th>Patient</th>
          <th>Date of admission</th>
          <th>Diagonosis</th>
          <th>Image</th>
          <th>Surgical Status</th>
          <th>Responsible bed doctor</th>

        </tr>
        </thead>
        <tbody>
        <%for(int i=0;i<size;i++){
          PatientDTO dto = dtoList.get(i);%>
        <tr>
          <td><%=(i+1) %></td>
          <td><%= dto.getWordNumber()%></td>
          <td><%= dto.getBedNumber() +" / "+ dto.getCabinNumber() %></td>
          <td>
            Name: <%=dto.getName() %><br>
            Age: <%=dto.getAge() %><br>
            Phone: <%=dto.getTelephoneNum()%><br>
          </td>
          <td><%= dto.date_of_adm==null?"":dto.date_of_adm %></td>
          <td class="my_disease">
            <table>
                  <%
                      int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getAccId());
                      VisitDTO visitDTO = new VisitDAO().getVisitById(currentVisitId);
                      StringUtil.removeNullFromObject(visitDTO);
                  %>
                  <%Iterator<Integer> it = visitDTO.diseaseTypeHash.iterator();
                      while(it.hasNext()) {
                          int diseaseTypeKey=it.next();
                          DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(dto.getAccId(), currentVisitId, diseaseTypeKey);
                          if (patCurDisDTO.patDiagonosisId.size()>0){
                              HashMap<Integer, DiseaseMetaData> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(diseaseTypeKey, MyConfig.diseaseDiagnosis);
                              HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(diseaseTypeKey, MyConfig.diseaseDiagnosis);
                  %>
                              <tr>
                                  <td style="padding: 10px 0;"><strong><%=diseaseList.get(diseaseTypeKey)%></strong></td>
                              </tr>
                              <%=MyUtility.generateHTML(disDiagnosisList, disDiagnosisParentByChild, "diagnosisId", patCurDisDTO.patDiagonosisId, patCurDisDTO, false)%>
                      <%}}%>
            </table>

          </td>
          <td>
            <%if(dto.imageName!=null && dto.imageName.length()>0){%>
            <input type="image" src="<%=MyConfig.filePath+dto.imageName%>" height="120" width="120">
            <%}%>
          </td>
          <td>
            <%if(dto.getSurgicalStatus()==0){%>
             Waiting for Admission
            <%}else if(dto.getSurgicalStatus()==1){%>
             Pending Surgical Plan
            <%}else if(dto.getSurgicalStatus()==2){%>
             Pre Operative
            <%}else if(dto.getSurgicalStatus()==3){%>
            Post Operative
            <%}else if(dto.getSurgicalStatus()==4){%>
            Discahrged
            <%}%>
          </td>
          <td><%=dto.referredBy==null?"":dto.referredBy %></td>

        </tr>
        <%}%>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
