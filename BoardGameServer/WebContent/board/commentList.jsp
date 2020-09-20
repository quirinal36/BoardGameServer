<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.bean.Comment"%>
<%@page import="kr.bacoder.coding.control.CommentControl"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("commentList.jsp");

String boardId = request.getParameter("boardId");
String token = request.getHeader("authorization");

logger.info("boardId:"+boardId);
logger.info("token:"+token);

Comment comment = new Comment();
CommentControl commentCtl = new CommentControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();
List<Comment> list;

if(boardId != null && token != null && token.length()>0) {
	Person person = control.getPersonByToken(token);
	if(person != null) {
		logger.info("id:"+person.getId());
		
		list = commentCtl.getCommentList(person.getId(),person.getUserLevel(), Integer.parseInt(boardId));
		logger.info(list.toString());
		
		out.print(list.toString());
	} else {
		// unauthorized person
	}
	
	
} else {
	//param null
}
%>