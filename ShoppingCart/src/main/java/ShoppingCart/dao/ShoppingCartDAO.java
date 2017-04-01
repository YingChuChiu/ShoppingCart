package ShoppingCart.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import ShoppingCart.entity.OrderItem;

public class ShoppingCartDAO {
	
	// LinkedHashMap是顺序存放，HashMap不是按插入顺序存放
	private Map<Integer, OrderItem> cart = new LinkedHashMap<>();
	

	public Map<Integer, OrderItem> getContent() {
		return cart;
	}

	//增加購物車商品
	public void addToCart(long id, OrderItem oi) { 
		if (oi.getId() <= 0) {
			return;
		}

		if (cart.get(id) == null) {
			cart.put((int) id, oi);
		}
	}

	//移除購物車商品
	public int deletephoto(int photoID) { 
		if (cart.get(photoID) != null) {
			cart.remove(photoID);
			return 1;
		} else {
			return 0;
		}
	}

	//
	public int getItemNumber() {
		return cart.size();
	}

	//商品總計
	public double getSubtotal() {
		double subTotal = 0; 
		Set<Integer> set = cart.keySet();
		for (int n : set) {
			double price = cart.get(n).getPrice();
			
			subTotal += price * 1;//限定商品只能買一次
		}
		return subTotal;
	}

	//商品清單
	public void listCart() {
		Set<Integer> set = cart.keySet();
		for (Integer k : set) {
			System.out.printf("BookID=%3d,  Qty=%3d,  price=%5.2f,  discount=%6.2f\n", k, cart.get(k).getId(),
					cart.get(k).getPrice());
		}
	}
}
