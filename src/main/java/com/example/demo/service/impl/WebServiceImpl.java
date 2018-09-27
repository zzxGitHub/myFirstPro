package com.example.demo.service.impl;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;

import com.example.demo.service.WebService;

@Service
public class WebServiceImpl implements WebService{

	@Override
	public String getInfo() {
		/*String url = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(url);
		// 需要密码的情况需要加上用户名和密码
		client.getOutInterceptors().add(new ClientLoginInterceptor(userNmae, pwd));
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke("bph_data_exchange", param, xml);
			String str = objects[0].toString();
			System.out.println(str);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
}
