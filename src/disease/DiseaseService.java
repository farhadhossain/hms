package disease;

import disease.form.DiseaseMetaData;
import prescription.VisitDAO;
import utility.DAOResult;

import java.util.ArrayList;
import java.util.HashMap;

public class DiseaseService {
	
	public HashMap<Integer, String> getSysDiseaseInfo(int typeID) {
		return new GetDiseaseInfoDAO().getSysDiseaseInfo(typeID);
	}
	
	public HashMap<Integer, String> getDiseaseDetailsByLevel(int diseaseId, int diseaseInfoType, int level) {
		return new GetDiseaseInfoDAO().getDiseaseDetailsByLevel(diseaseId, diseaseInfoType, level);
	}
	
	public HashMap<Integer, DiseaseMetaData> getDiseaseDetailsByDisIDAndDisType(int diseaseId, int diseaseInfoType) {
		return new GetDiseaseInfoDAO().getDiseaseDetailsByDisIDAndDisType(diseaseId, diseaseInfoType);
	}
	
	public HashMap<Integer, String> getParentByChildWithDisIDAndDisType(int diseaseId, int diseaseInfoType) {
		return new GetDiseaseInfoDAO().getParentByChildWithDisIDAndDisType(diseaseId, diseaseInfoType);
	}
	
	public HashMap<Integer, String> getSpecialCaseIdNameList(int diseaseId) {
		return new GetDiseaseInfoDAO().getSpecialCaseIdNameList(diseaseId);
	}
	//
	//
	public HashMap<Integer, DiseaseMetaData> getSpCaseDetailsByDisIDAndCaseID(int diseaseId, int specialCaseId) {
		return new GetDiseaseInfoDAO().getSpCaseDetailsByDisIDAndCaseID(diseaseId, specialCaseId);
	}
	
	public boolean getIsThisSpecialIdsChildAssigned(int userId, int diseaseId, int specialCaseId) {
		return new GetDiseaseInfoDAO().getIsThisSpecialIdsChildAssigned(userId, diseaseId, specialCaseId);
	}
	
	public HashMap<Integer, String> getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(int diseaseId, int specialCaseId) {
		return new GetDiseaseInfoDAO().getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(diseaseId, specialCaseId);
	}
	
	public DiseaseDTO getDiseaseInfo(int userID, int diseaseID) {
		int currentVisitId = new VisitDAO().getCurrentVisitId(userID);
		return new GetPatientDiseaseInfoDAO().getDiseaseInfo(userID, currentVisitId, diseaseID);
	}

	public ArrayList<FollowUpDTO> getFollowUpReport(int userID, int diseaseID) {
		return new GetPatientDiseaseInfoDAO().getFollowUpReport(userID, diseaseID);
	}
	public DAOResult addPatientDiseaseInfo(DiseaseDTO dto) {
		return new UpdatePatientDiseaseInfoDAO().addPatientDiseaseInfo(dto);
	}

	public String addNewFollowUp(int userID, int diseaseID, String newFindings) {
		return new UpdatePatientDiseaseInfoDAO().addNewFollowUp(userID, diseaseID, newFindings);
	}


	public DiseaseDTO getDiseaseInfo(int userID, int visitId, int diseaseID) {
		if (visitId==0){
			visitId = new VisitDAO().getCurrentVisitId(userID);
		}
		return new GetPatientDiseaseInfoDAO().getDiseaseInfo(userID, visitId, diseaseID);
	}
}
