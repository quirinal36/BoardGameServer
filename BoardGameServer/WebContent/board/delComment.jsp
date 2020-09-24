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
Logger logger = Logger.getLogger("delComment.jsp");

String commentId = request.getParameter("commentId");
String token = request.getHeader("authorization");

logger.info("commentId:"+commentId);

Comment comment = new Comment();

if(commentId != null &&commentId.length()>0) {
	comment.setId(Integer.parseInt(commentId));
}

CommentControl commentCtl = new CommentControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();

if(commentId != null && token != null && token.length()>0) {
	Person person = control.getPersonByToken(token);
	if(person != null) {
		logger.info("id:"+person.getId());
		if((comment.getWriterId() == person.getId()) 
				|| person.getUserLevel() > 4) {
			result.put("result", commentCtl.deleteCommentById(comment));
			out.print(result.toString());
		} else {
			result.put("result", -1);
			out.print(result.toString());
		}
		
	}
} else {
	//token error (unauthorized)
	response.sendError(401, control.unauthorized);
}

%>