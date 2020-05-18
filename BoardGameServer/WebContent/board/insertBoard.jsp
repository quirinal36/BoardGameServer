<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.bean.Board"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.BoardControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("insertBoard.jsp");

String data = new String();

String creatorId = request.getParameter("creatorId");
String patientId = request.getParameter("patientId");
String status = request.getParameter("status");
String text = request.getParameter("text");
String type = request.getParameter("type");
String accessLevel = request.getParameter("accessLevel");
String groupId = request.getParameter("groupId");
String token = request.getHeader("authorization");

Board board = new Board();
if(creatorId != null && creatorId.length() > 0) {
	board.setCreatorId(Integer.parseInt(creatorId));
}
if(patientId != null && patientId.length() > 0) {
	board.setPatientId(Integer.parseInt(patientId));
}
if(status != null && status.length() > 0) {
	board.setStatus(Integer.parseInt(status));
}
if(text != null && text.length() > 0) {
	board.setText(text);
}
if(type != null && type.length() > 0) {
	board.setType(Integer.parseInt(type));
}
if(accessLevel != null && accessLevel.length() > 0) {
	board.setAccessLevel(Integer.parseInt(accessLevel));
}
if(groupId != null && groupId.length() > 0) {
	board.setGroupId(Integer.parseInt(groupId));
}

BoardControl boardCtl = new BoardControl();

JSONObject result = new JSONObject();
result.put("result", boardCtl.insertBoard(board));
out.print(result.toJSONString());
%>