package com.example.demo.controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "TestService", // 暴露服务名称
targetNamespace = "http://controller.demo.example.com"// 命名空间,一般是接口的包名倒序
)
public interface WebServiceController {
	@WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String sendMessage(@WebParam(name = "username") String username);
}
