package patient;

import java.util.HashSet;


public class PatientDTO
{

	public static int SURGERY_STATUS_ADMITTED = 1;
	public static int SURGERY_STATUS_PRE_OPERATIVE = 2;
	public static int SURGERY_STATUS_POST_OPERATIVE = 3;
	public static int SURGERY_STATUS_DISCHARGED = 4;

	public PatientDTO(){
		ticketNumber="";
		regNumber="";
		wordNumber="";
		bedNumber="";
		cabinNumber="";
		telephoneNum="";
		presentAdd="";
		permanentAdd="";
		referredBy="";
	}
	private int accId;
	private String name;
	private int age;
	private String sex;
	private String telephoneNum;
	private String presentAdd;
	private String permanentAdd;
	private int deptId;
	private int diseaseType[];
	private int bedDoctorID;
	private String pin;
	private String dateOfRec;
	private String ticketNumber;
	private String regNumber;
	private String wordNumber;
	private String bedNumber;
	private String cabinNumber;
	private String bloodGroup;
	private int otherDeptRefId;
	private int surgicalStatus;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String imageName;
	public int dischargeStatus;	
	public HashSet<Integer> diseaseTypeHash;

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String addedBy;
	public String date_of_adm;
	public String date_of_disch;

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String referredBy;


	
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(String wordNumber) {
		this.wordNumber = wordNumber;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public String getCabinNumber() {
		return cabinNumber;
	}
	public void setCabinNumber(String cabinNumber) {
		this.cabinNumber = cabinNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelephoneNum() {
		return telephoneNum;
	}
	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}
	public String getPresentAdd() {
		return presentAdd;
	}
	public void setPresentAdd(String presentAdd) {
		this.presentAdd = presentAdd;
	}
	public String getPermanentAdd() {
		return permanentAdd;
	}
	public void setPermanentAdd(String permanentAdd) {
		this.permanentAdd = permanentAdd;
	}

	public int getSurgicalStatus() {
		return surgicalStatus;
	}

	public void setSurgicalStatus(int surgicalStatus) {
		this.surgicalStatus = surgicalStatus;
	}

	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int[] getDiseaseType() {
		return diseaseType;
	}
	public void setDiseaseType(int[] diseaseType) {
		this.diseaseType = diseaseType;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPin() {
		return pin;
	}
	public void setDateOfRec(String dateOfRec) {
		this.dateOfRec = dateOfRec;
	}
	public String getDateOfRec() {
		return dateOfRec;
	}
	public void setOtherDeptRefId(int otherDeptRefId) {
		this.otherDeptRefId = otherDeptRefId;
	}
	public int getOtherDeptRefId() {
		return otherDeptRefId;
	}
	public void setBedDoctorID(int bedDoctorID) {
		this.bedDoctorID = bedDoctorID;
	}
	public int getBedDoctorID() {
		return bedDoctorID;
	}
	
}
