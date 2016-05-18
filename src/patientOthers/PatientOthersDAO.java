package patientOthers;

import disease.form.DiseaseMetaData;
import prescription.VisitDAO;
import utility.DAOResult;
import utility.MyConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PatientOthersDAO
{
	public HashMap<Integer, String> getParentByChildWithInfoId(int infoId){
		String sql=null;
		Connection conn = null;
		Statement stmt = null; 
		
		HashMap<Integer, String> parentByChild = new HashMap<Integer, String>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			sql="select * from tbl_personal_info_history where info_id="+infoId+" order by id asc";
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	if(rs.getInt(4)>0){
	        		String str=parentByChild.get(rs.getInt(4));
	        		if(str==null){
	        			parentByChild.put(rs.getInt(4), ""+rs.getInt(1));	
	        		}else{
	        			parentByChild.put(rs.getInt(4), str+","+rs.getInt(1));
	        		}
	        	}else{
	        		parentByChild.put(rs.getInt(1), null);
	        	}
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return parentByChild;
	}	
	
	public HashMap<Integer, DiseaseMetaData> getSocialAndPersonalHistoryDetailsByID(int infoID) {
		HashMap<Integer, DiseaseMetaData> diseaseDetl = new HashMap<Integer, DiseaseMetaData>();
		String sql="select * from tbl_personal_info_history where info_id="+infoID;
		
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        /*ResultSet rs=stmt.executeQuery(sql+" order by id asc");*/
			ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	//diseaseDetl.put(rs.getInt("id"), rs.getString("name_of_perticular"));
				DiseaseMetaData diseaseMetaData = new DiseaseMetaData();
				diseaseMetaData.setName(rs.getString("name_of_perticular"));
				diseaseMetaData.setInputType(DAOResult.hasColumn(rs, "input_type")? rs.getString("input_type"): null);
				diseaseDetl.put(rs.getInt("id"), diseaseMetaData);
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return diseaseDetl;
	}

	public HashMap<Integer, String> getDrugGroup() {
		HashMap<Integer, String> drugGroup = new HashMap<Integer, String>();
		String sql="select * from tbl_drug_group";
		
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	drugGroup.put(rs.getInt("id"), rs.getString("drug_group_name"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return drugGroup;
	}
	public HashSet<Integer> getPatientDrugID(int userId) {
		HashSet<Integer> patDrugGroup = new HashSet<Integer>();
		String sql="select * from tbl_patient_drug_group where patient_id="+userId;
		
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	patDrugGroup.add(rs.getInt("drug_group_id"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return patDrugGroup;
	}
	
	public PatientOthersDTO getPatientOthersDTOByID(int accId) {

		int currentVisitId = new VisitDAO().getCurrentVisitId(accId);

		Connection conn=null;
		Statement stmt=null;
		
		PatientOthersDTO patOthDTO = new PatientOthersDTO();
		
		patOthDTO.patExposureToId = new HashSet<Integer>();
		patOthDTO.patHabitsId = new HashSet<Integer>();
		patOthDTO.patImmunizationHistoryId = new HashSet<Integer>();
		patOthDTO.patMenstrualHistoryId = new HashSet<Integer>();
		patOthDTO.patObstetricalHistoryId = new HashSet<Integer>();
		patOthDTO.patRelevantDiseaseHistoryId = new HashSet<Integer>();
		patOthDTO.patInfoId= new HashSet<Integer>();
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoExposureTo+")");
			while(rs.next()){
				patOthDTO.patExposureToId.add(rs.getInt("info_id"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHabits+")");
			while(rs.next()){
				patOthDTO.patHabitsId.add(rs.getInt("info_id"));
				if(rs.getInt("info_id")==4){
					patOthDTO.setCigarettesStick(rs.getString("input_value"));
					patOthDTO.setCigarettesYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==5){
					patOthDTO.setSmokelessTobaccoTime(rs.getString("input_value"));
					patOthDTO.setSmokelessTobaccoYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==6){
					patOthDTO.setAlcoholPack(rs.getString("input_value"));
					patOthDTO.setAlcoholYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==7){
					patOthDTO.setOtherSubsAbuse(rs.getString("input_value"));
					patOthDTO.setOtherSubsAbuseYear(rs.getString("input_value_2"));
				}
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoMenstrualHistory+")");
			while(rs.next()){
				patOthDTO.patMenstrualHistoryId.add(rs.getInt("info_id"));
			}
			rs.close();
			
			
			rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoObstetricalHistory+")");
			while(rs.next()){
				patOthDTO.patObstetricalHistoryId.add(rs.getInt("info_id"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoFamilyRelevantDiseaseHistory+")");
			while(rs.next()){
				patOthDTO.patRelevantDiseaseHistoryId.add(rs.getInt("info_id"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id in ("+MyConfig.infoHistoryOfBlood+","+MyConfig.infoHistoryOfOthers+","+MyConfig.infoHistoryOfPreAnestheticEvaluation+","+MyConfig.infoHistoryOfPreAnEvIntraOralExamination+","+MyConfig.infoHistoryOfPreAnEvExtraOralExamination+","+MyConfig.infoMentalStateAndIntelligence+","+MyConfig.infoMentalState+","+MyConfig.infoFacies+","+MyConfig.infoGeneralAssessmentOfIllness+"))");
			while(rs.next()){
				patOthDTO.patInfoId.add(rs.getInt("info_id"));
				if(rs.getInt("info_id")==4){
					patOthDTO.setCigarettesStick(rs.getString("input_value"));
					patOthDTO.setCigarettesYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==5){
					patOthDTO.setSmokelessTobaccoTime(rs.getString("input_value"));
					patOthDTO.setSmokelessTobaccoYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==6){
					patOthDTO.setAlcoholPack(rs.getString("input_value"));
					patOthDTO.setAlcoholYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==7){
					patOthDTO.setOtherSubsAbuse(rs.getString("input_value"));
					patOthDTO.setOtherSubsAbuseYear(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==75){
					patOthDTO.setInfoId75(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==76){
					patOthDTO.setInfoId76(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==86){
					patOthDTO.setInfoId86(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==87){
					patOthDTO.setInfoId87(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==92){
					patOthDTO.setInfoId92(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==93){
					patOthDTO.setInfoId93(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==94){
					patOthDTO.setInfoId94(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==95){
					patOthDTO.setInfoId95(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==96){
					patOthDTO.setInfoId96(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==97){
					patOthDTO.setInfoId97(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==98){
					patOthDTO.setInfoId98_1(rs.getString("input_value"));
					patOthDTO.setInfoId98_2(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==99){
					patOthDTO.setInfoId99(rs.getString("input_value"));
				}else if(rs.getInt("info_id")==100){
					patOthDTO.setInfoId100_1(rs.getString("input_value"));
					patOthDTO.setInfoId100_1(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==101){
					patOthDTO.setInfoId101(rs.getString("input_value"));
				}else if(rs.getInt("info_id")==121){
					patOthDTO.setInfoId121(rs.getString("input_value"));
				}else if(rs.getInt("info_id")==125){
					patOthDTO.setInfoId125(rs.getString("input_value"));
				}else if(rs.getInt("info_id")==136){
					patOthDTO.setInfoId136(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==137){
					patOthDTO.setInfoId137(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==139){
					patOthDTO.setInfoId139(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==146){
					patOthDTO.setInfoId146(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==147){
					patOthDTO.setInfoId147(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==149){
					patOthDTO.setInfoId149_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					patOthDTO.setInfoId149_2(rs.getString("input_value_2"));
				}else if(rs.getInt("info_id")==150){
					patOthDTO.setInfoId150(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==151){
					patOthDTO.setInfoId151(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(rs.getInt("info_id")==154){
					patOthDTO.setInfoId154(rs.getString("input_value"));
				}		
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_personal_info_history where patient_id="+accId+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfImmunization+")");
			while(rs.next()){
				patOthDTO.patImmunizationHistoryId.add(rs.getInt("info_id"));
				if(rs.getInt("info_id")==41){
					patOthDTO.setFamlyDisOther(rs.getString("input_value"));
				}
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_details where patient_id="+accId +" and visit_id="+currentVisitId );
			if(rs.next()){
				patOthDTO.setFather(rs.getString("father"));
				patOthDTO.setMother(rs.getString("mother"));
				patOthDTO.setBrother(rs.getString("brother"));
				patOthDTO.setSister(rs.getString("sister"));
				patOthDTO.setWifeOrHusband(rs.getString("wife_or_husband"));
				patOthDTO.setConsanguiOfMarri(rs.getString("consanguinity_of_marriage"));
				patOthDTO.setMaritalStatus(rs.getInt("marital_status"));
				patOthDTO.setHobbies(rs.getString("hobbies"));
				patOthDTO.setReligionId(rs.getInt("relision_id"));
				patOthDTO.setSocialStatusId(rs.getInt("social_status_id"));
				patOthDTO.setOccupation(rs.getString("occupation"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_pre_anesthetic_evaluation where patient_id="+accId+" and visit_id="+currentVisitId);
			if(rs.next()){
				patOthDTO.setCurProb(rs.getString("cur_prob"));
				patOthDTO.setOtherKnownProb(rs.getString("other_known_prob"));
				patOthDTO.setCurProbTreatment(rs.getString("cur_prob_treatment_or_medicines"));
				patOthDTO.setCurDose(rs.getString("cur_dose"));
				patOthDTO.setCurDoseDuration(rs.getString("cur_dose_duration"));
				patOthDTO.setCurDoseEffectiveness(rs.getString("cur_dose_effectiveness"));
				patOthDTO.setCurDrugsUse(rs.getString("cur_drugs_use"));
				patOthDTO.setCurDrugsReason(rs.getString("cur_drugs_reason"));
				patOthDTO.setCurDrugsDose(rs.getString("cur_drugs_dose"));
				patOthDTO.setCurDrugsDur(rs.getString("cur_drugs_dur"));
				patOthDTO.setCurDrugsEffectiveness(rs.getString("cur_drugs_effectiveness"));
				patOthDTO.setCurDrugsSideEffect(rs.getString("cur_drugs_side_effect"));
			}
			rs.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}	
		return patOthDTO;
	}
	
	public PatientOthersDTO getPatientPhyExmInfoDTOByID(int accId) {

		int currentVisitId = new VisitDAO().getCurrentVisitId(accId);

		Connection conn=null;
		Statement stmt=null;
		
		PatientOthersDTO patOthDTO = new PatientOthersDTO();
		
		patOthDTO.patInspectionId = new HashSet<Integer>();
		patOthDTO.patPalpationId = new HashSet<Integer>();
		
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from tbl_patient_disease_inspection where patient_id="+accId+" and visit_id="+currentVisitId+" and inspection_id in (select id from tbl_disease_inspection where dis_id=14)");
			while(rs.next()){
				patOthDTO.patInspectionId.add(rs.getInt("inspection_id"));
				if(rs.getInt("inspection_id")==15){
					patOthDTO.setInspecOthers(rs.getString("input_value"));
				}
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_palpation where patient_id="+accId+" and visit_id="+currentVisitId+" and palpation_id in (select id from tbl_disease_palpation where dis_id=14)");
			while(rs.next()){
				patOthDTO.patPalpationId.add(rs.getInt("palpation_id"));
				if(rs.getInt("palpation_id")==15){
					patOthDTO.setPalpaOthers(rs.getString("input_value"));
				}
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_phy_exm where patient_id="+accId+" and visit_id="+currentVisitId);
			if(rs.next()){
				patOthDTO.setVsTemp(rs.getString("vsTemp"));
				patOthDTO.setVsPulse(rs.getString("vsPulse"));
				patOthDTO.setVsRate(rs.getString("vsRate"));
				patOthDTO.setVsBloodPre(rs.getString("vsBloodPre"));
				patOthDTO.setBsHeight(rs.getString("bsHeight"));
				patOthDTO.setBsWeight(rs.getString("bsWeight"));
				patOthDTO.setBsBMI(rs.getString("bsBMI"));
				patOthDTO.setBsWC(rs.getString("bsWC"));
				patOthDTO.setClInspec(rs.getString("clInspec"));
				patOthDTO.setClPalpi(rs.getString("clPalpi"));
				patOthDTO.setClPrec(rs.getString("clPrec"));
				patOthDTO.setClAusc(rs.getString("clAusc"));
				patOthDTO.setJvp(rs.getString("jvp"));
				patOthDTO.setCarotidPulsCar(rs.getString("carotidPulsCar"));
				patOthDTO.setApexImp(rs.getString("apexImp"));
				patOthDTO.setParastImp(rs.getString("parastImp"));
				patOthDTO.setPalpaHs(rs.getString("palpaHs"));
				patOthDTO.setThrill(rs.getString("thrill"));
				patOthDTO.setHs(rs.getString("hs"));
				patOthDTO.setEc(rs.getString("ec"));
				patOthDTO.setOs(rs.getString("os"));
				patOthDTO.setMurmur(rs.getString("murmur"));
				patOthDTO.setPeripheralPul(rs.getString("peripheralPul"));
				patOthDTO.setInspecOthers(rs.getString("inspecOthers"));
				patOthDTO.setPalpaOthers(rs.getString("palpaOthers"));
				patOthDTO.setPrecusNote(rs.getString("precusNote"));
				patOthDTO.setAuscultion(rs.getString("auscultion"));
				patOthDTO.setNeckVein(rs.getInt("neckVein"));
				patOthDTO.setMovOfNeck(rs.getInt("movOfNeck"));
				patOthDTO.setThyroidGland(rs.getInt("thyroidGland"));
				patOthDTO.setCarotidPulse(rs.getString("carotidPulse"));
				patOthDTO.setGenIntFunc(rs.getString("genIntFunc"));
				patOthDTO.setMotorFunc(rs.getString("motorFunc"));
				patOthDTO.setSensory(rs.getString("sensory"));
				patOthDTO.setCarnialNerve(rs.getString("carnialNerve"));
				patOthDTO.setOtherDescip(rs.getString("otherDescip"));
			}
			rs.close();
			
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}	
		return patOthDTO;
	}
	
	public DAOResult updatePatientPhyExmInfoDTO(PatientOthersDTO dto) {

		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserId());

		Connection conn=null;
		Statement stmt=null;
		String sql=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		int sizeOfArray=0;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("delete from tbl_patient_phy_exm where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId);
			stmt.executeUpdate("delete from tbl_patient_disease_palpation where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and palpation_id in (select id from tbl_disease_palpation where dis_id=14)");
			stmt.executeUpdate("delete from tbl_patient_disease_inspection where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and inspection_id in (select id from tbl_disease_inspection where dis_id=14)");
			
			sql="insert into tbl_patient_phy_exm(patient_id, visit_id, vsTemp, vsPulse, vsRate, vsBloodPre, bsHeight, bsWeight, bsBMI, bsWC, clInspec, clPalpi,clPrec,clAusc,jvp,carotidPulsCar,apexImp,parastImp,palpaHs,thrill,hs,ec,os,murmur,peripheralPul,inspecOthers,palpaOthers,precusNote,auscultion,carotidPulse,genIntFunc,motorFunc,sensory,carnialNerve,otherDescip,neckVein,movOfNeck,thyroidGland) " +
					"values("+dto.getUserId()+", "+currentVisitId+", '"+dto.getVsTemp()+"', '"+dto.getVsPulse()+"', '"+dto.getVsRate()+"', '"+dto.getVsBloodPre()+"', '"+dto.getBsHeight()+"', '"+dto.getBsWeight()+"', '"+dto.getBsBMI()+"', '"+dto.getBsWC()+"', '"+dto.getClInspec()+"', '"+dto.getClPalpi()+"', '"+dto.getClPrec()+"', '"+dto.getClAusc()+"', '"+dto.getJvp()+"', '"+dto.getCarotidPulsCar()+"', '"+dto.getApexImp()+"', '"+dto.getParastImp()+"', '"+dto.getPalpaHs()+"', '"+dto.getThrill()+"', '"+dto.getHs()+"', '"+dto.getEc()+"', '"+dto.getOs()+"', '"+dto.getMurmur()+"', '"+dto.getPeripheralPul()+"', '"+dto.getInspecOthers()+"', '"+dto.getPalpaOthers()+"', '"+dto.getPrecusNote()+"', '"+dto.getAuscultion()+"', '"+dto.getCarotidPulse()+"', '"+dto.getGenIntFunc()+"', '"+dto.getMotorFunc()+"', '"+dto.getSensory()+"', '"+dto.getCarnialNerve()+"', '"+dto.getOtherDescip()+"',"+dto.getNeckVein()+", "+dto.getMovOfNeck()+", "+dto.getThyroidGland()+")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			{
				try{
					sizeOfArray=dto.getPalpationId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				for(int i=0;i<sizeOfArray;i++){
					stmt.execute("insert into tbl_patient_disease_palpation(patient_id, visit_id, palpation_id) values("+dto.getUserId()+", "+currentVisitId+", "+dto.getPalpationId()[i]+")");
					if(dto.getPalpationId()[i]==15){
						String tmp=dto.getPalpaOthers();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_disease_palpation set input_value='"+tmp+"' where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and palpation_id="+dto.getPalpationId()[i]);
						}
					}
				}
			}
			
			{
				try{
					sizeOfArray=dto.getInspectionId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				for(int i=0;i<sizeOfArray;i++){
					stmt.execute("insert into tbl_patient_disease_inspection(patient_id, visit_id, inspection_id) values("+dto.getUserId()+", "+currentVisitId+", "+dto.getInspectionId()[i]+")");
					if(dto.getInspectionId()[i]==15){
						String tmp=dto.getInspecOthers();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_disease_inspection set input_value='"+tmp+"' where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId +" and inspection_id="+dto.getInspectionId()[i]);
						}
					}
				}
			}
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public DAOResult updatePatientGeneralSurveyDTO(PatientOthersDTO dto) {

		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserId());

		Connection conn=null;
		Statement stmt=null;		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		int sizeOfArray=0;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoGeneralAssessmentOfIllness+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoMentalStateAndIntelligence+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoMentalState+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoFacies+")");
			
			
			{
				try{
					sizeOfArray=dto.getInfoId().length;
				}catch(Exception e){sizeOfArray=0;};
				String tail_1="", tail_2="";
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getInfoId()[i]==154){
						tail_1=", input_value"; tail_2=", '"+dto.getInfoId154()+"'";
					}
					stmt.execute("insert into tbl_patient_personal_info_history(patient_id, visit_id, info_id"+tail_1+") values("+dto.getUserId()+", "+currentVisitId+", "+dto.getInfoId()[i]+tail_2+")");
					tail_1="";
					tail_2="";
				}
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public DAOResult updatePatientPreAnestheticEvaluationDTO(PatientOthersDTO dto) {

		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserId());

		Connection conn=null;
		Statement stmt=null;		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		int sizeOfArray=0;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+ " and visit_id="+ currentVisitId +" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfPreAnestheticEvaluation+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+ " and visit_id="+ currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfPreAnEvIntraOralExamination+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+ " and visit_id="+ currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfPreAnEvExtraOralExamination+")");
			stmt.executeUpdate("delete from tbl_pre_anesthetic_evaluation where patient_id=" + dto.getUserId() + " and visit_id=" + currentVisitId);
			
			stmt.execute("insert into tbl_pre_anesthetic_evaluation(patient_id, visit_id, cur_prob, other_known_prob, cur_prob_treatment_or_medicines, cur_dose, cur_dose_duration, cur_dose_effectiveness, cur_drugs_use, cur_drugs_reason, cur_drugs_dose, cur_drugs_dur, cur_drugs_effectiveness, cur_drugs_side_effect) " +
					"values("+dto.getUserId()+", "+currentVisitId+",'"+dto.getCurProb()+"','"+dto.getOtherKnownProb()+"','"+dto.getCurProbTreatment()+"','"+dto.getCurDose()+"','"+dto.getCurDoseDuration()+"','"+dto.getCurDoseEffectiveness()+"','"+dto.getCurDrugsUse()+"','"+dto.getCurDrugsReason()+"','"+dto.getCurDrugsDose()+"','"+dto.getCurDrugsDur()+"','"+dto.getCurDrugsEffectiveness()+"','"+dto.getCurDrugsSideEffect()+"')");
			
			{
				try{
					sizeOfArray=dto.getInfoId().length;
				}catch(Exception e){sizeOfArray=0;};
				String tail_1="", tail_2="";
				String visit = "(select max(id) from visit where patient_id="+dto.getUserId()+")";
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getInfoId()[i]==99){
						tail_1=", input_value"; tail_2=", '"+dto.getInfoId99()+"'";
					}else if(dto.getInfoId()[i]==100){
						tail_1=", input_value, input_value_2"; tail_2=", '"+dto.getInfoId100_1()+"', '"+dto.getInfoId100_2()+"'";
					}else if(dto.getInfoId()[i]==101){
						tail_1=", input_value"; tail_2=", '"+dto.getInfoId101()+"'";
					}else if(dto.getInfoId()[i]==121){
						tail_1=", input_value"; tail_2=", '"+dto.getInfoId121()+"'";
					}else if(dto.getInfoId()[i]==125){
						tail_1=", input_value"; tail_2=", '"+dto.getInfoId125()+"'";
					}else if(dto.getInfoId()[i]==136){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId136();
					}else if(dto.getInfoId()[i]==137){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId137();
					}else if(dto.getInfoId()[i]==139){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId139();
					}else if(dto.getInfoId()[i]==146){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId146();
					}else if(dto.getInfoId()[i]==147){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId147();
					}else if(dto.getInfoId()[i]==149){
						tail_1=", input_value, input_value_2"; tail_2=", "+dto.getInfoId149_1()+", '"+dto.getInfoId149_2()+"'";
					}else if(dto.getInfoId()[i]==150){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId150();
					}else if(dto.getInfoId()[i]==151){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId151();
					}
					stmt.execute("insert into tbl_patient_personal_info_history(patient_id, visit_id, info_id"+tail_1+") values("+dto.getUserId()+", "+currentVisitId+", "+dto.getInfoId()[i]+tail_2+")");
					tail_1="";
					tail_2="";
				}
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public DAOResult updatePatientInvestigationDTO(PatientOthersDTO dto) {

		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserId());

		Connection conn=null;
		Statement stmt=null;		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		int sizeOfArray=0;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+ " and visit_id="+ currentVisitId +" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfBlood+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+ " and visit_id="+ currentVisitId +" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfOthers+")");
						
			{
				try{
					sizeOfArray=dto.getInfoId().length;
				}catch(Exception e){sizeOfArray=0;};
				String tail_1="", tail_2="";

				for(int i=0;i<sizeOfArray;i++){
					
					if(dto.getInfoId()[i]==75){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId75();
					}else if(dto.getInfoId()[i]==76){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId76();
					}else if(dto.getInfoId()[i]==86){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId86();
					}else if(dto.getInfoId()[i]==87){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId87();
					}else if(dto.getInfoId()[i]==92){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId92();
					}else if(dto.getInfoId()[i]==93){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId93();
					}else if(dto.getInfoId()[i]==94){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId94();
					}else if(dto.getInfoId()[i]==95){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId95();
					}else if(dto.getInfoId()[i]==96){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId96();
					}else if(dto.getInfoId()[i]==97){
						tail_1=", input_value"; tail_2=", "+dto.getInfoId97();
					}else if(dto.getInfoId()[i]==98){
						tail_1=", input_value, input_value_2"; tail_2=", '"+dto.getInfoId98_1()+"', '"+dto.getInfoId98_2()+"'";
					}
					stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id "+tail_1+") values("+dto.getUserId()+", "+dto.getInfoId()[i] +","+ currentVisitId + tail_2+")");
					tail_1="";
					tail_2="";
				}
			}
			
			
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public DAOResult updatePatientOthersDTO(PatientOthersDTO dto) {
		
		Connection conn=null;
		Statement stmt=null;
		String sql=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Updated Successfully");
		int sizeOfArray=0;
		int currentVisitId = new VisitDAO().getCurrentVisitId(dto.getUserId());
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("delete from tbl_patient_details where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId );
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHabits+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoExposureTo+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoMenstrualHistory+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoObstetricalHistory+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoFamilyRelevantDiseaseHistory+")");
			stmt.executeUpdate("delete from tbl_patient_personal_info_history where patient_id="+dto.getUserId()+" and visit_id="+currentVisitId+" and info_id in (select id from tbl_personal_info_history where info_id="+MyConfig.infoHistoryOfImmunization+")");
			
			sql="insert into tbl_patient_details(patient_id, visit_id, marital_status, hobbies, father, mother, sister, brother, wife_or_husband, consanguinity_of_marriage, relision_id, social_status_id, occupation) " +
					"values("+dto.getUserId()+","+currentVisitId+", "+dto.getMaritalStatus()+", '"+dto.getHobbies()+"', '"+dto.getFather()+"', '"+dto.getMother()+"', '"+dto.getSister()+"', '"+dto.getBrother()+"', '"+dto.getWifeOrHusband()+"', '"+dto.getConsanguiOfMarri()+"', "+dto.getReligionId()+", "+dto.getSocialStatusId()+", '"+dto.getOccupation()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			{
				try{
					sizeOfArray=dto.getExposureTo().length;
				}catch(Exception e){sizeOfArray=0;};
				
				for(int i=0;i<sizeOfArray;i++){
					stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id) values("+dto.getUserId()+","+dto.getExposureTo()[i]+","+currentVisitId+")");
				}
			}
			
			{
				try{
					sizeOfArray=dto.getHabitsId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				for(int i=0;i<sizeOfArray;i++){
					stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id) values("+dto.getUserId()+", "+dto.getHabitsId()[i]+","+currentVisitId+")");
					
					if(dto.getHabitsId()[i]==4){
						String tmp=dto.getCigarettesStick();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
						tmp=dto.getCigarettesYear();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value_2='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
					}else if(dto.getHabitsId()[i]==5){
						String tmp=dto.getSmokelessTobaccoTime();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);

						}
						tmp=dto.getSmokelessTobaccoYear();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value_2='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
					}else if(dto.getHabitsId()[i]==6){
						String tmp=dto.getAlcoholPack();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
						tmp=dto.getAlcoholYear();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value_2='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
					}else if(dto.getHabitsId()[i]==7){
						String tmp=dto.getOtherSubsAbuse();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
						tmp=dto.getOtherSubsAbuseYear();
						if(tmp!=null && tmp.length()>0){
							stmt.executeUpdate("update tbl_patient_personal_info_history set input_value_2='"+tmp+"' where patient_id="+dto.getUserId()+" and info_id="+dto.getHabitsId()[i]+" and visit_id="+currentVisitId);
						}
					}
				}
			}
			
			
			{
				{
					try{
						sizeOfArray=dto.getMenstrualHistoryId().length;
					}catch(Exception e){sizeOfArray=0;};
					
					for(int i=0;i<sizeOfArray;i++){
						stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id) values("+dto.getUserId()+", "+dto.getMenstrualHistoryId()[i]+","+currentVisitId+")");
					}
				}
			}
			
			
			{
				{
					try{
						sizeOfArray=dto.getObstetricalHistoryId().length;
					}catch(Exception e){sizeOfArray=0;};
					
					for(int i=0;i<sizeOfArray;i++){
						stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id) values("+dto.getUserId()+", "+dto.getObstetricalHistoryId()[i]+","+currentVisitId+")");
					}
				}
			}
			
			
			{
				{
					try{
						sizeOfArray=dto.getRelevantDiseaseHistoryId().length;
					}catch(Exception e){sizeOfArray=0;};
					
					for(int i=0;i<sizeOfArray;i++){
						stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id) values("+dto.getUserId()+", "+dto.getRelevantDiseaseHistoryId()[i]+","+currentVisitId+")");
					}
				}
			}
			
			{
				{
					try{
						sizeOfArray=dto.getImmunizationHistoryId().length;
					}catch(Exception e){sizeOfArray=0;};
					
					for(int i=0;i<sizeOfArray;i++){
						stmt.execute("insert into tbl_patient_personal_info_history(patient_id, info_id, visit_id) values("+dto.getUserId()+", "+dto.getImmunizationHistoryId()[i]+","+currentVisitId+")");
					}
				}
			}
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}

	public ArrayList<PatientOthersDTO> getMedicationList(int patientId, int type) {
		ArrayList<PatientOthersDTO> medList  = new ArrayList<PatientOthersDTO>();
		Connection conn=null;
		Statement stmt=null;
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from tbl_patient_medications where patient_id="+patientId+" and type="+type);
	        while(rs.next()){
	        	PatientOthersDTO dto = new PatientOthersDTO();
	        	dto.id=rs.getInt("id");
	        	dto.setADR(rs.getString("adr"));
	        	dto.setDateStarted(rs.getString("date_started"));
	        	dto.setDose(rs.getString("dose"));
	        	dto.setDrugs(rs.getString("drugs"));
	        	
	        	medList.add(dto);
	        }
	        rs.close();
		}catch(Exception e){
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return medList;
	}

	public DAOResult AddPatientMedication(PatientOthersDTO dto) {
		Connection conn=null;
		Statement stmt=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Added Successfully");
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			String sql="insert into tbl_patient_medications(patient_id, drugs, date_started, dose, adr) values( "+dto.getUserId()+", '"+dto.getDrugs()+"', '"+dto.getDateStarted()+"', '"+dto.getDose()+"', '"+dto.getADR()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
	public DAOResult makePastPatientMedication(int id) {
		Connection conn=null;
		Statement stmt=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Done");
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("update tbl_patient_medications set type=-1 where id="+id);
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}

	public DAOResult updatePatientDrugDTO(PatientOthersDTO dto) {
		Connection conn=null;
		Statement stmt=null;
		
		DAOResult daoResult = new DAOResult();
		daoResult.setValid(true);
		daoResult.setMessage("Added Successfully");
		int sizeOfArray=0;
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			stmt.executeUpdate("delete from tbl_patient_drug_group where patient_id="+dto.getUserId());
			
			{
				{
					try{
						sizeOfArray=dto.getDrugHistoryId().length;
					}catch(Exception e){sizeOfArray=0;};
					
					for(int i=0;i<sizeOfArray;i++){
						stmt.execute("insert into tbl_patient_drug_group(patient_id, drug_group_id) values("+dto.getUserId()+", "+dto.getDrugHistoryId()[i]+")");
					}
				}
			}
			
			
		}catch(Exception e){
			daoResult.setValid(false);
			daoResult.setMessage("Database Error: "+e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return daoResult;
	}
	
}
