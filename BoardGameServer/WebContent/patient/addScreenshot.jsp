<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("/addScreenshot.jsp");
String patientId = request.getParameter("patientId");
String name = request.getParameter("name");
String birth = request.getParameter("birth");
String sex = request.getParameter("sex");
String address = request.getParameter("address");
String phone = request.getParameter("phone");
String photoUrl = request.getParameter("photoUrl");
String uploader = request.getParameter("uploader");

Patient patient = new Patient();
patient.setPatientId(patientId);

PatientControl control = new PatientControl();
patient = control.getPatientById(patientId);

int result = 0;
if(patient != null && patient.getId()>0){
	patient.setName(name);
	patient.setBirth(birth);
	patient.setSex(sex);
	patient.setAddress(address);
	patient.setPhone(phone);
	
	if(photoUrl!=null && photoUrl.length()>0){
		PhotoControl photoControl = new PhotoControl();
		Photo photoInfo = new Photo();
		photoInfo.setPatientId(patientId);
		photoInfo.setPhotoUrl(photoUrl);
		photoInfo.setDoctor(patient.getDoctor());
		photoInfo.setUploader(uploader);
		
		photoControl.addPhotoInfo(photoInfo);
	}
	result = control.updatePatient(patient);
}else{
	patient.setName(name);
	patient.setBirth(birth);
	patient.setSex(sex);
	patient.setAddress(address);
	patient.setPhone(phone);
	patient.setPhoto(photoUrl);
	result = control.insertPatient(patient);
}
JSONObject json = new JSONObject();
json.put("result", result);
out.print(json.toString());
%>