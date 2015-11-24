<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="disease.DiseaseService"%>
<%@page import="disease.FollowUpDTO"%>
<%@page import="disease.DiseaseDTO"%>
<%@ page import="utility.MyUtility" %>
<%@ page language="Java" %>
<%@page import="java.util.HashSet"%>
<%

DiseaseService disServ = new DiseaseService();
DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(patientDTO.getAccId(), key); 
ArrayList<FollowUpDTO> followUpList=disServ.getFollowUpReport(patientDTO.getAccId(), key);

HashMap<Integer, String> disHistoryList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseHistory);
HashMap<Integer, String> disHistoryParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseHistory);

HashMap<Integer, String> disInspectionList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseInspection);
HashMap<Integer, String> disInspecParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseInspection);

HashMap<Integer, String> disPalpationList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseasePalpation);
HashMap<Integer, String> disPalpationParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseasePalpation);

HashMap<Integer, String> disTreatmentPlanList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseTreatmentPlan);
HashMap<Integer, String> disTreatmentPlanParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseTreatmentPlan);

HashMap<Integer, String> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseDiagnosis);
HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseDiagnosis);
HashMap<Integer, String> disSpecialCaseList = disServ.getSpecialCaseIdNameList(key);
%>


<style type="text/css">
	.theader {
		text-align: center;
		font-size: 16px;
	}

	.table {
		font-size: 16px;
	}
</style>
		<hr></hr>
		<div class="col-sm-12" style="color: red; text-align: center;">
			<strong style="color: red;">Oro-facia Infection</strong>
		</div>
	
		<br><br>
	<%if(disHistoryList.size()>0 && patCurDisDTO.patHisId.isEmpty()==false){%>
		<div class="col-sm-12 theader">
			<div class="theader">
				<label>History</label>
			</div>
			<div class="table-responsive">
				<table class="table">
					<%=MyUtility.generateHTML(disHistoryList, disHistoryParentByChild, "historyId", patCurDisDTO.patHisId, patCurDisDTO, editAndView)%>
				</table>
			 </div>
		 </div>
	<%}%>
		
	<%if(disInspectionList.size()>0 && patCurDisDTO.patInspId.isEmpty()==false){%>
		<div class="col-sm-12">
			<div class="theader">
				<label>Extra Oral Examination</label>
			</div>
			<div class="table-responsive">
				<table class="table">
					<%=MyUtility.generateHTML(disInspectionList, disInspecParentByChild, "inspectionId", patCurDisDTO.patInspId, patCurDisDTO, editAndView)%>
				 </table>
			</div>
		</div>
	<%}%>

	<%
	int keyVal=12;
	HashMap<Integer, String> disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
	HashMap<Integer, String> disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
	boolean isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
	if(isAnyAvailable){%>
		<div class="col-sm-12 theader">
			<div class="theader">
				<label><%=disSpecialCaseList.get(key)%></label>
			</div>
			<div class="table-responsive">
				<table class="table" style="font-size: 13px;">
					<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
				</table>
			 </div>
		</div>
	<%}%>
	
	<%if(disPalpationList.size()>0 && patCurDisDTO.patPalpId.isEmpty()==false){%>
		<div class="col-sm-12">
			<div class="theader">
				<label>Palpation</label>
			</div>
			<div class="table-responsive">
				<table class="table">
					<%=MyUtility.generateHTML(disPalpationList, disPalpationParentByChild, "palpationId", patCurDisDTO.patPalpId, patCurDisDTO, editAndView)%>
				 </table>
			</div>
		</div>
	<%}%>

	<%
	keyVal=17;
	disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
	disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
	isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
	if(isAnyAvailable){%>
		<div class="col-sm-12 theader">
			<div class="theader">
				<label><%=disSpecialCaseList.get(key)%></label>
			</div>
			<div class="table-responsive">
				<table class="table" style="font-size: 13px;">
					<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
				</table>
			 </div>
		</div>
	<%}%>
	
	<%if(disDiagnosisList.size()>0 && patCurDisDTO.patDiagonosisId.isEmpty()==false){%>
		<div class="col-sm-12">
			<div class="theader">
				<label>Diagnosis</label>
			</div>
			<div class="table-responsive">
				<table class="table">
					<%=MyUtility.generateHTML(disDiagnosisList, disDiagnosisParentByChild, "diagnosisId", patCurDisDTO.patDiagonosisId, patCurDisDTO, editAndView)%>
				</table>
			</div>
		</div>
	<%}%>
	
	
	<%if(disTreatmentPlanList.size()>0 && patCurDisDTO.patTrtmntId.isEmpty()==false){%>
		<div class="col-sm-12">
			<div class="theader">
				<label>Treatment Plan</label>
			</div>
			<div class="table-responsive">
				<table class="table">
					<%=MyUtility.generateHTML(disTreatmentPlanList, disTreatmentPlanParentByChild, "treatmentId", patCurDisDTO.patTrtmntId, patCurDisDTO, editAndView)%>
				 </table>
			</div>
		</div>
	<%}%>
	
	<%if(patCurDisDTO.getComplications()!=null && patCurDisDTO.getComplications().length()>0){%>
			<div class="col-sm-12">
				<div class="theader">
					<label>Complications</label>
				</div>
				<div class="table-responsive">
					<%=patCurDisDTO.getComplications()%>
				</div>
			</div>
		<%}%>
		
		
		<%if(patCurDisDTO.getSpecialNotes()!=null && patCurDisDTO.getSpecialNotes().length()>0){%>
			<div class="col-sm-12">
				<div class="theader">
					<label>Special Notes</label>
				</div>
				<div class="table-responsive">
					<%=patCurDisDTO.getSpecialNotes()%>
				</div>
			</div>
		<%}%>
		
		<%if(followUpList.size()>0){%>
			<div class="col-sm-12">
				<div class="theader">
					<label>Follow UP</label>
				</div>
				<div class="table-responsive">
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