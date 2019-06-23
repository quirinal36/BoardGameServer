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
import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.bean.Token;

public class TokenUtil {
	Logger logger = Logger.getLogger(UploadUtil.class.getSimpleName());
	
	
	private static String signature = "qhdghkdtpqoralrudqhdcksdnqhdtjdus";

	public final static String expiredToken = "유효기간이 만료되었습니다";
	public final static String unauthorized = "인가되지 않은 접근입니다";
	
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
	
	public Person getInfoByToken(String tokenStr) {
		
		Person person = new Person();
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(tokenStr).getBody();
			
			Date expirationDate = claims.getExpiration();
//		   token.setSubject(claims.getSubject()); 
//		   token.setExpDate(claims.getExpiration());
		   person.setUniqueId(claims.getId());
		   person.setUserLevel((int) claims.get("role"));

		    logger.info("getInfoByToken: authorized Token");

		   return person;
		    // we can safely trust the JWT
		   
		}
		catch (JwtException ex) {       // (4)
		    logger.info("Un-authorized : "+ ex.getMessage());
		    // we *cannot* use the JWT as intended by its creator
		    return null;
		}
	}
	
public String getIdByToken(String tokenStr) {
		
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(tokenStr).getBody();

		   String uniqueId = claims.getId();
//		    logger.info("getInfoByToken: authorized Token");

		   return uniqueId;
		   
		}
		catch (JwtException ex) {       // (4)
		    logger.info("Un-authorized : "+ ex.getMessage());
		    // we *cannot* use the JWT as intended by its creator
		    return null;
		}
	}
}
