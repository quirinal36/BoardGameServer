<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("updateToken.jsp");

String rToken = request.getParameter("rToken");

//logger.info("updateToken.jsp params : "+ rToken);

if (rToken != null) {

	DBconn dbconn = new DBconn();
	TokenControl control = new TokenControl();
	String newAToken = "";
	
	try {
		newAToken = control.updateAToken(rToken);
		//JSONObject obj = new JSONObject();
		//obj.put("rToken", rToken);

		if(newAToken.length() > 0) {
			logger.info("UpdateToken succeed");
			out.print(newAToken);
		} else {
			logger.info("null");
			response.sendError(401, "인증 실패");
		}
		
	} catch(Exception e) {
		out.print("exception: " + e.getMessage());
	}
	
} else {
	response.sendError(401, "인증 실패 - 파라미터 확인하세요 ");

}
%>