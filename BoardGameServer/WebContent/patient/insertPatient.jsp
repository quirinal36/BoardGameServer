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
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="http://www.bacoder.kr/webpr/css/style.css" />
	<link rel="stylesheet" type="text/css" href="http://www.bacoder.kr/webpr/css/table.css" />
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="/resources/js/sweetalert2.all.min.js"></script>
	<title>환자정보 입</title>

	<script type="text/javascript">
	
	</script>
</head>
<body>
<div class="wrap">
		<header>
			<jsp:include page="/inc/header.jsp"></jsp:include>
		</header>
		<div class="container">
			<form action="/addPatient.jsp">
				<input type="submit" value="저장"/>
				<table>
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<thead>
						<tr>
							<th colspan="2">새 환자 정보를 입력하세요</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td>name</td>
						<td>
							<input type="text" name="name" placeholder="이름"/>
						</td>
					</tr>
					<tr>
						<td>age</td>
						<td>
							<input type="text" name="age" placeholder="나이"/>
						</td>
					</tr>
					<tr>
						<td>birth</td>
						<td>
							<input type="text" name="birth" placeholder="생년월일(20190101)"/>
						</td>
					</tr>
					<tr>
						<td>phone</td>
						<td>
							<input type="text" name="phone" placeholder="01000000000"/>
						</td>
					</tr>
					<tr>
						<td>doctor</td>
						<td>
							<input type="text" name="doctor" placeholder="담당의사"/>
						</td>
					</tr>
					<tr>
						<td>memo</td>
						<td>
							<input type="text" name="memo" placeholder="메모"/>
						</td>
					</tr>
					<tr>
						<td>room</td>
						<td>
							<input type="text" name="room" placeholder="입원실"/>
						</td>
					</tr>
					<tr>
						<td>patientId</td>
						<td>
							<input type="text" name="patientId" placeholder="환자아이디"/>
						</td>
					</tr>
					</tbody>
				</table>
				
			</form>
		</div>
	</div>
</body>
</html>