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
				param = param + $(this).attr("name") + "=" + $(this).val(); 
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
			<col width="50%" />
			<col width="15%" />
			<col width="15%" />
			<col width="12%" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>링크</th>
				<th>param1</th>
				<th>param2</th>
				<th>test</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="center-horizontal">1</td>
				<td>getPhotoById.jsp</td>
				<td><input type="text" name="id" placeholder="id" value="73"/></td>
				<td></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">2</td>
				<td>patient/getPatientsByDoctor.jsp</td>
				<td><input type="text" name="doctor" placeholder="doctor" value="1"/></td>
				<td><input type="text" name="search" placeholder="search" value="12345"/></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
			<tr>
				<td class="center-horizontal">2</td>
				<td>patient/setPatientRepresentPhoto.jsp</td>
				<td><input type="text" name="patientId" placeholder="patientId" value="12345"/></td>
				<td><input type="text" name="photoId" placeholder="photoId" value="73"/></td>
				<td class="center-horizontal">
					<input type="button" value="전송" class="send-button"/>
				</td>
			</tr>
		</tbody>
				
	</thead>
</table>
</body>
</html>