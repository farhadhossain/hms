package utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DAOResult {

	private int objectId;
	private boolean isValid;
	private String message;

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public static boolean hasColumn(ResultSet rs, String colName){
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			for (int i = 1; i < numberOfColumns + 1; i++) {
				String columnName = rsMetaData.getColumnName(i);
				// Get the name of the column's table name
				if (colName.equals(columnName)) {
					return true;
				}
			}
		}catch(Exception e){}
		return false;
	}
}
