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
	Logger logger = Logger.getLogger("signUp.jsp");
	
	String imgEncodedStr = request.getParameter("image");
	
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String birth = request.getParameter("birth");
	String sex = request.getParameter("sex");
	String uuid = request.getParameter("uuid");
	
	Person person = new Person();
	person.setName(name);
	person.setPhone(phone);
	person.setBirth(birth);
	person.setSex(sex);
	person.setUuid(uuid);
	
	JSONObject json = new JSONObject();
	
	if (imgEncodedStr != null) {
		String fileName = request.getParameter("filename");
		person = new UploadUtil().setFile(path, imgEncodedStr, fileName, person);
	}
	
	DBconn dbconn = new DBconn();
	PersonControl control = new PersonControl();
	logger.info(person.toString());
	
	json.put("result", control.insertPersonByUuid(person));
	
	out.print(json.toJSONString());
%>
