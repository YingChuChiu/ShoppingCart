package ShoppingCart.servlet;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import ShoppingCart.dao.ShoppingCartDAO;
import ShoppingCart.entity.OrderItem;

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
		ShoppingCartDAO cart = (ShoppingCartDAO) session.getAttribute("ShoppingCart");

		// 測試
		// ListPhoto baBean=(ListPhoto)session.getAttribute("listphoto");
		// System.out.println("baBean="+baBean);

		if (cart == null) {
			cart = new ShoppingCartDAO();
			session.setAttribute("ShoppingCart", cart);
		}

		// 要顯示在購物清單頁面的東西
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String order_idStr = request.getParameter("order_id");
		String qtyStr = request.getParameter("qty");
		String noteStr = request.getParameter("note");

		Long id = 0L;
		Long order_id = 0L;
		int qty = 0;
		Double price = (double) 0;
		Clob note = null;

		// System.out.println("----------BuyPhotoServlet-------");
		// System.out.println("----------來自listPhoto的值-------");
		// System.out.println("id=" + idStr);
		// System.out.println("price=" + priceStr);
		// System.out.println("qtyStr=" + qtyStr);
		// System.out.println("----------------------------");

		try {
			id = Long.parseLong(idStr.trim());
			if (order_idStr != null) {
				order_id = Long.parseLong(order_idStr.trim());
			}

			if (qtyStr != null) {
				qty = Integer.parseInt(qtyStr.trim());
			}

			if (noteStr != null) {
				note = new SerialClob(noteStr.toCharArray());
			}
			if (priceStr != null) {
				price = Double.parseDouble(priceStr.trim());
			}

		} catch (NumberFormatException e) {
			throw new ServletException(e);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		OrderItem odi = new OrderItem(id, order_id, name, note, qty, price);
		cart.addToCart(id, odi);
		RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath() + "/pages/ListPhotoServlet");
		rd.forward(request, response);
	}
}
