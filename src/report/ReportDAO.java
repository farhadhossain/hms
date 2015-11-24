package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import utility.MyConfig;

public class ReportDAO {

	public ArrayList<ReportDTO> DiseaseSpecificReport(String diseaseID, String[] diagnosisId, int typeID){
		ArrayList<ReportDTO> dtoList = new ArrayList<ReportDTO>();
		
		Connection conn = null;
		Statement stmt = null; 
		
		String sql="select count(*), MONTHNAME(STR_TO_DATE(MONTH(date_of_rec), '%m')), YEAR(date_of_rec) from tbl_patient";
		sql+=" where id in (select distinct patient_id from tbl_patient_disease where disease_id="+diseaseID+")";
		
		if(diagnosisId!=null && diagnosisId.length>0){
			String idStr = "";
			for(int i=0;i<diagnosisId.length;i++){
				idStr+=diagnosisId[i]+",";
			}
			idStr = idStr.substring(0, idStr.length()-1);
			
			if(typeID==MyConfig.diseaseDiagnosis){
				sql+=" and id in (select distinct patient_id from tbl_patient_disease_diagnosis where diagnosis_id in ("+idStr+"))";
			}else if(typeID==MyConfig.diseaseTreatmentPlan){
				sql+=" and id in (select distinct patient_id from tbl_patient_disease_treatment where treatment_id in ("+idStr+"))";
			}
		}
		
		sql+="group by YEAR(date_of_rec), MONTH(date_of_rec)";
		
		System.out.println(sql);
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			System.out.println(sql);
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	ReportDTO dto = new ReportDTO();
	        	dto.totalPatient=rs.getInt(1);
	        	dto.nameOfMonth=rs.getString(2);
	        	dto.nameOfYear=rs.getString(3);
	        	dtoList.add(dto);
	        }
	        rs.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dtoList;
	}
	
	public ReportDTO HospitalPatientStatistics(String startDate, String endDate, int dateType, String sex, int fromAge, int toAge) {
		ReportDTO dto = new ReportDTO();
		
		Connection conn = null;
		Statement stmt = null; 
		
		Calendar clDate = Calendar.getInstance();
		String sql="select count(*), current_status from tbl_patient";
		if(dateType==1){
			sql+=" where date_of_rec>DATE_SUB(NOW(), INTERVAL 1 HOUR)";
		}else if(dateType==2){
			sql+=" where date_of_rec>='"+clDate.get(Calendar.YEAR)+"-"+(clDate.get(Calendar.MONTH)+1)+"-"+(clDate.get(Calendar.DAY_OF_MONTH))+" 00:00:00'";
			sql+=" and date_of_rec<='"+clDate.get(Calendar.YEAR)+"-"+(clDate.get(Calendar.MONTH)+1)+"-"+(clDate.get(Calendar.DAY_OF_MONTH))+" 23:59:59'";
		}else{
			String[] str1=startDate.split("-");
			String[] str2=endDate.split("-");
			sql+=" where date_of_rec>='"+str1[2]+"-"+str1[1]+"-"+str1[0]+" 00:00:00'";
			sql+=" and date_of_rec<='"+str2[2]+"-"+str2[1]+"-"+str2[0]+" 23:59:59'";
		}
		
		if(sex!=null && sex.length()>0){
			sql+=" and sex='%"+sex+"%'";
		}
		sql+=" and age>="+fromAge+" and age<="+toAge;
		
		sql+=" group by current_status";
		
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			System.out.println(sql);
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	if(rs.getInt("current_status")==1){
	        		dto.totalPatientoutdoor=rs.getInt(1);
	        	}else if(rs.getInt("current_status")==2){
	        		dto.totalPatientMinorOT=rs.getInt(1);
	        	}else if(rs.getInt("current_status")==3){
	        		dto.totalPatientIndoor=rs.getInt(1);
	        	}else if(rs.getInt("current_status")==4){
	        		dto.totalPatientEmergency=rs.getInt(1);
	        	} 
	        	dto.totalPatient+=rs.getInt(1);
	        }
	        rs.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		
		return dto;
	}

}
