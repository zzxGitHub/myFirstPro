package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Demo;
import com.example.demo.enums.TestEnum;

public class TestUtils {
	public static void main(String[] args) {
		/*assert "a".equals("b");
		int i = TestEnum.EEE.ordinal();
		System.out.println("--iii--"+i);
		Demo demo = new Demo();
		Demo demo1 = new Demo();
		Demo demo2 = new Demo();
		demo.setId(1);
		demo.setName("zzx1");
		demo1.setId(2);
		demo1.setName("zzx2");
		demo2.setId(3);
		demo2.setName("zzx3");
		List<Demo> demoList = new ArrayList<>();
		demoList.add(demo);
		demoList.add(demo1);
		demoList.add(demo2);
		List<String> name = demoList.stream().map(Demo::getName).collect(Collectors.toList());
		
		name.stream().forEach(n->{
			System.out.println("--name--"+n);
		});*/
		String a = "a"; // "a" 进入常量池
		a.intern();
		String b = new String("b");// b 进入常量池
		String c = "ab"; // "ab" 进入常量池
		String d = a+b; // "a" 与  "b" 进入常量池，此时常量池中已经存在"ab"，d直接指向"ab",所以c与d== true
		System.out.println(c==d);
		
		String s = new String("1");
		
		
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        
       
       /* String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);*/
        
  
       /* //+连接但编译器不优化
        String s1=new String("xy") + "z";  
        String s2=s1.intern();  
        System.out.println( s1==s1.intern() );  
        System.out.println( s1+" "+s2 );  
        System.out.println( s2==s1.intern() ); 
        
      // 一般情况
        String s1=new String("xyz") ;  
        String s2=s1.intern();  
        System.out.println( s1==s1.intern() );  
        System.out.println( s1+" "+s2 );  
        System.out.println( s2==s1.intern() ); 
        */
 
       //编译器优化
        /*String s1 = "xy" + "z";
        String s2 = s1.intern();
        System.out.println( s1==s1.intern() );  
        System.out.println( s1+" "+s2 );  
        System.out.println( s2==s1.intern() ); */
	}
}
