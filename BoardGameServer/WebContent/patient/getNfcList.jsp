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

JSONObject json = new JSONObject();

try{
	PatientControl control = new PatientControl();
	json.put("list", control.getNfcListJson(nfc));
}catch(NumberFormatException e){
	e.printStackTrace();
}

out.print(json.toString());
%>