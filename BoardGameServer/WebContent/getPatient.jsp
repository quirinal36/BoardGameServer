<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPatient.jsp");
String data = new String();
String id = request.getParameter("id");
String name = request.getParameter("name");

if(id!=null && id.length()==0) id=null;
if(name!=null && name.length()==0) name=null;
DBconn dbconn = new DBconn();
PatientControl control = new PatientControl();

out.print(control.getPatient(id, name));
%>
