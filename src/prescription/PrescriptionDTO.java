package prescription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macintosh on 11/28/15.
 */
public class PrescriptionDTO {
    private int id;
    private String chiefComplain;
    private String onObservation;
    private String investigation;
    private String diagonosis;
    private String advice;
    private int patientID;
    private List<PrescriptionMedicineDTO> medicines = new ArrayList<PrescriptionMedicineDTO>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOnObservation() {
        return onObservation;
    }

    public void setOnObservation(String onObservation) {
        this.onObservation = onObservation;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getDiagonosis() {
        return diagonosis;
    }

    public void setDiagonosis(String diagonosis) {
        this.diagonosis = diagonosis;
    }

    public String getChiefComplain() {
        return chiefComplain;
    }

    public void setChiefComplain(String chiefComplain) {
        this.chiefComplain = chiefComplain;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setMedicines(List<PrescriptionMedicineDTO> medicines) {
        this.medicines = medicines;
    }

    public List<PrescriptionMedicineDTO> getMedicines() {
        return this.medicines;
    }
}

