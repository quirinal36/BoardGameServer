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
String thumbnailName = request.getParameter("thumbnailFilename");
String fileSize = request.getParameter("fileSize");
String thumbnailSize = request.getParameter("thumbnailSize");
String sync = request.getParameter("sync");

Photo photo = new Photo();
photo.setPhotoId(id);
photo.setComment(comment);
photo.setClassification(classification);
photo.setDoctor(doctor);
if(thumbnailName != null && thumbnailName.length() > 0) {
	photo.setThumbnailName(thumbnailName);
}
if(fileSize != null && fileSize.length() > 0) {
	photo.setFileSize(Integer.parseInt(fileSize));
}
if(thumbnailSize != null && thumbnailSize.length() > 0) {
	photo.setThumbnailSize(Integer.parseInt(thumbnailSize));
}
if(sync != null && sync.length()>0) {
	photo.setSync(sync);
}

logger.info("photo : " +photo.toString());

PhotoControl control = new PhotoControl();
int result = control.updatePhoto(photo);
logger.info("result : "+result);
JSONObject json = new JSONObject();
json.put("result", result);
out.print(json.toString());
%>