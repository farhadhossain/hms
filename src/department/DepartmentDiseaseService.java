package department;

import java.util.HashMap;

import utility.DAOResult;

public class DepartmentDiseaseService {
	public HashMap<Integer, String> getDepartmentDiseaseListByID(int departmentID, int subDeptID){
		return new DepartmentDiseaseDAO().getDepartmentDiseaseListByID(departmentID, subDeptID);
	}

	public DAOResult updatePatientDepartmentDiseaseInfo(DepartmentDiseaseDTO dto) {
		return new DepartmentDiseaseDAO().updatePatientDepartmentDiseaseInfo(dto);
	}
	
	public DepartmentDiseaseDTO getPatientDepartmentDiseaseInfo(int accountID, int departmentID) {
		return new DepartmentDiseaseDAO().getPatientDepartmentDiseaseInfo(accountID, departmentID);
	}
	
}
