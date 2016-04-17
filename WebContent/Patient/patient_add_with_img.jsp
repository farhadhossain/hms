<%@ include file="../includes/checkLogin.jsp"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*,patient.PatientDTO, patient.PatientService" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%@ page import="javax.imageio.*,org.apache.commons.io.FilenameUtils,java.awt.Graphics2D,java.awt.image.*" %>

<%

 	String fileNameHeader=loginDTO.getUserID()+"_"+System.currentTimeMillis()+"_";
	PatientDTO dto = new PatientDTO();
	
	File file ;
	String fileName="";
   	int maxFileSize = 5000 * 1024;
   	int maxMemSize = 5000 * 1024;
   	ServletContext context = session.getServletContext();
   	String filePath = context.getInitParameter("file-upload");
    String contentType = request.getContentType();
    
   	if((contentType.indexOf("multipart/form-data") >= 0)) {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
    	factory.setSizeThreshold(maxMemSize);
      	factory.setRepository(new File("/Users/macintosh/Documents/Farhad/apache-tomcat-8.0.20/webapps/images/"));
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
		            	if( fileName.lastIndexOf("\\") >= 0 ){
			            	//file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            		file = new File( filePath, fileNameHeader+fileName.substring( fileName.lastIndexOf("\\"))) ;
			            }else{
			            	//file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			            	file = new File( filePath, fileNameHeader+fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            	}
		            	fi.write(file);
						
						String srcPath=filePath+fileNameHeader+fileName;
						System.out.println("filePath: "+filePath);
						System.out.println("srcPath: "+srcPath);
						
						BufferedImage originalImage = ImageIO.read(new File(srcPath));
						int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
						
						int IMG_WIDTH = originalImage.getHeight();
						int IMG_HEIGHT = originalImage.getWidth();
						if(originalImage.getHeight()>=400){
							IMG_WIDTH = 350;
							IMG_HEIGHT = 400;
						}
						
						BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
						g.dispose();
						
						ImageIO.write(resizedImage, FilenameUtils.getExtension(srcPath), new File(srcPath));
						
		            	dto.imageName=fileNameHeader+fileName;
		            }
            	}else{
                	String name = fi.getFieldName();
                    String value = fi.getString();
                	System.out.println("name: "+name+"; value: "+value);
                	if(name.equals("name")){
                		dto.setName(value);
                	}else if(name.equals("age")){
                		try{
                			dto.setAge(Integer.parseInt(value));
                		}catch(Exception e){}
                	}else if(name.equals("sex")){
                		dto.setSex(value);
                	}else if(name.equals("ticketNumber")){
                		dto.setTicketNumber(value);
                	}else if(name.equals("regNumber")){
                		dto.setRegNumber(value);
                	}else if(name.equals("wordNumber")){
                		dto.setWordNumber(value);
                	}else if(name.equals("bedNumber")){
                		dto.setBedNumber(value);
                	}else if(name.equals("cabinNumber")){
                		dto.setCabinNumber(value);
                	}else if(name.equals("bloodGroup")){
                		dto.setBloodGroup(value);
                	}else if(name.equals("telephoneNum")){
                		dto.setTelephoneNum(value);
                	}else if(name.equals("presentAdd")){
                		dto.setPresentAdd(value);
                	}else if(name.equals("permanentAdd")){
                		dto.setPermanentAdd(value);
                	}else if(name.equals("deptId")){
                		dto.setDeptId(Integer.parseInt(value));
                	}else if(name.equals("otherDeptRefId")){
                		dto.setOtherDeptRefId(Integer.parseInt(value));
                	}
                }
         	}
	        
	        if(dto.imageName==null || dto.imageName.length()<=5){
	        	dto.imageName="";
	        }
	        
	        new PatientService().addNewPatient(dto, loginDTO.getUserID());
      	}catch(Exception ex) {
        	System.out.println(ex);
      	}
      	session.setAttribute(SessionManager.PatientAdd, "Uploaded Successfully");      	
   	}
%>
<%response.sendRedirect("../Patient/SearchPatient.jsp");%>