package utility;

import java.util.HashMap;

public class OthersInfo {
	public static HashMap<Integer, String> ReligionByID = new HashMap<Integer , String>() {{
		put(1, "Muslim");
		put(2, "Hindu");
		put(3, "Christen");
		put(4, "Buddhists");
		put(5, "Others");
	}};
	
	public static HashMap<Integer, String> SocialStatByID = new HashMap<Integer , String>() {{
		put(1, "Status I");
		put(2, "Status II");
		put(3, "Status III");
	}};	
	
	
	public static String getPageNameByID(int userID, int diseaseID){
		if(diseaseID==1){
			return "../Disease/General.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==2){
			return "../Disease/CardiovascularSystem.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==3){
			return "../Disease/Respratory.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==4){
			return "../Disease/Liver.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==5){
			return "../Disease/Kidney.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==6){
			return "../Disease/Blood.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==11){
			return "../Disease/Endocrine.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==15){
			return "../Disease/LymphaticAndVascularTumor.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==16){
			return "../Disease/ZygomaticomaxillaryComplex.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==17){
			return "../Disease/SalivaryGland.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==18){
			return "../Disease/TMJDisorder.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==19){
			return "../Disease/MandibleWithTMJLowerFace.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==20){
			return "../Disease/MaxillofacialEmergency.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==21){
			return "../Disease/MaxillarySinusitisChOrAcuteFistula.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==22){
			return "../Disease/OroFacialCleft.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==23){
			return "../Disease/OroFaciaInfection.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==24){
			return "../Disease/MalignantTumor.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==25){
			return "../Disease/TMJAnkylosis.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==26){
			return "../Disease/BenignCystTumor.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==28){
			return "../Disease/Autoimmune.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}else if(diseaseID==29){
			return "../Disease/Others.jsp?userID="+userID+"&diseaseID="+diseaseID;
		}

		// for 2/3 use this html: <sup>2</sup>/<sub>3</sub>
		return "#";
	}	
	
}
