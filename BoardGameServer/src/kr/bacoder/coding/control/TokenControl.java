package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Token;
import kr.bacoder.coding.dev.SecurityUtil;
import kr.bacoder.coding.dev.TokenUtil;

public class TokenControl extends DBconn {
	
	private static final int AccessTokenEXPHours = 1;
	private static final int RefreshTokenEXPHours = 24 * 28;
	
	private static final String ATokenSubject = "AccessToken";
	private static final String RTokenSubject = "RefreshToken";


	public int userValid(String userId, String userPwd)  {
		
		SecurityUtil security = new SecurityUtil();
		String ePwd = security.encryptSHA256(userPwd);
		int userLevel = 0;
		
		try(Connection conn =  getConnection()){
			String sql = "SELECT * FROM Person WHERE uniqueId = ? AND password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			logger.info(pstmt.toString());			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				logger.info("pwd check success : " + rs.getString("uniqueId"));
				userLevel = rs.getInt("userLevel");
				return userLevel;
			} else {
				logger.info("pwd check failed");
				return 0;
			}

		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			return 0;
		}
	}
	
	public String getAccessToken(Token token) {
		
		int userLv = userValid(token.getUserId(), token.getUserPwd());
		if(userLv > 0) {
			TokenUtil util = new TokenUtil();
			return util.getToken(ATokenSubject, token.getUserId(), userLv, AccessTokenEXPHours);
		} else {
			return null;
		}	
	}
	
	public String getRefreshToken(Token token) {
		
		int userLv = userValid(token.getUserId(), token.getUserPwd());
		if(userLv > 0) {
			TokenUtil util = new TokenUtil();
			return util.getToken(RTokenSubject, token.getUserId(), userLv, RefreshTokenEXPHours);
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
	
//	public String updateAToken(String rToken, String aToken) {
//		
//		
//		
//		return newAccessToken;
//	}
	
}
