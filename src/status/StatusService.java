package status;

import java.util.HashMap;

public class StatusService {
	public HashMap<Integer, String> getAllStatus(){
		return new StatusDAO().getAllStatus();
	}
}
