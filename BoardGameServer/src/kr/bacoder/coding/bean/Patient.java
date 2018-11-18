package kr.bacoder.coding.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Patient {
	private int id;
	private String photo;
	private String p_date;
	private String name;
	private String doctor;
	private String birth;
	private String sex;
	private String address;
	private String phone;
	private String memo;
	private String room;
	private boolean admission;
	private String etc;
	private String patientId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public boolean isAdmission() {
		return admission;
	}
	public void setAdmission(boolean admission) {
		this.admission = admission;
	}
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public static Patient parseToPatient(ResultSet rs) throws SQLException {
		Patient result = new Patient();
		result.setId(rs.getInt("id"));
		result.setPhoto(rs.getString("photo"));
		result.setName(rs.getString("name"));
		result.setDoctor(rs.getString("doctor"));
		result.setBirth(rs.getString("birth"));
		result.setSex(rs.getString("sex"));
		result.setAddress(rs.getString("address"));
		result.setPhone(rs.getString("phone"));
		result.setMemo(rs.getString("memo"));
		result.setRoom(rs.getString("room"));
		result.setAdmission(rs.getInt("admission")>0);
		result.setPatientId(rs.getString("patientId"));
		return result;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
