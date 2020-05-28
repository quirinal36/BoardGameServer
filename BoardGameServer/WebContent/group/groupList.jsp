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
Logger logger = Logger.getLogger("groupList.jsp");

String param = request.getParameter("param");
String token = request.getHeader("authorization");

logger.info("######param : "+param);

Group group = new Group();
GroupControl groupCtl = new GroupControl();
JSONObject result = new JSONObject();
TokenControl control = new TokenControl();
List<Group> list = null;

if(token != null && token.length()>0) {
	Person person = control.getPersonByToken(token);
	logger.info("id:"+person.getId());
	if(person != null && param.equals("all")) {
		list = groupCtl.getGroupListAll(person.getId(), person.getUserLevel());
	} else if (person != null && param.equals("my")) {
		list = groupCtl.getGroupListMy(person.getId(), person.getUserLevel());

	} else if (person == null) {
		//token error (unauthorized)
	}
	out.print(list.toString());

} else {
	//token not found error
}

%>