package pastSergHistory.form;

import org.apache.struts.action.ActionForm;

public class PastSurgicalHistoryForm extends ActionForm{
	
	private int userID;
	private String accAndInjuries;
	private String nameOfOp;
	private String dateOfOp;
	private String compliOfAnesthesia;
	private String postOperativeCompli;
	private String others;
	private int doctorID;
	private int[] surgeonIDList;
	private int[] assistSurgIDList;
	private int[] anesthetistIDList;
	
	public int[] getSurgeonIDList() {
		return surgeonIDList;
	}
	public void setSurgeonIDList(int[] surgeonIDList) {
		this.surgeonIDList = surgeonIDList;
	}
	public int[] getAssistSurgIDList() {
		return assistSurgIDList;
	}
	public void setAssistSurgIDList(int[] assistSurgIDList) {
		this.assistSurgIDList = assistSurgIDList;
	}
	public int[] getAnesthetistIDList() {
		return anesthetistIDList;
	}
	public void setAnesthetistIDList(int[] anesthetistIDList) {
		this.anesthetistIDList = anesthetistIDList;
	}
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
	
	
}
