package com.example.demo.webCon;

import java.util.List;

public class EnglishWsClient {
	
	public static void main(String[] args) {
		EnglishChinese factory = new EnglishChinese();
		EnglishChineseSoap chinese = factory.getEnglishChineseSoap();
		ArrayOfString weatherInfo = chinese.suggestWord("hel");
		List<String> any = weatherInfo.getString();
		for(Object str:any) {
			System.out.println(str.toString());
		}
	}
}
