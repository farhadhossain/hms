<%@ page import="disease.form.DiseaseMetaData" %>
<%@ page import="utility.MyConfig" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="utility.MyUtility" %>
<%@ page import="patientOthers.PatientOthersService" %>

<%
  HashMap<Integer, DiseaseMetaData> patIntraOralExaminationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfPreAnEvIntraOralExamination);
  HashMap<Integer, String> disIntraOralExaminationListParentByChild = patOthersServ.getParentByChildWithInfoId(MyConfig.infoHistoryOfPreAnEvIntraOralExamination);
  HashMap<Integer, DiseaseMetaData> patExtraOralExaminationList = patOthersServ.getSocialAndPersonalHistoryDetailsByID(MyConfig.infoHistoryOfPreAnEvExtraOralExamination);
  HashMap<Integer, String> disExtraOralExaminationListParentByChild = patOthersServ.getParentByChildWithInfoId(MyConfig.infoHistoryOfPreAnEvExtraOralExamination);
  String name="";
%>

<div class="col-sm-offset-1">
   <label>Intra oral examination</label>  <br/>
   <div class="col-sm-offset-1">
     <table>
      <%=MyUtility.generateHTML(patIntraOralExaminationList, disIntraOralExaminationListParentByChild, "infoId", patCurInfoDTO.patInfoId, patCurInfoDTO, true)%>
      </table>
   </div>
   <label>Extra oral examination</label>  <br/>
   <div class="col-sm-offset-1">
     <table>
      <%=MyUtility.generateHTML(patExtraOralExaminationList, disExtraOralExaminationListParentByChild, "infoId", patCurInfoDTO.patInfoId, patCurInfoDTO , true)%>
      </table>
   </div>
</div>