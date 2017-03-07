/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: Tables.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.Util 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月6日 下午4:46:40 
 * @version: V1.0   
 */
package com.koolbao.maptest.Util;

/**
 * @ClassName: Tables
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月6日 下午4:46:40
 */
public class Tables {

	public void getDate(java.util.Map<String, String> map) {
		if ("1".equals(map.get("table"))) {
			map.put("dt", "0000");
		}
	}

	public String getType(java.util.Map<String, String> map) {
		String tableName = "";
		tableName = map.get("table");
		return getType(tableName);
	}

	public String getType(String tableName) {
		if ("1".equals(tableName)) {
			return "list";
		}
		return "";
	}
}
