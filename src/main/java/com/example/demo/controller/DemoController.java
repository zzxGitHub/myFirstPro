package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Demo;
import com.example.demo.service.DemoService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/first")
@Api(tags="测试")
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@GetMapping("/demo/{id}")
	@ApiOperation(value="单个查询",notes="根据id获取学生信息")
	public Demo demo(@PathVariable String id) {
		return demoService.getInfo(id);
	}
	
	@GetMapping("/demo/list/{pageIndex}/{pageSize}")
	@ApiOperation(value="分页列表查询",notes="列表分页查询")
	public PageInfo<Demo> demo(@PathVariable Integer pageIndex,@PathVariable Integer pageSize) {
		return demoService.getInfoList(pageIndex,pageSize);
	}
	
	@PostMapping(value = "/file")
	@ApiOperation("Upload files.")
	public String addFile(@RequestParam("file") MultipartFile file) throws IOException {
		return "success";
	}
}
