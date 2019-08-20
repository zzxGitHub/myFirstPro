package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Content;
import com.example.demo.service.ElasticSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/es")
@Api(tags = "es测试接口")
public class ElasticSearchController {

	@Autowired
	private ElasticSearchService elasticSearchService;

	@PostMapping(value = "/testPostElastic")
	@ApiOperation("测试es插入数据")
	public void getTest(@RequestBody Content con) {
		elasticSearchService.addInfo(con);
	}

	@PostMapping(value = "/testAddIndex")
	@ApiOperation("测试es新增index")
	public void getAddIndex(@RequestBody Content con) {
		elasticSearchService.addIndex(con);
	}

	@PostMapping(value = "/testDeleteIndex")
	@ApiOperation("测试es删除index")
	public void getDeleteIndex(@RequestParam String indexName) {
		elasticSearchService.deleteIndex(indexName);
	}
}
