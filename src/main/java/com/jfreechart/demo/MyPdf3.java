package com.jfreechart.demo;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.events.IndexEvents;
import com.itextpdf.text.pdf.events.IndexEvents.Entry;


public class MyPdf3 {
	public static void test(Map<String, Object> param,String fileDir, String fileName) throws Exception {
		createDir(fileDir);//创建临时目录
		try {			
					System.setProperty("sun.jnu.encoding","utf-8");
			             /** 实例化文档对象 */
			             Document document = new Document(PageSize.A4, 100, 100, 100, 100);
//			             document.add(n);
			             /** 创建 PdfWriter 对象 */
			             PdfWriter pw = PdfWriter.getInstance(document,// 文档对象的引用
			                     new FileOutputStream(fileDir+fileName));//文件的输出路径+文件的实际名称
			             
			             
			             
//			             ContentEvent event = new ContentEvent();
			             IndexEvents e = new IndexEvents();
//			             PdfPageEvent ppe = new PdfPageEvent();
			             pw.setPageEvent(e);
			             
			             document.open();// 打开文档
			             
			             document.newPage();
			             addCover(document,param);
			             
			             document.newPage();
			             addCatalog(document);  // TODO
			             
			             document.newPage();
			             addIntroduction(document);
			             
			             document.newPage();
			             addMainBody(document,param);
			             
			             
			             document.newPage();
			             
			             /** pdf文档中中文字体的设置，注意一定要添加iTextAsian.jar包 */
			             BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			                     "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			             Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);//加入document：
			             /** 向文档中添加内容，创建段落对象 */
			             
			             document.add(new Paragraph("First page of the document."));
			             document.add(new Paragraph("First page of the document."));// Paragraph添加文本
			             document.add(new Paragraph("我们是害虫", FontChinese));
			             /** 创建章节对象 */
			             Paragraph title1 = new Paragraph("第一章", FontChinese);
			             Chapter chapter1 = new Chapter(title1, 1);
			             chapter1.setNumberDepth(0);
			            /** 创建章节中的小节 */
			             Paragraph title11 = new Paragraph("表格的添加", FontChinese);
			             Section section1 = chapter1.addSection(title11);
			             /** 创建段落并添加到小节中 */
			             Paragraph someSectionText = new Paragraph("下面展示的为3 X 2 表格.",
			                     FontChinese);
			             section1.add(someSectionText);
			             /** 创建表格对象（包含行列矩阵的表格） */
			             
			             PdfPTable t = new PdfPTable(5);
			             for(int row=1;row<=5;row++) {
			            	 for(int c=1;c<=3;c++) {
			            		 t.addCell(row+","+c);
			            	 }
			             }
/*			             t.setBorderColor(new Color(220, 255, 100));
			             t.setPadding(5);
			             t.setSpacing(5);
			             t.setBorderWidth(1);
			             Cell c1 = new Cell(new Paragraph("第一格", FontChinese));
			             t.addCell(c1);
			             c1 = new Cell("Header2");
			             t.addCell(c1);
			             c1 = new Cell("Header3");
			             t.addCell(c1);
			             // 第二行开始不需要new Cell()
			             t.addCell("1.1");
			             t.addCell("1.2");
			             t.addCell("1.3");*/
			             section1.add(t);
			             /** 创建章节中的小节 */
			             Paragraph title13 = new Paragraph("列表的添加", FontChinese);
			             Section section3 = chapter1.addSection(title13);
			             /** 创建段落并添加到小节中 */
			             Paragraph someSectionText3 = new Paragraph("下面展示的为列表.", FontChinese);
			             section3.add(someSectionText3);
			             /** 创建列表并添加到pdf文档中 */
			             List l = new List(true, true, 10);// 第一个参数为true，则创建一个要自行编号的列表，
			             // 如果为false则不进行自行编号
			             l.add(new ListItem("First item of list"));
			             l.add(new ListItem("第二个列表", FontChinese));
			             section3.add(l);
			             document.add(chapter1);
			             /** 创建章节对象 */
			             Paragraph title2 = new Paragraph("第二章", FontChinese);
			             Chapter chapter2 = new Chapter(title2, 1);
			             chapter2.setNumberDepth(0);
			             /** 创建章节中的小节 */
			             Paragraph title12 = new Paragraph("png图片添加", FontChinese);
			             Section section2 = chapter2.addSection(title12);
			             /** 添加图片 */
			             section2.add(new Paragraph("图片添加: 饼图", FontChinese));
			             Image png = Image.getInstance("F:/abc.png");//图片的地址
			             section2.add(png);
			             document.add(chapter2);
			             
			             
			             
			             
			             Chapter indexChapter = new Chapter(new Paragraph("目录：", FontChinese), 0);  
			 	        indexChapter.setNumberDepth(-1);                              // 设置数字深度  
			 	        for (Entry index : e.getSortedEntries()) {  
			 	            String key = index.getKey();  
			 	            String keyValue = key;  
			 	            float size = 18f;  
			 	            if (countInString(key, ".") == 2) { //小标题缩进判断, 如有三级标题自己添加判断  
			 	                keyValue = "    " + key;  
			 	                size = 15f;  
			 	            } else if (countInString(key, ".") == 3) {  
			 	                keyValue = "    " + "    " + key;  
			 	                size = 12f;  
			 	            }  
			 	            Paragraph paragraph = new Paragraph(keyValue,FontChinese);  
			 	              
			 	            Chunk chunk0101 = new Chunk(new DottedLineSeparator());  
			 	              
			 	            Chunk pageno = new Chunk(index.getKey() + "");  
			 	  
			 	            Chunk chunk0102 = new Chunk(pageno);  
			 	  
			 	            //加入点点  
			 	            paragraph.add(chunk0101);  
			 	            //加入页码  
			 	            paragraph.add(chunk0102);  
			 	              
			 	            indexChapter.add(paragraph);  
			 	        }  
			 	          
			 	        document.add(indexChapter);  
			 	          
			 	        document.newPage();  
			 	          
			 	        //添加内容  
//			 	        for (Chapter c : chapterList) {  
//			 	            e. = true;  
//			 	            document.add(c);  
//			 	        }

			             
			             
			             document.close();
			         } catch (Exception e2) {
			             System.out.println(e2.getMessage());
			             throw e2;
			         }
	}
	
	
	
	private static void createDir(String fileDir) {
		File dir = new File(fileDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}



	private static void addMainBody(Document doc,Map<String, Object> param) throws DocumentException, IOException {
		float title_1=15;
		float title_2=12;
		float title_3=9;
		
		Paragraph title1 = new Paragraph("一、综合情况", getFont(title_1,Font.BOLD));
		title1.setLeading(30);
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);
		doc.add(chapter1);
		addParagraph(doc, "您在本阶段中评估中综合得分为："+param.get("zhScore")+" 分 （满分 5 分）， 本阶段评估平均分 为 "+param.get("avgScore")+" 分，您在此次评估中排名第 "+param.get("rank")+" 名，超过了"+param.get("rankPerc")+"的住院医师。");
		
		Paragraph title2 = new Paragraph("二、分数构成", getFont(title_1,Font.BOLD));
		title2.setLeading(30);
		Chapter chapter2 = new Chapter(title2, 2);
		chapter2.setNumberDepth(0);
		doc.add(chapter2);
		
		nextLine(doc, 1);
		
		PdfPTable t = new PdfPTable(3);
		String headers[] = {"评估人员","评估人数","权重"};
		String headersKey[] = {"pRoleName","pCount","weight"};
		java.util.List<Map<String,Object>> sList = (java.util.List<Map<String, Object>>) param.get("scoreForm");
		addHeaders(t,headers,headersKey,sList);
        doc.add(t);
		
		Paragraph title3 = new Paragraph("三、核心能力", getFont(title_1,Font.BOLD));
		title3.setLeading(30);
		Chapter chapter3 = new Chapter(title3, 3);
		chapter3.setNumberDepth(0);
		
		Paragraph title31 = new Paragraph("1、本阶段对比", getFont(title_2,Font.BOLD));
		title31.setLeading(30);
		Section section31 = chapter3.addSection(title31);
		section31.setNumberDepth(0);
		
		
		Paragraph title32 = new Paragraph("2、本阶段详情", getFont(title_2,Font.BOLD));
		title32.setLeading(30);
		Section section32 = chapter3.addSection(title32);
		section32.setNumberDepth(0);
		section32.add(new Paragraph("\n"));
		addJdDetail(section32,param);
		
		Paragraph title33 = new Paragraph("3、综合得分阶段变化", getFont(title_2,Font.BOLD));
		title33.setLeading(30);
		Section section33 = chapter3.addSection(title33);
		section33.setNumberDepth(0);
		
		doc.add(chapter3);
		
		
		Paragraph title4 = new Paragraph("四、能力优势和待发展", getFont(title_1,Font.BOLD));
		title4.setLeading(30);
		Chapter chapter4 = new Chapter(title4, 1);
		chapter4.setNumberDepth(0);
		chapter4.add(new Paragraph("\n"));
		set_4_0(chapter4,param);
		
		
		Paragraph title41 = new Paragraph("1、优势项", getFont(title_2,Font.BOLD));
		title41.setLeading(30);
		Section section41 = chapter4.addSection(title41);
		Paragraph p_411 = new Paragraph("优势项是您得分最高的三个评估项", getFont(title_3,Font.NORMAL));
		p_411.setLeading(30);
		section41.add(p_411);
		section41.add(new Paragraph("\n"));
		addBestQlist(section41,param);
		section41.setNumberDepth(0);
		
		
		Paragraph title42 = new Paragraph("2、待发展项", getFont(title_2,Font.BOLD));
		title42.setLeading(30);
		Section section42 = chapter4.addSection(title42);
		section42.setNumberDepth(0);
		Paragraph p_42 = new Paragraph("待发展项是您得分最抵的三个评估项", getFont(title_3,Font.NORMAL));
		p_411.setLeading(30);
		section42.add(p_411);
		section42.add(new Paragraph("\n"));
		addWorskQlist(section42,param);
		
		doc.add(chapter4);
		
		
		
		Paragraph title5 = new Paragraph("五、认知差异", getFont(title_1,Font.BOLD));
		title5.setLeading(30);
		Chapter chapter5 = new Chapter(title5, 1);
		chapter5.setNumberDepth(0);
		chapter5.add(new Paragraph("\n"));
		addXy(chapter5,param);
		addKeyValueToChapter(chapter5,"读图指引：", "(注:他评为不包含自评分值的其余层级加权值。)",title_3);
		addKeyValueToChapter(chapter5, "读图指引：", "(注:他评为不包含自评分值的其余层级加权值。)",title_3);
		addKeyValueToChapter(chapter5, "第一象限(优势共识区):", "自评大于等于自评均分， 且他评大于等于他评均分的维度。",title_3);
		addKeyValueToChapter(chapter5, "第二象限(潜能区):", "自评低于自评均分， 且他评高于他评均分的维度。",title_3);
		addKeyValueToChapter(chapter5, "第三象限(待发展共识区):", "自评低于自评均分， 且他评低于他评均分的维度。",title_3);
		addKeyValueToChapter(chapter5, "第四象限(盲区):", "自评大于等于自评均分， 且他评低于他评均分的维度。",title_3);
		
		
		Paragraph title51 = new Paragraph("认知差异较大的", getFont(title_3,Font.BOLD));
		title51.setLeading(30);
		Section section51 = chapter5.addSection(title51);
		section51.setNumberDepth(0);
		section51.add(new Paragraph("\n"));
		addRenZhi(section51,param);
		doc.add(chapter5);
		
		
		Paragraph title6 = new Paragraph("六、单项总结", getFont(title_1,Font.BOLD));
		title6.setLeading(30);
		Chapter chapter6 = new Chapter(title6, 1);
		chapter6.setNumberDepth(0);
		
		add_6(chapter6,param,title_2);
		
//		Paragraph title61 = new Paragraph("1、职业素养", getFont(title_2,Font.BOLD));
//		title61.setLeading(30);
//		Section section61 = chapter6.addSection(title61);
//		section61.setNumberDepth(0);
//		
//		Paragraph title62 = new Paragraph("2、知识技能", getFont(title_2,Font.BOLD));
//		title62.setLeading(30);
//		Section section62 = chapter6.addSection(title62);
//		section62.setNumberDepth(0);
		
		doc.add(chapter6);
		
		Paragraph title7 = new Paragraph("七、开放项评语", getFont(title_1,Font.BOLD));
		title7.setLeading(30);
		Chapter chapter7 = new Chapter(title7, 1);
		chapter7.setNumberDepth(0);
		
		
		doc.add(chapter7);
		
	}



	private static void add_6(Chapter chapter6, Map<String, Object> param,float title_2) throws DocumentException, IOException {
		java.util.List<Map<String,Object>> qtScoreList = (java.util.List<Map<String, Object>>) param.get("qtScoreList");
		java.util.List<Map<String,Object>> questionScoreList = (java.util.List<Map<String, Object>>) param.get("questionScoreList");
		java.util.List<Map<String,Object>> scoreForm = (java.util.List<Map<String, Object>>) param.get("scoreForm");
		int index =1;
		for(Map<String,Object> qt : qtScoreList) {
			String qtId = qt.get("qtId").toString();
			String qtName = qt.get("qtName").toString();
			String qtZhScore = qt.get("qtZhScore")==null?"":qt.get("qtZhScore").toString();
			String qtZpScore = qt.get("qtZpScore")==null?"":qt.get("qtZpScore").toString();
			String qtAvgScore = qt.get("qtAvgScore")==null?"":qt.get("qtAvgScore").toString();
			Paragraph title61 = new Paragraph((index++)+"、"+qtName, getFont(title_2,Font.BOLD));
			title61.setLeading(30);
			Section section61 = chapter6.addSection(title61);
			section61.setNumberDepth(0);
			
			section61.add(new Paragraph("\n"));
			PdfPTable t = new PdfPTable(3);
			t.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(getCell("综合得分"));
			t.addCell(getCell("自评得分"));
			t.addCell(getCell("本阶段平均分"));
			t.addCell(getCell(qtZhScore));
			t.addCell(getCell(qtZpScore));
			t.addCell(getCell(qtAvgScore));
			section61.add(t);
			
			
			Paragraph p = new Paragraph("         评估项明细", getFont(9,Font.NORMAL));
			p.setLeading(30);
//			section61.add(new Paragraph("\n"));
			section61.add(new Paragraph(p));
			section61.add(new Paragraph("\n"));
			
			float[] widths = new float[] {600,100,100,100,100,100,100};
			PdfPTable t2 = new PdfPTable(4+scoreForm.size());
			t2.setWidths(widths);
			t2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t2.addCell(getCell("评估项"));
			for(Map<String,Object> sf : scoreForm) {
				String pRoleName = sf.get("pRoleName").toString();
				t2.addCell(getCell(pRoleName));
			}
			t2.addCell(getCell("综合得分"));
			t2.addCell(getCell("自评得分"));
			t2.addCell(getCell("平均分"));
			for(Map<String,Object> q : questionScoreList) {
				String qtId2= q.get("qtId").toString();
				if(!qtId.equals(qtId2)) {
					continue;
				}
				String qContent = q.get("qContent").toString();
				String qZhScore = q.get("qZhScore")==null?" ":q.get("qZhScore").toString();
				String qZpScore = q.get("qZpScore")==null?" ":q.get("qZpScore").toString();
				String qAvgScore = q.get("qAvgScore")==null?" ":q.get("qAvgScore").toString();
				
				PdfPCell content = getCell(qContent);
//				content.set
				t2.addCell(content);
				for(Map<String,Object> sf : scoreForm) {
					String pRoleName = sf.get("pRoleName").toString();
					String pRoleId = sf.get("pRoleId").toString();
					String pScore = q.get(pRoleId)==null?" ":q.get(pRoleId).toString();
					t2.addCell(getCell(pScore));
				}
				t2.addCell(getCell(qZhScore));
				t2.addCell(getCell(qZpScore));
				t2.addCell(getCell(qAvgScore));
			}
			section61.add(t2);
		}
	}



	private static void addKeyValueToChapter(Chapter chapter5, String str1, String str2,float size) throws DocumentException, IOException {
		Paragraph p = new Paragraph();
		Chunk chunk1 = new Chunk(str1, getFont(size,Font.BOLD));
		Chunk chunk2 = new Chunk(str2, getFont(size,Font.NORMAL));
		p.add(chunk1);
		p.add(chunk2);
		p.setLeading(30);
		chapter5.add(p);
	}



	private static void addXy(Chapter section51, Map<String, Object> param) throws DocumentException, IOException {
		PdfPTable t = new PdfPTable(2);
		String xy_1=param.get("xy_1").toString();
		String xy_2=param.get("xy_2").toString();
		String xy_3=param.get("xy_3").toString();
		String xy_4=param.get("xy_4").toString();
		t.addCell(getCell(xy_2));
		t.addCell(getCell(xy_1));
		t.addCell(getCell(xy_3));
		t.addCell(getCell(xy_4));
		section51.add(t);
	}



	private static void addRenZhi(Section section51, Map<String, Object> param) throws DocumentException, IOException {
		java.util.List<Map<String, Object>> ll = (java.util.List<Map<String, Object>>) param.get("qCognitiveDifferencesList");
		PdfPTable t = new PdfPTable(5);
		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.addCell(getCell("评估维度"));
		t.addCell(getCell("评估项"));
		t.addCell(getCell("综合得分"));
		t.addCell(getCell("阶段均分"));
		t.addCell(getCell("差值"));
		for(Map<String, Object> u : ll) {
			t.addCell(getCell(u.get("qtName").toString()));
			t.addCell(getCell(u.get("qContent").toString()));
			t.addCell(getCell(u.get("qZhScore").toString()));
			t.addCell(getCell(u.get("qZpScore").toString()));
			t.addCell(getCell(u.get("qZhScore_qZpScore").toString()));
		}
		section51.add(t);
	}



	private static void addWorskQlist(Section section42, Map<String, Object> param) throws DocumentException, IOException {
		java.util.List<Map<String, Object>> ll = (java.util.List<Map<String, Object>>) param.get("worstQList");
		PdfPTable t = new PdfPTable(5);
		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.addCell(getCell("评估维度"));
		t.addCell(getCell("评估项"));
		t.addCell(getCell("综合得分"));
		t.addCell(getCell("阶段均分"));
		t.addCell(getCell("差值"));
		for(Map<String, Object> u : ll) {
			t.addCell(getCell(u.get("qtName").toString()));
			t.addCell(getCell(u.get("qContent").toString()));
			t.addCell(getCell(u.get("qZhScore").toString()));
			t.addCell(getCell(u.get("qAvgScore").toString()));
			t.addCell(getCell(u.get("qZhScore_qAvgScore").toString()));
		}
		section42.add(t);
	}



	private static void addBestQlist(Section section41, Map<String, Object> param) throws DocumentException, IOException {
		java.util.List<Map<String, Object>> ll = (java.util.List<Map<String, Object>>) param.get("bestQList");
		PdfPTable t = new PdfPTable(5);
		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.addCell(getCell("评估维度"));
		t.addCell(getCell("评估项"));
		t.addCell(getCell("综合得分"));
		t.addCell(getCell("阶段均分"));
		t.addCell(getCell("差值"));
		for(Map<String, Object> u : ll) {
			t.addCell(getCell(u.get("qtName").toString()));
			t.addCell(getCell(u.get("qContent").toString()));
			t.addCell(getCell(u.get("qZhScore").toString()));
			t.addCell(getCell(u.get("qAvgScore").toString()));
			t.addCell(getCell(u.get("qZhScore_qAvgScore").toString()));
		}
		section41.add(t);
	}



	private static void set_4_0(Chapter doc, Map<String, Object> param) throws DocumentException, IOException {
		String bestQtListStr = param.get("bestQtListStr").toString();
		String worstQtListStr = param.get("worstQtListStr").toString();
		
		PdfPTable t = new PdfPTable(2);
		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.addCell(getCell("在他人眼中，您在以下能力最优秀"));
		t.addCell(getCell("在他人眼中，您在以下能力需提升"));
		t.addCell(getCell(bestQtListStr));
		t.addCell(getCell(worstQtListStr));
		doc.add(t);
	}



	private static void addJdDetail(Section section32, Map<String, Object> param) throws DocumentException, IOException {
		java.util.List<java.util.List<String>> ll = (java.util.List<java.util.List<String>>) param.get("jdDetailList");
		PdfPTable t = new PdfPTable(ll.get(0).size());
		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		for(java.util.List<String> l : ll) {
			for(String str : l) {
				t.addCell(getCell(str));
			}
		}
		section32.add(t);
	}


	private static void addHeaders(PdfPTable t, String[] headers, String[] headersKey,
			java.util.List<Map<String, Object>> sList) throws DocumentException, IOException {
		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		for(int i=0;i<headers.length;i++) {
			t.addCell(getCell(headers[i]));
		}
		for(Map<String, Object> u : sList) {
			for(int i=0;i<headersKey.length;i++) {
				t.addCell(getCell(u.get(headersKey[i]).toString()));
			}
		}
	}



	private static PdfPCell getCell(String content) throws DocumentException, IOException {
		Paragraph  p = new Paragraph ((content), getFont());
		p.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell();
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(p);
//		cell.setBorderColor(BORDER_COLOR);
//		cell.setBackgroundColor(HCELL_BACKGROUNDCOLOR);
//		cell.setBorderWidthTop(TABLE_BORDER_WIDTH);
//		cell.setBorderWidthBottom(TABLE_BORDER_WIDTH);
//		cell.setBorderWidthLeft(TABLE_BORDER_WIDTH);
//		cell.setBorderWidthRight(TABLE_BORDER_WIDTH);
		return cell;
	}



	private static java.util.List<Map<String,Object>> getSList() {
		java.util.List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		Map<String,Object> i = new HashMap<>();
		i.put("playerRoleName", "指导医师");
		i.put("playerCount", 10);
		i.put("weight", "30%");
		l.add(i);
		
		i = new HashMap<>();
		i.put("playerRoleName", "护理人员");
		i.put("playerCount", 5);
		i.put("weight", "70%");
		l.add(i);
		return l;
	}





	private static void addIntroduction(Document doc) throws DocumentException, IOException {
		Paragraph title1 = new Paragraph("前言", getFont(20,Font.BOLD));
		title1.setLeading(30);
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);
		
		Paragraph p1 = new Paragraph("这是一份关于您的360度评估反馈报告，"
				+ "汇总了您工作密切相关的人员对您工作表现的看法。"
				+"通过这种多角度的反馈，"
				+"希望您更全面地认识自己，"
				+"扬长补短，持续成长！"
				,getFont());
		p1.setLeading(30);
		chapter1.add(p1);
		doc.add(chapter1);
		
		nextLine(doc, 2);
		
		Paragraph title2 = new Paragraph("此报告将帮助您", getFont(16,Font.BOLD));
		nextLine(doc, 1);
//		Chapter chapter2 = new Chapter(title2, 1);
		doc.add(title2);
		addParagraph(doc, "了解周围的人，基于日常的行为表现，对您的工作行为的评价；");
		addParagraph(doc, "将您对自己能力的认知与其他人的认知进行比较；");
		addParagraph(doc, "确定您的优势领域，在后续工作中更积极的展现；");
		addParagraph(doc, "确定您的待发展领域，在后续工作中制定并实施发展计划；");
		
		nextLine(doc, 2);
		
		Paragraph title3 = new Paragraph("理解此份报告", getFont(16,Font.BOLD));
		nextLine(doc, 1);
//		Chapter chapter3 = new Chapter(title3, 1);
		doc.add(title3);
		addParagraph(doc, "保持开放的心态，而不是防御");
		addParagraph(doc, "请记住每个人都有优势与待发展领域");
		addParagraph(doc, "这些反馈所评价的是您的工作行为，而非针对个人");
		addParagraph(doc, "听取这些反馈，尽量理解其他人的观点");
		addParagraph(doc, "认真地考量这些反馈信息，不要将他们拒之门外");
		addParagraph(doc, "表达出您对这些反馈的想法与感受");
	}



	private static void addParagraph(Document doc,String content) throws DocumentException, IOException {
		Paragraph p = new Paragraph(content,getFont());
		p.setLeading(30);
		doc.add(p);
//		nextLine(doc,1);
	}



	private static void addCatalog(Document doc) {
//		 Paragraph title1 = new Paragraph("第一章", getFont());
//         Chapter chapter1 = new Chapter(title1, 1);
//         chapter1.setNumberDepth(0);
//        /** 创建章节中的小节 */
//         Paragraph title11 = new Paragraph("表格的添加", FontChinese);
//         Section section1 = chapter1.addSection(title11);
	}



	private static void addCover(Document doc,Map<String,Object> param) throws DocumentException, IOException {
		nextLine(doc,5);
		
		Font font = getFont(50,Font.BOLD,BaseColor.GREEN);
		Paragraph title1 = new Paragraph("北京医视界医院", font);
		title1.setAlignment(Element.ALIGN_CENTER);
		doc.add(title1);
		
		nextLine(doc,2);
		
		Paragraph title2 = new Paragraph("360评估报告", getFont(30,Font.BOLD));
		title2.setAlignment(Element.ALIGN_CENTER);
		doc.add(title2);
		
		nextLine(doc,10);
		
		addKeyValue(doc,"姓          名：",param.get("userName")==null?"":param.get("userName").toString());
		addKeyValue(doc,"身          份：",param.get("roleName")==null?"":param.get("roleName").toString());
		addKeyValue(doc,"分          数：",param.get("zhScore")==null?"":param.get("zhScore").toString());
		addKeyValue(doc,"部          门：",param.get("deptName")==null?"":param.get("deptName").toString());
		addKeyValue(doc,"专          业：",param.get("majorName")==null?"":param.get("majorName").toString());
		addKeyValue(doc,"报告时间：",param.get("createDate")==null?"":param.get("createDate").toString());
	}
	



	private static void addKeyValue(Document doc, String str1, String str2) throws DocumentException, IOException {
		Paragraph p = new Paragraph();
		Chunk chunk1 = new Chunk(str1, getFont(15,Font.BOLD));
		Chunk chunk2 = new Chunk(str2, getFont(15,Font.NORMAL));
		p.add(chunk1);
		p.add(chunk2);
		p.setLeading(30);
		doc.add(p);
	}



	private static void nextLine(Document doc, int rowsCount) throws DocumentException {
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=rowsCount;i++) {
			sb.append("\n");
		}
		doc.add(new Paragraph(sb.toString()));
	}



	private static Font getFont() throws DocumentException, IOException {
		return getFont(9f,Font.NORMAL,null);
	}
	
	private static Font getFont(float size,int style) throws DocumentException, IOException {
		return getFont(size,style,null);
	}



	private static Font getFont(float size,int style,BaseColor baseColor) throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(bfChinese, size, style,baseColor);
		return FontChinese;
	}




	public static int countInString(String str1, String str2) {  
	        int total = 0;  
	        for (String tmp = str1; tmp != null && tmp.length() >= str2.length();){  
	          if(tmp.indexOf(str2) == 0){  
	            total++;  
	            tmp = tmp.substring(str2.length());  
	          }else{  
	            tmp = tmp.substring(1);  
	          }  
	        }  
	        return total;  
	    } 
	
	
	public static Map<String,Object> getData(){
		Map<String,Object> m = new HashMap<>();
		m.put("userName", "张三");
		m.put("roleName", "住院医师");
		m.put("majorName", "内科");
		m.put("deptName", "科室1");
		m.put("createDate", "20190718");
		m.put("zhScore", "4.5");
		m.put("avgScore", "4.3");
		m.put("rank", "20");
		m.put("rankPerc", "40%");
		m.put("bestQtListStr", "教学能力，搞笑能力");
		m.put("worstQtListStr", "排查问题能力");
		m.put("xy_1", "象限1");
		m.put("xy_2", "象限2");
		m.put("xy_3", "象限3");
		m.put("xy_4", "象限4");
		
		
		//二、分数构成
		java.util.List<Map<String,Object>> scoreForm = new ArrayList<>();
		Map<String,Object> w = new HashMap<>();
		w.put("pRoleId", "4");
		w.put("pRoleName", "住院医师");
		w.put("pCount", "11");
		w.put("weight", "40%");
		scoreForm.add(w);
		w = new HashMap<>();
		w.put("pRoleId", "5");
		w.put("pRoleName", "指导医师");
		w.put("pCount", "15");
		w.put("weight", "30%");
		scoreForm.add(w);
		w = new HashMap<>();
		w.put("pRoleId", "6");
		w.put("pRoleName", "护理人员");
		w.put("pCount", "3");
		w.put("weight", "30%");
		scoreForm.add(w);
		m.put("scoreForm", scoreForm);
		
		//题-----没用
		java.util.List<Map<String,Object>> questionScoreList = new ArrayList<>();
		w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "职业素养");
		w.put("qId", "1");
		w.put("qContent", "你大爷是我啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
		w.put("4", "3.5");
		w.put("5", "4");
		w.put("qZhScore", "4");
		w.put("qZpScore", "5");
		w.put("qAvgScore", "3");
		w.put("qZhScore_qZpScore", "-1");
		w.put("qZhScore_qAvgScore", "2");
		questionScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "职业素养");
		w.put("qId", "2");
		w.put("qContent", "你爸是我");
		w.put("4", "4.5");
		w.put("5", "3");
		w.put("qZhScore", "3.5");
		w.put("qZpScore", "4.5");
		w.put("qAvgScore", "4");
		w.put("qZhScore_qZpScore", "-1");
		w.put("qZhScore_qAvgScore", "0.5");
		questionScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "2");
		w.put("qtName", "教学能力");
		w.put("qId", "3");
		w.put("qContent", "你老子还是我");
		w.put("4", "3.5");
		w.put("5", "4");
		w.put("qZhScore", "4.5");
		w.put("qZpScore", "4");
		w.put("qAvgScore", "4");
		w.put("qZhScore_qZpScore", "0.5");
		w.put("qZhScore_qAvgScore", "0.5");
		questionScoreList.add(w);
		m.put("questionScoreList", questionScoreList);
		
		//题型
		java.util.List<Map<String,Object>> qtScoreList = new ArrayList<>();
		w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "职业素养");
		w.put("4", "4");
		w.put("5", "2");
		w.put("qtZhScore", "3");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "2");
		w.put("qtName", "教学能力");
		w.put("4", "3.5");
		w.put("5", "4");
		w.put("qtZhScore", "4.5");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "4");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "3");
		w.put("qtName", "搞笑能力");
		w.put("4", "3");
		w.put("5", "3");
		w.put("qtZhScore", "5");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "4");
		w.put("qtName", "打架能力");
		w.put("4", "5");
		w.put("5", "2");
		w.put("qtZhScore", "2");
		w.put("qtZpScore", "2");
		w.put("qtAvgScore", "3");
		qtScoreList.add(w);
		m.put("qtScoreList", qtScoreList);
		
//		Map<String,Object> qtAvgScore = new HashMap<>();
//		qtAvgScore.put("qtName", "平均分");
//		qtAvgScore.put("4", "3.5");
//		qtAvgScore.put("5", "4");
//		qtAvgScore.put("qZhScore", "4.5");
//		qtAvgScore.put("qZpScore", "4");
//		qtAvgScore.put("qAvgScore", "4");
//		m.put("qtAvgScore", qtAvgScore);
		
		//阶段详情
		java.util.List<java.util.List<String>> ll = new ArrayList<>();
		java.util.List<String> l1 = new ArrayList<>();
		l1.add("评估维度");
		l1.add("带教老师");
		l1.add("综合得分");
		l1.add("自评得分");
		l1.add("阶段均分");
		ll.add(l1);
		l1 = new ArrayList<>();
		l1.add("职业素养");
		l1.add("3");
		l1.add("4");
		l1.add("5");
		l1.add("4");
		ll.add(l1);
		l1 = new ArrayList<>();
		l1.add("职业素养");
		l1.add("2");
		l1.add("3");
		l1.add("4");
		l1.add("5");
		ll.add(l1);
		m.put("jdDetailList", ll);
		
		//优势项
		java.util.List<Map<String,Object>> bestQList = new ArrayList<>();
		w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "职业素养");
		w.put("qContent", "我是你大爷");
		w.put("qZhScore", "5");
		w.put("qZpScore", "5");
		w.put("qAvgScore", "5");
		w.put("qZhScore_qAvgScore", "2");
		bestQList.add(w);
		m.put("bestQList", bestQList);
		
		//待发展项
		java.util.List<Map<String,Object>> worstQList = new ArrayList<>();
		w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "教学能力");
		w.put("qContent", "你妹的");
		w.put("qZhScore", "3");
		w.put("qZpScore", "4");
		w.put("qAvgScore", "3");
		w.put("qZhScore_qAvgScore", "1");
		worstQList.add(w);
		m.put("worstQList", worstQList);
		
		
		java.util.List<Map<String,Object>> qCognitiveDifferencesList = new ArrayList<>();
		w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "教学能力");
		w.put("qContent", "你妹的");
		w.put("qZhScore", "3");
		w.put("qZpScore", "4");
		w.put("qAvgScore", "3");
		w.put("qZhScore_qZpScore", "1");
		qCognitiveDifferencesList.add(w);
		m.put("qCognitiveDifferencesList", qCognitiveDifferencesList);
		
		return m;
	}
	public static void main(String[] args) throws Exception {
//		test(getData(),"F://export/","test.pdf");
		test(getData(),"/data/360server","test.pdf");
	}
}
