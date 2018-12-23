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
	<title>회원정보 수정</title>

	<script type="text/javascript">
	function getInfo(){
		var getId = $("#uniqueId").val();
		window.location.replace("/patient/update.jsp?id=" + getId);
	}
	</script>
</head>
<body>
	<form action="/updatePatient.jsp">
		<table>
			<tr>
				<td>id</td>
				<td>
					<input id="uniqueId" type="text" name="id" value="<%=patient.getId()%>">
					<input type="button" onclick="javascript:getInfo();" value="가져오기">
				</td>
			</tr>
			<tr>
				<td>name</td>
				<td>
					<input type="text" name="name" value="<%=patient.getName()!=null?patient.getName():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>age</td>
				<td>
					<input type="text" name="age" value="<%=patient.getAge()%>"/>
				</td>
			</tr>
			<tr>
				<td>birth</td>
				<td>
					<input type="text" name="birth" value="<%=patient.getBirth()!=null?patient.getBirth():""%>"/>
				</td>
			</tr>
			<tr>
				<td>phone</td>
				<td>
					<input type="text" name="phone" value="<%=patient.getPhone()!=null?patient.getPhone():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>doctor</td>
				<td>
					<input type="text" name="doctor" value="<%=patient.getDoctor()!=null?patient.getDoctor():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<input type="text" name="memo" value="<%=patient.getMemo()!=null?patient.getMemo():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>room</td>
				<td>
					<input type="text" name="room" value="<%=patient.getRoom()!=null?patient.getRoom():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>patientId</td>
				<td>
					<input type="text" name="patientId" value="<%=patient.getPatientId()!=null?patient.getPatientId():"" %>"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="저장"/>
	</form>
</body>
</html>