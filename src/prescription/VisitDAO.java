package prescription;


import common.BaseDAO;
import utility.DAOResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by farhad on 4/26/16.
 */
public class VisitDAO extends BaseDAO{

    public DAOResult create(VisitDTO dto) {
        DAOResult daoResult = new DAOResult();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO visit(visit_date, patient_id, ticket_number, current_status, surgical_status) VALUES(NOW()," + dto.getPatientId() + ",'"+dto.getTicketNumber()+"',0,0)");
            stmt.execute("UPDATE tbl_patient SET current_status=0, surgical_status=0,date_of_rec=NOW(),date_of_adm=NULL,date_of_disch=NULL where id = "+ dto.getPatientId());
        }catch(Exception e){
            e.printStackTrace();
            daoResult.setValid(false);
            daoResult.setMessage("Database Error: " + e.toString());
        }finally {
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return daoResult;
    }

    public List<VisitDTO> getVisitsByPatientId(int patientId) {
        Connection conn=null;
        Statement stmt=null;
        String sql="SELECT * FROM visit where patient_id="+patientId;

        List<VisitDTO> dtos = new ArrayList<VisitDTO>();
        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();


            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                VisitDTO dto = new VisitDTO();
                dto.setId(rs.getInt("id"));
                dto.setTicketNumber(rs.getString("ticket_number"));
                dto.setVisitDate(rs.getDate("visit_date"));
                dto.setWordNumber(rs.getString("word_number"));
                dto.setBedNumber(rs.getString("bed_number"));
                dto.setCabinNumber(rs.getString("cabin_number"));
                dto.setDateOfAdmission(rs.getDate("date_of_adm"));
                dto.setBedDoctorID(rs.getInt("bed_doctor_id"));
                dto.setCurrentStatus(rs.getInt("current_status"));
                dtos.add(dto);
            }
            rs.close();


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return dtos;
    }

    public VisitDTO getVisitById(int id) {
        Connection conn=null;
        Statement stmt=null;
        String sql="SELECT * FROM visit where  id="+id;

        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();


            VisitDTO dto = new VisitDTO();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                dto.setId(rs.getInt("id"));
                dto.setTicketNumber(rs.getString("ticket_number"));
                dto.setVisitDate(rs.getDate("visit_date"));
                dto.setWordNumber(rs.getString("word_number"));
                dto.setBedNumber(rs.getString("bed_number"));
                dto.setCabinNumber(rs.getString("cabin_number"));
                dto.setDateOfAdmission(rs.getDate("date_of_adm"));
                dto.setBedDoctorID(rs.getInt("bed_doctor_id"));
                dto.setCurrentStatus(rs.getInt("current_status"));
            }
            rs.close();

            sql="select * from tbl_patient_disease where visit_id="+id;
        	System.out.println(sql);
        	rs = stmt.executeQuery(sql);
        	dto.diseaseTypeHash = new HashSet<Integer>();
        	while(rs.next()){
        		dto.diseaseTypeHash.add(rs.getInt("disease_id"));
        	}
        	rs.close();

            return dto;

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return null;
    }

    public int getCurrentVisitId(int patientId) {
        Connection conn=null;
        Statement stmt=null;
        String sql="SELECT max(id) FROM visit where patient_id="+patientId;
        int visitId = 0;
        try{
            conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
            stmt = conn.createStatement();


            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                visitId = rs.getInt(1);
            }

            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{stmt.close();}catch(Exception e){}
            try{conn.close();}catch(Exception e){}
        }
        return visitId;
    }


}
