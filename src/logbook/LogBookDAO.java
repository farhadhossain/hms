package logbook;

import patient.PatientSurgeryDAO;
import patient.PatientSurgeryDTO;
import user.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * Created by User on 4/25/2016.
 */
public class LogBookDAO {

   public Map<Integer, String> getPatientListsByRoleId(int userID, String startDate, String endDate, int roleID){
        Map<Integer, String> patientLists =  new java.util.HashMap<Integer, String>();
        if( startDate == null  || endDate==null ){
            return patientLists;
        }
        Connection conn=null;
        Statement stmt=null;
        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();
            String sql = "";
            sql = "select patientId, updateDate from tbl_logbook where userId="+userID+" and roleId="+ roleID+
                    " and DATE(updateDate)>= '"+startDate+"' and DATE(updateDate)<= '"+endDate+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println("----------patient id = "+rs.getInt(1)+" ------------");
                patientLists.put(rs.getInt(1), rs.getString(2));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Problems----"+e.toString());
        }finally {
            try {
                conn.close();
                stmt.close();
            }catch (Exception e){}
        }
        return patientLists;
    }
    public MajorOtDTO majorOtPatientInformationById(int patientId){
        MajorOtDTO dto = new MajorOtDTO();
        PatientSurgeryDAO surgeryDAO = new PatientSurgeryDAO();
        PatientSurgeryDTO surgeryDTO = new PatientSurgeryDTO();
        String surgeons = "", assSurgeons = "", annesthesists = "";

        Connection conn=null;
        Statement stmt=null;
        try {
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();

            surgeryDTO = surgeryDAO.getSurgeryByPatientID(patientId);
            dto.setOperationName(surgeryDTO.getNameOfOp());
            dto.setOperationDate(surgeryDTO.getDateOfOp());

            for(int i = 0; i < surgeryDTO.getSurgeonList().size(); i++){
                UserDTO userDTO = surgeryDTO.getSurgeonList().get(i);
                surgeons += userDTO.getEmployeeName()+", ";
            }

            for(int i = 0; i < surgeryDTO.getAssistSurgList().size(); i++){
                UserDTO userDTO = surgeryDTO.getAssistSurgList().get(i);
                assSurgeons += userDTO.getEmployeeName()+", ";
            }

            for(int i = 0; i < surgeryDTO.getAnesthetistList().size(); i++){
                UserDTO userDTO = surgeryDTO.getAnesthetistList().get(i);
                annesthesists += userDTO.getEmployeeName()+", ";
            }

            surgeons = surgeons.substring(0, surgeons.lastIndexOf(","));
            assSurgeons = assSurgeons.substring(0, assSurgeons.lastIndexOf(","));
            annesthesists = annesthesists.substring(0, annesthesists.lastIndexOf(","));
            dto.setSurgeons(surgeons);
            dto.setAssSurgeons(assSurgeons);
            dto.setAnesthetist(annesthesists);

            System.out.println("-------------- Surgeons: " + surgeons + " ----------------------");
            System.out.println("-------------- AssSurgeons: "+assSurgeons+" ----------------------");
            System.out.println("-------------- Annesthesists: "+annesthesists+" ----------------------");

            String sql = "select id,name from tbl_patient where id="+patientId;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                dto.setPatientId(rs.getInt("id"));
                dto.setPatientName(rs.getString("name"));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Problems----"+e.toString());
        }finally {
            try {
                conn.close();
                stmt.close();
            }catch (Exception e){}
        }
        return dto;
    }
}
