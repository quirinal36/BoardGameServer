<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getMemo.jsp");
String patientId = request.getParameter("patientId");

DBconn dbconn = new DBconn();
PatientControl control = new PatientControl();
out.print(control.getMemo(patientId));
%>
