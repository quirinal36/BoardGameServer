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
	
	private static final int AccessTokenEXPDays = 7;
	private static final int RefreshTokenEXPDays = 7;


	public boolean userCheck(String userId, String userPwd) throws SQLException {
		
		SecurityUtil security = new SecurityUtil();
		String ePwd = security.encryptSHA256(userPwd);
		
		try(Connection conn =  getConnection()){
			String sql = "SELECT person.password FROM Person person WHERE person.uniqueId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			logger.info(pstmt.toString());
			
			//int result = 0;

			ResultSet rs = pstmt.executeQuery();
			if (userPwd == rs.getString("password")) {
				logger.info("pwd check success");
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
	
	public String getAccessToken(Token token) throws SQLException {
		
		if(userCheck(token.getUserId(), token.getUserPwd())) {
			TokenUtil util = new TokenUtil();
			return util.getToken(token.getSubject(), token.getName(), token.getScope(), AccessTokenEXPDays);
		} else {
			return null;
		}	
	}
	
	
}
