package log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

public class LogDAO {

	public int LogDetailsReportCount(String startDate, String endDate, int dateType) {
		Calendar clDate = Calendar.getInstance();
		Connection conn=null;
		PreparedStatement pstmt=null;
		int total=0;
		try{
			String sql="select count(*) from tbl_log";
			if(dateType==1){
				sql+=" where time_to_change>DATE_SUB(NOW(), INTERVAL 1 HOUR)";
			}else if(dateType==2){
				sql+=" where time_to_change>='"+clDate.get(Calendar.YEAR)+"-"+(clDate.get(Calendar.MONTH)+1)+"-"+(clDate.get(Calendar.DAY_OF_MONTH))+" 00:00:00'";
				sql+=" and time_to_change<='"+clDate.get(Calendar.YEAR)+"-"+(clDate.get(Calendar.MONTH)+1)+"-"+(clDate.get(Calendar.DAY_OF_MONTH))+" 23:59:59'";
			}else{
				String[] str1=startDate.split("-");
				String[] str2=endDate.split("-");
				sql+=" where time_to_change>='"+str1[2]+"-"+str1[1]+"-"+str1[0]+" 00:00:00'";
				sql+=" and time_to_change<='"+str2[2]+"-"+str2[1]+"-"+str2[0]+" 23:59:59'";
			}
			System.out.println(sql);
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
	        if(rs.next()){
	        	total=rs.getInt(1);
	        }
	        rs.close();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(conn!=null){conn.close();}}catch(Exception e){}
			try{if(pstmt!=null){pstmt.close();}}catch(Exception e){}
		}
		return total;
	}
	
	
	public ArrayList<LogDTO> LogDetailsReport(String startDate, String endDate, int dateType, int pageNum, int currPageNo, int numOfRecord) {
		ArrayList<LogDTO> listOfDTOs= new ArrayList<LogDTO>();
		Calendar clDate = Calendar.getInstance();
		int numOfData=50;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			String sql="select * from tbl_log";
			
			if(dateType==1){
				sql+=" where time_to_change>DATE_SUB(NOW(), INTERVAL 1 HOUR)";
			}else if(dateType==2){
				sql+=" where time_to_change>='"+clDate.get(Calendar.YEAR)+"-"+(clDate.get(Calendar.MONTH)+1)+"-"+(clDate.get(Calendar.DAY_OF_MONTH))+" 00:00:00'";
				sql+=" and time_to_change<='"+clDate.get(Calendar.YEAR)+"-"+(clDate.get(Calendar.MONTH)+1)+"-"+(clDate.get(Calendar.DAY_OF_MONTH))+" 23:59:59'";
			}else{
				String[] str1=startDate.split("-");
				String[] str2=endDate.split("-");
				sql+=" where time_to_change>='"+str1[2]+"-"+str1[1]+"-"+str1[0]+" 00:00:00'";
				sql+=" and time_to_change<='"+str2[2]+"-"+str2[1]+"-"+str2[0]+" 23:59:59'";
			}
			sql+=" order by time_to_change desc";
			
			//if(getAllCDR==false){
				if(pageNum==0){
					pageNum=-1;
					currPageNo=1;
				}else if(pageNum==-2){
					pageNum=1;
					if(numOfRecord%numOfData==0){
						currPageNo=(numOfRecord-numOfData)/numOfData;
					}else{
						currPageNo=numOfRecord/numOfData;
					}
				}
				//System.out.println("pageNum: "+pageNum);
				//System.out.println("currPageNo: "+currPageNo);
				if(pageNum==-1){
					if(currPageNo==1){
						sql+=" limit 0, "+numOfData;
					}else{
						sql+=" limit "+((currPageNo-2)*numOfData)+", "+numOfData;
					}
				}else if(pageNum==1){
					sql+=" limit "+(currPageNo*numOfData)+", "+numOfData;
				}
			//}
			System.out.println(sql);
			
			conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
			pstmt = conn.prepareStatement(sql);
	        ResultSet rs=pstmt.executeQuery();
	        while(rs.next()){
	        	LogDTO dto = new LogDTO();	        	   	
	        	dto.moduleName=rs.getString("module_name");
	        	dto.description=rs.getString("description");
	        	dto.byWhom=rs.getString("by_whom");
	        	dto.timeToChange=rs.getString("time_to_change");
	        	listOfDTOs.add(dto);
	        }
	        rs.close();
				        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(conn!=null){conn.close();}}catch(Exception e){}
			try{if(pstmt!=null){pstmt.close();}}catch(Exception e){}
		}
		
		return listOfDTOs;
	}

}
