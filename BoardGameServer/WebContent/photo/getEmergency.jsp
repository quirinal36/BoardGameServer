<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.bean.PhotoPatientInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<%
String day = request.getParameter("day");
Photo photo = new Photo();
photo.setDay(day);
photo.setClassification("응급");
PhotoControl control = new PhotoControl();
List<PhotoPatientInfo> list = control.getEmergencyPhotos(photo);
JSONObject obj = control.toJSONObject(list);
out.print(obj.toString());
%>