<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("index.jsp");
int num1 = 15;
int num2 = 8;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

안녕하세요
<a href="http://www.naver.com">네이버바로가기</a>
<input type="text" />
<input type="button" value="로그인"/>
<%
out.print("num1 : " + num1);
%>

</body>
</html>