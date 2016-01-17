package prescription.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import prescription.PrescriptionDAO;
import prescription.PrescriptionDTO;
import utility.DAOResult;

/**
 * Created by macintosh on 11/28/15.
 */

@ParentPackage("json-default")
public class PrescriptionAction extends ActionSupport{



    @Action(value = "/rest/prescription/get", results = {@Result(name="success",type="json") })
    public String findPrescriptionById() {
        PrescriptionDAO dao = new PrescriptionDAO();
        prescription = dao.getPrescriptionByPatientId(accountID);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "/rest/prescription/save", results = {@Result(name="success",type="json")})
    public String savePrescription() {
        PrescriptionDAO dao = new PrescriptionDAO();
        prescription = getEntity(PrescriptionDTO.class);
        DAOResult daoResult =  dao.savePrescription(prescription, accountID);
        prescription.setId(daoResult.getObjectId());
        return ActionSupport.SUCCESS;
    }


    PrescriptionDTO prescription = new PrescriptionDTO();

    public PrescriptionDTO getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionDTO prescription) {
        this.prescription = prescription;
    }


    private int accountID;

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
