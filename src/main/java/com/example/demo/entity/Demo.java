package com.example.demo.entity;

import java.io.Serializable;

public class Demo implements Serializable{
	
	private static final long serialVersionUID = -5670100941398542996L;
	private Integer id;
	private String name;
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
}
