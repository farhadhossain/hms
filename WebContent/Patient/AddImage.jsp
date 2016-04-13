<%@ include file="../includes/checkLogin.jsp"%>
<%
if(loginDTO!=null){
	if(loginDTO.getClientType()!=-1 || RoleRepository.isPermitted(loginDTO.getRoleID(), PermissionDTO.PatientEdit)==false){
		response.sendRedirect("../");
	}
}
%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String accountID= request.getParameter("accountID");
%>
<html lang="en">
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SessionManager.title%></title>
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
    <script type="text/javascript" src="../Assets/Scripts/mir-billing-script.js"></script>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../Assets/NewAssets/js/bootstrap.min.js"></script>
    <script src="../Assets/NewAssets/js/jquery.metisMenu.js"></script>
    <script src="../Assets/NewAssets/js/jquery.slimscroll.min.js"></script>
	<script>
	
	   function AlertFilesize(el){
		   try{
			   	if(window.ActiveXObject){
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
			    }
			    //alert(fSize);
			    //alert(fSize+' '+fSExt[i]);
		   }catch(e){
			   alert(e);
		   }
	    }
	</script>
    <!-- Custom and plugin javascript -->
    <script src="../Assets/NewAssets/js/custom.js"></script>
    <script src="../Assets/NewAssets/js/pace.min.js"></script>	
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
                <div style="clear:both"></div>
   				<div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Upload Patient Images</h5> 
                    </div><!--/./ibox-title-->
					
                    <div class="ibox-content">
						<form action="upload_file_multipale.jsp?accountID=<%=accountID %>" method="post"  onsubmit="return validationResult();" enctype="multipart/form-data" name="form1" id="form1">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-4 control-label">Image 1</label>
									<div class="col-sm-4">
										<input name="file" type="file" id="file1" onchange="AlertFilesize('file1');">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Image 2</label>
									<div class="col-sm-4">
										<input name="file" type="file" id="file2" onchange="AlertFilesize('file2');">
									</div>
								</div>
							
								<div class="form-group">
									<label class="col-sm-4 control-label">Image 3</label>
									<div class="col-sm-4">
										<input name="file" type="file" id="file3" onchange="AlertFilesize('file3');">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
										<input type="submit" value="Submit" class="btn btn-primary">
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