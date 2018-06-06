package kr.bacoder.coding.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PicnicServlet
 */
@WebServlet("/picnic")
public class PicnicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicnicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ");
		String flag = request.getParameter("flag");
		if(flag != null) {
			int toWhere = Integer.parseInt(flag);
			switch(toWhere) {
			case 0:
				// read
				break;
			case 1:
				// update
				break;
			case 2:
				// append
				break;
			case 3:
				// delete
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("post: ").append(request.getContextPath());
		doGet(request, response);
	}

}
