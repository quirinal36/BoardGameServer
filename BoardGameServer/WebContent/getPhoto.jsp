<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="kr.bacoder.coding.dev.TokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPhoto.jsp");
String patientId = request.getParameter("patientId");
String tokenStr = request.getHeader("Authorization");

JSONObject resultJson = new JSONObject();
DBconn dbconn = new DBconn();
Photo photo = new Photo();
photo.setPatientId(patientId);
PhotoControl control = new PhotoControl();

TokenUtil token = new TokenUtil();

logger.info("IsValidToken : " + token.IsValidToken(tokenStr));

if(tokenStr != null && token.IsValidToken(tokenStr) >= 0) {
	JSONArray array = control.getOnlyPhotos(photo);
	resultJson.put("list", array);
	out.print(resultJson.toJSONString());
} else {
	 logger.info("getPhoto/IsValidToken : 0");
 	response.sendError(401, token.unauthorized);
}
%>