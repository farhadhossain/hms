<%@ page import="logbook.LogBookDAO" %>
<%@ page import="prescription.PrescriptionDAO" %>
<%@ page import="prescription.PrescriptionDTO" %>
<%@ page import="utility.MyConfig" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="extraction.ExtractionDTO" %>
<%@ page import="extraction.ExtractionDAO" %>
<%@ page import="extraction.TreatmentPlanDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes/checkLogin.jsp"%>

<%
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    System.out.println("-------- startdate = "+startDate+" & endDate = "+endDate+"------------");
    LogBookDAO logBook = new LogBookDAO();
    Map<Integer, String> diagnosisPatLists = logBook.getPatientListsByRoleId(loginDTO.getUserID(), startDate, endDate, MyConfig.diagnosisRoomRole);
    Map<Integer, String> minorOtPatLists = logBook.getPatientListsByRoleId(loginDTO.getUserID(), startDate, endDate, MyConfig.minorOTOrOutdoorRole);
    Map<Integer, String> majorOtPatLists = logBook.getPatientListsByRoleId(loginDTO.getUserID(), startDate, endDate, MyConfig.AssistantSurgeonRole);
    prescription.PrescriptionDAO presDAO = new PrescriptionDAO();
    prescription.PrescriptionDTO presDTO = new PrescriptionDTO();
    List<TreatmentPlanDTO> treatmentPlanDTOs;
    ExtractionDAO extractDAO = new ExtractionDAO();
%>

<html>
    <head>
        <title>Log Book</title>
        <link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap.css">
        <style>
            h3{color:#399;}
            body{color: #444}
            .diagnosis, .surgery{width:100%;}
            .diagnosis tbody>tr>td{padding:2px;}
            .surgery tbody>tr>td{padding:2px;}
        </style>
    </head>
    <body>
    <section class="text-center" style="padding:50px;">
        <img src="../Assets/Images/Logo_log.png" width="94" height="112" alt="BSMMU"/>
        <h2>Log Book</h2>
        <h4>
            Residency Training Program-Phase-A/B<br/>
            For<br/>
            MS In Oral and Maxillofacial Surgery
        </h4>
        <p>
            Discipline: Oral and Maxillofacial Surgery<br/>
            Faculty: Dentistry<br/>
        </p>

        <h3>Bangabandhu Sheikh Mujib Medical University</h3>
        <h4>Shahbag, Dhaka-1000, Bangladesh.</h4>
    </section>
    <section style="padding:50px;">
        <div style="clear:both">&nbsp;</div>
        <h4><strong>Name of <%=loginDTO.designation.equals("null")?"[Student's designation not mentioned]":loginDTO.designation%> Student:</strong> <%=loginDTO.employeeName%></h4>
        <table>
            <tbody>
            <tr>
                <td> <strong>Resident ID:</strong> _______________ &nbsp;</td>
                <td> <strong>Phase:</strong> _______________ &nbsp;</td>
                <td> <strong>Block:</strong> _______________ </td>
            </tr>
            <tr>
                <td colspan="3"><strong>Date from:</strong> <%=startDate%> &nbsp;<strong>To:</strong> <%=endDate%> </td>
            </tr>
            </tbody>
        </table>
        <div style="clear:both">&nbsp;</div>
    </section>
    <div class="panel panel-default" style="padding:50px;">
        <div class="panel-heading"><strong>Diagnosis Room [ Total: <%=diagnosisPatLists.size()%> ]</strong></div><!--/.panel-heading-->
        <%if(diagnosisPatLists.size() != 0){%>
        <%
            Iterator<Map.Entry<Integer,String>> itr = diagnosisPatLists.entrySet().iterator();
            int i = 0;
        %>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr class="success">
                        <th>Sl No</th>
                        <th>Name of Patient</th>
                        <th>Date</th>
                        <th>Diagnosis</th>
                    </tr>
                    </thead>
                    <%while (itr.hasNext()){%>
                   <%
                       int key = itr.next().getKey();
                       presDTO = presDAO.getPrescriptionByPatientId(key);
                       i++;
                       String oe = presDTO.getOnObservation();
                       System.out.println("*************** O/E = "+oe+" ************************");
                       String caries = "", looseTeeth = "", bdr = "", impacted = "";

                       if(oe.contains("Caries")) {
                           caries = oe.substring(oe.indexOf("Caries"), oe.indexOf("$$hashKey"));
                           oe = oe.replace(caries+"$$hashKey", "");
                       }

                       /*System.out.println("*************** Caries = "+caries+" ************************");
                       System.out.println("*************** O/E = "+oe+" ************************");*/

                       if(oe.contains("loose_teeth")) {
                           looseTeeth = oe.substring(oe.indexOf("loose_teeth"), oe.indexOf("$$hashKey"));
                           oe = oe.replace(looseTeeth+"$$hashKey", "");
                       }
                      /* System.out.println("*************** looseTeeth = "+looseTeeth+" ************************");
                       System.out.println("*************** O/E = "+oe+" ************************");*/

                       if(oe.contains("BDR")) {
                           bdr = oe.substring(oe.indexOf("BDR"), oe.indexOf("$$hashKey"));
                           oe = oe.replace(bdr+"$$hashKey", "");
                       }
                      /* System.out.println("*************** BDR = "+bdr+" ************************");
                       System.out.println("*************** O/E = "+oe+" ************************");*/

                       if(oe.contains("Impacted")) {
                           impacted = oe.substring(oe.indexOf("Impacted"), oe.indexOf("$$hashKey"));
                           oe = oe.replace(impacted+"$$hashKey", "");
                       }
                       /*System.out.println("*************** Impacted = "+impacted+" ************************");
                       System.out.println("*************** O/E = "+oe+" ************************");*/
                   %>
                    <tbody>
                        <tr>
                            <td><%=i%></td>
                            <td><%=presDTO.patientName%></td>
                            <td><%=diagnosisPatLists.get(key)%></td>
                            <td>
                                <table class="diagnosis" cellspacing="5">
                                    <tbody>
                                    <tr>
                                        <td rowspan="2">Caries</td>
                                        <td style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=caries.equals("")?"":caries.substring(caries.indexOf("\"lt\":[")+6, caries.indexOf("],\"rt\""))%>&nbsp;</td>
                                        <td style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=caries.equals("")?"":caries.substring(caries.indexOf("\"rt\":[")+6, caries.indexOf("],\"lb\""))%></td>
                                    </tr>
                                    <tr>
                                        <td style="border-right:1px solid #cdcdcd; text-align:right;"><%=caries.equals("")?"":caries.substring(caries.indexOf("\"lb\":[")+6, caries.indexOf("],\"rb\""))%>&nbsp;</td>
                                        <td>&nbsp;<%=caries.equals("")?"":caries.substring(caries.indexOf("\"rb\":[")+6, caries.indexOf("]}"))%></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"></td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2">Loose teeth</td>
                                        <td style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"lt\":[")+6, looseTeeth.indexOf("],\"rt\""))%>&nbsp;</td>
                                        <td style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"rt\":[")+6, looseTeeth.indexOf("],\"lb\""))%></td>
                                    </tr>
                                    <tr>
                                        <td style="border-right:1px solid #cdcdcd; text-align:right;"><%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"lb\":[")+6, looseTeeth.indexOf("],\"rb\""))%>&nbsp;</td>
                                        <td>&nbsp;<%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"rb\":[")+6, looseTeeth.indexOf("]}"))%></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"></td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2">BDR</td>
                                        <td style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"lt\":[")+6, bdr.indexOf("],\"rt\""))%>&nbsp;</td>
                                        <td style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"rt\":[")+6, bdr.indexOf("],\"lb\""))%></td>
                                    </tr>
                                    <tr>
                                        <td style="border-right:1px solid #cdcdcd; text-align:right;"><%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"lb\":[")+6, bdr.indexOf("],\"rb\""))%>&nbsp;</td>
                                        <td>&nbsp;<%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"rb\":[")+6, bdr.indexOf("]}"))%></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"></td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2">Impacted</td>
                                        <td style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"lt\":[")+6, impacted.indexOf("],\"rt\""))%>&nbsp;</td>
                                        <td style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"rt\":[")+6, impacted.indexOf("],\"lb\""))%></td>
                                    </tr>
                                    <tr>
                                        <td style="border-right:1px solid #cdcdcd; text-align:right;"><%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"lb\":[")+6, impacted.indexOf("],\"rb\""))%>&nbsp;</td>
                                        <td>&nbsp;<%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"rb\":[")+6, impacted.indexOf("]}"))%></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"></td>
                                    </tr>
                                    <%if(presDTO.getOnObservation().contains("Supernumerary")){%>
                                        <tr>
                                            <td colspan="3">Supernumerary</td>
                                        </tr>
                                    <%}%>
                                    <%if(presDTO.getOnObservation().contains("Supplemental")){%>
                                        <tr>
                                            <td colspan="3">Supplemental</td>
                                        </tr>
                                    <%}%>
                                    <%if(presDTO.getOnObservation().contains("Fracture")){%>
                                        <tr>
                                            <td colspan="3">Fracture</td>
                                        </tr>
                                    <%}%>
                                    <%if(presDTO.getOnObservation().contains("Cleft lip/ palate")){%>
                                        <tr>
                                            <td colspan="3">Cleft lip/palate</td>
                                        </tr>
                                    <%}%>
                                    <%if(presDTO.getOnObservation().contains("Lacerated wound")){%>
                                        <tr>
                                            <td colspan="3">Lacerated wound</td>
                                        </tr>
                                    <%}%>
                                    <%if(presDTO.getOnObservation().contains("Facial space infection")){%>
                                        <tr>
                                            <td colspan="3">Facial space infection</td>
                                        </tr>
                                    <%}%>
                                    <%if(presDTO.getOnObservation().contains("Ankylosis of tmj")){%>
                                        <tr>
                                            <td colspan="3">Ankylosis of tmj</td>
                                        </tr>
                                    <%}%>

                                    <tr>
                                        <td>Disease: <i class="fa fa-angle-right">&nbsp;</i></td>
                                        <td colspan="2"><%=presDTO.getDiagonosis().substring(presDTO.getDiagonosis().indexOf(":")+2, presDTO.getDiagonosis().indexOf("}")-1)%></td>
                                    </tr>

                                    <tr>
                                        <td>Others <i class="fa fa-angle-right"></i></td>
                                        <td colspan="2"><%=oe.contains("\"other\":{\"text\"")? oe.substring(oe.indexOf("{\"other\":{\"text\":")+18, oe.indexOf("},\"$$hashKey")-1): ""%></td>
                                    </tr>

                                    </tbody>

                                </table>
                            </td>
                        </tr>
                    </tbody>
                    <%}%>
                </table>
            </div><!--/.table-responsive-->
        </div><!--/.panel-body-->
        <%}%>
      </div><!--/.panel-->

    <div class="panel panel-default" style="padding:50px;">
        <div class="panel-heading"><strong>Minor OT/Out Door [ Total: <%=minorOtPatLists.size()%> ]</strong></div><!--/.panel-heading-->
        <%if(minorOtPatLists.size() != 0){%>
        <%
            Iterator<Map.Entry<Integer,String>> itr = minorOtPatLists.entrySet().iterator();
            int i = 0;
        %>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr class="success">
                        <th>Sl No</th>
                        <th>Name of Patient</th>
                        <th>Date</th>
                        <th>Diagnosis</th>
                        <th>Name of Surgery</th>
                    </tr>
                    </thead>
                    <%while (itr.hasNext()){%>
                    <%
                        int key = itr.next().getKey();
                        String caries = "", looseTeeth = "", bdr = "", impacted = "";
                        presDTO = presDAO.getPrescriptionByPatientId(key);
                        treatmentPlanDTOs = extractDAO.getTreatmentPlan(key);
                        System.out.println("------------------- size of treatment plan of patient id "+key+"= "+treatmentPlanDTOs.size()+" --------------------");
                        System.out.println("*************** TP DTO-1 = "+treatmentPlanDTOs.get(0).getInputValue1()+" ************************");
                        System.out.println("*************** TP DTO-2 = "+treatmentPlanDTOs.get(0).getInputValue2()+" ************************");
                        i++;
                        String oe = presDTO.getOnObservation();
                        /*System.out.println("*************** O/E = "+oe+" ************************");*/

                        if(oe.contains("Caries")) {
                            caries = oe.substring(oe.indexOf("Caries"), oe.indexOf("$$hashKey"));
                            oe = oe.replace(caries+"$$hashKey", "");
                        }

                        /*System.out.println("*************** Caries = "+caries+" ************************");
                        System.out.println("*************** O/E = "+oe+" ************************");
*/
                        if(oe.contains("loose_teeth")) {
                            looseTeeth = oe.substring(oe.indexOf("loose_teeth"), oe.indexOf("$$hashKey"));
                            oe = oe.replace(looseTeeth+"$$hashKey", "");
                        }
                        /*System.out.println("*************** looseTeeth = "+looseTeeth+" ************************");
                        System.out.println("*************** O/E = "+oe+" ************************");*/

                        if(oe.contains("BDR")) {
                            bdr = oe.substring(oe.indexOf("BDR"), oe.indexOf("$$hashKey"));
                            oe = oe.replace(bdr+"$$hashKey", "");
                        }
                        /*System.out.println("*************** BDR = "+bdr+" ************************");
                        System.out.println("*************** O/E = "+oe+" ************************");*/

                        if(oe.contains("Impacted")) {
                            impacted = oe.substring(oe.indexOf("Impacted"), oe.indexOf("$$hashKey"));
                            oe = oe.replace(impacted+"$$hashKey", "");
                        }
                       /* System.out.println("*************** Impacted = "+impacted+" ************************");
                        System.out.println("*************** O/E = "+oe+" ************************");*/
                    %>
                    <tbody>
                    <tr>
                        <td><%=i%></td>
                        <td> <%=presDTO.patientName%> </td>
                        <td> <%=minorOtPatLists.get(key)%> </td>
                        <td>
                            <table class="minorot" cellspacing="5">
                                <tbody>
                                <tr>
                                    <td rowspan="2">Caries</td>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=caries.equals("")?"":caries.substring(caries.indexOf("\"lt\":[")+6, caries.indexOf("],\"rt\""))%>&nbsp;</td>
                                    <td width="30%" style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=caries.equals("")?"":caries.substring(caries.indexOf("\"rt\":[")+6, caries.indexOf("],\"lb\""))%></td>
                                </tr>
                                <tr>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; text-align:right;"><%=caries.equals("")?"":caries.substring(caries.indexOf("\"lb\":[")+6, caries.indexOf("],\"rb\""))%>&nbsp;</td>
                                    <td width="30%">&nbsp;<%=caries.equals("")?"":caries.substring(caries.indexOf("\"rb\":[")+6, caries.indexOf("]}"))%></td>
                                </tr>
                                <tr>
                                    <td colspan="3"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">Loose teeth</td>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"lt\":[")+6, looseTeeth.indexOf("],\"rt\""))%>&nbsp;</td>
                                    <td width="30%" style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"rt\":[")+6, looseTeeth.indexOf("],\"lb\""))%></td>
                                </tr>
                                <tr>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; text-align:right;"><%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"lb\":[")+6, looseTeeth.indexOf("],\"rb\""))%>&nbsp;</td>
                                    <td width="30%">&nbsp;<%=looseTeeth.equals("")?"":looseTeeth.substring(looseTeeth.indexOf("\"rb\":[")+6, looseTeeth.indexOf("]}"))%></td>
                                </tr>
                                <tr>
                                    <td colspan="3"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">BDR</td>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"lt\":[")+6, bdr.indexOf("],\"rt\""))%>&nbsp;</td>
                                    <td width="30%" style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"rt\":[")+6, bdr.indexOf("],\"lb\""))%></td>
                                </tr>
                                <tr>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; text-align:right;"><%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"lb\":[")+6, bdr.indexOf("],\"rb\""))%>&nbsp;</td>
                                    <td width="30%">&nbsp;<%=bdr.equals("")?"":bdr.substring(bdr.indexOf("\"rb\":[")+6, bdr.indexOf("]}"))%></td>
                                </tr>
                                <tr>
                                    <td colspan="3"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">Impacted</td>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right;"><%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"lt\":[")+6, impacted.indexOf("],\"rt\""))%>&nbsp;</td>
                                    <td width="30%" style="border-bottom:1px solid #cdcdcd;">&nbsp;<%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"rt\":[")+6, impacted.indexOf("],\"lb\""))%></td>
                                </tr>
                                <tr>
                                    <td width="30%" style="border-right:1px solid #cdcdcd; text-align:right;"><%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"lb\":[")+6, impacted.indexOf("],\"rb\""))%>&nbsp;</td>
                                    <td width="30%">&nbsp;<%=impacted.equals("")?"":impacted.substring(impacted.indexOf("\"rb\":[")+6, impacted.indexOf("]}"))%></td>
                                </tr>
                                <tr>
                                    <td colspan="3"></td>
                                </tr>
                                <%if(presDTO.getOnObservation().contains("Supernumerary")){%>
                                <tr>
                                    <td colspan="3">Supernumerary</td>
                                </tr>
                                <%}%>
                                <%if(presDTO.getOnObservation().contains("Supplemental")){%>
                                <tr>
                                    <td colspan="3">Supplemental</td>
                                </tr>
                                <%}%>
                                <%if(presDTO.getOnObservation().contains("Fracture")){%>
                                <tr>
                                    <td colspan="3">Fracture</td>
                                </tr>
                                <%}%>
                                <%if(presDTO.getOnObservation().contains("Cleft lip/ palate")){%>
                                <tr>
                                    <td colspan="3">Cleft lip/palate</td>
                                </tr>
                                <%}%>
                                <%if(presDTO.getOnObservation().contains("Lacerated wound")){%>
                                <tr>
                                    <td colspan="3">Lacerated wound</td>
                                </tr>
                                <%}%>
                                <%if(presDTO.getOnObservation().contains("Facial space infection")){%>
                                <tr>
                                    <td colspan="3">Facial space infection</td>
                                </tr>
                                <%}%>
                                <%if(presDTO.getOnObservation().contains("Ankylosis of tmj")){%>
                                <tr>
                                    <td colspan="3">Ankylosis of tmj</td>
                                </tr>
                                <%}%>

                                <tr>
                                    <td>Disease: <i class="fa fa-angle-right">&nbsp;</i></td>
                                    <td colspan="2"><%=presDTO.getDiagonosis().substring(presDTO.getDiagonosis().indexOf(":")+2, presDTO.getDiagonosis().indexOf("}")-1)%></td>
                                </tr>

                                <tr>
                                    <td>Others <i class="fa fa-angle-right"></i></td>
                                    <td colspan="2"><%=oe.contains("\"other\":{\"text\"")? oe.substring(oe.indexOf("{\"other\":{\"text\":")+18, oe.indexOf("},\"$$hashKey")-1): ""%></td>
                                </tr>

                                </tbody>

                            </table>
                        </td>
                        <td>
                            <table class="surgery" cellspacing="5">
                                <tbody>
                                <tr>
                                    <td rowspan="2">Extraction <i class="fa fa-angle-right"></i></td>
                                    <td rowspan="2">Simple Extraction</td>
                                    <td width="13%" style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right">5</td>
                                    <td width="13%" style="border-bottom:1px solid #cdcdcd;">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td width="13%" style="border-right:1px solid #cdcdcd; text-align:right;">&nbsp;</td>
                                    <td width="13%">3</td>
                                </tr>
                                <tr>
                                    <td colspan="4"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2"></td>
                                    <td rowspan="2">Surgical Extraction</td>
                                    <td style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right">2</td>
                                    <td style="border-bottom:1px solid #cdcdcd;">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td style="border-right:1px solid #cdcdcd; text-align:right;">&nbsp;</td>
                                    <td>4</td>
                                </tr>
                                <tr>
                                    <td colspan="4"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2"></td>
                                    <td rowspan="2">Special Surgical Extraction</td>
                                    <td style="border-right:1px solid #cdcdcd; border-bottom:1px solid #cdcdcd; text-align:right">1,2</td>
                                    <td style="border-bottom:1px solid #cdcdcd;">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td style="border-right:1px solid #cdcdcd; text-align:right;">&nbsp;</td>
                                    <td>7</td>
                                </tr>
                                <tr>
                                    <td>Excision <i class="fa fa-angle-right"></i></td>
                                    <td colspan="3">Excision of epulis</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="3">Frenectomy</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="3">Incision oferupting teeth</td>
                                </tr>

                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                    <%}%>
                </table>
            </div><!--/.table-responsive-->
        </div><!--/.panel-body-->
        <%}%>
    </div><!--/.panel-->
    </body>
</html>
