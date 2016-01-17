package disease;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class GetPatientDiseaseInfoDAO {
	
	public ArrayList<FollowUpDTO> getFollowUpReport(int userID, int diseaseID) {
		Connection conn = null;
		Statement stmt = null;
		ArrayList<FollowUpDTO> dtoList = new ArrayList<FollowUpDTO>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_patient_disease_follow_up where patient_id="+userID+" and disease_id="+diseaseID);
			while(rs.next()){
				FollowUpDTO dto = new FollowUpDTO();
				dto.findings=rs.getString("findings");
				dto.addedTime=rs.getString("added_time");
				dtoList.add(dto);
			}
			rs.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}		
		return dtoList;
	}
	
	public DiseaseDTO getDiseaseInfo(int userID, int diseaseID) {
		DiseaseDTO disDTO= new DiseaseDTO();
		Connection conn = null;
		Statement stmt = null; 
		
		disDTO.patHisId = new HashSet<Integer>();
		disDTO.patSympId = new HashSet<Integer>();
		disDTO.patInspId = new HashSet<Integer>();
		disDTO.patAuscId = new HashSet<Integer>();
		disDTO.patPalpId = new HashSet<Integer>();
		disDTO.patOthersId = new HashSet<Integer>();
		disDTO.patSpCaseId = new HashSet<Integer>();
		disDTO.patTrtmntId = new HashSet<Integer>();
		disDTO.patDiagonosisId = new HashSet<Integer>();
		disDTO.patPrtFactId = new HashSet<Integer>();
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from tbl_patient_disease_history where patient_id="+userID+" and history_id in (select id from tbl_disease_history where dis_id="+diseaseID+")");
			while(rs.next()){
				int historyId=rs.getInt("history_id");
				
				if(historyId==41){
					disDTO.setOtherHistoryRes(rs.getString("input_value"));
				}if(historyId==54){
					disDTO.setLastDateOfDialysis(rs.getString("input_value"));
					disDTO.setFrequencyOfDialysis(rs.getString("input_value_2"));
				}else if(historyId==79){
					disDTO.setHisDescripId79(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==80){
					disDTO.setHisDescripId80(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==81){
					disDTO.setHisDescripId81(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==82){
					disDTO.setHisDescripId82(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==83){
					disDTO.setHisDescripId83(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==84){
					disDTO.setHisDescripId84(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==85){
					disDTO.setHisDescripId85(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==86){
					disDTO.setHisDescripId86(rs.getString("input_value"));
				}else if(historyId==87){
					disDTO.setHisDescripId87(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==88){
					disDTO.setHisDescripId88(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==89){
					disDTO.setHisDescripId89(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==90){
					disDTO.setHisDescripId90(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==91){
					disDTO.setHisDescripId91(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==92){
					disDTO.setHisDescripId92(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==93){
					disDTO.setHisDescripId93(rs.getString("input_value"));
				}else if(historyId==106){
					disDTO.setHisDescripId106(rs.getString("input_value"));
				}else if(historyId==131){
					disDTO.setHisDescripId131(rs.getString("input_value"));
				}else if(historyId==136){
					disDTO.setHisDescripId136(rs.getString("input_value"));
				}else if(historyId==139){
					disDTO.setHisDescripId139(rs.getString("input_value"));
				}else if(historyId==140){
					disDTO.setHisDescripId140_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					disDTO.setHisDescripId140_2(rs.getString("input_value_2"));
				}else if(historyId==141){
					disDTO.setHisDescripId141_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					disDTO.setHisDescripId141_2(rs.getString("input_value_2"));
				}else if(historyId==148){
					disDTO.setHisDescripId148(rs.getString("input_value"));
				}else if(historyId==162){
					disDTO.setHisDescripId162(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==165){
					disDTO.setHisDescripId165(rs.getString("input_value"));
				}else if(historyId==167){
					disDTO.setHisDescripId167(rs.getString("input_value"));
				}else if(historyId==168){
					disDTO.setHisDescripId168(rs.getString("input_value"));
				}else if(historyId==169){
					disDTO.setHisDescripId169(rs.getString("input_value"));
				}else if(historyId==170){
					disDTO.setHisDescripId170(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==171){
					disDTO.setHisDescripId171(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==173){
					disDTO.setHisDescripId173(rs.getString("input_value"));
				}else if(historyId==174){
					disDTO.setHisDescripId174(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==175){
					disDTO.setHisDescripId175(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==176){
					disDTO.setHisDescripId176(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==177){
					disDTO.setHisDescripId177(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==178){
					disDTO.setHisDescripId178_1(rs.getString("input_value"));
					disDTO.setHisDescripId178_2(rs.getString("input_value_2"));
					disDTO.setHisDescripId178_3(rs.getString("input_value_3"));
					disDTO.setHisDescripId178_4(rs.getString("input_value_4"));
				}else if(historyId==181){
					disDTO.setHisDescripId181(rs.getString("input_value"));
				}else if(historyId==204){
					disDTO.setHisDescripId204(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==211){
					disDTO.setHisDescripId211(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==219){
					disDTO.setHisDescripId219(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==222){
					disDTO.setHisDescripId222(rs.getString("input_value"));
				}else if(historyId==223){
					disDTO.setHisDescripId223(rs.getString("input_value"));
				}else if(historyId==224){
					disDTO.setFrequency(rs.getString("input_value"));
				}else if(historyId==225){
					disDTO.setSeverity(rs.getString("input_value"));
				}else if(historyId==226){
					disDTO.setDuration(rs.getString("input_value"));
				}else if(historyId==227){
					disDTO.setPeriodOfRemission(rs.getString("input_value"));
				}else if(historyId==232){
					disDTO.setHisDescripId232(rs.getString("input_value"));
				}else if(historyId==229){
					disDTO.setHisDescripId229(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(historyId==230){
					disDTO.setHisDescripId230(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}
				disDTO.patHisId.add(historyId);
			}
			rs.close();
			
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_symptom where patient_id="+userID+" and symptom_id in (select id from tbl_disease_symptom where dis_id="+diseaseID+")");
			while(rs.next()){
				disDTO.patSympId.add(rs.getInt("symptom_id"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_auscultation where patient_id="+userID+" and auscultation_id in (select id from tbl_disease_auscultation where dis_id="+diseaseID+")");
			while(rs.next()){
				disDTO.patAuscId.add(rs.getInt("auscultation_id"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_perticipating_factor where patient_id="+userID+" and p_factor_id in (select id from tbl_disease_perticipating_factor where dis_id="+diseaseID+")");
			while(rs.next()){
				if(rs.getInt("p_factor_id")==9){
					disDTO.setOtherPertFact(rs.getString("input_value"));
				}
				disDTO.patPrtFactId.add(rs.getInt("p_factor_id"));
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_special_case_details where patient_id="+userID+" and sp_case_id in (select id from tbl_disease_special_case_details where dis_id="+diseaseID+")");
			while(rs.next()){
				int specialCaseId=rs.getInt("sp_case_id");
				if(specialCaseId==1){
					disDTO.setSpecialCaseId1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==2){
					disDTO.setSpecialCaseId2(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==3){
					disDTO.setSpecialCaseId3(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==4){
					disDTO.setSpecialCaseId4(rs.getString("input_value"));
				}else if(specialCaseId==6){
					disDTO.setSpecialCaseId6(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==7){
					disDTO.setSpecialCaseId7(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==8){
					disDTO.setSpecialCaseId8(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==9){
					disDTO.setSpecialCaseId9_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId9_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId9_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId9_4(rs.getString("input_value_4"));
				}else if(specialCaseId==10){
					disDTO.setSpecialCaseId10_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId10_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId10_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId10_4(rs.getString("input_value_4"));
				}else if(specialCaseId==11){
					disDTO.setSpecialCaseId11(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==12){
					disDTO.setSpecialCaseId12_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId12_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId12_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId12_4(rs.getString("input_value_4"));
				}else if(specialCaseId==13){
					disDTO.setSpecialCaseId13(rs.getString("input_value"));
				}else if(specialCaseId==14){
					disDTO.setSpecialCaseId14(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==15){
					disDTO.setSpecialCaseId15(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==16){
					disDTO.setSpecialCaseId16(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==17){
					disDTO.setSpecialCaseId17(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==18){
					disDTO.setSpecialCaseId18(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==19){
					disDTO.setSpecialCaseId19(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==20){
					disDTO.setSpecialCaseId20(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==21){
					disDTO.setSpecialCaseId21(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==22){
					disDTO.setSpecialCaseId22(rs.getString("input_value"));
				}else if(specialCaseId==23){
					disDTO.setSpecialCaseId23(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==24){
					disDTO.setSpecialCaseId24(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==25){
					disDTO.setSpecialCaseId25(rs.getString("input_value"));
				}else if(specialCaseId==26){
					disDTO.setSpecialCaseId26(rs.getString("input_value"));
				}else if(specialCaseId==27){
					disDTO.setSpecialCaseId27(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==28){
					disDTO.setSpecialCaseId28(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==30){
					disDTO.setSpecialCaseId30(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==31){
					disDTO.setSpecialCaseId31(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==32){
					disDTO.setSpecialCaseId32(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==33){
					disDTO.setSpecialCaseId33(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==34){
					disDTO.setSpecialCaseId34(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==35){
					disDTO.setSpecialCaseId35(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==36){
					disDTO.setSpecialCaseId36(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==37){
					disDTO.setSpecialCaseId37(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==39){
					disDTO.setSpecialCaseId39(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==40){
					disDTO.setSpecialCaseId40(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==42){
					disDTO.setSpecialCaseId42(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==43){
					disDTO.setSpecialCaseId43(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==47){
					disDTO.setSpecialCaseId47(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==48){
					disDTO.setSpecialCaseId48(rs.getString("input_value"));
				}else if(specialCaseId==49){
					disDTO.setSpecialCaseId49(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==50){
					disDTO.setSpecialCaseId50(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==52){
					disDTO.setSpecialCaseId52(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==53){
					disDTO.setSpecialCaseId53(rs.getString("input_value"));
				}else if(specialCaseId==54){
					disDTO.setSpecialCaseId54(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==55){
					disDTO.setSpecialCaseId55(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==56){
					disDTO.setSpecialCaseId56(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==57){
					disDTO.setSpecialCaseId57(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==58){
					disDTO.setSpecialCaseId58(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==62){
					disDTO.setSpecialCaseId62(rs.getString("input_value"));
				}else if(specialCaseId==63){
					disDTO.setSpecialCaseId63(rs.getString("input_value"));
				}else if(specialCaseId==64){
					disDTO.setSpecialCaseId64(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==71){
					disDTO.setSpecialCaseId71(rs.getString("input_value"));
				}else if(specialCaseId==74){
					disDTO.setSpecialCaseId74(rs.getString("input_value"));
				}else if(specialCaseId==75){
					disDTO.setSpecialCaseId75(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==96){
					disDTO.setSpecialCaseId96(rs.getString("input_value"));
				}else if(specialCaseId==97){
					disDTO.setSpecialCaseId97(rs.getString("input_value"));
				}else if(specialCaseId==98){
					disDTO.setSpecialCaseId98(rs.getString("input_value"));
				}else if(specialCaseId==99){
					disDTO.setSpecialCaseId99(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==100){
					disDTO.setSpecialCaseId100(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==101){
					disDTO.setSpecialCaseId101(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==102){
					disDTO.setSpecialCaseId102(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==103){
					disDTO.setSpecialCaseId103(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==104){
					disDTO.setSpecialCaseId104(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==105){
					disDTO.setSpecialCaseId105(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==106){
					disDTO.setSpecialCaseId106(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==107){
					disDTO.setSpecialCaseId107_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId107_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId107_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId107_4(rs.getString("input_value_4"));
				}else if(specialCaseId==108){
					disDTO.setSpecialCaseId108_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId108_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId108_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId108_4(rs.getString("input_value_4"));
				}else if(specialCaseId==109){
					disDTO.setSpecialCaseId109_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId109_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId109_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId109_4(rs.getString("input_value_4"));
				}else if(specialCaseId==110){
					disDTO.setSpecialCaseId110_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId110_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId110_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId110_4(rs.getString("input_value_4"));
				}else if(specialCaseId==111){
					disDTO.setSpecialCaseId111_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId111_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId111_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId111_4(rs.getString("input_value_4"));
				}else if(specialCaseId==112){
					disDTO.setSpecialCaseId112_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId112_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId112_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId112_4(rs.getString("input_value_4"));
				}else if(specialCaseId==113){
					disDTO.setSpecialCaseId113_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId113_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId113_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId113_4(rs.getString("input_value_4"));
				}else if(specialCaseId==114){
					disDTO.setSpecialCaseId114_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId114_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId114_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId114_4(rs.getString("input_value_4"));
				}else if(specialCaseId==115){
					disDTO.setSpecialCaseId115_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId115_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId115_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId115_4(rs.getString("input_value_4"));
				}else if(specialCaseId==116){
					disDTO.setSpecialCaseId116(rs.getString("input_value"));
				}else if(specialCaseId==117){
					disDTO.setSpecialCaseId117(rs.getString("input_value"));
				}else if(specialCaseId==118){
					disDTO.setSpecialCaseId118(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==119){
					disDTO.setSpecialCaseId119(rs.getString("input_value"));
				}else if(specialCaseId==123){
					disDTO.setSpecialCaseId123(rs.getString("input_value"));
				}else if(specialCaseId==125){
					disDTO.setSpecialCaseId125(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==126){
					disDTO.setSpecialCaseId126_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId126_2(rs.getString("input_value_2"));
				}else if(specialCaseId==127){
					disDTO.setSpecialCaseId127_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId127_2(rs.getString("input_value_2"));
				}else if(specialCaseId==128){
					disDTO.setSpecialCaseId128(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==129){
					disDTO.setSpecialCaseId129(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==130){
					disDTO.setSpecialCaseId130(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==131){
					disDTO.setSpecialCaseId131(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==132){
					disDTO.setSpecialCaseId132(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==134){
					disDTO.setSpecialCaseId134(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==135){
					disDTO.setSpecialCaseId135(rs.getString("input_value"));
				}else if(specialCaseId==136){
					disDTO.setSpecialCaseId136(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==137){
					disDTO.setSpecialCaseId137(rs.getString("input_value"));
				}else if(specialCaseId==138){
					disDTO.setSpecialCaseId138_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					disDTO.setSpecialCaseId138_2(rs.getString("input_value_2"));
				}else if(specialCaseId==141){
					disDTO.setSpecialCaseId141_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					disDTO.setSpecialCaseId141_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId141_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId141_4(rs.getString("input_value_4"));
				}else if(specialCaseId==121){
					disDTO.setSpecialCaseId121_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					disDTO.setSpecialCaseId121_2(rs.getString("input_value_2"));
				}else if(specialCaseId==122){
					disDTO.setSpecialCaseId122(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==152){
					disDTO.setSpecialCaseId152(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==175){
					disDTO.setSpecialCaseId175(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==176){
					disDTO.setSpecialCaseId176(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==179){
					disDTO.setSpecialCaseId179(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==180){
					disDTO.setSpecialCaseId180(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==177){
					disDTO.setSpecialCaseId177(rs.getString("input_value"));
				}else if(specialCaseId==178){
					disDTO.setSpecialCaseId178(rs.getString("input_value"));
				}else if(specialCaseId==133){
					disDTO.setSpecialCaseId133(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==188){
					disDTO.setSpecialCaseId188(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==189){
					disDTO.setSpecialCaseId189(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==190){
					disDTO.setSpecialCaseId190(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==191){
					disDTO.setSpecialCaseId191(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==192){
					disDTO.setSpecialCaseId192(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==193){
					disDTO.setSpecialCaseId193(rs.getString("input_value"));
				}else if(specialCaseId==194){
					disDTO.setSpecialCaseId194(rs.getString("input_value"));
				}else if(specialCaseId==195){
					disDTO.setSpecialCaseId195(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==196){
					disDTO.setSpecialCaseId196(rs.getString("input_value"));
				}else if(specialCaseId==197){
					disDTO.setSpecialCaseId197(rs.getString("input_value"));
				}else if(specialCaseId==198){
					disDTO.setSpecialCaseId198(rs.getString("input_value"));
				}else if(specialCaseId==204){
					disDTO.setSpecialCaseId204(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==205){
					disDTO.setSpecialCaseId205(rs.getString("input_value"));
				}else if(specialCaseId==206){
					disDTO.setSpecialCaseId206(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==207){
					disDTO.setSpecialCaseId207_1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
					disDTO.setSpecialCaseId207_2(rs.getString("input_value"));
				}else if(specialCaseId==208){
					disDTO.setSpecialCaseId208(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==209){
					disDTO.setSpecialCaseId209(rs.getString("input_value"));
				}else if(specialCaseId==210){
					disDTO.setSpecialCaseId210(rs.getString("input_value"));
				}else if(specialCaseId==216){
					disDTO.setSpecialCaseId216(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==217){
					disDTO.setSpecialCaseId217(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==218){
					disDTO.setSpecialCaseId218(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==219){
					disDTO.setSpecialCaseId219(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==220){
					disDTO.setSpecialCaseId220(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==223){
					disDTO.setSpecialCaseId223_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId223_2(rs.getString("input_value_2"));
				}else if(specialCaseId==225){
					disDTO.setSpecialCaseId225(rs.getString("input_value"));
				}else if(specialCaseId==226){
					disDTO.setSpecialCaseId226(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==227){
					disDTO.setSpecialCaseId227(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==228){
					disDTO.setSpecialCaseId228(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==229){
					disDTO.setSpecialCaseId229(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==220){
					disDTO.setSpecialCaseId220(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==232){
					disDTO.setSpecialCaseId232(rs.getString("input_value"));
				}else if(specialCaseId==257){
					disDTO.setSpecialCaseId257(rs.getString("input_value"));
				}else if(specialCaseId==258){
					disDTO.setSpecialCaseId258(rs.getString("input_value"));
				}else if(specialCaseId==259){
					disDTO.setSpecialCaseId259(rs.getString("input_value"));
				}else if(specialCaseId==263){
					disDTO.setSpecialCaseId263(rs.getString("input_value"));
				}else if(specialCaseId==267){
					disDTO.setSpecialCaseId267(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==270){
					disDTO.setSpecialCaseId270(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==271){
					disDTO.setSpecialCaseId271(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==266){
					disDTO.setSpecialCaseId266_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId266_2(rs.getString("input_value_2"));
				}else if(specialCaseId==307){
					disDTO.setSpecialCaseId307(rs.getString("input_value"));
				}else if(specialCaseId==308){
					disDTO.setSpecialCaseId308(rs.getString("input_value"));
				}else if(specialCaseId==309){
					disDTO.setSpecialCaseId309(rs.getString("input_value"));
				}else if(specialCaseId==311){
					disDTO.setSpecialCaseId311(rs.getString("input_value"));
				}else if(specialCaseId==269){
					disDTO.setSpecialCaseId269(rs.getString("input_value"));
				}else if(specialCaseId==282){
					disDTO.setSpecialCaseId282(rs.getString("input_value"));
				}else if(specialCaseId==323){
					disDTO.setSpecialCaseId323(rs.getString("input_value"));
				}else if(specialCaseId==335){
					disDTO.setSpecialCaseId335(rs.getString("input_value"));
				}else if(specialCaseId==340){
					disDTO.setSpecialCaseId340(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==341){
					disDTO.setSpecialCaseId341(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==384){
					disDTO.setSpecialCaseId384(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==385){
					disDTO.setSpecialCaseId385(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==376){
					disDTO.setSpecialCaseId376_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId376_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId376_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId376_4(rs.getString("input_value_4"));
				}else if(specialCaseId==377){
					disDTO.setSpecialCaseId377_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId377_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId377_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId377_4(rs.getString("input_value_4"));
				}else if(specialCaseId==378){
					disDTO.setSpecialCaseId378_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId378_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId378_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId378_4(rs.getString("input_value_4"));
				}else if(specialCaseId==379){
					disDTO.setSpecialCaseId379_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId379_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId379_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId379_4(rs.getString("input_value_4"));
				}else if(specialCaseId==380){
					disDTO.setSpecialCaseId380_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId380_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId380_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId380_4(rs.getString("input_value_4"));
				}else if(specialCaseId==381){
					disDTO.setSpecialCaseId381_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId381_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId381_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId381_4(rs.getString("input_value_4"));
				}else if(specialCaseId==382){
					disDTO.setSpecialCaseId382_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId382_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId382_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId382_4(rs.getString("input_value_4"));
				}else if(specialCaseId==383){
					disDTO.setSpecialCaseId383_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId383_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId383_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId383_4(rs.getString("input_value_4"));
				}else if(specialCaseId==338){
					disDTO.setSpecialCaseId338_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId338_2(rs.getString("input_value_2"));
				}else if(specialCaseId==371){
					disDTO.setSpecialCaseId371(rs.getString("input_value"));
				}else if(specialCaseId==339){
					disDTO.setSpecialCaseId339(rs.getString("input_value"));
				}else if(specialCaseId==447){
					disDTO.setSpecialCaseId447_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId447_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId447_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId447_4(rs.getString("input_value_4"));
				}else if(specialCaseId==438){
					disDTO.setSpecialCaseId438_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId438_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId438_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId438_4(rs.getString("input_value_4"));
				}else if(specialCaseId==392){
					disDTO.setSpecialCaseId392_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId392_2(rs.getString("input_value_2"));
				}else if(specialCaseId==393){
					disDTO.setSpecialCaseId393(rs.getString("input_value"));
				}else if(specialCaseId==406){
					disDTO.setSpecialCaseId406(rs.getString("input_value"));
				}else if(specialCaseId==435){
					disDTO.setSpecialCaseId435(rs.getString("input_value"));
				}else if(specialCaseId==436){
					disDTO.setSpecialCaseId436(rs.getString("input_value"));
				}else if(specialCaseId==437){
					disDTO.setSpecialCaseId437(rs.getString("input_value"));
				}else if(specialCaseId==446){
					disDTO.setSpecialCaseId446(rs.getString("input_value"));
				}else if(specialCaseId==451){
					disDTO.setSpecialCaseId451(rs.getString("input_value"));
				}else if(specialCaseId==452){
					disDTO.setSpecialCaseId452(rs.getString("input_value"));
				}else if(specialCaseId==395){
					disDTO.setSpecialCaseId395(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==396){
					disDTO.setSpecialCaseId396(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==397){
					disDTO.setSpecialCaseId397(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==399){
					disDTO.setSpecialCaseId399(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==402){
					disDTO.setSpecialCaseId402(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==404){
					disDTO.setSpecialCaseId404(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==405){
					disDTO.setSpecialCaseId405(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==449){
					disDTO.setSpecialCaseId449(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==450){
					disDTO.setSpecialCaseId450(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(specialCaseId==454){
					disDTO.setSpecialCaseId454_1(rs.getString("input_value"));
					disDTO.setSpecialCaseId454_2(rs.getString("input_value_2"));
					disDTO.setSpecialCaseId454_3(rs.getString("input_value_3"));
					disDTO.setSpecialCaseId454_4(rs.getString("input_value_4"));
				}				
				disDTO.patSpCaseId.add(specialCaseId);
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_others where patient_id="+userID+" and others_id in (select id from tbl_disease_others where dis_id="+diseaseID+")");
			while(rs.next()){
				int othersId=rs.getInt("others_id");
				if(othersId==1){
					disDTO.setOthsDescripId1(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(othersId==2){
					disDTO.setOthsDescripId2(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(othersId==3){
					disDTO.setOthsDescripId3(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(othersId==4){
					disDTO.setOthsDescripId4(rs.getString("input_value"));
				}else if(othersId==8){
					disDTO.setOthsDescripId8(rs.getString("input_value"));
				}else if(othersId==12){
					disDTO.setOthsDescripId12(rs.getString("input_value"));
				}else if(othersId==13){
					disDTO.setOthsDescripId13(rs.getString("input_value"));
				}else if(othersId==14){
					disDTO.setOthsDescripId14(rs.getString("input_value"));
				}else if(othersId==15){
					disDTO.setOthsDescripId15(rs.getString("input_value"));
				}else if(othersId==16){
					disDTO.setOthsDescripId16(rs.getString("input_value"));
				}else if(othersId==17){
					disDTO.setOthsDescripId17(rs.getString("input_value"));
				}else if(othersId==20){
					disDTO.setOthsDescripId20(rs.getString("input_value"));
				}else if(othersId==21){
					disDTO.setOthsDescripId21(rs.getString("input_value"));
				}else if(othersId==22){
					disDTO.setOthsDescripId22(rs.getString("input_value"));
				}else if(othersId==25){
					disDTO.setOthsDescripId25(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}
				
				disDTO.patOthersId.add(othersId);
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_palpation where patient_id="+userID+" and palpation_id in (select id from tbl_disease_palpation where dis_id="+diseaseID+")");
			while(rs.next()){
				int palpationId=rs.getInt("palpation_id");
				if(palpationId==1){
					disDTO.setHeartRatePerMin(rs.getString("input_value"));
				}else if(palpationId==4){
					disDTO.setBloodPressure(rs.getString("input_value"));
				}else if(palpationId==7){
					disDTO.setApexBeat(rs.getString("input_value"));
				}else if(palpationId==22){
					disDTO.setPalpaDescripId22_1(rs.getString("input_value"));
					disDTO.setPalpaDescripId22_2(rs.getString("input_value_2"));					
				}else if(palpationId==27){
					disDTO.setPalpaDescripId27(rs.getString("input_value"));
				}else if(palpationId==28){
					disDTO.setPalpaDescripId28(rs.getString("input_value"));
				}else if(palpationId==53){
					disDTO.setPalpaDescripId53(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==56){
					disDTO.setPalpaDescripId56(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==57){
					disDTO.setPalpaDescripId57(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==58){
					disDTO.setPalpaDescripId58_1(rs.getString("input_value"));
					disDTO.setPalpaDescripId58_2(rs.getString("input_value_2"));
				}else if(palpationId==65){
					disDTO.setPalpaDescripId65(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==66){
					disDTO.setPalpaDescripId66(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==67){
					disDTO.setPalpaDescripId67_1(rs.getString("input_value"));
					disDTO.setPalpaDescripId67_2(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==71){
					disDTO.setPalpaDescripId71(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==72){
					disDTO.setPalpaDescripId72(rs.getString("input_value"));
				}else if(palpationId==73){
					disDTO.setPalpaDescripId73(rs.getString("input_value"));
				}else if(palpationId==74){
					disDTO.setPalpaDescripId74(rs.getString("input_value"));
				}else if(palpationId==75){
					disDTO.setPalpaDescripId75(rs.getString("input_value"));
				}else if(palpationId==88){
					disDTO.setPalpaDescripId88(rs.getString("input_value"));
				}else if(palpationId==93){
					disDTO.setPalpaDescripId93(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==100){
					disDTO.setPalpaDescripId100(rs.getString("input_value"));
				}else if(palpationId==107){
					disDTO.setPalpaDescripId107(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==108){
					disDTO.setPalpaDescripId108(rs.getString("input_value"));
				}else if(palpationId==112){
					disDTO.setPalpaDescripId112(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==113){
					disDTO.setPalpaDescripId113(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(palpationId==114){
					disDTO.setPalpaDescripId114(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}
				
				disDTO.patPalpId.add(palpationId);
			}
			rs.close();
			
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_inspection where patient_id="+userID+" and inspection_id in (select id from tbl_disease_inspection where dis_id="+diseaseID+")");
			while(rs.next()){
				int inspectionId=rs.getInt("inspection_id");
				if(inspectionId==4){
					disDTO.setHeightVsPressure(rs.getString("input_value"));
				}else if(inspectionId==6){
					disDTO.setInspecDescripId6(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==7){
					disDTO.setInspecDescripId7(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==8){
					disDTO.setInspecDescripId8(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==13){
					disDTO.setInspecDescripId13(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==49){
					disDTO.setInspecDescripId49(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==50){
					disDTO.setInspecDescripId50(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==60){
					disDTO.setInspecDescripId60(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==61){
					disDTO.setInspecDescripId61(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==69){
					disDTO.setInspecDescripId69(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==72){
					disDTO.setInspecDescripId72(rs.getString("input_value"));
				}else if(inspectionId==74){
					disDTO.setInspecDescripId74(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==75){
					disDTO.setInspecDescripId75(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==77){
					disDTO.setInspecDescripId77(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==78){
					disDTO.setInspecDescripId78(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==80){
					disDTO.setInspecDescripId80(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==81){
					disDTO.setInspecDescripId81(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==84){
					disDTO.setInspecDescripId84(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==87){
					disDTO.setInspecDescripId87(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==92){
					disDTO.setInspecDescripId92(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==93){
					disDTO.setInspecDescripId93(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==96){
					disDTO.setInspecDescripId96(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==99){
					disDTO.setInspecDescripId99(rs.getString("input_value"));
				}else if(inspectionId==103){
					disDTO.setInspecDescripId103(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==111){
					disDTO.setInspecDescripId111(rs.getString("input_value"));
				}else if(inspectionId==112){
					disDTO.setInspecDescripId112(rs.getString("input_value"));
				}else if(inspectionId==116){
					disDTO.setInspecDescripId116(rs.getString("input_value"));
				}else if(inspectionId==117){
					disDTO.setInspecDescripId117(rs.getString("input_value"));
				}else if(inspectionId==118){
					disDTO.setInspecDescripId118(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==119){
					disDTO.setInspecDescripId119(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==125){
					disDTO.setInspecDescripId125(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==134){
					disDTO.setInspecDescripId134(rs.getString("input_value"));
				}else if(inspectionId==135){
					disDTO.setInspecDescripId135_1(rs.getString("input_value"));
					disDTO.setInspecDescripId135_2(rs.getString("input_value_2"));
				}else if(inspectionId==141){
					disDTO.setInspecDescripId141(rs.getString("input_value"));
				}else if(inspectionId==142){
					disDTO.setInspecDescripId142(rs.getString("input_value"));
				}else if(inspectionId==137){
					disDTO.setInspecDescripId137(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==138){
					disDTO.setInspecDescripId138(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==139){
					disDTO.setInspecDescripId139(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==140){
					disDTO.setInspecDescripId140(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==148){
					disDTO.setInspecDescripId148(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==156){
					disDTO.setInspecDescripId156(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==157){
					disDTO.setInspecDescripId157(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==158){
					disDTO.setInspecDescripId158(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(inspectionId==151){
					disDTO.setInspecDescripId151(rs.getString("input_value"));
				}else if(inspectionId==155){
					disDTO.setInspecDescripId155(rs.getString("input_value"));
				}else if(inspectionId==162){
					disDTO.setInspecDescripId162(rs.getString("input_value"));
				}else if(inspectionId==163){
					disDTO.setInspecDescripId163(rs.getString("input_value"));
				}else if(inspectionId==165){
					disDTO.setInspecDescripId165_1(rs.getString("input_value"));
					disDTO.setInspecDescripId165_2(rs.getString("input_value_2"));
				}
				disDTO.patInspId.add(inspectionId);
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_treatment where patient_id="+userID+" and treatment_id in (select id from tbl_disease_treatment where dis_id="+diseaseID+")");
			while(rs.next()){
				int treatmentId=rs.getInt("treatment_id");
				if(treatmentId==5){
					disDTO.setTreatmentId5(rs.getString("input_value"));
				}else if(treatmentId==9){
					disDTO.setTreatmentId9(rs.getString("input_value"));
				}else if(treatmentId==11){
					disDTO.setTreatmentId11(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==13){
					disDTO.setTreatmentId13(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==14){
					disDTO.setTreatmentId14(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==15){
					disDTO.setTreatmentId15(rs.getString("input_value"));
				}else if(treatmentId==18){
					disDTO.setTreatmentId18(rs.getString("input_value"));
				}else if(treatmentId==34){
					disDTO.setTreatmentId34(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==35){
					disDTO.setTreatmentId35(rs.getString("input_value"));
				}else if(treatmentId==36){
					disDTO.setTreatmentId36(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==37){
					disDTO.setTreatmentId37(rs.getString("input_value"));
				}else if(treatmentId==38){
					disDTO.setTreatmentId38(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==39){
					disDTO.setTreatmentId39(rs.getString("input_value"));
				}else if(treatmentId==40){
					disDTO.setTreatmentId40(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(treatmentId==41){
					disDTO.setTreatmentId41(rs.getString("input_value"));
				}else if(treatmentId==48){
					disDTO.setTreatmentId48(rs.getString("input_value"));
				}else if(treatmentId==61){
					disDTO.setTreatmentId61(rs.getString("input_value"));
				}else if(treatmentId==68){
					disDTO.setTreatmentId68(rs.getString("input_value"));
				}else if(treatmentId==77){
					disDTO.setTreatmentId77(rs.getString("input_value"));
				}else if(treatmentId==82){
					disDTO.setTreatmentId82(rs.getString("input_value"));
				}else if(treatmentId==83){
					disDTO.setTreatmentId83(rs.getString("input_value"));
				}else if(treatmentId==84){
					disDTO.setTreatmentId84(rs.getString("input_value"));
				}else if(treatmentId==118){
					disDTO.setTreatmentId118(rs.getString("input_value"));
				}else if(treatmentId==157){
					disDTO.setTreatmentId157(rs.getString("input_value"));
				}else if(treatmentId==167){
					disDTO.setTreatmentId167(rs.getString("input_value"));
				}
				disDTO.patTrtmntId.add(treatmentId);
			}
			rs.close();
			
			
			rs = stmt.executeQuery("select * from tbl_patient_disease_diagnosis where patient_id="+userID+" and diagnosis_id in (select id from tbl_disease_diagnosis where dis_id="+diseaseID+")");
			while(rs.next()){
				int diagnosisId=rs.getInt("diagnosis_id");
				if(diagnosisId==18){
					disDTO.setDiagnosisId18(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(diagnosisId==17){
					disDTO.setDiagnosisId17(rs.getString("input_value"));
				}else if(diagnosisId==19){
					disDTO.setDiagnosisId19(rs.getString("input_value"));
				}else if(diagnosisId==20){
					disDTO.setDiagnosisId20(rs.getString("input_value"));
				}else if(diagnosisId==23){
					disDTO.setDiagnosisId23(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(diagnosisId==24){
					disDTO.setDiagnosisId24(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(diagnosisId==47){
					disDTO.setDiagnosisId47(rs.getString("input_value"));
				}else if(diagnosisId==62){
					disDTO.setDiagnosisId62(rs.getString("input_value"));
				}else if(diagnosisId==63){
					disDTO.setDiagnosisId63(rs.getString("input_value"));
				}else if(diagnosisId==90){
					disDTO.setDiagnosisId90(rs.getString("input_value"));
				}else if(diagnosisId==75){
					disDTO.setDiagnosisId75(rs.getString("input_value"));
				}else if(diagnosisId==87){
					disDTO.setDiagnosisId87(rs.getString("input_value"));
				}else if(diagnosisId==100){
					disDTO.setDiagnosisId100(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(diagnosisId==101){
					disDTO.setDiagnosisId101(rs.getString("input_value")==null?1:Integer.parseInt(rs.getString("input_value")));
				}else if(diagnosisId==102){
					disDTO.setDiagnosisId102(rs.getString("input_value"));
				}else if(diagnosisId==103){
					disDTO.setDiagnosisId103(rs.getString("input_value"));
				}else if(diagnosisId==104){
					disDTO.setDiagnosisId104(rs.getString("input_value"));
				}else if(diagnosisId==105){
					disDTO.setDiagnosisId105(rs.getString("input_value"));
				}else if(diagnosisId==106){
					disDTO.setDiagnosisId106(rs.getString("input_value"));
				}else if(diagnosisId==107){
					disDTO.setDiagnosisId107(rs.getString("input_value"));
				}else if(diagnosisId==108){
					disDTO.setDiagnosisId108(rs.getString("input_value"));
				}else if(diagnosisId==109){
					disDTO.setDiagnosisId109(rs.getString("input_value"));
				}else if(diagnosisId==110){
					disDTO.setDiagnosisId110(rs.getString("input_value"));
				}else if(diagnosisId==111){
					disDTO.setDiagnosisId111(rs.getString("input_value"));
				}else if(diagnosisId==112){
					disDTO.setDiagnosisId112(rs.getString("input_value"));
				}else if(diagnosisId==129){
					disDTO.setDiagnosisId129(rs.getString("input_value"));
				}
				disDTO.patDiagonosisId.add(diagnosisId);
			}
			rs.close();
			
			rs = stmt.executeQuery("select * from tbl_patient_disease where patient_id="+userID+" and disease_id="+diseaseID);
			if(rs.next()){
				disDTO.setNad(rs.getInt("nad"));
			}
			rs.close();
			
			
			rs = stmt.executeQuery("select * from tbl_oral_and_maxillofacial_trauma where patient_id="+userID);
			if(rs.next()){
				disDTO.setOmLocation(rs.getString("omLocation"));
				disDTO.setOmDuration(rs.getString("omDuration"));    
				disDTO.setOmTimeframe(rs.getString("omTimeframe"));
				disDTO.setOmIntensity(rs.getString("omIntensity"));
				disDTO.setOmMechanism(rs.getString("omMechanism")); 
				disDTO.setEyeOpening(rs.getString("eyeOpening"));
				disDTO.setVerbalResponse(rs.getString("verbalResponse")); 
				disDTO.setMotorResponse(rs.getString("motorResponse"));
				disDTO.setTotalScore(rs.getString("totalScore"));
				disDTO.setComments(rs.getString("comments"));
				disDTO.setMechanismOfInjury(rs.getString("mechanismOfInjury"));
				disDTO.setReflex(rs.getString("reflex"));
				disDTO.setMotorFunction(rs.getString("motorFunction"));
				disDTO.setMentalStatus(rs.getString("mentalStatus"));
				disDTO.setRectalTone(rs.getString("rectalTone"));
				disDTO.setOmSpninalCordOther(rs.getString("omSpninalCordOther"));
			}
			rs.close();
			
			
			rs = stmt.executeQuery("select * from tbl_patient_special_notes where patient_id="+userID+" and disease_id="+diseaseID);
			if(rs.next()){
				disDTO.setSpecialNotes(rs.getString("special_notes"));
				disDTO.setComplications(rs.getString("complications"));    
			}
			rs.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}	
		return disDTO;
	}
}
