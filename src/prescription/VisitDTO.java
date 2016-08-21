package prescription;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by macintosh on 4/28/16.
 */
public class VisitDTO {

    private int id;
    private int patientId;
    private String ticketNumber;
    private int currentStatus;
    private String regNumber;
    private String wordNumber;
    private String bedNumber;
    private String cabinNumber;
    private int otherDeptRefId;
    private int surgicalStatus;
    private int bedDoctorID;
    private Date visitDate;
    private Date dateOfAdmission;
    public HashSet<Integer> diseaseTypeHash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
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

    public int getOtherDeptRefId() {
        return otherDeptRefId;
    }

    public void setOtherDeptRefId(int otherDeptRefId) {
        this.otherDeptRefId = otherDeptRefId;
    }

    public int getSurgicalStatus() {
        return surgicalStatus;
    }

    public void setSurgicalStatus(int surgicalStatus) {
        this.surgicalStatus = surgicalStatus;
    }

    public int getBedDoctorID() {
        return bedDoctorID;
    }

    public void setBedDoctorID(int bedDoctorID) {
        this.bedDoctorID = bedDoctorID;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }
}
