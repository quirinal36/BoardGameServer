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
String id = request.getParameter("id");
String birth = request.getParameter("birth");
String sex = request.getParameter("sex");
String phone = request.getParameter("phone");
String address = request.getParameter("address");
String etc = request.getParameter("etc");

Patient patient = new Patient();
patient.setName(name);
patient.setPhoto(photo);
patient.setBirth(birth);
patient.setSex(sex);
patient.setPhone(phone);
patient.setAddress(address);
patient.setEtc(etc);
patient.setId(Integer.parseInt(id));

DBconn dbconn = new DBconn();
PatientControl control = new PatientControl();

logger.info(patient.toString());

JSONObject json = new JSONObject();
json.put("result", control.updatePatient(patient));
String result = json.toJSONString();
%>
<%=result%>