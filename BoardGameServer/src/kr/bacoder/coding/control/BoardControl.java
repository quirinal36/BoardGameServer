package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONArray;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Board;

public class BoardControl {

	public List<Board> getBoardList(int userId, int userLevel){
		DBconn db = new DBconn();
		List<Board> list = new ArrayList<>();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder()
					.append("SELECT board.id,"
							+ "board.creatorId,"
							+ "user.name as writerName,"
							+ "user.profileUrl as profileUrl,"
							+ "user.kImageUrl as kImageUrl, "
							+ "user.patientId as patientId,"
							+ "user.userType as userType,"
							+ "user.userLevel as userLevel,"
							+ "user.position as position,"
							+ "board.text,"
							+ "board.createdTime,"
							+ "board.updatedTime,"
							+ "board.patientId as boardPatientId,"
							+ "board.status,"
							+ "board.type,"
							+ "board.expireDate,"
							+ "board.accessLevel,"
							+ "board.groupId,"
							+ "(SELECT count(*) FROM Board_reply reply WHERE reply.boardId = board.id) AS replyCount,")
					.append(" ")
					.append("CASE ")
					.append("WHEN photo.id IS NULL THEN NULL ")
					.append("ELSE CONCAT('[',")
					.append("GROUP_CONCAT(CONCAT('{\\\\\"id\\\\\":',photo.id,',\\\\\"boardId\\\\\":',photo.boardId,',\\\\\"caption\\\\\":\\\\\"',REPLACE(photo.caption, '\"', '\\\\\"'),'\\\\\\\",\\\\\"photoId\\\\\":',photo.photoId,'}')),")
					.append("']') ")
					.append("END photoList ")
					.append(" ")
					.append("FROM Board_main board ")
					.append("LEFT JOIN Person AS user ON user.NUM = board.creatorId ")
					.append("LEFT JOIN Board_photo photo ON photo.boardId = board.id ")
					.append(" ")
					.append("WHERE ")
					.append("? >= board.accessLevel AND ")
					.append("(board.groupId IN (SELECT groupUser.groupId FROM Group_user groupUser WHERE groupUser.userId = ?) ")
					.append("OR ")
					.append("board.status = '3') ")
					.append("GROUP BY board.id "
							+ "ORDER BY board.createdTime DESC").toString();
			System.out.print(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userLevel);
			pstmt.setInt(2, userId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setCreatorId(rs.getInt("creatorId"));
				board.setWriterName(rs.getString("writerName"));
				board.setProfileUrl(rs.getString("profileUrl"));
				board.setkImageUrl(rs.getString("kImageUrl"));
				board.setPatientId(rs.getInt("patientId"));
				board.setUserType(rs.getInt("userType"));
				board.setUserLevel(rs.getInt("userLevel"));
				board.setPosition(rs.getString("position"));
				board.setText(rs.getString("text"));
				board.setCreatedTime(rs.getTimestamp("createdTime"));
				board.setUpdatedTime(rs.getTimestamp("updatedTime"));
				board.setBoardPatientId(rs.getInt("boardPatientId"));
				board.setStatus(rs.getInt("status"));
				board.setType(rs.getInt("type"));
				board.setExpireDate(rs.getTimestamp("expireDate"));
				board.setAccessLevel(rs.getInt("accessLevel"));
				board.setGroupId(rs.getInt("groupId"));
				board.setReplyCount(rs.getInt("replyCount"));
				board.setPhotoList(rs.getString("photoList"));

				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Board> getBoardListByGroupId(int userId, int userLevel, int groupId){
		DBconn db = new DBconn();
		List<Board> list = new ArrayList<>();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder()
					.append("SELECT board.id,"
							+ "board.creatorId,"
							+ "user.name as writerName,"
							+ "user.profileUrl as profileUrl,"
							+ "user.kImageUrl as kImageUrl, "
							+ "user.patientId as patientId,"
							+ "user.userType as userType,"
							+ "user.userLevel as userLevel,"
							+ "user.position as position,"
							+ "board.text,"
							+ "board.createdTime,"
							+ "board.updatedTime,"
							+ "board.patientId as boardPatientId,"
							+ "board.status,"
							+ "board.type,"
							+ "board.expireDate,"
							+ "board.accessLevel,"
							+ "board.groupId,"
							+ "(SELECT count(*) FROM Board_reply reply WHERE reply.boardId = board.id) AS replyCount,")
					.append(" ")
					.append("CASE ")
					.append("WHEN photo.id IS NULL THEN NULL ")
					.append("ELSE CONCAT('[',")
					.append("GROUP_CONCAT(CONCAT('{\\\\\"id\\\\\":',photo.id,',\\\\\"boardId\\\\\":',photo.boardId,',\\\\\"caption\\\\\":\\\\\"',REPLACE(photo.caption, '\"', '\\\\\"'),'\\\\\\\",\\\\\"photoId\\\\\":',photo.photoId,'}')),")
					.append("']') ")
					.append("END photoList ")
					.append(" ")
					.append("FROM Board_main board ")
					.append("LEFT JOIN Person AS user ON user.NUM = board.creatorId ")
					.append("LEFT JOIN Board_photo photo ON photo.boardId = board.id ")
					.append(" ")
					.append("WHERE ")
					.append("? >= board.accessLevel AND ")
					.append("(board.groupId = ? ")
					.append("OR ")
					.append("board.groupId = (SELECT groupList.parentGroupId FROM Group_list groupList WHERE groupList.id = ?) ")
					.append("OR ")
					.append("board.status = '3') ")
					.append("GROUP BY board.id "
							+ "ORDER BY board.createdTime DESC").toString();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userLevel);
			pstmt.setInt(2, groupId);
			pstmt.setInt(3, groupId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setCreatorId(rs.getInt("creatorId"));
				board.setWriterName(rs.getString("writerName"));
				board.setProfileUrl(rs.getString("profileUrl"));
				board.setkImageUrl(rs.getString("kImageUrl"));
				board.setPatientId(rs.getInt("patientId"));
				board.setUserType(rs.getInt("userType"));
				board.setUserLevel(rs.getInt("userLevel"));
				board.setPosition(rs.getString("position"));
				board.setText(rs.getString("text"));
				board.setCreatedTime(rs.getTimestamp("createdTime"));
				board.setUpdatedTime(rs.getTimestamp("updatedTime"));
				board.setBoardPatientId(rs.getInt("boardPatientId"));
				board.setStatus(rs.getInt("status"));
				board.setType(rs.getInt("type"));
				board.setExpireDate(rs.getTimestamp("expireDate"));
				board.setAccessLevel(rs.getInt("accessLevel"));
				board.setGroupId(rs.getInt("groupId"));
				board.setReplyCount(rs.getInt("replyCount"));
				board.setPhotoList(rs.getString("photoList"));

				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public JSONArray getBoardJsonArray (List<Board> list) {
		JSONArray array = new JSONArray();
		Iterator<Board> iter = list.iterator();
		while(iter.hasNext()) {
			array.add(iter.next().toString());
		}
		return array;
	}
	public int insertBoard(Board board) {
		int result = 0;
		DBconn db = new DBconn();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder().append("INSERT INTO Board_main (creatorId, patientId, status, text, type, accessLevel, groupId) VALUE (?,?,?,?,?,?,?) ").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCreatorId());
			pstmt.setInt(2, board.getPatientId());
			pstmt.setInt(3, board.getStatus());
			pstmt.setString(4, board.getText());
			pstmt.setInt(5, board.getType());
			pstmt.setInt(6, board.getAccessLevel());
			pstmt.setInt(7, board.getGroupId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateBoard(Board board) {
		int result = 0;
		DBconn db = new DBconn();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder().append("UPDATE Board_main SET creatorId=?, patientId=?, status=?, text=?, type=?, accessLevel=?, groupId=? WHERE id=?").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCreatorId());
			pstmt.setInt(2, board.getPatientId());
			pstmt.setInt(3, board.getStatus());
			pstmt.setString(4, board.getText());
			pstmt.setInt(5, board.getType());
			pstmt.setInt(6, board.getAccessLevel());
			pstmt.setInt(7, board.getGroupId());
			pstmt.setInt(8, board.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}