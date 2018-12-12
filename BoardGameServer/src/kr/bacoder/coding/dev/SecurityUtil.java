package kr.bacoder.coding.dev;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	public static final String ENCODING = "SHA-256";
	
	public String encryptSHA256(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance(ENCODING);
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff)+0x100, 16).substring(1));
			}
			sha = sb.toString();
		}catch(NoSuchAlgorithmException e){
			System.out.println("Encrypt Error - NoSuchAlgorithmException");
			sha = null;
		}
		return sha;
	}
}
