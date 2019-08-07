package com.example.demo.model;

import java.util.Date;

import com.example.demo.consts.BusinessFromEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class DateTestModel {
	
	@ApiModelProperty("测试时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX")
	private Date addDate;
	
	@ApiModelProperty("枚举")
	private BusinessFromEnum eaaa;

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public BusinessFromEnum getEaaa() {
		return eaaa;
	}

	public void setEaaa(BusinessFromEnum eaaa) {
		this.eaaa = eaaa;
	}
	
}
