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
<%@page import="java.util.logging.Logger"%>
<%
	final String path = getServletContext().getRealPath("/upload");
	Logger logger = Logger.getLogger("signup.jsp");
	
	String imgEncodedStr = request.getParameter("image");
	String fileName = request.getParameter("filename");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String device_id = request.getParameter("unique_id");
	String password = request.getParameter("password");
	String department = request.getParameter("department");
	
	Person person = new Person();
	person.setName(name);
	person.setPhone(phone);
	person.setEmail(email);
	person.setPassword(password);
	person.setUniqueId(device_id);
	person.setDepartment(department);
	
	JSONObject json = new JSONObject();
	
	if (imgEncodedStr != null) {
		person = new UploadUtil().setFile(path, imgEncodedStr, fileName, person);
	}
	
	DBconn dbconn = new DBconn();
	logger.info(person.toString());
	
	json.put("result", dbconn.insertPerson(person));
	
	out.print(json.toJSONString());
	System.out.println("Inside if");
%>
