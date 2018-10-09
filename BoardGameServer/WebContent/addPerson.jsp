<%@page import="kr.bacoder.coding.bean.Person"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("addPerson.jsp");

String name = request.getParameter("name");
String password = request.getParameter("password");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String address = request.getParameter("address");
String uniqueId = request.getParameter("uniqueId");

Person person = new Person();
person.setName(name);
person.setPassword(password);
person.setEmail(email);
person.setPhone(phone);
person.setAddress(address);
person.setUniqueId(uniqueId);

DBconn dbconn = new DBconn();

JSONObject json = new JSONObject();
json.put("result", dbconn.insertPerson(person));
String result = json.toJSONString();
%>
<%=result%>