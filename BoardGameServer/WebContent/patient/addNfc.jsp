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

%>