<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getToken.jsp");

String userId = request.getParameter("userId");
String pwd = request.getParameter("userPwd");

Token token = new Token();
token.setUserId(userId);
token.setUserPwd(pwd);
token.setSubject(userId);
token.setScope(1);

DBconn dbconn = new DBconn();
TokenControl control = new TokenControl();

out.print(control.getAccessToken(token));
%>