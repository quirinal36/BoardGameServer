package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Doctor;
import kr.bacoder.coding.bean.NfcTag;
import kr.bacoder.coding.bean.OPRecord;
import kr.bacoder.coding.bean.Patient;
import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.bean.Photo;
import kr.bacoder.coding.bean.PhotoPatientInfo;

public class OPRecordControl extends DBconn{

	public int addRecordInfo(OPRecord record) {
		int result = 0;
		int i = 1;
		
		try(Connection conn =  getConnection()){
			String sql = "INSERT INTO OPRecord "
					+ "(patientId, patientName, opdate, doctor, dx, anesthesia, opname, opfinding, opProcedure, opfee) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, record.getPatientId());
			pstmt.setString(i++, record.getPatientName());
			pstmt.setString(i++, record.getOpdate());
			pstmt.setString(i++, record.getDoctor());
			pstmt.setString(i++, record.getDx());
			pstmt.setString(i++, record.getAnesthesia());
			pstmt.setString(i++, record.getOpname());
			pstmt.setString(i++, record.getOpfinding());
			pstmt.setString(i++, record.getOpProcedure());
			pstmt.setString(i++, record.getOpfee());
			
			logger.info(pstmt.toString());
			
			result =pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	
}
