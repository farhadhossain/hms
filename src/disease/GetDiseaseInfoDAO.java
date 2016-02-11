package disease;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;

import disease.form.DiseaseMetaData;
import utility.DAOResult;
import utility.MyConfig;

public class GetDiseaseInfoDAO {
	public static void main(String args[]){
		new GetDiseaseInfoDAO().getParentByChildWithDisIDAndDisType(20, MyConfig.diseaseHistory);
	}
	public HashMap<Integer, String> getParentByChildWithDisIDAndDisType(int diseaseId, int diseaseInfoType){
		String sql=null;
		Connection conn = null;
		Statement stmt = null; 
		
		HashMap<Integer, String> parentByChild = new HashMap<Integer, String>();
		//LinkedHashMap<Integer, String> parentByChild = new LinkedHashMap<Integer, String>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			if(diseaseInfoType==MyConfig.diseaseHistory){
				sql="select * from tbl_disease_history where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseaseInspection){
				sql="select * from tbl_disease_inspection where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseasePalpation){
				sql="select * from tbl_disease_palpation where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseaseSymptom){
				sql="select * from tbl_disease_symptom where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseaseAuscultation){
				sql="select * from tbl_disease_auscultation where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseasePerticipatingFactor){
				sql="select * from tbl_disease_perticipating_factor where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseaseOthers){
				sql="select * from tbl_disease_others where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseaseTreatmentPlan){
				sql="select * from tbl_disease_treatment where dis_id="+diseaseId;
			}else if(diseaseInfoType==MyConfig.diseaseDiagnosis){
				sql="select * from tbl_disease_diagnosis where dis_id="+diseaseId;
			}else if(diseaseInfoType>=MyConfig.infoHistoryOfBlood ){
				sql="select * from tbl_personal_info_history where info_id="+diseaseId;
			}
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql+" order by parent_id,id asc");
	        
	        while(rs.next()){
	        	if(rs.getInt("parent_id")>0){
	        		String str=parentByChild.get(rs.getInt("parent_id"));
	        		if(str==null){
	        			parentByChild.put(rs.getInt("parent_id"), ""+rs.getInt("id"));
	        		}else{
	        			parentByChild.put(rs.getInt("parent_id"), str+","+rs.getInt("id"));
	        		}
	        	}else{
	        		parentByChild.put(rs.getInt("id"), null);
	        	}
	        }
	        rs.close();
	        
	        /*for(int key: parentByChild.keySet()){
	        	System.out.println("key: "+key+", child: "+parentByChild.get(key));
	        }*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return parentByChild;
	}	
	
	public HashMap<Integer, String> getSysDiseaseInfo(int typeID) {
		HashMap<Integer, String> diseaseList = new HashMap<Integer, String>();
		String sql=null;
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			sql="select * from tbl_disease";
			if(typeID>0){
				sql+=" where disease_type="+typeID;
			}
	        stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        while(rs.next()){
	        	if(rs.getInt("id")>=7 && rs.getInt("id")<=10){
	        		continue;
	        	}
	        	if(rs.getInt("id")>=12 && rs.getInt("id")<=14){
	        		continue;
	        	}
	        	diseaseList.put(rs.getInt("id"), rs.getString("disease_name"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return diseaseList;
	}

	public HashMap<Integer, String> getDiseaseDetailsByLevel(int diseaseId, int diseaseInfoType, int level) {
		HashMap<Integer, String> diseaseListByLevel = new HashMap<Integer, String>();
		String tableName="tbl_disease_history", sql="", columName="name_of_perticular";
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			
			if(diseaseInfoType==MyConfig.diseaseInspection){
				tableName="tbl_disease_inspection"; columName="name_of_inspection";
			}else if(diseaseInfoType==MyConfig.diseasePalpation){
				tableName="tbl_disease_palpation"; columName="name_of_palpation";
			}else if(diseaseInfoType==MyConfig.diseaseSymptom){
				tableName="tbl_disease_symptom"; columName="name_of_symptom";
			}else if(diseaseInfoType==MyConfig.diseaseAuscultation){
				tableName="tbl_disease_auscultation"; columName="name_of_auscultation";
			}else if(diseaseInfoType==MyConfig.diseasePerticipatingFactor){
				tableName="tbl_disease_perticipating_factor"; columName="perticipating_factor";
			}else if(diseaseInfoType==MyConfig.diseaseOthers){
				tableName="tbl_disease_others"; columName="name_of_perticular";
			}else if(diseaseInfoType==MyConfig.diseaseTreatmentPlan){
				tableName="tbl_disease_treatment"; columName="name_of_treatment";
			}else if(diseaseInfoType==MyConfig.diseaseDiagnosis){
				tableName="tbl_disease_diagnosis"; columName="name_of_diagnosis";
			}
			
			String tail="";
			if(level==0){
				tail+=" and parent_id=0";
			}else if(level==1){
				tail+=" and parent_id in (select id from "+tableName+" where parent_id=0)";
			}else if(level==2){
				tail+=" and parent_id in ( select id from "+tableName+" where parent_id in (select id from "+tableName+" where parent_id=0) )";
			}else if(level==3){
				tail+=" and parent_id in ( select id from "+tableName+" where parent_id in ( select id from "+tableName+" where parent_id in (select id from "+tableName+" where parent_id=0) ) )";
			}
			sql="select * from "+tableName+" where dis_id="+diseaseId+tail;
			System.out.println(sql);
			
	        stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	diseaseListByLevel.put(rs.getInt("id"), rs.getString(columName));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return diseaseListByLevel;
	}
	
	public HashMap<Integer, DiseaseMetaData> getDiseaseDetailsByDisIDAndDisType(int diseaseId, int diseaseInfoType) {
		HashMap<Integer, DiseaseMetaData> diseaseDetl = new HashMap<Integer, DiseaseMetaData>();
		String sql="select * from tbl_disease_history where dis_id="+diseaseId;
		String columName="name_of_perticular";
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			
			if(diseaseInfoType==MyConfig.diseaseInspection){
				sql="select * from tbl_disease_inspection where dis_id="+diseaseId;
				columName="name_of_inspection";
			}else if(diseaseInfoType==MyConfig.diseasePalpation){
				sql="select * from tbl_disease_palpation where dis_id="+diseaseId;
				columName="name_of_palpation";
			}else if(diseaseInfoType==MyConfig.diseaseSymptom){
				sql="select * from tbl_disease_symptom where dis_id="+diseaseId;
				columName="name_of_symptom";
			}else if(diseaseInfoType==MyConfig.diseaseAuscultation){
				sql="select * from tbl_disease_auscultation where dis_id="+diseaseId;
				columName="name_of_auscultation";
			}else if(diseaseInfoType==MyConfig.diseasePerticipatingFactor){
				sql="select * from tbl_disease_perticipating_factor where dis_id="+diseaseId;
				columName="perticipating_factor";
			}else if(diseaseInfoType==MyConfig.diseaseOthers){
				sql="select * from tbl_disease_others where dis_id="+diseaseId;
				columName="name_of_perticular";
			}else if(diseaseInfoType==MyConfig.diseaseTreatmentPlan){
				sql="select * from tbl_disease_treatment where dis_id="+diseaseId;
				columName="name_of_treatment";
			}else if(diseaseInfoType==MyConfig.diseaseDiagnosis){
				sql="select * from tbl_disease_diagnosis where dis_id="+diseaseId;
				columName="name_of_diagnosis";
			}
	        stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql+" order by id asc");
	        
	        while(rs.next()){
				DiseaseMetaData diseaseMetaData = new DiseaseMetaData();
				diseaseMetaData.setName(rs.getString(columName));
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



	public HashMap<Integer, String> getSpecialCaseIdNameList(int diseaseId) {
		HashMap<Integer, String> diseaseSpecialCaseList = new HashMap<Integer, String>();
		String sql="select * from tbl_disease_special_case where dis_id="+diseaseId;
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	diseaseSpecialCaseList.put(rs.getInt(1), rs.getString(3));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return diseaseSpecialCaseList;
	}
	
	public HashMap<Integer, DiseaseMetaData> getSpCaseDetailsByDisIDAndCaseID(int diseaseId, int specialCaseId) {
		HashMap<Integer, DiseaseMetaData> diseaseSpecialCaseList = new HashMap<Integer, DiseaseMetaData>();
		String sql="select * from tbl_disease_special_case_details where dis_id="+diseaseId+" and sp_case_id="+specialCaseId;
		
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
				DiseaseMetaData diseaseMetaData = new DiseaseMetaData();
				diseaseMetaData.setName(rs.getString("name_of_case_details"));
				diseaseMetaData.setInputType(DAOResult.hasColumn(rs, "input_type")? rs.getString("input_type"): null);
				diseaseSpecialCaseList.put(rs.getInt("id"), diseaseMetaData);
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return diseaseSpecialCaseList;
	}
	
	public HashMap<Integer, String> getParentByChildWithSpCaseDetailsByDisIDAndCaseDetailsID(int diseaseId, int specialCaseId){
		String sql="select * from tbl_disease_special_case_details where dis_id="+diseaseId+" and sp_case_id="+specialCaseId;
		Connection conn = null;
		Statement stmt = null; 
		
		HashMap<Integer, String> parentByChild = new HashMap<Integer, String>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			
	        stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	if(rs.getInt("parent_id")>0){
	        		String str=parentByChild.get(rs.getInt("parent_id"));
	        		if(str==null){
	        			parentByChild.put(rs.getInt("parent_id"), ""+rs.getInt("id"));
	        		}else{
	        			parentByChild.put(rs.getInt("parent_id"), str+","+rs.getInt("id"));
	        		}
	        	}
	        }
	        rs.close();
			for(int key: parentByChild.keySet()){
				System.out.println("key: "+key+", child: "+parentByChild.get(key));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return parentByChild;
	}

	public boolean getIsThisSpecialIdsChildAssigned(int userId, int diseaseId, int specialCaseId) {
		boolean isAssigned=false;
		Connection conn = null;
		Statement stmt = null; 
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			
			String sql="select * from tbl_patient_disease_special_case_details where patient_id="+userId+" and sp_case_id in (select id from tbl_disease_special_case_details where sp_case_id="+specialCaseId+" and dis_id="+diseaseId+")";
			
	        stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        if(rs.next()){
	        	isAssigned=true;
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return isAssigned;
	}	
}
