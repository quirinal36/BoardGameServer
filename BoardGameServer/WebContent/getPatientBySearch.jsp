<%@page import="java.net.URLEncoder"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Iterator"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPatientBySearch.jsp");

String searchQuery = request.getParameter("query");

PatientControl control = new PatientControl();
List<Patient> list = control.searchPatientByQuery(searchQuery);

JSONObject json = new JSONObject();
JSONArray array = new JSONArray();

Iterator <Patient> iter = list.iterator();
while(iter.hasNext()){
	array.put(new JSONObject(iter.next().toString()));
}
json.put("list", array);
out.print(URLEncoder.encode(json.toString(), "UTF-8"));
%>