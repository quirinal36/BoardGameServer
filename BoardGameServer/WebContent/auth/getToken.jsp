<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getToken.jsp");

String userId = request.getParameter("userId");
String pwd = request.getParameter("userPwd");

logger.info("getToken.jsp params : "+ userId + " , " + pwd);

if (userId != null && pwd != null) {
	Token token = new Token();
	token.setUserId(userId);
	token.setUserPwd(pwd);
	token.setSubject(userId);
	token.setScope(1);
	
	DBconn dbconn = new DBconn();
	TokenControl control = new TokenControl();
	
	try {
		JSONObject obj = new JSONObject();
		obj.put("rToken", control.getRefreshToken(token));
		obj.put("aToken", control.getAccessToken(token));
		
		out.print("" + obj.toString());
		
	} catch(Exception e) {
		out.print("" + e.getMessage());
	}
	
} else {
	out.print("error - require parameter");

}
%>