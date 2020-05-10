package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Patient;
import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.dev.BongPasswordEncoder;
import kr.bacoder.coding.dev.SecurityUtil;

public class PersonControl {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	public int disLike(int id) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("delete from LikePatient where id = ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			
			logger.info(pstmt.toString());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public int disLike(Person person, Patient patient) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("delete from LikePatient where personId = ? and patientId = ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, person.getUniqueId());
			pstmt.setString(2, patient.getPatientId());
			
			logger.info(pstmt.toString());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public int insertLike(Person person, Patient patient) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO LikePatient (personId, patientId) values (?, ?)");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, person.getUniqueId());
			pstmt.setString(2, patient.getPatientId());
			logger.info(pstmt.toString());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public String getPerson(String phone, String deviceId) throws SQLException {
		Person person = new Person();
		try(Connection conn = new DBconn().getConnection()){
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
	public String getPerson(String name, String birth, String uniqueId) throws SQLException {
		Person person = new Person();
		try(Connection conn = new DBconn().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Person WHERE name =? and birth = ? and uniqueId = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, uniqueId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				person.setId(rs.getInt("NUM"));
				person.setName(rs.getString("name"));
				person.setPhone(rs.getString("phone"));
				person.setPhoto(rs.getString("photo"));
				person.setUniqueId(rs.getString("uniqueId"));
				person.setDepartment(rs.getString("department"));
				person.setBirth(rs.getString("birth"));
				person.setUserLevel(rs.getInt("userLevel"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return person.toString();
	}
	public String getPerson(String name, String birth, String uniqueId, String password) throws SQLException {
		PasswordEncoder passwordEncoder = new BongPasswordEncoder();
		
		Person person = new Person();
		try(Connection conn = new DBconn().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Person WHERE name =? and birth = ? and uniqueId = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, uniqueId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				person.setId(rs.getInt("NUM"));
				person.setName(rs.getString("name"));
				person.setPhone(rs.getString("phone"));
				person.setPhoto(rs.getString("photo"));
				person.setUniqueId(rs.getString("uniqueId"));
				person.setDepartment(rs.getString("department"));
				person.setBirth(rs.getString("birth"));
				person.setUserLevel(rs.getInt("userLevel"));
				person.setPassword(rs.getString("password"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		logger.info(person.toString());
		boolean passwordResult = passwordEncoder.matches(password, person.getPassword());
		logger.info("match=" + passwordResult);
		return person.toString();
	}
	public String getPerson(String uniqueId) throws SQLException {
		Person person = new Person();
		try(Connection conn = new DBconn().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Person WHERE uniqueId = ?");
			pstmt.setString(1, uniqueId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				person.setId(rs.getInt("NUM"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return person.toString();
	}
	
	public int insertPerson(Person person) {
//		PasswordEncoder passwordEncoder = new BongPasswordEncoder();
		
//		SecurityUtil security = new SecurityUtil();
//		String ePwd = security.encryptSHA256(person.getPassword());	
		PasswordEncoder passwordEncoder = new BongPasswordEncoder();
		
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			String sql = "INSERT INTO Person "
					+ "(name, email, phone, password, uniqueId, photo, department, userLevel) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			
			logger.info(sql.toString());
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getEmail());
			pstmt.setString(3, person.getPhone());
			pstmt.setString(4, passwordEncoder.encode(person.getPassword()));
			pstmt.setString(5, person.getUniqueId());
			pstmt.setString(6, person.getPhoto());
			pstmt.setString(7, person.getDepartment());
			pstmt.setInt(8, person.getUserLevel());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int insertPersonByUuid(Person person) {
		
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			String sql = "INSERT INTO Person "
					+ "(name, phone, birth, sex, uuid, userLevel) "
					+ "VALUES (?,?,?,?,?,?)";
			
			logger.info(sql.toString());
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getPhone());
			pstmt.setString(3, person.getBirth());
			pstmt.setString(4, person.getSex());
			pstmt.setString(5, person.getUuid());
			pstmt.setInt(6, person.getUserLevel());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updatePerson(Person person) {
		
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE Person ");
			sql.append("SET ");
			if(person.getName()!=null && person.getName().length()>0) {
				sql.append("name=?,"); 
			}
			if(person.getEmail()!=null && person.getEmail().length()>0) {
				sql.append("email=?,");
			}
			if(person.getPhone()!=null && person.getPhone().length()>0) {
				sql.append("phone=?,");
			}
			if(person.getPhoto()!=null && person.getPhoto().length()>0) {
				sql.append("photo=?,");
			}
			if(person.getDepartment()!=null && person.getDepartment().length()>0) {
				sql.append("department=?,");
			}
			if(person.getUserLevel()>=0) {
				sql.append("userLevel=? ");
			}
			if(person.getBirth()!=null && person.getBirth().length()>0) {
				sql.append("birth=?,");
			}
			
			sql.append("WHERE uniqueId=?");
				
			int i = 1;
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			if(person.getName()!=null && person.getName().length()>0) {
				pstmt.setString(i++, person.getName());
			}
			if(person.getEmail()!=null && person.getEmail().length()>0) {
				pstmt.setString(i++, person.getEmail());	
			}
			if(person.getPhone()!=null && person.getPhone().length()>0) {
				pstmt.setString(i++, person.getPhone());
			}
			if(person.getPhoto()!=null && person.getPhoto().length()>0) {
				pstmt.setString(i++, person.getPhoto());
			}
			if(person.getDepartment()!=null && person.getDepartment().length()>0) {
				pstmt.setString(i++, person.getDepartment());
			}
			if(person.getUserLevel() >= 0 ) {
				pstmt.setInt(i++, person.getUserLevel());
			}
			if(person.getBirth()!=null && person.getBirth().length()>0) {
				pstmt.setString(i++, person.getBirth());
			}
			
			pstmt.setString(i, person.getUniqueId());
			
			logger.info(pstmt.toString());
			
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Person getPersonByUniqueId(Person person) {
		
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM Person WHERE uniqueId=?");
			PreparedStatement pstmt = conn.prepareStatement(builder.toString());
			pstmt.setString(1, person.getUniqueId());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				person.setAddress(rs.getString(Person.ADDRESS_KEY));
				person.setAge(rs.getInt(Person.AGE_KEY));
				person.setBirth(rs.getString(Person.BIRTH_KEY));
				person.setDepartment(rs.getString(Person.DEPARTMENT_KEY));
				person.setEmail(rs.getString(Person.EMAIL_KEY));
				person.setId(rs.getInt(Person.NUM_KEY));
				person.setName(rs.getString(Person.NAME_KEY));
				person.setPhone(rs.getString(Person.PHONE_KEY));
				person.setPhoto(rs.getString(Person.PHOTO_KEY));
				person.setUniqueId(rs.getString(Person.UNIQUE_ID_KEY));
				person.setUserLevel(rs.getInt(Person.USER_LEVEL_KEY));
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
}
