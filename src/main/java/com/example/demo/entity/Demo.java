package com.example.demo.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Demo implements Serializable{
	
	private static final long serialVersionUID = -5670100941398542996L;
	@Excel(name = "id")
	private Integer id;
	@Excel(name = "姓名")
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
