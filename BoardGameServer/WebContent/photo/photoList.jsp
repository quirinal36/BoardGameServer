<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.PhotoControl"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="kr.bacoder.coding.bean.Photo"%>
<%@page import="kr.bacoder.coding.bean.PhotoPatientInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String day = request.getParameter("day");
String classification = request.getParameter("classification");

Photo photo = new Photo();
photo.setDay(day);
if(classification==null || classification.length()==0){
	photo.setClassification("응급");
}else{
	photo.setClassification(classification);
}

PhotoControl control = new PhotoControl();
List<PhotoPatientInfo> list = control.getEmergencyPhotos(photo);
%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="http://www.bacoder.kr/webpr/css/style.css" />
		<link rel="stylesheet" type="text/css" href="http://www.bacoder.kr/webpr/css/table.css" />
	</head>
	<body>
		<div class="wrap">
		<header>
			<jsp:include page="/inc/header.jsp"></jsp:include>
		</header>
		<div class="container">
		<form method="get" action="/photo/photoList.jsp">
			<a href="/" >홈으로 가기</a>
			<div>
				day: <input type="text" name="day" value="<%=photo.getDay()%>"/>
				분류: <input type="text" name="classification" value="<%=photo.getClassification()%>"/>
				<input type="submit" value="검색"/>
			</div>
			
			<table border="1">
				<thead>
					<tr>
						<th> id </th>
						<th> 환자ID </th>
						<th> 이름 </th>
						<th> 나이 </th>
						<th> 전화번호 </th>
						<th> 성별 </th>
						<th> 주소 </th>
						<th> 분류 </th>
						<th> date </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="<%=list %>" var="item">
						<tr>
							<td>${item.photoId }</td>
							<td>${item.patientId }</td>
							<td>${item.patientName }</td>
							<td>${item.patientAge }</td>
							<td>${item.patientPhone }</td>
							<td>${item.patientSex }</td>
							<td>${item.patientAddress }</td>
							<td>${item.classification }</td>
							<td>${item.date }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		</div>
		</div>
	</body>
</html>