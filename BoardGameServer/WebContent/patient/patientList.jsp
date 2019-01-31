<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String searchQuery = request.getParameter("query");

PatientControl control = new PatientControl();
List<Patient> list = control.searchPatientByQuery(searchQuery);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="http://www.bacoder.kr/webpr/css/style.css" />
<link rel="stylesheet" type="text/css" href="http://www.bacoder.kr/webpr/css/table.css" />
<meta charset="UTF-8">
<title>관리자페이지</title>
<script type="text/javascript">
function writeNew(){
	window.location.href="/patient/insertPatient.jsp";
}
</script>
</head>
<body>
	<div class="wrap">
		<header>
			<jsp:include page="/inc/header.jsp"></jsp:include>
		</header>
		<div class="container">
		<form method="get" action="/patient/patientList.jsp">
			<div>
				<input type="text" name="query" placeholder="검색어를 입력하세요."/>
				<input type="submit" value="검색"/>
				<input type="button" value="새로입력" onclick="javascript:writeNew();"/>
			</div>
			
			<table border="1">
				<thead>
				<tr>
					<th> id </th>
					<th> patientId </th>
					<th> name </th>
					<th> doctor </th>
					<th> birth </th>
					<th> sex </th>
					<th> address </th>
					<th> phone </th>
					<th> memo </th>
					<th> room </th>
					<th> admission </th>					
				</tr>
				</thead>
				<tbody>
				<c:forEach items="<%=list %>" var="item">
					<tr>
						<td>${item.id }</td>
						<td>${item.patientId }</td>
						<td>${item.name }</td>
						<td>${item.doctor }</td>
						<td>${item.birth }</td>
						<td>${item.sex }</td>
						<td>${item.address }</td>
						<td>${item.phone }</td>
						<td>${item.memo }</td>
						<td>${item.room }</td>
						<td>${item.admission }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			
			
		</form>
	</div>
	</div>
	</body>
</html>