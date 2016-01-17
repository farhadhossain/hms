package extraction;

/**
 * Created by macintosh on 12/5/15.
 */
public class TreatmentPlanDTO {

    private int id;
    private int patientId;
    private int extractionId;
    private String inputValue1;
    private String inputValue2;
    private String inputValue3;
    private String inputValue4;
    private String paymentAmount;
    private String paymentRegNo;
    private boolean instrumentProvided;
    private boolean done;

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

    public int getExtractionId() {
        return extractionId;
    }

    public void setExtractionId(int extractionId) {
        this.extractionId = extractionId;
    }

    public String getInputValue1() {
        return inputValue1;
    }

    public void setInputValue1(String inputValue1) {
        this.inputValue1 = inputValue1;
    }

    public String getInputValue2() {
        return inputValue2;
    }

    public void setInputValue2(String inputValue2) {
        this.inputValue2 = inputValue2;
    }

    public String getInputValue3() {
        return inputValue3;
    }

    public void setInputValue3(String inputValue3) {
        this.inputValue3 = inputValue3;
    }

    public String getInputValue4() {
        return inputValue4;
    }

    public void setInputValue4(String inputValue4) {
        this.inputValue4 = inputValue4;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentRegNo() {
        return paymentRegNo;
    }

    public void setPaymentRegNo(String paymentRegNo) {
        this.paymentRegNo = paymentRegNo;
    }

    public boolean isInstrumentProvided() {
        return instrumentProvided;
    }

    public void setInstrumentProvided(boolean instrumentProvided) {
        this.instrumentProvided = instrumentProvided;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
