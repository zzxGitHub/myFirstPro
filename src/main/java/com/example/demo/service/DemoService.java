package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Demo;
import com.github.pagehelper.PageInfo;

public interface DemoService {

	Demo getInfo(String id);

	PageInfo<Demo> getInfoList(Integer pageIndex,Integer pageSize);

	String importInfo(MultipartFile file) throws Exception;

	String deleteInfo(Integer id);

	void downExcelFile(HttpServletRequest request, HttpServletResponse response);

}
