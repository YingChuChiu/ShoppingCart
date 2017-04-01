package ShoppingCart.entity;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.*;

// 本類別存放訂單資料
public class Orders {

	private Long id;
	private LocalDateTime update;
	private Clob dsc;
	private Long memId; 
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
	
	public Orders( LocalDateTime update, Clob dsc, Set<OrderDetail> orderDetails) {
		super();
		
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
