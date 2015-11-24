<%id++;%>			
<%int totalFollowup=followUpList.size();%>
<%if(editAndView==true || (editAndView==false && totalFollowup>0)){%>                    		
	<div class="panel panel-default">
    	<div class="panel-heading" role="tab" id="<%= "heading" + id%>">
        	<h4 class="panel-title">
            	<a data-toggle="collapse" data-parent="#accordion" href="<%= "#collapse"+ id%>" aria-expanded="false" aria-controls="<%= "collapse"+ id%>">
                	Follow up:
                </a>
            </h4>
        </div>
        <div id="<%= "collapse"+ id%>" class="panel-collapse collapse"  role="tabpanel" aria-labelledby="<%= "heading"+ id%>">
            <div class="panel-body">
				<div class="table-responsive">
                	<%if(editAndView==true){%>
	                	<div class="ibox-content">
							<html:form action="<%=actionNameFollowUp %>" >
								<div class="form-horizontal">
	                    			<div class="form-group">
	                            		<label class="col-sm-4 control-label">New a Findings<span class="red">*</span></label>
	                            		<div class="col-sm-6">
	                            			<textarea rows="3" cols="40" name="newFindings"></textarea>
	                            			<input type="submit" value="Add">
	                            			<input type="hidden" name="newFindingsUserId" id="newFindingsUserId" value="<%=userID%>">
	 										<input type="hidden" name="newFindingsDiseaseId" id="newFindingsDiseaseId" value="<%=diseaseID%>">
	                            		</div>
	                    			</div>
	                    			<div class="hr-line-dashed"></div>
	                   			</div>
	                		</html:form>
						</div><!--/./ibox-content-->
					<%}%>
                    <%if(totalFollowup>0){%>
                       	<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
                           	<thead>
                              		<tr>
                              			<th>SN</th>
                                    <th>Findings</th>
                                    <th>Date Of Entry</th>
								</tr>
                              	</thead>
                              	<tbody>
		                    	<%
								for(int i=0;i<totalFollowup;i++){%>
									<tr>
				                    	<td><%=(i+1)%></td>
										<td><%=followUpList.get(i).findings%></td>
										<td><%=followUpList.get(i).addedTime%></td>
									</tr>
								<%}%>
                              	</tbody>
                          	</table>
	              	<%}%>
				</div><!--/./form-group--> 
          	</div>
      	</div>
	</div><!--/./panel panel-default-->
	<div style="color:both">&nbsp;</div>
<%}%>