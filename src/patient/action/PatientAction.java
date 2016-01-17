package patient.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import patient.PatientDAO;
import patient.PatientDTO;
import prescription.PrescriptionDAO;
import prescription.PrescriptionDTO;
import prescription.action.ActionSupport;

/**
 * Created by macintosh on 12/25/15.
 */
@ParentPackage("json-default")
public class PatientAction extends ActionSupport {

    @Action(value = "/rest/patient/get", results = {@Result(name="success",type="json") })
    public String findPatientById() {
        PatientDAO dao = new PatientDAO();
        patienr = dao.getPatientDTObyId(accountID);
        return ActionSupport.SUCCESS;
    }



    PatientDTO patienr = new PatientDTO();

    public PatientDTO getPatienr() {
        return patienr;
    }

    private int accountID;

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

}
