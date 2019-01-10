<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="kr.bacoder.coding.bean.NfcTag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String tagId = request.getParameter("tagId");

NfcTag nfc = new NfcTag();
nfc.setTagId(tagId);

PatientControl control = new PatientControl();
List<Patient> list = control.getPatientListByNfc(nfc);

JSONArray array = control.parseToJsonArray(list);
JSONObject object = new JSONObject();

if(list.size() == 0){
	object.put("result", 0);
}else if(list.size() > 0){
	object.put("result", 1);
}

object.put("list", array);
out.print(object.toString());
%>