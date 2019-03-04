package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.example.demo")
@EnableSwagger2
@MapperScan("com.example.demo.dao")
public class DemoApplication {
	static Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		System.out.println("--测试日志级别--");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		SpringApplication.run(DemoApplication.class, args);
	}
}
