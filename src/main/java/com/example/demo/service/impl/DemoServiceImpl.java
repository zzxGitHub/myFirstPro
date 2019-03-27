package com.example.demo.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.DemoDao;
import com.example.demo.entity.Demo;
import com.example.demo.model.DateTestModel;
import com.example.demo.service.DemoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;
@Service
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, Demo> redisTemplateDemo;
	
	
	
	private static final Logger log = LoggerFactory.getLogger( DemoServiceImpl.class );

	/**
	 */
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

	@Override
	public String deleteInfo(Integer id) {
		//查询是否存在
		int cnt = demoDao.findDemo(id);
		if(cnt > 0) {
			//从数据库中删除
			demoDao.delete(id);
			//删除缓存redis中的数据
			redisTemplateDemo.delete(id.toString());
		}else {
			return "is not exist";
		}
		return "success";
	}

	@Override
	public String testDateFormate(DateTestModel time) {
		System.out.println("--time--"+time.getAddDate());
		return time.getAddDate().toString();
	}
	
	static class Model{
        public Date date;
    }
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"date\":\"2019-03-18T14:53:31.8713586+08:00\"}";
		
		System.out.println("jackson:" + new ObjectMapper().readValue(json, Model.class).date);
		//System.out.println("fastjson:" + JSON.parseObject(json, Model.class).date);
	}

	@Override
	public void testWordTemplate(HttpServletResponse response) {
		// 获取项目资源文件夹 zbxx.doc
		Resource resource = new ClassPathResource("zbxx" + ".doc");
		Resource resource1 = new ClassPathResource("chmb" + ".ftl");
		BufferedInputStream in = null;
		OutputStream out = null;
		BufferedInputStream in2 = null;
		try {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode("sssss" + ".doc", "UTF-8"));
			//in = new BufferedInputStream(resource.getInputStream());
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("jbjq", "警报接警");
			map.put("xsbj", "刑事报警多少肯定解封了时代峻峰看介绍的反馈大立科技的开发几个老师快递费");
			map.put("zabj", "治安报警山东矿机分离焦虑跨境电商房管局上岛咖啡较高的非");
			map.put("stationName", "江汉分局治安大队");
			map.put("date", "2019年02月28日");
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			//String filePath = resource.getFile().getPath();
			configuration.setClassForTemplateLoading(this.getClass(), "/templates");
			Template freemarkerTemplate = configuration.getTemplate("mb.ftl");
			File file = createDoc(map,freemarkerTemplate);
			InputStream fin = new FileInputStream(file);
			in2 = new BufferedInputStream(fin);
			
			
			out = response.getOutputStream();
			byte[] buff = new byte[1024];
			int i = -1;
			while ((i = in2.read(buff)) != -1) {
				out.write(buff, 0, i);
			}
			out.flush();
			out.close();
			in2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static File createDoc(Map<String, String> dataMap, Template template) {
		String name = "zzx.doc";
		File f = new File(name);
		Template t = template;
		try {
			// 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	} 
	 
}
