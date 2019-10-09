<%@page import="java.net.URLEncoder"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Iterator"%>
<%@page import="kr.bacoder.coding.bean.OPRecord"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="kr.bacoder.coding.dev.TokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getOPRecord.jsp");

String searchQuery = request.getParameter("query");
String doctor = request.getParameter("doctor");
String duration = request.getParameter("duration");

//String tokenStr = request.getHeader("Authorization");
//TokenUtil token = new TokenUtil();

OPRecord record = new OPRecord();

if(searchQuery != null && searchQuery.length() > 0) {
	record.setSearch(searchQuery);
}
if(doctor != null && doctor.length() > 0) {
	record.setDoctor(doctor);
}
if(duration != null && duration.length() > 0) {
	record.setDuration(duration);
}

//if(tokenStr != null && token.IsValidToken(tokenStr) > 0) {  
	PatientControl control = new PatientControl();
	
	List<OPRecord> list = control.searchRecordByQuery(record);

	JSONObject json = new JSONObject();
	JSONArray array = new JSONArray();

	Iterator <OPRecord> iter = list.iterator();
	while(iter.hasNext()){
		array.put(new JSONObject(iter.next().toString()));
	}
	json.put("list", array);
	out.print(json.toString());

//} else {
// 	response.sendError(401, token.unauthorized);
//}
%>