package disease.action;

import disease.DiseaseDTO;
import disease.DiseaseService;
import disease.form.DiseaseForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utility.DAOResult;
import utility.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPatientDisease extends Action{
	public ActionForward execute(ActionMapping p_mapping, ActionForm p_form, HttpServletRequest p_request, HttpServletResponse p_response){
		
		DiseaseForm form = (DiseaseForm) p_form;
		String target = "success";
		DiseaseDTO dto = new DiseaseDTO();
		dto.setRequestParameters(p_request.getParameterMap());
		
		String nad = p_request.getParameter("nad");
		if(nad!=null && nad.equals("1")){
			dto.setNad(1);
		}else{
			dto.setNad(0);
		}
		int[] initializeValue={0};
		dto.setUserId(form.getUserId());
		dto.setDiseaseId(form.getDiseaseId());
		dto.setHistoryId(form.getHistoryId());form.setHistoryId(initializeValue);
		dto.setSymptomId(form.getSymptomId());form.setSymptomId(initializeValue);
		dto.setInspectionId(form.getInspectionId());form.setInspectionId(initializeValue);
		dto.setPalpationId(form.getPalpationId());form.setPalpationId(initializeValue);
		dto.setAuscultationId(form.getAuscultationId());form.setAuscultationId(initializeValue);
		dto.setOthersId(form.getOthersId());form.setOthersId(initializeValue);
		dto.setTreatmentId(form.getTreatmentId());form.setTreatmentId(initializeValue);
		dto.setDiagnosisId(form.getDiagnosisId());form.setDiagnosisId(initializeValue);
		/*
		1. To do automatic get input type: Here get the Ids. And make a String with comma separated ids.
		2. Now request to the database to get info of that ids.
		3. If any ids have type=textbox, then get textbox in this way:   p_request.getParameter("historyid"+id);. This will give let for id 10, historyid10 text box value or select options value
		*/
		
		dto.setTreatmentId157(form.getTreatmentId157());
		dto.setTreatmentId167(form.getTreatmentId167());
		dto.setDiagnosisId129(form.getDiagnosisId129());
		
		dto.setInspecDescripId6(form.getInspecDescripId6());
		dto.setInspecDescripId7(form.getInspecDescripId7());
		dto.setInspecDescripId8(form.getInspecDescripId8());
		
		dto.setInspecDescripId49(form.getInspecDescripId49());
		dto.setInspecDescripId50(form.getInspecDescripId50());
		dto.setInspecDescripId60(form.getInspecDescripId60());
		dto.setInspecDescripId61(form.getInspecDescripId61());

		dto.setPalpaDescripId22_1(form.getPalpaDescripId22_1() == null ? "" : form.getPalpaDescripId22_1());
		dto.setPalpaDescripId22_2(form.getPalpaDescripId22_2() == null ? "" : form.getPalpaDescripId22_2());
		dto.setPalpaDescripId27(form.getPalpaDescripId27());
		dto.setPalpaDescripId28(form.getPalpaDescripId28());
		dto.setPalpaDescripId53(form.getPalpaDescripId53());
		
		dto.setHisDescripId79(form.getHisDescripId79());
		dto.setHisDescripId80(form.getHisDescripId80());
		dto.setHisDescripId81(form.getHisDescripId81());
		dto.setHisDescripId82(form.getHisDescripId82());
		dto.setHisDescripId83(form.getHisDescripId83());
		dto.setHisDescripId84(form.getHisDescripId84());
		dto.setHisDescripId85(form.getHisDescripId85());
		dto.setHisDescripId86(form.getHisDescripId86());
		dto.setHisDescripId87(form.getHisDescripId87());
		dto.setHisDescripId88(form.getHisDescripId88());
		dto.setHisDescripId89(form.getHisDescripId89());
		dto.setHisDescripId90(form.getHisDescripId90());
		dto.setHisDescripId91(form.getHisDescripId91());
		dto.setHisDescripId92(form.getHisDescripId92());
		dto.setHisDescripId93(form.getHisDescripId93());
		
		dto.setOthsDescripId1(form.getOthsDescripId1());
		dto.setOthsDescripId2(form.getOthsDescripId2());
		dto.setOthsDescripId3(form.getOthsDescripId3());
		dto.setOthsDescripId4(form.getOthsDescripId4());
		
		
		dto.setHisDescripId106(form.getHisDescripId106());

		dto.setInspecDescripId69(form.getInspecDescripId69());
		dto.setInspecDescripId72(form.getInspecDescripId72());
		dto.setInspecDescripId74(form.getInspecDescripId74());
		dto.setInspecDescripId75(form.getInspecDescripId75());
		dto.setInspecDescripId77(form.getInspecDescripId77());
		dto.setInspecDescripId78(form.getInspecDescripId78());
		dto.setInspecDescripId80(form.getInspecDescripId80());
		dto.setInspecDescripId81(form.getInspecDescripId81());
		dto.setInspecDescripId84(form.getInspecDescripId84());

		dto.setPalpaDescripId56(form.getPalpaDescripId56());
		dto.setPalpaDescripId57(form.getPalpaDescripId57());
		dto.setPalpaDescripId58_1(form.getPalpaDescripId58_1() == null ? "" : form.getPalpaDescripId58_1());
		dto.setPalpaDescripId58_2(form.getPalpaDescripId58_2() == null ? "" : form.getPalpaDescripId58_2());
		dto.setPalpaDescripId65(form.getPalpaDescripId65());
		dto.setPalpaDescripId66(form.getPalpaDescripId66());
		dto.setPalpaDescripId67_1(form.getPalpaDescripId67_1() == null ? "" : form.getPalpaDescripId67_1());
		dto.setPalpaDescripId67_2(form.getPalpaDescripId67_2());
		
		dto.setLastDateOfDialysis(form.getLastDateOfDialysis());
		dto.setFrequencyOfDialysis(form.getFrequencyOfDialysis());
		
		
		dto.setHisDescripId131(form.getHisDescripId131());
		dto.setHisDescripId136(form.getHisDescripId136());
		dto.setHisDescripId139(form.getHisDescripId139());
		dto.setInspecDescripId87(form.getInspecDescripId87());
		dto.setInspecDescripId92(form.getInspecDescripId92());
		dto.setInspecDescripId93(form.getInspecDescripId93());
		dto.setInspecDescripId96(form.getInspecDescripId96());
		dto.setInspecDescripId99(form.getInspecDescripId99());
		dto.setInspecDescripId103(form.getInspecDescripId103());

		dto.setPalpaDescripId71(form.getPalpaDescripId71());
		dto.setPalpaDescripId72(form.getPalpaDescripId72());
		dto.setPalpaDescripId73(form.getPalpaDescripId73());
		dto.setPalpaDescripId74(form.getPalpaDescripId74());
		dto.setPalpaDescripId75(form.getPalpaDescripId75());
		
		/** Benign cyst /tumor/ swelling **/
		dto.setHisDescripId232(form.getHisDescripId232());
		dto.setHisDescripId229(form.getHisDescripId229());
		dto.setHisDescripId230(form.getHisDescripId230());
		dto.setTreatmentId61(form.getTreatmentId61());
		dto.setSpecialCaseId223_1(form.getSpecialCaseId223_1());
		dto.setSpecialCaseId223_2(form.getSpecialCaseId223_2());
		dto.setSpecialCaseId225(form.getSpecialCaseId225());
		dto.setSpecialCaseId226(form.getSpecialCaseId226());
		dto.setSpecialCaseId227(form.getSpecialCaseId227());
		dto.setSpecialCaseId228(form.getSpecialCaseId228());
		dto.setSpecialCaseId229(form.getSpecialCaseId229());
		dto.setSpecialCaseId232(form.getSpecialCaseId232());
		dto.setSpecialCaseId257(form.getSpecialCaseId257());
		dto.setSpecialCaseId258(form.getSpecialCaseId258());
		dto.setSpecialCaseId259(form.getSpecialCaseId259());
		dto.setSpecialCaseId263(form.getSpecialCaseId263());
		dto.setSpecialCaseId267(form.getSpecialCaseId267());
		dto.setSpecialCaseId270(form.getSpecialCaseId270());
		dto.setSpecialCaseId271(form.getSpecialCaseId271());
		dto.setSpecialCaseId266_1(form.getSpecialCaseId266_1());
		dto.setSpecialCaseId266_2(form.getSpecialCaseId266_2());
		dto.setSpecialCaseId307(form.getSpecialCaseId307());
		dto.setSpecialCaseId308(form.getSpecialCaseId308());
		dto.setSpecialCaseId309(form.getSpecialCaseId309());
		dto.setSpecialCaseId311(form.getSpecialCaseId311());
		dto.setSpecialCaseId269(form.getSpecialCaseId269());
		dto.setSpecialCaseId323(form.getSpecialCaseId323());
		dto.setSpecialCaseId282(form.getSpecialCaseId282());
		dto.setSpecialCaseId335(form.getSpecialCaseId335());
		
		dto.setSpecialCaseId371(form.getSpecialCaseId371());
		dto.setSpecialCaseId338_1(form.getSpecialCaseId338_1());
		dto.setSpecialCaseId338_2(form.getSpecialCaseId338_2());
		dto.setSpecialCaseId339(form.getSpecialCaseId339());
		dto.setSpecialCaseId376_1(form.getSpecialCaseId376_1());
		dto.setSpecialCaseId376_2(form.getSpecialCaseId376_2());
		dto.setSpecialCaseId376_3(form.getSpecialCaseId376_3());
		dto.setSpecialCaseId376_4(form.getSpecialCaseId376_4());
		dto.setSpecialCaseId377_1(form.getSpecialCaseId377_1());
		dto.setSpecialCaseId377_2(form.getSpecialCaseId377_2());
		dto.setSpecialCaseId377_3(form.getSpecialCaseId377_3());
		dto.setSpecialCaseId377_4(form.getSpecialCaseId377_4());
		dto.setSpecialCaseId378_1(form.getSpecialCaseId378_1());
		dto.setSpecialCaseId378_2(form.getSpecialCaseId378_2());
		dto.setSpecialCaseId378_3(form.getSpecialCaseId378_3());
		dto.setSpecialCaseId378_4(form.getSpecialCaseId378_4());
		dto.setSpecialCaseId379_1(form.getSpecialCaseId379_1());
		dto.setSpecialCaseId379_2(form.getSpecialCaseId379_2());
		dto.setSpecialCaseId379_3(form.getSpecialCaseId379_3());
		dto.setSpecialCaseId379_4(form.getSpecialCaseId379_4());
		dto.setSpecialCaseId380_1(form.getSpecialCaseId380_1());
		dto.setSpecialCaseId380_2(form.getSpecialCaseId380_2());
		dto.setSpecialCaseId380_3(form.getSpecialCaseId380_3());
		dto.setSpecialCaseId380_4(form.getSpecialCaseId380_4());
		dto.setSpecialCaseId381_1(form.getSpecialCaseId381_1());
		dto.setSpecialCaseId381_2(form.getSpecialCaseId381_2());
		dto.setSpecialCaseId381_3(form.getSpecialCaseId381_3());
		dto.setSpecialCaseId381_4(form.getSpecialCaseId381_4());
		dto.setSpecialCaseId382_1(form.getSpecialCaseId382_1());
		dto.setSpecialCaseId382_2(form.getSpecialCaseId382_2());
		dto.setSpecialCaseId382_3(form.getSpecialCaseId382_3());
		dto.setSpecialCaseId382_4(form.getSpecialCaseId382_4());
		dto.setSpecialCaseId383_1(form.getSpecialCaseId383_1());
		dto.setSpecialCaseId383_2(form.getSpecialCaseId383_2());
		dto.setSpecialCaseId383_3(form.getSpecialCaseId383_3());
		dto.setSpecialCaseId383_4(form.getSpecialCaseId383_4());
		dto.setSpecialCaseId340(form.getSpecialCaseId340());
		dto.setSpecialCaseId341(form.getSpecialCaseId341());
		dto.setSpecialCaseId384(form.getSpecialCaseId384());
		dto.setSpecialCaseId385(form.getSpecialCaseId385());
		dto.setSpecialCaseId447_1(form.getSpecialCaseId447_1());
		dto.setSpecialCaseId447_2(form.getSpecialCaseId447_2());
		dto.setSpecialCaseId447_3(form.getSpecialCaseId447_3());
		dto.setSpecialCaseId447_4(form.getSpecialCaseId447_4());
		dto.setSpecialCaseId438_1(form.getSpecialCaseId438_1());
		dto.setSpecialCaseId438_2(form.getSpecialCaseId438_2());
		dto.setSpecialCaseId438_3(form.getSpecialCaseId438_3());
		dto.setSpecialCaseId438_4(form.getSpecialCaseId438_4());
		dto.setSpecialCaseId392_1(form.getSpecialCaseId392_1());
		dto.setSpecialCaseId392_2(form.getSpecialCaseId392_2());
		dto.setSpecialCaseId393(form.getSpecialCaseId393());
		dto.setSpecialCaseId406(form.getSpecialCaseId406());
		dto.setSpecialCaseId435(form.getSpecialCaseId435());
		dto.setSpecialCaseId436(form.getSpecialCaseId436());
		dto.setSpecialCaseId437(form.getSpecialCaseId437());
		dto.setSpecialCaseId446(form.getSpecialCaseId446());
		dto.setSpecialCaseId451(form.getSpecialCaseId451());
		dto.setSpecialCaseId452(form.getSpecialCaseId452());
		dto.setSpecialCaseId395(form.getSpecialCaseId395());
		dto.setSpecialCaseId396(form.getSpecialCaseId396());
		dto.setSpecialCaseId397(form.getSpecialCaseId397());
		dto.setSpecialCaseId399(form.getSpecialCaseId399());
		dto.setSpecialCaseId402(form.getSpecialCaseId402());
		dto.setSpecialCaseId404(form.getSpecialCaseId404());
		dto.setSpecialCaseId405(form.getSpecialCaseId405());
		dto.setSpecialCaseId449(form.getSpecialCaseId449());
		dto.setSpecialCaseId450(form.getSpecialCaseId450());	
		dto.setSpecialCaseId454_1(form.getSpecialCaseId454_1());
		dto.setSpecialCaseId454_2(form.getSpecialCaseId454_2());
		dto.setSpecialCaseId454_3(form.getSpecialCaseId454_3());
		dto.setSpecialCaseId454_4(form.getSpecialCaseId454_4());
		dto.setDiagnosisId47(form.getDiagnosisId47());
		dto.setDiagnosisId62(form.getDiagnosisId62());
		dto.setDiagnosisId63(form.getDiagnosisId63());
		dto.setDiagnosisId75(form.getDiagnosisId75());
		dto.setDiagnosisId87(form.getDiagnosisId87());
		dto.setDiagnosisId90(form.getDiagnosisId90());
		dto.setTreatmentId68(form.getTreatmentId68());
		dto.setTreatmentId77(form.getTreatmentId77());
		dto.setTreatmentId82(form.getTreatmentId82());
		dto.setTreatmentId83(form.getTreatmentId83());
		dto.setTreatmentId84(form.getTreatmentId84());
		
		/** Resparatory Disease **/
		dto.setOtherHistoryRes(form.getOtherHistoryRes());
		dto.setFrequency(form.getFrequency());
		dto.setSeverity(form.getSeverity());
		dto.setDuration(form.getDuration());
		dto.setPeriodOfRemission(form.getPeriodOfRemission());
		dto.setOtherPertFact(form.getOtherPertFact());
		dto.setPrticpFactId(form.getPrticpFactId());
		
		/** Cardiovascular Disease **/
		dto.setApexBeat(form.getApexBeat());
		dto.setBloodPressure(form.getBloodPressure());
		dto.setHeartRatePerMin(form.getHeartRatePerMin());
		dto.setHeightVsPressure(form.getHeightVsPressure());
		
		dto.setComplications(form.getComplications());
		dto.setSpecialNotes(form.getSpecialNotes());
		
		/** Oral and Maxillofacial Trauma **/
		dto.setOralAndMaxillofacTrauma(form.getOralAndMaxillofacTrauma());
		dto.setOmLocation(form.getOmLocation());
		dto.setOmDuration(form.getOmDuration());    
		dto.setOmTimeframe(form.getOmTimeframe());
		dto.setOmIntensity(form.getOmIntensity());
		dto.setOmMechanism(form.getOmMechanism()); 
		dto.setEyeOpening(form.getEyeOpening());
		dto.setVerbalResponse(form.getVerbalResponse()); 
		dto.setMotorResponse(form.getMotorResponse());
		dto.setTotalScore(form.getTotalScore());
		dto.setComments(form.getComments());
		dto.setMechanismOfInjury(form.getMechanismOfInjury());
		dto.setReflex(form.getReflex());
		dto.setMotorFunction(form.getMotorFunction());
		dto.setMentalStatus(form.getMentalStatus());
		dto.setRectalTone(form.getRectalTone());
		dto.setOmSpninalCordOther(form.getOmSpninalCordOther());
		/*dto.setHisDescripId140_1(form.getHisDescripId140_1());
		dto.setHisDescripId140_2(form.getHisDescripId140_2());*/
		dto.setEyeOpening(form.getEyeOpening());
		dto.setVerbalResponse(form.getVerbalResponse());
		dto.setMotorResponse(form.getMotorResponse());
		dto.setTotalScore(form.getTotalScore());
		dto.setHisDescripId141_1(form.getHisDescripId141_1());
		dto.setHisDescripId141_2(form.getHisDescripId141_2());
		dto.setHisDescripId148(form.getHisDescripId148());
		dto.setHisDescripId178_1(form.getHisDescripId178_1());
		dto.setHisDescripId178_2(form.getHisDescripId178_2());
		dto.setHisDescripId178_3(form.getHisDescripId178_3());
		dto.setHisDescripId178_4(form.getHisDescripId178_4());
		
		dto.setHisDescripId174(form.getHisDescripId174());
		dto.setHisDescripId175(form.getHisDescripId175());
		dto.setHisDescripId176(form.getHisDescripId176());
		dto.setHisDescripId177(form.getHisDescripId177());
		dto.setHisDescripId181(form.getHisDescripId181());
		
		dto.setHisDescripId162(form.getHisDescripId162());
		dto.setHisDescripId165(form.getHisDescripId165());
		dto.setHisDescripId167(form.getHisDescripId167());
		dto.setHisDescripId168(form.getHisDescripId168());
		dto.setHisDescripId169(form.getHisDescripId169());
		dto.setHisDescripId170(form.getHisDescripId170());
		dto.setHisDescripId171(form.getHisDescripId171());
		dto.setHisDescripId173(form.getHisDescripId173());
		dto.setInspecDescripId111(form.getInspecDescripId111());
		dto.setInspecDescripId112(form.getInspecDescripId112());
		dto.setInspecDescripId116(form.getInspecDescripId116());
		dto.setInspecDescripId117(form.getInspecDescripId117());
		
		dto.setOthsDescripId8(form.getOthsDescripId8());
		dto.setOthsDescripId12(form.getOthsDescripId12());
		dto.setOthsDescripId13(form.getOthsDescripId13());
		dto.setOthsDescripId14(form.getOthsDescripId14());
		dto.setOthsDescripId15(form.getOthsDescripId15());
		dto.setOthsDescripId16(form.getOthsDescripId16());
		dto.setOthsDescripId17(form.getOthsDescripId17());
		dto.setOthsDescripId20(form.getOthsDescripId20());
		dto.setOthsDescripId21(form.getOthsDescripId21());
		dto.setOthsDescripId22(form.getOthsDescripId22());
		dto.setOthsDescripId25(form.getOthsDescripId25());
		
		
		
		dto.setSpecialCaseId(form.getSpecialCaseId());
		dto.setSpecialCaseId1(form.getSpecialCaseId1());
		dto.setSpecialCaseId2(form.getSpecialCaseId2());
		dto.setSpecialCaseId3(form.getSpecialCaseId3());
		dto.setSpecialCaseId4(form.getSpecialCaseId4());
		dto.setSpecialCaseId6(form.getSpecialCaseId6());
		dto.setSpecialCaseId7(form.getSpecialCaseId7());
		dto.setSpecialCaseId8(form.getSpecialCaseId8());
		dto.setSpecialCaseId9_1(form.getSpecialCaseId9_1());
		dto.setSpecialCaseId9_2(form.getSpecialCaseId9_2());
		dto.setSpecialCaseId9_3(form.getSpecialCaseId9_3());
		dto.setSpecialCaseId9_4(form.getSpecialCaseId9_4());
		dto.setSpecialCaseId10_1(form.getSpecialCaseId10_1());
		dto.setSpecialCaseId10_2(form.getSpecialCaseId10_2());
		dto.setSpecialCaseId10_3(form.getSpecialCaseId10_3());
		dto.setSpecialCaseId10_4(form.getSpecialCaseId10_4());
		dto.setSpecialCaseId11(form.getSpecialCaseId11());
		dto.setSpecialCaseId12_1(form.getSpecialCaseId12_1());
		dto.setSpecialCaseId12_2(form.getSpecialCaseId12_2());
		dto.setSpecialCaseId12_3(form.getSpecialCaseId12_3());
		dto.setSpecialCaseId12_4(form.getSpecialCaseId12_4());
		dto.setSpecialCaseId13(form.getSpecialCaseId13());
		
		dto.setSpecialCaseId14(form.getSpecialCaseId14());
		dto.setSpecialCaseId15(form.getSpecialCaseId15());
		dto.setSpecialCaseId16(form.getSpecialCaseId16());
		dto.setSpecialCaseId17(form.getSpecialCaseId17());
		dto.setSpecialCaseId18(form.getSpecialCaseId18());
		dto.setSpecialCaseId19(form.getSpecialCaseId19());
		dto.setSpecialCaseId20(form.getSpecialCaseId20());
		dto.setSpecialCaseId21(form.getSpecialCaseId21());
		dto.setSpecialCaseId22(form.getSpecialCaseId22());
		dto.setSpecialCaseId23(form.getSpecialCaseId23());
		dto.setSpecialCaseId24(form.getSpecialCaseId24());
		dto.setSpecialCaseId25(form.getSpecialCaseId25());
		dto.setSpecialCaseId26(form.getSpecialCaseId26());

		dto.setHisDescripId194(form.getHisDescripId194());
		dto.setHisDescripId195(form.getHisDescripId195());
		dto.setHisDescripId196(form.getHisDescripId196());
		dto.setHisDescripId198(form.getHisDescripId198());
		dto.setSpecialCaseId27(form.getSpecialCaseId27());
		dto.setSpecialCaseId28(form.getSpecialCaseId28());
		dto.setSpecialCaseId30(form.getSpecialCaseId30());
		dto.setSpecialCaseId31(form.getSpecialCaseId31());
		dto.setSpecialCaseId32(form.getSpecialCaseId32());
		dto.setSpecialCaseId33(form.getSpecialCaseId33());
		dto.setSpecialCaseId34(form.getSpecialCaseId34());
		dto.setSpecialCaseId35(form.getSpecialCaseId35());
		dto.setSpecialCaseId36(form.getSpecialCaseId36());
		dto.setSpecialCaseId37(form.getSpecialCaseId37());
		dto.setSpecialCaseId39(form.getSpecialCaseId39());
		dto.setSpecialCaseId40(form.getSpecialCaseId40());
		dto.setSpecialCaseId42(form.getSpecialCaseId42());
		dto.setSpecialCaseId43(form.getSpecialCaseId43());
		
		
		dto.setSpecialCaseId47(form.getSpecialCaseId47());
		dto.setSpecialCaseId48(form.getSpecialCaseId48());
		dto.setSpecialCaseId49(form.getSpecialCaseId49());
		dto.setSpecialCaseId50(form.getSpecialCaseId50());
		dto.setSpecialCaseId52(form.getSpecialCaseId52());
		dto.setSpecialCaseId53(form.getSpecialCaseId53());
		dto.setSpecialCaseId54(form.getSpecialCaseId54());
		dto.setSpecialCaseId55(form.getSpecialCaseId55());
		dto.setSpecialCaseId56(form.getSpecialCaseId56());
		dto.setSpecialCaseId57(form.getSpecialCaseId57());
		dto.setSpecialCaseId58(form.getSpecialCaseId58());
		
		dto.setSpecialCaseId62(form.getSpecialCaseId62());
		dto.setSpecialCaseId63(form.getSpecialCaseId63());
		dto.setSpecialCaseId64(form.getSpecialCaseId64());
		dto.setSpecialCaseId71(form.getSpecialCaseId71());
		dto.setSpecialCaseId74(form.getSpecialCaseId74());
		dto.setSpecialCaseId75(form.getSpecialCaseId75());
		
		dto.setHisDescripId211(form.getHisDescripId211());
		dto.setInspecDescripId118(form.getInspecDescripId118());
		dto.setInspecDescripId119(form.getInspecDescripId119());
		dto.setInspecDescripId125(form.getInspecDescripId125());
		dto.setInspecDescripId134(form.getInspecDescripId134());
		dto.setInspecDescripId135_1(form.getInspecDescripId135_1());
		dto.setInspecDescripId135_2(form.getInspecDescripId135_2());
		dto.setInspecDescripId141(form.getInspecDescripId141());
		dto.setInspecDescripId142(form.getInspecDescripId142());
		dto.setInspecDescripId137(form.getInspecDescripId137());
		dto.setInspecDescripId138(form.getInspecDescripId138());
		dto.setInspecDescripId139(form.getInspecDescripId139());
		dto.setInspecDescripId140(form.getInspecDescripId140());
		
		dto.setSpecialCaseId96(form.getSpecialCaseId96());
		dto.setSpecialCaseId97(form.getSpecialCaseId97());
		dto.setSpecialCaseId98(form.getSpecialCaseId98());
		dto.setSpecialCaseId99(form.getSpecialCaseId99());
		dto.setSpecialCaseId100(form.getSpecialCaseId100());
		dto.setSpecialCaseId101(form.getSpecialCaseId101());
		dto.setSpecialCaseId102(form.getSpecialCaseId102());
		dto.setSpecialCaseId103(form.getSpecialCaseId103());
		dto.setSpecialCaseId104(form.getSpecialCaseId104());
		dto.setSpecialCaseId105(form.getSpecialCaseId105());
		dto.setSpecialCaseId106(form.getSpecialCaseId106());
		dto.setSpecialCaseId107_1(form.getSpecialCaseId107_1());
		dto.setSpecialCaseId107_2(form.getSpecialCaseId107_2());
		dto.setSpecialCaseId107_3(form.getSpecialCaseId107_3());
		dto.setSpecialCaseId107_4(form.getSpecialCaseId107_4());
		dto.setSpecialCaseId108_1(form.getSpecialCaseId108_1());
		dto.setSpecialCaseId108_2(form.getSpecialCaseId108_2());
		dto.setSpecialCaseId108_3(form.getSpecialCaseId108_3());
		dto.setSpecialCaseId108_4(form.getSpecialCaseId108_4());
		dto.setSpecialCaseId109_1(form.getSpecialCaseId109_1());
		dto.setSpecialCaseId109_2(form.getSpecialCaseId109_2());
		dto.setSpecialCaseId109_3(form.getSpecialCaseId109_3());
		dto.setSpecialCaseId109_4(form.getSpecialCaseId109_4());
		dto.setSpecialCaseId110_1(form.getSpecialCaseId110_1());
		dto.setSpecialCaseId110_2(form.getSpecialCaseId110_2());
		dto.setSpecialCaseId110_3(form.getSpecialCaseId110_3());
		dto.setSpecialCaseId110_4(form.getSpecialCaseId110_4());
		dto.setSpecialCaseId111_1(form.getSpecialCaseId111_1());
		dto.setSpecialCaseId111_2(form.getSpecialCaseId111_2());
		dto.setSpecialCaseId111_3(form.getSpecialCaseId111_3());
		dto.setSpecialCaseId111_4(form.getSpecialCaseId111_4());
		dto.setSpecialCaseId112_1(form.getSpecialCaseId112_1());
		dto.setSpecialCaseId112_2(form.getSpecialCaseId112_2());
		dto.setSpecialCaseId112_3(form.getSpecialCaseId112_3());
		dto.setSpecialCaseId112_4(form.getSpecialCaseId112_4());
		dto.setSpecialCaseId113_1(form.getSpecialCaseId113_1());
		dto.setSpecialCaseId113_2(form.getSpecialCaseId113_2());
		dto.setSpecialCaseId113_3(form.getSpecialCaseId113_3());
		dto.setSpecialCaseId113_4(form.getSpecialCaseId113_4());
		dto.setSpecialCaseId114_1(form.getSpecialCaseId114_1());
		dto.setSpecialCaseId114_2(form.getSpecialCaseId114_2());
		dto.setSpecialCaseId114_3(form.getSpecialCaseId114_3());
		dto.setSpecialCaseId114_4(form.getSpecialCaseId114_4());
		dto.setSpecialCaseId115_1(form.getSpecialCaseId115_1());
		dto.setSpecialCaseId115_2(form.getSpecialCaseId115_2());
		dto.setSpecialCaseId115_3(form.getSpecialCaseId115_3());
		dto.setSpecialCaseId115_4(form.getSpecialCaseId115_4());
		
		
		dto.setPalpaDescripId88(form.getPalpaDescripId88());
		dto.setPalpaDescripId93(form.getPalpaDescripId93());
		dto.setPalpaDescripId100(form.getPalpaDescripId100());
		
		dto.setHisDescripId204(form.getHisDescripId204());
		dto.setHisDescripId219(form.getHisDescripId219());
		dto.setSpecialCaseId116(form.getSpecialCaseId116());
		dto.setSpecialCaseId117(form.getSpecialCaseId117());
		dto.setSpecialCaseId118(form.getSpecialCaseId118());
		dto.setSpecialCaseId119(form.getSpecialCaseId119());
		dto.setSpecialCaseId123(form.getSpecialCaseId123());
		dto.setSpecialCaseId125(form.getSpecialCaseId125());
		dto.setSpecialCaseId126_1(form.getSpecialCaseId126_1());
		dto.setSpecialCaseId126_2(form.getSpecialCaseId126_2());
		dto.setSpecialCaseId127_1(form.getSpecialCaseId127_1());
		dto.setSpecialCaseId127_2(form.getSpecialCaseId127_2());
		dto.setSpecialCaseId128(form.getSpecialCaseId128());
		dto.setSpecialCaseId129(form.getSpecialCaseId129());
		dto.setSpecialCaseId130(form.getSpecialCaseId130());
		dto.setSpecialCaseId131(form.getSpecialCaseId131());
		dto.setSpecialCaseId132(form.getSpecialCaseId132());
		dto.setSpecialCaseId134(form.getSpecialCaseId134());
		dto.setSpecialCaseId135(form.getSpecialCaseId135());
		dto.setSpecialCaseId136(form.getSpecialCaseId136());
		dto.setSpecialCaseId137(form.getSpecialCaseId137());
		dto.setSpecialCaseId138_1(form.getSpecialCaseId138_1());
		dto.setSpecialCaseId138_2(form.getSpecialCaseId138_2());
		dto.setSpecialCaseId139(form.getSpecialCaseId139());
		dto.setSpecialCaseId141_1(form.getSpecialCaseId141_1());
		dto.setSpecialCaseId141_2(form.getSpecialCaseId141_2());
		dto.setSpecialCaseId141_3(form.getSpecialCaseId141_3());
		dto.setSpecialCaseId141_4(form.getSpecialCaseId141_4());
		dto.setSpecialCaseId121_1(form.getSpecialCaseId121_1());
		dto.setSpecialCaseId121_2(form.getSpecialCaseId121_2());
		dto.setSpecialCaseId122(form.getSpecialCaseId122());
		dto.setSpecialCaseId152(form.getSpecialCaseId152());
		dto.setSpecialCaseId175(form.getSpecialCaseId175());
		dto.setSpecialCaseId176(form.getSpecialCaseId176());
		dto.setSpecialCaseId177(form.getSpecialCaseId177());
		dto.setSpecialCaseId178(form.getSpecialCaseId178());
		dto.setSpecialCaseId179(form.getSpecialCaseId179());
		dto.setSpecialCaseId180(form.getSpecialCaseId180());
		dto.setSpecialCaseId133(form.getSpecialCaseId133());
		dto.setSpecialCaseId188(form.getSpecialCaseId188());
		dto.setSpecialCaseId189(form.getSpecialCaseId189());
		dto.setSpecialCaseId190(form.getSpecialCaseId190());
		dto.setSpecialCaseId191(form.getSpecialCaseId191());
		dto.setSpecialCaseId192(form.getSpecialCaseId192());
		
		dto.setSpecialCaseId193(form.getSpecialCaseId193());
		dto.setSpecialCaseId194(form.getSpecialCaseId194());
		dto.setSpecialCaseId195(form.getSpecialCaseId195());
		dto.setSpecialCaseId196(form.getSpecialCaseId196());
		dto.setSpecialCaseId197(form.getSpecialCaseId197());
		dto.setSpecialCaseId198(form.getSpecialCaseId198());

		dto.setSpecialCaseId204(form.getSpecialCaseId204());
		dto.setSpecialCaseId205(form.getSpecialCaseId205());
		dto.setSpecialCaseId206(form.getSpecialCaseId206());
		dto.setSpecialCaseId207_1(form.getSpecialCaseId207_1());
		dto.setSpecialCaseId207_2(form.getSpecialCaseId207_2());
		dto.setSpecialCaseId208(form.getSpecialCaseId208());
		dto.setSpecialCaseId209(form.getSpecialCaseId209());
		dto.setSpecialCaseId210(form.getSpecialCaseId210());
		
		dto.setTreatmentId5(form.getTreatmentId5());
		dto.setTreatmentId9(form.getTreatmentId9());
		dto.setTreatmentId11(form.getTreatmentId11());
		dto.setTreatmentId13(form.getTreatmentId13());
		dto.setTreatmentId14(form.getTreatmentId14());
		dto.setTreatmentId15(form.getTreatmentId15());
		dto.setTreatmentId18(form.getTreatmentId18());
		dto.setTreatmentId118(form.getTreatmentId118());
		
		dto.setDiagnosisId100(form.getDiagnosisId100());
		dto.setDiagnosisId101(form.getDiagnosisId101());
		dto.setDiagnosisId102(form.getDiagnosisId102());
		dto.setDiagnosisId103(form.getDiagnosisId103());
		dto.setDiagnosisId104(form.getDiagnosisId104());
		dto.setDiagnosisId105(form.getDiagnosisId105());
		dto.setDiagnosisId106(form.getDiagnosisId106());
		dto.setDiagnosisId107(form.getDiagnosisId107());
		dto.setDiagnosisId108(form.getDiagnosisId108());
		dto.setDiagnosisId109(form.getDiagnosisId109());
		dto.setDiagnosisId110(form.getDiagnosisId110());
		dto.setDiagnosisId111(form.getDiagnosisId111());
		dto.setDiagnosisId112(form.getDiagnosisId112());
		
		dto.setComplications(form.getComplications());
		dto.setSpecialNotes(form.getSpecialNotes());
		
		dto.setTreatmentId34(form.getTreatmentId34());
		dto.setTreatmentId35(form.getTreatmentId35());
		dto.setTreatmentId36(form.getTreatmentId36());
		dto.setTreatmentId37(form.getTreatmentId37());
		dto.setTreatmentId38(form.getTreatmentId38());
		dto.setTreatmentId39(form.getTreatmentId39());
		
		
		
		dto.setDiagnosisId18(form.getDiagnosisId18());
		dto.setDiagnosisId17(form.getDiagnosisId17());
		dto.setDiagnosisId19(form.getDiagnosisId19());
		dto.setDiagnosisId20(form.getDiagnosisId20());
		
		dto.setInspecDescripId13(form.getInspecDescripId13());
		dto.setInspecDescripId163(form.getInspecDescripId163());
		
		dto.setSpecialCaseId216(form.getSpecialCaseId216());
		dto.setSpecialCaseId217(form.getSpecialCaseId217());
		dto.setSpecialCaseId218(form.getSpecialCaseId218());
		dto.setSpecialCaseId219(form.getSpecialCaseId219());
		dto.setSpecialCaseId220(form.getSpecialCaseId220());
		
		/** TMJ ankylosis **/
		dto.setHisDescripId222(form.getHisDescripId222());
		dto.setInspecDescripId151(form.getInspecDescripId151());
		dto.setInspecDescripId155(form.getInspecDescripId155());
		dto.setInspecDescripId162(form.getInspecDescripId162());
		dto.setInspecDescripId148(form.getInspecDescripId148());
		dto.setInspecDescripId156(form.getInspecDescripId156());
		dto.setInspecDescripId157(form.getInspecDescripId157());
		dto.setInspecDescripId158(form.getInspecDescripId158());
		dto.setPalpaDescripId107(form.getPalpaDescripId107());
		dto.setPalpaDescripId108(form.getPalpaDescripId108());
		dto.setPalpaDescripId112(form.getPalpaDescripId112());
		dto.setPalpaDescripId113(form.getPalpaDescripId113());
		dto.setPalpaDescripId114(form.getPalpaDescripId114());
		
		dto.setTreatmentId40(form.getTreatmentId40());
		dto.setTreatmentId41(form.getTreatmentId41());
		dto.setTreatmentId48(form.getTreatmentId48());
		dto.setDiagnosisId23(form.getDiagnosisId23());
		dto.setDiagnosisId24(form.getDiagnosisId24());
		
		dto.setHisDescripId223(form.getHisDescripId223());
		
		dto.setPalpaDescripId90(form.getPalpaDescripId90());
		dto.setPalpaDescripId115(form.getPalpaDescripId115());

		dto.setInspecDescripId165(form.getInspecDescripId165());
		dto.setInspecDescripId165_2(form.getInspecDescripId165_2());
		dto.setInspecDescripId166(form.getInspecDescripId166());
		dto.setInspecDescripId168(form.getInspecDescripId168());
		dto.setInspecDescripId168_2(form.getInspecDescripId168_2());
		dto.setInspecDescripId178(form.getInspecDescripId178());
		dto.setInspecDescripId290(form.getInspecDescripId290());
		dto.setInspecDescripId332(form.getInspecDescripId332());
		dto.setInspecDescripId351(form.getInspecDescripId351());
		dto.setInspecDescripId355(form.getInspecDescripId355());
		dto.setInspecDescripId356(form.getInspecDescripId356());
		dto.setInspecDescripId178_2(form.getInspecDescripId178_2());
		dto.setInspecDescripId178_3(form.getInspecDescripId178_3());
		dto.setInspecDescripId178_4(form.getInspecDescripId178_4());
		dto.setInspecDescripId178_5(form.getInspecDescripId178_5());
		dto.setInspecDescripId178_6(form.getInspecDescripId178_6());

		//Orthognathic Surgery
		dto.setInspecDescripId487_1(form.getInspecDescripId487_1());
		dto.setInspecDescripId487_2(form.getInspecDescripId487_2());
		dto.setInspecDescripId487_3(form.getInspecDescripId487_3());
		dto.setInspecDescripId487_4(form.getInspecDescripId487_4());

		dto.setInspecDescripId488_1(form.getInspecDescripId488_1());
		dto.setInspecDescripId488_2(form.getInspecDescripId488_2());
		dto.setInspecDescripId488_3(form.getInspecDescripId488_3());
		dto.setInspecDescripId488_4(form.getInspecDescripId488_4());

		dto.setInspecDescripId489_1(form.getInspecDescripId489_1());
		dto.setInspecDescripId489_2(form.getInspecDescripId489_2());
		dto.setInspecDescripId489_3(form.getInspecDescripId489_3());
		dto.setInspecDescripId489_4(form.getInspecDescripId489_4());

		dto.setInspecDescripId490_1(form.getInspecDescripId490_1());
		dto.setInspecDescripId490_2(form.getInspecDescripId490_2());
		dto.setInspecDescripId490_3(form.getInspecDescripId490_3());
		dto.setInspecDescripId490_4(form.getInspecDescripId490_4());

		dto.setInspecDescripId536_1(form.getInspecDescripId536_1());
		dto.setInspecDescripId536_2(form.getInspecDescripId536_2());
		dto.setInspecDescripId537_1(form.getInspecDescripId537_1());
		dto.setInspecDescripId537_2(form.getInspecDescripId537_2());
		
		DiseaseService service = new DiseaseService();
		DAOResult daoResult=service.addPatientDiseaseInfo(dto);
		if(daoResult.isValid()==false){
			target="failure";
		}
		p_request.getSession(true).setAttribute(SessionManager.PatientRespratory, daoResult.getMessage());
		return (p_mapping.findForward(target));
	}
}
