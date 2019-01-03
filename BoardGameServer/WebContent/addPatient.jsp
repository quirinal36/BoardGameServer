<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("addPatient.jsp");

String name = request.getParameter("name");
String ageStr = request.getParameter("age");
String doctor = request.getParameter("doctor");
String memo = request.getParameter("memo");
String room = request.getParameter("room");
String photo = request.getParameter("photo");
String birth = request.getParameter("birth");
String sex = request.getParameter("sex");
String phone = request.getParameter("phone");
String address = request.getParameter("address");
String etc = request.getParameter("etc");
String patientId = request.getParameter("patientId");

Patient patient = new Patient();
patient.setName(name);
patient.setAge(ageStr);
patient.setDoctor(doctor);
patient.setMemo(memo);
patient.setRoom(room);
patient.setPhoto(photo);
patient.setBirth(birth);
patient.setSex(sex);
patient.setPhone(phone);
patient.setAddress(address);
patient.setEtc(etc);
patient.setPatientId(patientId);

DBconn dbconn = new DBconn();

logger.info(patient.toString());
PatientControl control = new PatientControl();

JSONObject json = new JSONObject();
json.put("result", control.insertPatient(patient));
String result = json.toJSONString();
%>
<%=result%>