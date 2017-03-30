package ShoppingCart.entity;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.*;
// 本類別存放訂單資料
public class Order {
	
	private Long id;
	private LocalDateTime update;
	private Clob dsc;// 針對order 補充說明
	private Long memId; // 說明下訂人員
	private Set<OrderDetail> orderDetails;
	
	public Order(Long id, LocalDateTime update, Clob dsc, Long memId, Set<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.update = update;
		this.dsc = dsc;
		this.memId = memId;
		this.orderDetails = orderDetails;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getUpdate() {
		return update;
	}
	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}
	public Clob getDsc() {
		return dsc;
	}
	public void setDsc(Clob dsc) {
		this.dsc = dsc;
	}
	public Long getMemId() {
		return memId;
	}
	public void setMemId(Long memId) {
		this.memId = memId;
	}
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
