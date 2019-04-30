package kr.bacoder.coding.dev;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;

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
		//logger.info("IsValidToken? : " + token);
		
		String subject = "HACKER";
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(token).getBody();
			
//		    jws = Jwts.parser()         // (1)
//		    .setSigningKey(getSignatureKey())         // (2)
//		    .parseClaimsJws(token); // (3)
//		    subject = jws.getBody().getSubject();
		   String sub = claims.getSubject();
		   Date expirationDate = claims.getExpiration();
		   // String exp = jws.getBody().getExpiration().toString();
		    logger.info("authorized Token : "+ sub + "/" + expirationDate);
		   result = 1;
		   return result;
		    // we can safely trust the JWT
		   
		}
		catch (JwtException ex) {       // (4)
		    logger.info("Un-authorized : "+ ex.getMessage());
		    // we *cannot* use the JWT as intended by its creator
		    return 0;
		}

//		finally {
//			logger.info("finally");
//			//return false;
//			return result;
//
//		}

	}
}
