package com.tech.member.util;

import java.util.Map;

public class JSonUtil {
	public static String toJson(boolean success, Map<String, String> jsonMap) {
        StringBuffer buffer = new StringBuffer();
        if (success) {
            buffer.append("{success:true");
        } else {
            buffer.append("{success:false");
        }


        if (jsonMap.size() > 0) {
            buffer.append(",");
            for (String key : jsonMap.keySet()) {
                if (!key.equals("class"))
                    buffer.append(key + " : '" + jsonMap.get(key) + "',");
            }
            // 去掉最后一个','
            buffer.deleteCharAt(buffer.length() - 1);
        }
        buffer.append("}");
        return buffer.toString();
    }
	
	public static void main(String[] args) {
		
	}
	
}
