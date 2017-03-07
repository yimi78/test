/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: SqlFomat.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.Util 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月2日 下午5:29:06 
 * @version: V1.0   
 */
package com.koolbao.maptest.Util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SqlFomat
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月2日 下午5:29:06
 */
public class SqlFomat {

	/**
	 * 将sql转化为map
	 * 
	 * @Title: formate
	 * @Description: TODO
	 * @param item
	 * @return
	 * @return: Map<String,String>
	 */
	public Map<String, String> formate(String item) {
		if (!item.contains("values")) {
			return null;
		}

		try {
			String[] itemss = item.split("[\\(\\)]");
			String head = itemss[1];
			String data = item.split("values")[1].toString().replaceAll(
					"[\\(\\)]", "");
			String[] headList = head.split(",");
			String[] dataList = data.split(",");
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < headList.length; i++) {
				if (StringUtils.isBlank(headList[i].trim())
						|| StringUtils.isBlank(dataList[i].replaceAll("'", "")
								.toString())) {
					continue;
				}
				map.put(headList[i].trim(), dataList[i].replaceAll("'", "")
						.toString());
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(item);
		}
		return null;
	}
}
