package kr.bacoder.coding.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class PhotoPatientInfo extends Photo{
	private int patientId;
	private int patientAge;
	private String patientName;
	private String patientBirth;
	private String patientSex;
	private String patientAddress;
	private String patientPhone;
	private String patientEtc;
	
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
	
	public String getPatientBirth() {
		return patientBirth;
	}
	public void setPatientBirth(String patientBirth) {
		this.patientBirth = patientBirth;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getPatientEtc() {
		return patientEtc;
	}
	public void setPatientEtc(String patientEtc) {
		this.patientEtc = patientEtc;
	}
	public static PhotoPatientInfo makeInfo(ResultSet rs) throws SQLException {
		PhotoPatientInfo result = new PhotoPatientInfo();
		
		try {
			result.setAccessLv(rs.getInt("accessLv"));
			result.setClassification(rs.getString("classification"));
			result.setComment(rs.getString("comment"));
			result.setDate(rs.getString("date"));
			result.setDoctor(rs.getString("doctor"));
			result.setPatientId(rs.getInt("patientId"));
			result.setPhotoUrl(rs.getString("photoUrl"));
			result.setUploader(rs.getString("uploader"));
			result.setPhotoId(rs.getInt("id"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		result.setPatientId(rs.getInt("patientId"));
		result.setPatientName(rs.getString("patientName"));
		result.setPatientAge(rs.getInt("patientAge"));
		result.setPatientAddress(rs.getString("patientAddress"));
		result.setPatientBirth(rs.getString("patientBirth"));
		result.setPatientEtc(rs.getString("patientEtc"));
		result.setPatientPhone(rs.getString("patientPhone"));
		result.setPatientSex(rs.getString("patientSex"));
		
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
		result.put("patientAddress", info.getPatientAddress());
		result.put("patientBirth", info.getPatientBirth());
		result.put("patientEtc", info.getPatientEtc());
		result.put("patientPhone", info.getPatientPhone());
		result.put("patientSex", info.getPatientSex());
		return result;
	}
}
