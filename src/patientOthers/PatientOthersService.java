package patientOthers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import utility.DAOResult;

public class PatientOthersService {
	
	public HashMap<Integer, String> getParentByChildWithInfoId(int infoId){
		return new PatientOthersDAO().getParentByChildWithInfoId(infoId);
	}
	public HashMap<Integer, String> getSocialAndPersonalHistoryDetailsByID(int infoId){
		return new PatientOthersDAO().getSocialAndPersonalHistoryDetailsByID(infoId);
	}
	
	public HashMap<Integer, String> getDrugGroup(){
		return new PatientOthersDAO().getDrugGroup();
	}
	public HashSet<Integer> getPatientDrugID(int userId){
		return new PatientOthersDAO().getPatientDrugID(userId);
	}
	
	public ArrayList<PatientOthersDTO> getMedicationList(int patientId, int type){
		return new PatientOthersDAO().getMedicationList(patientId, type);
	}
	
	public PatientOthersDTO getPatientOthersDTOByID(int patientId){
		return new PatientOthersDAO().getPatientOthersDTOByID(patientId);
	}
	
	public PatientOthersDTO getPatientPhyExmInfoDTOByID(int patientId){
		return new PatientOthersDAO().getPatientPhyExmInfoDTOByID(patientId);
	}
	
	public DAOResult updatePatientOthersDTO(PatientOthersDTO dto){
		return new PatientOthersDAO().updatePatientOthersDTO(dto);
	}
	
	public DAOResult updatePatientPhyExmInfoDTO(PatientOthersDTO dto){
		return new PatientOthersDAO().updatePatientPhyExmInfoDTO(dto);
	}
	
	public DAOResult AddPatientMedication(PatientOthersDTO dto) {
		return new PatientOthersDAO().AddPatientMedication(dto);
	}
	
	public DAOResult makePastPatientMedication(int id) {
		return new PatientOthersDAO().makePastPatientMedication(id);
	}

	public DAOResult updatePatientDrugDTO(PatientOthersDTO dto) {
		return new PatientOthersDAO().updatePatientDrugDTO(dto);
	}
	public DAOResult updatePatientInvestigationDTO(PatientOthersDTO dto) {
		return new PatientOthersDAO().updatePatientInvestigationDTO(dto);
	}
	public DAOResult updatePatientPreAnestheticEvaluationDTO(PatientOthersDTO dto) {
		return new PatientOthersDAO().updatePatientPreAnestheticEvaluationDTO(dto);
	}
	public DAOResult updatePatientGeneralSurveyDTO(PatientOthersDTO dto) {
		return new PatientOthersDAO().updatePatientGeneralSurveyDTO(dto);
	}
}
