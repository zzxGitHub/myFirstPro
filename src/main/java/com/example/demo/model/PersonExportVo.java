package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonExportVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 姓名
	 */
	@Excel(name = "姓名", orderNum = "0", width = 15)
	private String name;

	/**
	 * 登录用户名
	 */
	@Excel(name = "用户名", orderNum = "1", width = 15)
	private String username;

	@Excel(name = "手机号码", orderNum = "2", width = 15)
	private String phoneNumber;

	/**
	 * 人脸图片
	 */
	@Excel(name = "人脸图片", orderNum = "3", width = 15, height = 30, type = 2)
	private String imageUrl;
}
