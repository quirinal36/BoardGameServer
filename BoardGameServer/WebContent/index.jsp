<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("index.jsp");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<a href="getCity.jsp">게임맵</a>
<br>
<a href="getPerson.jsp">사람들</a>
<br>
<a href="getAndroidVer.jsp">안드로이드버젼</a>

</body>
</html>