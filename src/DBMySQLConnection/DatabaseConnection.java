package DBMySQLConnection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
	public String DatabaseURL;
	public String UserName;
	public String PassWord;
	public static String url="";
	public static String usrNameOfDB;
	public static String passOfDB;
	
	public static Connection ConnectionManager(){
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			return DriverManager.getConnection("jdbc:mysql:///hms", "root", "");
			//return DriverManager.getConnection("jdbc:mysql://104.194.248.186/SoftSwitch", "root", "a!2345");
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return null;
	}
	/*
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionManager();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select pin_number, pass_word from tbl_pins");
			File file = new File("sip.conf");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			while(rs.next()){
				System.out.println(rs.getString(1));
				bw.write("["+rs.getString(1)+"]\n");
				bw.write("type = friend\n");
				bw.write("secret = "+rs.getString(2)+"\n");
				bw.write("host = dynamic\n");				
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
