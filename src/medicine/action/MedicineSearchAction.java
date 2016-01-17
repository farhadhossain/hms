package medicine.action;
import com.opensymphony.xwork2.ActionSupport;
import disease.DiseaseDTO;
import disease.DiseaseService;
import extraction.ExtractionDAO;
import extraction.ExtractionItem;
import medicine.MedicineDAO;
import medicine.MedicineDTO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Created by macintosh on 11/25/15.
 */


@ParentPackage("json-default")
public class MedicineSearchAction extends ActionSupport{

    private List<MedicineDTO> medicineList;

    public List<MedicineDTO> getMedicineList() {
        return medicineList;
    }
    @Action(value = "/rest/medicine/search", results = {@Result(type="json",params = {"includeProperties", "medicineList\\[\\d+\\]\\.medicineID,medicineList\\[\\d+\\]\\.medicineName"})})
    public String searchMedicine() {
        MedicineDAO dao = new MedicineDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        medicineList = dao.searchMedicine(request.getParameter("keyword"));
        return ActionSupport.SUCCESS;
    }




    private Map<Integer,String> diseaseList;
    public Map<Integer,String>  getDiseaseList() {
        return diseaseList;
    }

    @Action(value = "/rest/disease/get/all", results = {@Result(name="success",type="json")})
    public String findAllDisease() {
        DiseaseService service = new DiseaseService();
        diseaseList = service.getSysDiseaseInfo(1);
        return ActionSupport.SUCCESS;
    }



    List<ExtractionItem> extractionItems;

    public List<ExtractionItem> getExtractionItems() {
        return extractionItems;
    }

    @Action(value = "/rest/extraction/get/all", results = {@Result(name="success",type="json")})
    public String findAllExtraction() {
        ExtractionDAO dao = new ExtractionDAO();
        extractionItems = dao.getExtractionItems();
        return ActionSupport.SUCCESS;
    }

}
