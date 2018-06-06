package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.AndroidVersionInfo;

public class ControlAndroid {

	public int addAndroidInfo(AndroidVersionInfo info) {
		int result = 0;
		DBconn db = new DBconn();
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement stmt = conn.prepareStatement(makeAddQuery());
			stmt.setDouble(1, info.getVersion());
			stmt.setInt(2, info.getYear());
			stmt.setString(3, info.getVersionNameKor());
			stmt.setString(4, info.getVersionNameEng());
			stmt.setString(5, info.getAlphaBet());
			result = stmt.executeUpdate();
//			JSONObject object = new JSONObject();
//			object.put("result", result);
//			response.getWriter().append(object.toJSONString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	private String makeAddQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO Android_Version  ");
		sb.append("(version_num");
		sb.append(",year");
		sb.append(",version_name");
		sb.append(",version_name_kor");
		sb.append(",alphabet)");
		sb.append("	VALUES(?,?,?,?,?)");
		
		return sb.toString();
	}
	public String getAndroidInfo(AndroidVersionInfo info) {
		JSONObject resultObj = new JSONObject();
		
		DBconn db = new DBconn();
		Connection conn = null;
		try {
			conn = db.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM Android_Version");
			if(info.getAlphaBet() != null && info.getAlphaBet().length()>0) {
				sb.append("	WHERE alphabet = ?");
			}
			PreparedStatement stmt = conn.prepareStatement(sb.toString());
			if(info.getAlphaBet() != null && info.getAlphaBet().length()>0) {
				stmt.setString(1, info.getAlphaBet());
			}
			ResultSet rs;
			rs = stmt.executeQuery();
			
			
			JSONArray array = new JSONArray();
			while(rs.next()) {
				array.add(AndroidVersionInfo.getAndroidVer(rs));
			}
			resultObj.put("list", array);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return resultObj.toJSONString();
	}
}
