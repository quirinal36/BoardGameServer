package kr.bacoder.coding.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BongPasswordEncoder implements PasswordEncoder {
	private PasswordEncoder passwordEncoder; 
	public BongPasswordEncoder() { 
		this.passwordEncoder = new BCryptPasswordEncoder(11); 
	}

	@Override
	public String encode(CharSequence arg0) {
		return passwordEncoder.encode(arg0);
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return passwordEncoder.matches(arg0, arg1);
	}

}
