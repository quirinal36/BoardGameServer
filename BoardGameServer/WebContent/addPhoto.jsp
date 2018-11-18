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
	final String path = getServletContext().getRealPath("/upload");
	Logger logger = Logger.getLogger("addPhoto.jsp");
	
	String imgEncodedStr = request.getParameter("image");
	String fileName = request.getParameter("filename");
	String patientId = request.getParameter("patientId");
	String patientName = request.getParameter("patientName");
	String classification = request.getParameter("classification");
	String doctor = request.getParameter("doctor");
	String uploader = request.getParameter("uploader");
	String comment = request.getParameter("comment");
	String accessLv = request.getParameter("accessLv");
	
	String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date());
	
	Photo photoInfo = new Photo();
	if(patientId != null){
		photoInfo.setPatientId(patientId);
	}
	photoInfo.setPatientName(patientName);
	photoInfo.setClassification(classification);
	photoInfo.setDoctor(doctor);
	photoInfo.setDate(timeStamp);
	photoInfo.setUploader(uploader);
	if(comment != null){
		photoInfo.setComment(comment);
	}
	if(accessLv != null){
		photoInfo.setAccessLv(accessLv);
	}
	
	JSONObject json = new JSONObject();
	String imgUrl = new String();
	if (imgEncodedStr != null) {
		imgUrl = new UploadUtil().setPhoto(path, imgEncodedStr, fileName, photoInfo.getPatientId());
	}
	photoInfo.setPhotoUrl(imgUrl);
	
	DBconn dbconn = new DBconn();
	PhotoControl control = new PhotoControl();
	
	json.put("result", control.addPhotoInfo(photoInfo));
	
	out.print(json.toJSONString());
%>
