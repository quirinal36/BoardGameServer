<%@page import="kr.bacoder.coding.bean.Patient"%>
<%@page import="kr.bacoder.coding.control.PatientControl"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.control.PersonControl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String strId = request.getParameter("id");
int id = 0;
try{
	id = Integer.parseInt(strId);
}catch(NumberFormatException e){
	
}
PatientControl control = new PatientControl();
Patient patient = new Patient();
patient.setId(id);
patient = control.getPatientById(id);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="/resources/js/sweetalert2.all.min.js"></script>
	<title>환자정보 입</title>

	<script type="text/javascript">
	
	</script>
</head>
<body>
	<form action="/addPatient.jsp">
		<table>
			
			<tr>
				<td>name</td>
				<td>
					<input type="text" name="name" />
				</td>
			</tr>
			<tr>
				<td>age</td>
				<td>
					<input type="text" name="age" />
				</td>
			</tr>
			<tr>
				<td>birth</td>
				<td>
					<input type="text" name="birth" />
				</td>
			</tr>
			<tr>
				<td>phone</td>
				<td>
					<input type="text" name="phone" />
				</td>
			</tr>
			<tr>
				<td>doctor</td>
				<td>
					<input type="text" name="doctor" />
				</td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<input type="text" name="memo" />
				</td>
			</tr>
			<tr>
				<td>room</td>
				<td>
					<input type="text" name="room" />
				</td>
			</tr>
			<tr>
				<td>patientId</td>
				<td>
					<input type="text" name="patientId" />
				</td>
			</tr>
		</table>
		<input type="submit" value="저장"/>
	</form>
</body>
</html>