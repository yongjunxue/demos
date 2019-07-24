package com.jfreechart.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class MyJFreeChart {
	public static java.util.List<Map<String,Object>> getData() {
		java.util.List<Map<String,Object>> qtScoreList = new ArrayList<>();
		Map<String,Object> w = new HashMap<>();
		w.put("qtId", "1");
		w.put("qtName", "职业素养");
		w.put("qtZhScore", "3");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "2");
		w.put("qtName", "教学能力");
		w.put("qtZhScore", "4.5");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "4");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "3");
		w.put("qtName", "搞笑能力");
		w.put("qtZhScore", "5");
		w.put("qtZpScore", "3");
		w.put("qtAvgScore", "4");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "4");
		w.put("qtName", "打架能力");
		w.put("qtZhScore", "2");
		w.put("qtZpScore", "2");
		w.put("qtAvgScore", "3");
		qtScoreList.add(w);
		w = new HashMap<>();
		w.put("qtId", "5");
		w.put("qtName", "沟通能力");
		w.put("qtZhScore", "4");
		w.put("qtZpScore", "4");
		w.put("qtAvgScore", "3");
		qtScoreList.add(w);
		return qtScoreList;
	}
	public static void main(String[] args) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Map<String,Object> map : getData()) {
			String group1 = "综合得分";
			if(map.get("qtZhScore") != null) {
				Double socre = Double.parseDouble( map.get("qtZhScore").toString() );
				String qtId = map.get("qtId").toString();
				String qtName = map.get("qtName").toString();
				dataset.addValue(socre, group1, qtName);
			}
		}
		for(Map<String,Object> map : getData()) {
			String group2 = "自评得分";
			if(map.get("qtZpScore") != null) {
				Double socre = Double.parseDouble( map.get("qtZpScore").toString() );
				String qtId = map.get("qtId").toString();
				String qtName = map.get("qtName").toString();
				dataset.addValue(socre, group2, qtName);
			}
		}
		for(Map<String,Object> map : getData()) {
			String group3 = "阶段平均分";
			if(map.get("qtAvgScore") != null) {
				Double socre = Double.parseDouble( map.get("qtAvgScore").toString() );
//				String qtId = map.get("qtId").toString();
				String qtName = map.get("qtName").toString();
				dataset.addValue(socre, group3, qtName);
			}
		}

		SpiderWebPlot spiderwebplot = new SpiderWebPlot(dataset);

		JFreeChart jfreechart = new JFreeChart("", TextTitle.DEFAULT_FONT, spiderwebplot, false);
		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.TOP);
		jfreechart.addSubtitle(legendtitle);
		try {
			ChartUtilities.writeChartAsJPEG(new FileOutputStream("f:/export/tu1.jpeg"), jfreechart, 800, 600);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
