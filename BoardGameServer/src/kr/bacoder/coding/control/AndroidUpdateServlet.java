package kr.bacoder.coding.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.AndroidVersionInfo;

/**
 * Servlet implementation class AndroidUpdateServlet
 */
@WebServlet("/androidUpdate")
public class AndroidUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		DBconn db = new DBconn();
		Connection conn = null;
		AndroidVersionInfo info = AndroidVersionInfo.setParameters(request);
		try {
			conn = db.getConnection();
			PreparedStatement stmt = conn.prepareStatement(makeUpdateQuery());
			stmt.setDouble(1, info.getVersion());
			stmt.setInt(2, info.getYear());
			stmt.setString(3, info.getVersionNameKor());
			stmt.setString(4, info.getVersionNameEng());
			stmt.setString(5, info.getAlphaBet());
			stmt.setString(6, info.getAlphaBet());
			
			int result = stmt.executeUpdate();
			JSONObject object = new JSONObject();
			object.put("result", result);
			response.getWriter().append(object.toJSONString());
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
	}
	
	private String makeUpdateQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE Android_Version SET ");
		sb.append("		version_num = ?");
		sb.append(",	year = ?");
		sb.append(",	version_name = ?");
		sb.append(",	version_name_kor = ?");
		sb.append(",	alphabet = ?");
		sb.append(" WHERE alphabet = ?");
		return sb.toString();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
