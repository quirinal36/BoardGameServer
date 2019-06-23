<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.dev.TokenUtil"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("checkToken.jsp");

String aToken = request.getParameter("aToken");

if (aToken != null) {

	DBconn dbconn = new DBconn();
	Person person = new Person();
	TokenControl control = new TokenControl();
	TokenUtil util = new TokenUtil();
	
	if(util.IsValidToken(aToken) > 0) { //유효한 토큰이면 
		person = control.getPersonByToken(aToken);
		
		if(person != null){
			out.print(person.toString());
			
		} else { //person null
			response.sendError(401, "유효한 토큰으로 사용자 정보를 찾지 못했습니다.");
		}
	} else if(util.IsValidToken(aToken) == -1 ){  //유효기간 만료되었으면 
		response.sendError(495, "토큰 유효기간 만료");
	} else {
		response.sendError(401, "유효하지 않은 토큰입니다");
	}
	
} else {
	response.sendError(401, "인증 실패 - 파라미터 확인하세요 ");

}
%>