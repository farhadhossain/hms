package department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import utility.DAOResult;
import utility.MyConfig;

public class DepartmentDiseaseDAO {

	public HashMap<Integer, String> getDepartmentDiseaseListByID(int departmentID, int subDeptID) {
		HashMap<Integer, String> deptDisList = new HashMap<Integer, String>();
		String sql="select * from tbl_department_disease_info where department_id="+departmentID+" and sub_department_id="+subDeptID;
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	deptDisList.put(rs.getInt("id"), rs.getString("name"));
	        }
	        rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return deptDisList;
	}

	public DAOResult updatePatientDepartmentDiseaseInfo(DepartmentDiseaseDTO dto) {
		DAOResult daoRes = new DAOResult();
		daoRes.setMessage("Added Successfully");
		int sizeOfArray=0;
		Connection conn = null;
		Statement stmt = null; 
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			

			stmt.executeUpdate("delete from tbl_patient_department_disease_info where patient_id="+dto.getUserId()+" and disease_info_id in (select id from tbl_department_disease_info where department_id="+dto.getDepartmentId()+")");
			stmt.executeUpdate("delete from tbl_patient_department_disease_others where patient_id="+dto.getUserId()+" and department_id="+dto.getDepartmentId());
			
			
			stmt.execute("insert into tbl_patient_department_disease_others(patient_id, department_id, diagnosis, advice) values("+dto.getUserId()+", "+dto.getDepartmentId()+", '"+dto.getDiagnosis()==null?"":dto.getDiagnosis()+"', '"+dto.getAdvice()==null?"":dto.getAdvice()+"')");
			
			
			{
				try{
					sizeOfArray=dto.getDiseaseId().length;
				}catch(Exception e){sizeOfArray=0;};
				
				String tail_2="";
				for(int i=0;i<sizeOfArray;i++){
					if(dto.getDiseaseId()[i]==1){
						tail_2=", '"+dto.getDiseaseId1_1()+"', '"+dto.getDiseaseId1_2()+"', '"+dto.getDiseaseId1_3()+"', '"+dto.getDiseaseId1_4()+"'";
					}else if(dto.getDiseaseId()[i]==2){
						tail_2=", '"+dto.getDiseaseId2_1()+"', '"+dto.getDiseaseId2_2()+"', '"+dto.getDiseaseId2_3()+"', '"+dto.getDiseaseId2_4()+"'";
					}else if(dto.getDiseaseId()[i]==3){
						tail_2=", '"+dto.getDiseaseId3_1()+"', '"+dto.getDiseaseId3_2()+"', '"+dto.getDiseaseId3_3()+"', '"+dto.getDiseaseId3_4()+"'";
					}else if(dto.getDiseaseId()[i]==4){
						tail_2=", '"+dto.getDiseaseId4_1()+"', '"+dto.getDiseaseId4_2()+"', '"+dto.getDiseaseId4_3()+"', '"+dto.getDiseaseId4_4()+"'";
					}else if(dto.getDiseaseId()[i]==5){
						tail_2=", '"+dto.getDiseaseId5_1()+"', '"+dto.getDiseaseId5_2()+"', '"+dto.getDiseaseId5_3()+"', '"+dto.getDiseaseId5_4()+"'";
					}else if(dto.getDiseaseId()[i]==6){
						tail_2=", '"+dto.getDiseaseId6_1()+"', '"+dto.getDiseaseId6_2()+"', '"+dto.getDiseaseId6_3()+"', '"+dto.getDiseaseId6_4()+"'";
					}else if(dto.getDiseaseId()[i]==7){
						tail_2=", '"+dto.getDiseaseId7_1()+"', '"+dto.getDiseaseId7_2()+"', '"+dto.getDiseaseId7_3()+"', '"+dto.getDiseaseId7_4()+"'";
					}else if(dto.getDiseaseId()[i]==8){
						tail_2=", '"+dto.getDiseaseId8_1()+"', '"+dto.getDiseaseId8_2()+"', '"+dto.getDiseaseId8_3()+"', '"+dto.getDiseaseId8_4()+"'";
					}else if(dto.getDiseaseId()[i]==9){
						tail_2=", '"+dto.getDiseaseId9_1()+"', '"+dto.getDiseaseId9_2()+"', '"+dto.getDiseaseId9_3()+"', '"+dto.getDiseaseId9_4()+"'";
					}else if(dto.getDiseaseId()[i]==10){
						tail_2=", '"+dto.getDiseaseId10_1()+"', '"+dto.getDiseaseId10_2()+"', '"+dto.getDiseaseId10_3()+"', '"+dto.getDiseaseId10_4()+"'";
					}else if(dto.getDiseaseId()[i]==11){
						tail_2=", '"+dto.getDiseaseId11_1()+"', '"+dto.getDiseaseId11_2()+"', '"+dto.getDiseaseId11_3()+"', '"+dto.getDiseaseId11_4()+"'";
					}else if(dto.getDiseaseId()[i]==12){
						tail_2=", '"+dto.getDiseaseId12_1()+"', '"+dto.getDiseaseId12_2()+"', '"+dto.getDiseaseId12_3()+"', '"+dto.getDiseaseId12_4()+"'";
					}else if(dto.getDiseaseId()[i]==13){
						tail_2=", '"+dto.getDiseaseId13_1()+"', '"+dto.getDiseaseId13_2()+"', '"+dto.getDiseaseId13_3()+"', '"+dto.getDiseaseId13_4()+"'";
					}else if(dto.getDiseaseId()[i]==14){
						tail_2=", '"+dto.getDiseaseId14_1()+"', '"+dto.getDiseaseId14_2()+"', '"+dto.getDiseaseId14_3()+"', '"+dto.getDiseaseId14_4()+"'";
					}else if(dto.getDiseaseId()[i]==15){
						tail_2=", '"+dto.getDiseaseId15_1()+"', '"+dto.getDiseaseId15_2()+"', '"+dto.getDiseaseId15_3()+"', '"+dto.getDiseaseId15_4()+"'";
					}else if(dto.getDiseaseId()[i]==16){
						tail_2=", '"+dto.getDiseaseId16_1()+"', '"+dto.getDiseaseId16_2()+"', '"+dto.getDiseaseId16_3()+"', '"+dto.getDiseaseId16_4()+"'";
					}else if(dto.getDiseaseId()[i]==17){
						tail_2=", '"+dto.getDiseaseId17_1()+"', '"+dto.getDiseaseId17_2()+"', '"+dto.getDiseaseId17_3()+"', '"+dto.getDiseaseId17_4()+"'";
					}else if(dto.getDiseaseId()[i]==18){
						tail_2=", '"+dto.getDiseaseId18_1()+"', '"+dto.getDiseaseId18_2()+"', '"+dto.getDiseaseId18_3()+"', '"+dto.getDiseaseId18_4()+"'";
					}else if(dto.getDiseaseId()[i]==19){
						tail_2=", '"+dto.getDiseaseId19_1()+"', '"+dto.getDiseaseId19_2()+"', '"+dto.getDiseaseId19_3()+"', '"+dto.getDiseaseId19_4()+"'";
					}else if(dto.getDiseaseId()[i]==20){
						tail_2=", '"+dto.getDiseaseId20_1()+"', '"+dto.getDiseaseId20_2()+"', '"+dto.getDiseaseId20_3()+"', '"+dto.getDiseaseId20_4()+"'";
					}else if(dto.getDiseaseId()[i]==21){
						tail_2=", '"+dto.getDiseaseId21_1()+"', '"+dto.getDiseaseId21_2()+"', '"+dto.getDiseaseId21_3()+"', '"+dto.getDiseaseId21_4()+"'";
					}else if(dto.getDiseaseId()[i]==22){
						tail_2=", '"+dto.getDiseaseId22_1()+"', '"+dto.getDiseaseId22_2()+"', '"+dto.getDiseaseId22_3()+"', '"+dto.getDiseaseId22_4()+"'";
					}
					stmt.execute("insert into tbl_patient_department_disease_info(patient_id, disease_info_id, input_value, input_value_2, input_value_3, input_value_4) values("+dto.getUserId()+", "+dto.getDiseaseId()[i]+tail_2+")");
					tail_2="";
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

	public DepartmentDiseaseDTO getPatientDepartmentDiseaseInfo(int accountID, int departmentID) {
		DepartmentDiseaseDTO dto = new DepartmentDiseaseDTO();
		Connection conn = null;
		Statement stmt = null; 
		dto.patCurDepartDisList = new HashSet<Integer>();
		try{
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			stmt = conn.createStatement();
			String sql="select * from tbl_patient_department_disease_info where patient_id="+accountID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				dto.patCurDepartDisList.add(rs.getInt("disease_info_id"));
				
				if(rs.getInt("disease_info_id")==1){
					dto.setDiseaseId1_1(rs.getString("input_value")); dto.setDiseaseId1_2(rs.getString("input_value_2"));
					dto.setDiseaseId1_3(rs.getString("input_value_3")); dto.setDiseaseId1_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==2){
					dto.setDiseaseId2_1(rs.getString("input_value")); dto.setDiseaseId2_2(rs.getString("input_value_2"));
					dto.setDiseaseId2_3(rs.getString("input_value_3")); dto.setDiseaseId2_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==3){
					dto.setDiseaseId3_1(rs.getString("input_value")); dto.setDiseaseId3_2(rs.getString("input_value_2"));
					dto.setDiseaseId3_3(rs.getString("input_value_3")); dto.setDiseaseId3_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==4){
					dto.setDiseaseId4_1(rs.getString("input_value")); dto.setDiseaseId4_2(rs.getString("input_value_2"));
					dto.setDiseaseId4_3(rs.getString("input_value_3")); dto.setDiseaseId4_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==5){
					dto.setDiseaseId5_1(rs.getString("input_value")); dto.setDiseaseId5_2(rs.getString("input_value_2"));
					dto.setDiseaseId5_3(rs.getString("input_value_3")); dto.setDiseaseId5_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==6){
					dto.setDiseaseId6_1(rs.getString("input_value")); dto.setDiseaseId6_2(rs.getString("input_value_2"));
					dto.setDiseaseId6_3(rs.getString("input_value_3")); dto.setDiseaseId6_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==7){
					dto.setDiseaseId7_1(rs.getString("input_value")); dto.setDiseaseId7_2(rs.getString("input_value_2"));
					dto.setDiseaseId7_3(rs.getString("input_value_3")); dto.setDiseaseId7_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==8){
					dto.setDiseaseId8_1(rs.getString("input_value")); dto.setDiseaseId8_2(rs.getString("input_value_2"));
					dto.setDiseaseId8_3(rs.getString("input_value_3")); dto.setDiseaseId8_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==9){
					dto.setDiseaseId9_1(rs.getString("input_value")); dto.setDiseaseId9_2(rs.getString("input_value_2"));
					dto.setDiseaseId9_3(rs.getString("input_value_3")); dto.setDiseaseId9_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==10){
					dto.setDiseaseId10_1(rs.getString("input_value")); dto.setDiseaseId10_2(rs.getString("input_value_2"));
					dto.setDiseaseId10_3(rs.getString("input_value_3")); dto.setDiseaseId10_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==11){
					dto.setDiseaseId11_1(rs.getString("input_value")); dto.setDiseaseId11_2(rs.getString("input_value_2"));
					dto.setDiseaseId11_3(rs.getString("input_value_3")); dto.setDiseaseId11_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==12){
					dto.setDiseaseId12_1(rs.getString("input_value")); dto.setDiseaseId12_2(rs.getString("input_value_2"));
					dto.setDiseaseId12_3(rs.getString("input_value_3")); dto.setDiseaseId12_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==13){
					dto.setDiseaseId13_1(rs.getString("input_value")); dto.setDiseaseId13_2(rs.getString("input_value_2"));
					dto.setDiseaseId13_3(rs.getString("input_value_3")); dto.setDiseaseId13_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==14){
					dto.setDiseaseId14_1(rs.getString("input_value")); dto.setDiseaseId14_2(rs.getString("input_value_2"));
					dto.setDiseaseId14_3(rs.getString("input_value_3")); dto.setDiseaseId14_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==15){
					dto.setDiseaseId15_1(rs.getString("input_value")); dto.setDiseaseId15_2(rs.getString("input_value_2"));
					dto.setDiseaseId15_3(rs.getString("input_value_3")); dto.setDiseaseId15_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==16){
					dto.setDiseaseId16_1(rs.getString("input_value")); dto.setDiseaseId16_2(rs.getString("input_value_2"));
					dto.setDiseaseId16_3(rs.getString("input_value_3")); dto.setDiseaseId16_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==17){
					dto.setDiseaseId17_1(rs.getString("input_value")); dto.setDiseaseId17_2(rs.getString("input_value_2"));
					dto.setDiseaseId17_3(rs.getString("input_value_3")); dto.setDiseaseId17_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==18){
					dto.setDiseaseId18_1(rs.getString("input_value")); dto.setDiseaseId18_2(rs.getString("input_value_2"));
					dto.setDiseaseId18_3(rs.getString("input_value_3")); dto.setDiseaseId18_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==19){
					dto.setDiseaseId19_1(rs.getString("input_value")); dto.setDiseaseId19_2(rs.getString("input_value_2"));
					dto.setDiseaseId19_3(rs.getString("input_value_3")); dto.setDiseaseId19_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==20){
					dto.setDiseaseId20_1(rs.getString("input_value")); dto.setDiseaseId20_2(rs.getString("input_value_2"));
					dto.setDiseaseId20_3(rs.getString("input_value_3")); dto.setDiseaseId20_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==21){
					dto.setDiseaseId21_1(rs.getString("input_value")); dto.setDiseaseId21_2(rs.getString("input_value_2"));
					dto.setDiseaseId21_3(rs.getString("input_value_3")); dto.setDiseaseId21_4(rs.getString("input_value_4"));
				}else if(rs.getInt("disease_info_id")==22){
					dto.setDiseaseId22_1(rs.getString("input_value")); dto.setDiseaseId22_2(rs.getString("input_value_2"));
					dto.setDiseaseId22_3(rs.getString("input_value_3")); dto.setDiseaseId22_4(rs.getString("input_value_4"));
				}
				
			}
			rs.close();
			
			sql="select * from tbl_patient_department_disease_others where patient_id="+accountID+" and department_id="+departmentID;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				dto.setAdvice(rs.getString("advice"));
				dto.setDiagnosis(rs.getString("diagnosis"));
			}
			rs.close();
			
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try{stmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}
		return dto;
	}

}
