<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.dev.UploadUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.util.logging.Logger"%>
<%
//	final String path = getServletContext().getRealPath("/upload");
	Logger logger = Logger.getLogger("addPhotoUrl.jsp");
	
	//String imgEncodedStr = request.getParameter("image");
	//String fileName = request.getParameter("filename");
	String photoUrl = request.getParameter("photoUrl");
	String patientId = request.getParameter("pId");
	String patientName = request.getParameter("pName");
	String classification = request.getParameter("class");
	String doctor = request.getParameter("doc");
	String uploader = request.getParameter("uploader");
	String comment = request.getParameter("comment");
	String accessLv = request.getParameter("accessLv");
	String date = request.getParameter("date");
	
	String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date());
	
	Photo photoInfo = new Photo();
	
	if(patientId != null){
		photoInfo.setPatientId(patientId);
	}
	if(patientName != null){
		photoInfo.setPatientName(patientName);
	}
	if(classification != null){
		photoInfo.setClassification(classification);
	}
	if(doctor != null){
		photoInfo.setDoctor(doctor);
	}
	if(uploader != null){
		photoInfo.setUploader(uploader);
	}
	if(comment != null){
		photoInfo.setComment(comment);
	}
	if(accessLv != null){
		photoInfo.setAccessLv(accessLv);
	}
	
	if(date != null){
		photoInfo.setDate(date);
	} 
	else {
		photoInfo.setDate(timeStamp);
	}
	
	JSONObject json = new JSONObject();
//	String imgUrl = new String();
//	if (imgEncodedStr != null) {
//		imgUrl = new UploadUtil().setPhoto(path, imgEncodedStr, fileName, photoInfo.getPatientId());
//	}
	photoInfo.setPhotoUrl(photoUrl);
	
	DBconn dbconn = new DBconn();
	PhotoControl control = new PhotoControl();
	
	json.put("result", control.addPhotoInfo(photoInfo));
	
	out.print(json.toJSONString());
%>