package ShoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ShoppingCart.entity.OrderDetail;
import ShoppingCart.entity.Orders;

//   本類別
//   1.新增一筆訂單到orders表格
//   2.查詢orders表格內的單筆訂單
//   3.查詢orders表格內的所有訂單

public class OrdersDAO {
	private DataSource ds = null;

	public OrdersDAO() throws NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Iwowwow");
	}

	public void insertOrder(Orders od) throws SQLException {
		String sqlOrder = "Insert Into orders " + " (D_UPDATE, dsc) " + " values(  ?, ?) ";
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet generatedKeys = null;
		PreparedStatement pStmt2 = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false); // 開啟JDBC Transaction
			pStmt = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
			pStmt.setObject(1, od.getUpdate());
			pStmt.setClob(2, od.getDsc());

			pStmt.executeUpdate();
			int id = 0;
			System.out.println(pStmt);
			generatedKeys = pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {

				id = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Creating user failed, no generated key obtained.");
			}
			String sqlItem = "Insert Into Order_details (order_id,pic_id,description,price, note) "
					+ " values( ?, ?, ?, ?,?) ";
			Set<OrderDetail> items = od.getOrderDetails();
			pStmt2 = conn.prepareStatement(sqlItem);
			for (OrderDetail oib : items) {
				pStmt2.setLong(1, id);
				pStmt2.setLong(2, oib.getPicId());
				pStmt2.setString(3, oib.getDescription());
				pStmt2.setDouble(4, oib.getPrice());
				pStmt2.setClob(5, oib.getNote());
				pStmt2.executeUpdate();
				pStmt2.clearParameters();
			}
			System.out.println("123");
			conn.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("資料還原");
			if (conn != null)
				conn.rollback();
		} finally {

			if (pStmt != null) {
				pStmt.close();
			}
			if (pStmt2 != null) {
				pStmt2.close();
			}
			if (conn != null)
				conn.setAutoCommit(true);
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Orders getOrder(Long no) throws SQLException {
		String sqlOrder = "SELECT * FROM orders WHERE id = ? ";
		String sqlOrderItems = "SELECT * FROM Order_details WHERE order_id = ?";
		Set<OrderDetail> items = new HashSet<OrderDetail>();
		Connection conn = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Orders ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			pStmt.setLong(1, no);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				ob = new Orders(null, null, null);
				ob.setId(rs.getLong(1));
				Timestamp date = rs.getTimestamp("D_UPDATE");
				Instant instant = date.toInstant();
				LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
				ob.setUpdate(datetime);
				ob.setDsc(rs.getClob(3));
				ob.setMemId(rs.getLong(4));
			}
			if (ob == null) {
				throw new SQLException("資料庫邏輯錯誤：無此紀錄, 訂單編號=" + no);
			} else {
				pStmt2 = conn.prepareStatement(sqlOrderItems);
				pStmt2.setLong(1, no);
				rs2 = pStmt2.executeQuery();
				OrderDetail oib = null;
				while (rs2.next()) {
					oib = new OrderDetail();
					oib.setId(rs2.getLong(1));
					oib.setOrder_id(rs2.getLong(2));
					oib.setPicId(rs2.getLong(3));
					oib.setDescription(rs2.getString(4));
					oib.setPrice(rs2.getDouble(5));
					oib.setNote(rs2.getClob(6));
					items.add(oib);
				}
			}
			ob.setOrderDetails(items);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (pStmt2 != null) {
				pStmt2.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return ob;
	}

	public Collection<Orders> getAllOrders() throws SQLException {
		Collection<Orders> coll = new ArrayList<Orders>();
		String sqlOrder = "SELECT * FROM orders Order by D_UPDATE desc, ID desc ";
		PreparedStatement pStmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Orders ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				ob = new Orders(null, null, null);
				ob.setId(rs.getLong(1));
				Timestamp date = rs.getTimestamp("D_UPDATE");
				Instant instant = date.toInstant();
				LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
				ob.setUpdate(datetime);
				System.out.println(datetime);
				ob.setDsc(rs.getClob(3));
				ob.setMemId(rs.getLong(4));
				coll.add(ob);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		// System.out.println("OrderDAO coll.size()=" + coll.size());
		return coll;
	}
}