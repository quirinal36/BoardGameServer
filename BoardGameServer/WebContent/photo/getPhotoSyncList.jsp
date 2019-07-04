<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.bean.PhotoPatientInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<%
String classification = request.getParameter("class");
String sync = request.getParameter("sync");

if(classification==null || classification.length()==0){
	classification = "수술중";
}

Photo photo = new Photo();
photo.setClassification(classification);
photo.setSync(sync);

PhotoControl control = new PhotoControl();
List<PhotoPatientInfo> list = control.getPhotoList(photo);
JSONObject obj = control.toJSONObject(list);
out.print(obj.toString());
%>