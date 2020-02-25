<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("login.jsp");

String from = request.getParameter("from");
String uuid = request.getParameter("uuid");

logger.info("login.jsp params : "+ from + " , " + uuid);

if (from != null && uuid != null) {
	Person person = new Person();
	person.setFrom(from);
	person.setUuid(uuid);
	
	DBconn dbconn = new DBconn();
	TokenControl control = new TokenControl();
	
	try {
		JSONObject obj = new JSONObject();
		//String rToken = control.getRefreshToken(person);
		Person personResp = control.getTokenByLogin(person);
	//	logger.info("token : "+ rToken +"/"+aToken);
		
		if(personResp.getaToken() != null && personResp.getaToken().length() > 1) {
			//obj.put("rToken", rToken);
			//obj.put("aToken", personResp.getaToken());
			//control.updateRefreshToken(userId, rToken); //DB에 리프레시 토큰 저장 
			//out.print(obj.toString());
			out.print(personResp.toString());
			
		} else if (personResp.getUserLevel() == 0) {
			response.sendError(402, "userlevel failure");
		} else {
			response.sendError(401, "인증 실패");
		}
		
		
	} catch(Exception e) {
		out.print("" + e.getMessage());
	}
	
} else {
	response.sendError(405, "인증 실패 - 파라미터 확인하세요 ");

}
%>