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
	public int updatePerson(Person person) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
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
		try(Connection conn = new DBconn().getConnection()){
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
	
}
