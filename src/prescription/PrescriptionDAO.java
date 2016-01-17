package prescription;

import org.apache.commons.lang.RandomStringUtils;
import patient.PatientDTO;
import utility.DAOResult;
import utility.MyConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Created by macintosh on 11/28/15.
 */
public class PrescriptionDAO {

    public DAOResult savePrescription(PrescriptionDTO dto, int userID) {

        Connection conn=null;
        Statement stmt=null;

        DAOResult daoResult = new DAOResult();
        daoResult.setValid(true);
        //daoResult.setMessage("New Patient '"+dto.getName()+"' added Successfully");


        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();

            if(dto.getId()>0){
                stmt.execute("DELETE from prescription where id = "+dto.getId());
            }

            String sql="insert into prescription(chiefComplain, onObservation, investigation, diagonosis,advice, patientId) " +
                    "values('"+dto.getChiefComplain()+"', '"+dto.getOnObservation()+"', '"+dto.getInvestigation()+"','"+dto.getDiagonosis()+"', '"+dto.getAdvice()+"' , "+dto.getPatientID()+")";
            System.out.println(sql);
            PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.execute(sql);

            int autoIncKeyFromApi = -1;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                autoIncKeyFromApi = generatedKeys.getInt(1);
            }
            daoResult.setObjectId(autoIncKeyFromApi);

            for(PrescriptionMedicineDTO med: dto.getMedicines()){
                sql="insert into prescription_medicine(medicineName, medicineType, frequency, dose, comment, afterMeal, prescriptionId) " +
                        "values('"+med.getMedicineName()+"','"+med.getMedicineType()+"','"+med.getFrequency()+"','"+med.getDose()+"','"+med.getComment()+"','"+med.getAfterMeal()+"',"+autoIncKeyFromApi+")";
                stmt.execute(sql);
            }

        }catch(Exception e){
            daoResult.setValid(false);
            e.printStackTrace();
            daoResult.setMessage("Database Error: "+e.toString());
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return daoResult;
    }

    public PrescriptionDTO getPrescriptionByPatientId(int patientId) {
        Connection conn=null;
        Statement stmt=null;
        PrescriptionDTO dto = new PrescriptionDTO();
        String sql="SELECT * FROM prescription where patientId="+patientId;
        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();

            System.out.println(sql);

            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                dto.setId(rs.getInt("id"));
                dto.setInvestigation(rs.getString("investigation"));
                dto.setOnObservation(rs.getString("onObservation"));
                dto.setDiagonosis(rs.getString("diagonosis"));
                dto.setAdvice(rs.getString("advice"));
                dto.setChiefComplain(rs.getString("chiefComplain"));
                dto.setPatientID(rs.getInt("patientId"));
            }
            rs.close();

            rs=stmt.executeQuery("select * from prescription_medicine where prescriptionId = " + dto.getId());
            while(rs.next()){
                PrescriptionMedicineDTO medicineDTO= new PrescriptionMedicineDTO();
                medicineDTO.setMedicineName(rs.getString("medicineName"));
                medicineDTO.setAfterMeal(rs.getString("afterMeal"));
                medicineDTO.setComment(rs.getString("comment"));
                medicineDTO.setMedicineType(rs.getString("medicineType"));
                medicineDTO.setFrequency(rs.getString("frequency"));
                medicineDTO.setDose(rs.getString("dose"));
                medicineDTO.setPrescriptionId(rs.getInt("prescriptionId"));
                dto.getMedicines().add(medicineDTO);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return dto;
    }


}
