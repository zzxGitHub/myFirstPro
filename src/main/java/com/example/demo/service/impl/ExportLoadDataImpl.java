package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Demo;

import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
@Service
public class ExportLoadDataImpl implements IExcelExportServer{

	@Override
	public List<Object> selectListForExcelExport(Object obj, int page) {
		List<Object> result = new ArrayList<>();
		for(int i=0;i<100000;i++) {
			Demo a = new Demo();
			a.setId(i);
			a.setName("张泽雄"+i);
			result.add(a);
		}
		if(page>1){
            return null;
        }
		return result;
	}

}
