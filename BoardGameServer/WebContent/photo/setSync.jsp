<%@page import="org.json.JSONObject"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("/photo/setSync.jsp");

String id = request.getParameter("id");
String sync = request.getParameter("sync");

if(sync==null || sync.length()==0){
	sync = "3";
}
logger.info(id+"/"+sync);
Photo photo = new Photo();
try{
photo.setPhotoId(Integer.parseInt(id));
}catch(NumberFormatException e){
	e.printStackTrace();
}
photo.setSync(sync);

PhotoControl control = new PhotoControl();
int result = control.updatePhotoSync(photo);
JSONObject json = new JSONObject();
json.put("result", result);
out.print(json.toString());
%>