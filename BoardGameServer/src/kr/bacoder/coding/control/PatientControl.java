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
import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.bean.Photo;
import kr.bacoder.coding.bean.PhotoPatientInfo;

public class PatientControl extends DBconn{

	/**
	 * tagId,patientId 占쏙옙치占쏙옙 占쏙옙占쏙옙
	 * @param nfc
	 * @return
	 */
	public int delNfc(NfcTag nfc) {
		int result = 0;
		
		try(Connection conn = getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from NfcTag ")
			.append("WHERE tagId = ? and patientId = ? ");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,  nfc.getTagId());
			pstmt.setString(2,  nfc.getPatientId());
			logger.info(pstmt.toString());
			
			result = pstmt.executeUpdate();

			
		}catch(SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		
		return result;
	}
	
	public JSONArray getNfcListJson(NfcTag nfc){
//		List<NfcTag> list = new ArrayList<>();
		JSONArray array = new JSONArray();
		
		try(Connection conn = getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("select * from NfcTag ");
			if(nfc.getPatientId()!=null && nfc.getPatientId().length()>0) {
				sql.append("where patientId = ?");
			}else if(nfc.getTagId()!=null && nfc.getTagId().length()>0) {
				sql.append("where tagId = ? ");
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(nfc.getPatientId()!=null && nfc.getPatientId().length()>0) {
				pstmt.setString(1, nfc.getPatientId());
			}else if(nfc.getTagId()!=null && nfc.getTagId().length()>0) {
				pstmt.setString(1, nfc.getTagId());
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				NfcTag tag = new NfcTag();
				tag.setId(rs.getInt("id"));
				tag.setPatientId(rs.getString("patientId"));
				tag.setTagId(rs.getString("tagId"));
				
//				list.add(tag);
				array.add(tag.toString());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return array;
	}
	public List<NfcTag> getNfcList(NfcTag nfc){
		List<NfcTag> list = new ArrayList<>();
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("select * from NfcTag ");
			if(nfc.getPatientId()!=null && nfc.getPatientId().length()>0) {
				sql.append("where patientId = ?");
			}else if(nfc.getTagId()!=null && nfc.getTagId().length()>0) {
				sql.append("where tagId = ? ");
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(nfc.getPatientId()!=null && nfc.getPatientId().length()>0) {
				pstmt.setString(1, nfc.getPatientId());
			}else if(nfc.getTagId()!=null && nfc.getTagId().length()>0) {
				pstmt.setString(1, nfc.getTagId());
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				NfcTag tag = new NfcTag();
				tag.setId(rs.getInt("id"));
				tag.setPatientId(rs.getString("patientId"));
				tag.setTagId(rs.getString("tagId"));
				
				list.add(tag);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateNfc(NfcTag nfc) {
		int result = 0;
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			if(nfc.getId() > 0) {
				sql.append("update NfcTag set tagId=?, patientId=? where id =?");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getTagId());
			pstmt.setString(2, nfc.getPatientId());
			pstmt.setInt(3, nfc.getId());
			 logger.info("updateNfc : " + sql.toString());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	public int updateNfcPatientIdbytagId(NfcTag nfc) {
		int result = 0;
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			if(nfc.getId() > 0) {
				sql.append("update NfcTag set patientId=? where tagId =?");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getPatientId());
			pstmt.setString(2, nfc.getTagId());
			 logger.info("updateNfc : " + sql.toString());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	public int updateNfcPatientIdbyId(NfcTag nfc) {
		int result = 0;
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			if(nfc.getId() > 0) {
				sql.append("update NfcTag set patientId=? where id =?");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getPatientId());
			pstmt.setInt(2, nfc.getId());
			 logger.info("updateNfc : " + sql.toString());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * tagId占쏙옙占쏙옙占쏙옙 tag占십깍옙화 占싹깍옙
	 * 
	 * @param nfc
	 * @return
	 */
	public int deleteNfc(NfcTag nfc) {
		int result = 0;
		try(Connection conn =  getConnection()){
			String sql = new StringBuilder()
					.append("delete from NfcTag")
					.append(" ")
					.append("where tagId=?")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nfc.getTagId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	public int insertNfc(NfcTag nfc) {
		int result = 0;
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into NfcTag (tagId, patientId) values (?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getTagId());
			pstmt.setString(2, nfc.getPatientId());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Patient> getPatientListByNfc(NfcTag nfc){
		List<Patient> result = new ArrayList<>();
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, p_date, name, birth, sex, etc, doctor, memo, room, admission, patientId, doctorId, photoId"
					+ " FROM PatientInfo WHERE patientId IN (SELECT patientId FROM NfcTag WHERE tagId = ?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, nfc.getTagId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Patient patient = Patient.parseToPatient(rs);
				result.add(patient);
			}
		}catch(SQLException e) {
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Patient> getPatientByClassification(Photo photo) {
		List<Patient> list = new ArrayList<>();
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
				.append("id, photo, p_date, name, age, birth, sex, etc, doctor, memo, room, admission, patientId, doctorId, photoId")
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
			setErrorMsg(e.getMessage());
			e.printStackTrace();
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
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE PatientInfo ").append("SET photoId=?, photo=(SELECT photoUrl FROM PhotoInfo WHERE id=?)").append("WHERE patientId=?");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, patient.getPhotoId());
			pstmt.setInt(2, patient.getPhotoId());
			pstmt.setString(3, patient.getPatientId());

			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	public List<Patient> searchPatientByQuery(String search){
		List<Patient> result = new ArrayList<>();
		try(Connection conn =  getConnection()){
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
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	public int insertPatient(Patient patient) {
		int result = 0;
		int i = 1;
		
		try(Connection conn =  getConnection()){
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
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	public int updatePatient(Patient patient) {
		int result = 0;
		int i = 1;
		
		try(Connection conn =  getConnection()){
			
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
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	public int addPatient(Patient patient) {
		int result = 0;
		int i = 1;
		
		try(Connection conn =  getConnection()){
			StringBuilder sql = new StringBuilder();
			 sql.append("INSERT INTO PatientInfo ")
			 .append("(photo, p_date, name, age, birth, sex, address, phone, etc, ")
			 .append("doctor, memo, memo_emr, room, admission, patientId) ")
			 .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ")
			 .append("ON DUPLICATE KEY UPDATE ");
			
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
			if(hasString(patient.getMemo_emr())) {
				appendSql(sql, "memo_emr");
			}
			if(hasString(patient.getRoom())) {
				appendSql(sql, "room");
			}
			
			if(patient.getAge() > 0) {
				appendSql(sql, "age");
				//sql.append("age=?");
			}
			if(patient.isAdmission()) {
				appendSql(sql, "admission");
			//	sql.append("admission=?");
			}
			sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",")+1, " ");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
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
			pstmt.setString(i++, patient.getMemo_emr());
			pstmt.setString(i++, patient.getRoom());
			pstmt.setInt(i++, patient.isAdmission()?1:0);
			pstmt.setString(i++, patient.getPatientId());
			
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
			if(hasString(patient.getMemo_emr())) {
				pstmt.setString(i++, patient.getMemo_emr());
			}
			if(hasString(patient.getRoom())) {
				pstmt.setString(i++, patient.getRoom());
			}
			
			if(patient.getAge()>0) {
				pstmt.setInt(i++, patient.getAge());
			}
			if(patient.isAdmission()) {
				pstmt.setInt(i++, patient.isAdmission()?1:0);
			}
			
			logger.info(pstmt.toString());
			
			result =pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	
	public String getPatients(String query) {
		JSONObject json = new JSONObject();
		try(Connection conn =  getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
			.append("patient.id AS patientId, patient.name AS patientName, patient.age AS patientAge,")
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
			setErrorMsg(e.getMessage());
		}
		return json.toJSONString();
	}
	public String getPatient(String id, String query) {
		org.json.JSONObject result = new org.json.JSONObject();
		org.json.JSONArray array = new org.json.JSONArray();

		try(Connection conn =  getConnection()){
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
			setErrorMsg(e.getMessage());
		}
		result.put("list", array);
		return result.toString();
	}
	
	public String getMemo(String patientId) throws SQLException {
		Patient patient = new Patient();
		try(Connection conn = new DBconn().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT id, memo FROM PatientInfo WHERE patientId = ?");
			pstmt.setInt(1, Integer.parseInt(patientId));
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				patient.setId(rs.getInt("id"));
				patient.setMemo(rs.getString("memo"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return patient.toString();
	}
//	public String getPatientsByDoctor(String doctor, String search) {
//		JSONObject json = new JSONObject();
//		try(Connection conn =  getConnection()){
//
//			ResultSet rs;
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT ").append(" ")
//			.append("patient.id AS id, patient.name AS name, patient.age AS age,")
//			.append("patient.sex AS sex, patient.phone AS phone, patient.address AS address,")
//			.append("patient.birth AS birth, patient.etc AS etc, photo.photoUrl AS photo,")
//			.append("patient.memo, patient.room, patient.admission,")
//			.append("person.name AS doctor, patient.patientId AS patientId")
//			.append(" ")
//			.append("FROM ").append(" ")
//			.append("PatientInfo patient LEFT JOIN PhotoInfo photo").append(" ")
//			.append(" ON ").append(" ")
//			.append("photo.id = patient.photoId").append(" ")
//			.append(", Person person").append(" ")
//			.append(" WHERE patient.doctorId = ? and patient.doctorId = person.uniqueId").append(" ");
//
//			if(search!=null &&search.length()>0) {
//				search = "%"+search+"%";
//				sql.append("AND (patient.name like ? ")
//				.append("OR patient.memo like ? ")
//				.append("OR patient.patientId like ? )");
//			}
//			
//			PreparedStatement stmt = conn.prepareStatement(sql.toString());
//			stmt.setString(1, doctor);
//			
//			if(search!=null &&search.length()>0) {
////				search = "%"+search+"%";
//				stmt.setString(2, search);
//				stmt.setString(3, search);
//				stmt.setString(4, search);
//			}
//			
//			logger.info(stmt.toString());
//			
//			rs = stmt.executeQuery();
//
//			JSONArray array = new JSONArray();
//			while(rs.next()) {
//				Patient patient = Patient.parseToPatient(rs);
//
//				array.add(patient.toString());
//			}
//			json.put("list", array);
//		}catch(SQLException e) {
//			e.printStackTrace();
//			setErrorMsg(e.getMessage());
//		}
//		return json.toJSONString();
//	}
	public JSONObject getPatientsByDoctor(String doctor, String department) {
		JSONObject json = new JSONObject();
		try(Connection conn =  getConnection()){

			ResultSet rs;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
			.append("patient.id AS id, patient.name AS name, patient.age AS age,")
			.append("patient.sex AS sex, patient.phone AS phone, patient.address AS address,")
			.append("patient.birth AS birth, patient.etc AS etc, photo.id AS photoId, photo.photoUrl AS photo,")
			.append("patient.memo, patient.room, patient.admission,")
			.append("person.name AS doctor, patient.patientId AS patientId")
			.append(" ")
			.append("FROM ").append(" ")
			.append("PatientInfo patient LEFT JOIN PhotoInfo photo").append(" ")
			.append(" ON ").append(" ")
			.append("photo.id = patient.photoId").append(" ")
			.append(", Person person").append(" ");
		//	if(department!=null && department.length()>0 && department == "吏꾨즺遺�") {
				sql.append(" WHERE patient.doctor = (SELECT person.name FROM Person person WHERE person.uniqueId = ?) and patient.admission = 1");
				sql.append(" ").append("AND patient.doctor = person.name ");
				sql.append(" ").append("ORDER BY patient.room ASC ");

//			.append(" WHERE patient.doctorId = ? and patient.doctorId = person.uniqueId").append(" ")
//			.append("AND person.department = ?");
			
//			if(search!=null &&search.length()>0) {
//				search = "%"+search+"%";
//				sql.append("AND (patient.name like ? ")
//				.append("OR patient.memo like ? ")
//				.append("OR patient.patientId like ? )");
//			}
			
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, doctor);
//			stmt.setString(2, department);
			
//			if(search!=null &&search.length()>0) {
////				search = "%"+search+"%";
//				stmt.setString(2, search);
//				stmt.setString(3, search);
//				stmt.setString(4, search);
//			}
			
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
			setErrorMsg(e.getMessage());
		}
		
		return json;
	}
	public List<Patient> getPatientsByDoctorList(String doctorId, String search) {
		List<Patient> result = new ArrayList<>();
		
		try(Connection conn =  getConnection()){

			ResultSet rs;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ").append(" ")
			.append("patient.id AS id, patient.name AS name, patient.age AS age,")
			.append("patient.sex AS sex, patient.phone AS phone, patient.address AS address,")
			.append("patient.birth AS birth, patient.etc AS etc, photo.photoUrl AS photo,")
			.append("patient.memo, patient.room, patient.admission,")
			.append("doctor.doctorName AS doctor, patient.patientId AS patientId")
			.append(" ")
			.append("FROM ").append(" ")
			.append("PatientInfo patient LEFT JOIN PhotoInfo photo").append(" ")
			.append(" ON ").append(" ")
			.append("photo.id = patient.photoId").append(" ")
			.append(", Person person").append(" ")
			.append(" WHERE patient.doctorId = ? and patient.doctorId = person.uniqueId").append(" ");

			if(search!=null &&search.length()>0) {
				search = "%"+search+"%";
				sql.append("AND (patient.name like ? ")
				.append("OR patient.memo like ? ")
				.append("OR patient.patientId like ? )");

			}
			
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, doctorId);
			
			if(search!=null &&search.length()>0) {
//				search = "%"+search+"%";
				stmt.setString(2, search);
				stmt.setString(3, search);
				stmt.setString(4, search);
			}
			logger.info(stmt.toString());
			
			rs = stmt.executeQuery();

			while(rs.next()) {
				Patient patient = Patient.parseToPatient(rs);

				result.add(patient);
			}
			//json.put("list", array);
		}catch(SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	public Patient getPatientById(String patientId) {
		Patient patient = new Patient();
		try(Connection conn =  getConnection()){
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
			setErrorMsg(e.getMessage());
			return null;
		}
		return patient;
	}
	public Patient getPatientById(int id) {
		Patient patient = new Patient();
		try(Connection conn =  getConnection()){
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
			setErrorMsg(e.getMessage());
		}
		return patient;
	}
	public String getPatient(int id) {
		Patient patient = new Patient();
		try(Connection conn =  getConnection()){
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
			setErrorMsg(e.getMessage());
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
