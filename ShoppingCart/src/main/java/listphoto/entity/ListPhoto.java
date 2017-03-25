package listphoto.entity;

import java.sql.Blob;
import java.time.LocalDateTime;

public class ListPhoto {

	private Integer id;// 流水號
	private String name;// 名稱
	private Integer assort; // 分類
	private LocalDateTime dateUpLoad;// 上傳時間
	private Boolean visibility; // 公開 or 私人的?
	private Double price;// 售價
	private Blob file_photo;// 檔案

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAssort() {
		return assort;
	}

	public void setAssort(Integer assort) {
		this.assort = assort;
	}

	public LocalDateTime getDateUpLoad() {
		return dateUpLoad;
	}

	public void setDateUpLoad(LocalDateTime dateUpLoad) {
		this.dateUpLoad = dateUpLoad;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Blob getFile_photo() {
		return file_photo;
	}

	public void setFile_photo(Blob file_photo) {
		this.file_photo = file_photo;
	}

	@Override
	public String toString() {
		return "ListPhoto [id=" + id + ", name=" + name + ", assort=" + assort + ", dateUpLoad=" + dateUpLoad
				+ ", visibility=" + visibility + ", price=" + price + ", file_photo=" + file_photo + "]";
	}



}
