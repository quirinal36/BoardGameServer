<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("index.jsp");
logger.info("index.jsp");
String data = new String();

DBconn dbconn = new DBconn();
try{
	data = dbconn.getData();
}catch(SQLException e){
	e.printStackTrace();
}catch(ClassNotFoundException e){
	e.printStackTrace();
}
logger.info(data);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%= data %>
</body>
</html>