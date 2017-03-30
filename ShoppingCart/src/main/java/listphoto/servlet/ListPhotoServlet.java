package listphoto.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import listphoto.entity.ListPhoto;
import listphoto.server.ListphotoService;

@WebServlet("/pages/ListPhotoServlet")
public class ListPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		ListphotoService listphotoservice = new ListphotoService();
		Collection<ListPhoto> listphoto = listphotoservice.findAll();
		request.setAttribute("listphoto", listphoto);
		RequestDispatcher rd = request.getRequestDispatcher("listphoto.jsp");
		rd.forward(request, response);
		
		return;
	}

}
