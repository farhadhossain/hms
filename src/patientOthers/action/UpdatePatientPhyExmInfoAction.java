package patientOthers.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import patientOthers.PatientOthersDTO;
import patientOthers.PatientOthersService;
import patientOthers.form.PatientOthersForm;


import utility.DAOResult;
import utility.SessionManager;


public class UpdatePatientPhyExmInfoAction extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		PatientOthersForm form = (PatientOthersForm) p_form;
		String target = "success";
		PatientOthersDTO dto = new PatientOthersDTO();
		
		dto.setUserId(form.getUserId());
		dto.setVsTemp(form.getVsTemp());
		dto.setVsPulse(form.getVsPulse());
		dto.setVsRate(form.getVsRate());
		dto.setVsBloodPre(form.getVsBloodPre());
		dto.setBsHeight(form.getBsHeight());
		dto.setBsWeight(form.getBsWeight());
		dto.setBsBMI(form.getBsBMI());
		dto.setBsWC(form.getBsWC());
		dto.setClInspec(form.getClInspec());
		dto.setClPalpi(form.getClPalpi());
		dto.setClPrec(form.getClPrec());
		dto.setClAusc(form.getClAusc());
		dto.setJvp(form.getJvp());
		dto.setCarotidPulsCar(form.getCarotidPulsCar());
		dto.setApexImp(form.getApexImp());
		dto.setParastImp(form.getParastImp());

		dto.setPalpaHs(form.getPalpaHs());
		dto.setThrill(form.getThrill());
		dto.setHs(form.getHs());
		dto.setEc(form.getEc());
		dto.setOs(form.getOs());
		dto.setMurmur(form.getMurmur());
		dto.setPeripheralPul(form.getPeripheralPul());
		dto.setInspecOthers(form.getInspecOthers());
		dto.setInspectionId(form.getInspectionId());
		dto.setPalpationId(form.getPalpationId());
		dto.setPalpaOthers(form.getPalpaOthers());
		dto.setPrecusNote(form.getPrecusNote());
		dto.setAuscultion(form.getAuscultion());
		dto.setNeckVein(form.getNeckVein());
		dto.setMovOfNeck(form.getMovOfNeck());
		dto.setThyroidGland(form.getThyroidGland());
		dto.setCarotidPulse(form.getCarotidPulse());
		dto.setGenIntFunc(form.getGenIntFunc());
		dto.setMotorFunc(form.getMotorFunc());
		dto.setSensory(form.getSensory());
		dto.setCarnialNerve(form.getCarnialNerve());
		dto.setOtherDescip(form.getOtherDescip());
		
		PatientOthersService service = new PatientOthersService();
		DAOResult daoResult=service.updatePatientPhyExmInfoDTO(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientAdd, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
