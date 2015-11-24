<%if(editAndView==true || (editAndView==false && patCurDisDTO.patTrtmntId.isEmpty()==false)){%>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
			<h4 class="panel-title">
			<a data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
				Treatment Dose/Plan:
			</a>
			</h4>
		</div>
		<div id="<%= "collapse"+ id%>" class="panel-collapse collapse"  role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table" style="font-size: 13px;">
						<%=MyUtility.generateHTML(disTreatmentPlanList, disTreatmentPlanParentByChild, "treatmentId", patCurDisDTO.patTrtmntId, patCurDisDTO, editAndView)%>
					 </table>
				 </div><!--/./form-group--> 
			 </div>
		 </div>
	</div><!--/./panel panel-default-->
<%}%>