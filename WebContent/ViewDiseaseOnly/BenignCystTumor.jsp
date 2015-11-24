<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="disease.DiseaseDTO"%>
<%@page import="disease.FollowUpDTO"%>
<%@ page import="utility.MyUtility" %>
<%@page import="java.util.HashSet"%>
<%
DiseaseService disServ = new DiseaseService();

DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(patientDTO.getAccId(), key); 
ArrayList<FollowUpDTO> followUpList=disServ.getFollowUpReport(patientDTO.getAccId(), key);

HashMap<Integer, String> disHistoryList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseHistory);
HashMap<Integer, String> disHistoryParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseHistory);

HashMap<Integer, String> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseDiagnosis);
HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseDiagnosis);

HashMap<Integer, String> disTreatmentPlanList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseTreatmentPlan);
HashMap<Integer, String> disTreatmentPlanParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseTreatmentPlan);

HashMap<Integer, String> disSpecialCaseList = disServ.getSpecialCaseIdNameList(key);

%>
<style type="text/css">
	.theader {
		text-align: left;
		font-size: 16px;
		font-style: italic;
	}

	.table {
		font-size: 16px;
	}
</style>
		<hr></hr>
		<div class="col-sm-12" style="color: red; text-align: center;">
			<strong style="color: red;"><%=diseaseList.get(key)%></strong>
		</div>
		<br><br>
		<%if(disHistoryList.size()>0 && patCurDisDTO.patHisId.isEmpty()==false){%>
			<div class="">
			<br><br>
				<div class="theader col-sm-2">
					<label>History</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disHistoryList, disHistoryParentByChild, "historyId", patCurDisDTO.patHisId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			 </div>
		<%}%>
    									    
		<div class="">
			<br><br>
			<div class="theader col-sm-2">
				<label>Extra Oral Examination</label>
			</div>
			<div class="col-sm-10 table-responsive">
				
				<%
				int keyVal=18;
				HashMap<Integer, String> disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				HashMap<Integer, String> disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				boolean isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
					<div class="">
						<div class="">
							<label>Inspection</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="font-size: 13px;">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>
				
				<%
				keyVal=19;
				disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
				<br><br>
					<div class="">
						<div class="">
							<label>Palpation</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>
				
				<%
				keyVal=20;
				disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
				<br><br>
					<div class="">
						<div class="">
							<label>Ascultation</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>
					
				<%
				keyVal=21;
				disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
				<br><br>
					<div class="">
						<div class="">
							<label>Others</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>
			 </div>
		 </div>					
		 <br><br>
		 <div class="">
			<div class="col-sm-2 theader">
				<label>Intra Oral Examination</label>
			</div>
			<div class="table-responsive col-sm-10">
				
				<%
				keyVal=22;
				disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
				
					<div class="">
						<div class="">
							<label>Inspection</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>
				
				<%
				keyVal=23;
				disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
				<br><br>
					<div class="">
						<div class="">
							<label>Palpation</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>
				
				<%
				keyVal=24;
				disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
				disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
				isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
				if(isAnyAvailable){%>
				<br><br>
					<div class="">
						<div class="">
							<label>Percution</label>
						</div>
						<div class="table-responsive">
							<table class="table" style="">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							</table>
						 </div>
					</div>
				<%}%>	
			 </div>
		 </div>
		 
		<%
		keyVal=25;
		disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
		disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
		isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
		if(isAnyAvailable){%>	
		<br><br>	
			 <div class="">
				<div class="">
					<label>Aspiration Note</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table" style="">
						<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			</div>
		<%}%>
		
		<%
		keyVal=26;
		disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
		disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
		isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
		if(isAnyAvailable){%>	
		<br><br>	
			<div class="">
				<div class="">
					<label>Others</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table" style="">
						<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			</div>
		<%}%>
		
		<%
		keyVal=27;
		disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
		disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
		isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
		if(isAnyAvailable){%>	
		<br><br>	
			<div class="">
				<div class="">
					<label>Specific Investigation</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table" style="">
						<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			</div>
		<%}%>
		
		<%if(disTreatmentPlanList.size()>0 && patCurDisDTO.patTrtmntId.isEmpty()==false){%>
			<br><br>
			<div class="">
				<div class="theader col-sm-2">
					<label>Treatment Plan</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disTreatmentPlanList, disTreatmentPlanParentByChild, "treatmentId", patCurDisDTO.patTrtmntId, patCurDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
		
		<%if(patCurDisDTO.getComplications()!=null && patCurDisDTO.getComplications().length()>0){%>
			<div class="">
				<div class="theader col-sm-2">
					<label>Complications</label>
				</div>
				<div class="table-responsive col-sm-10">
					<%=patCurDisDTO.getComplications()%>
				</div>
			</div>
		<%}%>
		
		
		<%if(patCurDisDTO.getSpecialNotes()!=null && patCurDisDTO.getSpecialNotes().length()>0){%>
			<div class="">
				<div class="theader col-sm-2">
					<label>Special Notes</label>
				</div>
				<div class="table-responsive col-sm-10">
					<%=patCurDisDTO.getSpecialNotes()%>
				</div>
			</div>
		<%}%>
		
		<%if(followUpList.size()>0){%>
			<div class="">
				<div class="theader col-sm-2">
					<label>Follow UP</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<thead>
                        	<tr>
                            	<th>SN</th>
                                <th>Findings</th>
                                <th>Date Of Entry</th>
							</tr>
                        </thead>
                        <tbody>
	                    	<%
							for(int i=0;i<followUpList.size();i++){%>
								<tr>
			                    	<td><%=(i+1)%></td>
									<td><%=followUpList.get(i).findings%></td>
									<td><%=followUpList.get(i).addedTime%></td>
								</tr>
							<%}%>
                        </tbody>
					</table>
				</div>
			</div>
		<%}%>
