<%@page import="kr.bacoder.coding.control.TokenControl"%>
<%@page import="kr.bacoder.coding.bean.Token"%>
<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.bean.Group"%>
<%@page import="kr.bacoder.coding.control.GroupControl"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getGroupInfo.jsp");

String id = request.getParameter("id");
String token = request.getHeader("authorization");

logger.info("######id : "+id);

Group group = new Group();
GroupControl groupCtl = new GroupControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();

if(token != null && token.length()>0) {
	Person person = control.getPersonByToken(token);
	logger.info("id:"+person.getId());
	if(person != null && id != null && id.length() >0 ) {
		group = groupCtl.getGroupInfo(id, person.getUserLevel());
	} else {
		//token error (unauthorized)
	}
	out.print(group.toString());

} else {
	//token not found error
}

%>