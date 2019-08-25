<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="kr.bacoder.coding.bean.NfcTag"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String tagId = request.getParameter("tagId");
String patientId = request.getParameter("patientId");
String id = request.getParameter("id");

NfcTag nfc = new NfcTag();
nfc.setTagId(tagId);
nfc.setPatientId(patientId);
int result = 0;
if(id!=null && id.length()>0 && !id.equals("0")){
	try{
		nfc.setId(Integer.parseInt(id));
		PatientControl control = new PatientControl();
		result = control.updateNfcPatientIdbyId(nfc);
	}catch(NumberFormatException e){
		e.printStackTrace();
	}
} else if (tagId!=null && tagId.length()>0) {
	try{
		nfc.setId(Integer.parseInt(id));
		PatientControl control = new PatientControl();
		result = control.updateNfcPatientIdbytagId(nfc);
	}catch(NumberFormatException e){
		e.printStackTrace();
	}
}

JSONObject json = new JSONObject();
json.put("result", result);

out.print(json.toString());
%>