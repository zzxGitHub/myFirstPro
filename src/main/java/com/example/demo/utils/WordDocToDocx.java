package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordDocToDocx {
	public static void main(String[] args) throws IOException {
		String filePath = "F:\\wordFile\\wordDoc.doc";
		//新建一个文档
        XWPFDocument doczzz = new XWPFDocument();
		try {
			InputStream source = new FileInputStream(filePath);
			HSSFWorkbook doc = new HSSFWorkbook(source);
			XSSFWorkbook docx = new XSSFWorkbook();
			
			OutputStream os = new FileOutputStream("F:\\wordFile\\wordDoc111.docx");
			doczzz.write(os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
