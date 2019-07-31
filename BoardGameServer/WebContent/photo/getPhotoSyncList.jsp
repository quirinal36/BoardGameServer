<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.bean.PhotoPatientInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%
Logger logger = Logger.getLogger("/photo/getPhotoSyncList.jsp");

String classification = request.getParameter("class");
String sync = request.getParameter("sync");

Photo photo = new Photo();
photo.setClassification(classification);
if(sync != "3") { //sync 3으로 조회 불가 - 과부하
	photo.setSync(sync);
}

logger.info("params :"+sync+"/"+classification);

PhotoControl control = new PhotoControl();
List<PhotoPatientInfo> list = control.getPhotoList(photo);
JSONObject obj = control.toJSONObject(list);
out.print(obj.toString());
%>