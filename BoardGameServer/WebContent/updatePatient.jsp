<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("updatePatient.jsp");

String name = request.getParameter("name");
String photo = request.getParameter("photo");
String id = request.getParameter("id");

Patient patient = new Patient();
patient.setName(name);
patient.setPhoto(photo);
patient.setId(Integer.parseInt(id));

DBconn dbconn = new DBconn();

logger.info(patient.toString());

JSONObject json = new JSONObject();
json.put("result", dbconn.updatePatient(patient));
String result = json.toJSONString();
%>
<%=result%>