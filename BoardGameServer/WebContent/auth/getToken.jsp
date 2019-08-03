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
	Person person = new Person();
	person.setUniqueId(userId);
	person.setPassword(pwd);
	
	DBconn dbconn = new DBconn();
	TokenControl control = new TokenControl();
	
	try {
		JSONObject obj = new JSONObject();
		String rToken = control.getRefreshToken(person);
		String aToken = control.getAccessToken(person);
	//	logger.info("token : "+ rToken +"/"+aToken);
		
		if(rToken != null && rToken.length() > 0 && aToken != null && aToken.length() > 0) {
			obj.put("rToken", rToken);
			obj.put("aToken", aToken);
			control.updateRefreshToken(userId, rToken); //DB에 리프레시 토큰 저장 
			out.print(obj.toString());
		} else {
			response.sendError(401, "인증 실패");
		}
		
		
	} catch(Exception e) {
		out.print("" + e.getMessage());
	}
	
} else {
	response.sendError(402, "인증 실패 - 파라미터 확인하세요 ");

}
%>