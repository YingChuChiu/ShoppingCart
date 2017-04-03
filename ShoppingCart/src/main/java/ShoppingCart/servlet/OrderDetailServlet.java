package ShoppingCart.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ShoppingCart.dao.OrdersDAO;
import ShoppingCart.entity.OrderDetail;
import ShoppingCart.entity.Orders;

@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("XXXX");
		String orderid = request.getParameter("id");
		Long no  = Long.parseLong(orderid.trim());   // ###
		try {
			OrdersDAO ordDAO = new OrdersDAO();
			Orders ob = ordDAO.getOrder(no);
			request.setAttribute("OrderBean", ob);
			RequestDispatcher  rd = request.getRequestDispatcher("pages/ShowOrderDetail.jsp");
			rd.forward(request, response);
			return;
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	public void displayOrderBean(Orders ob) {
		System.out.println("ob.getId()=" + ob.getId());
		System.out.println("ob.getUpdate()=" + ob.getUpdate());
		System.out.println("ob.getDsc()=" + ob.getDsc());
		System.out.println("ob.getMemId=" + ob.getMemId());

		System.out.println("==============訂單明細=================");
		Set<OrderDetail> items = ob.getOrderDetails();
		for (OrderDetail oib : items) {
			System.out.println("---------------一筆明細---------------");
			System.out.println("   oib.getId()=" + oib.getId());
			System.out.println("   oib.getOrder_id()=" + oib.getOrder_id());
			System.out.println("   oib.description()=" + oib.getPicId());
			System.out.println("   oib.description()=" + oib.getDescription());
			System.out.println("   oib.getBookId()=" + oib.getPrice());
			System.out.println("   oib.getNote()="+ oib.getNote());

		}
	}
}