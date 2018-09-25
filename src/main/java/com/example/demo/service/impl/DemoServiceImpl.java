package com.example.demo.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DemoDao;
import com.example.demo.entity.Demo;
import com.example.demo.service.DemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, Demo> redisTemplateDemo;
	
	private static final Logger log = LoggerFactory.getLogger( DemoServiceImpl.class );

	@Override
	public Demo getInfo(String id) {
		//测试从redis中获取数据
		String zzx = redisTemplate.opsForValue().get("abc");
		if(StringHelper.isNullOrEmptyString(zzx)) {
			
		}
		//将数据放入redis中
		redisTemplate.opsForValue().set("ooddd", "dddd");
		log.info("向redis中存入数据");
		//从缓存中取值
		Demo demo = redisTemplateDemo.opsForValue().get(id);
		if(demo==null) {
			log.info("开始查询数据库取值");
			demo = demoDao.test(id);
			redisTemplateDemo.opsForValue().set(id, demo,1,TimeUnit.MINUTES);
		}else {
			log.info("从redis中取的值");
		}
		return demo;
	}

	@Override
	public PageInfo<Demo> getInfoList(Integer pageIndex,Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<Demo> result = demoDao.findList();
		PageInfo<Demo> page = new PageInfo<Demo>(result);
		return page;
	}
}
