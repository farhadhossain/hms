package utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static ArrayList<String> diffDateFormats = new ArrayList<String>();
	public DateUtil(){
		if(diffDateFormats.size()==0){
			diffDateFormats.add("yyyy-MM-dd HH:mm:ss");
			diffDateFormats.add("yyyy-MM-dd HH:mm");
		}
	}
	
	public String getDecDateFromString(String dateStr){
		Date date = null; 
		int size=diffDateFormats.size();
		for(int i=0;i<size;i++){
			try{
				date = new SimpleDateFormat(diffDateFormats.get(i)).parse(dateStr);
			}catch(Exception e){
				continue;
			}	
			if(date!=null){
				if(date.getYear()>=0 && date.getMonth()>=0){
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.MILLISECOND, -1000);
					return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
				}			
			}
		}
		return null;
	}
	public String getDateFromString(String dateStr){
		Date date = null; 
		int size=diffDateFormats.size();
		for(int i=0;i<size;i++){
			try{
				date = new SimpleDateFormat(diffDateFormats.get(i)).parse(dateStr);
			}catch(Exception e){
				continue;
			}	
			if(date!=null){
				if(date.getYear()>=0 && date.getMonth()>=0){
					return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				}			
			}
		}
		return null;
	}
	
	
}