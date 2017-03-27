package ShoppingCart.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ShoppingCart.dao.ShoppingCart;

@WebServlet("/BuyBookServlet")
public class BuyBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BuyBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("ShoppingCart", cart);
		}

	}

}
