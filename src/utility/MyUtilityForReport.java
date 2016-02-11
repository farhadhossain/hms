package utility;

import java.util.HashMap;
import java.util.HashSet;

import disease.DiseaseDTO;
import disease.form.DiseaseMetaData;

public class MyUtilityForReport {
	
	
	public static String getInputString(String inputName, int spaceingColumn, int descripId, DiseaseMetaData metaData, boolean isChildExists){
		String str1="";
		if(spaceingColumn>0){
			str1=" style=\"margin-left: "+spaceingColumn+"px;\" ";
		}
		if(isChildExists){
			str1+=" onchange=\"changeThis('block"+inputName+descripId+"')\"";
		}
		return "<input type=\"checkbox\" "+str1+" name=\""+inputName+"\" value=\""+descripId+"\"> "+metaData.getName()+"\n";
	}
	
	public static String generateHTMLWithOutTextBox(HashMap<Integer, DiseaseMetaData> diseaseList, HashMap<Integer, String> parentByChild, String inputName){
		HashSet<Integer> insertedId = new HashSet<Integer>();
		String html="";
		int spaceingColumn=40;
		
		int[] disKeySet = MyUtility.getSortedKeyList(diseaseList);
		for(int index=0; index<disKeySet.length; index++){
			if(insertedId.contains(disKeySet[index])){
				continue;
			}
			html+="<tr>\n";
			html+="<td style=\"border: none;\">\n";
			if(parentByChild.get(disKeySet[index])!=null){
				insertedId.add(disKeySet[index]);
				
				html+=getInputString(inputName, -1, disKeySet[index], diseaseList.get(disKeySet[index]), parentByChild.get(disKeySet[index])!=null?true:false);
				
				String[] mapValue1=parentByChild.get(disKeySet[index]).split(",");
				if(mapValue1.length>0){
					String styleDisplay="style=\"display:none;\"";
					html+="<div "+styleDisplay+" id=\"block"+inputName+disKeySet[index]+"\">\n";
				}
				for(int i=0;i<mapValue1.length;i++){
					int idValue1=Integer.parseInt(mapValue1[i]);
					insertedId.add(idValue1);
					html+="<br>\n";
					html+=getInputString(inputName, spaceingColumn, idValue1, diseaseList.get(idValue1), parentByChild.get(idValue1)!=null?true:false);
					if(parentByChild.get(idValue1)!=null){
						String[] mapValue2=parentByChild.get(idValue1).split(",");
						if(mapValue2.length>0){
							String styleDisplay="style=\"display:none;\"";
							html+="<div "+styleDisplay+" id=\"block"+inputName+idValue1+"\">\n";
						}
						for(int j=0;j<mapValue2.length;j++){
							int idValue2=Integer.parseInt(mapValue2[j]);
							insertedId.add(idValue2);
							html+="<br>\n";
							html+=getInputString(inputName, (spaceingColumn*2), idValue2, diseaseList.get(idValue2), parentByChild.get(idValue2)!=null?true:false);
							
							if(parentByChild.get(idValue2)!=null){
								String[] mapValue3=parentByChild.get(idValue2).split(",");
								if(mapValue3.length>0){
									String styleDisplay="style=\"display:none;\"";
									html+="<div "+styleDisplay+" id=\"block"+inputName+idValue2+"\">\n";
								}
								for(int k=0;k<mapValue3.length;k++){
									int idValue3=Integer.parseInt(mapValue3[k]);
									insertedId.add(idValue3);
									html+="<br>\n";
									html+=getInputString(inputName, (spaceingColumn*3), idValue3, diseaseList.get(idValue3), parentByChild.get(idValue3)!=null?true:false);
									
									//new 2
									if(parentByChild.get(idValue3)!=null){
										String[] mapValue4=parentByChild.get(idValue3).split(",");
										if(mapValue4.length>0){
											String styleDisplay="style=\"display:none;\"";
											html+="<div "+styleDisplay+" id=\"block"+inputName+idValue3+"\">\n";
										}
										for(int l=0;l<mapValue4.length;l++){
											int idValue4=Integer.parseInt(mapValue4[l]);
											insertedId.add(idValue4);
											html+="<br>\n";
											html+=getInputString(inputName, (spaceingColumn*4), idValue4, diseaseList.get(idValue4), false);
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
				html+=getInputString(inputName, -1, disKeySet[index], diseaseList.get(disKeySet[index]), false);				
			}
			html+="</td>\n";
			html+="</tr>\n";
		}
		return html;
	}
}
