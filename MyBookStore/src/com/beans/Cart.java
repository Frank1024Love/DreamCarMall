package com.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.domain.Book;
//购物车
public class Cart implements Serializable {
	//key:对应的书的ID   value:CartItem购物项，一次购物过程一项就对应一本书
	private Map<String, CartItem> items = new HashMap<String, CartItem>();
	private int totalQuantity;//总数量
	private float totalMoney;//总金额
	public int getTotalQuantity() {
		totalQuantity = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			totalQuantity +=me.getValue().getQuantity();
		}
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public float getTotalMoney() {
		totalMoney = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			totalMoney +=me.getValue().getMoney();
		}
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Map<String, CartItem> getItems() {
		return items;
	}
	//向购物车中添加一本书
	public void addBook(Book book){
		if(items.containsKey(book.getId())){
			//有对应的购物项则数量加1
			CartItem item = items.get(book.getId());
			item.setQuantity(item.getQuantity()+1);
		}else{
			CartItem item = new CartItem(book);
			item.setQuantity(1);
			items.put(book.getId(), item);
		}
	}
}
