package prescription;


import utility.DAOResult;
import utility.MyConfig;
import utility.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * Created by macintosh on 11/28/15.
 */
public class PrescriptionDAO {

    public DAOResult savePrescription(PrescriptionDTO dto, int userID) {

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs = null;

        DAOResult daoResult = new DAOResult();
        daoResult.setValid(true);
        //daoResult.setMessage("New Patient '"+dto.getName()+"' added Successfully");

        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();

            System.out.println("*********************** From Save Prescription with user id =  "+ MyConfig.userID+" & role id = "+ MyConfig.roleID+"*********************************");

            if(dto.getId()>0){
                stmt.execute("DELETE from prescription where id = "+dto.getId());
            }
            String nextVisitDate="NULL";
            try {
                nextVisitDate = "'"+new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("MM/dd/yyyy").parse(dto.getNextVisitDate()))+"'";
            }catch(Exception t){}
            String sql="insert into prescription(nextVisitDate,referTo,ho, chiefComplain, onObservation, investigation, diagonosis,advice, patientId, visitId) " +
                    "values("+nextVisitDate+",'"+dto.getReferTo()+"','"+dto.getHo()+"','"+dto.getChiefComplain()+"', '"+dto.getOnObservation()+"', '"+dto.getInvestigation()+"','"+dto.getDiagonosis()+"', '"+dto.getAdvice()+"' , "+dto.getPatientID()+",(select max(id) from visit where patient_id="+dto.getPatientID()+"))";
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
                sql="insert into prescription_medicine(medicineName, medicineType, frequency, dose, comment, afterMeal, prescriptionId, doseUnit, totalNumber, duration) " +
                        "values('"+med.getMedicineName()+"','"+med.getMedicineType()+"','"+med.getFrequency()+"','"+med.getDose()+"','"+med.getComment()+"','"+med.getAfterMeal()+"',"+autoIncKeyFromApi+",'"+med.getDoseUnit()+"','"+med.getTotalNumber()+"','"+med.getDuration()+"')";
                stmt.execute(sql);
            }

            if(MyConfig.roleID != MyConfig.SurgeonRole && MyConfig.roleID != MyConfig.dutyNurseRole) {
                sql = "delete FROM tbl_logbook where userId=" + MyConfig.userID + " and patientId=" + dto.getPatientID() + " and roleId=" + MyConfig.roleID;
                System.out.println(sql);
                stmt.executeUpdate(sql);

                String upDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                sql = "insert into tbl_logbook(userId, patientId, updateDate, roleId) " + "values('" + MyConfig.userID + "','" + dto.getPatientID() + "','" + upDate + "','" + MyConfig.roleID + "')";
                System.out.println(sql);
                stmt.execute(sql);
            }

        }catch(Exception e){
            daoResult.setValid(false);
            e.printStackTrace();
            daoResult.setMessage("Database Error: "+e.toString());
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
            try {
                rs.close();
            }catch(Exception e){}
        }
        return daoResult;
    }

    public PrescriptionDTO getPrescriptionByPatientId(int patientId, int visitId) {
        Connection conn=null;
        Statement stmt=null;
        PrescriptionDTO dto = new PrescriptionDTO();
        String visit = visitId==0?"(select max(id) from visit where patient_id="+patientId+")":visitId+"";
        String sql="SELECT * FROM prescription where patientId="+patientId +" and visitId="+visit;
        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();

            System.out.println(sql);

            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                dto.setId(rs.getInt("id"));
                dto.setHo(rs.getString("ho"));
                dto.setInvestigation(rs.getString("investigation"));
                dto.setOnObservation(rs.getString("onObservation"));
                System.out.println("*********************** O/E = "+dto.getOnObservation()+" *********************************");
                dto.setDiagonosis(rs.getString("diagonosis"));
                System.out.println("*********************** diagnosis = "+dto.getDiagonosis()+" *********************************");
                dto.setAdvice(rs.getString("advice"));
                dto.setChiefComplain(rs.getString("chiefComplain"));
                dto.setPatientID(rs.getInt("patientId"));
                try {
                    dto.setNextVisitDate(new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("nextVisitDate")));
                }catch(Exception r){}
                dto.setReferTo(rs.getString("referTo"));
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
                medicineDTO.setDoseUnit(rs.getString("doseUnit"));
                medicineDTO.setPrescriptionId(rs.getInt("prescriptionId"));
                medicineDTO.setTotalNumber(rs.getString("totalNumber"));
                medicineDTO.setDuration(rs.getString("duration"));
                StringUtil.removeNullFromObject(medicineDTO);
                dto.getMedicines().add(medicineDTO);
            }
            rs=stmt.executeQuery("select name from tbl_patient where id="+patientId);
            if (rs.next())
               dto.patientName = rs.getString(1);
            StringUtil.removeNullFromObject(dto);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return dto;
    }


}
