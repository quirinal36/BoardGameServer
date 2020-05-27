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
Logger logger = Logger.getLogger("boardList.jsp");

String groupId = request.getParameter("groupId");
String token = request.getHeader("authorization");

logger.info("groupId:"+groupId);
logger.info("token:"+token);

Board board = new Board();
BoardControl boardCtl = new BoardControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();
List<Board> list;

if(token != null &&token.length()>0 && control.getPersonByToken(token) != null) {
	Person person = control.getPersonByToken(token);
	logger.info("id:"+person.getId());
	board.setCreatorId(person.getId());
	if(groupId != null && groupId.length() >0) {
		list = boardCtl.getBoardListByGroupId(person.getId(),person.getUserLevel(), Integer.parseInt(groupId));
		logger.info(list.toString());
	} else {
		list = boardCtl.getBoardList(person.getId(),person.getUserLevel());
		logger.info(list.toString());
	}
	out.print(list.toString());
} else {
	//token error (unauthorized)
	out.print(boardCtl.getBoardList(0,0).toString());
}
%>