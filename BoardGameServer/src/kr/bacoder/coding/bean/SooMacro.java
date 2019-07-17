package kr.bacoder.coding.bean;

import java.util.Date;

import org.json.simple.JSONObject;

public class SooMacro {
	private int id;
	private int verNumber;
	private String fileName;
	private Date releaseDate;
	private String info;
	private String notice;
	private int downloadCount;
	private String downloader;
	private String ipAddress;
	private Date downloadDate;
	private int downloadVer;
	private int previousVer;
	private String userName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVerNumber() {
		return verNumber;
	}
	public void setVerNumber(int verNumber) {
		this.verNumber = verNumber;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getDownloader() {
		return downloader;
	}
	public void setDownloader(String downloader) {
		this.downloader = downloader;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getDownloadDate() {
		return downloadDate;
	}
	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}
	public int getDownloadVer() {
		return downloadVer;
	}
	public void setDownloadVer(int downloadVer) {
		this.downloadVer = downloadVer;
	}
	public int getPreviousVer() {
		return previousVer;
	}
	public void setPreviousVer(int previousVer) {
		this.previousVer = previousVer;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public static JSONObject parseJSON(SooMacro info) {
		JSONObject result = new JSONObject();
		result.put("id", info.getId());
		result.put("verNumber", info.getVerNumber());
		result.put("fileName", info.getFileName());
		result.put("releaseDate", info.getReleaseDate());
		result.put("info", info.getInfo());
		result.put("notice", info.getNotice());
		result.put("downloadCount", info.getDownloadCount());
		result.put("downloader", info.getDownloader());
		result.put("ipAddress", info.getIpAddress());
		result.put("downloadDate", info.getDownloadDate());
		result.put("downloadVer", info.getDownloadVer());
		result.put("previousVer", info.getPreviousVer());
		return result;
	}
	
}
