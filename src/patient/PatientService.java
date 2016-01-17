package patient;

import java.util.ArrayList;
import java.util.HashMap;

import login.LoginDTO;

import utility.DAOResult;

public class PatientService {
	
	public DAOResult addNewPatient(PatientDTO dto, int userID){
		PatientDAO dao = new PatientDAO();
		return dao.addNewPatient(dto, userID);
	}
	
	public ArrayList<PatientDTO> getPatientDTOs(String startDate, String endDate, String searchBy, String nameTicketPhone, int roleID){
		PatientDAO dao = new PatientDAO();
		return dao.getPatientDTOs(startDate, endDate, searchBy, nameTicketPhone, roleID);
	}
	
	public ArrayList<String> getPatientImagesList(int accountID){
		PatientDAO dao = new PatientDAO();
		return dao.getPatientImagesList(accountID);
	}
	
	public PatientDTO getPatientDTO(int accId){
		PatientDAO dao = new PatientDAO();
		return dao.getPatientDTObyId(accId);
	}
	
	public HashMap<Integer, String> getPatientInfoAllDeptByYear(int year){
		PatientDAO dao = new PatientDAO();
		return dao.getPatientInfoAllDeptByYear(year);
	}
	
	public DAOResult updatePatientDTO(PatientDTO dto, LoginDTO loginDTO){
		PatientDAO dao = new PatientDAO();
		return dao.updatePatientDTO(dto, loginDTO);
	}

	public DAOResult UpdateIndoorDiseaseInfo(PatientDTO dto) {
		PatientDAO dao = new PatientDAO();
		return dao.UpdateIndoorDiseaseInfo(dto);
	}

	public String DischargePatientByID(int patientId) {
		return new PatientDAO().DischargePatientByID(patientId);
	}

	public ArrayList<PatientDTO> getCurrentWardStatus(){
		PatientDAO dao = new PatientDAO();
		return dao.getCurrentWardStatus();
	}
}
