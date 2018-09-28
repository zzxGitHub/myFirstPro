package com.example.demo.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	@Transactional
	@Override
	public String importInfo(MultipartFile file) throws Exception {
		boolean notNull = false;
		List<Demo> userList = new ArrayList<Demo>();
		String fileName = file.getOriginalFilename();
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new Exception("上传文件格式不正确");
		}
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		// 获取文件内容
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
			//xsl后缀
			wb = new HSSFWorkbook(is);
		} else {
			//xslt后缀
			wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);//获取第一个表单
		if(sheet!=null){
            notNull = true;
        }
		//循环获取数据
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			Demo demo = new Demo();
			Row row = sheet.getRow(r);
			if(row == null) {
				continue;
			}
			//第一列
			if( row.getCell(0).getCellType() !=1){
                throw new Exception("导入失败(第"+(r+1)+"行,id请设为文本格式)");
            }
			//取值
			int id = Integer.parseInt(row.getCell(0).getStringCellValue());
			if(id==0){
                throw new Exception("导入失败(第"+(r+1)+"行,id未填写)");
            }
			
			//第二列
			if( row.getCell(1).getCellType() !=1){
                throw new Exception("导入失败(第"+(r+1)+"行,name请设为文本格式)");
            }
			//取值
			String name = row.getCell(1).getStringCellValue();
			if(name == null || name.isEmpty()){
                throw new Exception("导入失败(第"+(r+1)+"行,id未填写)");
            }
			demo.setId(id);
			demo.setName(name);
			userList.add(demo);
		}
		for(Demo demo:userList) {
			int cnt = demoDao.findDemo(demo.getId());
			if(cnt > 0) {
				demoDao.update(demo);
			}else {
				demoDao.insert(demo);
			}
		}
		return "success";
	}
}
