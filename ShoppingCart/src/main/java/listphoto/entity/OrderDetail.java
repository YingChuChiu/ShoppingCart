package listphoto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import tw.com.iwow.entity.id.OrderDetailId;

@Entity
@Table(name = "ORDER_DETAILS") // oderDetail 為記錄每次訂單內容,因單次可有多筆訂單,拆出orderDetail
@IdClass(OrderDetailId.class)
public class OrderDetail {
	@Id
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Id
	@Column(name = "SPEC_ID")
	private Long specId;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "SPEC_ID")
	private Spec spec;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public Spec getSpec() {
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

}
