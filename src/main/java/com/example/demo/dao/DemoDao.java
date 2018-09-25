package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Demo;

public interface DemoDao {

	Demo test(@Param("id")String id);

	List<Demo> findList();

}
