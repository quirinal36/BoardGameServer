<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.bacoder.coding.bean.Board"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.BoardControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("insertBoards.jsp");
String data = new String();
String title = request.getParameter("title");
String writer = request.getParameter("writer");
String content = request.getParameter("content");
String date = request.getParameter("date");
String id = request.getParameter("id");

Board board = new Board();
board.setId(id);
board.setTitle(title);
board.setWriter(writer);
board.setContent(content);
board.setDate(date);

BoardControl boardCtl = new BoardControl();

JSONObject result = new JSONObject();
result.put("result", boardCtl.updateBoard(board));
out.print(result.toJSONString());
%>