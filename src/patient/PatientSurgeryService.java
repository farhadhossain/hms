package patient;

import utility.DAOResult;

import java.util.ArrayList;

public class PatientSurgeryService {
	public DAOResult addNewSurgery(PatientSurgeryDTO dto) {
		return new PatientSurgeryDAO().NewSurgery(dto);
	}

	public DAOResult makePostOperative(PatientSurgeryDTO dto) {
		return new PatientSurgeryDAO().makePostOperative(dto);
	}

	public DAOResult makeDischarge(PatientSurgeryDTO dto) {
		return new PatientSurgeryDAO().makeDiscahrge(dto);
	}

	public ArrayList<PatientSurgeryDTO> getSurgeryList(int userId){
		return new PatientSurgeryDAO().getSurgicalHistory(userId);
	}
	
	public PatientSurgeryDTO getSurgeryByPatientID(int patientId, int visitId) {
		return new PatientSurgeryDAO().getSurgeryByPatientID(patientId, visitId);
	}
	
	public ArrayList<PatientSurgeryDTO> getPatientStatusLists(int fromStatusID, String startDate, String endDate) {
		return new PatientSurgeryDAO().getPatientStatusLists(fromStatusID, startDate, endDate);
	}

	public String UpdateSurgicalHistoryStatus(int surgicalId, int currentStatus) {
		return new PatientSurgeryDAO().UpdateSurgicalHistoryStatus(surgicalId, currentStatus);
	}
}
