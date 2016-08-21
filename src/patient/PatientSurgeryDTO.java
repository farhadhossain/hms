package patient;


import user.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class PatientSurgeryDTO {

	private int surgicalID;
	private int userID;
	public String patientName;
	public String patientDetails;
	private String nameOfOp;
	private String dateOfOp;
	private String compliOfAnesthesia;
	private String postOperativeCompli;
	private String others;
	private String operationalNotes;
	private int doctorID;
	public String doctorName;
	public String surgeonDescripton;
	public String assistSurgeonDescripton;
	public String anesthetistDescripton;
	
	private int surgicalStatus;
	
	private int[] surgeonIDList;
	private int[] assistSurgIDList;
	private int[] anesthetistIDList;
	public String responsibleDoctorBed;
	public String updatePerform;

	private String followupAdvice;
	private String nextVisitDate;

	private List<UserDTO> surgeonList = new ArrayList<UserDTO>();
	private List<UserDTO> assistSurgList = new ArrayList<UserDTO>();
	private List<UserDTO> anesthetistList = new ArrayList<UserDTO>();
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getNameOfOp() {
		return nameOfOp;
	}
	public void setNameOfOp(String nameOfOp) {
		this.nameOfOp = nameOfOp;
	}
	public String getDateOfOp() {
		return dateOfOp;
	}
	public void setDateOfOp(String dateOfOp) {
		this.dateOfOp = dateOfOp;
	}
	public String getCompliOfAnesthesia() {
		return compliOfAnesthesia;
	}
	public void setCompliOfAnesthesia(String compliOfAnesthesia) {
		this.compliOfAnesthesia = compliOfAnesthesia;
	}
	public String getPostOperativeCompli() {
		return postOperativeCompli;
	}
	public void setPostOperativeCompli(String postOperativeCompli) {
		this.postOperativeCompli = postOperativeCompli;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public int getDoctorID() {
		return doctorID;
	}
	
	public void setSurgicalStatus(int surgicalStatus) {
		this.surgicalStatus = surgicalStatus;
	}
	public int getSurgicalStatus() {
		return surgicalStatus;
	}
	public void setSurgicalID(int surgicalID) {
		this.surgicalID = surgicalID;
	}
	public int getSurgicalID() {
		return surgicalID;
	}
	public void setSurgeonIDList(int[] surgeonIDList) {
		this.surgeonIDList = surgeonIDList;
	}
	public int[] getSurgeonIDList() {
		return surgeonIDList;
	}
	public void setAssistSurgIDList(int[] assistSurgIDList) {
		this.assistSurgIDList = assistSurgIDList;
	}
	public int[] getAssistSurgIDList() {
		return assistSurgIDList;
	}
	public void setAnesthetistIDList(int[] anesthetistIDList) {
		this.anesthetistIDList = anesthetistIDList;
	}
	public int[] getAnesthetistIDList() {
		return anesthetistIDList;
	}

	public String getOperationalNotes() {
		return operationalNotes;
	}

	public void setOperationalNotes(String operationalNotes) {
		this.operationalNotes = operationalNotes;
	}

	public List<UserDTO> getSurgeonList() {
		return surgeonList;
	}

	public void setSurgeonList(List<UserDTO> surgeonList) {
		this.surgeonList = surgeonList;
	}

	public List<UserDTO> getAssistSurgList() {
		return assistSurgList;
	}

	public void setAssistSurgList(List<UserDTO> assistSurgList) {
		this.assistSurgList = assistSurgList;
	}

	public List<UserDTO> getAnesthetistList() {
		return anesthetistList;
	}

	public void setAnesthetistList(List<UserDTO> anesthetistList) {
		this.anesthetistList = anesthetistList;
	}

	public String getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

	public String getFollowupAdvice() {
		return followupAdvice;
	}

	public void setFollowupAdvice(String followupAdvice) {
		this.followupAdvice = followupAdvice;
	}
}
