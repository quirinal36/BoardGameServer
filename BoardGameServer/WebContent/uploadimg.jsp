<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Logger logger = Logger.getLogger("uploadimg.jsp");
	final String path = getServletContext().getRealPath("/upload");
	String imgEncodedStr = request.getParameter("image");
	String fileName = request.getParameter("filename");
	logger.info("Filename: " + fileName);
	if (imgEncodedStr != null) {
		//ManipulateImage.convertStringtoImage(imgEncodedStr, fileName);
		try {
			// Decode String using Base64 Class
			byte[] imageByteArray = Base64.decodeBase64(imgEncodedStr); 

			// Write Image into File system - Make sure you update the path
			File file = new File(path +File.separator + fileName);
			FileOutputStream imageOutFile = new FileOutputStream(file);
			imageOutFile.write(imageByteArray);

			imageOutFile.close();

			System.out.println("Image Successfully Stored");
			JSONObject json = new JSONObject();
			
			json.put("result", 1);
			json.put("fileurl", "http://upload.uffda.kr/upload/");
			
			out.print(json.toJSONString());
		} catch (FileNotFoundException fnfe) {
			System.out.println("Image Path not found" + fnfe);
		} catch (IOException ioe) {
			System.out.println("Exception while converting the Image " + ioe);
		}
		System.out.println("Inside if");
		out.print("Image upload complete, Please check your directory");
	} else {
		System.out.println("Inside else");
		out.print("Image is empty");
	}
%>
