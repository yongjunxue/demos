package com.jfreechart.demo;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.DefaultCategoryDataset;

public class MyBarChart {
	public static void main(String[] args) {
		DefaultCategoryDataset dataSet = getDataSet();
		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
	       //设置标题字体  
	       standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
//	       //设置图例的字体  
	       standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
//	       //设置轴向的字体  
	       standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
	       //应用主题样式  
	       ChartFactory.setChartTheme(standardChartTheme);  
		JFreeChart jf = ChartFactory.createBarChart(null, null, null, dataSet);
//		JFreeChart jfreechart = new JFreeChart("", TextTitle.DEFAULT_FONT, jf, false);
		try {
			ChartUtilities.writeChartAsJPEG(new FileOutputStream("f://export/bar.jpeg"), jf, 800, 600);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static DefaultCategoryDataset getDataSet() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		java.util.List<Map<String,Object>> zhScoreChange_4 = new ArrayList<>();
		Map<String,Object> w = new HashMap<>();
		w.put("reportName", "报告2");
		w.put("qtId", "1");
		w.put("qtName", "职业素养");
		w.put("qtZhScore", "3");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		zhScoreChange_4.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告2");
		w.put("qtId", "2");
		w.put("qtName", "教学能力");
		w.put("qtZhScore", "4.5");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "4");
		zhScoreChange_4.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告2");
		w.put("qtId", "3");
		w.put("qtName", "搞笑能力");
		w.put("qtZhScore", "5");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		zhScoreChange_4.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告2");
		w.put("qtId", "4");
		w.put("qtName", "打架能力");
		w.put("qtZhScore", "2");
		w.put("qtZpScore", "2");
		w.put("qtAvgScore", "3");
		zhScoreChange_4.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告2");
		w.put("qtId", "5");
		w.put("qtName", "沟通能力");
		w.put("qtZhScore", "4");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "3");
		zhScoreChange_4.add(w);
		
		
		java.util.List<Map<String,Object>> zhScoreChange_3 = new ArrayList<>();
//		w = new HashMap<>();
//		w.put("qtId", "1");
//		w.put("qtName", "职业素养");
//		w.put("qtZhScore", "3");
//		w.put("qtZpScore", "3");
//		w.put("qtAvgScore", "4");
//		zhScoreChange_3.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告1");
		w.put("qtId", "2");
		w.put("qtName", "教学能力");
		w.put("qtZhScore", "4.5");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "4");
		zhScoreChange_3.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告1");
		w.put("qtId", "3");
		w.put("qtName", "搞笑能力");
		w.put("qtZhScore", "5");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		zhScoreChange_3.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告1");
		w.put("qtId", "4");
		w.put("qtName", "打架能力");
		w.put("qtZhScore", "2");
		w.put("qtZpScore", "2");
		w.put("qtAvgScore", "3");
		zhScoreChange_3.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告1");
		w.put("qtId", "5");
		w.put("qtName", "沟通能力");
		w.put("qtZhScore", "4");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "3");
		zhScoreChange_3.add(w);
		w = new HashMap<>();
		w.put("reportName", "报告1");
		w.put("qtId", "6");
		w.put("qtName", "死磕能力");
		w.put("qtZhScore", "3");
		w.put("qtZpScore", "1");
		w.put("qtAvgScore", "2");
		zhScoreChange_3.add(w);
		
		for(Map<String,Object> map : zhScoreChange_4 ) {
			String reportName = map.get("reportName").toString();
			if(map.get("qtZhScore") != null) {
				Double socre = Double.parseDouble( map.get("qtZpScore").toString() );
				String qtName = map.get("qtName").toString();
				dataSet.addValue(socre, reportName, qtName);
			}
		}
		for(Map<String,Object> map : zhScoreChange_3 ) {
			String reportName = map.get("reportName").toString();
			if(map.get("qtZhScore") != null) {
				Double socre = Double.parseDouble( map.get("qtZpScore").toString() );
				String qtName = map.get("qtName").toString();
				dataSet.addValue(socre, reportName, qtName);
			}
		}
		
		return dataSet;
	}

}
