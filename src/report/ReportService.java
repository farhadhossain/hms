package report;

import java.util.ArrayList;


public class ReportService {
	
	public ReportDTO HospitalPatientStatistics(String startDate, String endDate, String dateType, String sex, int fromAge, int toAge){
		return new ReportDAO().HospitalPatientStatistics(startDate, endDate, Integer.parseInt(dateType), sex, fromAge, toAge);
	}

	public ArrayList<ReportDTO> DiseaseSpecificReport(String diseaseID, String[] childId, int typeID){
		return new ReportDAO().DiseaseSpecificReport(diseaseID, childId, typeID);
	}
	
}
