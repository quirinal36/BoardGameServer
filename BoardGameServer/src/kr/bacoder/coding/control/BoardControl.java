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

import com.mysql.jdbc.Statement;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Board;
import kr.bacoder.coding.bean.Patient;
import kr.bacoder.coding.bean.Photo;

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
							+ "user.photoId as profilePhotoId,"
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
							+ "board.youtubeLink,"
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
				board.setProfilePhotoId(rs.getInt("profilePhotoId"));
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
				board.setYoutubeLink(rs.getString("youtubeLink"));

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
							+ "user.photoId as profilePhotoId,"
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
							+ "board.youtubeLink,"
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
				board.setProfilePhotoId(rs.getInt("profilePhotoId"));
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
				board.setYoutubeLink(rs.getString("youtubeLink"));

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
			final String sql = new StringBuilder().append("INSERT INTO Board_main (creatorId, patientId, status, text, type, accessLevel, groupId, youtubeLink) VALUE (?,?,?,?,?,?,?,?)")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, board.getCreatorId());
			pstmt.setInt(2, board.getPatientId());
			pstmt.setInt(3, board.getStatus());
			pstmt.setString(4, board.getText());
			pstmt.setInt(5, board.getType());
			pstmt.setInt(6, board.getAccessLevel());
			pstmt.setInt(7, board.getGroupId());
			pstmt.setString(8, board.getYoutubeLink());
			result = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
		        result = rs.getInt(1);
		    } else {
		    	result = -2;
		        // throw an exception from here
		    }
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateBoard(Board board) {
		int result = 0;
		DBconn db = new DBconn();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder().append("UPDATE Board_main SET creatorId=?, patientId=?, status=?, text=?, type=?, accessLevel=?, groupId=? , youtubeLink=? WHERE id=?").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCreatorId());
			pstmt.setInt(2, board.getPatientId());
			pstmt.setInt(3, board.getStatus());
			pstmt.setString(4, board.getText());
			pstmt.setInt(5, board.getType());
			pstmt.setInt(6, board.getAccessLevel());
			pstmt.setInt(7, board.getGroupId());
			pstmt.setString(8, board.getYoutubeLink());
			pstmt.setInt(9, board.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertBoardPhoto(int boardId, int photoId, String caption) {
		int result = 0;
		DBconn db = new DBconn();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder().append("INSERT INTO Board_photo (boardId, photoId, caption) VALUE (?,?,?) ").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			pstmt.setInt(2, photoId);
			pstmt.setString(3, caption);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int deleteBoardById(Board board) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM Board_main WHERE id = ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, board.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}