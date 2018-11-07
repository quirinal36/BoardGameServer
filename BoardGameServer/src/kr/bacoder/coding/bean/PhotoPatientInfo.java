package kr.bacoder.coding.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class PhotoPatientInfo extends Photo{
	int patientId;
	int patientAge;
	String patientName;
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public static PhotoPatientInfo makeInfo(ResultSet rs) throws SQLException {
		PhotoPatientInfo result = new PhotoPatientInfo();
		if(rs.next()) {
			result.setAccessLv(rs.getInt("accessLv"));
			result.setClassification(rs.getString("classification"));
			result.setComment(rs.getString("comment"));
			result.setDate(rs.getString("date"));
			result.setDoctor(rs.getString("doctor"));
			result.setPatientId(rs.getInt("patientId"));
			result.setPhotoId(rs.getInt("id"));
			result.setPhotoUrl(rs.getString("photoUrl"));
			result.setUploader(rs.getString("uploader"));
			result.setPatientId(rs.getInt("patientId"));
			result.setPatientName(rs.getString("patientName"));
			result.setPatientAge(rs.getInt("patientAge"));
		}
		return result;
	}
	public static JSONObject parseJSON(PhotoPatientInfo info) {
		JSONObject result = new JSONObject();
		result.put("accessLv", info.getAccessLv());
		result.put("classification", info.getClassification());
		result.put("comment", info.getComment());
		result.put("date", info.getDate());
		result.put("doctor", info.getDoctor());
		result.put("patientId", info.getPatientId());
		result.put("patientName", info.getPatientName());
		result.put("id", info.getPhotoId());
		result.put("photoUrl", info.getPhotoUrl());
		result.put("uploader", info.getUploader());
		result.put("patientAge", info.getPatientAge());
		return result;
	}
}
