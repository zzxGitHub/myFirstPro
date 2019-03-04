package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.entity.Demo;


@Configuration
public class RedisConfig {
	Logger log = LoggerFactory.getLogger(getClass());
	
	public RedisConfig() {
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		System.out.println("--初始化--");
	}
	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
	
	@Bean
    public RedisTemplate<String, Demo> redisTemplateDemo(RedisConnectionFactory factory) {
        RedisTemplate<String, Demo> template = new RedisTemplate<String, Demo>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
