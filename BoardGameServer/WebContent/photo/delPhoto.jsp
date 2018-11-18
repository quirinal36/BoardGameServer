<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String photoId = request.getParameter("id");
Photo photo = new Photo();
photo.setPhotoId(photoId);

JSONObject result = new JSONObject();

PhotoControl control = new PhotoControl();

result.put("result", control.deletePhotoById(photo));
out.print(result.toString());
%>