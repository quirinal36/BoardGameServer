<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.control.PersonControl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String uniqueId = request.getParameter("uniqueId");
PersonControl control = new PersonControl();
Person person = new Person();
person.setUniqueId(uniqueId);
person = control.getPersonByUniqueId(person);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="/resources/js/sweetalert2.all.min.js"></script>
	<title>회원정보 수정</title>

	<script type="text/javascript">
	function getInfo(){
		var getUid = $("#uniqueId").val();
		window.location.replace("/person/update.jsp?uniqueId=" + getUid);
	}
	</script>
</head>
<body>
	<form action="/person/updatePerson.jsp">
		<table>
			<tr>
				<td>사번</td>
				<td>
					<input id="uniqueId" type="text" name="uniqueId" value="<%=person.getUniqueId()!=null?person.getUniqueId():""%>">
					<input type="button" onclick="javascript:getInfo();" value="가져오기">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" value="<%=person.getName()!=null?person.getName():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>부서</td>
				<td>
					<input type="text" name="department" value="<%=person.getDepartment()!=null?person.getDepartment():""%>"/>
				</td>
			</tr>
			<tr>
				<td>권한</td>
				<td>
					<input type="text" name="userLevel" value="<%=person.getUserLevel()>0?person.getUserLevel():0%>"/>
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td>
					<input type="text" name="email" value="<%=person.getEmail()!=null?person.getEmail():"" %>"/>
				</td>
			</tr>
			<tr>
				<td>phone</td>
				<td>
					<input type="text" name="phone" value="<%=person.getPhone()!=null?person.getPhone():"" %>"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="저장"/>
	</form>
</body>
</html>