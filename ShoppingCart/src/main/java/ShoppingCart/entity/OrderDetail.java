package ShoppingCart.entity;

import java.sql.Clob;

public class OrderDetail {

	Long id	;
	Long order_id;
	Double price;	
	Clob note;	
	
	public OrderDetail(){
		
	}

	public OrderDetail(Long id, Long order_id, Double price, Clob note) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.price = price;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Clob getNote() {
		return note;
	}

	public void setNote(Clob note) {
		this.note = note;
	}
	
}