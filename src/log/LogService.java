package log;

import java.util.ArrayList;

public class LogService {
	public ArrayList<LogDTO> LogDetailsReport(String startDate, String endDate, String dateType, String pageNum, String currPageNo, int numOfRecord){
		LogDAO dao = new LogDAO();
		if(pageNum==null){
			pageNum="-1";
			currPageNo="1";
		}
		return dao.LogDetailsReport(startDate, endDate, Integer.parseInt(dateType), Integer.parseInt(pageNum), Integer.parseInt(currPageNo), numOfRecord);
	}
	
	public int LogDetailsReportCount(String startDate, String endDate, String dateType) {
		LogDAO dao = new LogDAO();
		return dao.LogDetailsReportCount(startDate, endDate, Integer.parseInt(dateType));
	}
}
