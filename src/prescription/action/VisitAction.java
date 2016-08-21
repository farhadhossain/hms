package prescription.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import prescription.VisitDAO;
import prescription.VisitDTO;

import java.util.List;

/**
 * Created by macintosh on 4/28/16.
 */
@ParentPackage("json-default")
public class VisitAction extends ActionSupport{

    private String result = ActionSupport.SUCCESS;

    @Action(value = "/rest/visit/save", results = {@Result(name="success",type="json")})
    public String saveVisit() {
        VisitDAO dao = new VisitDAO();
        VisitDTO visitDTO= getEntity(VisitDTO.class);
        dao.create(visitDTO);
        return result;
    }


    @Action(value = "/rest/visit/all", results = {@Result(name="success",type="json")})
    public String findVisits() {
        VisitDAO dao = new VisitDAO();
        visits = dao.getVisitsByPatientId(accountID);
        return result;
    }


    public String getResult() {
        return result;
    }

    private int accountID;

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    private List<VisitDTO> visits;

    public List<VisitDTO> getVisits() {
        return visits;
    }
}
