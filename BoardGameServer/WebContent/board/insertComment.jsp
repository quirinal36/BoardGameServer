<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.bean.Comment"%>
<%@page import="kr.bacoder.coding.control.CommentControl"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.IOException"%>

<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("insertComment.jsp");

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
	 	logger.info("paramBody:"+body);

	 	//JSONObject jsonRequest = new JSONObject(body);
	 	//JSONParser parser = new JSONParser(); 
	 	
		String boardId = null;
		String text = null;
		String writerId = null;
		String boardOwnerId = null;
		String secret = null;
		String token = null;
	 	
 	 	JSONObject jsonRequest = null;
	 	try {
	 		jsonRequest = new JSONObject(body);
	 		
	 		boardId = jsonRequest.getString("boardId");
	 		text = jsonRequest.getString("text");
	 		writerId = jsonRequest.getString("writerId");
	 		boardOwnerId = jsonRequest.getString("boardOwnerId");
	 		secret = jsonRequest.getString("secret");

	 		token = request.getHeader("authorization"); 
	 		
	 			 		
	 	} catch (JSONException e) {
	 		e.printStackTrace();
	 	}

logger.info("param_boardId:"+boardId);
logger.info("param_token:"+token);

Comment comment = new Comment();
CommentControl commentCtl = new CommentControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();

if(boardId != null && boardId.length() > 0) {
	comment.setBoardId(Integer.parseInt(boardId));
}
if(text != null && text.length() > 0) {
	comment.setText(text);
}
if(writerId != null && writerId.length() > 0) {
	comment.setWriterId(Integer.parseInt(writerId));
}
if(boardOwnerId != null && boardOwnerId.length() > 0) {
	comment.setBoardOwnerId(Integer.parseInt(boardOwnerId));
}
if(secret != null && secret.length() > 0) {
	comment.setSecret(Integer.parseInt(secret));
} else {
	comment.setSecret(0);
}
comment.setStatus(1);

if(boardId != null && token != null && token.length()>0) {
	Person person = control.getPersonByToken(token);
	if(person != null) {
		logger.info("id:"+person.getId());
		
		int commentId = 0;
		commentId = commentCtl.insertComment(comment);
		result.put("result", boardId);
		
		logger.info(result.toString());
		
	} else {
		// unauthorized person
		result.put("result", -1);
	}
	
	
} else {
	//param null
	result.put("result", -2);
}
out.print(result.toString());
%>