<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("addPatient.jsp");

String name = request.getParameter("name");
String photo = request.getParameter("photo");

Patient patient = new Patient();
patient.setName(name);
patient.setPhoto(photo);

DBconn dbconn = new DBconn();

JSONObject json = new JSONObject();
json.put("result", dbconn.insertPatient(patient));
String result = json.toJSONString();
%>
<%=result%>