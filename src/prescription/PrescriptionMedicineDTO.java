package prescription;

/**
 * Created by macintosh on 12/3/15.
 */
public class PrescriptionMedicineDTO {
    private int id;
    private int prescriptionId;
    private String medicineName;
    private String medicineType;
    private String frequency;
    private String dose;
    private String comment;
    private String afterMeal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAfterMeal() {
        return afterMeal;
    }

    public void setAfterMeal(String afterMeal) {
        this.afterMeal = afterMeal;
    }
}
