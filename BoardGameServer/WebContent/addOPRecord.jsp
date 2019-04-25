<%@page import="kr.bacoder.coding.control.OPRecordControl"%>
<%@page import="kr.bacoder.coding.bean.OPRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.bacoder.coding.DBconn"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.util.logging.Logger"%>
<%
//	final String path = getServletContext().getRealPath("/upload");
	Logger logger = Logger.getLogger("addOPRecord.jsp");

	String patientId = request.getParameter("patientId");
	String opdate = request.getParameter("opdate");
	String doctor = request.getParameter("doctor");
	String dx = request.getParameter("dx");
	String anesthesia = request.getParameter("anesthesia");
	String opname = request.getParameter("opname");
	String opfinding = request.getParameter("opfinding");
	String opProcedure = request.getParameter("opProcedure");
	String opfee = request.getParameter("opfee");
	
	
	OPRecord record = new OPRecord();
	
	if(patientId != null){
		record.setPatientId(patientId);
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
	
	out.print(json.toJSONString());
%>