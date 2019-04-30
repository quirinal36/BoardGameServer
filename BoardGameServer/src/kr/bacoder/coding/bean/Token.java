package kr.bacoder.coding.bean;

public class Token {

	private String tokenType;
	private String tokenStr;
	private String name;
	private String subject;
	private int scope;
	private int expDays;
	private String userId;
	private String userPwd;
	
	
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getTokenStr() {
		return tokenStr;
	}
	public void setTokenStr(String tokenStr) {
		this.tokenStr = tokenStr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}
	public int getExpDays() {
		return expDays;
	}
	public void setExpDays(int expDays) {
		this.expDays = expDays;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
}
