<%if(loginDTO!=null){ %>

<%@page import="utility.MyConfig"%><nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"> 
                        	<span>
                            	<%=loginDTO.employeeName %>
                            </span>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="">Profile</a></li>
                                <li class="divider"></li>
                                <li><a href="../Logout.do">Logout</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            IN+                    
                        </div>
                    </li>
                    <li>
                        <a href="../Dashboard/Dashboard.jsp">
                        <i class="fa fa-th-large"></i> 
                        <span class="nav-label">Dashboards</span>
                        </a>
                    </li>
                    <%if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientSearch)==true){%>
	                    <%if(loginDTO.getRoleID()!=MyConfig.dutyNurseRole){%>
		                    <li>
		                        <a href='../Patient/SearchPatient.jsp'>
					            	<i class="fa fa-search"></i> 
					                <span class="nav-label">Patient Management</span>
					            </a>                
					        </li>
					   	<%}%>
                    <%}%>
                    
                   	<%if(loginDTO.getRoleID()==MyConfig.dutyNurseRole){%>
		                <li>
	                        <a href='../Patient/ExtractionList.jsp'>
	                        	<i class="fa fa-pie-chart"></i> 
	                        	<span class="nav-label">Duty Nurse </span>
	                        </a>                
	                    </li>
	                <%}%>   
                   
                    <%if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientSearch)==true){%>
                    	<%if(loginDTO.getRoleID()==MyConfig.DoctorForBed || loginDTO.getRoleID()==MyConfig.deptIndoor || loginDTO.getRoleID()==MyConfig.SurgeonRole || loginDTO.getRoleID()==MyConfig.AssistantSurgeonRole || loginDTO.getRoleID()==MyConfig.AnesthetistRole){%>
			                <li>
		                        <a href='../Patient/PresentStatus.jsp'>
		                        	<i class="fa fa-pie-chart"></i> 
		                        	<span class="nav-label">Surgical Status</span>
		                        </a>                
		                    </li>
		                <%}%>   
                    <%}%>
                    
                    <%if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.ReportView)==true){%>
	                    <li>
	                        <a href='../Report/Statistics.jsp'>
	                        	<i class="fa fa-area-chart"></i> 
	                        	<span class="nav-label">Hospital Statistics</span>
	                        </a>                
	                    </li>
	                    
	                    <li>
	                        <a href='../Report/DiseaseSpecificReportDiagnosis.jsp'>
	                        	<i class="fa fa-line-chart"></i> 
	                        	<span class="nav-label">Report By Diagnosis</span>
	                        </a>                
	                    </li>
	                    
	                    <li>
	                        <a href='../Report/DiseaseSpecificReportTreatmentPlan.jsp'>
	                        	<i class="fa fa-line-chart"></i> 
	                        	<span class="nav-label">Report By Treatment Plan</span>
	                        </a>                
	                    </li>
	                    
	                    <li>
	                        <a href='../Others/Prescription.jsp'>
	                        	<i class="fa fa-stethoscope"></i> 
	                        	<span class="nav-label">Prescription</span>
	                        </a>                
	                    </li>
	                    
	                    <li>
	                        <a href='../Others/OTPlan.jsp'>
	                        	<i class="fa fa-ambulance"></i> 
	                        	<span class="nav-label">OT Plan</span>
	                        </a>                
	                    </li>
	                    
	                    <li>
	                        <a href='../Others/DrugList.jsp' target="_blank">
	                        	<i class="fa fa-medkit"></i> 
	                        	<span class="nav-label">Drug List</span>
	                        </a>                
	                    </li>
	                    
	                    <li>
	                        <a href='../Report/PresentWardStatus.jsp'>
	                        	<i class="fa fa-bar-chart"></i> 
	                        	<span class="nav-label">Present Ward Status</span>
	                        </a>                
	                    </li>
						<li>
							<a href='../Report/AllDeptCompareReport.jsp'>
								<i class="fa fa-bar-chart"></i>
								<span class="nav-label">Department Comparison Report</span>
							</a>
						</li>
                    <%}%>
                    
                    <%if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.UserSearch)==true){%>
						<li id='menuid-1-7'>
							<a href='../User/SearchUser.jsp'>
								User Management
							</a>
						</li>
					<%}%>
					
					<%if(loginDTO.getClientType()==-1 && RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.RoleSearch)==true){%>
						<li id='menuid-1-8'>
							<a href='../Role/SearchRole.jsp'>
								<i class="fa fa-user-secret"></i> 
								Role Management
							</a>
						</li>
					<%}%>
					
					
                    <li>
                        <a href="../Logout.do">
                        	<i class="fa fa-lock"></i> 
                        	<span class="nav-label">Logout</span>
                        </a>
                    </li>
              </ul>
    
            </div>
        </nav><!--/./navbar-default navbar-static-side-->
<%}%>
        