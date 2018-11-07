package kr.bacoder.coding.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.simple.JSONObject;

public class Photo {
    private int photoId;
    private int patientId;
    private String patientName;
    private String photoUrl;
    private String classification;
    private String doctor;
    private String date;
    private String uploader;
    private String comment;
    private int accessLv;
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setPhotoId(String photoId) {
		setPhotoId(Integer.parseInt(photoId));
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setPatientId(String patientId) {
		setPatientId(Integer.parseInt(patientId));
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getAccessLv() {
		return accessLv;
	}
	public void setAccessLv(int accessLv) {
		this.accessLv= accessLv;
	}
	public void setAccessLv(String accessLv) {
		setAccessLv(Integer.parseInt(accessLv));
	}
	public static Photo makePhoto(ResultSet rs) throws SQLException {
		Photo result = new Photo();
		result.setAccessLv(rs.getInt("accessLv"));
		result.setPatientName(rs.getString("patientName"));
		result.setClassification(rs.getString("classification"));
		result.setComment(rs.getString("comment"));
		result.setDate(rs.getString("date"));
		result.setDoctor(rs.getString("doctor"));
		result.setPatientId(rs.getInt("patientId"));
		result.setPhotoId(rs.getInt("id"));
		result.setPhotoUrl(rs.getString("photoUrl"));
		result.setUploader(rs.getString("uploader"));
		return result;
	}
	public static JSONObject parseJSON(Photo photo) {
		JSONObject result = new JSONObject();
		result.put("accessLv", photo.getAccessLv());
		result.put("patientName", photo.getPatientName());
		result.put("classification", photo.getClassification());
		result.put("comment", photo.getComment());
		result.put("date", photo.getDate());
		result.put("doctor", photo.getDoctor());
		result.put("patientId", photo.getPatientId());
		result.put("id", photo.getPhotoId());
		result.put("photoUrl", photo.getPhotoUrl());
		result.put("uploader", photo.getUploader());
		return result;
	}
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
