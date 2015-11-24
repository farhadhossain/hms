<%@ include file="../includes/checkLogin.jsp"%>
<%@ page language="Java" %>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html>
<html lang="en">
<head><html:base/><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dashboard :: <%=SessionManager.title%></title>
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
    <script type="text/javascript" src="../Assets/Scripts/mir-billing-script.js"></script>
    
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
       $('#myModal1').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  });
	  $('#myModal2').on('shown.bs.modal', function () {
		$('#myInput').focus();
	  })
    </script>
</head>


<body>

	<!--*********************Modal Starts from here*****************************-->
	
	<div class="modal fade" id="myModal1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">Profile</h4>
	      </div>
	      <div class="modal-body">
	        <div class="row">
	        	<div class="col-lg-4">
	        		<img alt="image" class="img-rounded"  src="../Assets/NewAssets/images/img_billur_Rahman.jpg" />
	        	</div>
	        	<div class="col-lg-6">
	        		<div class="form-group">
	        			<label>Prof. Quazi Billur Rahman</label>
	        		</div>
	        		<div class="form-group">
	        			<label>Professor</label>
	        		</div>
	        		<div class="form-group">
	        			<label>Tel : 88-02-8620200</label>
	        		</div>
	        		<div class="form-group">
	        			<label>PABX : 4572</label>
	        		</div>
	        		<div class="form-group">
	        			<label>Email: billu2002bd@yahoo.com</label>
	        		</div>
	        		
	        	</div>
	        	<div class="col-lg-2"></div>
	        	<div hr-line-dashed"></div>
	        	<div class="col-lg-12" >
	        		<p> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
	        		</p>
	        	</div>
	        	
	        	
	        </div>
	        
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="myModal2">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">Profile</h4>
	      </div>
	      <div class="modal-body">
	        <div class="row">
	        	<div class="col-lg-4">
	        		<img alt="image" class="img-rounded"  src="../Assets/NewAssets/images/dummy_doc.jpg" />
	        	</div>
	        	<div class="col-lg-6">
	        		<div class="form-group">
	        			<label>Prof. Quazi Billur Rahman</label>
	        		</div>
	        		<div class="form-group">
	        			<label>Professor</label>
	        		</div>
	        		<div class="form-group">
	        			<label>Tel : 88-02-8620200</label>
	        		</div>
	        		<div class="form-group">
	        			<label>PABX : 4572</label>
	        		</div>
	        		<div class="form-group">
	        			<label>Email: billu2002bd@yahoo.com</label>
	        		</div>
	        		
	        	</div>
	        	<div class="col-lg-2"></div>
	        	<div hr-line-dashed"></div>
	        	<div class="col-lg-12" >
	        		<p> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
	        		</p>
	        	</div>
	        	
	        	
	        </div>
	        
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
    <!--************************************************************************-->
    
    
    
    
    
    
    <!--*********************Body part Starts from here************************-->
    <!--***********************************************************************-->
    <div id="wrapper">
        <%@ include file="../includes/leftNav.jsp"%><!--sidebar-->

        <div id="page-wrapper" class="gray-bg">
        <%@ include file="../includes/topNav.jsp" %> <!--top NavBar-->
        
        <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>Credentials</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="Dashboard.jsp">Dashboard</a>                        
                        </li>
                        
                        <li class="active">
                            <strong>Credentials</strong>                        
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">                
                </div>
        </div><!--/./row wrapper border-bottom white-bg page-heading-->
        
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
            	<div class="col-lg-12">
                
             	<div style="clear:both"></div>
   				 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Dashboard</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>                            
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>                            
                             </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#">Config option 1</a></li>
                                <li><a href="#">Config option 2</a></li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>                        
                        </div>
                    </div><!--/./ibox-title-->
                    <div class="ibox-content">
	                   <div class="row">
	                 		<div col-lg-12>
	                 			<a class="thumbnail" href="#" data-toggle="modal" data-target="#myModal1">
		                 			<div class="row">
			            				<div class="credential">
	                               			<img alt="image" class="img-circle"  src="../Assets/NewAssets/images/img_billur_Rahman.jpg" />
			      						
			      							<blockquote style="">
			          								We have been using Icetheme template for a number of years with great success. I find their attention to detail very unique in all of their work. Their templates have been easy to work with
		      							 	</blockquote>
		      							 	
	      							 	</div>
      							 	</div>
		       					</a>
	                 		
	                 		</div>
	                 		
	                 		<div col-lg-12>
	                 			<a class="thumbnail" href="#" data-toggle="modal" data-target="#myModal2">
		                 			<div class="row">
			            				<div class="credential">
			            					<blockquote style="">
			          								We have been using Icetheme template for a number of years with great success. I find their attention to detail very unique in all of their work. Their templates have been easy to work with
		      							 	</blockquote>
	                               			<img alt="image" width="170" height="170" class="img-circle"  src="../Assets/NewAssets/images/druzzal.jpg" />

	      							 	</div>
      							 	</div>
		       					</a>
	                 		
	                 		</div>
  						</div>
             
                    </div><!--/./ibox-content-->
                </div><!--/./ibox float-e-margins-->
            </div><!--/./col-lg-12-->

           </div> <!--/./row-->
        </div><!--/./wrapper wrapper-content animated fadeInRight-->
        
        <%@ include file="../includes/newFooter.jsp"%><!-- footer -->
    </div><!--/./gray-bg-->
</div><!--/#/wrapper-->


</body>

</html>