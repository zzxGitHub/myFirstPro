package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.WebService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webService")
@Api(tags="测试webService")
public class WebServiceTestController {
	@Autowired
	private WebService webService;
	
	@GetMapping("/demo")
	@ApiOperation(value="调用尾部webservice",notes="调用外部webservice服务")
	public String demo() {
		return webService.getInfo();
	}
}
