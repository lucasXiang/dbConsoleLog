package com.tech.member.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateUtil {
	/**
	 * 将当前日期转化为指定格式的字符串
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午1:08:42
	 * @param pattern
	 * @return
	 */
	public static String formateDateToString(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	public static void main(String[] args) {
		System.out.println(DateFormateUtil.formateDateToString("yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateFormateUtil.formateDateToString("yyyyMMddHHmmss"));
	}
}
