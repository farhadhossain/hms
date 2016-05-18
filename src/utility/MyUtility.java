package utility;

import department.DepartmentDiseaseDTO;
import disease.DiseaseDTO;
import disease.form.DiseaseMetaData;
import disease.form.DiseaseMetaValues;
import patientOthers.PatientOthersDTO;

import java.util.HashMap;
import java.util.HashSet;

public class MyUtility {
	static int[] getSortedKeyList(HashMap<Integer, DiseaseMetaData> mapInfo){
		int[] array = new int[mapInfo.size()]; 
		
		int i=0;
		for(int key : mapInfo.keySet()){
			array[i++]=key;
		}
		
		for(i=0;i<array.length;i++){
			for(int j=i+1;j<array.length;j++){
				if(array[i]>array[j]){
					int tmp=array[j];
					array[j]=array[i];
					array[i]=tmp;
				}
			}
		}
		return array;
	}
	
	public static String generateSelectBox(String selectBoxName, int seBoxIdenti, String[] showNames, boolean editAndView){
		String str1="";
		if(editAndView==false){
			str1=" disabled=\"disabled\" ";
		}
		String tail="";
		if(selectBoxName.endsWith("-factor")==Boolean.FALSE) {
			tail = "<select name=\"" + selectBoxName + "\" id=\"" + selectBoxName + "\" " + str1 + " style=\"width: 90px;\">\n";
			for(int i=0;i<showNames.length;i++){
				if(seBoxIdenti==(i+1)){
					tail+="<option selected=\"selected\" value=\""+(i+1)+"\">"+showNames[i]+"</option>\n";
				}else{
					tail+="<option value=\""+(i+1)+"\">"+showNames[i]+"</option>\n";
				}
			}
			tail+="</select>\n";
		}
		else {
			if(selectBoxName.equalsIgnoreCase("T-factor"))
				tail = "<select ng-change=\"tnmGenerator(" + selectBoxName + ")\" ng-options=\"x for x in tList\" ng-model=\"" + selectBoxName + "\" id=\"" + selectBoxName + "\" " + str1 + " style=\"width: 90px;\">\n";
			else if(selectBoxName.equalsIgnoreCase("N-factor"))
				tail = "<select ng-change=\"tnmGenerator(" + selectBoxName + ")\" ng-options=\"x for x in nList\" ng-model=\"" + selectBoxName + "\" id=\"" + selectBoxName + "\" " + str1 + " style=\"width: 90px;\">\n";
			else
				tail = "<select ng-change=\"tnmGenerator(" + selectBoxName + ")\" ng-options=\"x for x in mList\" ng-model=\"" + selectBoxName + "\" id=\"" + selectBoxName + "\" " + str1 + " style=\"width: 90px;\">\n";

			tail+="</select>\n";
		}

		return tail;
	}
	
	public static String generateSelectBox(String selectBoxName, int seBoxIdenti, String[] showNames){
		String tail="";
		tail="<select name=\""+selectBoxName+"\" id=\""+selectBoxName+"\" style=\"width: 90px;\">\n";
		for(int i=0;i<showNames.length;i++){
			if(seBoxIdenti==(i+1)){
				tail+="<option selected=\"selected\" value=\""+(i+1)+"\">"+showNames[i]+"</option>\n";
			}else{
				tail+="<option value=\""+(i+1)+"\">"+showNames[i]+"</option>\n";
			}
		}
		tail+="</select>\n";
		return tail;
	}
	
	public static String getInputString(String inputName, int spaceingColumn, int descripId, DiseaseMetaData metaData, boolean isChecked, DiseaseDTO patCurDisDTO, boolean isChildExists, boolean editAndView){
		String tableName="";
		String str1="";

		if(spaceingColumn>0){
			str1=" style=\"margin-left: "+spaceingColumn+"px;\" ";
		}
		if(isChildExists){
			str1+=" onchange=\"changeThis('block"+inputName+descripId+"')\"";
		}
		if(isChecked){
			str1+=" checked=\"checked\" ";
		}
		if(editAndView==false){
			str1+=" disabled=\"disabled\" ";
		}

		String tail="";
		if(inputName.equals("specialCaseId")){
			tableName = "tbl_patient_disease_special_case_details";
			String name="specialCaseId"+descripId;
			if(descripId==1){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId1(), new String[]{"Present", "Absent"}, editAndView);
			}else if(descripId==2){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId2(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==3){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId3(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==4){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId4()+"\">\n";
			}else if(descripId==6){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId6(), new String[]{"Open","Polypoid mass"}, editAndView);
			}else if(descripId==7){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId7(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==8){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId8(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==9){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId9_1\" value=\""+patCurDisDTO.getSpecialCaseId9_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId9_2\" value=\""+patCurDisDTO.getSpecialCaseId9_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId9_3\" value=\""+patCurDisDTO.getSpecialCaseId9_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId9_4\" value=\""+patCurDisDTO.getSpecialCaseId9_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==10){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId10_1\" value=\""+patCurDisDTO.getSpecialCaseId10_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId10_2\" value=\""+patCurDisDTO.getSpecialCaseId10_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId10_3\" value=\""+patCurDisDTO.getSpecialCaseId10_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId10_4\" value=\""+patCurDisDTO.getSpecialCaseId10_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==11){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId11(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==12){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId12_1\" value=\""+patCurDisDTO.getSpecialCaseId12_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId12_2\" value=\""+patCurDisDTO.getSpecialCaseId12_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId12_3\" value=\""+patCurDisDTO.getSpecialCaseId12_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId12_4\" value=\""+patCurDisDTO.getSpecialCaseId12_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==13){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId13()+"\">\n";
			}
			else if(descripId==14){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId14(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==15){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId15(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==16){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId16(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==17){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId17(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==18){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId18(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==19){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId19(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==20){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId20(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==21){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId21(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==22){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId22()+"\">\n";
			}else if(descripId==23){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId23(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==24){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId24(), new String[]{"Positive","Negative"}, editAndView);
			}else if(descripId==25){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId25()+"\">\n";
			}else if(descripId==26){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId26()+"\">\n";
			}else if(descripId==27){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId27(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==28){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId28(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==30){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId30(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==31){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId31(), new String[]{"Small","Medium","Large"}, editAndView);
			}else if(descripId==32){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId32(), new String[]{"Right","Left","Medium"}, editAndView);
			}else if(descripId==33){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId33(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==34){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId34(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==35){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId35(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==36){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId36(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==37){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId37(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==39){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId39(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==40){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId40(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==42){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId42(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==43){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId43(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==47){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId47(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==48){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId48()+"\">\n";
			}else if(descripId==49){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId49(), new String[]{"None","Slight","Moderate","Marked"}, editAndView);
			}else if(descripId==50){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId50(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==52){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId52(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==53){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId53()+"\">\n";
			}else if(descripId==54){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId54(), new String[]{"Right","Left","Absent"}, editAndView);
			}else if(descripId==55){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId55(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==56){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId56(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==57){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId57(), new String[]{"One-thirds","Two-thirds","Complete"}, editAndView);
			}else if(descripId==58){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId58(), new String[]{"Congenital lip","Sinus"}, editAndView);
			}else if(descripId==62){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId62()+"\">\n";
			}else if(descripId==63){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId63()+"\">\n";
			}else if(descripId==64){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId64(), new String[]{"Normal","Abnormal"}, editAndView);
			}else if(descripId==71){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId71()+"\">\n";
			}else if(descripId==74){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId74()+"\">\n";
			}else if(descripId==75){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId75(), new String[]{"Good","Impaired","Loss of hearing"}, editAndView);
			}else if(descripId==96){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId96()+"\">\n";
			}else if(descripId==97){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId97()+"\">\n";
			}else if(descripId==98){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId98()+"\">\n";
			}else if(descripId==99){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId99(), new String[]{"Normal","Inflamed","Ulcerated"}, editAndView);
			}else if(descripId==100){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId100(), new String[]{"Normal","Inflamed","Thick purulent discharge"}, editAndView);
			}else if(descripId==101){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId101(), new String[]{"Normal","Inflamed","Thick/ purulent discharge"}, editAndView);
			}else if(descripId==102){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId102(), new String[]{"Redness","Swelling","White coat"}, editAndView);
			}else if(descripId==103){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId103(), new String[]{"Redness","Swelling","Shifted"}, editAndView);
			}else if(descripId==104){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId104(), new String[]{"Redness","Swelling","Pushed"}, editAndView);
			}else if(descripId==105){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId105(), new String[]{"Redness","Pushed superiorly","Coated","Swelling"}, editAndView);
			}else if(descripId==106){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId106(), new String[]{"Normal","pushed superiorly","Restricted movement"}, editAndView);
			}else if(descripId==107){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId107_1\" value=\""+patCurDisDTO.getSpecialCaseId107_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId107_2\" value=\""+patCurDisDTO.getSpecialCaseId107_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId107_3\" value=\""+patCurDisDTO.getSpecialCaseId107_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId107_4\" value=\""+patCurDisDTO.getSpecialCaseId107_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==108){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId108_1\" value=\""+patCurDisDTO.getSpecialCaseId108_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId108_2\" value=\""+patCurDisDTO.getSpecialCaseId108_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId108_3\" value=\""+patCurDisDTO.getSpecialCaseId108_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId108_4\" value=\""+patCurDisDTO.getSpecialCaseId108_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==109){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId109_1\" value=\""+patCurDisDTO.getSpecialCaseId109_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId109_2\" value=\""+patCurDisDTO.getSpecialCaseId109_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId109_3\" value=\""+patCurDisDTO.getSpecialCaseId109_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId109_4\" value=\""+patCurDisDTO.getSpecialCaseId109_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==110){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId110_1\" value=\""+patCurDisDTO.getSpecialCaseId110_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId110_2\" value=\""+patCurDisDTO.getSpecialCaseId110_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId110_3\" value=\""+patCurDisDTO.getSpecialCaseId110_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId110_4\" value=\""+patCurDisDTO.getSpecialCaseId110_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==111){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId111_1\" value=\""+patCurDisDTO.getSpecialCaseId111_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId111_2\" value=\""+patCurDisDTO.getSpecialCaseId111_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId111_3\" value=\""+patCurDisDTO.getSpecialCaseId111_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId111_4\" value=\""+patCurDisDTO.getSpecialCaseId111_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==112){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId112_1\" value=\""+patCurDisDTO.getSpecialCaseId112_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId112_2\" value=\""+patCurDisDTO.getSpecialCaseId112_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId112_3\" value=\""+patCurDisDTO.getSpecialCaseId112_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId112_4\" value=\""+patCurDisDTO.getSpecialCaseId112_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==113){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId113_1\" value=\""+patCurDisDTO.getSpecialCaseId113_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId113_2\" value=\""+patCurDisDTO.getSpecialCaseId113_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId113_3\" value=\""+patCurDisDTO.getSpecialCaseId113_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId113_4\" value=\""+patCurDisDTO.getSpecialCaseId113_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==114){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId114_1\" value=\""+patCurDisDTO.getSpecialCaseId114_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId114_2\" value=\""+patCurDisDTO.getSpecialCaseId114_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId114_3\" value=\""+patCurDisDTO.getSpecialCaseId114_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId114_4\" value=\""+patCurDisDTO.getSpecialCaseId114_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==115){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId115_1\" value=\""+patCurDisDTO.getSpecialCaseId115_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId115_2\" value=\""+patCurDisDTO.getSpecialCaseId115_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"specialCaseId115_3\" value=\""+patCurDisDTO.getSpecialCaseId115_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId115_4\" value=\""+patCurDisDTO.getSpecialCaseId115_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==116){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId116()+"\">\n";
			}else if(descripId==117){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId117()+"\">\n";
			}else if(descripId==118){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId118(), new String[]{"Sudden","Gradual","Gradual then sudden"}, editAndView);
			}else if(descripId==119){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId119()+"\">\n";
			}else if(descripId==123){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId123()+"\">\n";
			}else if(descripId==125){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId125(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==126){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getSpecialCaseId126_1()+"\">\n<br>";
				tail+="&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Superior -inferiorly<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId126_2()+"\">\n<br>";
			}else if(descripId==127){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getSpecialCaseId127_1()+"\">\n X ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId127_2()+"\">\n cm<sup>2</sup><br>";
			}else if(descripId==128){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId128(), new String[]{"Less then 2 cm","2-4 cm","More then 6 cm"}, editAndView);
			}else if(descripId==129){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId129(), new String[]{"T&#x2093;","T&#8320;","Tis","T&#8321;","T&#8322;","T&#8323;","T&#8324;"}, editAndView);
			}else if(descripId==130){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId130(), new String[]{"Palpable","Not palpable"}, editAndView);
			}else if(descripId==131){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId131(), new String[]{"Nx","No","N1","N2","N2a","N2b","N2c","N3"}, editAndView);
			}else if(descripId==132){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId132(), new String[]{"Absent","Present"}, editAndView);
			}else if(descripId==134){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId132(), new String[]{"Mx","M0","M1"}, editAndView);
			}else if(descripId==135){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId135()+"\">\n";
			}else if(descripId==136){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId136(), new String[]{"Stage 0","Stage-I","Stage-II","Stage-III","Stage-IVA","Stage-IVB","Stage-IVC"}, editAndView);
			}else if(descripId==137){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId137()+"\">\n";
			}else if(descripId==138){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId138_1(), new String[]{"Swelling","Ulcer","Perforation","Sinus","Necrosis","Abscess"}, editAndView);
				tail+="&emsp;Others <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId138_2()+"\">\n";
			}else if(descripId==139){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId139(), new String[]{"Smooth superficial","Round","Mobile","Lobuleted","Circumscribed","Busselation","Solid cystic","Soft fluctuant","Compressible"}, editAndView);
			}else if(descripId==141){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId141_1(), new String[]{"Exophytic","Ulcer infiltrative","Rerrulouy","Papillary","Nodular","Pigmented","Deep excavatating ulcer with diffuse peripheral extension","Leukoplakia"}, editAndView);
				tail+="&emsp;Others <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId141_2()+"\">\n<br>";
				tail+="&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Surface:<input type=\"text\" name=\""+name+"_3"+"\" value=\""+patCurDisDTO.getSpecialCaseId141_3()+"\">\n<br>";
				tail+="&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Edge:<input type=\"text\" name=\""+name+"_4"+"\" value=\""+patCurDisDTO.getSpecialCaseId141_4()+"\">\n";
			}else if(descripId==121){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId121_1(), new String[]{"Nil","Mild","Moderate","Sever"}, editAndView);
				tail+="&emsp;Referred to <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId121_2()+"\">\n";
			}else if(descripId==122){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId122(), new String[]{"Upper lip","Lower lip","Cheek","Hard palate"}, editAndView);
			}else if(descripId==152){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId152(), new String[]{"Temporal","Zygomatic","Buccal","Mandible","Cervical"}, editAndView);
			}else if(descripId==175){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId175(), new String[]{"Anterior","Posterior"}, editAndView);
			}else if(descripId==176){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId176(), new String[]{"Dorsum surface","Ventral surface","Lateral","Anterior &#8532;","Posterior &#8531;","Most posterior part","Tip"}, editAndView);
			}else if(descripId==179){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId179(), new String[]{"Done","Not Done"}, editAndView);
			}else if(descripId==180){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId180(), new String[]{"Done","Not Done"}, editAndView);
			}else if(descripId==177){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId177()+"\">\n";
			}else if(descripId==178){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId178()+"\">\n";
			}else if(descripId==133){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId133(), new String[]{"Lung","Liver","Stomach","Brain","Unknown","Others"}, editAndView);
			}else if(descripId==188){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId188(), new String[]{"Sub mental","Sub mandibular","Middle jugular","Lower jugular","Posterior triangle group","Visceral","Upper anterior mediastinal"}, editAndView);
			}else if(descripId==189){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId189(), new String[]{"Ipsilateral","Bilateral","Contralaterial"}, editAndView);
			}else if(descripId==190){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId190(), new String[]{"less than 3 cm","3-6 cm","more than 6 cm"}, editAndView);
			}else if(descripId==191){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId191(), new String[]{"fixed","mobile"}, editAndView);
			}else if(descripId==192){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId192(), new String[]{"Present","absent"}, editAndView);
			}else if(descripId==193){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId193()+"\">\n";
			}else if(descripId==194){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId194()+"\">\n";
			}else if(descripId==195){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId195(), new String[]{"Nose","Brest","Lung","Bronchus","Colon","Rectum","Stomach","Esophagus","Bladder","Prostate","Testis","Hodgking lymphoma","Non Hodgking lymphoma","Malignant neoplasm","Lymphoid hemopoietic with related tissue","Unknown"}, editAndView);
			}else if(descripId==196){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId196()+"\">\n";
			}else if(descripId==197){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId197()+"\">\n";
			}else if(descripId==198){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId198()+"\">\n";
			}else if(descripId==204){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId204(), new String[]{"Local","Regional","Distant","Unknown"}, editAndView);
			}else if(descripId==205){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId205()+"\">\n";
			}else if(descripId==206){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId206(), new String[]{"Curative","Palliative","Unknown","Refusal of treatment by patient"}, editAndView);
			}else if(descripId==207){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId207_1(), new String[]{"Surgery","Radiotherapy","Chemotherapy","Supportive therapy","Unknown"}, editAndView);
				tail+="&emsp;Others <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId207_2()+"\">\n<br>";
			}else if(descripId==208){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId208(), new String[]{"Osteoradionecrosis","Shoulder syndrome","Lock jaw","Scar"}, editAndView);
			}else if(descripId==209){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId209()+"\">\n";
			}else if(descripId==210){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId210()+"\">\n";
			}else if(descripId==216){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId216(), new String[]{"Palpable","Not palpable"}, editAndView);
			}else if(descripId==217){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId217(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}else if(descripId==218){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId218(), new String[]{"Sub mandibular", "Sub lingual", "Jugulo-digesti", "Per auricular", "Past auricular","Others"}, editAndView);
			}else if(descripId==219){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId219(), new String[]{"Firm","Soft","Flactuate"}, editAndView);
			}else if(descripId==220){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId220(), new String[]{"Present","Absent"}, editAndView);
			}			
			/** Benign cyst /tumor/ swelling **/
			else if(descripId==223){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getSpecialCaseId223_1()+"\">\n X ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId223_2()+"\">\n cm<sup>2</sup><br>";
			}else if(descripId==225){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId225()+"\">\n";
			}else if(descripId==226){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId226(), new String[]{"Smooth", "Lobulated"}, editAndView);
			}else if(descripId==227){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId227(), new String[]{"Single", "Multiple"}, editAndView);
			}else if(descripId==228){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId228(), new String[]{"Clearly define", "Diffuse", "Pedunculated", "Sessile"}, editAndView);
			}else if(descripId==229){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId229(), new String[]{"Over","Swelling","Normal","Reddish","Black/blue"}, editAndView);
			}else if(descripId==232){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId232()+"\">\n";
			}else if(descripId==257){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId257()+"\">\n";
			}else if(descripId==258){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId258()+"\">\n";
			}else if(descripId==259){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId259()+"\">\n";
			}else if(descripId==263){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId263()+"\">\n";
			}else if(descripId==266){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getSpecialCaseId266_1()+"\">\n X ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId266_2()+"\">\n cm<sup>2</sup><br>";
			}else if(descripId==307){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId307()+"\">\n";
			}else if(descripId==308){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId308()+"\">\n";
			}else if(descripId==309){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId309()+"\">\n";
			}else if(descripId==269){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId269()+"\">\n";
			}else if(descripId==311){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId311()+"\">\n";
			}else if(descripId==323){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId323()+"\">\n";
			}else if(descripId==282){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId282()+"\">\n";
			}else if(descripId==267){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId267(), new String[]{"Single","Multiple"}, editAndView);
			}else if(descripId==270){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId270(), new String[]{"Smooth", "Lobulated"}, editAndView);
			}else if(descripId==271){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId271(), new String[]{"Normal", "Raised", "Decrease"}, editAndView);
			}else if(descripId==335){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId335()+"\">\n";
			}else if(descripId==371){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId371()+"\">\n";
			}else if(descripId==338){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getSpecialCaseId338_1()+"\">\n X ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId338_2()+"\">\n cm<sup>2</sup><br>";
			}else if(descripId==339){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId339()+"\">\n";
			}else if(descripId==340){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId340(), new String[]{"Smooth", "Lobulated"}, editAndView);
			}else if(descripId==341){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId341(), new String[]{"Single", "Multiple"}, editAndView);
			}else if(descripId==384){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId384(), new String[]{"Normal", "Pale", "Reddish", "Black", "Blue"}, editAndView);
			}else if(descripId==385){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId385(), new String[]{"Inflamed", "Swollen", "Ulcerated"}, editAndView);
			}else if(descripId==376){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId376_1\" value=\""+patCurDisDTO.getSpecialCaseId376_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId376_2\" value=\""+patCurDisDTO.getSpecialCaseId376_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId376_3\" value=\""+patCurDisDTO.getSpecialCaseId376_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId376_4\" value=\""+patCurDisDTO.getSpecialCaseId376_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==377){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId377_1\" value=\""+patCurDisDTO.getSpecialCaseId377_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId377_2\" value=\""+patCurDisDTO.getSpecialCaseId377_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId377_3\" value=\""+patCurDisDTO.getSpecialCaseId377_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId377_4\" value=\""+patCurDisDTO.getSpecialCaseId377_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==378){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId378_1\" value=\""+patCurDisDTO.getSpecialCaseId378_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId378_2\" value=\""+patCurDisDTO.getSpecialCaseId378_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId378_3\" value=\""+patCurDisDTO.getSpecialCaseId378_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId378_4\" value=\""+patCurDisDTO.getSpecialCaseId378_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==379){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId379_1\" value=\""+patCurDisDTO.getSpecialCaseId379_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId379_2\" value=\""+patCurDisDTO.getSpecialCaseId379_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId379_3\" value=\""+patCurDisDTO.getSpecialCaseId379_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId379_4\" value=\""+patCurDisDTO.getSpecialCaseId379_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==380){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId380_1\" value=\""+patCurDisDTO.getSpecialCaseId380_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId380_2\" value=\""+patCurDisDTO.getSpecialCaseId380_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId380_3\" value=\""+patCurDisDTO.getSpecialCaseId380_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId380_4\" value=\""+patCurDisDTO.getSpecialCaseId380_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==381){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId381_1\" value=\""+patCurDisDTO.getSpecialCaseId381_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId381_2\" value=\""+patCurDisDTO.getSpecialCaseId381_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId381_3\" value=\""+patCurDisDTO.getSpecialCaseId381_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId381_4\" value=\""+patCurDisDTO.getSpecialCaseId381_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==382){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId382_1\" value=\""+patCurDisDTO.getSpecialCaseId382_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId382_2\" value=\""+patCurDisDTO.getSpecialCaseId382_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId382_3\" value=\""+patCurDisDTO.getSpecialCaseId382_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId382_4\" value=\""+patCurDisDTO.getSpecialCaseId382_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==383){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId383_1\" value=\""+patCurDisDTO.getSpecialCaseId383_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId383_2\" value=\""+patCurDisDTO.getSpecialCaseId383_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId383_3\" value=\""+patCurDisDTO.getSpecialCaseId383_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId383_4\" value=\""+patCurDisDTO.getSpecialCaseId383_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==447){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId447_1\" value=\""+patCurDisDTO.getSpecialCaseId447_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId447_2\" value=\""+patCurDisDTO.getSpecialCaseId447_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId447_3\" value=\""+patCurDisDTO.getSpecialCaseId447_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId447_4\" value=\""+patCurDisDTO.getSpecialCaseId447_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==438){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId438_1\" value=\""+patCurDisDTO.getSpecialCaseId438_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId438_2\" value=\""+patCurDisDTO.getSpecialCaseId438_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId438_3\" value=\""+patCurDisDTO.getSpecialCaseId438_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId438_4\" value=\""+patCurDisDTO.getSpecialCaseId438_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==392){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getSpecialCaseId392_1()+"\">\n X ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getSpecialCaseId392_2()+"\">\n cm<sup>2</sup><br>";
			}else if(descripId==393){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId393()+"\">\n";
			}else if(descripId==406){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId406()+"\">\n";
			}else if(descripId==435){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId435()+"\">\n";
			}else if(descripId==436){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId436()+"\">\n";
			}else if(descripId==437){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId437()+"\">\n";
			}else if(descripId==446){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId446()+"\">\n";
			}else if(descripId==451){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId451()+"\">\n";
			}else if(descripId==452){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getSpecialCaseId452()+"\">\n";
			}else if(descripId==395){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId395(), new String[]{"Smooth", "Lobulated"}, editAndView);
			}else if(descripId==396){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId396(), new String[]{"Single", "Nultiple"}, editAndView);
			}else if(descripId==397){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId397(), new String[]{"Clearly define", "Diffuse", "Pedunculated", "Sessile"}, editAndView);
			}else if(descripId==399){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId399(), new String[]{"Normal", "Raised"}, editAndView);
			}else if(descripId==402){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId402(), new String[]{"Partly", "Completely"}, editAndView);
			}else if(descripId==404){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId404(), new String[]{"Expansile pulsation", "Transmitted pulsation"}, editAndView);
			}else if(descripId==405){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId405(), new String[]{"Fixed to mucosa", "Fixed to underlying structure"}, editAndView);
			}else if(descripId==449){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId449(), new String[]{"Nothing", "Scanty", "Small", "Copious"}, editAndView);
			}else if(descripId==450){
				tail=generateSelectBox(name, patCurDisDTO.getSpecialCaseId450(), new String[]{"Rclear pale", "Straw coloured", "Creamy white", "Blood stain", "Bright red blood", "Dark red blood"}, editAndView);
			}else if(descripId==454){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId454_1\" value=\""+patCurDisDTO.getSpecialCaseId454_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId454_2\" value=\""+patCurDisDTO.getSpecialCaseId454_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" style=\"text-align: right\" name=\"specialCaseId454_3\" value=\""+patCurDisDTO.getSpecialCaseId454_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"specialCaseId454_4\" value=\""+patCurDisDTO.getSpecialCaseId454_4()+"\"></td></tr>";
				tail+="</table>";
			}			
		}else if(inputName.equals("inspectionId")){
			tableName = "tbl_patient_disease_inspection";
			String name="inspecDescripId"+descripId;

			if(descripId==4){
				tail="<input type=\"text\" name=\"heightVsPressure\" value=\""+patCurDisDTO.getHeightVsPressure()+"\">\n";
			}else if(descripId==6){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId6(), new String[]{"Normal","Deformed"}, editAndView);
			}else if(descripId==7){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId7(), new String[]{"Left","Right"}, editAndView);
			}else if(descripId==8){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId8(), new String[]{"Abdomen to chest","Chest to abdomen"}, editAndView);
			}else if(descripId==13){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId13(), new String[]{"Small","Medium","Large"}, editAndView);
			}else if(descripId==49){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId49(), new String[]{"Unilateral","Bilateral"}, editAndView);
			}else if(descripId==50){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId50(), new String[]{"Monocular","Bionocular"}, editAndView);
			}else if(descripId==60){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId60(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==61){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId61(), new String[]{"Lateral","Medial","Posterior"}, editAndView);
			}else if(descripId==69){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId69(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==72){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId72()+"\">\n";
			}else if(descripId==74){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId74(), new String[]{"Hypoplasia","Normal"}, editAndView);
			}else if(descripId==75){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId75(), new String[]{"Normal","Reduced","Almost nil"}, editAndView);
			}else if(descripId==77){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId77(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==78){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId78(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==80){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId80(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==81){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId81(), new String[]{"Class-I","Class-II","Class-III"}, editAndView);
			}else if(descripId==84){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId84(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==87){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId87()+"\">\n";
			}else if(descripId==92){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId92(), new String[]{"Ipsilateral","Contralateral","Bilateral"}, editAndView);
			}else if(descripId==93){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId93(), new String[]{"Unilateral ","Bilateral"}, editAndView);
			}else if(descripId==96){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId96(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==99){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId99()+"\">\n";
			}else if(descripId==103){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId103(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==111){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId111()+"\">\n";
			}else if(descripId==112){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId112()+"\">\n";
			}else if(descripId==116){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId116()+"\">\n";
			}else if(descripId==117){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId117()+"\">\n";
			}else if(descripId==118){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId118(), new String[]{"Toxic","ill","dehydrated","air way obstruction","dialation of alanasi"}, editAndView);
			}else if(descripId==119){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId119(), new String[]{"Normal","Red","Black"}, editAndView);
			}else if(descripId==125){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId125(), new String[]{"Single","Multiple"}, editAndView);
			}else if(descripId==134){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId134()+"\">\n";
			}else if(descripId==135){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getInspecDescripId135_1()+"\"> X <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getInspecDescripId135_2()+"\"> c&#13217;";
			}else if(descripId==141){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId141()+"\">\n";
			}else if(descripId==142){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId142()+"\">\n";
			}else if(descripId==137){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId137(), new String[]{"Well defined","Ill define","Defuse","Circumscribed"}, editAndView);
			}else if(descripId==138){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId138(), new String[]{"Right","Left","Bilateral","Middle"}, editAndView);
			}else if(descripId==139){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId139(), new String[]{"Cheek","Upper lip","Temporal region","Sublingual area","Submandibular area","preauricular","TMJ area","Masseter area","Anterior part of neck","Supra clavicular area","Chest","Others"}, editAndView);
			}else if(descripId==140){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId140(), new String[]{"Firm","Fluctuant","Board like","Taut","Pitting","Non pitting","Indurated"}, editAndView);
			}else if(descripId==163){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId163()+"\">\n";
			}
			/** TMJ ankylosis **/
			else if(descripId==148){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId148(), new String[]{"Normal","Reduced", "Nil"}, editAndView);
			}else if(descripId==151){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId151()+"\">\n";
			}else if(descripId==155){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId155()+"\">\n";
			}else if(descripId==156){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId156(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==157){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId157(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==158){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId158(), new String[]{"Right","Left"}, editAndView);
			}else if(descripId==162){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getInspecDescripId162()+"\">\n";
			}else if(descripId==165){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId165() , new String[]{"Swelling","Ulcer","Perforation","Sinus","Necrosis","Osteomilitis","Abscess"}, editAndView);
				tail+="&emsp;Others <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getInspecDescripId165_2()+"\">\n";
			}else if(descripId==166){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId166(), new String[]{"Smooth superficial", "Round", "Mobile", "Lobuleted", "Circumscribed", "Busselation", "Solid cystic", "Soft fluctuant", "Compressible"}, editAndView);
			}else if(descripId==168){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId168(), new String[]{"Exophytic", "Ulceroinfiltrative", "Rerrulouy", "Papillary", "Lodular", "Pigmented", "Deep excavatating ulcer with diffuse peripheral extension", "Leukoplakia"}, editAndView);
				tail+="&emsp;Others <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getInspecDescripId168_2()+"\">\n<br>";
			}else if(descripId==178){
				tail="<div class=\"fourth-child\">Skin size : <input type=\"text\" name=\""+name+"_2"+"\" value=\""+(patCurDisDTO.getInspecDescripId178_2()==null?"":patCurDisDTO.getInspecDescripId178_2())+"\"> X <input type=\"text\" name=\""+name+"_3"+"\" value=\""+(patCurDisDTO.getInspecDescripId178_3()==null?"":patCurDisDTO.getInspecDescripId178_3())+"\"></div>";
				tail+="<div class=\"fourth-child\">Mucosa : <input type=\"text\" name=\""+name+"_4"+"\" value=\""+(patCurDisDTO.getInspecDescripId178_4()==null?"":patCurDisDTO.getInspecDescripId178_4())+"\"> X <input type=\"text\" name=\""+name+"_5"+"\" value=\""+(patCurDisDTO.getInspecDescripId178_5()==null?"":patCurDisDTO.getInspecDescripId178_5())+"\"></div>";
				tail+="<div class=\"fourth-child\">Muscle name:<input type=\"text\" name=\""+name+"_6"+"\" value=\""+(patCurDisDTO.getInspecDescripId178_6()==null?"":patCurDisDTO.getInspecDescripId178_6())+"\"></div>";
			}else if(descripId==290){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId290(), new String[]{"","TX", "T0", "Tis", "T1", "T2", "T3", "T4a", "T4b"}, editAndView);
			}else if(descripId==332){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId332(), new String[]{"","NX", "N0", "N1", "N2", "N2a", "N2b", "N2c", "N3"}, editAndView);
			}else if(descripId==351){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId351(), new String[]{"","MX", "M0", "M1"}, editAndView);
			}else if(descripId==355){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				String val355 = "";
				String tClass [] = new String[]{"","TX", "T0", "Tis", "T1", "T2", "T3", "T4a", "T4b"};
				String nClass [] = new String[]{"","NX", "N0", "N1", "N2", "N2a", "N2b", "N2c", "N3"};
				String mClass [] = new String[]{"","MX", "M0", "M1"};
				if(patCurDisDTO.getInspecDescripId290() != 0 && patCurDisDTO.getInspecDescripId332() != 0 && patCurDisDTO.getInspecDescripId351() != 0)
					val355 = tClass[patCurDisDTO.getInspecDescripId290()-1]+""+nClass[patCurDisDTO.getInspecDescripId332()-1]+""+mClass[patCurDisDTO.getInspecDescripId351() - 1];
					patCurDisDTO.setInspecDescripId355(val355);
				tail="<input type=\"text\" name=\""+name+"\" value=\""+val355+"\" ng-readonly=\"true\">";
			}else if(descripId==356){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				String val = "";
				String tnmVal = patCurDisDTO.getInspecDescripId355();
				if(tnmVal == null || tnmVal.trim().equals("") || tnmVal.trim().equals("null"))
					val = "Incomplete Information";
				else {
					if(tnmVal.trim().equalsIgnoreCase("TisN0M0"))
						val = "Stage 0";
					else if(tnmVal.trim().equalsIgnoreCase("T1N0M0"))
						val = "Stage I";
					else if(tnmVal.trim().equalsIgnoreCase("T2N0M0"))
						val = "Stage II";
					else if(tnmVal.trim().equalsIgnoreCase("T3N0M0") || tnmVal.trim().equalsIgnoreCase("T1N1M0") || tnmVal.trim().equalsIgnoreCase("T2N1M0") || tnmVal.trim().equalsIgnoreCase("T3N1M0"))
						val = "Stage III";
					else if(tnmVal.trim().equalsIgnoreCase("T4aN0M0") || tnmVal.trim().equalsIgnoreCase("T4aN1M0") || tnmVal.trim().equalsIgnoreCase("T1N2M0") || tnmVal.trim().equalsIgnoreCase("T2N2M0") || tnmVal.trim().equalsIgnoreCase("T3N2M0") || tnmVal.trim().equalsIgnoreCase("T4aN2M0"))
						val = "Stage IVA";
					else if((tnmVal.trim().toLowerCase().startsWith("t4b") && tnmVal.trim().toLowerCase().endsWith("m0")) || tnmVal.toLowerCase().trim().endsWith("n3m0"))
						val = "Stage IVB";
					else if(tnmVal.trim().toLowerCase().endsWith("m1"))
						val = "Stage IVC";
					else
						val = "No Rules Defined";
				}
				patCurDisDTO.setInspecDescripId356(val);
				tail="<input type=\"text\" name=\""+name+"\" value=\""+val+"\" ng-readonly=\"true\">";
			}
			// Orthognathic Surgery
			else if(descripId==532){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId532(), new String[]{"right", "left", "both"}, editAndView);
			}else if(descripId==533){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId533(), new String[]{"right", "left", "both"}, editAndView);
			}else if(descripId==534){
				tail=generateSelectBox(name, patCurDisDTO.getInspecDescripId534(), new String[]{"right", "left", "both"}, editAndView);
			}else if(descripId==487){
				System.out.println("*************** 487 = "+patCurDisDTO.getInspecDescripId487_1()+"*********************");
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId487_1\" value=\""+patCurDisDTO.getInspecDescripId487_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId487_2\" value=\""+patCurDisDTO.getInspecDescripId487_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId487_3\" value=\""+patCurDisDTO.getInspecDescripId487_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId487_4\" value=\""+patCurDisDTO.getInspecDescripId487_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==488){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId488_1\" value=\""+patCurDisDTO.getInspecDescripId488_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId488_2\" value=\""+patCurDisDTO.getInspecDescripId488_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId488_3\" value=\""+patCurDisDTO.getInspecDescripId488_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId488_4\" value=\""+patCurDisDTO.getInspecDescripId488_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==489){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId489_1\" value=\""+patCurDisDTO.getInspecDescripId489_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId489_2\" value=\""+patCurDisDTO.getInspecDescripId489_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId489_3\" value=\""+patCurDisDTO.getInspecDescripId489_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId489_4\" value=\""+patCurDisDTO.getInspecDescripId489_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==490){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId490_1\" value=\""+patCurDisDTO.getInspecDescripId490_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId490_2\" value=\""+patCurDisDTO.getInspecDescripId490_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"inspecDescripId490_3\" value=\""+patCurDisDTO.getInspecDescripId490_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"inspecDescripId490_4\" value=\""+patCurDisDTO.getInspecDescripId490_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==536){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getInspecDescripId536_1()+"\">\n mm/Discrepancy ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getInspecDescripId536_2()+"\">\n <br>";
			}else if(descripId==537){
				tail="<input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getInspecDescripId537_1()+"\">\n mm/Discrepancy ";
				tail+="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getInspecDescripId537_2()+"\">\n <br>";
			}
		}else if(inputName.equals("diagnosisId")){
			tableName = "tbl_patient_disease_diagnosis";
			String name="diagnosisId"+descripId;
			if(descripId==18){
				tail=generateSelectBox(name, patCurDisDTO.getDiagnosisId18(), new String[]{"High","Low"}, editAndView);
			}else if(descripId==17){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId17()+"\">\n";
			}else if(descripId==19){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId19()+"\">\n";
			}else if(descripId==20){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId20()+"\">\n";
			}
			/** TMJ ankylosis **/
			else if(descripId==23){
				tail=generateSelectBox(name, patCurDisDTO.getDiagnosisId23(), new String[]{"Bony","Fibrous"}, editAndView);
			}else if(descripId==24){
				tail=generateSelectBox(name, patCurDisDTO.getDiagnosisId24(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}
			/** Benign cyst /tumor/ swelling **/
			else if(descripId==47){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId47()+"\">\n";
			}else if(descripId==62){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId62()+"\">\n";
			}else if(descripId==63){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId63()+"\">\n";
			}else if(descripId==75){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId75()+"\">\n";
			}else if(descripId==87){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId87()+"\">\n";
			}else if(descripId==90){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId90()+"\">\n";
			}else if(descripId==100){
				tail=generateSelectBox(name, patCurDisDTO.getDiagnosisId100(), new String[]{"Type-I","Type-II","Type-III","Type-IV","Type-V"}, editAndView);
			}else if(descripId==101){
				tail=generateSelectBox(name, patCurDisDTO.getDiagnosisId101(), new String[]{"Type-I","Type-II","Type-III"}, editAndView);
			}else if(descripId==102){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId102()+"\">\n";
			}else if(descripId==103){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId103()+"\">\n";
			}else if(descripId==104){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId104()+"\">\n";
			}else if(descripId==105){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId105()+"\">\n";
			}else if(descripId==106){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId106()+"\">\n";
			}else if(descripId==107){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId107()+"\">\n";
			}else if(descripId==108){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId108()+"\">\n";
			}else if(descripId==109){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId109()+"\">\n";
			}else if(descripId==110){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId110()+"\">\n";
			}else if(descripId==111){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId111()+"\">\n";
			}else if(descripId==112){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId112()+"\">\n";
			}else if(descripId==129){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getDiagnosisId129()+"\">\n";
			}
		}else if(inputName.equals("treatmentId")){
			tableName = "tbl_patient_disease_treatment";
			String name="treatmentId"+descripId;
			if(descripId==5){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId5()+"\">\n";
			}else if(descripId==9){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId9()+"\">\n";
			}else if(descripId==11){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId11(), new String[]{"Type-I","Type-II","Type-III"}, editAndView);
			}else if(descripId==13){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId13(), new String[]{"Supra omohyoid","Extended omohyoid","Pestolateral","Lateral","Central"}, editAndView);
			}else if(descripId==14){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId14(), new String[]{"Primary Clause","Regional Flap","Distal Flap","Free Flap"}, editAndView);
			}else if(descripId==15){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId15()+"\"> /CG\n";
			}else if(descripId==18){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId18()+"\">\n";
			}else if(descripId==34){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId34(), new String[]{"Essig","Gilma","Risdon","Ivy eyelet","Col. Stoutis multiloop"}, editAndView);
			}else if(descripId==35){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId35()+"\">\n";
			}else if(descripId==36){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId36(), new String[]{"Lateral Compression","Gunning","Cap Splint"}, editAndView);
			}else if(descripId==37){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId37()+"\">\n";
			}else if(descripId==38){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId38(), new String[]{"Micro Plate","Mini Plate","Reconstruction Plate","Bio- degradable Plate"}, editAndView);
			}else if(descripId==39){
				tail=" Mandibular pin fixation <input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId39()+"\">\n";
			}
			/** TMJ ankylosis **/
			else if(descripId==40){
				tail=generateSelectBox(name, patCurDisDTO.getTreatmentId40(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}else if(descripId==41){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId41()+"\">\n";
			}else if(descripId==48){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId48()+"\">\n";
			}else if(descripId==68){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId68()+"\">\n";
			}else if(descripId==77){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId77()+"\">\n";
			}else if(descripId==82){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId82()+"\">\n";
			}else if(descripId==83){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId83()+"\">\n";
			}else if(descripId==84){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId84()+"\">\n";
			}
			/** Benign cyst /tumor/ swelling **/
			else if(descripId==61){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId61()+"\">\n";
			}else if(descripId==118){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId118()+"\">\n";
			}
			
			else if(descripId==157){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId157()+"\">\n";
			}else if(descripId==167){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getTreatmentId167()+"\">\n";
			}
		}else if(inputName.equals("auscultationId")){
			tableName="tbl_patient_disease_auscultation";
			String name="auscuDescripId"+descripId;
			if(descripId==7){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getAuscuDescripId7()+"\">\n";
			}
		}else if(inputName.equals("historyId")){
			tableName = "tbl_patient_disease_history";
			String name="hisDescripId"+descripId;
			if(descripId==54){
				tail="<br> &emsp;&emsp;&emsp;Last date of dialysis <input type=\"text\" name=\"lastDateOfDialysis\" value=\""+patCurDisDTO.getLastDateOfDialysis()+"\">&emsp;";
				tail+="Frequency of dialysis <input type=\"text\" name=\"frequencyOfDialysis\" value=\""+patCurDisDTO.getFrequencyOfDialysis()+"\">\n";
			}else if(descripId==79){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId79(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==80){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId80(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==81){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId81(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==82){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId82(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==83){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId83(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==84){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId84(), new String[]{"Present","Absent","During mastication","Psychic stimulation","Sudden","gradually"}, editAndView);
			}else if(descripId==85){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId85(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==86){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId86()+"\">\n";
			}else if(descripId==87){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId87(), new String[]{"Unilateral","Bilateral"}, editAndView);
			}else if(descripId==88){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId88(), new String[]{"Well circumscribed","Difference"}, editAndView);
			}else if(descripId==89){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId89(), new String[]{"Firm","Fluctuant","Pitting","Non pitting"}, editAndView);
			}else if(descripId==90){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId90(), new String[]{"Fixed","Freely mobile"}, editAndView);
			}else if(descripId==91){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId91(), new String[]{"Tongue","Ear lobe"}, editAndView);
			}else if(descripId==92){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId92(), new String[]{"Normal","Reduced","watery","Thick","Purulent"}, editAndView);
			}else if(descripId==93){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId93()+"\">\n";
			}else if(descripId==106){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId106()+"\">\n";
			}else if(descripId==131){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId131()+"\">\n";
			}else if(descripId==136){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId136()+"\">\n";
			}else if(descripId==139){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId139()+"\">\n";
			}else if(descripId==296){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				tail = generateSelectBox("eyeOpening", patCurDisDTO.getEyeOpening(), new String[]{"","1","2","3","4"}, editAndView);
			}else if(descripId==297){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				tail = generateSelectBox("verbalResponse", patCurDisDTO.getVerbalResponse(), new String[]{"","1","2","3","4","5"}, editAndView);
			}else if(descripId==298){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				tail = generateSelectBox("motorResponse", patCurDisDTO.getMotorResponse(), new String[]{"","1","2","3","4","5","6"}, editAndView);
			}else if(descripId==299){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				String val = "";
				if(patCurDisDTO.getEyeOpening() != 1 && patCurDisDTO.getVerbalResponse() != 1 && patCurDisDTO.getMotorResponse() != 1){
					val = ""+(patCurDisDTO.getEyeOpening()+patCurDisDTO.getVerbalResponse()+patCurDisDTO.getMotorResponse()-3);
				}
				if(val.trim().startsWith("-") == Boolean.TRUE)
					val = "";
				patCurDisDTO.setTotalScore(val);
				tail = "<input readonly=\"readonly\" type=\"text\" name=\"totalScore\" value=\""+val+"\">\n";
			}else if(descripId==300){
				if(str1.contains(" checked=\"checked\" ")==Boolean.FALSE)
					str1+=" checked=\"checked\" ";
				String val = "";
				String totalScore = patCurDisDTO.getTotalScore();
				if(totalScore.trim().equalsIgnoreCase(""))
					val = "Incomplete Information";
				else if(Integer.parseInt(totalScore.trim()) >= 3 && Integer.parseInt(totalScore.trim()) <= 8)
					val = "Severe brain injury";
				else if(Integer.parseInt(totalScore.trim()) >= 9 && Integer.parseInt(totalScore.trim()) <= 13)
					val = "Moderate brain injury";
				else if(Integer.parseInt(totalScore.trim()) >= 14 && Integer.parseInt(totalScore.trim()) <= 15)
					val = "Mild brain injury";
				patCurDisDTO.setComments(val);
				tail = "<input readonly=\"readonly\" type=\"text\" name=\"comments\" value=\""+val+"\">\n";
			}else if(descripId==141){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId141_1(), new String[]{"Airway obstruction","Breathing difficulty","Tracheal position shifting","Flail chest","Cardiac temponade","Hemothorax","Pneumothorax"}, editAndView);
				tail+="<br>&emsp;&emsp;Others <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getHisDescripId141_2()+"\">\n";
			}else if(descripId==142){
				tail+="<br>&emsp;&emsp;Mechanism of injury <input type=\"text\" name=\"mechanismOfInjury\" value=\""+patCurDisDTO.getMechanismOfInjury() +"\">\n";
				tail+="<br>&emsp;&emsp;Reflex <input type=\"text\" name=\"reflex\" value=\""+patCurDisDTO.getReflex() +"\">\n";
				tail+="<br>&emsp;&emsp;Motor function <input type=\"text\" name=\"motorFunction\" value=\""+patCurDisDTO.getMotorFunction() +"\">\n";
				tail+="<br>&emsp;&emsp;Mental status <input type=\"text\" name=\"mentalStatus\" value=\""+patCurDisDTO.getMentalStatus() +"\">\n";
				tail+="<br>&emsp;&emsp;Rectal tone & bladder control <input type=\"text\" name=\"rectalTone\" value=\""+patCurDisDTO.getRectalTone() +"\">\n";
				tail+="<br>&emsp;&emsp;Other <input type=\"text\" name=\"omSpninalCordOther\" value=\""+patCurDisDTO.getOmSpninalCordOther() +"\">\n";
			}else if(descripId==148){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId148()+"\">\n";
			}else if(descripId==162){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId162(), new String[]{"Blunt","Penetrating"}, editAndView);
			}else if(descripId==165){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId165()+"\">\n";
			}else if(descripId==167){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId167()+"\">\n";
			}else if(descripId==168){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId168()+"\">\n";
			}else if(descripId==169){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId169()+"\">\n";
			}else if(descripId==170){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId170(), new String[]{"Open","Close","Contaminated"}, editAndView);
			}else if(descripId==171){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId171(), new String[]{"Pelvic","Upper limb","Lower limb"}, editAndView);
			}else if(descripId==173){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId173()+"\">\n";
			}
			/** Maxillary sinusitis Ch/Acute, Fistula **/
			else if(descripId==174){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId174(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==175){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId175(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==176){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId176(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==177){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId177(), new String[]{"Present","Absent"}, editAndView);
			}else if(descripId==178){
				tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"hisDescripId178_1\" value=\""+patCurDisDTO.getHisDescripId178_1()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"hisDescripId178_2\" value=\""+patCurDisDTO.getHisDescripId178_2()+"\"></td></tr>";
				tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\"hisDescripId178_3\" value=\""+patCurDisDTO.getHisDescripId178_3()+"\"></td><td><input type=\"text\" class=\"input-small\" name=\"hisDescripId178_4\" value=\""+patCurDisDTO.getHisDescripId178_4()+"\"></td></tr>";
				tail+="</table>";
			}else if(descripId==181){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId181()+"\">\n";
			}else if(descripId==194){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId194()+"\">\n";
			}else if(descripId==195){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId195()+"\">\n";
			}else if(descripId==196){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId196()+"\">\n";
			}else if(descripId==198){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId198(), new String[]{"Normal","Impaired"}, editAndView);
			}else if(descripId==204){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId204(), new String[]{"Nil","Minimal","Dull throbbing","Lancenating","Sever","Interfering with sleep","Intense"}, editAndView);
			}else if(descripId==211){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId211(), new String[]{"Sudden","Gradually"}, editAndView);
			}else if(descripId==219){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId219(), new String[]{"Primary","Secondary","Recurrence"}, editAndView);
			}else if(descripId==222){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId222()+"\">\n";
			}else if(descripId==223){
				tail+="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId223()+"\">\n";
			}else if(descripId==224){
				tail="<input type=\"text\" name=\"frequency\" value=\""+patCurDisDTO.getFrequency()+"\">\n";
			}else if(descripId==225){
				tail="<input type=\"text\" name=\"severity\" value=\""+patCurDisDTO.getSeverity()+"\">\n";
			}else if(descripId==226){
				tail="<input type=\"text\" name=\"duration\" value=\""+patCurDisDTO.getDuration()+"\">\n";
			}else if(descripId==227){
				tail="<input type=\"text\" name=\"periodOfRemission\" value=\""+patCurDisDTO.getPeriodOfRemission()+"\">\n";
			}else if(descripId==232){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getHisDescripId232()+"\">\n";
			}else if(descripId==229){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId229(), new String[]{"Sudden","Gradual","Recurrent"}, editAndView);
			}else if(descripId==230){
				tail=generateSelectBox(name, patCurDisDTO.getHisDescripId230(), new String[]{"Slow growing", "Rapid growing", "Slow growing than rapid"}, editAndView);
			}
		}else if(inputName.equals("prticpFactId")){
			if(descripId==9){
				tail="<input type=\"text\" name=\"otherPertFact\" value=\""+patCurDisDTO.getOtherPertFact()+"\">\n";
			}
		}else if(inputName.equals("othersId")){
			tableName = "tbl_patient_disease_others";
			String name="othsDescripId"+descripId;
			if(descripId==1){
				tail=generateSelectBox(name, patCurDisDTO.getOthsDescripId1(), new String[]{"Skin","Mucosa","Bluish","Radish","Sinus opening","Ulcer","Normal"}, editAndView);
			}else if(descripId==2){
				tail=generateSelectBox(name, patCurDisDTO.getOthsDescripId2(), new String[]{"Sinus","Duct"}, editAndView);
			}else if(descripId==3){
				tail=generateSelectBox(name, patCurDisDTO.getOthsDescripId3(), new String[]{"Puffy, red","Normal"}, editAndView);
			}else if(descripId==4){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId4()+"\">\n";
			}else if(descripId==8){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId8()+"\">\n";
			}else if(descripId==12){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId12()+"\">\n";
			}else if(descripId==13){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId13()+"\">\n";
			}else if(descripId==14){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId14()+"\">\n";
			}else if(descripId==15){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId15()+"\">\n";
			}else if(descripId==16){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId16()+"\">\n";
			}else if(descripId==17){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId17()+"\">\n";
			}else if(descripId==20){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId20()+"\">\n";
			}else if(descripId==21){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId21()+"\">\n";
			}else if(descripId==22){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getOthsDescripId22()+"\">\n";
			}else if(descripId==25){
				tail=generateSelectBox(name, patCurDisDTO.getOthsDescripId25(), new String[]{"A","B","C"}, editAndView);
			}
			
		}else if(inputName.equals("palpationId")){
			tableName = "tbl_patient_disease_palpation";
			String name="palpaDescripId"+descripId;
			if(descripId==1){
				tail="<input type=\"text\" name=\"heartRatePerMin\" value=\""+patCurDisDTO.getHeartRatePerMin()+"\">\n";
			}else if(descripId==4){
				tail="<input type=\"text\" name=\"bloodPressure\" value=\""+patCurDisDTO.getBloodPressure()+"\">\n";
			}else if(descripId==7){
				tail="<input type=\"text\" name=\"apexBeat\" value=\""+patCurDisDTO.getApexBeat()+"\">\n";
			}else if(descripId==22){
				tail="<br>&emsp;&emsp;Location <input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getPalpaDescripId22_1()+"\">\n";
				tail+="<br>&emsp;&emsp;Nature <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getPalpaDescripId22_2()+"\">\n";
			}else if(descripId==27){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId27()+"\">\n";
			}else if(descripId==28){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId28()+"\">\n";
			}else if(descripId==53){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId53(), new String[]{"Normal","Traumatic telecanthus"}, editAndView);
			}else if(descripId==56){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId56(), new String[]{"Left","Right","Bilateral"}, editAndView);
			}else if(descripId==57){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId57(), new String[]{"Left","Right"}, editAndView);
			}else if(descripId==58){
				tail="<br>&emsp;&emsp;Area <input type=\"text\" name=\""+name+"_1"+"\" value=\""+patCurDisDTO.getPalpaDescripId58_1()+"\">\n";
				tail+="<br>&emsp;&emsp;Extension <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patCurDisDTO.getPalpaDescripId58_2()+"\">\n";
			}else if(descripId==65){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId65(), new String[]{"Hard","Firm","Soft"}, editAndView);
			}else if(descripId==66){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId66(), new String[]{"During closing","During opening"}, editAndView);
			}else if(descripId==67){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId67_1()+"\">\n";
				tail+="<br>&emsp;&emsp;&emsp;&emsp;";
				tail+=generateSelectBox(name, patCurDisDTO.getPalpaDescripId67_2(), new String[]{"Inflamed","Stiffness","Hard"}, editAndView);
			}else if(descripId==71){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId71(), new String[]{"Left","Right"}, editAndView);
			}else if(descripId==72){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId72()+"\">\n";
			}else if(descripId==73){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId73()+"\">\n";
			}else if(descripId==74){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId74()+"\">\n";
			}else if(descripId==75){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId75()+"\">\n";
			}else if(descripId==88){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId88()+"\"> cm\n";
			}else if(descripId==90){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId90(), new String[]{"Raised","Normal"}, editAndView);
			}else if(descripId==115){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId115()+"\"> mm\n";
			}else if(descripId==100){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId100()+"\">\n";
			}else if(descripId==93){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId93(), new String[]{"Normal","Enlarge"}, editAndView);
			}
			/** TMJ ankylosis **/
			else if(descripId==107){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId107(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}else if(descripId==108){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patCurDisDTO.getPalpaDescripId108()+"\">\n";
			}else if(descripId==112){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId112(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}else if(descripId==113){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId113(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}else if(descripId==114){
				tail=generateSelectBox(name, patCurDisDTO.getPalpaDescripId114(), new String[]{"Right","Left","Bilateral"}, editAndView);
			}
		}else if(inputName.equals("symptomId")) {
			tableName = "tbl_patient_disease_symptom";
		}

		String fieldName = tableName+"-"+descripId+"-";
		if("TEXT_AREA".equals(metaData.getInputType())){
			DiseaseMetaValues metaValues = new DiseaseMetaValues();
			if(patCurDisDTO.getValues().get(tableName+"-"+descripId)!=null){
				metaValues = patCurDisDTO.getValues().get(tableName+"-"+descripId);
			}
			return metaData.getName()+ ": <textarea type=\"text\" class=\"form-control\" rows=\"2\" name=\""+fieldName+"input_value\">"+metaValues.getParam1()+"</textarea>";
		}else if("INPUT_TEXT".equals(metaData.getInputType())){
			DiseaseMetaValues metaValues = new DiseaseMetaValues();
			if(patCurDisDTO.getValues().get(tableName+"-"+descripId)!=null){
				metaValues = patCurDisDTO.getValues().get(tableName+"-"+descripId);
			}
			return metaData.getName()+ ": <input type=\"text\" name=\""+fieldName+"input_value\" value=\""+metaValues.getParam1()+"\"> ";
		}else if("OTHER_TEXT".equals(metaData.getInputType())){
			DiseaseMetaValues metaValues = new DiseaseMetaValues();
			if(patCurDisDTO.getValues().get(tableName+"-"+descripId)!=null){
				metaValues =  patCurDisDTO.getValues().get(tableName+"-"+descripId);
			}
			return "<input type=\"checkbox\" " + str1 + " name=\"" + fieldName + "input_value\" value=\"" + descripId + "\"> " + metaData.getName()+ ": <input type=\"text\" name=\""+fieldName+"input_value_2\" value=\""+metaValues.getParam2()+"\"> ";
		}else {
			return "<input type=\"checkbox\" " + str1 + " name=\"" + inputName + "\" value=\"" + descripId + "\"> " + metaData.getName() + "\n" + tail;
		}
	}
	
	
	/*public static String generateHTML(HashMap<Integer, String> disHistoryList, HashMap<Integer, String> parentByChild, String inputName, HashSet<Integer> patCurDisInfo, DiseaseDTO patCurDisDTO){
		HashSet<Integer> insertedId = new HashSet<Integer>();
		String html="";
		int spaceingColumn=40;
		
		int[] disKeySet = getSortedKeyList(disHistoryList);
		for(int index=0; index<disKeySet.length; index++){
			if(insertedId.contains(disKeySet[index])){
				continue;
			}
			html+="<tr>\n";
			//html+="<td class=\"tableData2\">\n";
			html+="<td style=\"border: none;\">\n";
			if(parentByChild.get(disKeySet[index])!=null){
				insertedId.add(disKeySet[index]);
				
				html+=getInputString(inputName, -1, disKeySet[index], disHistoryList.get(disKeySet[index]), patCurDisInfo.contains(disKeySet[index]), patCurDisDTO);
				
				String[] mapValue1=parentByChild.get(disKeySet[index]).split(",");
				for(int i=0;i<mapValue1.length;i++){
					int idValue1=Integer.parseInt(mapValue1[i]);
					insertedId.add(idValue1);
					html+="<br>\n";
					html+=getInputString(inputName, spaceingColumn, idValue1, disHistoryList.get(idValue1), patCurDisInfo.contains(idValue1), patCurDisDTO);
					if(parentByChild.get(idValue1)!=null){
						String[] mapValue2=parentByChild.get(idValue1).split(",");
						for(int j=0;j<mapValue2.length;j++){
							int idValue2=Integer.parseInt(mapValue2[j]);
							insertedId.add(idValue2);
							html+="<br>\n";
							System.out.println("disHistoryList.get(idValue2): "+disHistoryList.get(idValue2));
							html+=getInputString(inputName, (spaceingColumn*2), idValue2, disHistoryList.get(idValue2), patCurDisInfo.contains(idValue2), patCurDisDTO);
						}
					}
				}
			}else{
				insertedId.add(disKeySet[index]);
				html+=getInputString(inputName, -1, disKeySet[index], disHistoryList.get(disKeySet[index]), patCurDisInfo.contains(disKeySet[index]), patCurDisDTO);				
			}
			html+="</td>\n";
			html+="</tr>\n";
		}
		return html;
	}*/
	
	
	public static String generateHTML(HashMap<Integer, DiseaseMetaData> disHistoryList, HashMap<Integer, String> parentByChild, String inputName, HashSet<Integer> patCurDisInfo, DiseaseDTO patCurDisDTO, boolean editAndView){
		HashSet<Integer> insertedId = new HashSet<Integer>();
		String html="";
		int spaceingColumn=40;
		
		int[] disKeySet = getSortedKeyList(disHistoryList);
		for(int index=0; index<disKeySet.length; index++){
			if(insertedId.contains(disKeySet[index])){
				continue;
			}
			if(editAndView==false && patCurDisInfo.contains(disKeySet[index])==false){
				continue;
			}
			html+="<tr>\n";
			html+="<td style=\"border: none;\">\n";
			if(parentByChild.get(disKeySet[index])!=null){
				insertedId.add(disKeySet[index]);
				
				html+=getInputString(inputName, -1, disKeySet[index], disHistoryList.get(disKeySet[index]), patCurDisInfo.contains(disKeySet[index]), patCurDisDTO, parentByChild.get(disKeySet[index])!=null?true:false, editAndView);
				
				String[] mapValue1=parentByChild.get(disKeySet[index]).split(",");
				if(mapValue1.length>0){
					String styleDisplay="style=\"display:none;\"";
					if(patCurDisInfo.contains(disKeySet[index])){
						styleDisplay="style=\"display:block;\"";
					}
					if(editAndView){
						html+="<i class=\"fa fa-chevron-circle-right\"></i>";
					}
					html+="<div "+styleDisplay+" id=\"block"+inputName+disKeySet[index]+"\">\n";
				}
				for(int i=0;i<mapValue1.length;i++){
					int idValue1=Integer.parseInt(mapValue1[i]);
					insertedId.add(idValue1);
					if(editAndView==false && patCurDisInfo.contains(idValue1)==false){
						continue;
					}
					html+="<br>\n";
					html+=getInputString(inputName, spaceingColumn, idValue1, disHistoryList.get(idValue1), patCurDisInfo.contains(idValue1), patCurDisDTO, parentByChild.get(idValue1)!=null?true:false, editAndView);
					if(parentByChild.get(idValue1)!=null){
						String[] mapValue2=parentByChild.get(idValue1).split(",");
						if(mapValue2.length>0){
							String styleDisplay="style=\"display:none;\"";
							if(patCurDisInfo.contains(idValue1)){
								styleDisplay="style=\"display:block;\"";
							}
							if(editAndView){
								html+="<i class=\"fa fa-chevron-circle-right\"></i>";
							}
							html+="<div "+styleDisplay+" id=\"block"+inputName+idValue1+"\">\n";
						}
						for(int j=0;j<mapValue2.length;j++){
							int idValue2=Integer.parseInt(mapValue2[j]);
							insertedId.add(idValue2);
							if(editAndView==false && patCurDisInfo.contains(idValue2)==false){
								continue;
							}
							html+="<br>\n";
							html+=getInputString(inputName, (spaceingColumn*2), idValue2, disHistoryList.get(idValue2), patCurDisInfo.contains(idValue2), patCurDisDTO, parentByChild.get(idValue2)!=null?true:false, editAndView);
							
							
							//new
							if(parentByChild.get(idValue2)!=null){
								String[] mapValue3=parentByChild.get(idValue2).split(",");
								if(mapValue3.length>0){
									String styleDisplay="style=\"display:none;\"";
									if(patCurDisInfo.contains(idValue2)){
										styleDisplay="style=\"display:block;\"";
									}
									if(editAndView){
										html+="<i class=\"fa fa-chevron-circle-right\"></i>";
									}
									html+="<div "+styleDisplay+" id=\"block"+inputName+idValue2+"\">\n";
								}
								for(int k=0;k<mapValue3.length;k++){
									int idValue3=Integer.parseInt(mapValue3[k]);
									insertedId.add(idValue3);
									html+="<br>\n";
									html+=getInputString(inputName, (spaceingColumn*3), idValue3, disHistoryList.get(idValue3), patCurDisInfo.contains(idValue3), patCurDisDTO, parentByChild.get(idValue3)!=null?true:false, editAndView);
									
									//new 2
									if(parentByChild.get(idValue3)!=null){
										String[] mapValue4=parentByChild.get(idValue3).split(",");
										if(mapValue4.length>0){
											String styleDisplay="style=\"display:none;\"";
											if(patCurDisInfo.contains(idValue3)){
												styleDisplay="style=\"display:block;\"";
											}
											if(editAndView){
												html+="<i class=\"fa fa-chevron-circle-right\"></i>";
											}
											html+="<div "+styleDisplay+" id=\"block"+inputName+idValue3+"\">\n";
										}
										for(int l=0;l<mapValue4.length;l++){
											int idValue4=Integer.parseInt(mapValue4[l]);
											insertedId.add(idValue4);
											html+="<br>\n";
											html+=getInputString(inputName, (spaceingColumn*4), idValue4, disHistoryList.get(idValue4), patCurDisInfo.contains(idValue4), patCurDisDTO, false, editAndView);
											
											
										}
										if(mapValue4.length>0){
											html+="</div>\n";
										}
									}
								}
								if(mapValue3.length>0){
									html+="</div>\n";
								}
							}
						}
						
						if(mapValue2.length>0){
							html+="</div>\n";
						}
						
					}	
				}
				if(mapValue1.length>0){
					html+="</div>\n";
				}
			}else{
				insertedId.add(disKeySet[index]);
				html+=getInputString(inputName, -1, disKeySet[index], disHistoryList.get(disKeySet[index]), patCurDisInfo.contains(disKeySet[index]), patCurDisDTO, false, editAndView);
			}
			html+="</td>\n";
			html+="</tr>\n";
		}
		return html;
	}
	
	public static String generateHTML(HashMap<Integer, DiseaseMetaData> disHistoryList, HashMap<Integer, String> parentByChild, String inputName, HashSet<Integer> patCurDisInfo, PatientOthersDTO patOthersDTO){
		HashSet<Integer> insertedId = new HashSet<Integer>();
		String html="";
		int spaceingColumn=40;
		
		int[] disKeySet = getSortedKeyList(disHistoryList);
		for(int index=0; index<disKeySet.length; index++){
			if(insertedId.contains(disKeySet[index])){
				continue;
			}
			html+="<tr>\n";
			html+="<td style=\"border: none;\">\n";
			if(parentByChild.get(disKeySet[index])!=null){
				insertedId.add(disKeySet[index]);
				
				html+=getInputString(inputName, -1, disKeySet[index], disHistoryList.get(disKeySet[index]), patCurDisInfo.contains(disKeySet[index]), patOthersDTO);
				
				String[] mapValue1=parentByChild.get(disKeySet[index]).split(",");
				for(int i=0;i<mapValue1.length;i++){
					int idValue1=Integer.parseInt(mapValue1[i]);
					insertedId.add(idValue1);
					html+="<br>\n";
					html+=getInputString(inputName, spaceingColumn, idValue1, disHistoryList.get(idValue1), patCurDisInfo.contains(idValue1), patOthersDTO);
					if(parentByChild.get(idValue1)!=null){
						String[] mapValue2=parentByChild.get(idValue1).split(",");
						for(int j=0;j<mapValue2.length;j++){
							int idValue2=Integer.parseInt(mapValue2[j]);
							insertedId.add(idValue2);
							html+="<br>\n";
							html+=getInputString(inputName, (spaceingColumn*2), idValue2, disHistoryList.get(idValue2), patCurDisInfo.contains(idValue2), patOthersDTO);
						}
					}
				}
			}else{
				insertedId.add(disKeySet[index]);
				html+=getInputString(inputName, -1, disKeySet[index], disHistoryList.get(disKeySet[index]), patCurDisInfo.contains(disKeySet[index]), patOthersDTO);				
			}
			html+="</td>\n";
			html+="</tr>\n";
		}
		return html;
	}
	
	
	
	public static String generateHTMLForDeptDisList(HashMap<Integer, String> disList, HashSet<Integer> patCurDisInfo, DepartmentDiseaseDTO patDeptDisInfoDTO, boolean editAndView){
		String html="", str1="", str2="", str3="", str4="";
		for(int key : disList.keySet()){
			if(key==1){
				str1=patDeptDisInfoDTO.getDiseaseId1_1();str2=patDeptDisInfoDTO.getDiseaseId1_2();
				str3=patDeptDisInfoDTO.getDiseaseId1_3();str4=patDeptDisInfoDTO.getDiseaseId1_4();
			}else if(key==2){
				str1=patDeptDisInfoDTO.getDiseaseId2_1();str2=patDeptDisInfoDTO.getDiseaseId2_2();
				str3=patDeptDisInfoDTO.getDiseaseId2_3();str4=patDeptDisInfoDTO.getDiseaseId2_4();
			}else if(key==3){
				str1=patDeptDisInfoDTO.getDiseaseId3_1();str2=patDeptDisInfoDTO.getDiseaseId3_2();
				str3=patDeptDisInfoDTO.getDiseaseId3_3();str4=patDeptDisInfoDTO.getDiseaseId3_4();
			}else if(key==4){
				str1=patDeptDisInfoDTO.getDiseaseId4_1();str2=patDeptDisInfoDTO.getDiseaseId4_2();
				str3=patDeptDisInfoDTO.getDiseaseId4_3();str4=patDeptDisInfoDTO.getDiseaseId4_4();
			}else if(key==5){
				str1=patDeptDisInfoDTO.getDiseaseId5_1();str2=patDeptDisInfoDTO.getDiseaseId5_2();
				str3=patDeptDisInfoDTO.getDiseaseId5_3();str4=patDeptDisInfoDTO.getDiseaseId5_4();
			}else if(key==6){
				str1=patDeptDisInfoDTO.getDiseaseId6_1();str2=patDeptDisInfoDTO.getDiseaseId6_2();
				str3=patDeptDisInfoDTO.getDiseaseId6_3();str4=patDeptDisInfoDTO.getDiseaseId6_4();
			}else if(key==7){
				str1=patDeptDisInfoDTO.getDiseaseId7_1();str2=patDeptDisInfoDTO.getDiseaseId7_2();
				str3=patDeptDisInfoDTO.getDiseaseId7_3();str4=patDeptDisInfoDTO.getDiseaseId7_4();
			}else if(key==8){
				str1=patDeptDisInfoDTO.getDiseaseId8_1();str2=patDeptDisInfoDTO.getDiseaseId8_2();
				str3=patDeptDisInfoDTO.getDiseaseId8_3();str4=patDeptDisInfoDTO.getDiseaseId8_4();
			}else if(key==9){
				str1=patDeptDisInfoDTO.getDiseaseId9_1();str2=patDeptDisInfoDTO.getDiseaseId9_2();
				str3=patDeptDisInfoDTO.getDiseaseId9_3();str4=patDeptDisInfoDTO.getDiseaseId9_4();
			}else if(key==10){
				str1=patDeptDisInfoDTO.getDiseaseId10_1();str2=patDeptDisInfoDTO.getDiseaseId10_2();
				str3=patDeptDisInfoDTO.getDiseaseId10_3();str4=patDeptDisInfoDTO.getDiseaseId10_4();
			}else if(key==11){
				str1=patDeptDisInfoDTO.getDiseaseId11_1();str2=patDeptDisInfoDTO.getDiseaseId11_2();
				str3=patDeptDisInfoDTO.getDiseaseId11_3();str4=patDeptDisInfoDTO.getDiseaseId11_4();
			}else if(key==12){
				str1=patDeptDisInfoDTO.getDiseaseId12_1();str2=patDeptDisInfoDTO.getDiseaseId12_2();
				str3=patDeptDisInfoDTO.getDiseaseId12_3();str4=patDeptDisInfoDTO.getDiseaseId12_4();
			}else if(key==13){
				str1=patDeptDisInfoDTO.getDiseaseId13_1();str2=patDeptDisInfoDTO.getDiseaseId13_2();
				str3=patDeptDisInfoDTO.getDiseaseId13_3();str4=patDeptDisInfoDTO.getDiseaseId13_4();
			}else if(key==14){
				str1=patDeptDisInfoDTO.getDiseaseId14_1();str2=patDeptDisInfoDTO.getDiseaseId14_2();
				str3=patDeptDisInfoDTO.getDiseaseId14_3();str4=patDeptDisInfoDTO.getDiseaseId14_4();
			}else if(key==15){
				str1=patDeptDisInfoDTO.getDiseaseId15_1();str2=patDeptDisInfoDTO.getDiseaseId15_2();
				str3=patDeptDisInfoDTO.getDiseaseId15_3();str4=patDeptDisInfoDTO.getDiseaseId15_4();
			}else if(key==16){
				str1=patDeptDisInfoDTO.getDiseaseId16_1();str2=patDeptDisInfoDTO.getDiseaseId16_2();
				str3=patDeptDisInfoDTO.getDiseaseId16_3();str4=patDeptDisInfoDTO.getDiseaseId16_4();
			}else if(key==17){
				str1=patDeptDisInfoDTO.getDiseaseId17_1();str2=patDeptDisInfoDTO.getDiseaseId17_2();
				str3=patDeptDisInfoDTO.getDiseaseId17_3();str4=patDeptDisInfoDTO.getDiseaseId17_4();
			}else if(key==18){
				str1=patDeptDisInfoDTO.getDiseaseId18_1();str2=patDeptDisInfoDTO.getDiseaseId18_2();
				str3=patDeptDisInfoDTO.getDiseaseId18_3();str4=patDeptDisInfoDTO.getDiseaseId18_4();
			}else if(key==19){
				str1=patDeptDisInfoDTO.getDiseaseId19_1();str2=patDeptDisInfoDTO.getDiseaseId19_2();
				str3=patDeptDisInfoDTO.getDiseaseId19_3();str4=patDeptDisInfoDTO.getDiseaseId19_4();
			}else if(key==20){
				str1=patDeptDisInfoDTO.getDiseaseId20_1();str2=patDeptDisInfoDTO.getDiseaseId20_2();
				str3=patDeptDisInfoDTO.getDiseaseId20_3();str4=patDeptDisInfoDTO.getDiseaseId20_4();
			}else if(key==21){
				str1=patDeptDisInfoDTO.getDiseaseId21_1();str2=patDeptDisInfoDTO.getDiseaseId21_2();
				str3=patDeptDisInfoDTO.getDiseaseId21_3();str4=patDeptDisInfoDTO.getDiseaseId21_4();
			}else if(key==22){
				str1=patDeptDisInfoDTO.getDiseaseId22_1();str2=patDeptDisInfoDTO.getDiseaseId22_2();
				str3=patDeptDisInfoDTO.getDiseaseId22_3();str4=patDeptDisInfoDTO.getDiseaseId22_4();
			}
			
			if(editAndView==false && patCurDisInfo.contains(key)==false){
				continue;
			}
			html+="<tr>\n";
			html+="<td style=\"border: none;\">\n";
			html+=generateHTMLString(key, disList.get(key), patCurDisInfo.contains(key), str1, str2, str3, str4);				
			html+="</td>\n";
			html+="</tr>\n";
		}
		return html;
	}
	
	public static String generateHTMLString(int diseaseId, String diseaseName, boolean isChecked, String str1, String str2, String str3, String str4){
		String checkBox="";
		if(isChecked){
			checkBox=" checked=\"checked\" ";
		}
		String tail="";
		String name="diseaseId"+diseaseId;
		
		tail+="<table border=\"1\" rules=\"all\" frame=\"void\" style=\"margin-left: 40px;\">";
			tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\""+name+"_1\" value=\""+str1+"\"></td><td><input type=\"text\" class=\"input-small\" name=\""+name+"_2\" value=\""+str2+"\"></td></tr>";
			tail+="<tr><td class=\"tableData2\" style=\"margin: 2pt;\"><input type=\"text\" class=\"input-small\" style=\"text-align: right\" name=\""+name+"_3\" value=\""+str3+"\"></td><td><input type=\"text\" class=\"input-small\" name=\""+name+"_4\" value=\""+str4+"\"></td></tr>";
		tail+="</table>";
		
		return "<input type=\"checkbox\" "+checkBox+" name=\"diseaseId\" value=\""+diseaseId+"\"> "+diseaseName+tail+"\n";
	}
	
	public static String getInputString(String inputName, int spaceingColumn, int descripId, DiseaseMetaData metaData, boolean isChecked, PatientOthersDTO patOthersDTO){
		String str1="";
		if(spaceingColumn>0){
			str1=" style=\"margin-left: "+spaceingColumn+"px;\" ";
		}
		if(isChecked){
			str1+=" checked=\"checked\" ";
		}
		String tail="";
		if(inputName.equals("infoId")){
			String name="infoId"+descripId;
			if(descripId==93){
				tail=generateSelectBox(name, patOthersDTO.getInfoId93(), new String[]{"Posteroanterior view","Anteroposterior view"});
			}else if(descripId==94){
				tail=generateSelectBox(name, patOthersDTO.getInfoId94(), new String[]{"Posteroanterior view","Reverse Towne�s view","Towne�s view","Lateral view","Oblique view","Occipitomental view","30� Occipitomental view","Submentovertex view","OPG view","Cephalometry view"});
			}else if(descripId==95){
				tail=generateSelectBox(name, patOthersDTO.getInfoId95(), new String[]{"Periapical view","Occlusal view of upper jaw","Occlusal view of lower jaw"});
			}else if(descripId==92){
				tail=generateSelectBox(name, patOthersDTO.getInfoId92(), new String[]{"Arthography","Sialography"});
			}else if(descripId==75){
				tail=generateSelectBox(name, patOthersDTO.getInfoId75(), new String[]{"Plain","Contrast","3D image"});
			}else if(descripId==76){
				tail=generateSelectBox(name, patOthersDTO.getInfoId76(), new String[]{"Lymph node", "Vascular lesion","Fluid filled cavity"});
			}else if(descripId==96){
				tail=generateSelectBox(name, patOthersDTO.getInfoId96(), new String[]{"Axial","Coronal","Sagital","Oblique"});
			}else if(descripId==97){
				tail=generateSelectBox(name, patOthersDTO.getInfoId97(), new String[]{"Axial","Coronal","Sagital","Oblique"});
			}else if(descripId==98){
				tail="<br>&emsp;&emsp;1 <input type=\"text\" name=\""+name+"_1"+"\" value=\""+patOthersDTO.getInfoId98_1()+"\">\n";
				tail+="<br>&emsp;&emsp;2 <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patOthersDTO.getInfoId98_2()+"\">\n";
			}else if(descripId==86){
				tail=generateSelectBox(name, patOthersDTO.getInfoId86(), new String[]{"Endoscope","Sinuscope"});
			}else if(descripId==87){
				tail=generateSelectBox(name, patOthersDTO.getInfoId87(), new String[]{"Excisional biopsy","Incisional biopsy","Brush biopsy","Frozen section biopsy","Fine needle aspiration biopsy","Ultrasound guided FNAC","Core biopsy"});
			}else if(descripId==99){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId99()+"\">\n";
			}else if(descripId==219){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId219()+"\">\n";
			}else if(descripId==205){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId205()+"\">\n";
			}else if(descripId==210){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId210()+"\">\n";
			}else if(descripId==200){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId200()+"\">\n";
			}else if(descripId==197){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId197()+"\">\n";
			}else if(descripId==190){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId190()+"\">\n";
			}else if(descripId==169){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId169()+"\">\n";
			}else if(descripId==182){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId182()+"\">\n";
			}else if(descripId==100){
				tail="<br>&emsp;&emsp;Quentity <input type=\"text\" name=\""+name+"_1"+"\" value=\""+patOthersDTO.getInfoId100_1()+"\">\n";
				tail+="<br>&emsp;&emsp;Duration <input type=\"text\" name=\""+name+"_2"+"\" value=\""+patOthersDTO.getInfoId100_2()+"\">\n";
			}else if(descripId==101){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId101()+"\">\n";
			}else if(descripId==121){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId121()+"\">\n";
			}else if(descripId==125){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId125()+"\">\n";
			}else if(descripId==136){
				tail=generateSelectBox(name, patOthersDTO.getInfoId136(), new String[]{"Foreign body","Loose teeth","Denture","Tumor","Soft tissue edema","Fracture"});
			}else if(descripId==137){
				tail=generateSelectBox(name, patOthersDTO.getInfoId137(), new String[]{"Burn","Radiotherapy","Previous tracheostomy","Infection","Trauma"});
			}else if(descripId==139){
				tail=generateSelectBox(name, patOthersDTO.getInfoId139(), new String[]{"Large","Normal"});
			}else if(descripId==146){
				tail=generateSelectBox(name, patOthersDTO.getInfoId146(), new String[]{"Well seen","Not seen"});
			}else if(descripId==147){
				tail=generateSelectBox(name, patOthersDTO.getInfoId147(), new String[]{"Ankylosis","Poor mobility of mandible","Normal"});
			}else if(descripId==149){
				tail="<input type=\"text\" name=\""+name+"_2"+"\" value=\""+patOthersDTO.getInfoId149_2()+"\">\n";
				tail+="<br>&emsp;&emsp;"+generateSelectBox(name, patOthersDTO.getInfoId149_1(), new String[]{"Normal","Decreased"});
			}else if(descripId==150){
				tail=generateSelectBox(name, patOthersDTO.getInfoId150(), new String[]{"Normal","Decreased"});
			}else if(descripId==151){
				tail=generateSelectBox(name, patOthersDTO.getInfoId151(), new String[]{"Normal","Decreased"});
			}else if(descripId==154){
				tail="<input type=\"text\" name=\""+name+"\" value=\""+patOthersDTO.getInfoId154()+"\">\n";
			}
		}
		
		return "<input type=\"checkbox\" "+str1+" name=\""+inputName+"\" value=\""+descripId+"\"> "+metaData.getName()+"\n"+tail;
	}
}
