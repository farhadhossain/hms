package patientOthers;

import java.util.HashSet;


public class PatientOthersDTO
{
	
	public PatientOthersDTO(){
		cigarettesStick="";
		cigarettesYear="";
		smokelessTobaccoTime="";
		smokelessTobaccoYear="";
		alcoholPack="";
		alcoholYear="";
		otherSubsAbuse="";
		otherSubsAbuseYear="";
		father="";
		mother="";
		brother="";
		sister="";
		wifeOrHusband="";
		consanguiOfMarri="";
		famlyDisOther="";
		hobbies="";
		vsTemp="";
		vsPulse="";
		vsRate="";
		vsBloodPre="";
		bsHeight="";
		bsWeight="";
		bsBMI="";
		bsWC="";
		clInspec="";
		clPalpi="";
		clPrec="";
		clAusc="";
		jvp="";
		carotidPulsCar="";
		apexImp="";
		parastImp="";
		palpaHs="";
		thrill="";
		hs="";
		ec="";
		os="";
		murmur="";
		peripheralPul="";
		inspecOthers="";
		palpaOthers="";
		precusNote="";
		auscultion="";
		carotidPulse="";
		genIntFunc="";
		motorFunc="";
		sensory="";
		carnialNerve="";
		otherDescip="";
		infoId98_1="";
		infoId98_2="";
		infoId99="";
		infoId100_1="";
		infoId100_2="";
		infoId101="";
		infoId121="";
		infoId125="";
		infoId149_2="";
		curProb="";
		otherKnownProb="";
		curProbTreatment="";
		curDose="";
		curDoseDuration="";
		curDoseEffectiveness="";
		curDrugsUse="";
		curDrugsReason="";
		curDrugsDose="";
		curDrugsDur="";
		curDrugsEffectiveness="";
		curDrugsSideEffect="";
		infoId154="";
		infoId219="";
		infoId205="";
		infoId210="";
		infoId200="";
		infoId197="";
		infoId190="";
		infoId169="";
		infoId182="";
		setOccupation("");
	}
	
	private int userId;
	private int exposureTo[];
	private int habitsId[];
	private int menstrualHistoryId[];
	private int obstetricalHistoryId[];
	private int relevantDiseaseHistoryId[];
	private int immunizationHistoryId[];
	
	private int religionId;
	private int socialStatusId;
	private String occupation;
	private String cigarettesStick;
	private String cigarettesYear;
	private String smokelessTobaccoTime;
	private String smokelessTobaccoYear;
	private String alcoholPack;
	private String alcoholYear;
	private String otherSubsAbuse;
	private String otherSubsAbuseYear;
	private int maritalStatus;
	
	private String father;
	private String mother;
	private String brother;
	private String sister;
	private String wifeOrHusband;
	private String consanguiOfMarri;
	private String famlyDisOther;
	private String hobbies;
	
	public HashSet<Integer> patExposureToId;
	public HashSet<Integer> patHabitsId;
	public HashSet<Integer> patMenstrualHistoryId;
	public HashSet<Integer> patObstetricalHistoryId;
	public HashSet<Integer> patRelevantDiseaseHistoryId;
	public HashSet<Integer> patImmunizationHistoryId;
	public HashSet<Integer> patInfoId;
	
	/**** Investigation *****/
	private int infoId[];
	private int infoId92;
	private int infoId93;
	private int infoId94;
	private int infoId95;
	private int infoId96;
	private int infoId97;
	private int infoId75;
	private int infoId76;

	public String getInfoId197() {
		return infoId197;
	}

	public void setInfoId197(String infoId197) {
		this.infoId197 = infoId197;
	}

	private String infoId197;

	public String getInfoId190() {
		return infoId190;
	}

	public void setInfoId190(String infoId190) {
		this.infoId190 = infoId190;
	}

	private String infoId190;

	public String getInfoId169() {
		return infoId169;
	}

	public void setInfoId169(String infoId169) {
		this.infoId169 = infoId169;
	}

	private String infoId169;

	public String getInfoId182() {
		return infoId182;
	}

	public void setInfoId182(String infoId182) {
		this.infoId182 = infoId182;
	}

	private String infoId182;
	private String infoId98_1;
	private String infoId98_2;

	public String getInfoId219() {
		return infoId219;
	}

	public void setInfoId219(String infoId219) {
		this.infoId219 = infoId219;
	}

	private String infoId219;
	private int infoId86;
	private int infoId87;
	
	/**** Pre-anesthetic evaluation *****/
	
	private String curProb;
	private String otherKnownProb;
	private String curProbTreatment;
	private String curDose;
	private String curDoseDuration;
	private String curDoseEffectiveness;
	private String curDrugsUse;
	private String curDrugsReason;
	private String curDrugsDose;
	private String curDrugsDur;
	private String curDrugsEffectiveness;
	private String curDrugsSideEffect;
	
	private String infoId99;

	public String getInfoId205() {
		return infoId205;
	}

	public void setInfoId205(String infoId205) {
		this.infoId205 = infoId205;
	}

	private String infoId205;
	private String infoId100_1;
	private String infoId100_2;
	private String infoId101;
	private String infoId121;
	private String infoId125;

	public String getInfoId200() {
		return infoId200;
	}

	public void setInfoId200(String infoId200) {
		this.infoId200 = infoId200;
	}

	private String infoId200;

	public String getInfoId210() {
		return infoId210;
	}

	public void setInfoId210(String infoId210) {
		this.infoId210 = infoId210;
	}

	private String infoId210;
	private int infoId136;
	private int infoId137;
	private int infoId139;
	private int infoId146;
	private int infoId147;
	private int infoId149_1;
	private String infoId149_2;
	private int infoId150;
	private int infoId151;
	private String infoId154;
	/**** PHYSICAL EXAMINATION *****/
	private String vsTemp;
	private String vsPulse;
	private String vsRate;
	private String vsBloodPre;
	private String bsHeight;
	private String bsWeight;
	private String bsBMI;
	private String bsWC;
	private String clInspec;
	private String clPalpi;
	private String clPrec;
	private String clAusc;
	private String jvp;
	private String carotidPulsCar;
	private String apexImp;
	private String parastImp;
	
	private String palpaHs;
	private String thrill;
	private String hs;
	private String ec;
	private String os;
	private String murmur;
	private String peripheralPul;
	private String inspecOthers;
	private int inspectionId[];
	private int palpationId[];
	private String palpaOthers;
	private String precusNote;
	private String auscultion;
	private int neckVein;
	private int movOfNeck;
	private int thyroidGland;
	private String carotidPulse;
	private String genIntFunc;
	private String motorFunc;
	private String sensory;
	private String carnialNerve;
	private String otherDescip;
	
	public HashSet<Integer> patInspectionId;
	public HashSet<Integer> patPalpationId;
	
	/**** Drug & Medication History *****/
	public int id;
	private String Drugs; 
	private String DateStarted;
	private String Dose;
	private String ADR;
	private int drugHistoryId[];
	

	public String getCurProb() {
		return curProb;
	}
	public void setCurProb(String curProb) {
		this.curProb = curProb;
	}
	public String getOtherKnownProb() {
		return otherKnownProb;
	}
	public void setOtherKnownProb(String otherKnownProb) {
		this.otherKnownProb = otherKnownProb;
	}
	public String getCurProbTreatment() {
		return curProbTreatment;
	}
	public void setCurProbTreatment(String curProbTreatment) {
		this.curProbTreatment = curProbTreatment;
	}
	public String getCurDose() {
		return curDose;
	}
	public void setCurDose(String curDose) {
		this.curDose = curDose;
	}
	public String getCurDoseDuration() {
		return curDoseDuration;
	}
	public void setCurDoseDuration(String curDoseDuration) {
		this.curDoseDuration = curDoseDuration;
	}
	public String getCurDoseEffectiveness() {
		return curDoseEffectiveness;
	}
	public void setCurDoseEffectiveness(String curDoseEffectiveness) {
		this.curDoseEffectiveness = curDoseEffectiveness;
	}
	public String getCurDrugsUse() {
		return curDrugsUse;
	}
	public void setCurDrugsUse(String curDrugsUse) {
		this.curDrugsUse = curDrugsUse;
	}
	public String getCurDrugsDose() {
		return curDrugsDose;
	}
	public void setCurDrugsDose(String curDrugsDose) {
		this.curDrugsDose = curDrugsDose;
	}
	public String getCurDrugsDur() {
		return curDrugsDur;
	}
	public void setCurDrugsDur(String curDrugsDur) {
		this.curDrugsDur = curDrugsDur;
	}
	public String getCurDrugsEffectiveness() {
		return curDrugsEffectiveness;
	}
	public void setCurDrugsEffectiveness(String curDrugsEffectiveness) {
		this.curDrugsEffectiveness = curDrugsEffectiveness;
	}
	public String getCurDrugsSideEffect() {
		return curDrugsSideEffect;
	}
	public void setCurDrugsSideEffect(String curDrugsSideEffect) {
		this.curDrugsSideEffect = curDrugsSideEffect;
	}
	public String getInfoId100_1() {
		return infoId100_1;
	}
	public void setInfoId100_1(String infoId100_1) {
		this.infoId100_1 = infoId100_1;
	}
	public String getInfoId100_2() {
		return infoId100_2;
	}
	public void setInfoId100_2(String infoId100_2) {
		this.infoId100_2 = infoId100_2;
	}
	
	public String getInfoId121() {
		return infoId121;
	}
	public void setInfoId121(String infoId121) {
		this.infoId121 = infoId121;
	}
	public String getInfoId125() {
		return infoId125;
	}
	public void setInfoId125(String infoId125) {
		this.infoId125 = infoId125;
	}
	public int getInfoId136() {
		return infoId136;
	}
	public void setInfoId136(int infoId136) {
		this.infoId136 = infoId136;
	}
	public int getInfoId137() {
		return infoId137;
	}
	public void setInfoId137(int infoId137) {
		this.infoId137 = infoId137;
	}
	public int getInfoId139() {
		return infoId139;
	}
	public void setInfoId139(int infoId139) {
		this.infoId139 = infoId139;
	}
	public int getInfoId146() {
		return infoId146;
	}
	public void setInfoId146(int infoId146) {
		this.infoId146 = infoId146;
	}
	public int getInfoId147() {
		return infoId147;
	}
	public void setInfoId147(int infoId147) {
		this.infoId147 = infoId147;
	}
	public int getInfoId149_1() {
		return infoId149_1;
	}
	public void setInfoId149_1(int infoId149_1) {
		this.infoId149_1 = infoId149_1;
	}
	
	public int getInfoId150() {
		return infoId150;
	}
	public void setInfoId150(int infoId150) {
		this.infoId150 = infoId150;
	}
	public int getInfoId151() {
		return infoId151;
	}
	public void setInfoId151(int infoId151) {
		this.infoId151 = infoId151;
	}
	public int getInfoId92() {
		return infoId92;
	}
	public void setInfoId92(int infoId92) {
		this.infoId92 = infoId92;
	}
	public int getInfoId94() {
		return infoId94;
	}
	public void setInfoId94(int infoId94) {
		this.infoId94 = infoId94;
	}
	public int getInfoId95() {
		return infoId95;
	}
	public void setInfoId95(int infoId95) {
		this.infoId95 = infoId95;
	}
	public int getInfoId96() {
		return infoId96;
	}
	public void setInfoId96(int infoId96) {
		this.infoId96 = infoId96;
	}
	public int getInfoId97() {
		return infoId97;
	}
	public void setInfoId97(int infoId97) {
		this.infoId97 = infoId97;
	}
	public int getInfoId75() {
		return infoId75;
	}
	public void setInfoId75(int infoId75) {
		this.infoId75 = infoId75;
	}
	public int getInfoId76() {
		return infoId76;
	}
	public void setInfoId76(int infoId76) {
		this.infoId76 = infoId76;
	}
	public String getInfoId98_1() {
		return infoId98_1;
	}
	public void setInfoId98_1(String infoId98_1) {
		this.infoId98_1 = infoId98_1;
	}
	public String getInfoId98_2() {
		return infoId98_2;
	}
	public void setInfoId98_2(String infoId98_2) {
		this.infoId98_2 = infoId98_2;
	}
	public int getInfoId86() {
		return infoId86;
	}
	public void setInfoId86(int infoId86) {
		this.infoId86 = infoId86;
	}
	public int getInfoId87() {
		return infoId87;
	}
	public void setInfoId87(int infoId87) {
		this.infoId87 = infoId87;
	}
	public HashSet<Integer> getPatExposureToId() {
		return patExposureToId;
	}
	public void setPatExposureToId(HashSet<Integer> patExposureToId) {
		this.patExposureToId = patExposureToId;
	}
	public HashSet<Integer> getPatHabitsId() {
		return patHabitsId;
	}
	public void setPatHabitsId(HashSet<Integer> patHabitsId) {
		this.patHabitsId = patHabitsId;
	}
	public HashSet<Integer> getPatMenstrualHistoryId() {
		return patMenstrualHistoryId;
	}
	public void setPatMenstrualHistoryId(HashSet<Integer> patMenstrualHistoryId) {
		this.patMenstrualHistoryId = patMenstrualHistoryId;
	}
	public HashSet<Integer> getPatObstetricalHistoryId() {
		return patObstetricalHistoryId;
	}
	public void setPatObstetricalHistoryId(HashSet<Integer> patObstetricalHistoryId) {
		this.patObstetricalHistoryId = patObstetricalHistoryId;
	}
	public HashSet<Integer> getPatRelevantDiseaseHistoryId() {
		return patRelevantDiseaseHistoryId;
	}
	public void setPatRelevantDiseaseHistoryId(
			HashSet<Integer> patRelevantDiseaseHistoryId) {
		this.patRelevantDiseaseHistoryId = patRelevantDiseaseHistoryId;
	}
	public HashSet<Integer> getPatImmunizationHistoryId() {
		return patImmunizationHistoryId;
	}
	public void setPatImmunizationHistoryId(
			HashSet<Integer> patImmunizationHistoryId) {
		this.patImmunizationHistoryId = patImmunizationHistoryId;
	}
	public String getVsTemp() {
		return vsTemp;
	}
	public void setVsTemp(String vsTemp) {
		this.vsTemp = vsTemp;
	}
	public String getVsPulse() {
		return vsPulse;
	}
	public void setVsPulse(String vsPulse) {
		this.vsPulse = vsPulse;
	}
	public String getVsRate() {
		return vsRate;
	}
	public void setVsRate(String vsRate) {
		this.vsRate = vsRate;
	}
	public String getVsBloodPre() {
		return vsBloodPre;
	}
	public void setVsBloodPre(String vsBloodPre) {
		this.vsBloodPre = vsBloodPre;
	}
	public String getBsHeight() {
		return bsHeight;
	}
	public void setBsHeight(String bsHeight) {
		this.bsHeight = bsHeight;
	}
	public String getBsWeight() {
		return bsWeight;
	}
	public void setBsWeight(String bsWeight) {
		this.bsWeight = bsWeight;
	}
	public String getBsBMI() {
		return bsBMI;
	}
	public void setBsBMI(String bsBMI) {
		this.bsBMI = bsBMI;
	}
	public String getBsWC() {
		return bsWC;
	}
	public void setBsWC(String bsWC) {
		this.bsWC = bsWC;
	}
	public String getClInspec() {
		return clInspec;
	}
	public void setClInspec(String clInspec) {
		this.clInspec = clInspec;
	}
	public String getClPalpi() {
		return clPalpi;
	}
	public void setClPalpi(String clPalpi) {
		this.clPalpi = clPalpi;
	}
	public String getClPrec() {
		return clPrec;
	}
	public void setClPrec(String clPrec) {
		this.clPrec = clPrec;
	}
	public String getClAusc() {
		return clAusc;
	}
	public void setClAusc(String clAusc) {
		this.clAusc = clAusc;
	}
	public String getJvp() {
		return jvp;
	}
	public void setJvp(String jvp) {
		this.jvp = jvp;
	}
	
	public String getApexImp() {
		return apexImp;
	}
	public void setApexImp(String apexImp) {
		this.apexImp = apexImp;
	}
	public String getParastImp() {
		return parastImp;
	}
	public void setParastImp(String parastImp) {
		this.parastImp = parastImp;
	}
	public String getPalpaHs() {
		return palpaHs;
	}
	public void setPalpaHs(String palpaHs) {
		this.palpaHs = palpaHs;
	}
	public String getThrill() {
		return thrill;
	}
	public void setThrill(String thrill) {
		this.thrill = thrill;
	}
	public String getHs() {
		return hs;
	}
	public void setHs(String hs) {
		this.hs = hs;
	}
	public String getEc() {
		return ec;
	}
	public void setEc(String ec) {
		this.ec = ec;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getMurmur() {
		return murmur;
	}
	public void setMurmur(String murmur) {
		this.murmur = murmur;
	}
	public String getPeripheralPul() {
		return peripheralPul;
	}
	public void setPeripheralPul(String peripheralPul) {
		this.peripheralPul = peripheralPul;
	}
	public String getInspecOthers() {
		return inspecOthers;
	}
	public void setInspecOthers(String inspecOthers) {
		this.inspecOthers = inspecOthers;
	}
	public int[] getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(int[] inspectionId) {
		this.inspectionId = inspectionId;
	}
	public int[] getPalpationId() {
		return palpationId;
	}
	public void setPalpationId(int[] palpationId) {
		this.palpationId = palpationId;
	}
	public String getPalpaOthers() {
		return palpaOthers;
	}
	public void setPalpaOthers(String palpaOthers) {
		this.palpaOthers = palpaOthers;
	}
	public String getPrecusNote() {
		return precusNote;
	}
	public void setPrecusNote(String precusNote) {
		this.precusNote = precusNote;
	}
	public String getAuscultion() {
		return auscultion;
	}
	public void setAuscultion(String auscultion) {
		this.auscultion = auscultion;
	}
	public int getNeckVein() {
		return neckVein;
	}
	public void setNeckVein(int neckVein) {
		this.neckVein = neckVein;
	}
	public int getMovOfNeck() {
		return movOfNeck;
	}
	public void setMovOfNeck(int movOfNeck) {
		this.movOfNeck = movOfNeck;
	}
	public int getThyroidGland() {
		return thyroidGland;
	}
	public void setThyroidGland(int thyroidGland) {
		this.thyroidGland = thyroidGland;
	}
	public String getCarotidPulse() {
		return carotidPulse;
	}
	public void setCarotidPulse(String carotidPulse) {
		this.carotidPulse = carotidPulse;
	}
	public String getGenIntFunc() {
		return genIntFunc;
	}
	public void setGenIntFunc(String genIntFunc) {
		this.genIntFunc = genIntFunc;
	}
	public String getMotorFunc() {
		return motorFunc;
	}
	public void setMotorFunc(String motorFunc) {
		this.motorFunc = motorFunc;
	}
	public String getSensory() {
		return sensory;
	}
	public void setSensory(String sensory) {
		this.sensory = sensory;
	}
	public String getCarnialNerve() {
		return carnialNerve;
	}
	public void setCarnialNerve(String carnialNerve) {
		this.carnialNerve = carnialNerve;
	}
	public String getOtherDescip() {
		return otherDescip;
	}
	public void setOtherDescip(String otherDescip) {
		this.otherDescip = otherDescip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrugs() {
		return Drugs;
	}
	public void setDrugs(String drugs) {
		Drugs = drugs;
	}
	public String getDateStarted() {
		return DateStarted;
	}
	public void setDateStarted(String dateStarted) {
		DateStarted = dateStarted;
	}
	public String getDose() {
		return Dose;
	}
	public void setDose(String dose) {
		Dose = dose;
	}
	public String getADR() {
		return ADR;
	}
	public void setADR(String adr) {
		ADR = adr;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int[] getExposureTo() {
		return exposureTo;
	}
	public void setExposureTo(int[] exposureTo) {
		this.exposureTo = exposureTo;
	}
	public int[] getHabitsId() {
		return habitsId;
	}
	public void setHabitsId(int[] habitsId) {
		this.habitsId = habitsId;
	}
	public int[] getMenstrualHistoryId() {
		return menstrualHistoryId;
	}
	public void setMenstrualHistoryId(int[] menstrualHistoryId) {
		this.menstrualHistoryId = menstrualHistoryId;
	}
	public int[] getObstetricalHistoryId() {
		return obstetricalHistoryId;
	}
	public void setObstetricalHistoryId(int[] obstetricalHistoryId) {
		this.obstetricalHistoryId = obstetricalHistoryId;
	}
	public int[] getRelevantDiseaseHistoryId() {
		return relevantDiseaseHistoryId;
	}
	public void setRelevantDiseaseHistoryId(int[] relevantDiseaseHistoryId) {
		this.relevantDiseaseHistoryId = relevantDiseaseHistoryId;
	}
	public int[] getImmunizationHistoryId() {
		return immunizationHistoryId;
	}
	public void setImmunizationHistoryId(int[] immunizationHistoryId) {
		this.immunizationHistoryId = immunizationHistoryId;
	}
	public String getCigarettesStick() {
		return cigarettesStick;
	}
	public void setCigarettesStick(String cigarettesStick) {
		this.cigarettesStick = cigarettesStick;
	}
	public String getCigarettesYear() {
		return cigarettesYear;
	}
	public void setCigarettesYear(String cigarettesYear) {
		this.cigarettesYear = cigarettesYear;
	}
	public String getSmokelessTobaccoTime() {
		return smokelessTobaccoTime;
	}
	public void setSmokelessTobaccoTime(String smokelessTobaccoTime) {
		this.smokelessTobaccoTime = smokelessTobaccoTime;
	}
	public String getSmokelessTobaccoYear() {
		return smokelessTobaccoYear;
	}
	public void setSmokelessTobaccoYear(String smokelessTobaccoYear) {
		this.smokelessTobaccoYear = smokelessTobaccoYear;
	}
	public String getAlcoholPack() {
		return alcoholPack;
	}
	public void setAlcoholPack(String alcoholPack) {
		this.alcoholPack = alcoholPack;
	}
	public String getAlcoholYear() {
		return alcoholYear;
	}
	public void setAlcoholYear(String alcoholYear) {
		this.alcoholYear = alcoholYear;
	}
	public String getOtherSubsAbuse() {
		return otherSubsAbuse;
	}
	public void setOtherSubsAbuse(String otherSubsAbuse) {
		this.otherSubsAbuse = otherSubsAbuse;
	}
	public String getOtherSubsAbuseYear() {
		return otherSubsAbuseYear;
	}
	public void setOtherSubsAbuseYear(String otherSubsAbuseYear) {
		this.otherSubsAbuseYear = otherSubsAbuseYear;
	}
	public int getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getBrother() {
		return brother;
	}
	public void setBrother(String brother) {
		this.brother = brother;
	}
	public String getSister() {
		return sister;
	}
	public void setSister(String sister) {
		this.sister = sister;
	}
	public String getWifeOrHusband() {
		return wifeOrHusband;
	}
	public void setWifeOrHusband(String wifeOrHusband) {
		this.wifeOrHusband = wifeOrHusband;
	}
	public String getConsanguiOfMarri() {
		return consanguiOfMarri;
	}
	public void setConsanguiOfMarri(String consanguiOfMarri) {
		this.consanguiOfMarri = consanguiOfMarri;
	}
	public String getFamlyDisOther() {
		return famlyDisOther;
	}
	public void setFamlyDisOther(String famlyDisOther) {
		this.famlyDisOther = famlyDisOther;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setDrugHistoryId(int drugHistoryId[]) {
		this.drugHistoryId = drugHistoryId;
	}
	public int[] getDrugHistoryId() {
		return drugHistoryId;
	}
	public void setCarotidPulsCar(String carotidPulsCar) {
		this.carotidPulsCar = carotidPulsCar;
	}
	public String getCarotidPulsCar() {
		return carotidPulsCar;
	}
	public void setInfoId(int infoId[]) {
		this.infoId = infoId;
	}
	public int[] getInfoId() {
		return infoId;
	}
	public void setInfoId93(int infoId93) {
		this.infoId93 = infoId93;
	}
	public int getInfoId93() {
		return infoId93;
	}
	public void setInfoId149_2(String infoId149_2) {
		this.infoId149_2 = infoId149_2;
	}
	public String getInfoId149_2() {
		return infoId149_2;
	}
	public void setInfoId101(String infoId101) {
		this.infoId101 = infoId101;
	}
	public String getInfoId101() {
		return infoId101;
	}
	public void setInfoId99(String infoId99) {
		this.infoId99 = infoId99;
	}
	public String getInfoId99() {
		return infoId99;
	}
	public void setCurDrugsReason(String curDrugsReason) {
		this.curDrugsReason = curDrugsReason;
	}
	public String getCurDrugsReason() {
		return curDrugsReason;
	}
	public void setInfoId154(String infoId154) {
		this.infoId154 = infoId154;
	}
	public String getInfoId154() {
		return infoId154;
	}
	public void setReligionId(int religionId) {
		this.religionId = religionId;
	}
	public int getReligionId() {
		return religionId;
	}
	public void setSocialStatusId(int socialStatusId) {
		this.socialStatusId = socialStatusId;
	}
	public int getSocialStatusId() {
		return socialStatusId;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOccupation() {
		return occupation;
	}
	
}
