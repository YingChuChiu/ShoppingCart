package ShoppingCart.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ShoppingCart.dao.ShoppingCart;

// 本類別可修改購物車內的商品資料，包括刪除某項商品
@WebServlet("/pages/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		if (session == null) { // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		// 取出session物件內的ShoppingCart物件
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		
		System.out.println("-----DeleteCartServlet的資訊-------");
		System.out.println("sc"+sc);
		System.out.println("------------------------");
		if (sc == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		String cmd = request.getParameter("cmd");
		String idStr = request.getParameter("photoID");
		int photoID = Integer.parseInt(idStr.trim());
		if (cmd.equalsIgnoreCase("DEL")) {
			
			sc.deletephoto(photoID); // 刪除購物車內的某項商品
			RequestDispatcher rd = request.getRequestDispatcher("ShowCartContent.jsp");
			rd.forward(request, response);
			return;
		}

	}
}