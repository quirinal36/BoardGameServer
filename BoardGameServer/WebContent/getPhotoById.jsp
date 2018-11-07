<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPhotoById.jsp");
String photoId = request.getParameter("id");
logger.info("getPhotoById.jsp");

DBconn dbconn = new DBconn();
Photo photo = new Photo();
photo.setPhotoId(photoId);

JSONObject resultJson = dbconn.getPhoto(photo);
out.print(resultJson.toJSONString());
%>