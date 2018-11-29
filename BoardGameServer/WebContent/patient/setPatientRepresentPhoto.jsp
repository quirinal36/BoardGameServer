<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.bean.Doctor"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPatients.jsp");

String photoId = request.getParameter("photoId");
String patientId = request.getParameter("patientId");

Patient patient = new Patient(patientId);
patient.setPhotoId(photoId);

String data = new String();
PatientControl control = new PatientControl();

DBconn dbconn = new DBconn();
JSONObject json = new JSONObject();
json.put("result", control.setPatientRepresentPhoto(patient));
out.print(json.toString());
%>
