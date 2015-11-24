<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="department.DepartmentDiseaseService"%>
<%@page import="department.DepartmentDiseaseDTO"%>
<%@ page import="utility.MyUtility" %>
<%
DepartmentDiseaseService deptDisServ = new DepartmentDiseaseService();
DepartmentDiseaseDTO patDeptDisDTO = deptDisServ.getPatientDepartmentDiseaseInfo(patientDTO.getAccId(), patientDTO.getDeptId()); 
HashMap<Integer, String> disGeneralList = deptDisServ.getDepartmentDiseaseListByID(patientDTO.getDeptId(), 0);
HashMap<Integer, String> disHistoryList = deptDisServ.getDepartmentDiseaseListByID(patientDTO.getDeptId(), 1);
HashMap<Integer, String> disClinicalExmList = deptDisServ.getDepartmentDiseaseListByID(patientDTO.getDeptId(), 2);
HashMap<Integer, String> disTreatmentList = deptDisServ.getDepartmentDiseaseListByID(patientDTO.getDeptId(), 3);

%>
<style type="text/css">
	.theader {
		text-align: center;
		font-size: 16px;
	}

	.table {
		font-size: 16px;
	}
</style>
	<div class="ibox-content patientView">
		<%if(disGeneralList.size()>0){%>
			<div class="" style="text-align: center;">
				<div class="theader col-sm-2">
					<label>Disease Name</label>
				</div>
				
				<div class="table-responsive col-sm-10">
					<table class="table" style="font-size: 16px;">
						<%=MyUtility.generateHTMLForDeptDisList(disGeneralList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
					 </table>
				 </div>
			 </div>
		<%}%>
			
		<%if(disHistoryList.size()>0){%>
			<div class="col-sm-12 theader">
				<div class="theader">
					<label>History</label>
				</div>
				<div class="table-responsive">
					<table class="table">
						<%=MyUtility.generateHTMLForDeptDisList(disHistoryList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
					 </table>
				 </div>
			 </div>
		<%}%>
		
			
		<%if(disTreatmentList.size()>0){%>
			<div class="col-sm-12">
				<div class="theader">
					<label>Treatment Plan</label>
				</div>
				<div class="table-responsive">
					<table class="table">
						<%=MyUtility.generateHTMLForDeptDisList(disTreatmentList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
		
		
		<%if(disClinicalExmList.size()>0){%>
			<div class="col-sm-12">
				<div class="theader">
					<label>Clinical Examination</label>
				</div>
				<div class="table-responsive">
					<table class="table" style="font-size: 13px;">
						<%=MyUtility.generateHTMLForDeptDisList(disClinicalExmList, patDeptDisDTO.patCurDepartDisList, patDeptDisDTO, editAndView)%>
					 </table>
				</div>
			</div>
		<%}%>
	</div>
