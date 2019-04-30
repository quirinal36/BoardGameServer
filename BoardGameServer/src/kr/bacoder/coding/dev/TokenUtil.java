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

public class TokenUtil {
	Logger logger = Logger.getLogger(UploadUtil.class.getSimpleName());
	
	private static String signature = "qhdghkdtpqoralrudqhdcksdnqhdtjdus";

	public byte[] getSignatureKey() {
		return this.signature.getBytes();
	}
	
	public String getToken(String subject, String name, int scope, int expDays) {
				
		Date expirationDate = new Date();
		LocalDateTime dateTime = LocalDateTime.now();
		dateTime = dateTime.plusDays(expDays);
		expirationDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		//logger.info(expirationDate.toString());
		String jwt = 
		    Jwts.builder()
		    .setIssuer("http://hsbong.synology.me")
			  .setSubject("users/" + subject)
			  .setExpiration(expirationDate)
			  .claim("name", name)
			  .claim("scope", scope)
			  .signWith(
			    SignatureAlgorithm.HS256,
			    getSignatureKey()
			  )
			  .compact();
		logger.info(jwt);
		return jwt;
	}
	
	
	public int IsValidToken(String token) {
		int result = 0;
		//logger.info("IsValidToken? : " + token);
		
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(getSignatureKey())
				       .parseClaimsJws(token).getBody();
			
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
	}
	
}
