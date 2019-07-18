<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.SooMacroControl"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>

<%
Logger logger = Logger.getLogger("getQue.jsp");

SooMacroControl control = new SooMacroControl();
int result = control.getQue();

out.print(result); 


%>