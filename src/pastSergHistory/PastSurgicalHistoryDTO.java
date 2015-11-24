package pastSergHistory;


public class PastSurgicalHistoryDTO{
	private int surgicalID;
	private int userID;
	public String patientName;
	public String patientDetails;
	private String accAndInjuries;
	private String nameOfOp;
	private String dateOfOp;
	private String compliOfAnesthesia;
	private String postOperativeCompli;
	private String others;
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
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getAccAndInjuries() {
		return accAndInjuries;
	}
	public void setAccAndInjuries(String accAndInjuries) {
		this.accAndInjuries = accAndInjuries;
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
	
}
