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
		int k = 1;
		int j = 1;
		
		try(Connection conn =  getConnection()){
			
			String sql1 = "SELECT id FROM OPRecord WHERE patientId = ? AND opdate = ? AND doctor = ?";
			
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(i++, record.getPatientId());
			pstmt1.setString(i++, record.getOpdate());
			pstmt1.setString(i++, record.getDoctor());

			logger.info(pstmt1.toString());
			
			ResultSet result1 =pstmt1.executeQuery();
			
			
			if(result1.next() && result1.getInt(1) > 0) {
				//update
				String sql = "UPDATE OPRecord SET "
						+ "patientId = ?, patientName = ?, opdate = ?, doctor = ?, dx = ?, "
						+ "anesthesia = ?, opname = ?, opfinding = ?, opProcedure = ?, opfee = ? "
						+ "WHERE id = " + result1.getInt(1);
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(k++, record.getPatientId());
				pstmt.setString(k++, record.getPatientName());
				pstmt.setString(k++, record.getOpdate());
				pstmt.setString(k++, record.getDoctor());
				pstmt.setString(k++, record.getDx());
				pstmt.setString(k++, record.getAnesthesia());
				pstmt.setString(k++, record.getOpname());
				pstmt.setString(k++, record.getOpfinding());
				pstmt.setString(k++, record.getOpProcedure());
				pstmt.setString(k++, record.getOpfee());
				
				logger.info(pstmt.toString());
				
				result =pstmt.executeUpdate();
			} else {
				String sql = "INSERT INTO OPRecord "
						+ "(patientId, patientName, opdate, doctor, dx, anesthesia, opname, opfinding, opProcedure, opfee) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(j++, record.getPatientId());
				pstmt.setString(j++, record.getPatientName());
				pstmt.setString(j++, record.getOpdate());
				pstmt.setString(j++, record.getDoctor());
				pstmt.setString(j++, record.getDx());
				pstmt.setString(j++, record.getAnesthesia());
				pstmt.setString(j++, record.getOpname());
				pstmt.setString(j++, record.getOpfinding());
				pstmt.setString(j++, record.getOpProcedure());
				pstmt.setString(j++, record.getOpfee());
				
				logger.info(pstmt.toString());
				
				result =pstmt.executeUpdate();
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	
}
