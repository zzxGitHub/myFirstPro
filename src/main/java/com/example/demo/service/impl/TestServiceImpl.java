package com.example.demo.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.example.demo.controller.WebServiceController;
@WebService(serviceName = "TestService", // 与接口中指定的name一致
targetNamespace = "http://controller.demo.example.com", // 与接口中的命名空间一致,一般是接口的包名倒
endpointInterface = "com.example.demo.controller.WebServiceController"// 接口地址
)
@Component
public class TestServiceImpl implements WebServiceController{

	@Override
	public String sendMessage(String username) {
		return "hello";
	}

}
