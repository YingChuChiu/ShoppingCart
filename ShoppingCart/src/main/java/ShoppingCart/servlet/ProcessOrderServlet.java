package ShoppingCart.servlet;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialClob;

import ShoppingCart.dao.OrdersDAO;
import ShoppingCart.dao.ShoppingCartDAO;
import ShoppingCart.entity.OrderDetail;
import ShoppingCart.entity.OrderItem;
import ShoppingCart.entity.Orders;

// OrderConfirm.jsp 呼叫本程式
@WebServlet("/pages/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String finalDecision = request.getParameter("finalDecision");
			HttpSession session = request.getSession(false);
			if (session == null) { // 使用逾時
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
			}

			ShoppingCartDAO sc = (ShoppingCartDAO) session.getAttribute("ShoppingCart");
			if (sc == null) {
				// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
				// 導向首頁
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
			}

			if (finalDecision.equals("CANCEL")) {
				session.removeAttribute("ShoppingCart");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
				return; // 一定要記得 return
			}
			String idStr = request.getParameter("id");
			// String updateStr = request.getParameter("update");
			String memIdStr = request.getParameter("memId");
			String dscStr = request.getParameter("dsc");
			Long memId = 1L;
			Clob dsc = null;
			// System.out.println(updateStr);
			// if (idStr != null) {
			// Long id = Long.parseLong(idStr.trim()); // 字串轉整數
			// }

			// DateTimeFormatter dtf =
			// DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// LocalDateTime update = LocalDateTime.parse(updateStr, dtf);
			LocalDateTime update = LocalDateTime.now();
			// Long memId = Long.parseLong(memIdStr.trim());
			if (dscStr != null) {
				dsc = new SerialClob(dscStr.toCharArray());
			}
			Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
			Map<Integer, OrderItem> cart = sc.getContent();
			Set<Integer> set = cart.keySet();
			for (Integer k : set) {
				OrderItem oib = cart.get(k);
				OrderDetail oiDAO = new OrderDetail(oib.getId(), oib.getOrder_id(), oib.getPrice(), oib.getNote());
				orderDetails.add(oiDAO);
			}
			// OrderBean:封裝一筆訂單資料的容器(包含訂單主檔與訂單明細檔的資料)
			Orders ob = new Orders( update, dsc, orderDetails);
			OrdersDAO order = new OrdersDAO();
			order.insertOrder(ob);
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(getServletContext().getContextPath() + "/pages/ThanksForOrdering.jsp");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}