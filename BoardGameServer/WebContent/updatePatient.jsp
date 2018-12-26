<%@page import="kr.bacoder.coding.control.PatientControl"%>
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
//String id = request.getParameter("id");
String birth = request.getParameter("birth");
String sex = request.getParameter("sex");
String phone = request.getParameter("phone");
String address = request.getParameter("address");
String etc = request.getParameter("etc");
String age = request.getParameter("age");
String doctor = request.getParameter("doctor");
String memo = request.getParameter("memo");
String room = request.getParameter("room");
String admission = request.getParameter("admission");
String patientId = request.getParameter("patientId");
logger.info("admission: " + admission);

Patient patient = new Patient();
patient.setName(name);
patient.setPhoto(photo);
patient.setBirth(birth);
patient.setSex(sex);
patient.setPhone(phone);
patient.setAddress(address);
patient.setEtc(etc);
//patient.setId(Integer.parseInt(id));
patient.setAge(age);
patient.setDoctor(doctor);
patient.setMemo(memo);
patient.setRoom(room);
patient.setPatientId(patientId);
if(admission!=null && admission.length()>0){
	boolean adm = Boolean.parseBoolean(admission);
	logger.info("adm: " + adm);
	patient.setAdmission(adm);
}

DBconn dbconn = new DBconn();
PatientControl control = new PatientControl();

logger.info(patient.toString());

JSONObject json = new JSONObject();
json.put("result", control.updatePatient(patient));
String result = json.toJSONString();
%>
<%=result%>