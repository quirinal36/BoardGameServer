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
<%@page import="java.util.logging.Logger"%>
<%
	final String path = getServletContext().getRealPath("/upload");
	Logger logger = Logger.getLogger("signup.jsp");
	
	String imgEncodedStr = request.getParameter("image");
	
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String uniqueId = request.getParameter("uniqueId");
	//String password = request.getParameter("password");
	String department = request.getParameter("department");
	String userLevel = request.getParameter("userLevel");
	
	Person person = new Person();
	person.setName(name);
	person.setPhone(phone);
	person.setEmail(email);
	//person.setPassword(password);
	person.setUniqueId(uniqueId);
	person.setDepartment(department);
	person.setUserLevel(userLevel);
	
	JSONObject json = new JSONObject();
	
	if (imgEncodedStr != null) {
		String fileName = request.getParameter("filename");
		person = new UploadUtil().setFile(path, imgEncodedStr, fileName, person);
	}
	
	DBconn dbconn = new DBconn();
	PersonControl control = new PersonControl();
	logger.info(person.toString());
	
	json.put("result", control.updatePerson(person));
	
	out.print(json.toJSONString());
%>
