<%@page import="kr.bacoder.coding.bean.Board"%>
<%@page import="java.util.List"%>
<%@page import="kr.bacoder.coding.control.BoardControl"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getBoards.jsp");
String data = new String();
String from = request.getParameter("from");
String to = request.getParameter("to");

BoardControl boardCtl = new BoardControl();
List<Board> boards = boardCtl.getBoards(Integer.parseInt(from), Integer.parseInt(to));
out.print(boardCtl.getBoardJsonArray(boards));
%>