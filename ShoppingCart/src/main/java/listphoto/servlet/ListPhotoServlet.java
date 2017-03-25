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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		ListphotoService listphotoservice = new ListphotoService();
		Collection<ListPhoto> listphoto = listphotoservice.findAll();
		request.setAttribute("listphoto", listphoto);
		RequestDispatcher rd = request.getRequestDispatcher("listphoto.jsp");
		rd.forward(request, response);
		InputStream in = null;
		ServletOutputStream oStream = null;
		
		try {
			response.reset();
			response.setContentType("image/jpeg"); 
			request.setCharacterEncoding("gbk");

			oStream = response.getOutputStream();
			Blob blob = null;
			in = blob.getBinaryStream();
			int blobsize = (int) blob.length();
			byte[] blobbytes = new byte[blobsize];
			int bytesRead = 0;
			while ((bytesRead = in.read(blobbytes)) != -1) {
				oStream.write(blobbytes, 0, bytesRead);
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
		} finally {
			if (in != null) {
				in.close();
			}
			if (oStream != null) {
				oStream.close();
			}
		}
		
		return;
	}

}
