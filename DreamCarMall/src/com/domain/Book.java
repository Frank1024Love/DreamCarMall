package com.domain;

import java.io.Serializable;

public class Book implements Serializable {
	private String id;
	private String name;
	private String author;
	private float price;
	private String path;//存放图片的路径
	private String filename;//更改后的唯一文件名
	private String description;
	//Many2One
	private Category category;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return " #Car-Message   Name:" + name + "  Company:" + author + "  Price:" + price
				+ "  Description:[" + description + "]";
	}
	
	
	
}
