<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.control.SooMacroControl"%>
<%@page import="kr.bacoder.coding.bean.SooMacro"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.logging.Logger"%>

<%
Logger logger = Logger.getLogger("getLatestVersion.jsp");

String ipAddress = request.getParameter("ipAddress");
String prevVer = request.getParameter("prevVer");
String downloader = request.getParameter("downloader");

SooMacro macro = new SooMacro();

if(ipAddress!=null && ipAddress.length()!=0){
	macro.setIpAddress(ipAddress);
}
if(prevVer!=null && prevVer.length()!=0){
	macro.setPreviousVer(Integer.parseInt(prevVer));
}
if(downloader!=null && downloader.length()!=0){
	macro.setDownloader(downloader);
}

SooMacroControl control = new SooMacroControl();
String verNumAndFileName = control.getLatestVerFileName(macro);

out.print(verNumAndFileName); //verNum,fileName


%>