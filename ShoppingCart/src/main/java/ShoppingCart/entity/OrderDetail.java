package ShoppingCart.entity;

import java.sql.Clob;

public class OrderDetail {

	Long id	; //流水號
 	Long order_id; //訂單號碼
 	Long picId;
	String description; //訂單項目名稱
	Double price; 	
	Clob note;	
	
	public OrderDetail(){
		
	}

	public OrderDetail(Long id, Long order_id, Long picId, String description, Double price, Clob note) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.picId = picId;
		this.description = description;
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

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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