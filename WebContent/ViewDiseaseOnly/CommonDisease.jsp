<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="disease.FollowUpDTO"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="disease.DiseaseService"%>
<%@page import="disease.DiseaseDTO"%>
<%@ page import="utility.MyUtility" %>
<%@ page language="Java" %>
<%

DiseaseService disServ = new DiseaseService();

DiseaseDTO patCurDisDTO = disServ.getDiseaseInfo(patientDTO.getAccId(), Integer.parseInt(visitID), key);
ArrayList<FollowUpDTO> followUpList=disServ.getFollowUpReport(patientDTO.getAccId(), key);

HashMap<Integer, DiseaseMetaData> disHistoryList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseHistory);
HashMap<Integer, String> disHistoryParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseHistory);

HashMap<Integer, DiseaseMetaData> disSymptomList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseSymptom);
HashMap<Integer, String> disSymptomParentByChild= new HashMap<Integer, String> ();

HashMap<Integer, DiseaseMetaData> disInspectionList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseInspection);
HashMap<Integer, String> disInspecParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseInspection);

HashMap<Integer, DiseaseMetaData> disPalpationList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseasePalpation);
HashMap<Integer, String> disPalpationParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseasePalpation);

HashMap<Integer, DiseaseMetaData> disTreatmentPlanList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseTreatmentPlan);
HashMap<Integer, String> disTreatmentPlanParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseTreatmentPlan);

HashMap<Integer, DiseaseMetaData> disDiagnosisList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseDiagnosis);
HashMap<Integer, String> disDiagnosisParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseDiagnosis);

HashMap<Integer, DiseaseMetaData> disAuscultationList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseAuscultation);
HashMap<Integer, String> disAuscultationParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseAuscultation);

HashMap<Integer, DiseaseMetaData> disOthersList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseaseOthers);
HashMap<Integer, String> disOthersParentByChild = disServ.getParentByChildWithDisIDAndDisType(key, MyConfig.diseaseOthers);

HashMap<Integer, DiseaseMetaData> disPerticipatingFactorList = disServ.getDiseaseDetailsByDisIDAndDisType(key, MyConfig.diseasePerticipatingFactor);
HashMap<Integer, String> disPerticipatingFactorParentByChild = new HashMap<Integer, String> ();

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

	.thin{
		font-weight: normal;
	}
</style>
	<div class="ibox-content patientView">
		
		<div class="col-sm-12" style="">
			<strong style=""><%=indexCount%>. <%=diseaseList.get(key)%></strong>
		</div>
		<div class="col-sm-offset-1">
		<br>
		<br>
		<%if(disHistoryList.size()>0 && patCurDisDTO.patHisId.isEmpty()==false){%>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">History</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%if(key==20){%>
							<%if(patCurDisDTO.getOmLocation()!=null && patCurDisDTO.getOmLocation().length()>0){%>
								<tr><td style="border: none;" class="tableData2">Location <input type="text" name="omLocation" value="<%=patCurDisDTO.getOmLocation()%>"></td></tr>
							<%}%>
							
							<%if(patCurDisDTO.getOmDuration()!=null && patCurDisDTO.getOmDuration().length()>0){%>
								<tr><td style="border: none;" class="tableData2">Duration <input type="text" name="omDuration" value="<%=patCurDisDTO.getOmDuration()%>"></td></tr>
							<%}%>
							
							<%if(patCurDisDTO.getOmTimeframe()!=null && patCurDisDTO.getOmTimeframe().length()>0){%>
								<tr><td style="border: none;" class="tableData2">Time frame <input type="text" name="omTimeframe" value="<%=patCurDisDTO.getOmTimeframe() %>"></td></tr>
							<%}%>
							
							<%if(patCurDisDTO.getOmIntensity()!=null && patCurDisDTO.getOmIntensity().length()>0){%>
								<tr><td style="border: none;" class="tableData2">Intensity <input type="text" name="omIntensity" value="<%=patCurDisDTO.getOmIntensity() %>"></td></tr>
							<%}%>
							
							<%if(patCurDisDTO.getOmMechanism()!=null && patCurDisDTO.getOmMechanism().length()>0){%>
								<tr><td style="border: none;" class="tableData2">Mechanism <input type="text" name="omMechanism" value="<%=patCurDisDTO.getOmMechanism() %>"></td></tr>
							<%}%>
						<%}%>
						<%=MyUtility.generateHTML(disHistoryList, disHistoryParentByChild, "historyId", patCurDisDTO.patHisId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			 </div>
		<%}%>
		
		<%if(key==17){
			if(disOthersList.size()>0 && patCurDisDTO.patOthersId.isEmpty()==false){%>
				<br>
				<br>
				<div class="">
					<div class="theader col-sm-2">
						<label class="thin">Over Lining</label>
					</div>
					<div class="table-responsive col-sm-10">
						<table class="table">
							<%=MyUtility.generateHTML(disOthersList, new HashMap<Integer, String>(), "othersId", patCurDisDTO.patOthersId, patCurDisDTO, editAndView)%>
						</table>
					 </div>
				</div>
			<%}%>
		<%}%>
		
		<%if(disSymptomList.size()>0 && patCurDisDTO.patSympId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Symptom</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disSymptomList, disSymptomParentByChild, "symptomId", patCurDisDTO.patSympId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			 </div>
		<%}%>
		
		<%if(disPerticipatingFactorList.size()>0 && patCurDisDTO.patPrtFactId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Perticipating Factor</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disPerticipatingFactorList, disPerticipatingFactorParentByChild, "prticpFactId", patCurDisDTO.patPrtFactId, patCurDisDTO, editAndView)%>
					</table>
				 </div>
			 </div>
		<%}%>
		
			
		<%if(disInspectionList.size()>0 && patCurDisDTO.patInspId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Inspection</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disInspectionList, disInspecParentByChild, "inspectionId", patCurDisDTO.patInspId, patCurDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
		
		
		<%if(disPalpationList.size()>0 && patCurDisDTO.patPalpId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Palpation</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disPalpationList, disPalpationParentByChild, "palpationId", patCurDisDTO.patPalpId, patCurDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
		
		<%if(disAuscultationList.size()>0 && patCurDisDTO.patAuscId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Auscultation</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disAuscultationList, disAuscultationParentByChild, "auscultationId", patCurDisDTO.patAuscId, patCurDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
		
		<%if(disDiagnosisList.size()>0 && patCurDisDTO.patDiagonosisId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Diagnosis</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disDiagnosisList, disDiagnosisParentByChild, "diagnosisId", patCurDisDTO.patDiagonosisId, patCurDisDTO, editAndView)%>
					</table>
				</div>
			</div>
		<%}%>
		
		
		<%if(disTreatmentPlanList.size()>0 && patCurDisDTO.patTrtmntId.isEmpty()==false){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Treatment Plan</label>
				</div>
				<div class="table-responsive col-sm-10">
					<table class="table">
						<%=MyUtility.generateHTML(disTreatmentPlanList, disTreatmentPlanParentByChild, "treatmentId", patCurDisDTO.patTrtmntId, patCurDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
		
		<%if(disSpecialCaseList.size()>0){
			for(int keyVal: disSpecialCaseList.keySet()){
				HashMap<Integer, DiseaseMetaData> disSpecialCaseListDetails = disServ.getSpCaseDetailsByDisIDAndCaseID(key, keyVal);
            	HashMap<Integer, String> disSpecialCaseListDetailsParentByChild = disServ.getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(key, keyVal);
            	boolean isAnyAvailable=disServ.getIsThisSpecialIdsChildAssigned(patientDTO.getAccId(), key, keyVal);
            	if(isAnyAvailable){%>
            		<br>
            		<br>
					<div class="">
						<div class="theader col-sm-2">
							<label><%=disSpecialCaseList.get(keyVal) %></label>
						</div>
						<div class="table-responsive col-sm-10">
							<table class="table">
								<%=MyUtility.generateHTML(disSpecialCaseListDetails, disSpecialCaseListDetailsParentByChild, "specialCaseId", patCurDisDTO.patSpCaseId, patCurDisDTO, editAndView)%>
							 </table>
						</div>
					</div>
				<%}
			}
		}%>
		
		<%if(patCurDisDTO.getComplications()!=null && patCurDisDTO.getComplications().length()>0){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Complications</label>
				</div>
				<div class="table-responsive col-sm-10">
					<%=patCurDisDTO.getComplications()%>
				</div>
			</div>
		<%}%>
		
		
		<%if(patCurDisDTO.getSpecialNotes()!=null && patCurDisDTO.getSpecialNotes().length()>0){%>
			<br>
			<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Special Notes</label>
				</div>
				<div class="table-responsive col-sm-10">
					<%=patCurDisDTO.getSpecialNotes()%>
				</div>
			</div>
		<%}%>
		
		<%if(followUpList.size()>0){%>
		<br>
		<br>
			<div class="">
				<div class="theader col-sm-2">
					<label class="thin">Follow UP</label>
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

		</div>
 </div>
