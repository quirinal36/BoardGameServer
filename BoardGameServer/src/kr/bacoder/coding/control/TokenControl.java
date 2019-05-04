package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.bean.Token;
import kr.bacoder.coding.dev.SecurityUtil;
import kr.bacoder.coding.dev.TokenUtil;

/**
 * @author bonghwangse
 *
 */
public class TokenControl extends DBconn {
	
	private static final int AccessTokenEXPMins = 1;
	private static final int RefreshTokenEXPMins = 60 * 24 * 7;
	
	private static final String ATokenSubject = "AccessToken";
	private static final String RTokenSubject = "RefreshToken";


	public Person userValid(Person person)  {
		
		SecurityUtil security = new SecurityUtil();
		String ePwd = security.encryptSHA256(person.getPassword());
		
		try(Connection conn =  getConnection()){
			String sql = "SELECT * FROM Person WHERE uniqueId = ? AND password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getUniqueId());
			pstmt.setString(2, person.getPassword());
			
			logger.info(pstmt.toString());			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				logger.info("pwd check success : " + rs.getString("uniqueId"));
//				person.setAddress(rs.getString(Person.ADDRESS_KEY));
//				person.setAge(rs.getInt(Person.AGE_KEY));
//				person.setBirth(rs.getString(Person.BIRTH_KEY));
				person.setDepartment(rs.getString(Person.DEPARTMENT_KEY));
//				person.setEmail(rs.getString(Person.EMAIL_KEY));
//				person.setId(rs.getInt(Person.NUM_KEY));
//				person.setName(rs.getString(Person.NAME_KEY));
//				person.setPhone(rs.getString(Person.PHONE_KEY));
//				person.setPhoto(rs.getString(Person.PHOTO_KEY));
				person.setUniqueId(rs.getString(Person.UNIQUE_ID_KEY));
				person.setUserLevel(rs.getInt(Person.USER_LEVEL_KEY));
				person.setrToken(rs.getString(Person.R_TOKEN_KEY));
				
//				return person;
			} else {
				logger.info("pwd check failed");
//				return null;
			}

		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
//			return null;
		}
		return person;
	}
	
	public String getAccessToken(Person person) {	
		Person validPerson = new Person();
		validPerson = userValid(person); 
		int userLv = validPerson.getUserLevel();
		
		logger.info("getAccessToken : "+ userLv);
		
		if(userLv > 0) {
			TokenUtil util = new TokenUtil();
			return util.getToken(ATokenSubject, validPerson.getUniqueId(), userLv, AccessTokenEXPMins);
		} else {
			return "";
		}	
	}
	
	public String getRefreshToken(Person person) {
		Person validPerson = new Person();
		validPerson = userValid(person); 
		int userLv = validPerson.getUserLevel();
		
		logger.info("getRefreshToken : "+ userLv);
		
		if(userLv > 0) {
			TokenUtil util = new TokenUtil();
			return util.getToken(RTokenSubject, validPerson.getUniqueId(), userLv, RefreshTokenEXPMins);
		} else {
			return "";
		}	
	}
	
	
	/**
	 * update RefreshToken update into database
	 * @param userId
	 * @param rToken
	 * @return int result
	 */
	public int updateRefreshToken(String userId, String rToken) {
		
		int result = 0;
		try(Connection conn =  getConnection()) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE Person ");
			sql.append("SET ");
			sql.append("rToken=?").append(" "); 
			sql.append("WHERE uniqueId= ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rToken);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
		}
		return result;
	}
	

	/**
	 * @param rToken
	 * @return
	 */
	public String updateAToken(String rToken) {
		TokenUtil util = new TokenUtil();
		Token token = new Token();
		token = util.getInfoByToken(rToken);
		if(token.getSubject().equals("RefreshToken")) {
			return util.getToken(ATokenSubject, token.getUserId(), token.getRole(), AccessTokenEXPMins);
		} else {
			logger.info("subject : "+ token.getSubject());
			return "";
		}
	}
	
}
