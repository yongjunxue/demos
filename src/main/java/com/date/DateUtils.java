package com.date;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.alibaba.fastjson.JSONObject;
import com.httpclient.HttpClientUtil2;

/**
 * 日期处理
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * 
	 * @param date 日期
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * 
	 * @param date 日期
	 * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param strDate 日期字符串
	 * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (strDate != null) {
			return null;
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间戳转字符串
	 * @param timestamp
	 * @return
	 */
	public static String timestampToStr(Timestamp timestamp){
		String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
		return str;
	}

	public static String timestampToStr(Timestamp timestamp,String dateFormat){
		String str = new SimpleDateFormat(dateFormat).format(timestamp);
		return str;
	}

	/**
	 * 字符串转时间戳
	 * @param time
	 * @param dateFormat
	 * @return
	 */
	public static Timestamp strToTimestamp(String time,String dateFormat){
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp dateSQL = new Timestamp(date.getTime());
		return dateSQL;
	}

	/**
	 * 时间戳转日期
	 * @param timestamp
	 * @return
	 */
	public static Date timestampToDate(Timestamp timestamp){
		Date date = new Date(timestamp.getTime());
		return date;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(Date date1,Date date2,boolean flag)
	{
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		if (flag){
			days ++;
		}
		return days;
	}

	/**
	 * 获取两个日期之间的日期(包含这俩个日期)
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		result.add(start);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		result.add(end);
		return result;
	}

	/**
	 *  添加 或  减去
	 * @return
	 */
	public static Date NumberTime(Date time, Integer unit,Integer number){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(unit,number);
		return calendar.getTime();
	}
	
	/**
	 * @return
	 */
	public static Date parseDate(String s, String patterns){
		Date parse = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINESE);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			parse = format.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
//			throw new RRException("日期格式错误！");
		}
		return parse;
	}

	public static void parseDate(Map<String, Object> params, String pattern) {
		if(params != null) {    //2019-05-04T06:48:47.997Z
			if(params.get("startTime") != null && !params.get("startTime").equals("")) {
				Date s = parseDate(params.get("startTime").toString(),pattern);
				params.put("startTime", format(s,DATE_TIME_PATTERN));
			}
			if(params.get("endTime") != null && !params.get("endTime").equals("")) {
				Date s = parseDate(params.get("endTime").toString(),pattern);
				params.put("endTime", format(s,DATE_TIME_PATTERN));
			}
			if(params.get("startDate") != null && !params.get("startDate").equals("")) {
				Date s = parseDate(params.get("startDate").toString(),pattern);
				params.put("startDate", format(s,DATE_TIME_PATTERN));
			}
		}
	}
	
	/**
	 * 获取北京时间，而不是系统时间
	 * @return
	 */
	public String getBJDate() {
//       JSONObject d =HttpClientUtil2.doGet("http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp");
//       System.out.println(new Date(d.getJSONObject("data").getLong("t")));
       JSONObject d2 =HttpClientUtil2.doGet("http://quan.suning.com/getSysTime.do");
//       System.out.println(d2.getString("sysTime2"));
       return d2.getString("sysTime2");
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(Calendar.getInstance(Locale.CHINA).getTime());
		System.out.println(new Date(System.currentTimeMillis()));
		
//		Date date = new Date(System.currentTimeMillis());
//	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
//	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//	    String BeijingTime = dateFormat.format(date);
//	    System.out.println(BeijingTime);
	    
	    URL url=new URL("http://www.bjtime.cn");//取得资源对象
	       URLConnection uc=url.openConnection();//生成连接对象
	       uc.connect(); //发出连接
	       long ld=uc.getDate(); //取得网站日期时间
	       Date date=new Date(ld); //转换为标准时间对象
	       //分别取得时间中的小时，分钟和秒，并输出
	       System.out.println(date);
	
	       
	       TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
	       GregorianCalendar c = new GregorianCalendar(tz);
	       
	       System.out.println(c.getTime());
	       
//	       if(LOG.isDebugEnabled()){
//	       LOG.debug("current hour:"+(c.get(Calendar.HOUR))); // 显示的是12小时进制；
//	       LOG.debug("current hour:"+(c.get(Calendar.HOUR_OF_DAY ))); // 显示的是24小时进制；
//
//	       LOG.debug("current minite:"+(c.get(Calendar.MINUTE)));
//	       }
	       
	       JSONObject d =HttpClientUtil2.doGet("http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp");
	       System.out.println(new Date(d.getJSONObject("data").getLong("t")));
	       JSONObject d2 =HttpClientUtil2.doGet("http://quan.suning.com/getSysTime.do");
	       System.out.println(d2.getString("sysTime2"));
	}
	
	/**
	 * 获取该月的第一天
	 * @param date
	 * @return
	 */
	public Date getFirstDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.setTime(date);
//		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String first = format.format(c.getTime());
//		System.out.println("===============first:"+first);
		return c.getTime();
	}
	
	/**
	 * 获取改月的最后一天
	 * @param startDate
	 * @param period
	 * @return
	 */
	public Date getEndDate(Date startDate,int period) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance(); 
		ca.setTime(startDate);
		ca.add(Calendar.DAY_OF_WEEK, 1);
		ca.add(Calendar.MONTH, period);
		ca.set(Calendar.DAY_OF_MONTH,0);
//		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(ca.getTime());
//		System.out.println("===============last:"+last);
		return ca.getTime();
	}
	
}
