<%@page import="kr.bacoder.coding.control.PersonControl"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPerson.jsp");
String userName = request.getParameter("userName");
String userId = request.getParameter("userId");
String userBirth = request.getParameter("userBirth");
String password = request.getParameter("password");

DBconn dbconn = new DBconn();
PersonControl control = new PersonControl();
out.print(control.getPerson(userName, userBirth, userId, password));
%>
