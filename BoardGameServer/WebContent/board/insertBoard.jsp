<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.bean.Board"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.BoardControl"%>
<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("insertBoard.jsp");

String body = null;
StringBuilder stringBuilder = new StringBuilder();
BufferedReader bufferedReader = null;

	 try {
	     InputStream inputStream = request.getInputStream();
	     if (inputStream != null) {
	         bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	         char[] charBuffer = new char[128];
	         int bytesRead = -1;
	         while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	             stringBuilder.append(charBuffer, 0, bytesRead);
	         }
	     } else {
	         stringBuilder.append("");
	     }
	 } catch (IOException ex) {
	     throw ex;
	 } finally {
	     if (bufferedReader != null) {
	         try {
	             bufferedReader.close();
	         } catch (IOException ex) {
	             throw ex;
	         }
	     }
	 }

	 	body = stringBuilder.toString();
	 	JSONParser jsonParser = new JSONParser();
	 	Object obj = jsonParser.parse( body );
	 	JSONObject jsonRequest = (JSONObject) obj;

	 	
String patientId = (String) jsonRequest.get("patientId");
String status = (String) jsonRequest.get("status");
String text = (String) jsonRequest.get("text");
String type = (String) jsonRequest.get("type");
String accessLevel = (String) jsonRequest.get("accessLevel");
String groupId = (String) jsonRequest.get("groupId");
String youtubeLink = (String) jsonRequest.get("youtubeLink");
String token = request.getHeader("authorization");


Board board = new Board();

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
} else {
	board.setType(2);
}
if(accessLevel != null && accessLevel.length() > 0) {
	board.setAccessLevel(Integer.parseInt(accessLevel));
} else {
	board.setAccessLevel(1);
}
if(groupId != null && groupId.length() > 0) {
	board.setGroupId(Integer.parseInt(groupId));
} else {
	board.setGroupId(1);
}
if(youtubeLink != null && youtubeLink.length() > 0) {
	board.setYoutubeLink(youtubeLink);
}

logger.info("text:"+text);
logger.info("token:"+token);

JSONObject result = new JSONObject();
TokenControl control = new TokenControl();
if(control.getPersonByToken(token) != null) {
	Person person = control.getPersonByToken(token);
	logger.info("id:"+person.getId());
	board.setCreatorId(person.getId());
	board.setUserLevel(person.getUserLevel());
	
	BoardControl boardCtl = new BoardControl();
	result.put("result", boardCtl.insertBoard(board));
	out.print(result.toJSONString());
} else {
	//token error (unauthorized)
	result.put("result", -1);
	out.print(result.toJSONString());
}
%>