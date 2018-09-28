package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class WebServiceAsmxUtils {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList webServiceUtils(String url,String soapaction,String methodName,String paraName) {
		String City="湖北";
		ArrayList<String> ar = new ArrayList<String>();
		Service service=new Service();
		try {
			Call call=(Call)service.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(soapaction,methodName)); //设置要调用哪个方法
			call.addParameter(new QName(soapaction,paraName),
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(new QName(soapaction,methodName),Vector.class);//要返回的数据类型（自定义类型）
//          call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//（标准的类型）
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapaction + methodName);
			Vector v=(Vector)call.invoke(new Object[]{City});//调用方法并传递参数
			for(int i=0;i<v.size();i++) {
				System.out.println(v.get(i));
				ar.add(v.get(i).toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ar;
	}
}
