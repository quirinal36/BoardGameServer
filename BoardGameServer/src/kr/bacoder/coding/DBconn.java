package kr.bacoder.coding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.bean.Patient;
import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.bean.Photo;

public class DBconn {
	Logger logger = Logger.getLogger(DBconn.class.getSimpleName());
	
	private String userName 	= "dev";
	private String password 	= "789gagul";
	private String dbms 		= "mysql";
	private String dbName 		= "new_schema";
	private String serverName 	= "35.234.23.104";
	private int portNumber 		= 3306;
	
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    if (this.dbms.equals("mysql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/" +
	                   this.dbName + "?" +
	                   "useSSL=false",
	                   connectionProps);
	    }
	    return conn;
	}
	public String getPerson(String phone, String deviceId) throws SQLException {
		Person person = new Person();
		try(Connection conn = getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Person WHERE phone =? and uniqueId = ?");
			pstmt.setString(1, phone);
			pstmt.setString(2, deviceId);
			logger.info("SELECT * FROM Person WHERE phone =? and uniqueId = ?");
//			logger.info("phone: " + phone);
//			logger.info("deviceId: " + deviceId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				person.setId(rs.getInt("NUM"));
				person.setName(rs.getString("name"));
				person.setEmail(rs.getString("email"));
				person.setPhone(rs.getString("phone"));
				person.setPhoto(rs.getString("photo"));
				person.setDepartment(rs.getString("department"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return person.toString();
	}
	public int insertPatient(Patient patient) {
		int result = 0;
		try(Connection conn = getConnection()){
			String sql = "INSERT INTO PatientInfo "
					+ "(photo, p_date, name) "
					+ "VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patient.getPhoto());
			pstmt.setString(2, new SimpleDateFormat("yyyyMMddhhmm", Locale.KOREA).format(new Date()));
			pstmt.setString(3, patient.getName());
			result =pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updatePatient(Patient patient) {
		int result = 0;
		try(Connection conn = getConnection()){
			String sql = "UPDATE PatientInfo "
					+ "SET photo=?, name=? "
					+ "WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patient.getPhoto());
			pstmt.setString(2, patient.getName());
			pstmt.setInt(3, patient.getId());
			result =pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getPatients() {
		JSONObject json = new JSONObject();
		try(Connection conn = getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM PatientInfo");

			JSONArray array = new JSONArray();
			while(rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				array.add(patient.toString());
			}
			json.put("list", array);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	public String getPatient(int id) {
		Patient patient = new Patient();
		try(Connection conn = getConnection()){
			final String sql = "SELECT * FROM PatientInfo WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				patient.setId(rs.getInt("id"));
				patient.setPhoto(rs.getString("photo"));
				patient.setP_date(rs.getString("p_date"));
				patient.setName(rs.getString("name"));
				patient.toString();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return patient.toString();
	}
	public int updatePerson(Person person) {
		int result = 0;
		try(Connection conn = getConnection()){
			String sql = "UPDATE Person "
					+ "SET name=?,address=?,email=?,photo=? WHERE phone= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getAddress());
			pstmt.setString(3, person.getEmail());
			pstmt.setString(4, person.getPhoto());
			pstmt.setString(5, person.getPhone());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int insertPerson(Person person) {
		int result = 0;
		try(Connection conn = getConnection()){
			String sql = "INSERT INTO Person "
					+ "(name, email, phone, password, uniqueId, photo, department) "
					+ "VALUES (?,?,?,?,?,?,?)";
			logger.info(sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getEmail());
			pstmt.setString(3, person.getPhone());
			pstmt.setString(4, person.getPassword());
			pstmt.setString(5, person.getUniqueId());
			pstmt.setString(6, person.getPhoto());
			pstmt.setString(7, person.getDepartment());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getData() throws SQLException, ClassNotFoundException{
		JSONObject resultObj = new JSONObject();
		try(Connection conn = getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM Map");

			JSONArray array = new JSONArray();
			while(rs.next()){
				int id = rs.getInt("idMap");
				String name = rs.getString("name");
				int xLoc = rs.getInt("x_loc");
				int yLoc = rs.getInt("y_loc");
				int price = rs.getInt("price");

				JSONObject item = new JSONObject();
				item.put("id", id);
				item.put("name", name);
				item.put("xLoc", xLoc);
				item.put("yLoc", yLoc);
				item.put("price", price);
				array.add(item);
			}
			resultObj.put("list", array);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultObj.toJSONString();
	}
	public int addPhotoInfo(Photo photoInfo) {
		int result = 0;
		try(Connection conn = getConnection()){
			String sql = "INSERT INTO PhotoInfo "
					+ "(patientId, patientName, photoUrl, classification, doctor, date, uploader, comment, accessLv) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoInfo.getPatientId());
			pstmt.setString(2, photoInfo.getPatientName());
			pstmt.setString(3, photoInfo.getPhotoUrl());
			pstmt.setString(4, photoInfo.getClassification());
			pstmt.setString(5, photoInfo.getDoctor());
			pstmt.setString(6, photoInfo.getDate());
			pstmt.setString(7, photoInfo.getUploader());
			pstmt.setString(8, photoInfo.getComment());
			pstmt.setInt(9, photoInfo.getAccessLv());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public JSONArray getPhotos(Photo photo) {
		JSONArray result = new JSONArray();
		try(Connection conn = getConnection()){
			String sql = "SELECT * FROM PhotoInfo WHERE patientId = ?";
			logger.info(sql);;
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photo.getPatientId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Photo p = Photo.makePhoto(rs);
				result.add(Photo.parseJSON(p));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
