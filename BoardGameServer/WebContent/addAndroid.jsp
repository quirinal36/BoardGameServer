<%@page import="kr.bacoder.coding.control.ControlAndroid"%>
<%@page import="kr.bacoder.coding.bean.AndroidVersionInfo"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPerson.jsp");
AndroidVersionInfo info = AndroidVersionInfo.setParameters(request);
int result = new ControlAndroid().addAndroidInfo(info);
%>
<%=result%>