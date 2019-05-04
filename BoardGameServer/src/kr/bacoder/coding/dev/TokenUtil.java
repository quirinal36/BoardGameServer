package kr.bacoder.coding.dev;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import kr.bacoder.coding.bean.Token;

public class TokenUtil {
	Logger logger = Logger.getLogger(UploadUtil.class.getSimpleName());
	
	private static String signature = "qhdghkdtpqoralrudqhdcksdnqhdtjdus";

	public byte[] getSignatureKey() {
		return this.signature.getBytes();
	}
	
	public String getToken(String subject, String userId, int role, int expMins) {
				
		Date expirationDate = new Date();
		LocalDateTime dateTime = LocalDateTime.now();
//		dateTime = dateTime.plusDays(expDays);
		dateTime = dateTime.plusMinutes(expMins);

		expirationDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		//logger.info(expirationDate.toString());
		String jwt = 
		    Jwts.builder()
		    .setIssuer("http://hsbong.synology.me")
			  .setSubject("" + subject)
			  .setExpiration(expirationDate)
			  .setId(userId)
			  .claim("role", role)
			  .signWith(
			    SignatureAlgorithm.HS256,
			    getSignatureKey()
			  )
			  .compact();
		logger.info(jwt);
		return jwt;
	}
	
	
	public int IsValidToken(String token) {
		//int result = 0;
		int role = 0;
		//logger.info("IsValidToken? : " + token);
		
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(token).getBody();
			
		   role = (int) claims.get("role");
		   String subject = claims.getSubject();
		   String userId = claims.getId();
		   Date expirationDate = claims.getExpiration();

		   logger.info("authorized Token : "+ claims.getSubject() + "/" + expirationDate);
		  
		   return role;
		    // we can safely trust the JWT   
		}
		catch (ExpiredJwtException e) {
			logger.info("Expired : "+ e.getMessage());
		    return -1;
		}
		catch (JwtException ex) {       // (4)
		    logger.info("Un-authorized : "+ ex.getMessage());
		    // we *cannot* use the JWT as intended by its creator
		    return 0;
		}
	}
	
	public Token getInfoByToken(String tokenStr) {
		
		Token token = new Token();
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(tokenStr).getBody();
			
		   token.setSubject(claims.getSubject()); 
		   token.setExpDate(claims.getExpiration());
		   token.setUserId(claims.getId());
		   token.setRole((int) claims.get("role"));

		    logger.info("getInfoByToken: authorized Token");

		   return token;
		    // we can safely trust the JWT
		   
		}
		catch (JwtException ex) {       // (4)
		    logger.info("Un-authorized : "+ ex.getMessage());
		    // we *cannot* use the JWT as intended by its creator
		    return null;
		}
	}
}
