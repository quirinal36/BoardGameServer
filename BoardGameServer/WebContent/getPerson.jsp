<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getPerson.jsp");
String data = new String();
String name = request.getParameter("name");
String password = request.getParameter("password");
logger.info("getPerson.jsp");
logger.info("name: " + name +"/password: " + password);

DBconn dbconn = new DBconn();
JSONObject resultObj = new JSONObject();
Connection conn = dbconn.getConnection();
try{
	PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM Person WHERE name =? and password = ?");
	pstmt.setString(1, name);
	pstmt.setString(2, password);
	
	ResultSet rs = pstmt.executeQuery();
	if(rs.next()){
		resultObj.put("result", rs.getObject(1));
	}
}catch(SQLException e){
	e.printStackTrace();
}
out.print(resultObj.toString());
%>
