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
	<body>
		<form method="get" action="/photo/photoList.jsp">
			<div>
				day: <input type="text" name="day" value="<%=photo.getDay()%>"/>
				분류: <input type="text" name="classification" value="<%=photo.getClassification()%>"/>
				<input type="submit" value="검색"/>
			</div>
			
			<table border="1">
				<tr>
					<th> photoId </th>
					<th> patientId </th>
					<th> patientName </th>
					<th> patientAge </th>
					<th> patientPhone </th>
					<th> patientSex </th>
					<th> patientAddress </th>
					<th> patientBirth </th>
					<th> accessLv </th>
					<th> classification </th>
					<th> comment </th>
					<th> date </th>
					<th> photoUrl </th>
					<th> uploader </th>
					<th> doctor </th>
				</tr>
				<c:forEach items="<%=list %>" var="item">
					<tr>
						<td>${item.photoId }</td>
						<td>${item.patientId }</td>
						<td>${item.patientName }</td>
						<td>${item.patientAge }</td>
						<td>${item.patientPhone }</td>
						<td>${item.patientSex }</td>
						<td>${item.patientAddress }</td>
						<td>${item.patientBirth }</td>
						<td>${item.accessLv }</td>
						<td>${item.classification }</td>
						<td>${item.comment }</td>
						<td>${item.date }</td>
						<td><a href="${item.photoUrl }" target="_blank">${item.photoUrl }</a></td>
						<td>${item.uploader }</td>
						<td>${item.doctor }</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>