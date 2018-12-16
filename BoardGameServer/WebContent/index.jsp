<%@page import="java.util.List"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("index.jsp");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/resources/js/sweetalert2.all.min.js"></script>
<title>API 리스트</title>
<style type="text/css">
table{
	width: 100%;
	border: 1px solid #000;
	border-collapse : collapse;
}
th, td{
	border: 1px solid #444;
}
.center-horizontal{
	text-align:center;
}
</style>
<script type="text/javascript">
$(function(){
	$(".send-button").on('click', function(){
		// console.log("button click");
		var url = $(this).parent().parent().find("td:eq(1)").text();
		console.log("url : "+ url);
		
		var param = "";
		$(this).parent().parent().find("input").each(function(){
			if($(this).attr("name") != undefined && $(this).attr("name").length > 0){
				param = param + "&" +$(this).attr("name") + "=" + $(this).val(); 
			}
		});
		// url = url + param;
		$.ajax({
			type : "GET",
			url : url,
			data : param,
			success : function(response, textStatus, jqXHR){
				swal({
					text : JSON.stringify(JSON.parse($.trim(response)), null, 2)
				});
			}
		});
	});
	
});
</script>
</head>
<body>
<table>
	<thead>
		<colgroup>
			<col width="5%" />
			<col width="30%" />
			<col width="20%" />
			<col width="15%" />
			<col width="15%" />
			<col width="12%" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>링크</th>
				<th>설명</th>
				<th>param1</th>
				<th>param2</th>
				<th>param3</th>
				<th>test</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="center-horizontal">1</td>
				<td>person/hasSamePerson.jsp</td>
				<td>동일한 사번으로 저장된 정보가 있는지 조회</td>
				<td>userId: <input type="text" name="userId" placeholder="userId" value="D0007"/></td>
				<td></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">2</td>
				<td>getPerson.jsp</td>
				<td>로그인을 합니다.</td>
				<td>userName: <input type="text" name="userName" placeholder="userName" value="봉황세"/></td>
				<td>userBirth: <input type="text" name="userBirth" placeholder="userBirth" value="19821003"/></td>
				<td>userId: <input type="text" name="userId" placeholder="userId" value="D0007"/></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">3</td>
				<td>getPhotoById.jsp</td>
				<td></td>
				<td>id: <input type="text" name="id" placeholder="id" value="73"/></td>
				<td></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">4</td>
				<td>patient/getPatientsByDoctor.jsp</td>
				<td></td>
				<td>doctor : <input type="text" name="doctor" placeholder="doctor" value="1"/></td>
				<td>search : <input type="text" name="search" placeholder="search" value="12345"/></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
					<input type="button" value="table" onclick="location.href='patient/getPatientsByDoctorTable.jsp'"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">5</td>
				<td>patient/setPatientRepresentPhoto.jsp</td>
				<td>환자의 메인 사진을 수정한</td>
				<td>patientId : <input type="text" name="patientId" placeholder="patientId" value="12345"/></td>
				<td>photoId : <input type="text" name="photoId" placeholder="photoId" value="73"/></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">6</td>
				<td>getPatientBySearch.jsp</td>
				<td></td>
				<td>query : <input type="text" name="query" placeholder="query" value="12345"/></td>
				<td></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
					<input type="button" value="table" onclick="location.href='patient/patientList.jsp'"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">7</td>
				<td>photo/getEmergency.jsp</td>
				<td></td>
				<td>day : <input type="text" name="day" placeholder="10" value="10"/></td>
				<td>class : <input type="text" name="class" placeholder="10" /></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
					<input type="button" value="table" onclick="location.href='photo/photoList.jsp'"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">8</td>
				<td>person/updatePerson.jsp</td>
				<td>unique_id 에 해당하는 사람정보를 수정</td>
				<td>unique_id : <input type="text" name="unique_id" placeholder="D0000" value="D0000"/></td>
				<td>department : <input type="text" name="department" placeholder="응급"/></td>
				<td>name : <input type="text" name="name" placeholder="홍길동"/></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
					<input type="button" value="세부수정" onclick="location.href='person/update.jsp'"/>
				</td>
			</tr>
		</tbody>
				
	
</table>
</body>
</html>