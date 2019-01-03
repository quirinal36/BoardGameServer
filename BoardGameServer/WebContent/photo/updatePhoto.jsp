<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("/photo/updatePhoto.jsp");

String comment = request.getParameter("comment");
String classification = request.getParameter("classification");
String doctor = request.getParameter("doctor");
String id = request.getParameter("id");

Photo photo = new Photo();
photo.setPhotoId(id);
photo.setComment(comment);
photo.setClassification(classification);
photo.setDoctor(doctor);

PhotoControl control = new PhotoControl();
int result = control.updatePhoto(photo);
JSONObject json = new JSONObject();
json.put("result", result);
out.print(json.toString());
%>