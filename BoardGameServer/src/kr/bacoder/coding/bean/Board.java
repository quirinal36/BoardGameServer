package kr.bacoder.coding.bean;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONArray;
import org.json.JSONException;

public class Board {
	
	private int id;
	private int creatorId;
	private String writerName;
	private String writerUserId;
	private String profileUrl;
	private String kImageUrl;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private int patientId;
	private int status;
	private String text;
	private int type;
	private Timestamp expireDate;
	private int replyCount;
	private int userType;
	private int userLevel;
	private int accessLevel;
	private String position;
	private int groupId;
	private int userId;
    private String photoList;
    private JSONArray photoListArray;
	private String token;
	private int photoId;
	private String caption;
	private int boardPatientId;
	
//	public Board() {}
//	
//	public Board(int id) {
//		this.id = id;
//	}
//	
//	public BoardBase(int totalCount, int pageNo) {
//		super(totalCount, pageNo);
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Timestamp getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Timestamp timestamp) {
		this.expireDate = timestamp;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getWriterUserId() {
		return writerUserId;
	}

	public void setWriterUserId(String writerUserId) {
		this.writerUserId = writerUserId;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getkImageUrl() {
		return kImageUrl;
	}

	public void setkImageUrl(String kImageUrl) {
		this.kImageUrl = kImageUrl;
	}
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getPhotoList() {
		return photoList;
	}

	public void setPhotoList(String photoList) {
		this.photoList = photoList;
	}
	

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getBoardPatientId() {
		return boardPatientId;
	}
	public void setBoardPatientId(int boardPatientId) {
		this.boardPatientId = boardPatientId;
	}
	
	public JSONArray getPhotoListArray() {
		if(this.photoList != null && this.photoList.length()>0) {
			JSONArray array = new JSONArray(this.photoList);
			return array;
		} else {
			return null;
		}
	}
	
	public void setPhotoListArray(JSONArray photoListArray) {
		this.photoListArray = photoListArray;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}
