
<%@page import="kr.bacoder.coding.bean.Doctor"%>
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
Logger logger = Logger.getLogger("getPatients.jsp");

String doctorId = request.getParameter("doctor");
String department = request.getParameter("department");

String data = new String();
PatientControl control = new PatientControl();

DBconn dbconn = new DBconn();
out.print(control.getPatientsByDoctor(doctorId, department).toJSONString());
%>