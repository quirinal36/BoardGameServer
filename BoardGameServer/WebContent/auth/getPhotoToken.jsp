<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPhotoToken.jsp");

String userId = request.getParameter("userId");
String pwd = request.getParameter("userPwd");
String code = request.getParameter("code");
String expMin = request.getParameter("expMin");
String scope = request.getParameter("scope");

//logger.info("getPhotoToken.jsp params : "+ userId + " , " + pwd);
logger.info("code : "+userId+"/"+pwd+"/"+code);

if (userId != null && pwd != null && code.equals("qhdghkdtp")) {

	Token token = new Token();
	token.setUserId(userId);
	token.setUserPwd(pwd);
	if(scope != null && scope.length() > 0) {
		token.setScope(scope);
	} else {
		token.setScope("photo");
	}
	if(expMin != null && expMin.length() > 0) {
		token.setExpMin(Integer.parseInt(expMin));
	} else {
		token.setExpMin(0);
	}
	
	DBconn dbconn = new DBconn();
	TokenControl control = new TokenControl();
	
	try {
		String pToken = control.getPhotoToken(token);
		
		if(pToken != null && pToken.length() > 0) {
			out.print(pToken);
		} else {
			response.sendError(401, "인증 실패");
		}
	} catch(Exception e) {
		out.print("" + e.getMessage());
	}
} else {
	response.sendError(401, "인증 실패 - 파라미터 확인하세요 ");

}
%>