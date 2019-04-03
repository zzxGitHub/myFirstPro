package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFFootnotes;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;






public class WordToPDF {
	/**
	 * 将word文档， 转换成pdf, 中间替换掉变量
	 * 
	 * @param source
	 *            源为word文档， 必须为docx文档
	 * @param target
	 *            目标输出
	 * @param params
	 *            需要替换的变量
	 * @throws Exception
	 */
	public static void wordConverterToPdf(InputStream source, OutputStream target, Map<String, String> params)
			throws Exception {
		wordConverterToPdf(source, target, null, params);
	}

	/**
	 * 将word文档， 转换成pdf, 中间替换掉变量
	 * 
	 * @param source
	 *            源为word文档， 必须为docx文档
	 * @param target
	 *            目标输出
	 * @param params
	 *            需要替换的变量
	 * @param options
	 *            PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
	 * @throws Exception
	 */
	public static void wordConverterToPdf(InputStream source, OutputStream target, PdfOptions options,
			Map<String, String> params) throws Exception {
		XWPFDocument doc = new XWPFDocument(source);
		// CTFtnEdn ctf = new CTFtnEdn();
		// doc.addFootnote();
		paragraphReplace(doc.getParagraphs(), params);
		params.put("审核:", "护手霜");
		// 尾注
		XWPFFootnote note = doc.getFootnoteByID(1);
		POIXMLDocumentPart foo = note.getOwner();
		paragraphReplace(note.getParagraphs(), params);
		
		/*for (XWPFParagraph p : note.getParagraphs()) {
			for (XWPFRun r : p.getRuns()) {
				String content = r.getText(r.getTextPosition());
				System.out.println("------content-----"+content);
				if (!StringUtils.isEmpty(content) && params.containsKey(content)) {
					r.setText(params.get(content), 0);
				}
			}
		}*/
		
		for (XWPFTable table : note.getTables()) {
			for (XWPFTableRow row : table.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					paragraphReplace(cell.getParagraphs(), params);
				}
			}
		}
		// 普通内容
		for (XWPFTable table : doc.getTables()) {
			for (XWPFTableRow row : table.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					paragraphReplace(cell.getParagraphs(), params);
				}
			}
		}
		PdfConverter.getInstance().convert(doc, target, options);
		
	}

	/** 替换段落中内容 */
	private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
		if (MapUtils.isNotEmpty(params)) {
			for (XWPFParagraph p : paragraphs) {
				for (XWPFRun r : p.getRuns()) {
					String content = r.getText(r.getTextPosition());
					if (!StringUtils.isEmpty(content) && params.containsKey(content)) {
						r.setText(params.get(content), 0);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		String filepath = "F:\\wordFile\\我是word.docx";
		String outpath = "F:\\wordFile\\我是结果.pdf";

		InputStream source;
		OutputStream target;
		try {
			source = new FileInputStream(filepath);
			target = new FileOutputStream(outpath);
			Map<String, String> params = new HashMap<String, String>();

			PdfOptions options = PdfOptions.create();

			wordConverterToPdf(source, target, options, params);
			PdfReader reader = new PdfReader(outpath);
			reader.getNumberOfPages();
			System.out.println("--reader.getNumberOfPages()--"+reader.getNumberOfPages());
			
			Rectangle pageSize = reader.getPageSize(reader.getNumberOfPages());
			Document document = new Document(pageSize);
			PdfWriter writer = PdfWriter.getInstance(document, target);
			document.open();
			PdfContentByte cbUnder = writer.getDirectContentUnder();
			PdfImportedPage pageTemplate = writer.getImportedPage(reader, 1);
			cbUnder.addTemplate(pageTemplate, 0, 0);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
