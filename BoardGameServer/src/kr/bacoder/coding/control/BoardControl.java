package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Board;

public class BoardControl {

	public List<Board> getBoards(int from, int to){
		DBconn db = new DBconn();
		List<Board> list = new ArrayList<>();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder().append("SELECT * FROM Board WHERE state = 1 LIMIT ?, ? ").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setDate(rs.getString("date"));
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
			final String sql = new StringBuilder().append("INSERT INTO Board (title, writer, content, date) VALUE (?,?,?,?) ").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getDate());
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
			final String sql = new StringBuilder().append("UPDATE Board SET title=?, writer=?, content=?, date=? WHERE id=?").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getDate());
			pstmt.setInt(5, board.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}