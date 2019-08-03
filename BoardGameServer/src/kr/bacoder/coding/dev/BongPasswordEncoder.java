package kr.bacoder.coding.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BongPasswordEncoder implements PasswordEncoder {
	private PasswordEncoder passwordEncoder; 
	public BongPasswordEncoder() { 
		this.passwordEncoder = new BCryptPasswordEncoder(11); 
	}

	@Override
	public String encode(CharSequence input) {
		return passwordEncoder.encode(input);
	}

	@Override
	public boolean matches(CharSequence input, String db) {
		return passwordEncoder.matches(input, db);
	}

}
