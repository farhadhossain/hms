package dashboard;

import java.util.ArrayList;

public class DashBoardService {

	public DashBoardDTO getSystemInfo(){
		return new DashBoardDAO().getSystemInfo();
	}
}
