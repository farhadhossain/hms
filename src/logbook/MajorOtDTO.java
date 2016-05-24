package logbook;

/**
 * Created by User on 4/29/2016.
 */
public class MajorOtDTO {
    String patientName;
    String diagnosis;
    String operationName;
    String surgeons;
    String assSurgeons;
    String anesthetist;
    String operationDate;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAnesthetist() {
        return anesthetist;
    }

    public void setAnesthetist(String anesthetist) {
        this.anesthetist = anesthetist;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getSurgeons() {
        return surgeons;
    }

    public void setSurgeons(String surgeons) {
        this.surgeons = surgeons;
    }

    public String getAssSurgeons() {
        return assSurgeons;
    }

    public void setAssSurgeons(String assSurgeons) {
        this.assSurgeons = assSurgeons;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }
}
