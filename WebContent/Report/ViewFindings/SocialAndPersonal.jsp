<%
PatientOthersDTO patPhyExmDTO = patOthersServ.getPatientPhyExmInfoDTOByID(Integer.parseInt(accountID));
%>
<label>1. Systematic Evaluation:</label>  <br/>
<div class="col-sm-offset-1">
    <label>a. Social and Personal History</label>  <br/>
    <div class="col-sm-offset-1">
        <label class="thin">Exposure to : </label>
        <%for(Integer key:patCurInfoDTO.patExposureToId){%>
            <%=patExposureToList.get(key).getName()%>
        <%}%>
        <br/>
        <label class="thin">Hobbies : </label> <%=patCurInfoDTO.getHobbies()%><br/>
        <label class="thin">Habits : </label>
        <table class="table" style="font-size: 13px;">
          <%if(patCurInfoDTO.patHabitsId.contains(4)==true){%>
            <tr>
                <td>
                    <strong><%=patHabitsList.get(4).getName()%> :</strong>  Each day <%=patCurInfoDTO.getCigarettesStick()%> Stick For <%=patCurInfoDTO.getCigarettesYear()%> year
                </td>
            </tr>
          <%}%>
          <%if(patCurInfoDTO.patHabitsId.contains(5)==true){%>
            <tr>
                <td>
                   <strong><%=patHabitsList.get(5).getName()%> : </strong> Each day <%=patCurInfoDTO.getSmokelessTobaccoTime()%> Time For <%=patCurInfoDTO.getSmokelessTobaccoYear()%> year
                </td>
            </tr>
          <%}%>
          <%if(patCurInfoDTO.patHabitsId.contains(6)==true){%>
            <tr>
                <td>
                    <strong><%=patHabitsList.get(6).getName()%> :</strong> Each day <%=patCurInfoDTO.getAlcoholPack()%> Pack for <%=patCurInfoDTO.getAlcoholYear()%> year
                </td>
            </tr>
          <%}%>
          <%if(patCurInfoDTO.patHabitsId.contains(7)==true){%>
            <tr>
                <td>
                    <strong><%=patHabitsList.get(7).getName()%> :</strong> <%=patCurInfoDTO.getOtherSubsAbuse()%> for <%=patCurInfoDTO.getOtherSubsAbuseYear()%> year
                </td>
            </tr>
          <%}%>
        </table>
        <label class="thin">Gender : </label> <%=new String[]{"Single", "Married", "Divorced", "Unknown"}[patCurInfoDTO.getMaritalStatus()>0 ? patCurInfoDTO.getMaritalStatus()-1 : 3]%><br/>
        <label class="thin">Religion : </label> <%=OthersInfo.ReligionByID.get(patCurInfoDTO.getReligionId())%><br/>
        <label class="thin">Social Status : </label> <%=OthersInfo.SocialStatByID.get(patCurInfoDTO.getSocialStatusId())%><br/>
        <label class="thin">Occupation : </label> <%=patCurInfoDTO.getOccupation()%><br/>
    </div>
    <label>b. General Investigation</label>  <br/>
    <label>c. Physical Examination</label>  <br/>
    <div class="col-sm-offset-1">
        <label>Vital Sign</label>  <br/>
        <div class="col-sm-offset-1">
            <label class="thin">Temp : </label> <%=patPhyExmDTO.getVsTemp()%><br/>
            <label class="thin">Pulse : </label> <%=patPhyExmDTO.getVsPulse()%><br/>
            <label class="thin">Rate : </label> <%=patPhyExmDTO.getVsRate()%><br/>
            <label class="thin">Blood Pressure : </label> <%=patPhyExmDTO.getVsBloodPre()%><br/>
        </div>
    </div>
    <label>d. Pre-Anesthetic Evaluation</label>  <br/>
    <%@ include file="PreAnestheticEvaluation.jsp" %>
</div>