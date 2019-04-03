package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class XmlToDocx {
	
	/**
	 *
	 * @param xmlTemplate
	 *            xml的文件名
	 * @param docxTemplate
	 *            docx的路径和文件名
	 * @param xmlTemp
	 *            填充完数据的临时xml
	 * @param toFilePath
	 *            目标文件名
	 * @param map
	 *            需要动态传入的数据
	 * @throws IOException
	 * @throws TemplateException
	 *//*
	public static void toDocx(String xmlTemplate, String docxTemplate, String xmlTemp, String toFilePath, Map map) {
		try {
			// 1.map是动态传入的数据
			Writer w = new FileWriter(new File(xmlTemp));
			// 2.把map中的数据动态由freemarker传给xml
			XmlTplUtil.process(xmlTemplate, map, w);

			// 3.把填充完成的xml写入到docx中
			XmlToDocx xtd = new XmlToDocx();

			xtd.outDocx(new File(xmlTemp), docxTemplate, toFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*//**
	 *
	 * @param documentFile
	 *            动态生成数据的docunment.xml文件
	 * @param docxTemplate
	 *            docx的模板
	 * @param toFilePath
	 *            需要导出的文件路径
	 * @throws ZipException
	 * @throws IOException
	 *//*

	public void outDocx(File documentFile, String docxTemplate, String toFilePath) throws ZipException, IOException {

		try {
			File docxFile = new File(docxTemplate);
			ZipFile zipFile = new ZipFile(docxFile);
			Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
			ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
			int len = -1;
			byte[] buffer = new byte[1024];
			while (zipEntrys.hasMoreElements()) {
				ZipEntry next = zipEntrys.nextElement();
				InputStream is = zipFile.getInputStream(next);
				// 把输入流的文件传到输出流中 如果是word/document.xml由我们输入
				zipout.putNextEntry(new ZipEntry(next.toString()));
				if ("word/document.xml".equals(next.toString())) {
					InputStream in = new FileInputStream(documentFile);
					while ((len = in.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					in.close();
				} else {
					while ((len = is.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					is.close();
				}
			}
			zipout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	 /**
     * @Description 根据参数生成docx合同文档
     * @author belle.wang
     * @param templatepath 模板所在文件夹
     * @param docxname docx格式模板文件名(不带路径)
     * @param xmlname xml格式模板，有freemaker标记(不带路径)
     * @param tmpxmlpath 临时xml文件路径
     * @param targetPath 目标路径
     * @param param 待填充数据
     * @return
     * @throws Exception
     */
    public static boolean toDocx(String templatepath, String docxname, String xmlname,
                                    String tmpxmlpath, String targetPath, Map<String, Object> param)  {
        try {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(templatepath));
        Template template = cfg.getTemplate(xmlname);
        template.setOutputEncoding("UTF-8");
        Writer out = new FileWriter(new File(tmpxmlpath));
        // 数据放到模板xml里面，生成带数据的xml
        template.process(param, out);
        if (out != null) {
            out.close();
        }
        // 带数据的xml生成docx
        File file = new File(tmpxmlpath);
        File docxFile = new File(templatepath + "/" + docxname);
        ZipFile zipFile = new ZipFile(docxFile);
        Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
        ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(targetPath));
        int len = -1;
        byte[] buffer = new byte[1024];
        while (zipEntrys.hasMoreElements()) {
            ZipEntry next = zipEntrys.nextElement();
            InputStream is = zipFile.getInputStream(next);
            // 把输入流的文件传到输出流中 如果是word/document.xml由我们输入
            zipout.putNextEntry(new ZipEntry(next.toString()));
            if ("word/document.xml".equals(next.toString())) {
                InputStream in = new FileInputStream(file);
                while ((len = in.read(buffer)) != -1) {
                    zipout.write(buffer, 0, len);
                }
                in.close();
            } else {
                while ((len = is.read(buffer)) != -1) {
                    zipout.write(buffer, 0, len);
                }
                is.close();
            }
        }
        zipout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
 
 
 
    public static void main(String[] args) throws IOException, TemplateException {
 
        // 路径
        String templatepath = "F:/";
        String docxname = "test11.docx";
        String xmlname = "test11.xml";
        String tmpxmlpath = "F:\\tmpTest.xml";
        String targetpath = "F:\\testNew.docx";
        // 数据
        Map<String,Object> data = new HashMap();
        data.put("zzx","这里是文字sdds");
        // 生成文档
        try {
            toDocx(templatepath, docxname, xmlname, tmpxmlpath, targetpath, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
