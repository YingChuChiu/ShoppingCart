package ShoppingCart.entity;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.*;

// 本類別存放訂單資料
public class Orders {

	private Long id;
	private LocalDateTime update;
	private Long memId; // 說明下訂人員
	private Clob dsc;
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();

	public Orders(Long id, LocalDateTime update, Long memId, Clob dsc, Set<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.update = update;
		this.memId = memId;
		this.dsc = dsc;
		this.orderDetails = orderDetails;
	}
	public Orders(LocalDateTime update, Long memId, Clob dsc, Set<OrderDetail> orderDetails) {
		super();
		
		this.update = update;
		this.memId = memId;
		this.dsc = dsc;
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

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public Clob getDsc() {
		return dsc;
	}

	public void setDsc(Clob dsc) {
		this.dsc = dsc;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
