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

<html>
	<body>
		<form method="get" action="/patient/patientList.jsp">
			<div><a href="/" >홈으로 가기</a></div>
		
			<div>
				<input type="text" name="query"/>
				<input type="submit" value="검색"/>
			</div>
			
			<table border="1">
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
			</table>
			
			
		</form>
	</body>
</html>