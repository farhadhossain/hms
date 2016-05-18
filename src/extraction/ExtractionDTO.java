package extraction;

import java.util.HashSet;

public class ExtractionDTO {
	private int userId;
	public String patientInfo;
	public String doctorInfo;
	private int uniqIDExtraction;
	
	private int extractionId[];
	
	private String extractionId1_1;
	private String extractionId1_2;
	private String extractionId1_3;
	private String extractionId1_4;
	
	private String extractionId2_1;
	private String extractionId2_2;
	private String extractionId2_3;
	private String extractionId2_4;
	
	private String extractionId3_1;
	private String extractionId3_2;
	private String extractionId3_3;
	private String extractionId3_4;
	
	private String extractionId4_1;
	private String extractionId4_2;
	private String extractionId4_3;
	private String extractionId4_4;
	private int totalTk;
	private String paymentRegNumber;
	private String extractionId4;
	private String extractionId5;
	private String extractionId6;
	private String extractionId7;
	private String extractionId8;
	private String extractionId9;
	private String extractionId10;
	private String extractionId11;
	private String extractionId12;
	private String extractionId13;
	private String extractionId14;

	public HashSet<Integer> patCurExtractionList;
	
	public ExtractionDTO(){
		uniqIDExtraction=-1;
		extractionId1_1="";
		extractionId1_2="";
		extractionId1_3="";
		extractionId1_4="";
		
		extractionId2_1="";
		extractionId2_2="";
		extractionId2_3="";
		extractionId2_4="";
		
		extractionId3_1="";
		extractionId3_2="";
		extractionId3_3="";
		extractionId3_4="";
		
		extractionId4_1="";
		extractionId4_2="";
		extractionId4_3="";
		extractionId4_4="";
		totalTk=0;
		paymentRegNumber="";

		extractionId4 = "";
		extractionId5 = "";
		extractionId6 = "";
		extractionId7 = "";
		extractionId8 = "";
		extractionId9 = "";
		extractionId10 = "";
		extractionId11 = "";
		extractionId12 = "";
		extractionId13 = "";
		extractionId14 = "";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getExtractionId1_1() {
		return extractionId1_1;
	}

	public void setExtractionId1_1(String extractionId1_1) {
		this.extractionId1_1 = extractionId1_1;
	}

	public String getExtractionId1_2() {
		return extractionId1_2;
	}

	public void setExtractionId1_2(String extractionId1_2) {
		this.extractionId1_2 = extractionId1_2;
	}

	public String getExtractionId1_3() {
		return extractionId1_3;
	}

	public void setExtractionId1_3(String extractionId1_3) {
		this.extractionId1_3 = extractionId1_3;
	}

	public String getExtractionId1_4() {
		return extractionId1_4;
	}

	public void setExtractionId1_4(String extractionId1_4) {
		this.extractionId1_4 = extractionId1_4;
	}

	public String getExtractionId2_1() {
		return extractionId2_1;
	}

	public void setExtractionId2_1(String extractionId2_1) {
		this.extractionId2_1 = extractionId2_1;
	}

	public String getExtractionId2_2() {
		return extractionId2_2;
	}

	public void setExtractionId2_2(String extractionId2_2) {
		this.extractionId2_2 = extractionId2_2;
	}

	public String getExtractionId2_3() {
		return extractionId2_3;
	}

	public void setExtractionId2_3(String extractionId2_3) {
		this.extractionId2_3 = extractionId2_3;
	}

	public String getExtractionId2_4() {
		return extractionId2_4;
	}

	public void setExtractionId2_4(String extractionId2_4) {
		this.extractionId2_4 = extractionId2_4;
	}

	public String getExtractionId3_1() {
		return extractionId3_1;
	}

	public void setExtractionId3_1(String extractionId3_1) {
		this.extractionId3_1 = extractionId3_1;
	}

	public String getExtractionId3_2() {
		return extractionId3_2;
	}

	public void setExtractionId3_2(String extractionId3_2) {
		this.extractionId3_2 = extractionId3_2;
	}

	public String getExtractionId3_3() {
		return extractionId3_3;
	}

	public void setExtractionId3_3(String extractionId3_3) {
		this.extractionId3_3 = extractionId3_3;
	}

	public String getExtractionId3_4() {
		return extractionId3_4;
	}

	public void setExtractionId3_4(String extractionId3_4) {
		this.extractionId3_4 = extractionId3_4;
	}

	public String getExtractionId4_1() {
		return extractionId4_1;
	}

	public void setExtractionId4_1(String extractionId4_1) {
		this.extractionId4_1 = extractionId4_1;
	}

	public String getExtractionId4_2() {
		return extractionId4_2;
	}

	public void setExtractionId4_2(String extractionId4_2) {
		this.extractionId4_2 = extractionId4_2;
	}

	public String getExtractionId4_3() {
		return extractionId4_3;
	}

	public void setExtractionId4_3(String extractionId4_3) {
		this.extractionId4_3 = extractionId4_3;
	}

	public String getExtractionId4_4() {
		return extractionId4_4;
	}

	public void setExtractionId4_4(String extractionId4_4) {
		this.extractionId4_4 = extractionId4_4;
	}

	public void setExtractionId(int extractionId[]) {
		this.extractionId = extractionId;
	}

	public int[] getExtractionId() {
		return extractionId;
	}

	public void setTotalTk(int totalTk) {
		this.totalTk = totalTk;
	}

	public int getTotalTk() {
		return totalTk;
	}

	public void setPaymentRegNumber(String paymentRegNumber) {
		this.paymentRegNumber = paymentRegNumber;
	}

	public String getPaymentRegNumber() {
		return paymentRegNumber;
	}

	public void setUniqIDExtraction(int uniqIDExtraction) {
		this.uniqIDExtraction = uniqIDExtraction;
	}

	public int getUniqIDExtraction() {
		return uniqIDExtraction;
	}

	public String getExtractionId5() {
		return extractionId5;
	}

	public void setExtractionId5(String extractionId5) {
		this.extractionId5 = extractionId5;
	}

	public String getExtractionId4() {
		return extractionId4;
	}

	public void setExtractionId4(String extractionId4) {
		this.extractionId4 = extractionId4;
	}

	public String getExtractionId6() {
		return extractionId6;
	}

	public void setExtractionId6(String extractionId6) {
		this.extractionId6 = extractionId6;
	}

	public String getExtractionId7() {
		return extractionId7;
	}

	public void setExtractionId7(String extractionId7) {
		this.extractionId7 = extractionId7;
	}

	public String getExtractionId8() {
		return extractionId8;
	}

	public void setExtractionId8(String extractionId8) {
		this.extractionId8 = extractionId8;
	}

	public String getExtractionId9() {
		return extractionId9;
	}

	public void setExtractionId9(String extractionId9) {
		this.extractionId9 = extractionId9;
	}

	public String getExtractionId10() {
		return extractionId10;
	}

	public void setExtractionId10(String extractionId10) {
		this.extractionId10 = extractionId10;
	}

	public String getExtractionId11() {
		return extractionId11;
	}

	public void setExtractionId11(String extractionId11) {
		this.extractionId11 = extractionId11;
	}

	public String getExtractionId12() {
		return extractionId12;
	}

	public void setExtractionId12(String extractionId12) {
		this.extractionId12 = extractionId12;
	}

	public String getExtractionId13() {
		return extractionId13;
	}

	public void setExtractionId13(String extractionId13) {
		this.extractionId13 = extractionId13;
	}

	public String getExtractionId14() {
		return extractionId14;
	}

	public void setExtractionId14(String extractionId14) {
		this.extractionId14 = extractionId14;
	}
}
