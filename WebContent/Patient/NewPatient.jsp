<%@ include file="../includes/checkLogin.jsp"%>
<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientAdd)==false){
		response.sendRedirect("../");
	}
}
%>
<%@page import="status.StatusService"%>
<%@page import="java.util.HashMap"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<html lang="en">
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SessionManager.title%></title>
<!--<link rel="stylesheet" type="text/css" href="../Assets/Fonts/fontface.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/united-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/main.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/sidebar-menu.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/style.css" />
<link rel="stylesheet" type="text/css" href="../Assets/Styles/tableOfTanvir.css" />

    -->
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link href="../Assets/NewAssets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Font -->
    <link href="../Assets/NewAssets/css/font-awesome.css" type="text/css" rel="stylesheet">
    <!-- Animation -->
    <link href="../Assets/NewAssets/css/animate.css" type="text/css" rel="stylesheet">
    <!-- Custom css -->
    <link href="../Assets/NewAssets/css/style.css" type="text/css" rel="stylesheet">
    
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
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../Assets/NewAssets/js/bootstrap.min.js"></script>
    <script src="../Assets/NewAssets/js/jquery.metisMenu.js"></script>
    <script src="../Assets/NewAssets/js/jquery.slimscroll.min.js"></script>

   

    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>

	<script>

	function isNumber(s)
    {
      for(var i=0;i<s.length;i++)
      {  
          if(s.charAt(i)=='1'||s.charAt(i)=='2'|| s.charAt(i)=='3'||s.charAt(i)=='4'|| s.charAt(i)=='5'||s.charAt(i)=='6'|| s.charAt(i)=='7'||s.charAt(i)=='8'|| s.charAt(i)=='9'|| s.charAt(i)=='0'|| s.charAt(i)=='.')
            continue;
          else 
            break;   
      }
      if(i==s.length)
          return true;
      else
          return false;      
    }
    function validationResult(){
    	var f = document.forms[0];
    	if(f.name.value==null || f.name.value==''){
          alert("Please enter name of the patient");
          return false;
        }
    	if(f.age.value==null || f.age.value=='' || isNumber(f.age.value)==false){
          alert("Please specify patient age");
          return false;
        }
    	if(f.ticketNumber.value==null || f.ticketNumber.value==''){
            alert("Please enter a valid Ticket Number");
            return false;
          }
        return true;
    }

	function showReferedDeptDetails(deptId){
       	if(deptId==5){
       		document.getElementById('otherDeptRefDetails').style.display = 'block';
       	}else{
       		document.getElementById('otherDeptRefDetails').style.display = 'none';
       	}	
    }
	
    
    function AlertFilesize(el){
		   try{
			   	/*if(window.ActiveXObject){
			        var fso = new ActiveXObject("Scripting.FileSystemObject");
			        var filepath = document.getElementById(el).value;
			        var thefile = fso.getFile(filepath);
			        var sizeinbytes = thefile.size;
			    }else{
				    var sizeinbytes = document.getElementById(el).files[0].size;
				}
	
			    var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
			    fSize = sizeinbytes; 
			    i=0;
			    while(fSize>900){
				    fSize/=1024;
				    i++;
				}
			    fSize=Math.round(fSize*100)/100;
			    if(i>1){
			    	document.getElementById(el).value='';
			    	alert("Invalid Size. File Size Should Not Be Greater Then 100KB");
			    }else if(i==1 && fSize>100){
			    	document.getElementById(el).value='';
			    	alert("Invalid Size. File Size Should Not Be Greater Then 100KB");
			    }*/
			    //alert(fSize);
			    //alert(fSize+' '+fSExt[i]);
		   }catch(e){
			   alert(e);
		   }
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
                 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Add New Patient</h5>
                    </div><!--/./ibox-title-->
					<%if(session.getAttribute(SessionManager.PatientAdd)!=null){%>
			        	<div class="alert alert-success" id="AlertSuccess" ><%=session.getAttribute(SessionManager.PatientAdd)%></div>
			        	<%session.removeAttribute(SessionManager.PatientAdd);
			        }%>
					
                    <div class="ibox-content">
						<form action="patient_add_with_img.jsp" method="post"  onsubmit="return validationResult();" enctype="multipart/form-data" name="form1" id="form1">
							<div class="form-horizontal">
						`		
								<div class="form-group">
									<label class="col-sm-4 control-label">Name <span class="red">*</span></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="name">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Age <span class="red">*</span></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="age">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Sex <span class="red">*</span></label>
									<div class="col-sm-4">
										<select class="form-control m-b" name="sex">
											<option value="Male">Male</option>
											<option Value="Female">Female</option>
											<option value="Others">Others</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Ticket Number <span class="red">*</span></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="ticketNumber">
									</div>
								</div>
								
								<div class="form-group" style="display: none;">
									<label class="col-sm-4 control-label">Admitted Registration Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="regNumber" value="">
									</div>
								</div>
								
								<div class="form-group" style="display: none;">
									<label class="col-sm-4 control-label">Blood group</label>
									<div class="col-sm-4">
										<select class="form-control m-b" name="bloodGroup">
											<option value="Unknown">Unknown</option>
											<option value="O+">O+</option>
											<option value="A+">A+</option>
											<option value="B+">B+</option>
											<option value="AB+">AB+</option>
											<option value="O-">O-</option>
											<option value="A-">A-</option>
											<option value="B-">B-</option>
											<option value="AB-">AB-</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Telephone Number</label>
									<div class="col-sm-4">
										<input class="form-control" type="text" name="telephoneNum">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Address</label>
									<div class="col-sm-4">
										<textarea class="form-control" rows="2" name="presentAdd"></textarea>
									</div>
								</div>
								
								<div class="form-group" style="display: none;">
									<label class="col-sm-4 control-label">Permanent Address</label>
									<div class="col-sm-4">
										<textarea class="form-control" rows="2" name="permanentAdd"></textarea>
									</div>
								</div>
								
								<div class="form-group" style="display: none;">
									<label class="col-sm-4 control-label">Admited To</label>
									<div class="col-sm-4">
										<select class="form-control m-b" name="deptId" id="deptId" onchange="showReferedDeptDetails(this.value)">
											<%
											HashMap<Integer, String> statusList = new StatusService().getAllStatus();
											for (int key : statusList.keySet()) {%>
												<option value="<%=key%>"><%=statusList.get(key)%></option>
											<%}%>																						
										</select>
									</div>
								</div>
								
								<div class="form-group" id="otherDeptRefDetails" style="display: none;">
									<label class="col-sm-4 control-label">Refered To Other Department Details</label>
									<div class="col-sm-4">
										<select class="form-control m-b" name="otherDeptRefId" id="otherDeptRefId">
											<%for (int key : MyConfig.refToOtherDeptDetails.keySet()) {%>
												<option value="<%=key%>"><%=MyConfig.refToOtherDeptDetails.get(key)%></option>
											<%}%>
										</select>
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-sm-4 control-label">Image</label>
									<div class="col-sm-4">
										<input type="file" id="file1" name="file1" size="20" onchange="AlertFilesize('file1');">
									</div>
								</div>
							
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
										<input type="submit" value="Save" class="btn btn-primary">
									</div>
								</div>
							</div>
						</form>
					</div><!-- /ibox-content -->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
    </div><!--/./gray-bg-->
</div><!--/#/wrapper-->


</body>

</html>