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

public class TokenControl extends DBconn {
	
	private static final int AccessTokenEXPMins = 60 * 24;
	private static final int RefreshTokenEXPMins = 60 * 24 * 28;
	private static final int PhotoTokenEXPMins = 60 * 24;

	private static final String ATokenSubject = "AccessToken";
	private static final String RTokenSubject = "RefreshToken";
	private static final String PTokenSubject = "PhotoToken";

	public Person userValid(Person person)  {
		
		SecurityUtil security = new SecurityUtil();
		String ePwd = security.encryptSHA256(person.getPassword());
		
		try(Connection conn =  getConnection()){
			String sql = "SELECT * FROM Person WHERE uniqueId = ? AND password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getUniqueId());
			pstmt.setString(2, ePwd);
			
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
				
				return person;
			} else {
				logger.info("pwd check failed");
				return null;
			}

		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			return null;
		}
	}
	
	//userLv=0
	public String getPhotoToken(Token token) {
		Person person = new Person();
		person.setUniqueId(token.getUserId());
		person.setPassword(token.getUserPwd());
		String scope = "";
		int expMin = 0;
		if(token.getScope() != null) {
			scope = token.getScope();
		} 
		if(token.getExpMin() == 0) {
			expMin = PhotoTokenEXPMins;
		} else {
			expMin = token.getExpMin();
		}
		if(userValid(person) != null) {
			TokenUtil util = new TokenUtil();
			return util.getToken(PTokenSubject, person.getUniqueId(), 0, PhotoTokenEXPMins, scope);
		} else {
			return null;
		}
	}
	
	public String getAccessToken(Person person) {	
		Person validPerson = new Person();
		validPerson = userValid(person); 
		
		if(validPerson != null) {
			int userLv = validPerson.getUserLevel();
			
			logger.info("getAccessToken : "+ userLv);
			
			if(userLv > 0) {
				TokenUtil util = new TokenUtil();
				return util.getToken(ATokenSubject, validPerson.getUniqueId(), userLv, AccessTokenEXPMins, "all");
			} else {
				return null;
			}	
		} else {
			return null;
		}
	}
	
	public String getRefreshToken(Person person) {
		Person validPerson = new Person();
		validPerson = userValid(person); 
		
		if(validPerson != null) {
			int userLv = validPerson.getUserLevel();
			
			logger.info("getRefreshToken : "+ userLv);
			
			if(userLv > 0) {
				TokenUtil util = new TokenUtil();
				return util.getToken(RTokenSubject, validPerson.getUniqueId(), userLv, RefreshTokenEXPMins, "all");
			} else {
				return null;
			}	
		} else {
			return null;
		}
	}
	
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
	
	public String updateAToken(String rToken) {
		TokenUtil util = new TokenUtil();
		//String newAccessToken = "";	
		Person person = new Person();
		person = util.getInfoByToken(rToken);
		logger.info("TokenControl.udateAToken (userId): "+ person.getUniqueId());
		
		if(person != null && person.getUserLevel() > 1) {
			return util.getToken(ATokenSubject, person.getUniqueId(), person.getUserLevel(), AccessTokenEXPMins, "all");
		} else {
			return null;
		}
		
	}
	
	public Person getPersonByToken(String aToken) {
		TokenUtil util = new TokenUtil();
		Person person = new Person();
		String uniqueId = util.getIdByToken(aToken); //유효성 검사 및 id 얻
		
		if(aToken != null && uniqueId != null) {			
			
			try(Connection conn = new DBconn().getConnection()){
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Person WHERE uniqueId = ?");
				pstmt.setString(1, uniqueId);
				
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
			return person;
			
		} else {
			return null;
		}
	}
	
}
