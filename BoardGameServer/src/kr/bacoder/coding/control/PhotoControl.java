package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Photo;
import kr.bacoder.coding.bean.PhotoPatientInfo;

public class PhotoControl extends Controller {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	public int addPhotoInfo(Photo photoInfo) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			String sql = "INSERT INTO PhotoInfo "
					+ "(patientId, photoUrl, classification, doctor, captureDate, uploader, comment, accessLv, sync, contentType, size, thumbnailFilename, thumbnailSize) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			java.sql.Date sqlDate = new java.sql.Date(photoInfo.getCaptureDate().getTime());
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoInfo.getPatientId());
			pstmt.setString(2, photoInfo.getPhotoUrl());
			pstmt.setString(3, photoInfo.getClassification());
			pstmt.setString(4, photoInfo.getDoctor());
			pstmt.setDate(5, sqlDate);
			pstmt.setString(6, photoInfo.getUploader());
			pstmt.setString(7, photoInfo.getComment());
			pstmt.setInt(8, photoInfo.getAccessLv());
			pstmt.setString(9, photoInfo.getSync());
			pstmt.setString(10, photoInfo.getContentType());
			pstmt.setInt(11, photoInfo.getFileSize());
			pstmt.setString(12, photoInfo.getThumbnailName());
			pstmt.setInt(13, photoInfo.getThumbnailSize());
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
					.append("photo.id, patient.patientId, photoUrl, classification, photo.doctor, date, uploader, comment, accessLv, patient.name AS patientName, age AS patientAge,")
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
					.append("patient.patientId AS patientId, patient.name AS patientName, patient.age AS patientAge,")
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
	
	public JSONArray getOnlyPhotos(Photo photo) {
		JSONArray result = new JSONArray();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder()
					.append("SELECT ").append(" ")
					.append("patient.patientId AS patientId, patient.name AS patientName, patient.age AS patientAge,")
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
					sql.append("order by photo.classification DESC, photo.id DESC ");
					//sql.append("limit 0, 50 ");
			System.out.println(sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(photo.getPatientId() > 0) {
				pstmt.setInt(1, photo.getPatientId());
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhotoPatientInfo p = PhotoPatientInfo.makeInfo(rs);
				result.add(PhotoPatientInfo.parseJSONforOpen(p));
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
	public List<PhotoPatientInfo> getEmergencyPhotos(Photo photo){
		List<PhotoPatientInfo> list = new ArrayList<>();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
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
			sql.append("WHERE photo.classification IS NOT NULL AND photo.classification like ? ")
				.append(" AND photo.date BETWEEN DATE_SUB(NOW(), INTERVAL ? DAY) AND NOW()")
				.append(" order by photo.id desc");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, photo.getClassification());
			pstmt.setInt(2, photo.getDay());
			logger.info(pstmt.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				PhotoPatientInfo p = PhotoPatientInfo.makeInfo(rs);
				list.add(p);
			}
		}catch(SQLException e) {
			
		}
		return list;
	}
	
	public List<PhotoPatientInfo> getPhotoList(Photo photo){
		List<PhotoPatientInfo> list = new ArrayList<>();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
				.append("photo.patientId AS patientId, patient.name AS patientName, age AS patientAge,")
				.append("patient.sex AS patientSex, patient.phone AS patientPhone, patient.address AS patientAddress,")
				.append("patient.birth AS patientBirth, patient.etc AS patientEtc,")
				.append("photo.accessLv, photo.classification, photo.comment, photo.date, photo.photoUrl, photo.uploader,")
				.append("photo.doctor, photo.id AS id, photo.sync AS sync, photo.captureDate AS captureDate")
				.append(" ")
				.append("FROM ").append(" ")
				.append("PatientInfo patient RIGHT JOIN PhotoInfo photo").append(" ")
				.append("ON ").append(" ")
				.append("photo.patientId = patient.patientId").append(" ")
				.append("WHERE photo.sync = ?");
			if(photo.getClassification() != null && photo.getClassification().length()>0) {
				sql.append(" AND photo.classification IS NOT NULL AND photo.classification like ")
				.append("'"+photo.getClassification()+"'");
			}
				sql.append(" order by photo.id desc");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(photo.getSync() == null || photo.getSync().length() ==0) {
				pstmt.setString(1, "2");
			} else {
				pstmt.setString(1, photo.getSync());
			}

			logger.info(pstmt.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				PhotoPatientInfo p = PhotoPatientInfo.makeInfo(rs);
				list.add(p);
			}
		}catch(SQLException e) {
			
		}
		return list;
	}
	
	
	public int updatePhoto(Photo photo) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE PhotoInfo SET ");
			
			if(hasString(photo.getComment())) {
				appendSql(sql, "comment");
			}
			if(hasString(photo.getClassification())) {
				appendSql(sql, "classification");
			}
			if(hasString(photo.getDoctor())) {
				appendSql(sql, "doctor");
			}
			sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",")+1, " ");
			sql.append("WHERE id = ?");
			
			int i = 1;
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(hasString(photo.getComment())) {
				pstmt.setString(i++, photo.getComment());
			}
			if(hasString(photo.getClassification())) {
				pstmt.setString(i++, photo.getClassification());
			}
			if(hasString(photo.getDoctor())) {
				pstmt.setString(i++, photo.getDoctor());
			}
			pstmt.setInt(i++, photo.getPhotoId());
			logger.info(pstmt.toString());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			
		}
		return result;
	}
	
	public int updatePhotoSync(Photo photo) {
		int result = 0;
		try(Connection conn =  new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			if(photo.getPhotoId() > 0) {
				sql.append("update PhotoInfo set sync=? where id =?");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, photo.getSync());
			pstmt.setInt(2, photo.getPhotoId());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
//			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public org.json.JSONObject toJSONObject(List<PhotoPatientInfo> input){
		org.json.JSONObject result = new org.json.JSONObject();
		org.json.JSONArray array = new org.json.JSONArray();
		Iterator<PhotoPatientInfo> iter = input.iterator();
		while(iter.hasNext()) {
			PhotoPatientInfo info = iter.next();
			array.put(PhotoPatientInfo.parseJSON(info));
		}
		
		result.put("list", array);
		return result;
	}
}
