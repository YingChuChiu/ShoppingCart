package ShoppingCart.entity;

import java.sql.Clob;

public class OrderItem {

	private Long id;
	private Long order_id;
	private String name;
	private Clob note;
	private Integer qty = 0;
	private Double price;
	
	public OrderItem(Long id, Long order_id, String name, Clob note, Integer qty, Double price) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.name = name;
		this.note = note;
		this.qty = qty;
		this.price = price;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clob getNote() {
		return note;
	}

	public void setNote(Clob note) {
		this.note = note;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}