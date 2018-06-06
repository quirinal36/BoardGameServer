package kr.bacoder.coding.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.AndroidVersionInfo;

/**
 * Servlet implementation class AndroidCreateServlet
 */
@WebServlet("/androidAdd")
public class AndroidAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidAddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		
		AndroidVersionInfo info = AndroidVersionInfo.setParameters(request);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
