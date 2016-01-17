package medicine;

import patient.PatientDTO;
import utility.MyConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macintosh on 11/25/15.
 */
public class MedicineDAO {

    public List<MedicineDTO> searchMedicine(String keyword) {
        Connection conn=null;
        Statement stmt=null;
        Statement stmt2=null;
        List<MedicineDTO> medicineDTOs = new ArrayList<MedicineDTO>();
        String sql="SELECT * from medicine_list WHERE medicineName like '%"+keyword+"%'";
        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            sql+=" order by medicineName asc";

            System.out.println(sql);

            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                MedicineDTO dto = new MedicineDTO();

                dto.setMedicineID(rs.getInt("medicineID"));
                dto.setMedicineName(rs.getString("medicineName"));

	        	/*dto.diseaseTypeHash = new HashSet<Integer>();
	        	if(dto.getDeptId()==MyConfig.deptIndoor){
		        	sql="select * from tbl_patient_disease where patient_id="+dto.getAccId();
		        	System.out.println(sql);
		        	ResultSet rsInn = stmt2.executeQuery(sql);
		        	while(rsInn.next()){
		        		dto.diseaseTypeHash.add(rsInn.getInt("disease_id"));
		        	}
		        	rsInn.close();
	        	}*/

                medicineDTOs.add(dto);
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{stmt2.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return medicineDTOs;
    }
}
