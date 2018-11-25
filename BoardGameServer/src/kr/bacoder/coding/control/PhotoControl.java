package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Photo;
import kr.bacoder.coding.bean.PhotoPatientInfo;

public class PhotoControl {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	public int addPhotoInfo(Photo photoInfo) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			String sql = "INSERT INTO PhotoInfo "
					+ "(patientId, photoUrl, classification, doctor, date, uploader, comment, accessLv) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoInfo.getPatientId());
			pstmt.setString(2, photoInfo.getPhotoUrl());
			pstmt.setString(3, photoInfo.getClassification());
			pstmt.setString(4, photoInfo.getDoctor());
			pstmt.setString(5, photoInfo.getDate());
			pstmt.setString(6, photoInfo.getUploader());
			pstmt.setString(7, photoInfo.getComment());
			pstmt.setInt(8, photoInfo.getAccessLv());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public JSONObject getPhoto(Photo photo) {
		JSONObject result = new JSONObject();
		try(Connection conn = new DBconn().getConnection()){
			String sql = new StringBuilder()
					.append("SELECT ").append(" ")
					.append("photo.id, patient.patientId, photoUrl, classification, photo.doctor, date, uploader, comment, accessLv, name AS patientName, age AS patientAge,")
					.append("patient.sex AS patientSex, patient.phone AS patientPhone, patient.address AS patientAddress,")
					.append("patient.birth AS patientBirth, patient.etc AS patientEtc")
					.append(" ")
					.append("FROM ").append(" ")
					.append("PhotoInfo photo, PatientInfo patient ").append(" ")
					.append("WHERE ").append(" ")
					.append("photo.id = ? AND ").append(" ")
					.append("patient.patientId = photo.patientId").toString();
			logger.info(sql +"id="+photo.getPhotoId());
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photo.getPhotoId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				PhotoPatientInfo p = PhotoPatientInfo.makeInfo(rs);
				result = PhotoPatientInfo.parseJSON(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public JSONArray getPhotos(Photo photo) {
		JSONArray result = new JSONArray();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder()
					.append("SELECT ").append(" ")
					.append("patient.patientId AS patientId, name AS patientName, age AS patientAge,")
					.append("patient.sex AS patientSex, patient.phone AS patientPhone, patient.address AS patientAddress,")
					.append("patient.birth AS patientBirth, patient.etc AS patientEtc,")
					.append("photo.accessLv, photo.classification, photo.comment, photo.date, photo.photoUrl, photo.uploader,")
					.append("photo.doctor, photo.id AS id")
					.append(" ")
					.append("FROM ").append(" ")
					.append("PatientInfo patient RIGHT JOIN PhotoInfo photo").append(" ")
					.append("ON ").append(" ")
					.append("photo.patientId = patient.patientId").append(" ");
					if(photo.getPatientId() > 0) {
						sql.append("WHERE patient.patientId = ?").append(" ");
					}
					sql.append("order by photo.id desc ");
					sql.append("limit 0, 20 ");
			System.out.println(sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(photo.getPatientId() > 0) {
				pstmt.setInt(1, photo.getPatientId());
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhotoPatientInfo p = PhotoPatientInfo.makeInfo(rs);
				result.add(PhotoPatientInfo.parseJSON(p));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int deletePhotoById(Photo photo) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM PhotoInfo WHERE id = ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, photo.getPhotoId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
