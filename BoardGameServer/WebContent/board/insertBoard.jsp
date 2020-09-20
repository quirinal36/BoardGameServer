<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONException"%>

<%@page import="kr.bacoder.coding.bean.Board"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<%@page import="kr.bacoder.coding.control.BoardControl"%>
<%@page import="kr.bacoder.coding.control.TokenControl"%>
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
	 	logger.info("paramBody:"+body);
	 	logger.info("token:"+request.getHeader("authorization"));

	 	//JSONObject jsonRequest = new JSONObject(body);
	 	//JSONParser parser = new JSONParser(); 
	 	
	 	String patientId = null;
		String status = null;
		String text = null;
		String type = null;
		String accessLevel = null;
		String groupId = null;
		String youtubeLink = null;
		String token = null;
		String photoId = null;
		String caption = null;
	 	
 	 	JSONObject jsonRequest = null;
	 	try {
	 		jsonRequest = new JSONObject(body);
	 		
	 		 //patientId = jsonRequest.getString("patientId");
	 		 //status = jsonRequest.getString("status");
	 		 text = jsonRequest.getString("text");
	 		 type = jsonRequest.getString("type");
	 		 //accessLevel = jsonRequest.getString("accessLevel");
	 		 groupId = jsonRequest.getString("groupId");
	 		 youtubeLink = jsonRequest.getString("youtubeLink");
	 		 token = request.getHeader("authorization");
	 		 photoId = jsonRequest.getString("photoId");
	 		 //caption = jsonRequest.getString("caption");
	 		
	 	} catch (JSONException e) {
	 		e.printStackTrace();
	 	}
	 	
	 	
/* 	 	JSONParser jsonParser = new JSONParser();
	 	Object obj = jsonParser.parse( body );
	 	JSONObject jsonRequest = (JSONObject) obj; 
	 	*/

	 	


	logger.info("photoId:"+photoId);


Board board = new Board();

if(patientId != null && patientId.length() > 0) {
	board.setPatientId(Integer.parseInt(patientId));
}
if(status != null && status.length() > 0) {
	board.setStatus(Integer.parseInt(status));
}
if(text != null && text.length() > 0) {
	board.setText(text);
} else {
	board.setText("");
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
if(caption != null && caption.length() > 0) {
	board.setCaption(caption);
} else {
	caption = "";
	board.setCaption(caption);
}

logger.info("text:"+text);
logger.info("token:"+token);

JSONObject result = new JSONObject();
TokenControl control = new TokenControl();

try {
	if(control.getPersonByToken(token) != null) 
	{
		Person person = control.getPersonByToken(token);
		logger.info("id:"+person.getId());
		board.setCreatorId(person.getId());
		board.setUserLevel(person.getUserLevel());
		
		BoardControl boardCtl = new BoardControl();
		int boardId = 0;
		boardId = boardCtl.insertBoard(board);
		result.put("result", boardId);
		logger.info("### boardId:"+boardId);
		logger.info("### photoIdList:"+photoId);
	
		if(boardId >0 && photoId != null && !photoId.equals("null")) {
			
			//remove first and last character and create array of Strings splitting around the character ','
			String[] strings = photoId.substring(1, photoId.length() - 1).split(",");

			//Now since every element except the first one has an extra leading space, remove it
			for (int i = 1; i < strings.length; i++) {
			    strings[i] = strings[i].substring(1);
			}
			
			List<String> idStringList = Arrays.asList(strings);

			logger.info("### idStringList:"+idStringList.toString());
			
			for(int i=0;i<idStringList.size();i++)
			{
				logger.info("### photoId:"+Integer.parseInt(idStringList.get(i)));
				result.put("result", boardCtl.insertBoardPhoto(boardId, Integer.parseInt(idStringList.get(i)), caption));
			}
		}
		out.print(result.toString());
		
		
	} else {
		//token error (unauthorized)
		result.put("result", -1);
		out.print(result.toString());
		}
} catch(Exception e) {
	e.printStackTrace();
}
%>