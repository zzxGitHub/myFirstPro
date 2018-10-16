package com.example.demo.controller;

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
	
	@PostMapping("/demo/delete")
	@ApiOperation(value="删除",notes="根据id删除学生信息")
	public String delete(@RequestParam(value="id",required=true)Integer id) {
		return demoService.deleteInfo(id);
	}
	
	@GetMapping("/demo/list/{pageIndex}/{pageSize}")
	@ApiOperation(value="分页列表查询",notes="列表分页查询")
	public PageInfo<Demo> demo(@PathVariable Integer pageIndex,@PathVariable Integer pageSize) {
		return demoService.getInfoList(pageIndex,pageSize);
	}
	
	@PostMapping(value = "/importFile")
	@ApiOperation("Upload files.上传excel,导入信息进数据库")
	public String addFile(@RequestParam("file") MultipartFile file) throws Exception {
		return demoService.importInfo(file);
	}
}
