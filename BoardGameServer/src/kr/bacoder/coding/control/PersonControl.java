package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Person;

public class PersonControl {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
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
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
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
			pstmt.setString(4, person.getPassword());
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
