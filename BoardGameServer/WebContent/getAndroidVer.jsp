<%@page import="kr.bacoder.coding.control.ControlAndroid"%>
<%@page import="kr.bacoder.coding.bean.AndroidVersionInfo"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPerson.jsp");
AndroidVersionInfo info = AndroidVersionInfo.setParameters(request);

final String personJSON = new ControlAndroid().getAndroidInfo(info);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=personJSON %>
</body>
</html>