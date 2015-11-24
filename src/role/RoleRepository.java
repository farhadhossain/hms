package role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

public class RoleRepository{
	static HashMap<Integer, HashSet<Integer>> hm = new HashMap<Integer, HashSet<Integer>>();
	
	public static void reload(){
		try{
			if(hm.size()>0){
				hm.clear();
			}
			
			Connection conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
	        Statement st = conn.createStatement();
	        PreparedStatement pstmt = conn.prepareStatement("SELECT ROLE_ID, MENU_ID FROM role_permission");
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next())
	        {
	        	if(hm.get(rs.getInt("ROLE_ID"))==null){
	        		HashSet<Integer> hs = new HashSet<Integer>();
	        		hs.add(rs.getInt("MENU_ID"));
	        		hm.put(rs.getInt("ROLE_ID"), hs);
	        	}else{
	        		HashSet<Integer> hs =hm.get(rs.getInt("ROLE_ID"));
	        		if(hs.contains(rs.getInt("MENU_ID"))==false){
	        			hs.add(rs.getInt("MENU_ID"));
	        			hm.put(rs.getInt("ROLE_ID"), hs);
	        		}
	        	}
	        }
	        
	        rs.close();
	        st.close();
	        conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isPermitted(int roleID, int permissionModuleID){
		if(hm.size()>0 && hm.get(roleID)!=null){
			HashSet<Integer> hs =hm.get(roleID);
			if(hs.contains(permissionModuleID)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
