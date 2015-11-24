package pastSergHistory;

import java.util.ArrayList;

import utility.DAOResult;

public class PastSurgicalHistoryService {
	public DAOResult NewSurgicalHistory(PastSurgicalHistoryDTO dto) {
		return new PastSurgicalHistoryDAO().NewSurgicalHistory(dto);
	}
	
	public ArrayList<PastSurgicalHistoryDTO> getSurgicalHistory(int userId){
		return new PastSurgicalHistoryDAO().getSurgicalHistory(userId);
	}
	
	public PastSurgicalHistoryDTO getPastSurgicalHistoryDTOBySurgicalID(int surID) {
		return new PastSurgicalHistoryDAO().getPastSurgicalHistoryDTOBySurgicalID(surID);
	}
	
	public ArrayList<PastSurgicalHistoryDTO> getPatientStatusLists(int fromStatusID, String startDate, String endDate) {
		return new PastSurgicalHistoryDAO().getPatientStatusLists(fromStatusID, startDate, endDate);
	}

	public String UpdateSurgicalHistoryStatus(int surgicalId, int currentStatus) {
		return new PastSurgicalHistoryDAO().UpdateSurgicalHistoryStatus(surgicalId, currentStatus);
	}
}
