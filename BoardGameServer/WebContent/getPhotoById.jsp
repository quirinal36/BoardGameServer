<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="kr.bacoder.coding.dev.TokenUtil"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPhotoById.jsp");
String photoId = request.getParameter("id");
String tokenStr = request.getHeader("Authorization");

logger.info("getPhotoById.jsp");

DBconn dbconn = new DBconn();
Photo photo = new Photo();
photo.setPhotoId(photoId);
PhotoControl control = new PhotoControl();
TokenUtil token = new TokenUtil();

if(tokenStr != null && token.IsValidToken(tokenStr) > 0) {
	JSONObject resultJson = control.getPhoto(photo);
	out.print(resultJson.toJSONString());
} else {
	 logger.info("getPhotoById/IsValidToken <= 0");
	response.sendError(401, token.unauthorized);
}
%>