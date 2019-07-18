<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.SooMacroControl"%>
<%@page import="kr.bacoder.coding.bean.SooMacro"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>

<%
Logger logger = Logger.getLogger("setQue.jsp");
String que = request.getParameter("que");

if(que==null || que.length()==0){
	que = "1";
}

SooMacroControl control = new SooMacroControl();
int result = control.setQue(Integer.parseInt(que));

out.print(result); 

%>