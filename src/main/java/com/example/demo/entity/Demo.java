package com.example.demo.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Demo implements Serializable{
	
	private static final long serialVersionUID = -5670100941398542996L;
	
	private Integer id;
	
	private String name;
	
}
