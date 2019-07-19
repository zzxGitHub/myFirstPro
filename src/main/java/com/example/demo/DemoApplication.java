package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.example.demo",exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
@MapperScan("com.example.demo.dao")
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		logger.info("info");
		logger.debug("debug");
		logger.error("err");
		logger.warn("warn");
		SpringApplication.run(DemoApplication.class, args);
	}
}
