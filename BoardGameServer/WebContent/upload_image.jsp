<%@page import="com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.sun.org.apache.xml.internal.security.utils.Base64"%>
<%@ page import="com.sun.org.apache.xml.internal.security.*" %>
<%
Init.init();
String file = request.getParameter("file");
byte b[] = file.getBytes();
try(FileOutputStream fo = new FileOutputStream("/upload/uploaded.jpg")){
	byte bb[] = Base64.decode(b);
	fo.write(bb);
}catch(Base64DecodingException e){
	e.printStackTrace();
}
out.print("successfully uploaded");
%>