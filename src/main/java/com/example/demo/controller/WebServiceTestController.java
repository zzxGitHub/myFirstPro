package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.WebService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webService")
@Api(tags="调用webService")
public class WebServiceTestController {
	@Autowired
	private WebService webService;
	
	@GetMapping("/localDemo")
	@ApiOperation(value="调用本地webservice",notes="调用本地webservice服务，非asmx结尾的地址")
	public String demo() {
		return webService.getInfo();
	}
	
	@GetMapping("/remoteDemo")
	@ApiOperation(value="调用远程webservice,asmx结尾",notes="asmx结尾的地址")
	public List<String> demoRemote() {
		return webService.getInfoRemote();
	}
}
