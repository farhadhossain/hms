package extraction;

import java.util.ArrayList;
import java.util.HashMap;

import login.LoginDTO;

import utility.DAOResult;

public class ExtractionService {
	public HashMap<Integer, String> getExtractionListByID(){
		return new ExtractionDAO().getExtractionListByID();
	}
	
	public HashMap<Integer, Integer> getExtractionTakaByID() {
		return new ExtractionDAO().getExtractionTakaByID();
	}
	
	public DAOResult addPatientExtractionInfo(ExtractionDTO dto, LoginDTO loginDTO) {
		return new ExtractionDAO().addPatientExtractionInfo(dto, loginDTO);
	}
	
	public ExtractionDTO getExtractionDTOByID(int accountID) {
		return new ExtractionDAO().getExtractionDTOByID(accountID);
	}
	
	public ArrayList<ExtractionDTO> getPatientExtractionDTOList(String startDate, String endDate) {
		return new ExtractionDAO().getPatientExtractionDTOList(startDate, endDate);
	}
	
	
	/*public ExtractionDTO getPatientExtractionInfo(int accountID, int paymentRegNum) {
		return new ExtractionDAO().getPatientExtractionInfo(accountID, paymentRegNum);
	}*/
	
}
