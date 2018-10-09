package kr.bacoder.coding.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet("/person")
public class PersonServlet extends HttpServlet {
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	private ServletFileUpload uploader = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("Content type is not multipart/form-data");
		}
		logger.info("doPost");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		
		try {
			ServletFileUpload fileUpload = new ServletFileUpload();
			InputStream is;
			FileItemIterator items = fileUpload.getItemIterator(request);
		    // iterate items
		    while (items.hasNext()) {
		        FileItemStream item = items.next();
		        if (!item.isFormField()) {
		            is = item.openStream();
		        }
		    }
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		out.write("</body></html>");
	}

}
