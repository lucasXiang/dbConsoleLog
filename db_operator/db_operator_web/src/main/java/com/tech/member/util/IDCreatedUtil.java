package com.tech.member.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IDCreatedUtil {
	private static final AtomicInteger integer = new AtomicInteger(0);
	/**
	 * 生成 17 位的主键值
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午1:07:00
	 * @return
	 */
	public static String getId() {
	  long time = System.currentTimeMillis();
	  StringBuilder str = new StringBuilder(20);
	  str.append(DateFormateUtil.formateDateToString("yyyyMMddHHmmss")).append("-");
	  str.append(time);
	  int intValue = integer.getAndIncrement();
	  if (integer.get() >= 10000) {
	   integer.set(0);
	  }
	  if (intValue < 10) {
	   str.append("000");
	  } else if (intValue < 100) {
	   str.append("00");
	  } else if (intValue < 1000) {
	   str.append("0");
	  }
	  
	  return str.append(intValue).toString().substring(0, 32);
	 }
	
	public static void main(String[] args) {
		System.out.println(getId());
	}
}
