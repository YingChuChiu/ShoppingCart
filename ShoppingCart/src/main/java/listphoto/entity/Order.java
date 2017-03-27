package listphoto.entity;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="ORDERS")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime update;
	private Clob description;// 針對order 補充說明
	@Column(name="mem_id")
	private Long memberId; //說明下訂人員
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
	@JoinColumn(name = "ORDER_ID", referencedColumnName="ID")
	private Set<OrderDetail> orderDetails;
	//add field total price from orderDetails?

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

	public Clob getDescription() {
		return description;
	}

	public void setDescription(Clob description) {
		this.description = description;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
