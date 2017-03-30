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
import ShoppingCart.entity.OrderDetail;
import listphoto.entity.ListPhoto;

@WebServlet("/pages/BuyPhotoServlet")
public class BuyPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

		// 測試
		// ListPhoto baBean=(ListPhoto)session.getAttribute("listphoto");
		// System.out.println("baBean="+baBean);

		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("ShoppingCart", cart);
		}

		// 要顯示在購物清單頁面的東西
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String qtyStr = request.getParameter("qty");

		int id = 0;
		int qty = 0;
		double price = 0;

		System.out.println("----------BuyPhotoServlet-------");
		System.out.println("----------來自listPhoto的值-------");
		System.out.println("id=" + idStr);
		System.out.println("name=" + name);
		System.out.println("price=" + priceStr);
		System.out.println("qtyStr=" + qtyStr);
		System.out.println("----------------------------");
		try {
			id = Integer.parseInt(idStr.trim());
			price = Double.parseDouble(priceStr.trim());
			if(qtyStr!=null){
			qty = Integer.parseInt(qtyStr.trim());
			}
		} catch (NumberFormatException e) {
			throw new ServletException(e);
		}

		OrderDetail odi = new OrderDetail(id, name, qty, price);
		cart.addToCart(id, odi);
		RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath() + "/pages/ListPhotoServlet");
		rd.forward(request, response);

	}

}
