package ShoppingCart.entity;

import java.sql.Blob;

public class OrderItem {
	String name;
	Blob file_photo;
	int qty = 0;
	int photoID = 0;
	double price = 0;
	double discount = 1;

	public OrderItem(String name, Blob file_photo, int qty, int bookID, double price, double discount) {
		super();
		this.name = name;
		this.file_photo = file_photo;
		this.qty = qty;
		this.photoID = bookID;
		this.price = price;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getFile_photo() {
		return file_photo;
	}

	public void setFile_photo(Blob file_photo) {
		this.file_photo = file_photo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getBookID() {
		return photoID;
	}

	public void setBookID(int photoID) {
		this.photoID = photoID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
