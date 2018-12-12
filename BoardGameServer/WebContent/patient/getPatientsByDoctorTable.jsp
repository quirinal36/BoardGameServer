<%@page import="java.util.ArrayList"%>
<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.bean.Doctor"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPatients.jsp");

String doctorIdStr = request.getParameter("doctor");
String query = request.getParameter("search");

Doctor doctor = new Doctor(doctorIdStr);

String data = new String();
PatientControl control = new PatientControl();

DBconn dbconn = new DBconn();
List<Patient> list = control.getPatientsByDoctor(doctor, query);
%>
<html>
	<body>
		<form method="get" action="/patient/getPatientsByDoctorTable.jsp">
			<div>
				doctor :<input type="text" name="doctor"/>
				/ search : <input type="text" name="search" />
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
					<th> photo </th>				
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
						<td><a href="${item.photo }">${item.photo }</a></td>
					</tr>
				</c:forEach>
			</table>
			
			
		</form>
	</body>
</html>