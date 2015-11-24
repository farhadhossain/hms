<%if(editAndView==true || (editAndView==false && patCurDisDTO.patAuscId.isEmpty()==false)){%>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
			<h4 class="panel-title">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
				  Auscultation
				</a>
			</h4>
		</div>
		<div id="<%= "collapse"+ id%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table" style="font-size: 13px;">
						<%=MyUtility.generateHTML(disAuscultationList, disAuscultationParentByChild, "auscultationId", patCurDisDTO.patAuscId, patCurDisDTO, editAndView)%>
					</table>
				</div><!-- /./table-responsive -->
			</div>
		</div>
	</div><!--/./panel panel-default-->
<%}%>