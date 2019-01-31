<%@page import="java.util.List"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("index.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="./resources/js/sweetalert2.all.min.js"></script>
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
		<a href="./admin/index.jsp">어드민페이지</a>
		<table>
			<thead>
				<colgroup>
					<col width="5%" />
					<col width="20%" />
					<col width="15%" />
					<col width="15%" />
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
						<th>param4</th>
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
						<td></td>
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
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">4</td>
						<td>patient/getPatientsByDoctor.jsp</td>
						<td></td>
						<td>doctor : <input type="text" name="doctor" placeholder="doctor" value="D0007"/></td>
						<td>search : <input type="text" name="search" placeholder="search" value=""/></td>
						<td>department : <input type="text" name="department" placeholder="department" value="진료부"/></td>
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
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
							<input type="button" value="세부수정" onclick="location.href='person/update.jsp'"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">9</td>
						<td>updatePatient.jsp</td>
						<td>patientId 에 해당하는 patient정보를 수정</td>
						<td>patientId : <input type="text" name="patientId" placeholder="12345" value="12345"/></td>
						<td>age : <input type="text" name="age" placeholder="32"/></td>
						<td>name : <input type="text" name="name" placeholder="kim"/></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
							<input type="button" value="세부수정" onclick="location.href='patient/update.jsp'"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">10</td>
						<td>addPatient.jsp</td>
						<td>환자정보 만들기 </td>
						
						<td>age : <input type="text" name="age" placeholder="32"/></td>
						<td>name : <input type="text" name="name" placeholder="kim"/></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
							<input type="button" value="세부수정" onclick="location.href='patient/insertPatient.jsp'"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">11</td>
						<td>photo/updatePhoto.jsp</td>
						<td>사진 정보 수정하기 </td>
						<td>id : <input type="text" name="id" placeholder="100"/></td>
						<td>comment : <input type="text" name="comment" placeholder="comment"/></td>
						<td>classification : <input type="text" name="classification" placeholder="classification"/></td>
						<td>doctor : <input type="text" name="doctor" placeholder="doctor"/></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">12</td>
						<td>patient/getEmergency.jsp</td>
						<td>응급환자조회하기</td>
						<td>classification : <input type="text" name="classification" placeholder="응급"/></td>
						<td>day : <input type="text" name="day" placeholder="10"/></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">13-1</td>
						<td>person/like.jsp</td>
						<td>즐겨찾기 </td>
						<td>uniqueId : <input type="text" name="uniqueId" placeholder="D0000"/></td>
						<td>patientId : <input type="text" name="patientId" placeholder="12345"/></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">13-2</td>
						<td>person/disLike.jsp</td>
						<td>즐겨찾기 삭제</td>
						<td>uniqueId : <input type="text" name="uniqueId" placeholder="D0000"/></td>
						<td>patientId : <input type="text" name="patientId" placeholder="12345"/></td>
						<td>id : <input type="text" name="id" placeholder="id 를 입력하세요"/></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">14</td>
						<td>patient/getPatientListByNfc.jsp</td>
						<td>patient list</td>
						<td>tagId : <input type="text" name="tagId" placeholder="1111"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">15</td>
						<td>patient/addNfc.jsp</td>
						<td>add NFC tag</td>
						<td>tagId : <input type="text" name="tagId" placeholder="1111"/></td>
						<td>patientId : <input type="text" name="patientId" placeholder="12345"/></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">16</td>
						<td>patient/addScreenshot.jsp</td>
						<td>screen shot 추가하기 </td>
						<td>patientId : <input type="text" name="patientId" placeholder="12345"/></td>
						<td>photoUrl : <input type="text" name="photoUrl" placeholder="http://hsbong.synology.me:7070/image/image.jpg"/></td>
						<td>name: <input type="text" name="name" placeholder="새이름"/></td>
						<td>birth: <input type="text" name="birth" placeholder="20190109"/></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">17</td>
						<td>patient/getNfcList.jsp</td>
						<td>NFC 태그 리스트 </td>
						<td>tagId : <input type="text" name="tagId" placeholder="tagId" value="1111"/></td>
						<td>patientId : <input type="text" name="patientId" placeholder="patientId" value=""/></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
					<tr>
						<td class="center-horizontal">18</td>
						<td>patient/delNfc.jsp</td>
						<td>NFC tag 특정 환자정보 삭제  </td>
						<td>tagId : <input type="text" name="tagId" placeholder="tagId" value="1111"/></td>
						<td>patientId : <input type="text" name="patientId" placeholder="patientId" value=""/></td>
						<td></td>
						<td></td>
						<td class="center-horizontal">
							<input type="button" value="전송" class="send-button"/>
						</td>
					</tr>
			</tbody>
		</table>
	</body>
</html>