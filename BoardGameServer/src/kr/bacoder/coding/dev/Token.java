package kr.bacoder.coding.dev;

import java.util.logging.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class Token {
	Logger logger = Logger.getLogger(UploadUtil.class.getSimpleName());
	private static String signature = "privateKey";
	
	public byte[] getSignatureKey() {
	
		return this.signature.getBytes();
	}
	
	public int IsValidToken(String token) {
		int result = 0;
		logger.info("IsValidToken? : " + token);
		Jws<Claims> jws;
		
		String subject = "HACKER";
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(token).getBody();
			
//		    jws = Jwts.parser()         // (1)
//		    .setSigningKey(getSignatureKey())         // (2)
//		    .parseClaimsJws(token); // (3)
//		    subject = jws.getBody().getSubject();
		   // String sub = jws.getBody().getSubject();
		   // String exp = jws.getBody().getExpiration().toString();
		    logger.info("authorized Token : "+ claims.getSubject());
		   result = 1;
		   
		    // we can safely trust the JWT
		   
		}
		catch (SignatureException e) {
			logger.info("SignatureException : "+ e.toString());
		    // we *cannot* use the JWT as intended by its creator
			result = 0;
		}
		catch (ExpiredJwtException e) {
			logger.info("ExpiredJwtException : "+ e.toString());
		    // we *cannot* use the JWT as intended by its creator
			result = 0;

		}
		catch (MalformedJwtException e) {
			logger.info("ExpiredJwtException : "+ e.toString());
		    // we *cannot* use the JWT as intended by its creator
			result = 0;

		}
		catch (UnsupportedJwtException e) {
			logger.info("ExpiredJwtException : "+ e.toString());
		    // we *cannot* use the JWT as intended by its creator
			result = 0;

		}
		finally {
			logger.info("finally");
			//return false;
			return result;

		}

	}
}
