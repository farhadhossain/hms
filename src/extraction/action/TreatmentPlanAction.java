package extraction.action;

import extraction.ExtractionDAO;
import extraction.TreatmentPlanDTO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import prescription.action.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macintosh on 12/5/15.
 */

@ParentPackage("json-default")
public class TreatmentPlanAction extends ActionSupport {


    @Action(value = "/rest/treatmentPlan/save", results = {@Result(name="success",type="json")})
    public String savePrescription() {
        ExtractionDAO dao = new ExtractionDAO();
        TreatmentPlanDTO[] planArray= getEntity(TreatmentPlanDTO[].class);
         for (TreatmentPlanDTO plan : planArray) {
             dao.saveTreatmentPlan(plan);
         }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "/rest/treatmentPlan/all", results = {@Result(name="success",type="json")})
    public String allTreatmentPlan() {
        ExtractionDAO dao = new ExtractionDAO();
        plans = dao.getTreatmentPlan(accountID, visitID);
        return ActionSupport.SUCCESS;
    }


    private int accountID;

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    private List<TreatmentPlanDTO>  plans = new ArrayList<TreatmentPlanDTO>();

    public List<TreatmentPlanDTO> getPlans() {
        return plans;
    }

    public void setPlans(List<TreatmentPlanDTO> plans) {
        this.plans = plans;
    }

    private int visitID;

    public void setVisitID(int visitID) {
        this.visitID = visitID;
    }

}
