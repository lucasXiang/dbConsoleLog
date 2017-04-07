package com.tech.member.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataReadedInExcel {
	
	public static HSSFWorkbook writeList2Excel(List list){
		//创建操作Excel的HSSFWorkbook对象
		HSSFWorkbook excel= new HSSFWorkbook();
		//Excel中的一个sheet（工作表)对应着java中的一个HSSFSheet对象，利用HSSFWorkbook对象可以创建一个HSSFSheet对象
		HSSFSheet sheet = excel.createSheet("sheet1");
		//给表格添加表头
		createFirstRow(sheet.createRow(0),(Map)list.get(0));
		//给表格添加内容
		createContextRow(sheet,list);
		
		return excel;
	}
	public static void createFirstRow(HSSFRow firstRow,Map firstCellNames){
		Set keys=firstCellNames.keySet();
		Iterator it=keys.iterator();
		int i=0;
		while(it.hasNext()){
			Object obj=it.next();
			if(obj instanceof String)
				firstRow.createCell(i).setCellValue((String)obj);
			else if (obj instanceof BigDecimal)
				firstRow.createCell(i).setCellValue(((BigDecimal)obj).intValue());
			else if (obj==null)
				firstRow.createCell(i).setCellValue("null");
			else 
				firstRow.createCell(i).setCellValue("");
			i++;
		}
	}
	public static void createContextRow(HSSFSheet sheet,List list){
		for(int i=0;i<list.size();i++){
			HSSFRow row=sheet.createRow(i+1);
			Map map=(Map)list.get(i);
			Collection collection=map.values();
			Iterator it = collection.iterator();
			int j=0;
			while(it.hasNext()){
				Object obj=it.next();
				if(obj instanceof String)
					row.createCell(j).setCellValue((String)obj);
				else if (obj instanceof BigDecimal)
					row.createCell(j).setCellValue(((BigDecimal)obj).intValue());
				else if (obj==null)
					row.createCell(j).setCellValue("null");
				else 
					row.createCell(j).setCellValue("");
				j++;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		List list=new ArrayList();
		Map map=new HashMap();
		map.put("sfdsfgdsfgdf", "11");
		map.put("sdfg", "22");
		list.add(map);
		File file=new File("d:\\1.xls");
		HSSFWorkbook excel=writeList2Excel(list);
		OutputStream out=new FileOutputStream(file);
		excel.write(out);
	}
}
