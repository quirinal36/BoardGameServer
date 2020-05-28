<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.bean.Board"%>
<%@page import="kr.bacoder.coding.control.BoardControl"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("delBoard.jsp");

String boardId = request.getParameter("boardId");
String token = request.getHeader("authorization");

logger.info("boardId:"+boardId);

Board board = new Board();

if(boardId != null &&boardId.length()>0) {
	board.setId(Integer.parseInt(boardId));
}

BoardControl boardCtl = new BoardControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();

//param null과 token invalid 분기하기
if(boardId != null && boardId.length()>0 
	&& token != null && token.length()>0 
	&& control.getPersonByToken(token) != null) {
	
	Person person = control.getPersonByToken(token);
	logger.info("id:"+person.getId());
	board.setCreatorId(person.getId());
	board.setUserLevel(person.getUserLevel());
	if((board.getCreatorId() == person.getId()) 
			|| person.getUserLevel() > 4) {
		result.put("result", boardCtl.deleteBoardById(board));
		out.print(result.toString());
	} else {
		result.put("result", -1);
		out.print(result.toString());
	}
	
} else {
	//token error (unauthorized)
	response.sendError(401, control.unauthorized);
}
%>