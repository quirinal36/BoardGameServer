<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.bean.PhotoPatientInfo"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getEmergency.jsp");
String classification = request.getParameter("classification");
String day = request.getParameter("day");

Photo photo = new Photo();
photo.setClassification("응급");
photo.setDay(10);

if(classification!=null && classification.length()>0){
	photo.setClassification(classification);
}
if(day!=null && day.length()>0){
	photo.setDay(day);
}
PatientControl control = new PatientControl();
List<Patient> patients = control.getPatientByClassification(photo);
JSONArray array = control.parseToJsonArray(patients);
JSONObject object = new JSONObject();
object.put("list", array);

out.print(object.toString());
%>