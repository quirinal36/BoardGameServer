<%@page import="kr.bacoder.coding.control.OPRecordControl"%>
<%@page import="kr.bacoder.coding.bean.OPRecord"%>
<%@page import="kr.bacoder.coding.dev.TokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.util.logging.Logger"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %> 

<%
 	//	final String path = getServletContext().getRealPath("/upload");
 	Logger logger = Logger.getLogger("addOPRecord.jsp");

 	//MultipartRequest multi = null;
   //  int maxPostSize = 10 * 1024 * 1024; // 10MB
   //  String saveDirectory = request.getServletContext().getRealPath("/pps");
  //   try {
 //		multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8");
   //  } catch (Exception e) {
  //   	e.printStackTrace();
   //  }
  //response.setContentType("application/json");
  String body = null;
  StringBuilder stringBuilder = new StringBuilder();
  BufferedReader bufferedReader = null;

  TokenUtil token = new TokenUtil();
  String tokenStr = request.getHeader("Authorization");
  logger.info("request : " + request.toString());
  logger.info("IsValidToken : " + token.IsValidToken(tokenStr));
  
  if(tokenStr != null && token.IsValidToken(tokenStr) > 1) {
 	 logger.info("addOPRecord/IsValidToken : >1");
 	 try {
 	     InputStream inputStream = request.getInputStream();
 	     if (inputStream != null) {
 	         bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
 	         char[] charBuffer = new char[128];
 	         int bytesRead = -1;
 	         while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
 	             stringBuilder.append(charBuffer, 0, bytesRead);
 	         }
 	     } else {
 	         stringBuilder.append("");
 	     }
 	 } catch (IOException ex) {
 	     throw ex;
 	 } finally {
 	     if (bufferedReader != null) {
 	         try {
 	             bufferedReader.close();
 	         } catch (IOException ex) {
 	             throw ex;
 	         }
 	     }
 	 }

 	 	body = stringBuilder.toString();
 	 	JSONParser jsonParser = new JSONParser();
 	 	Object obj = jsonParser.parse( body );
 	 	JSONObject jsonRequest = (JSONObject) obj;

 	 	//JSONObject jsonObj = new JSONObject(body);
 	 	//jsonObj.getInt(arg0)
 	 	//jsonObj.getString();
 	 	
 		String patientId = (String) jsonRequest.get("pId");
 		String patientName = (String) jsonRequest.get("pName");
 		String opdate = (String) jsonRequest.get("opdate");
 		String doctor = (String) jsonRequest.get("doctor");
 		String dx = (String) jsonRequest.get("dx");
 		String anesthesia = (String) jsonRequest.get("anesthesia");
 		String opname = (String) jsonRequest.get("opname");
 		String opfinding = (String) jsonRequest.get("opfinding");
 		String opProcedure = (String) jsonRequest.get("opProcedure");
 		String opfee = (String) jsonRequest.get("opfee");
 		
 		
 		OPRecord record = new OPRecord();
 		
 		if(patientId != null){
 	record.setPatientId(patientId);
 		}
 		if(patientName != null){
 	record.setPatientName(patientName);
 		}
 		if(opdate != null){
 	record.setOpdate(opdate);
 		}
 		if(doctor != null){
 	record.setDoctor(doctor);
 		}
 		if(dx != null){
 	record.setDx(dx);
 		}
 		if(anesthesia != null){
 	record.setAnesthesia(anesthesia);
 		}
 		if(opname != null){
 	record.setOpname(opname);
 		}
 		if(opfinding != null){
 	record.setOpfinding(opfinding);
 		}	
 		if(opProcedure != null){
 	record.setOpProcedure(opProcedure);
 		} 
 		if(opfee != null){
 	record.setOpfee(opfee);
 		} 

 		JSONObject json = new JSONObject();
 		DBconn dbconn = new DBconn();
 		OPRecordControl control = new OPRecordControl();
 		
 		json.put("result", control.addRecordInfo(record));
 	 
  } else if (token.IsValidToken(tokenStr) == -1){
 	// logger.info("addOPRecord/IsValidToken : 0");

 	// out.print(json.toJSONString());
 	//out.print("Un-Authorized connection!");
 	response.sendError(401, "유효기간이 만료되었습니다.");
  } else {
	 	 logger.info("addOPRecord/IsValidToken : 0");

	  	// out.print(json.toJSONString());
	  	//out.print("Un-Authorized connection!");
	  	response.sendError(401, "인가되지 않은 접근입니다.");
}
 %>