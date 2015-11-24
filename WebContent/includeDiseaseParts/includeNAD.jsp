<%if(editAndView==true || (editAndView==false && patCurDisDTO.getNad()==1)){%>
	<div class="form-group">
		<div class="col-sm-12">
			<div class="checkbox">
				<label>
					<input type="checkbox" name="nad" value="1" <%if(patCurDisDTO.getNad()==1){%>checked="checked"<%}%>>NAD
				</label>
			</div>
		</div>
	</div><!--/./form-group--> 
	<div style="clear:both;"></div>
<%}%>