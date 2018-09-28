package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;

import com.example.demo.service.WebService;
import com.example.demo.utils.WebServiceAsmxUtils;

@Service
public class WebServiceImpl implements WebService{

	@Override
	public String getInfo() {
		String url = "http://localhost:8080/services/TestService?wsdl";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(url);
		// 需要密码的情况需要加上用户名和密码
		//client.getOutInterceptors().add(new ClientLoginInterceptor(userNmae, pwd));
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke("sendMessage","1");
			String str = objects[0].toString();
			System.out.println(str);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 调用远程webservice
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getInfoRemote() {
		String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
		String soapaction = "http://WebXml.com.cn/";
		String methodName = "getSupportCity";
		String paraName = "byProvinceName";
		ArrayList<String> result = WebServiceAsmxUtils.webServiceUtils(url, soapaction, methodName, paraName);
		return result;
	}
}
