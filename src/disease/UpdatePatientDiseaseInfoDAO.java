package disease;

import prescription.VisitDAO;
import utility.DAOResult;

import java.sql.Connection;
import java.sql.Statement;

public class UpdatePatientDiseaseInfoDAO {
	
	public DAOResult addPatientDiseaseInfo(DiseaseDTO dto) {
		DAOResult daoRes = new DAOResult();
		daoRes.setMessage("Added Successfully");
		int sizeOfArray=0;
		Connection conn = null;
		Statement stmt = null;
		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserId());
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			

			stmt.executeUpdate("delete from tbl_patient_disease_history where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and history_id in (select id from tbl_disease_history where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_symptom where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and symptom_id in (select id from tbl_disease_symptom where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_inspection where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and inspection_id in (select id from tbl_disease_inspection where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_palpation where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and palpation_id in (select id from tbl_disease_palpation where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_auscultation where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and auscultation_id in (select id from tbl_disease_auscultation where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_others where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and others_id in (select id from tbl_disease_others where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_special_case_details where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and sp_case_id in (select id from tbl_disease_special_case_details where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_treatment where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and treatment_id in (select id from tbl_disease_treatment where dis_id="+dto.getDiseaseId()+")");
			stmt.executeUpdate("delete from tbl_patient_disease_diagnosis where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId+" and diagnosis_id in (select id from tbl_disease_diagnosis where dis_id="+dto.getDiseaseId()+")");
			
			if(dto.getOralAndMaxillofacTrauma()>0){
				stmt.executeUpdate("delete from tbl_oral_and_maxillofacial_trauma where patient_id="+dto.getUserId()+ " and visit_id="+currentVisitId);
				stmt.execute("insert into tbl_oral_and_maxillofacial_trauma(patient_id,visit_id, omLocation, omDuration, omTimeframe, omIntensity, omMechanism, eyeOpening, verbalResponse, motorResponse, totalScore, comments, mechanismOfInjury, reflex, motorFunction, mentalStatus, rectalTone, omSpninalCordOther) " +
						"values("+dto.getUserId()+","+currentVisitId+",'"+dto.getOmLocation()+"','"+dto.getOmDuration()+"','"+dto.getOmTimeframe()+"','"+dto.getOmIntensity()+"','"+dto.getOmMechanism()+"','"+dto.getEyeOpening()+"','"+dto.getVerbalResponse()+"','"+dto.getMotorResponse()+"','"+dto.getTotalScore()+"','"+dto.getComments()+"','"+dto.getMechanismOfInjury()+"','"+dto.getReflex()+"','"+dto.getMotorFunction()+"','"+dto.getMentalStatus()+"','"+dto.getRectalTone()+"', '"+dto.getOmSpninalCordOther()+"')");
			}
			
			{
				try{
					sizeOfArray=dto.getSpecialCaseId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				String tail_1="", tail_2="";
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getSpecialCaseId()[i]==1){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId1();
					}else if(dto.getSpecialCaseId()[i]==2){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId2();
					}else if(dto.getSpecialCaseId()[i]==3){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId3();
					}else if(dto.getSpecialCaseId()[i]==4){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId4();
					}else if(dto.getSpecialCaseId()[i]==6){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId6();
					}else if(dto.getSpecialCaseId()[i]==7){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId7();
					}else if(dto.getSpecialCaseId()[i]==8){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId8();
					}else if(dto.getSpecialCaseId()[i]==9){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; 
						tail_2=", '"+dto.getSpecialCaseId9_1()+"', '"+dto.getSpecialCaseId9_2()+"', '"+dto.getSpecialCaseId9_3()+"', '"+dto.getSpecialCaseId9_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==10){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId10_1()+"', '"+dto.getSpecialCaseId10_2()+"', '"+dto.getSpecialCaseId10_3()+"', '"+dto.getSpecialCaseId10_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==11){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId11();
					}else if(dto.getSpecialCaseId()[i]==12){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId12_1()+"', '"+dto.getSpecialCaseId12_2()+"', '"+dto.getSpecialCaseId12_3()+"', '"+dto.getSpecialCaseId12_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==13){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId13()+"'";
					}else if(dto.getSpecialCaseId()[i]==14){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId14();
					}else if(dto.getSpecialCaseId()[i]==15){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId15();
					}else if(dto.getSpecialCaseId()[i]==16){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId16();
					}else if(dto.getSpecialCaseId()[i]==17){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId17();
					}else if(dto.getSpecialCaseId()[i]==18){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId18();
					}else if(dto.getSpecialCaseId()[i]==18){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId19();
					}else if(dto.getSpecialCaseId()[i]==20){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId20();
					}else if(dto.getSpecialCaseId()[i]==21){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId21();
					}else if(dto.getSpecialCaseId()[i]==22){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId22()+"'";
					}else if(dto.getSpecialCaseId()[i]==23){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId23();
					}else if(dto.getSpecialCaseId()[i]==24){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId24();
					}else if(dto.getSpecialCaseId()[i]==25){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId25()+"'";
					}else if(dto.getSpecialCaseId()[i]==26){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId26()+"'";
					}else if(dto.getSpecialCaseId()[i]==27){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId27();
					}else if(dto.getSpecialCaseId()[i]==28){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId28();
					}else if(dto.getSpecialCaseId()[i]==30){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId30();
					}else if(dto.getSpecialCaseId()[i]==31){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId31();
					}else if(dto.getSpecialCaseId()[i]==32){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId32();
					}else if(dto.getSpecialCaseId()[i]==33){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId33();
					}else if(dto.getSpecialCaseId()[i]==34){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId34();
					}else if(dto.getSpecialCaseId()[i]==35){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId35();
					}else if(dto.getSpecialCaseId()[i]==36){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId36();
					}else if(dto.getSpecialCaseId()[i]==37){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId37();
					}else if(dto.getSpecialCaseId()[i]==39){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId39();
					}else if(dto.getSpecialCaseId()[i]==40){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId40();
					}else if(dto.getSpecialCaseId()[i]==42){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId42();
					}else if(dto.getSpecialCaseId()[i]==43){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId43();
					}else if(dto.getSpecialCaseId()[i]==47){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId47();
					}else if(dto.getSpecialCaseId()[i]==48){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId48()+"'";
					}else if(dto.getSpecialCaseId()[i]==49){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId49();
					}else if(dto.getSpecialCaseId()[i]==50){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId50();
					}else if(dto.getSpecialCaseId()[i]==52){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId52();
					}else if(dto.getSpecialCaseId()[i]==53){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId53()+"'";
					}else if(dto.getSpecialCaseId()[i]==54){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId54();
					}else if(dto.getSpecialCaseId()[i]==55){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId55();
					}else if(dto.getSpecialCaseId()[i]==56){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId56();
					}else if(dto.getSpecialCaseId()[i]==57){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId57();
					}else if(dto.getSpecialCaseId()[i]==58){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId58();
					}else if(dto.getSpecialCaseId()[i]==62){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId62()+"'";
					}else if(dto.getSpecialCaseId()[i]==63){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId63()+"'";
					}else if(dto.getSpecialCaseId()[i]==64){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId64();
					}else if(dto.getSpecialCaseId()[i]==71){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId71()+"'";
					}else if(dto.getSpecialCaseId()[i]==74){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId74()+"'";
					}else if(dto.getSpecialCaseId()[i]==75){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId75();
					}else if(dto.getSpecialCaseId()[i]==96){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId96()+"'";
					}else if(dto.getSpecialCaseId()[i]==97){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId97()+"'";
					}else if(dto.getSpecialCaseId()[i]==98){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId98()+"'";
					}else if(dto.getSpecialCaseId()[i]==99){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId99();
					}else if(dto.getSpecialCaseId()[i]==100){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId100();
					}else if(dto.getSpecialCaseId()[i]==101){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId101();
					}else if(dto.getSpecialCaseId()[i]==102){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId102();
					}else if(dto.getSpecialCaseId()[i]==103){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId103();
					}else if(dto.getSpecialCaseId()[i]==104){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId104();
					}else if(dto.getSpecialCaseId()[i]==105){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId105();
					}else if(dto.getSpecialCaseId()[i]==106){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId106();
					}else if(dto.getSpecialCaseId()[i]==107){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; 
						tail_2=", '"+dto.getSpecialCaseId107_1()+"', '"+dto.getSpecialCaseId107_2()+"', '"+dto.getSpecialCaseId107_3()+"', '"+dto.getSpecialCaseId107_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==108){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; 
						tail_2=", '"+dto.getSpecialCaseId108_1()+"', '"+dto.getSpecialCaseId108_2()+"', '"+dto.getSpecialCaseId108_3()+"', '"+dto.getSpecialCaseId108_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==109){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; 
						tail_2=", '"+dto.getSpecialCaseId109_1()+"', '"+dto.getSpecialCaseId109_2()+"', '"+dto.getSpecialCaseId109_3()+"', '"+dto.getSpecialCaseId109_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==110){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId110_1()+"', '"+dto.getSpecialCaseId110_2()+"', '"+dto.getSpecialCaseId110_3()+"', '"+dto.getSpecialCaseId110_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==111){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId111_1()+"', '"+dto.getSpecialCaseId111_2()+"', '"+dto.getSpecialCaseId111_3()+"', '"+dto.getSpecialCaseId111_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==112){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId112_1()+"', '"+dto.getSpecialCaseId112_2()+"', '"+dto.getSpecialCaseId112_3()+"', '"+dto.getSpecialCaseId112_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==113){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId113_1()+"', '"+dto.getSpecialCaseId113_2()+"', '"+dto.getSpecialCaseId113_3()+"', '"+dto.getSpecialCaseId113_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==114){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId114_1()+"', '"+dto.getSpecialCaseId114_2()+"', '"+dto.getSpecialCaseId114_3()+"', '"+dto.getSpecialCaseId114_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==115){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId115_1()+"', '"+dto.getSpecialCaseId115_2()+"', '"+dto.getSpecialCaseId115_3()+"', '"+dto.getSpecialCaseId115_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==116){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId116()+"'";
					}else if(dto.getSpecialCaseId()[i]==117){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId117()+"'";
					}else if(dto.getSpecialCaseId()[i]==118){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId118();
					}else if(dto.getSpecialCaseId()[i]==119){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId119()+"'";
					}else if(dto.getSpecialCaseId()[i]==123){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId123()+"'";
					}else if(dto.getSpecialCaseId()[i]==125){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId125();
					}else if(dto.getSpecialCaseId()[i]==126){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getSpecialCaseId126_1()+", '"+dto.getSpecialCaseId126_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==127){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getSpecialCaseId127_1()+", '"+dto.getSpecialCaseId127_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==128){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId128();
					}else if(dto.getSpecialCaseId()[i]==129){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId129();
					}else if(dto.getSpecialCaseId()[i]==130){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId130();
					}else if(dto.getSpecialCaseId()[i]==131){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId131();
					}else if(dto.getSpecialCaseId()[i]==132){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId132();
					}else if(dto.getSpecialCaseId()[i]==134){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId134();
					}else if(dto.getSpecialCaseId()[i]==135){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId135()+"'";
					}else if(dto.getSpecialCaseId()[i]==136){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId136();
					}else if(dto.getSpecialCaseId()[i]==137){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId137()+"'";
					}else if(dto.getSpecialCaseId()[i]==138){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getSpecialCaseId138_1()+", '"+dto.getSpecialCaseId138_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==139){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId139();
					}else if(dto.getSpecialCaseId()[i]==141){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", "+dto.getSpecialCaseId141_1()+", '"+dto.getSpecialCaseId141_2()+"', '"+dto.getSpecialCaseId141_3()+"', '"+dto.getSpecialCaseId141_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==121){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getSpecialCaseId121_1()+", '"+dto.getSpecialCaseId121_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==122){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId122();
					}else if(dto.getSpecialCaseId()[i]==152){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId152();
					}else if(dto.getSpecialCaseId()[i]==175){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId175();
					}else if(dto.getSpecialCaseId()[i]==176){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId176();
					}else if(dto.getSpecialCaseId()[i]==179){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId179();
					}else if(dto.getSpecialCaseId()[i]==180){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId180();
					}else if(dto.getSpecialCaseId()[i]==177){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId177()+"'";
					}else if(dto.getSpecialCaseId()[i]==178){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId178()+"'";
					}else if(dto.getSpecialCaseId()[i]==133){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId133();
					}else if(dto.getSpecialCaseId()[i]==188){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId188();
					}else if(dto.getSpecialCaseId()[i]==189){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId189();
					}else if(dto.getSpecialCaseId()[i]==190){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId190();
					}else if(dto.getSpecialCaseId()[i]==191){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId191();
					}else if(dto.getSpecialCaseId()[i]==192){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId192();
					}else if(dto.getSpecialCaseId()[i]==193){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId193()+"'";
					}else if(dto.getSpecialCaseId()[i]==194){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId194()+"'";
					}else if(dto.getSpecialCaseId()[i]==195){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId195();
					}else if(dto.getSpecialCaseId()[i]==196){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId196()+"'";
					}else if(dto.getSpecialCaseId()[i]==197){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId197()+"'";
					}else if(dto.getSpecialCaseId()[i]==198){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId198()+"'";
					}else if(dto.getSpecialCaseId()[i]==204){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId204();
					}else if(dto.getSpecialCaseId()[i]==205){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId205()+"'";
					}else if(dto.getSpecialCaseId()[i]==206){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId206();
					}else if(dto.getSpecialCaseId()[i]==207){
						tail_1=", input_value, input_value_2";tail_2=", "+dto.getSpecialCaseId207_1()+", '"+dto.getSpecialCaseId207_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==208){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId208();
					}else if(dto.getSpecialCaseId()[i]==209){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId209()+"'";
					}else if(dto.getSpecialCaseId()[i]==210){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId210()+"'";
					}else if(dto.getSpecialCaseId()[i]==216){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId216();
					}else if(dto.getSpecialCaseId()[i]==217){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId217();
					}else if(dto.getSpecialCaseId()[i]==218){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId218();
					}else if(dto.getSpecialCaseId()[i]==219){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId219();
					}else if(dto.getSpecialCaseId()[i]==220){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId220();
					}else if(dto.getSpecialCaseId()[i]==223){
						tail_1=", input_value, input_value_2";tail_2=", '"+dto.getSpecialCaseId223_1()+"', '"+dto.getSpecialCaseId223_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==225){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId225()+"'";
					}else if(dto.getSpecialCaseId()[i]==226){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId226();
					}else if(dto.getSpecialCaseId()[i]==227){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId227();
					}else if(dto.getSpecialCaseId()[i]==228){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId228();
					}else if(dto.getSpecialCaseId()[i]==229){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId229();
					}else if(dto.getSpecialCaseId()[i]==220){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId220();
					}else if(dto.getSpecialCaseId()[i]==232){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId232()+"'";
					}else if(dto.getSpecialCaseId()[i]==257){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId257()+"'";
					}else if(dto.getSpecialCaseId()[i]==258){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId258()+"'";
					}else if(dto.getSpecialCaseId()[i]==259){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId259()+"'";
					}else if(dto.getSpecialCaseId()[i]==263){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId263()+"'";
					}else if(dto.getSpecialCaseId()[i]==267){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId267();
					}else if(dto.getSpecialCaseId()[i]==270){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId270();
					}else if(dto.getSpecialCaseId()[i]==271){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId271();
					}else if(dto.getSpecialCaseId()[i]==266){
						tail_1=", input_value, input_value_2";tail_2=", '"+dto.getSpecialCaseId266_1()+"', '"+dto.getSpecialCaseId266_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==307){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId307()+"'";
					}else if(dto.getSpecialCaseId()[i]==308){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId308()+"'";
					}else if(dto.getSpecialCaseId()[i]==309){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId309()+"'";
					}else if(dto.getSpecialCaseId()[i]==311){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId311()+"'";
					}else if(dto.getSpecialCaseId()[i]==269){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId269()+"'";
					}else if(dto.getSpecialCaseId()[i]==282){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId282()+"'";
					}else if(dto.getSpecialCaseId()[i]==323){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId323()+"'";
					}else if(dto.getSpecialCaseId()[i]==335){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId335()+"'";
					}else if(dto.getSpecialCaseId()[i]==371){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId371()+"'";
					}else if(dto.getSpecialCaseId()[i]==338){
						tail_1=", input_value, input_value_2";tail_2=", '"+dto.getSpecialCaseId338_1()+"', '"+dto.getSpecialCaseId338_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==339){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId339()+"'";
					}else if(dto.getSpecialCaseId()[i]==376){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId376_1()+"', '"+dto.getSpecialCaseId376_2()+"', '"+dto.getSpecialCaseId376_3()+"', '"+dto.getSpecialCaseId376_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==377){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId377_1()+"', '"+dto.getSpecialCaseId377_2()+"', '"+dto.getSpecialCaseId377_3()+"', '"+dto.getSpecialCaseId377_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==378){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId378_1()+"', '"+dto.getSpecialCaseId378_2()+"', '"+dto.getSpecialCaseId378_3()+"', '"+dto.getSpecialCaseId378_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==379){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId379_1()+"', '"+dto.getSpecialCaseId379_2()+"', '"+dto.getSpecialCaseId379_3()+"', '"+dto.getSpecialCaseId379_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==380){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId380_1()+"', '"+dto.getSpecialCaseId380_2()+"', '"+dto.getSpecialCaseId380_3()+"', '"+dto.getSpecialCaseId380_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==381){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId381_1()+"', '"+dto.getSpecialCaseId381_2()+"', '"+dto.getSpecialCaseId381_3()+"', '"+dto.getSpecialCaseId381_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==382){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId382_1()+"', '"+dto.getSpecialCaseId382_2()+"', '"+dto.getSpecialCaseId382_3()+"', '"+dto.getSpecialCaseId382_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==383){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId383_1()+"', '"+dto.getSpecialCaseId383_2()+"', '"+dto.getSpecialCaseId383_3()+"', '"+dto.getSpecialCaseId383_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==340){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId340();
					}else if(dto.getSpecialCaseId()[i]==341){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId341();
					}else if(dto.getSpecialCaseId()[i]==384){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId384();
					}else if(dto.getSpecialCaseId()[i]==385){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId385();
					}else if(dto.getSpecialCaseId()[i]==447){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId447_1()+"', '"+dto.getSpecialCaseId447_2()+"', '"+dto.getSpecialCaseId447_3()+"', '"+dto.getSpecialCaseId447_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==438){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId438_1()+"', '"+dto.getSpecialCaseId438_2()+"', '"+dto.getSpecialCaseId438_3()+"', '"+dto.getSpecialCaseId438_4()+"'";
					}else if(dto.getSpecialCaseId()[i]==392){
						tail_1=", input_value, input_value_2";tail_2=", '"+dto.getSpecialCaseId392_1()+"', '"+dto.getSpecialCaseId392_2()+"'";
					}else if(dto.getSpecialCaseId()[i]==393){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId393()+"'";
					}else if(dto.getSpecialCaseId()[i]==406){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId406()+"'";
					}else if(dto.getSpecialCaseId()[i]==435){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId435()+"'";
					}else if(dto.getSpecialCaseId()[i]==436){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId436()+"'";
					}else if(dto.getSpecialCaseId()[i]==437){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId437()+"'";
					}else if(dto.getSpecialCaseId()[i]==446){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId446()+"'";
					}else if(dto.getSpecialCaseId()[i]==451){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId451()+"'";
					}else if(dto.getSpecialCaseId()[i]==452){
						tail_1=", input_value"; tail_2=", '"+dto.getSpecialCaseId452()+"'";
					}else if(dto.getSpecialCaseId()[i]==395){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId395();
					}else if(dto.getSpecialCaseId()[i]==396){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId396();
					}else if(dto.getSpecialCaseId()[i]==397){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId397();
					}else if(dto.getSpecialCaseId()[i]==399){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId399();
					}else if(dto.getSpecialCaseId()[i]==402){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId402();
					}else if(dto.getSpecialCaseId()[i]==404){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId404();
					}else if(dto.getSpecialCaseId()[i]==405){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId405();
					}else if(dto.getSpecialCaseId()[i]==449){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId449();
					}else if(dto.getSpecialCaseId()[i]==450){
						tail_1=", input_value"; tail_2=", "+dto.getSpecialCaseId450();
					}else if(dto.getSpecialCaseId()[i]==454){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4";
						tail_2=", '"+dto.getSpecialCaseId454_1()+"', '"+dto.getSpecialCaseId454_2()+"', '"+dto.getSpecialCaseId454_3()+"', '"+dto.getSpecialCaseId454_4()+"'";
					}
					stmt.execute("insert into tbl_patient_disease_special_case_details(patient_id, visit_id, sp_case_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getSpecialCaseId()[i]+tail_2+")");
					tail_1="";
					tail_2="";
				}
			}
			
			
			{
				try{
					sizeOfArray=dto.getHistoryId().length;
				}catch(Exception e){sizeOfArray=0;};
				String tail_1="", tail_2="";
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getHistoryId()[i]==41){
						tail_1=", input_value"; tail_2=", '"+dto.getOtherHistoryRes()+"'";
					}else if(dto.getHistoryId()[i]==54){
						tail_1=", input_value, input_value_2"; tail_2=", '"+dto.getLastDateOfDialysis()+"', '"+dto.getFrequencyOfDialysis()+"'";
					}else if(dto.getHistoryId()[i]==79){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId79();
					}else if(dto.getHistoryId()[i]==80){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId80();
					}else if(dto.getHistoryId()[i]==81){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId81();
					}else if(dto.getHistoryId()[i]==82){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId82();
					}else if(dto.getHistoryId()[i]==83){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId83();
					}else if(dto.getHistoryId()[i]==84){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId84();
					}else if(dto.getHistoryId()[i]==85){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId85();
					}else if(dto.getHistoryId()[i]==86){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId86()+"'";
					}else if(dto.getHistoryId()[i]==87){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId87();
					}else if(dto.getHistoryId()[i]==88){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId88();
					}else if(dto.getHistoryId()[i]==89){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId89();
					}else if(dto.getHistoryId()[i]==90){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId90();
					}else if(dto.getHistoryId()[i]==91){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId91();
					}else if(dto.getHistoryId()[i]==92){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId92();
					}else if(dto.getHistoryId()[i]==93){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId93()+"'";
					}else if(dto.getHistoryId()[i]==106){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId106()+"'";
					}else if(dto.getHistoryId()[i]==131){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId131()+"'";
					}else if(dto.getHistoryId()[i]==136){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId136()+"'";
					}else if(dto.getHistoryId()[i]==139){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId139()+"'";
					}/*else if(dto.getHistoryId()[i]==140){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getHisDescripId140_1()+", '"+dto.getHisDescripId140_2()+"'";
					}*/else if(dto.getHistoryId()[i]==141){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getHisDescripId141_1()+", '"+dto.getHisDescripId141_2()+"'";
					}else if(dto.getHistoryId()[i]==296){
						tail_1=", input_value"; tail_2=", '"+dto.getEyeOpening()+"'";
					}else if(dto.getHistoryId()[i]==297){
						tail_1=", input_value"; tail_2=", '"+dto.getVerbalResponse()+"'";
					}else if(dto.getHistoryId()[i]==298){
						tail_1=", input_value"; tail_2=", '"+dto.getMotorResponse()+"'";
					}else if(dto.getHistoryId()[i]==299){
						tail_1=", input_value"; tail_2=", '"+dto.getTotalScore()+"'";
					}else if(dto.getHistoryId()[i]==148){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId148()+"'";
					}else if(dto.getHistoryId()[i]==162){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId162();
					}else if(dto.getHistoryId()[i]==165){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId165()+"'";
					}else if(dto.getHistoryId()[i]==167){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId167()+"'";
					}else if(dto.getHistoryId()[i]==168){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId168()+"'";
					}else if(dto.getHistoryId()[i]==169){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId169()+"'";
					}else if(dto.getHistoryId()[i]==170){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId170();
					}else if(dto.getHistoryId()[i]==171){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId171();
					}else if(dto.getHistoryId()[i]==173){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId173()+"'";
					}else if(dto.getHistoryId()[i]==174){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId174();
					}else if(dto.getHistoryId()[i]==175){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId175();
					}else if(dto.getHistoryId()[i]==176){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId176();
					}else if(dto.getHistoryId()[i]==177){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId177();
					}else if(dto.getHistoryId()[i]==178){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; tail_2=", '"+dto.getHisDescripId178_1()+"', '"+dto.getHisDescripId178_2()+"', '"+dto.getHisDescripId178_3()+"', '"+dto.getHisDescripId178_4()+"'";
					}else if(dto.getHistoryId()[i]==181){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId181()+"'";
					}else if(dto.getHistoryId()[i]==204){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId204();
					}else if(dto.getHistoryId()[i]==211){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId211();
					}else if(dto.getHistoryId()[i]==219){
						tail_1=", input_value"; tail_2=", "+dto.
								getHisDescripId219();
					}else if(dto.getHistoryId()[i]==222){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId222();
					}else if(dto.getHistoryId()[i]==223){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId223()+"'";
					}else if(dto.getHistoryId()[i]==224){
						tail_1=", input_value"; tail_2=", '"+dto.getFrequency()+"'";
					}else if(dto.getHistoryId()[i]==225){
						tail_1=", input_value"; tail_2=", '"+dto.getSeverity()+"'";
					}else if(dto.getHistoryId()[i]==226){
						tail_1=", input_value"; tail_2=", '"+dto.getDuration()+"'";
					}else if(dto.getHistoryId()[i]==227){
						tail_1=", input_value"; tail_2=", '"+dto.getPeriodOfRemission()+"'";
					}
					/** Benign cyst /tumor/ swelling **/
					else if(dto.getHistoryId()[i]==232){
						tail_1=", input_value"; tail_2=", '"+dto.getHisDescripId232()+"'";
					}else if(dto.getHistoryId()[i]==229){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId229();
					}else if(dto.getHistoryId()[i]==230){
						tail_1=", input_value"; tail_2=", "+dto.getHisDescripId230();
					}

					stmt.execute("insert into tbl_patient_disease_history(patient_id, visit_id, history_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getHistoryId()[i]+tail_2+")");

					tail_1="";
					tail_2="";
				}
			}
			
			
			{
				try{
					sizeOfArray=dto.getSymptomId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				for(int i=0;i<sizeOfArray;i++){
					stmt.execute("insert into tbl_patient_disease_symptom(patient_id,visit_id, symptom_id) values("+dto.getUserId()+","+currentVisitId+", "+dto.getSymptomId()[i]+")");
				}
			}
			
			{
				try{
					sizeOfArray=dto.getOthersId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getOthersId()[i]==1){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", "+dto.getOthsDescripId1()+")");
					}else if(dto.getOthersId()[i]==2){
						stmt.execute("insert into tbl_patient_disease_others(patient_id,visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", "+dto.getOthsDescripId2()+")");
					}else if(dto.getOthersId()[i]==3){
						stmt.execute("insert into tbl_patient_disease_others(patient_id,visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", "+dto.getOthsDescripId3()+")");
					}else if(dto.getOthersId()[i]==4){
						stmt.execute("insert into tbl_patient_disease_others(patient_id,visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", "+dto.getOthsDescripId4()+")");
					}else if(dto.getOthersId()[i]==8){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId8()+"')");
					}else if(dto.getOthersId()[i]==12){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId12()+"')");
					}else if(dto.getOthersId()[i]==13){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId13()+"')");
					}else if(dto.getOthersId()[i]==14){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId14()+"')");
					}else if(dto.getOthersId()[i]==15){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId15()+"')");
					}else if(dto.getOthersId()[i]==16){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId16()+"')");
					}else if(dto.getOthersId()[i]==17){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId17()+"')");
					}else if(dto.getOthersId()[i]==20){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId20()+"')");
					}else if(dto.getOthersId()[i]==21){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId21()+"')");
					}else if(dto.getOthersId()[i]==22){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", '"+dto.getOthsDescripId22()+"')");
					}else if(dto.getOthersId()[i]==25){
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+", "+dto.getOthsDescripId25()+")");
					}else{
						stmt.execute("insert into tbl_patient_disease_others(patient_id, visit_id, others_id) values("+dto.getUserId()+","+currentVisitId+", "+dto.getOthersId()[i]+")");
					}
				}
			}			
			
			{
				try{
					sizeOfArray=dto.getInspectionId().length;
				}catch(Exception e){sizeOfArray=0;};
				String tail_1="", tail_2="";
				for(int i=0;i<sizeOfArray;i++){
					
					if(dto.getInspectionId()[i]==4){
						tail_1=", input_value"; tail_2=", '"+dto.getHeightVsPressure()+"'";
					}else if(dto.getInspectionId()[i]==6){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId6();
					}else if(dto.getInspectionId()[i]==7){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId7();
					}else if(dto.getInspectionId()[i]==8){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId8();
					}else if(dto.getInspectionId()[i]==13){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId13();
					}else if(dto.getInspectionId()[i]==49){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId49();
					}else if(dto.getInspectionId()[i]==50){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId50();
					}else if(dto.getInspectionId()[i]==60){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId60();
					}else if(dto.getInspectionId()[i]==61){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId61();
					}else if(dto.getInspectionId()[i]==69){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId69();
					}else if(dto.getInspectionId()[i]==72){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId72();
					}else if(dto.getInspectionId()[i]==74){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId74();
					}else if(dto.getInspectionId()[i]==75){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId75();
					}else if(dto.getInspectionId()[i]==77){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId77();
					}else if(dto.getInspectionId()[i]==78){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId78();
					}else if(dto.getInspectionId()[i]==80){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId80();
					}else if(dto.getInspectionId()[i]==81){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId81();
					}else if(dto.getInspectionId()[i]==84){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId84();
					}else if(dto.getInspectionId()[i]==87){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId87();
					}else if(dto.getInspectionId()[i]==92){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId92();
					}else if(dto.getInspectionId()[i]==93){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId93();
					}else if(dto.getInspectionId()[i]==96){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId96();
					}else if(dto.getInspectionId()[i]==99){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId99();
					}else if(dto.getInspectionId()[i]==103){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId103();
					}else if(dto.getInspectionId()[i]==111){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId111();
					}else if(dto.getInspectionId()[i]==112){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId112();
					}else if(dto.getInspectionId()[i]==116){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId116();
					}else if(dto.getInspectionId()[i]==117){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId117();
					}else if(dto.getInspectionId()[i]==118){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId118();
					}else if(dto.getInspectionId()[i]==119){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId119();
					}else if(dto.getInspectionId()[i]==125){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId125();
					}else if(dto.getInspectionId()[i]==134){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId134()+"'";
					}else if(dto.getInspectionId()[i]==135){
						tail_1=", input_value, input_value_2"; tail_2=", '"+dto.getInspecDescripId135_1()+"', '"+dto.getInspecDescripId135_2()+"'";
					}else if(dto.getInspectionId()[i]==141){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId141()+"'";
					}else if(dto.getInspectionId()[i]==142){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId142()+"'";
					}else if(dto.getInspectionId()[i]==137){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId137();
					}else if(dto.getInspectionId()[i]==138){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId138();
					}else if(dto.getInspectionId()[i]==139){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId139();
					}else if(dto.getInspectionId()[i]==140){
						tail_1=", input_value";tail_2=", "+dto.getInspecDescripId140();
					}else if(dto.getInspectionId()[i]==148){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId148();
					}else if(dto.getInspectionId()[i]==151){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId151()+"'";
					}else if(dto.getInspectionId()[i]==155){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId155()+"'";
					}else if(dto.getInspectionId()[i]==156){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId156();
					}else if(dto.getInspectionId()[i]==157){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId157();
					}else if(dto.getInspectionId()[i]==158){
						tail_1=", input_value"; tail_2=", "+dto.getInspecDescripId158();
					}else if(dto.getInspectionId()[i]==162){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId162()+"'";
					}else if(dto.getInspectionId()[i]==163){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId163()+"'";
					}else if(dto.getInspectionId()[i]==163){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId163()+"'";
					}else if(dto.getInspectionId()[i]==165){
						tail_1=", input_value, input_value_2"; tail_2=", '"+dto.getInspecDescripId165()+"', '"+dto.getInspecDescripId165_2()+"'";
					}else if(dto.getInspectionId()[i]==166){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId166()+"'";
					}else if(dto.getInspectionId()[i]==290){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId290()+"'";
					}else if(dto.getInspectionId()[i]==332){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId332()+"'";
					}else if(dto.getInspectionId()[i]==351){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId351()+"'";
					}else if(dto.getInspectionId()[i]==355){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId355()+"'";
					}else if(dto.getInspectionId()[i]==356){
						tail_1=", input_value";tail_2=", '"+dto.getInspecDescripId356()+"'";
					}else if(dto.getInspectionId()[i]==532){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId532()+"'";
					}else if(dto.getInspectionId()[i]==533){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId533()+"'";
					}else if(dto.getInspectionId()[i]==534){
						tail_1=", input_value"; tail_2=", '"+dto.getInspecDescripId534()+"'";
					}else if(dto.getInspectionId()[i]==168){
						tail_1=", input_value, input_value_2";
						tail_2=", '"+dto.getInspecDescripId168()+"', '"+dto.getInspecDescripId168_2()+"'";
					}else if(dto.getInspectionId()[i]==178){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4, input_value_5, input_value_6";
						tail_2=", '"+dto.getInspecDescripId178()+"', '"+dto.getInspecDescripId178_2()+"', '"+dto.getInspecDescripId178_3()+"', '"+dto.getInspecDescripId178_4()+"', '"+dto.getInspecDescripId178_5()+"', '"+dto.getInspecDescripId178_6()+"'";
					}else if(dto.getInspectionId()[i]==487){
						System.out.println("***********************"+dto.getInspecDescripId487_1()+"*********************");
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; tail_2=", '"+dto.getInspecDescripId487_1()+"', '"+dto.getInspecDescripId487_2()+"', '"+dto.getInspecDescripId487_3()+"', '"+dto.getInspecDescripId487_4()+"'";
					}else if(dto.getInspectionId()[i]==488){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; tail_2=", '"+dto.getInspecDescripId488_1()+"', '"+dto.getInspecDescripId488_2()+"', '"+dto.getInspecDescripId488_3()+"', '"+dto.getInspecDescripId488_4()+"'";
					}else if(dto.getInspectionId()[i]==489){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; tail_2=", '"+dto.getInspecDescripId489_1()+"', '"+dto.getInspecDescripId489_2()+"', '"+dto.getInspecDescripId489_3()+"', '"+dto.getInspecDescripId489_4()+"'";
					}else if(dto.getInspectionId()[i]==490){
						tail_1=", input_value, input_value_2, input_value_3, input_value_4"; tail_2=", '"+dto.getInspecDescripId490_1()+"', '"+dto.getInspecDescripId490_2()+"', '"+dto.getInspecDescripId490_3()+"', '"+dto.getInspecDescripId490_4()+"'";
					}else if(dto.getInspectionId()[i]==536){
						tail_1=", input_value, input_value_2";tail_2=", '"+dto.getInspecDescripId536_1()+"', '"+dto.getInspecDescripId536_2()+"'";
					}else if(dto.getInspectionId()[i]==537){
						tail_1=", input_value, input_value_2";tail_2=", '"+dto.getInspecDescripId537_1()+"', '"+dto.getInspecDescripId537_2()+"'";
					}
					stmt.execute("insert into tbl_patient_disease_inspection(patient_id, visit_id, inspection_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getInspectionId()[i]+tail_2+")");
					tail_1="";
					tail_2="";
				}
			}
			
			{
				try{
					sizeOfArray=dto.getPalpationId().length;
				}catch(Exception e){sizeOfArray=0;};
				String tail_1="", tail_2="";
				try{	
					for(int i=0;i<sizeOfArray;i++){
						if(dto.getPalpationId()[i]==1){
							tail_1=", input_value"; tail_2=", '"+dto.getHeartRatePerMin()+"'";
						}else if(dto.getPalpationId()[i]==4){
							tail_1=", input_value"; tail_2=", '"+dto.getBloodPressure()+"'";
						}else if(dto.getPalpationId()[i]==7){
							tail_1=", input_value"; tail_2=", '"+dto.getApexBeat()+"'";
						}else if(dto.getPalpationId()[i]==22){
							tail_1=", input_value, input_value_2"; tail_2=",  '"+dto.getPalpaDescripId22_1()+"', '"+dto.getPalpaDescripId22_2()+"'";
						}else if(dto.getPalpationId()[i]==27){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId27()+"'";
						}else if(dto.getPalpationId()[i]==28){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId28()+"'";
						}else if(dto.getPalpationId()[i]==53){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId53();
						}else if(dto.getPalpationId()[i]==56){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId56();
						}else if(dto.getPalpationId()[i]==57){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId57();
						}else if(dto.getPalpationId()[i]==58){
							tail_1=", input_value, input_value_2"; tail_2=",  '"+dto.getPalpaDescripId58_1()+"', '"+dto.getPalpaDescripId58_2()+"'";
						}else if(dto.getPalpationId()[i]==65){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId65();
						}else if(dto.getPalpationId()[i]==66){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId66();
						}else if(dto.getPalpationId()[i]==67){
							tail_1=", input_value, input_value_2"; tail_2=",  '"+dto.getPalpaDescripId67_1()+"', '"+dto.getPalpaDescripId67_2()+"'";
						}else if(dto.getPalpationId()[i]==71){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId71()+"'";
						}else if(dto.getPalpationId()[i]==72){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId72()+"'";
						}else if(dto.getPalpationId()[i]==73){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId73()+"'";
						}else if(dto.getPalpationId()[i]==74){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId74()+"'";
						}else if(dto.getPalpationId()[i]==75){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId75()+"'";
						}else if(dto.getPalpationId()[i]==88){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId88()+"'";
						}else if(dto.getPalpationId()[i]==93){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId93();
						}else if(dto.getPalpationId()[i]==100){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId100()+"'";
						}else if(dto.getPalpationId()[i]==108){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId108()+"'";
						}else if(dto.getPalpationId()[i]==107){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId107();
						}else if(dto.getPalpationId()[i]==112){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId112();
						}else if(dto.getPalpationId()[i]==113){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId113();
						}else if(dto.getPalpationId()[i]==114){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId114();
						}else if(dto.getPalpationId()[i]==90){
							tail_1=", input_value"; tail_2=", "+dto.getPalpaDescripId90();
						}else if(dto.getPalpationId()[i]==115){
							tail_1=", input_value"; tail_2=", '"+dto.getPalpaDescripId115()+"'";
						}
						stmt.execute("insert into tbl_patient_disease_palpation(patient_id, visit_id, palpation_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getPalpationId()[i]+tail_2+")");
						tail_1="";
						tail_2="";
					}
				}catch(Exception e){}
			}
			
			
			{
				try{
					sizeOfArray=dto.getAuscultationId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				try{
					for(int i=0;i<sizeOfArray;i++){
						if(dto.getAuscultationId()[i]==7){
							stmt.execute("insert into tbl_patient_disease_auscultation(patient_id, visit_id, auscultation_id, input_value) values("+dto.getUserId()+","+currentVisitId+", "+dto.getAuscultationId()[i]+", '"+dto.getAuscuDescripId7()+"')");
						}else{
							stmt.execute("insert into tbl_patient_disease_auscultation(patient_id, visit_id, auscultation_id) values("+dto.getUserId()+","+currentVisitId+", "+dto.getAuscultationId()[i]+")");
						}
					}
				}catch(Exception e){}
			}
			
			{
				try{
					sizeOfArray=dto.getTreatmentId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				try{
					String tail_1="", tail_2="";
					for(int i=0;i<sizeOfArray;i++){
						if(dto.getTreatmentId()[i]==5){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId5()+"'";
						}else if(dto.getTreatmentId()[i]==9){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId9()+"'";
						}else if(dto.getTreatmentId()[i]==11){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId11();
						}else if(dto.getTreatmentId()[i]==13){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId13();
						}else if(dto.getTreatmentId()[i]==14){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId14();
						}else if(dto.getTreatmentId()[i]==15){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId15()+"'";
						}else if(dto.getTreatmentId()[i]==18){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId18()+"'";
						}else if(dto.getTreatmentId()[i]==34){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId34();
						}else if(dto.getTreatmentId()[i]==35){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId35()+"'";
						}else if(dto.getTreatmentId()[i]==36){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId36();
						}else if(dto.getTreatmentId()[i]==37){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId37()+"'";
						}else if(dto.getTreatmentId()[i]==38){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId38();
						}else if(dto.getTreatmentId()[i]==39){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId39()+"'";
						}else if(dto.getTreatmentId()[i]==40){
							tail_1=", input_value"; tail_2=", "+dto.getTreatmentId40();
						}else if(dto.getTreatmentId()[i]==41){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId41()+"'";
						}else if(dto.getTreatmentId()[i]==48){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId48()+"'";
						}else if(dto.getTreatmentId()[i]==61){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId61()+"'";
						}else if(dto.getTreatmentId()[i]==77){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId77()+"'";
						}else if(dto.getTreatmentId()[i]==68){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId68()+"'";
						}else if(dto.getTreatmentId()[i]==82){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId82()+"'";
						}else if(dto.getTreatmentId()[i]==83){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId83()+"'";
						}else if(dto.getTreatmentId()[i]==84){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId84()+"'";
						}else if(dto.getTreatmentId()[i]==118){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId118()+"'";
						}else if(dto.getTreatmentId()[i]==157){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId157()+"'";
						}else if(dto.getTreatmentId()[i]==167){
							tail_1=", input_value"; tail_2=", '"+dto.getTreatmentId167()+"'";
						}
						
						stmt.execute("insert into tbl_patient_disease_treatment(patient_id, visit_id, treatment_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getTreatmentId()[i]+tail_2+")");
						tail_1="";
						tail_2="";
					}
				}catch(Exception e){}
			}
			
			{
				try{
					sizeOfArray=dto.getDiagnosisId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				try{
					String tail_1="", tail_2="";
					for(int i=0;i<sizeOfArray;i++){
						if(dto.getDiagnosisId()[i]==18){
							tail_1=", input_value"; tail_2=", "+dto.getDiagnosisId18();
						}else if(dto.getDiagnosisId()[i]==17){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId17()+"'";
						}else if(dto.getDiagnosisId()[i]==19){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId19()+"'";
						}else if(dto.getDiagnosisId()[i]==20){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId20()+"'";
						}else if(dto.getDiagnosisId()[i]==23){
							tail_1=", input_value"; tail_2=", "+dto.getDiagnosisId23();
						}else if(dto.getDiagnosisId()[i]==24){
							tail_1=", input_value"; tail_2=", "+dto.getDiagnosisId24();
						}else if(dto.getDiagnosisId()[i]==47){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId47()+"'";
						}else if(dto.getDiagnosisId()[i]==62){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId62()+"'";
						}else if(dto.getDiagnosisId()[i]==63){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId63()+"'";
						}else if(dto.getDiagnosisId()[i]==75){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId75()+"'";
						}else if(dto.getDiagnosisId()[i]==87){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId87()+"'";
						}else if(dto.getDiagnosisId()[i]==90){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId90()+"'";
						}else if(dto.getDiagnosisId()[i]==100){
							tail_1=", input_value"; tail_2=", "+dto.getDiagnosisId100();
						}else if(dto.getDiagnosisId()[i]==101){
							tail_1=", input_value"; tail_2=", "+dto.getDiagnosisId101();
						}else if(dto.getDiagnosisId()[i]==102){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId102()+"'";
						}else if(dto.getDiagnosisId()[i]==103){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId103()+"'";
						}else if(dto.getDiagnosisId()[i]==104){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId104()+"'";
						}else if(dto.getDiagnosisId()[i]==105){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId105()+"'";
						}else if(dto.getDiagnosisId()[i]==106){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId106()+"'";
						}else if(dto.getDiagnosisId()[i]==107){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId107()+"'";
						}else if(dto.getDiagnosisId()[i]==108){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId108()+"'";
						}else if(dto.getDiagnosisId()[i]==109){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId109()+"'";
						}else if(dto.getDiagnosisId()[i]==110){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId110()+"'";
						}else if(dto.getDiagnosisId()[i]==111){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId111()+"'";
						}else if(dto.getDiagnosisId()[i]==112){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId112()+"'";
						}else if(dto.getDiagnosisId()[i]==129){
							tail_1=", input_value"; tail_2=", '"+dto.getDiagnosisId129()+"'";
						}
						stmt.execute("insert into tbl_patient_disease_diagnosis(patient_id, visit_id, diagnosis_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getDiagnosisId()[i]+tail_2+")");
						tail_1="";
						tail_2="";
					}
				}catch(Exception e){}
			}
			
			{
				try{
					sizeOfArray=dto.getPrticpFactId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				try{
					String tail_1="", tail_2="";
					for(int i=0;i<sizeOfArray;i++){
						if(dto.getPrticpFactId()[i]==9){
							tail_1=", input_value"; tail_2=", "+dto.getOtherPertFact();
						}
						stmt.execute("insert into tbl_patient_disease_perticipating_factor(patient_id, visit_id, p_factor_id"+tail_1+") values("+dto.getUserId()+","+currentVisitId+", "+dto.getPrticpFactId()[i]+tail_2+")");
						tail_1="";
						tail_2="";
					}
				}catch(Exception e){}
			}
						
			{
				stmt.executeUpdate("delete from tbl_patient_special_notes where patient_id="+dto.getUserId()+" and disease_id="+dto.getDiseaseId() +" and visit_id="+ currentVisitId);
				if( (dto.getSpecialNotes()!=null && dto.getSpecialNotes().length()>0) || (dto.getComplications()!=null && dto.getComplications().length()>0)){
					stmt.execute("insert into tbl_patient_special_notes(patient_id, visit_id, disease_id, special_notes, complications) values("+dto.getUserId()+","+currentVisitId+","+dto.getDiseaseId()+",'"+dto.getSpecialNotes()+"','"+dto.getComplications()+"')");
				}
			}
			
			stmt.executeUpdate("update tbl_patient_disease set nad="+dto.getNad()+" where patient_id="+dto.getUserId()+" and disease_id="+dto.getDiseaseId() +" and visit_id="+currentVisitId);

            for(Object key: dto.getRequestParameters().keySet()){
                if(key.toString().split("-").length>1 && key.toString().split("-").length<4 && key.toString().endsWith("input_value")){
                    String table =  key.toString().split("-")[0];
                    String id =  key.toString().split("-")[1];
                    String columns = "input_value";
                    String[] strArr=(String[])dto.getRequestParameters().get(key);
                    String values = "'"+strArr[0]+"'";
                    if(dto.getRequestParameters().get(table+"-"+id+"-input_value_2")!=null) {
                        columns+=", input_value_2";
                        strArr=(String[])dto.getRequestParameters().get(table+"-"+id+"-input_value_2");
                        values+=", '"+strArr[0]+"'";
						if(dto.getRequestParameters().get(table+"-"+id+"-input_value_3")!=null) {
							columns+=", input_value_3";
							strArr=(String[])dto.getRequestParameters().get(table+"-"+id+"-input_value_3");
							values+=", '"+strArr[0]+"'";
							if(dto.getRequestParameters().get(table+"-"+id+"-input_value_4")!=null) {
								columns+=", input_value_4";
								strArr=(String[])dto.getRequestParameters().get(table+"-"+id+"-input_value_4");
								values+=", '"+strArr[0]+"'";
							}
						}
                    }
					String firstField="";
					if(table.equals("tbl_patient_disease_history")){
					   firstField = "history_id";
					}else if(table.equals("tbl_patient_disease_inspection")){
					   firstField = "inspection_id";
					}else if(table.equals("tbl_patient_disease_special_case_details")){
						firstField = "sp_case_id";
					}else if(table.equals("tbl_patient_disease_diagnosis")){
						firstField = "diagnosis_id";
					}else if(table.equals("tbl_patient_disease_others")){
						firstField = "others_id";
					}else if(table.equals("tbl_patient_disease_palpation")){
						firstField = "palpation_id";
					}else if(table.equals("tbl_patient_disease_symptom")){
						firstField = "symptom_id";
				    }else if(table.equals("tbl_patient_disease_auscultation")){
						firstField = "auscultation_id";
					}

                    String query = "insert into "+ table+"(patient_id, visit_id, "+firstField+", "+columns+") values("+dto.getUserId()+","+currentVisitId+", "+id+", "+ values+")";
                    System.out.println(query);
                    stmt.execute(query);
                }
            }
		}catch(Exception e){
			daoRes.setMessage("Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}	
		return daoRes;
	}


	public String addNewFollowUp(int userID, int diseaseID, String newFindings) {
		Connection conn = null;
		Statement stmt = null; 
		String message="Added Successfully";
        int currentVisitId = new VisitDAO().getCurrentVisitId(userID);
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into tbl_patient_disease_follow_up(patient_id, visit_id, disease_id, findings) values("+userID+","+currentVisitId+","+diseaseID+",'"+newFindings+"')");
			
		}catch(Exception e){
			message="Error: "+e.toString();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}		
		return message;
	}
}
