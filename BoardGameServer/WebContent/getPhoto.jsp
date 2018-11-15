<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPhoto.jsp");
String patientId = request.getParameter("patientId");

JSONObject resultJson = new JSONObject();
DBconn dbconn = new DBconn();
Photo photo = new Photo();
photo.setPatientId(patientId);

JSONArray array = dbconn.getPhotos(photo);
resultJson.put("list", array);
out.print(resultJson.toJSONString());
%>