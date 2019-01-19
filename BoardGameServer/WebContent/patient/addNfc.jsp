<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="kr.bacoder.coding.bean.NfcTag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String tagId = request.getParameter("tagId");
String patientId = request.getParameter("patientId");

NfcTag nfc = new NfcTag();
nfc.setTagId(tagId);
nfc.setPatientId(patientId);

PatientControl control = new PatientControl();

int result = 0;
if(nfc.getPatientId() != null && nfc.getPatientId().length() > 0){
	result = control.insertNfc(nfc);
}else{
	result = control.deleteNfc(nfc);
}

JSONObject json = new JSONObject();
json.put("result", result);
json.put("errorMsg", control.getErrorMsg());
out.print(json.toString());
%>