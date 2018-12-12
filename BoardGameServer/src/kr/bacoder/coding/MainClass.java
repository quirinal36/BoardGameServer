package kr.bacoder.coding;

import kr.bacoder.coding.dev.SecurityUtil;

public class MainClass {

	public static void main(String[] args) {
		SecurityUtil securityUtil = new SecurityUtil();
		String gagul = securityUtil.encryptSHA256("789gagul");
		String mypwd = securityUtil.encryptSHA256("789gagul");
		System.out.println(gagul);
		System.out.println(mypwd);
		if(gagul.equals(mypwd)) {
			System.out.println("same~!!");
		}
	}

}
