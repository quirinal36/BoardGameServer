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
import kr.bacoder.coding.bean.Comment;
import kr.bacoder.coding.bean.Patient;
import kr.bacoder.coding.bean.Photo;

public class CommentControl {

	

	public List<Comment> getCommentList(int userId, int userLevel, int boardId){ //userInfo
		DBconn db = new DBconn();
		List<Comment> list = new ArrayList<>();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder()
					.append("SELECT comment.id,"
							+ "comment.writerId,"
							+ "user.name as writerName,"
							+ "user.profileUrl as profileUrl,"
							+ "user.userLevel as userLevel,"
							+ "user.position as position,"
							+ "comment.photoId,"
							+ "comment.text,"
							+ "comment.createdTime,"
							+ "comment.updatedTime,"
							+ "comment.boardOwnerId,"
							+ "comment.secret,"
							+ "comment.status")
							
					.append(" ")
					.append("FROM Board_reply comment ")
					.append("LEFT JOIN Person AS user ON user.NUM = comment.writerId ") // comment 를 쓴 유저 정보 불러오기
					.append(" ")
					.append("WHERE ")
					.append("comment.boardId = ? AND ")  // comment.boardId 가 param(boardId) 인 row 선택  // OR
					.append("(comment.boardOwnerId = ? ") // comment 쓴 사람이 boardWiter = userId(param)
					.append("OR ")
					.append("comment.secret = '0') ")
					.append("GROUP BY comment.id ").toString();
			System.out.print(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			pstmt.setInt(2, userId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setWriterId(rs.getInt("writerId"));
				comment.setWriterName(rs.getString("writerName"));
				comment.setProfileUrl(rs.getString("profileUrl"));
				comment.setPosition(rs.getString("position"));
				comment.setUserLevel(rs.getInt("userLevel"));
				comment.setPhotoId(rs.getInt("photoId"));
				comment.setText(rs.getString("text"));
				comment.setCreatedTime(rs.getTimestamp("createdTime"));
				comment.setUpdatedTime(rs.getTimestamp("updatedTime"));
				comment.setBoardOwnerId(rs.getInt("boardOwnerId"));
				comment.setStatus(rs.getInt("Status"));
				comment.setSecret(rs.getInt("Secret"));

				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertComment(Comment comment) {
		int result = 0;
		DBconn db = new DBconn();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder().append("INSERT INTO Board_reply (boardId, text, status, writerId, boardOwnerId, secret) VALUE (?,?,?,?,?,?)")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, comment.getBoardId());
			pstmt.setString(2, comment.getText());
			pstmt.setInt(3, comment.getStatus());
			pstmt.setInt(4, comment.getWriterId());
			pstmt.setInt(5, comment.getBoardOwnerId());
			pstmt.setInt(6, comment.getSecret());

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
	public int deleteCommentById(Comment comment) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM Board_reply WHERE id = ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, comment.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray getCommentJsonArray (List<Comment> list) {
		JSONArray array = new JSONArray();
		Iterator<Comment> iter = list.iterator();
		while(iter.hasNext()) {
			array.add(iter.next().toString());
		}
		return array;
	}
	
}