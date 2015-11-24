<%@ include file="../includes/checkLogin.jsp"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*,java.sql.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%
 	String accountID = request.getParameter("accountID");
	
	String fileNameHeader1=loginDTO.getUserID()+"_"+System.currentTimeMillis()+"_";
	String fileNameHeader2=loginDTO.getUserID()+"_"+System.currentTimeMillis()+"_";
	String fileNameHeader3=loginDTO.getUserID()+"_"+System.currentTimeMillis()+"_";
	
	File file ;
	String fileName="", fileName1=null, fileName2=null, fileName3=null;
   	int maxFileSize = 5000 * 1024;
   	int maxMemSize = 5000 * 1024;
   	ServletContext context = pageContext.getServletContext();
   	String filePath = context.getInitParameter("file-upload");
    String contentType = request.getContentType();
    
   	if((contentType.indexOf("multipart/form-data") >= 0)) {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
    	factory.setSizeThreshold(maxMemSize);
      	factory.setRepository(new File("c:\\temp"));
	    ServletFileUpload upload = new ServletFileUpload(factory);
    	upload.setSizeMax( maxFileSize );
      	try{
    	   	List fileItems = upload.parseRequest(request);
	    	Iterator i = fileItems.iterator();
	        while(i.hasNext()){
            	FileItem fi = (FileItem)i.next();
            	if(!fi.isFormField()){
            		String fieldName = fi.getFieldName();
	            	fileName = fi.getName();
	            	
	            	if(fileName!=null && fileName.length()>0){
		            	boolean isInMemory = fi.isInMemory();
		            	long sizeInBytes = fi.getSize();
		            	String currentFileNameHeader="";
		            	if(fileName1==null){
		            		fileName1=fileName;
		            		currentFileNameHeader=fileNameHeader1;
		            	}else if(fileName2==null){
		            		fileName2=fileName;
		            		currentFileNameHeader=fileNameHeader2;
		            	}else if(fileName3==null){
		            		fileName3=fileName;
		            		currentFileNameHeader=fileNameHeader3;
		            	}
		            	
		            	if( fileName.lastIndexOf("\\") >= 0 ){
			            	//file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            		file = new File( filePath, currentFileNameHeader+fileName.substring( fileName.lastIndexOf("\\"))) ;
			            }else{
			            	//file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			            	file = new File( filePath, currentFileNameHeader+fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            	}
		            	
		            	fi.write(file);
	            	}
            	}
         	}
      	}catch(Exception ex) {
        	System.out.println(ex);
      	}
      	
      	Connection conn=null;
      	Statement stmt=null;
      	try{
      		conn = DBMySQLConnection.DatabaseConnection.ConnectionManager();
      		stmt = conn.createStatement();
      		
      		String sql="";
      		
      		if(fileName1!=null){
	      		sql="insert into tbl_patient_images(patient_id, file_name) values("+accountID+", '"+fileNameHeader1+fileName1+"')";
	      		System.out.println(sql);
	      		stmt.execute(sql);
      		}
      		
      		if(fileName2!=null){
	      		sql="insert into tbl_patient_images(patient_id, file_name) values("+accountID+", '"+fileNameHeader2+fileName2+"')";
	      		System.out.println(sql);
	      		stmt.execute(sql);
      		}
      		
      		if(fileName3!=null){
	      		sql="insert into tbl_patient_images(patient_id, file_name) values("+accountID+", '"+fileNameHeader3+fileName3+"')";
	      		System.out.println(sql);
	      		stmt.execute(sql);
      		}
      		
      		session.setAttribute(SessionManager.PatientAdd, "Uploaded Successfully");
      		
      	}catch(Exception e){
      		session.setAttribute(SessionManager.PatientAdd, e.toString());
    		System.out.println(e.toString());
    	}finally{
    		try{conn.close();}catch(Exception e){}			
    	}
      	
   	}
%>
<%response.sendRedirect("../Patient/SearchPatient.jsp");%>