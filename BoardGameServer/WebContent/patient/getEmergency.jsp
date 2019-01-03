<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getEmergency.jsp");
String room = request.getParameter("room");

PatientControl control = new PatientControl();
List<Patient> patients = control.getPatientByClassification(room);
JSONArray array = control.parseToJsonArray(patients);
JSONObject object = new JSONObject();
object.put("list", array);
out.print(object.toString());
%>