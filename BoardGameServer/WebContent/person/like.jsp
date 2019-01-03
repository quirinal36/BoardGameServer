<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.control.PersonControl"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("like.jsp");
String uniqueId = request.getParameter("uniqueId");
String patientId = request.getParameter("patientId");

Person person = new Person();
person.setUniqueId(uniqueId);

Patient patient = new Patient();
patient.setPatientId(patientId);

PersonControl control = new PersonControl();
int result = control.insertLike(person, patient);
JSONObject json = new JSONObject();
json.put("result", result);
out.print(json.toString());
%>