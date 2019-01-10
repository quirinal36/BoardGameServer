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
import kr.bacoder.coding.bean.Patient;
import kr.bacoder.coding.bean.Photo;
import kr.bacoder.coding.bean.PhotoPatientInfo;

public class PatientControl extends Controller{
	Logger logger = Logger.getLogger(getClass().getSimpleName());

	public int insertNfc(NfcTag nfc) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into NfcTag (tagId, patientId) values (?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getTagId());
			pstmt.setString(2, nfc.getPatientId());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			
		}
		return result;
	}
	
	public List<Patient> getPatientListByNfc(NfcTag nfc){
		List<Patient> result = new ArrayList<>();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM PatientInfo WHERE patientId IN (SELECT patientId FROM NfcTag WHERE tagId = ?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getTagId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Patient patient = Patient.parseToPatient(rs);
				result.add(patient);
			}
		}catch(SQLException e) {
			
		}
		return result;
	}
	
	public List<Patient> getPatientByClassification(Photo photo) {
		List<Patient> list = new ArrayList<>();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
				.append("id, photo, p_date, name, age, birth, sex, phone, address, etc, doctor, memo, room, admission, patientId, doctorId, photoId")
				.append(" ")
				.append("FROM ").append(" ")
				.append("PatientInfo").append(" ")
				.append("WHERE patientId IN").append(" ");
			sql.append("(SELECT patientId FROM PhotoInfo ")
				.append("WHERE classification IS NOT NULL AND classification like ? ")
				.append(" AND date BETWEEN DATE_SUB(NOW(), INTERVAL ? DAY) AND NOW()")
				.append(" group by patientId)");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, photo.getClassification());
			pstmt.setInt(2, photo.getDay());
			logger.info(pstmt.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Patient patient = Patient.parseToPatient(rs);
				//PhotoPatientInfo p = PhotoPatientInfo.makeInfo(rs);
				list.add(patient);
			}
		}catch(SQLException e) {
			
		}
		return list;
	}
	public org.json.JSONArray parseToJsonArray(List<Patient> list){
		org.json.JSONArray result = new org.json.JSONArray();
		Iterator<Patient> iter = list.iterator();
		while(iter.hasNext()) {
			result.put(new org.json.JSONObject(iter.next().toString()));
		}
		return result;
	}
	public int setPatientRepresentPhoto(Patient patient) {

		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE PatientInfo ").append("SET photoId=?, photo=(SELECT photoUrl FROM PhotoInfo WHERE id=?)").append("WHERE patientId=?");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, patient.getPhotoId());
			pstmt.setInt(2, patient.getPhotoId());
			pstmt.setString(3, patient.getPatientId());

			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Patient> searchPatientByQuery(String search){
		List<Patient> result = new ArrayList<>();
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT patient.id, patient.photo, patient.name, patient.doctor, patient.birth, patient.sex, ").append(" ")
			.append("patient.address, patient.phone, patient.memo, patient.room, patient.admission, patient.patientId ").append(" ")
			.append("FROM PatientInfo patient left join PhotoInfo photo on photo.id = patient.photoId").append(" ");
			if(search!=null && search.length() > 0) {
				search = "%"+search+"%";
				sql.append("WHERE patient.patientId like ? ").append(" ");
				sql.append("OR patient.name like ? ").append(" ");
				sql.append("OR patient.memo like ?").append(" ");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(search!=null && search.length() > 0) {
				search = "%"+search+"%";
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setString(3, search);
			}
			logger.info(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(Patient.parseToPatient(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int insertPatient(Patient patient) {
		int result = 0;
		int i = 1;
		
		try(Connection conn = new DBconn().getConnection()){
			String sql = "INSERT INTO PatientInfo "
					+ "(photo, p_date, name, age, birth, sex, address, phone, etc, "
					+ "doctor, memo, room, admission, patientId) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, patient.getPhoto());
			pstmt.setString(i++, new SimpleDateFormat("yyyyMMddhhmm", Locale.KOREA).format(new Date()));
			pstmt.setString(i++, patient.getName());
			pstmt.setInt(i++, patient.getAge());
			pstmt.setString(i++, patient.getBirth());
			pstmt.setString(i++, patient.getSex());
			pstmt.setString(i++, patient.getAddress());
			pstmt.setString(i++, patient.getPhone());
			pstmt.setString(i++, patient.getEtc());
			pstmt.setString(i++, patient.getDoctor());
			pstmt.setString(i++, patient.getMemo());
			pstmt.setString(i++, patient.getRoom());
			pstmt.setInt(i++, patient.isAdmission()?1:0);
			pstmt.setString(i++, patient.getPatientId());
			
			logger.info(pstmt.toString());
			
			result =pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updatePatient(Patient patient) {
		int result = 0;
		int i = 1;
		
		try(Connection conn = new DBconn().getConnection()){
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE PatientInfo ");
			sql.append("SET ");
			if(hasString(patient.getPhoto())) {
				appendSql(sql, "photo");
			}
			if(hasString(patient.getName())) {
				appendSql(sql, "name");
			}
			if(hasString(patient.getBirth())) {
				appendSql(sql, "birth");
			}
			if(hasString(patient.getSex())) {
				appendSql(sql, "sex");
			}
			if(hasString(patient.getPhone())) {
				appendSql(sql, "phone");
			}
			if(hasString(patient.getAddress())) {
				appendSql(sql, "address");
			}
			if(hasString(patient.getEtc())) {
				appendSql(sql, "etc");
			}
			if(hasString(patient.getDoctor())) {
				appendSql(sql, "doctor");
			}
			if(hasString(patient.getMemo())) {
				appendSql(sql, "memo");
			}
			if(hasString(patient.getRoom())) {
				appendSql(sql, "room");
			}
			
			if(patient.getAge() > 0) {
				sql.append("age=?,");
			}
			sql.append("admission=?");
			sql.append(" WHERE id=?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(hasString(patient.getPhoto())) {
				pstmt.setString(i++, patient.getPhoto());
			}
			if(hasString(patient.getName())) {
				pstmt.setString(i++, patient.getName());
			}
			if(hasString(patient.getBirth())) {
				pstmt.setString(i++, patient.getBirth());
			}
			if(hasString(patient.getSex())) {
				pstmt.setString(i++, patient.getSex());
			}
			if(hasString(patient.getPhone())) {
				pstmt.setString(i++, patient.getPhone());
			}
			if(hasString(patient.getAddress())) {
				pstmt.setString(i++, patient.getAddress());
			}
			if(hasString(patient.getEtc())) {
				pstmt.setString(i++, patient.getEtc());
			}
			if(hasString(patient.getDoctor())) {
				pstmt.setString(i++, patient.getDoctor());
			}
			if(hasString(patient.getMemo())) {
				pstmt.setString(i++, patient.getMemo());
			}
			if(hasString(patient.getRoom())) {
				pstmt.setString(i++, patient.getRoom());
			}
			
			if(patient.getAge()>0) {
				pstmt.setInt(i++, patient.getAge());
			}
			pstmt.setInt(i++, patient.isAdmission()?1:0);
			pstmt.setInt(i++, patient.getId());
			
			logger.info(pstmt.toString());
			
			result =pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getPatients(String query) {
		JSONObject json = new JSONObject();
		try(Connection conn = new DBconn().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
			.append("patient.id AS patientId, name AS patientName, age AS patientAge,")
			.append("patient.sex AS patientSex, patient.phone AS patientPhone, patient.address AS patientAddress,")
			.append("patient.birth AS patientBirth, patient.etc AS patientEtc,")
			.append("photo.accessLv, photo.classification, photo.comment, photo.date, photo.photoUrl, photo.uploader,")
			.append("photo.doctor, photo.id AS id").append(" ")
			.append("patient.doctor, patient.memo, patient.room, patient.admission, patient.patientId")
			.append(" ")
			.append("FROM ").append(" ")
			.append("PatientInfo patient RIGHT JOIN PhotoInfo photo").append(" ")
			.append("ON ").append(" ")
			.append("photo.patientId = patient.id").append(" ");

			rs = stmt.executeQuery(sql.toString());

			JSONArray array = new JSONArray();
			while(rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setBirth(rs.getString("birth"));
				patient.setEtc(rs.getString("etc"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setDoctor(rs.getString("doctor"));
				patient.setMemo(rs.getString("memo"));
				patient.setRoom(rs.getString("room"));
				patient.setAdmission(rs.getInt("admission") > 0);
				patient.setPatientId(rs.getString("patientId"));
				array.add(patient.toString());
			}
			json.put("list", array);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	public String getPatient(String id, String query) {
		org.json.JSONObject result = new org.json.JSONObject();
		org.json.JSONArray array = new org.json.JSONArray();

		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM PatientInfo ");
			if(id != null && query != null) {
				sql.append("WHERE patientId=?");
				sql.append(" OR ");
				sql.append("name like ?");
			} else if(id!=null && query == null) {
				sql.append("WHERE patientId=?");
			}else if(id==null && query!=null) {
				sql.append("WHERE name like ?");
			}

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(id != null && query==null) {
				pstmt.setString(1, id);
			}else if(id != null && query!=null) {
				pstmt.setString(1, id);
				pstmt.setString(2, "%"+query+"%");
			}else if(id == null && query != null) {
				pstmt.setString(1, "%"+query+"%");
			}

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setBirth(rs.getString("birth"));
				patient.setEtc(rs.getString("etc"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setDoctor(rs.getString("doctor"));
				patient.setMemo(rs.getString("memo"));
				patient.setRoom(rs.getString("room"));
				patient.setAdmission(rs.getInt("admission")>0);
				patient.setPatientId(rs.getString("patientId"));
				array.put(patient.toString());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		result.put("list", array);
		return result.toString();
	}
	public String getPatientsByDoctor(Doctor doctor, String search) {
		JSONObject json = new JSONObject();
		try(Connection conn = new DBconn().getConnection()){

			ResultSet rs;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
			.append("patient.id AS id, patient.name AS name, age AS age,")
			.append("patient.sex AS sex, patient.phone AS phone, patient.address AS address,")
			.append("patient.birth AS birth, patient.etc AS etc, photo.photoUrl AS photo,")
			.append("patient.memo, patient.room, patient.admission,")
			.append("doctor.doctorName AS doctor, patient.patientId AS patientId")
			.append(" ")
			.append("FROM ").append(" ")
			.append("PatientInfo patient LEFT JOIN PhotoInfo photo").append(" ")
			.append(" ON ").append(" ")
			.append("photo.id = patient.photoId").append(" ")
			.append(", Doctor doctor").append(" ")
			.append(" WHERE patient.doctorId = ? and patient.doctorId = doctor.id").append(" ");

			if(search!=null &&search.length()>0) {
				search = "%"+search+"%";
				sql.append("AND (patient.name like ? ")
				.append("OR patient.memo like ? ")
				.append("OR patient.patientId like ? )");

			}
			
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, doctor.getId());
			
			if(search!=null &&search.length()>0) {
//				search = "%"+search+"%";
				stmt.setString(2, search);
				stmt.setString(3, search);
				stmt.setString(4, search);
			}
			
			logger.info(stmt.toString());
			
			rs = stmt.executeQuery();

			JSONArray array = new JSONArray();
			while(rs.next()) {
				Patient patient = Patient.parseToPatient(rs);

				array.add(patient.toString());
			}
			json.put("list", array);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	public List<Patient> getPatientsByDoctorList(Doctor doctor, String search) {
		List<Patient> result = new ArrayList<>();
		
		JSONObject json = new JSONObject();
		try(Connection conn = new DBconn().getConnection()){

			ResultSet rs;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
			.append("patient.id AS id, patient.name AS name, age AS age,")
			.append("patient.sex AS sex, patient.phone AS phone, patient.address AS address,")
			.append("patient.birth AS birth, patient.etc AS etc, photo.photoUrl AS photo,")
			.append("patient.memo, patient.room, patient.admission,")
			.append("doctor.doctorName AS doctor, patient.patientId AS patientId")
			.append(" ")
			.append("FROM ").append(" ")
			.append("PatientInfo patient LEFT JOIN PhotoInfo photo").append(" ")
			.append(" ON ").append(" ")
			.append("photo.id = patient.photoId").append(" ")
			.append(", Doctor doctor").append(" ")
			.append(" WHERE patient.doctorId = ? and patient.doctorId = doctor.id").append(" ");

			if(search!=null &&search.length()>0) {
				search = "%"+search+"%";
				sql.append("AND (patient.name like ? ")
				.append("OR patient.memo like ? ")
				.append("OR patient.patientId like ? )");

			}
			
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, doctor.getId());
			
			if(search!=null &&search.length()>0) {
//				search = "%"+search+"%";
				stmt.setString(2, search);
				stmt.setString(3, search);
				stmt.setString(4, search);
			}
			
			rs = stmt.executeQuery();

			JSONArray array = new JSONArray();
			while(rs.next()) {
				Patient patient = Patient.parseToPatient(rs);

				result.add(patient);
				//array.add(patient.toString());
			}
			//json.put("list", array);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Patient getPatientById(String patientId) {
		Patient patient = new Patient();
		try(Connection conn = new DBconn().getConnection()){
			final String sql = "SELECT * FROM PatientInfo WHERE patientId=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patientId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				patient.setId(rs.getInt("id"));
				patient.setAge(rs.getInt("age"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setBirth(rs.getString("birth"));
				patient.setEtc(rs.getString("etc"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setDoctor(rs.getString("doctor"));
				patient.setMemo(rs.getString("memo"));
				patient.setRoom(rs.getString("room"));
				patient.setAdmission(rs.getInt("admission")>0);
				patient.setPatientId(rs.getString("patientId"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}
	public Patient getPatientById(int id) {
		Patient patient = new Patient();
		try(Connection conn = new DBconn().getConnection()){
			final String sql = "SELECT * FROM PatientInfo WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				patient.setId(rs.getInt("id"));
				patient.setAge(rs.getInt("age"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setBirth(rs.getString("birth"));
				patient.setEtc(rs.getString("etc"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setDoctor(rs.getString("doctor"));
				patient.setMemo(rs.getString("memo"));
				patient.setRoom(rs.getString("room"));
				patient.setAdmission(rs.getInt("admission")>0);
				patient.setPatientId(rs.getString("patientId"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}
	public String getPatient(int id) {
		Patient patient = new Patient();
		try(Connection conn = new DBconn().getConnection()){
			final String sql = "SELECT * FROM PatientInfo WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				patient.setId(rs.getInt("id"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setBirth(rs.getString("birth"));
				patient.setEtc(rs.getString("etc"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setDoctor(rs.getString("doctor"));
				patient.setMemo(rs.getString("memo"));
				patient.setRoom(rs.getString("room"));
				patient.setAdmission(rs.getInt("admission")>0);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return patient.toString();
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
