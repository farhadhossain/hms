<%@ include file="../includes/checkLogin.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.OthersInfo"%>
<%@page import="utility.MyConfig"%>
<%@page import="status.StatusService"%>
<%@page import="status.StatusService"%>
<%@page import="patientOthers.PatientOthersService"%>
<%@page import="patientOthers.PatientOthersDTO"%>

<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<%
String userID = request.getParameter("userID");
if(userID==null){
	userID=(String)session.getAttribute("userID");
}else{
	session.setAttribute("userID", userID);
}

int TOTAL_PAGES=1;
int PAGE_SIZE=30;

PatientOthersService patOthersServ = new PatientOthersService();

ArrayList<PatientOthersDTO> medDTOListCur = patOthersServ.getMedicationList(Integer.parseInt(userID), 0); 
ArrayList<PatientOthersDTO> medDTOListPast = patOthersServ.getMedicationList(Integer.parseInt(userID), -1);

HashSet<Integer> patDrugHis = patOthersServ.getPatientDrugID(Integer.parseInt(userID));
HashMap<Integer, String> drugGroup = patOthersServ.getDrugGroup();
%>

<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><html:base/>
<title><%=SessionManager.title%></title>
<!--

<link rel="stylesheet" type="text/css" href="../Assets/Fonts/fontface.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/united-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/main.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/sidebar-menu.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/style.css" />

    -->

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="../Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font -->
    <link href="../Assets/NewAssets/css/font-awesome.css" rel="stylesheet">
    <!-- Animation -->
    <link href="../Assets/NewAssets/css/animate.css" rel="stylesheet">
    <!-- Custom css -->
    <link href="../Assets/NewAssets/css/style.css" rel="stylesheet">
    
    <script type="text/javascript" src="../Assets/Scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/sidebar-menu.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/bootstrap-timepicker.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/jquery.numeric.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/picnet.table.filter.min.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/highcharts.js"></script>
    <script type="text/javascript" src="../Assets/Scripts/mir-billing-script.js"></script>
    <script type="text/javascript">

    function navigate(nav)
    {
      //var forms = document.forms;
      var totalPage = document.getElementById("totalPage");
      var page = document.getElementById("pageCount");
      
      if(nav == "f")
      {
    	page.value = "1";
      }
      else if(nav == "l")
      {
    	page.value = totalPage.value;
      }
      else if(nav == "p")
      {
        if(page.value != "1")
        {
        	var val = parseInt(page.value);
        	page.value = "" +(val-1);
        }
      }
      else if(nav == "n")
      {
        if(page.value != totalPage.value)
        {
          var val = parseInt(page.value) + 1;
          page.value = ""+val;
        }
      }
      showData();
      return false;
    }

    function showData(){
    	var page = document.getElementById("pageCount");
    	var currentPage = parseInt(page.value);
    	var totalPage = document.getElementById("totalPage");
    	var tPage = parseInt(totalPage.value);
    	
    	for(var i = 1; i <= tPage; i++){
    		if(i!=currentPage)
    			document.getElementById(i).style.display = "none";
    		else
    			document.getElementById(i).style.display = "block";
    	}
    	return true;
    }
    
    </script>
</head>


<body>
    <!--*********************Modal Starts from here*****************************-->
    <!--************************************************************************-->
    

    <!--*********************Body part Starts from here************************-->
    <!--***********************************************************************-->
    <div id="wrapper">
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->

        <div id="page-wrapper" class="gray-bg">
        
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
            		<div class="row border-bottom">
				            <nav class="navbar navbar-static-top navbar-inverse" role="navigation" style="margin-bottom: 0">
				                <div class = "container-fluid">
				                	<ul class="nav nav-tabs">
				                	
				               			<li role="presentation"><a href="../PatientOthers/PatientPersonalHistory.jsp?userID=<%=userID%>">Social & Personal</a></li>
								  		<li role="presentation"><a href="../PatientOthers/Investigation.jsp?userID=<%=userID%>">Investigation</a></li>
									  	<li role="presentation"><a href="../PatientOthers/PatinetPhysicalExamination.jsp?userID=<%=userID%>">Physical Examination</a></li>
									  	<li role="presentation"><a href="../PatientOthers/PreAnestheticEvaluation.jsp?userID=<%=userID%>">Pre-Anesthetic Evaluation</a></li>
									  	<li role="presentation"><a href="../PatientOthers/GeneralSurvey.jsp?userID=<%=userID%>">General Survey</a></li>
										<li role="presentation" class="dropdown">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Surgery<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation"><a href="../PastSergHistory/SearchPastSergHistory.jsp?userID=<%=userID%>">Surgical History</a></li>
									      		<li role="presentation"><a href="../PastSergHistory/AddPastSergHistory.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  	<li role="presentation" class="dropdown active">
											<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
									      		Drugs<span class="caret"></span>
								    		</a>
									    	<ul class="dropdown-menu" role="menu">
									      		<li role="presentation" class="active"><a href="../PatientOthers/PatientDrugAnMedication.jsp?userID=<%=userID%>">Drug History</a></li>
									      		<li role="presentation"><a href="../PatientOthers/AddMedication.jsp?userID=<%=userID %>">Add New</a></li>
									    	</ul>
									  	</li>
									  
									</ul>
				                </div>
				                
				            </nav><!--/./navbar navbar-static-top-->
		            
        				</div><!--/./row border-bottom-->
            	
        		 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Patient Drug & Medication</h5>
                    </div><!--/./ibox-title-->

                    <%if(session.getAttribute(SessionManager.PatientDrugAndMedication)!=null){%>
                        <div class="" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientDrugAndMedication)%></div>
                        <%session.removeAttribute(SessionManager.PatientDrugAndMedication);
                    }%>

                    <div class="ibox-content">
                  
					
						<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        
                          <div class="panel panel-default">
                            
                            
                            <div class="panel-heading" role="tab" id="headingOne">
                              <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                  Drug History
                                </a>
                              </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                              <div class="panel-body">
                              <div class="form-horizontal">
								<html:form action="/PatientDrugGroup">
							
                            <div class="form-group">
                                    <%
                                    int count=0;
                                    for(int key : drugGroup.keySet()){
                                        if(count%3==0){%>
                                 </div>
                                 <div class="form-group">
                                     <%}
                                     count++;%>
                                    <div class="col-sm-4">
                                        <div class="checkbox">
                                             <label>
                                             <input type="checkbox" name="drugHistoryId" value="<%=key%>" <%if(patDrugHis.contains(key)==true){%>checked="checked"<%}%>> <%=drugGroup.get(key)%>
                                            </label>
                                        </div>
                                    </div>
                                    <%}%>    
                            
                         </div><!--/./form-group-->

                         <hr/>
                         <input type="submit" value="Save" class="btn btn-primary"/>
                         <input type="hidden" name="userId" id="userId" value="<%=userID%>">

                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        	
                        
							</html:form>
							</div>
                            
                              </div>
                            
                            </div>
                            
                          </div><!--/./panel panel-default-->
                          
                          <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                              <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                 Current Medications:
                                </a>
                              </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                              <div class="panel-body">
                                <a href="../PatientOthers/AddMedication.jsp?userID=<%=userID %>">Add New</a>
                                <div style="clear:both"></div>
                                <hr/>
                                <div class="table-responsive">
                                    <%if(medDTOListCur.size()>0){%>
                                		<table class="table table-bordered table-striped table-hover">
			                                <thead>
			                                    <tr>
				                                    <th>#</th>
				                                    <th>
				                                        <table width="100%" class="tbl-sort"><tr><td>Drugs</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
				                                    </th>
				                                    <th>
				                                        <table width="100%" class="tbl-sort"><tr><td>Date Started</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
				                                    </th>
				                                    <th>
				                                        <table width="100%" class="tbl-sort"><tr><td>Dose</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
				                                    </th>
				                                    <th>
				                                        <table width="100%" class="tbl-sort"><tr><td>ADR if any</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
				                                    </th>
				                                    <th>
				                                        <table width="100%" class="tbl-sort"><tr><td>Status</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
				                                    </th>
				                            	</tr>
			                                </thead>
                                			<tbody>
                                     			<%for(int i=0;i<medDTOListCur.size(); i++){
	                                        		PatientOthersDTO dto = medDTOListCur.get(i);%>
	                                               	<tr>
					                                    <th scope="row"><%= i+1 %></th>
					                                    <td><%=dto.getDrugs()%></td>
					                                    <td><%=dto.getDateStarted()%></td>
					                                    <td><%=dto.getDateStarted()%></td>
					                                    <td><%=dto.getADR()%></td>
					                                    <td><a href="../DrugComplete.do?id=<%=dto.id%>" >Make Complete</a></td>
				                                	</tr>
                                				<%}%>
                                			</tbody>
                            			</table>
                            		<%}%>
                        		</div><!--/./table-responsive-->
                    		</div>
                      	</div>
                 	</div><!--/./panel panel-default-->
                          
                          <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                              <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                  Past Medications:
                                </a>
                              </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                              <div class="panel-body">
                               
                         
                        <div class="table-responsive">
                            <%if(medDTOListPast.size()>0){%>
                                <table class="table table-bordered table-striped table-hover">
                                <thead>
                                    <tr>
                                    <th>#</th>
                                    <th>
                                        <table width="100%" class="tbl-sort"><tr><td>Drugs</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
                                    </th>
                                    
                                    <th>
                                        <table width="100%" class="tbl-sort"><tr><td>ADR if any</td><td valign="top"><a class="right" href="#"><span class="fa fa-sort"></span></a></td></tr></table>
                                    </th>
                                </tr>
                                    
                                </thead>
                                <tbody>
                                    <%
                                    for(int i=0, j=1; i<medDTOListPast.size(); i++,j++){
                                        PatientOthersDTO dto = medDTOListPast.get(i);%>
                                            
                                    <tr>
                                    <th scope="row"><%=i+1 %></th>
                                    <td><%=dto.getDrugs()%></td>
                                    <td><%=dto.getADR()%></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                            <%}%>
                        </div><!--/./table-responsive-->

                   

                        
                         
                        

                              </div>
                            </div>
                          </div><!--/./panel panel-default-->
                          
                         
                          
                         
                          <div style="clear:both;"></div> 
                          
                        </div>
                        
                        
                        
                        
                   
                
                <div style="color:both">&nbsp;</div>
                    </div><!--/./ibox-content-->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
    </div><!--/./gray-bg-->
</div><!--/#/wrapper-->


 	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../Assets/NewAssets/js/bootstrap.min.js"></script>
    <script src="../Assets/NewAssets/js/jquery.metisMenu.js"></script>
    <script src="../Assets/NewAssets/js/jquery.slimscroll.min.js"></script>

   

    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>

    <!-- Page-Level Scripts -->
    <script>
       $('#myModal').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  });
	  
    </script>

</body>
</html>