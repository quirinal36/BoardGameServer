package kr.bacoder.coding.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.simple.JSONObject;

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
	public void setAlphaBet(String alphabet) {
		this.alphaBet = alphabet;
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
	
	public static JSONObject getAndroidVer(ResultSet rs) throws SQLException{
		JSONObject item = new JSONObject();
		item.put(AndroidVersionInfo.alphabet, rs.getString(AndroidVersionInfo.alphabet));
		item.put(AndroidVersionInfo.version_name, rs.getString(AndroidVersionInfo.version_name));
		item.put(AndroidVersionInfo.version_name_kor, rs.getString(AndroidVersionInfo.version_name_kor));
		item.put(AndroidVersionInfo.version_num, rs.getDouble(AndroidVersionInfo.version_num));
		item.put(AndroidVersionInfo.year_key, rs.getInt(AndroidVersionInfo.year_key));
		return item;
	}
	public static AndroidVersionInfo setParameters(HttpServletRequest req) {
		AndroidVersionInfo result = new AndroidVersionInfo();
		
		String versionNumStr 		= req.getParameter(version_num);
		String yearStr 				= req.getParameter(year_key);
		String versionName	 		= req.getParameter(version_name);
		String versionNameKor		= req.getParameter(version_name_kor);
		String alphabetParam		= req.getParameter(alphabet);
		if(versionNumStr!=null) 	result.setVersion(Double.parseDouble(versionNumStr));
		if(yearStr != null) 		result.setYear(Integer.parseInt(yearStr));
		if(versionName != null) 	result.setVersionNameEng(versionName);
		if(versionNameKor!=null)	result.setVersionNameKor(versionNameKor);
		if(alphabetParam != null)	result.setAlphaBet(alphabetParam);
		
		return result;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, 
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
