package kr.bacoder.coding.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AndroidVersionInfo {
	public static final String version_num = "version_num";
	public static final String year_key = "year";
	public static final String version_name = "version_name";
	public static final String version_name_kor = "version_name_kor";
	public static final String alphabet = "alphabet";
	
	private String alphaBet;
	private String versionNameEng;
	private String versionNameKor;
	private double version;
	private int year;
	
	public AndroidVersionInfo(){
		
	}
	
	
	public AndroidVersionInfo(String alphaBet, String versionNameEng, 
			String versionNameKor, double version, int year) {
		this.alphaBet = alphaBet;
		this.versionNameEng = versionNameEng;
		this.versionNameKor = versionNameKor;
		this.version = version;
		this.year = year;
	}
	
	public String getAlphaBet() {
		return alphaBet;
	}
	public void setAlphaBet(String alphaBet) {
		this.alphaBet = alphaBet;
	}
	public String getVersionNameEng() {
		return versionNameEng;
	}
	public void setVersionNameEng(String versionNameEng) {
		this.versionNameEng = versionNameEng;
	}
	public String getVersionNameKor() {
		return versionNameKor;
	}
	public void setVersionNameKor(String versionNameKor) {
		this.versionNameKor = versionNameKor;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public static AndroidVersionInfo makeObj(String alphabet, String verEng, String verKor, double ver, int year){
		return new AndroidVersionInfo(alphabet, verEng, verKor, ver, year);
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, 
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
