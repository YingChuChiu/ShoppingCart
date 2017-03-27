package ShoppingCart.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import ShoppingCart.entity.OrderItem;

public class ShoppingCart {

	private Map<Integer, OrderItem> cart = new LinkedHashMap<>();

	public ShoppingCart() {
	}

	public Map<Integer, OrderItem> getContent() {
		return cart;
	}

	public void addToCart(int photoID, OrderItem oi) {
		if (oi.getQty() <= 0) {
			return;
		}

		if (cart.get(photoID) == null) {
			cart.put(photoID, oi);
		} else {
			OrderItem oit = cart.get(photoID);
			oit.setQty(oi.getQty() + oit.getQty());
		}
	}

	public int deletephoto(int photoID) {
		if (cart.get(photoID) != null) {
			cart.remove(photoID);
			return 1;
		} else {
			return 0;
		}
	}

	public int getItemNumber() {
		return cart.size();
	}

	public double getSubtotal() {
		double subTotal = 0;
		Set<Integer> set = cart.keySet();
		for (int n : set) {
			double price = cart.get(n).getPrice();
			double discount = cart.get(n).getDiscount();
			int qty = cart.get(n).getQty();
			subTotal += price * discount * qty;
		}
		return subTotal;
	}

	public void listCart() {
		Set<Integer> set = cart.keySet();
		for (Integer k : set) {
			System.out.printf("BookID=%3d,  Qty=%3d,  price=%5.2f,  discount=%6.2f\n", k, cart.get(k).getQty(),
					cart.get(k).getPrice(), cart.get(k).getDiscount());
		}
	}
}
