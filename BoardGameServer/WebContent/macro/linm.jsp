<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.SooMacroControl"%>
<%@page import="kr.bacoder.coding.bean.SooMacro"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>

<%
Logger logger = Logger.getLogger("linm.jsp");
String que = request.getParameter("que");

if(que!=null && que.length()!=0){
	SooMacroControl control = new SooMacroControl();
	int result = control.setQue(Integer.parseInt(que));
}




%>
<html>
	<head>

	</head>
	<body>
		<div class="wrap">
		<header>
		현재 위치는..
		</header>
				<img src="http://hsbong.synology.me:8080/macroImg/zone.png" alt="">
		<br>
		<div class="container">
		<form method="get" action="linm.jsp">
		<div>
				*귀환하기 : <input type="hidden" name="que" value="<%=1%>"/>
				<input type="submit" value="전송"/>
			</div>
		</form>
		<!--  <a onClick="window.location.reload()">새로고침</a> -->
		<a href="http://hsbong.synology.me:8080/pps/macro/linm.jsp">새로고침</a>
		</div>
		</div>
	</body>
</html>