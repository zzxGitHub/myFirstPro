package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.service.WebService;
import com.example.demo.utils.WebServiceAsmxUtils;

@Service
public class WebServiceImpl implements WebService{

	@Override
	public String getInfo() {
		return "sdsds";
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
