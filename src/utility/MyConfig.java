package utility;

import java.util.HashMap;

public class MyConfig{
	
	public static final int lengthOfPINCode=10;
	public static final String pinPrefix="PG";
	
	public static final int diseaseHistory = 1;
	public static final int diseaseSymptom = 2;
	public static final int diseaseInspection = 3;
	public static final int diseasePalpation = 4;
	public static final int diseaseAuscultation = 5;
	public static final int diseasePerticipatingFactor = 6;
	public static final int diseaseOthers = 7;
	public static final int diseaseTreatmentPlan = 16;
	public static final int diseaseDiagnosis = 17;
	
	
	public static final int infoExposureTo = 1;
	public static final int infoHabits = 2;
	public static final int infoMenstrualHistory=3;
	public static final int infoObstetricalHistory=4;
	public static final int infoFamilyRelevantDiseaseHistory=5;
	public static final int infoHistoryOfImmunization=6;
	
	public static final int infoHistoryOfBlood=7;
	public static final int infoHistoryOfOthers=8;
	
	
	public static final int infoHistoryOfPreAnestheticEvaluation=9;
	public static final int infoHistoryOfPreAnEvIntraOralExamination=10;
	public static final int infoHistoryOfPreAnEvExtraOralExamination=11;
	
	public static final int infoGeneralAssessmentOfIllness=12;
	public static final int infoMentalStateAndIntelligence=13;
	public static final int infoMentalState=14;
	public static final int infoFacies=15;
	
	public static final int userSystem=1;
	public static final int userDoctor=2;
	
	public static final String filePath = "http://localhost:8080/images/";
	
	public static HashMap<Integer, String> refToOtherDeptDetails = new HashMap<Integer , String>() {{
		put(0, "None");
		put(1, "Maxillofacial Indoor");
		put(2, "Maxillofacial Minor surgery");
		put(3, "Maxillofacial Day case surgery / sedation technique");
		put(4, "Maxillofacial Emergency room");
		put(5, "Conservative and endodontic department");
		put(6, "Orthrodontic department");
		put(7, "Prosthodonticdepartment");
		put(8, "Children and preventive wing");
		put(9, "Medicine");
		put(10, "Surgery");
		put(11, "Physiotherapy centet");
		put(12, "DOT center");
		put(13, "Other");
	}};
	
	public static final int diagnosisRoomRole=10;
	public static final int frontDeskRole=11;
	public static final int minorOTOrOutdoorRole=12;
	public static final int dutyNurseRole=13;
	public static final int SurgeonRole=14;
	public static final int AssistantSurgeonRole=15;
	public static final int AnesthetistRole=16;
	public static final int DoctorForBed=17;
	
	public static final int deptDiagnosisRoom=0;
	public static final int deptOutdoorOrMinorOT=2;
	public static final int deptIndoor=3;
	public static final int deptEmergency=4;
	public static final int deptDaycare=6;
	
	
	
	
}
