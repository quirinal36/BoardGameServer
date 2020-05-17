<%@page import="kr.bacoder.coding.control.PersonControl"%>
<%@page import="kr.bacoder.coding.dev.UploadUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.util.logging.Logger"%>
<%
	final String path = getServletContext().getRealPath("/upload");
	Logger logger = Logger.getLogger("signUp.jsp");
	
	
	  String body = null;
	  StringBuilder stringBuilder = new StringBuilder();
	  BufferedReader bufferedReader = null;

	 	 try {
	 	     InputStream inputStream = request.getInputStream();
	 	     if (inputStream != null) {
	 	         bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	 	         char[] charBuffer = new char[128];
	 	         int bytesRead = -1;
	 	         while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	 	             stringBuilder.append(charBuffer, 0, bytesRead);
	 	         }
	 	     } else {
	 	         stringBuilder.append("");
	 	     }
	 	 } catch (IOException ex) {
	 	     throw ex;
	 	 } finally {
	 	     if (bufferedReader != null) {
	 	         try {
	 	             bufferedReader.close();
	 	         } catch (IOException ex) {
	 	             throw ex;
	 	         }
	 	     }
	 	 }

	 	 	body = stringBuilder.toString();
	 	 	JSONParser jsonParser = new JSONParser();
	 	 	Object obj = jsonParser.parse( body );
	 	 	JSONObject jsonRequest = (JSONObject) obj;

	 	 	
	 		String imgEncodedStr = (String) jsonRequest.get("image");
	 		String name = (String) jsonRequest.get("name");
	 		String phone = (String) jsonRequest.get("phone");
	 		String birth = (String) jsonRequest.get("birth");
	 		String sex = (String) jsonRequest.get("sex");
	 		String uuid = (String) jsonRequest.get("uuid");
	 
	
/* 	String imgEncodedStr = request.getParameter("image");
	
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String birth = request.getParameter("birth");
	String sex = request.getParameter("sex");
	String uuid = request.getParameter("uuid"); */
	
	logger.info("name:"+name);
	
	
	Person person = new Person();
	person.setName(name);
	person.setPhone(phone);
	person.setBirth(birth);
	person.setSex(sex);
	person.setUuid(uuid);
	person.setUserLevel(1);
	
	JSONObject json = new JSONObject();
	
	if (imgEncodedStr != null) {
		String fileName = request.getParameter("filename");
		person = new UploadUtil().setFile(path, imgEncodedStr, fileName, person);
	}
	
	DBconn dbconn = new DBconn();
	PersonControl control = new PersonControl();
	logger.info(person.toString());
	
	if(person.getName() != null && person.getUuid() != null) {
		if(control.getPersonByUuid(uuid) == 0) {
			json.put("result", control.insertPersonByUuid(person));
		} else {
			json.put("result", -1);
		}
	} else {
		json.put("result", 0);
	}
	
	out.print(json.toJSONString());
%>
