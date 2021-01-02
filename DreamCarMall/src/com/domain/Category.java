package com.domain;

import java.io.Serializable;

public class Category implements Serializable{
	private String id;//GUID
	private String name;//唯一 不能为空
	private String description;//描述
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
