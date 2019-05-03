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
	
	private static final int AccessTokenEXPHours = 2;
	private static final int RefreshTokenEXPHours = 24 * 14;


	public boolean userValid(String userId, String userPwd)  {
		
		SecurityUtil security = new SecurityUtil();
		String ePwd = security.encryptSHA256(userPwd);

		try(Connection conn =  getConnection()){
			String sql = "SELECT * FROM Person WHERE uniqueId = ? AND password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			logger.info(pstmt.toString());			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				logger.info("pwd check success : " + rs.getString("uniqueId"));
				return true;
			} else {
				logger.info("pwd check failed");
				return false;
			}

		}catch (SQLException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			return false;
		}
	}
	
	public String getAccessToken(Token token) {
		
		if(userValid(token.getUserId(), token.getUserPwd())) {
			TokenUtil util = new TokenUtil();
			return util.getToken(token.getSubject(), token.getScope(), AccessTokenEXPHours);
		} else {
			return null;
		}	
	}
	
	public String getRefreshToken(Token token) {
		
		if(userValid(token.getUserId(), token.getUserPwd())) {
			TokenUtil util = new TokenUtil();
			return util.getToken(token.getSubject(), token.getScope(), RefreshTokenEXPHours);
		} else {
			return null;
		}	
	}
	
//	public void updateRefreshToken(String rToken) {
//		
//		int result = 0;
//		try(Connection conn =  getConnection()) {
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE Person ");
//			sql.append("SET ");
//			if(person.getName()!=null && person.getName().length()>0) {
//				sql.append("name=?,"); 
//			}
//
//			
//			sql.append("WHERE uniqueId=?");
//			
//			logger.info(pstmt.toString());			
//			
//			 rs = pstmt.executeUpdate();
//			
//		}catch (SQLException e) {
//			e.printStackTrace();
//			setErrorMsg(e.getMessage());
//		}
//	}
}
