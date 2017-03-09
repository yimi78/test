/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: Statistics.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.Util 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月9日 下午4:15:11 
 * @version: V1.0   
 */
package com.koolbao.maptest.Util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: Statistics
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月9日 下午4:15:11
 */
public class Statistics {

	/**
	 * 范围统计
	 * 
	 * @Title: statistics
	 * @Description: TODO
	 * @param list
	 * @param item
	 * @return
	 * @return: String
	 */
	public String statistics(List<Map<String, Object>> list, String item) {

		if (StringUtils.isBlank(item) || !item.contains("#")) {
			return "";
		}
		String[] cols = item.split("#");

		String col = cols[0];
		String symbol = cols[1];
		int index = 0;
		double min = 0;
		double max = 0;
		double sum = 0;
		double agv = 0;
		double tmp = 0;
		for (Map<String, Object> map : list) {
			if (!map.containsKey(col.toUpperCase())) {
				continue;
			}
			++index;
			double itemTmp = Double.parseDouble(map.get(col.toUpperCase())
					.toString());
			if (itemTmp > max) {
				max = itemTmp;
			}
			if (itemTmp < min) {
				itemTmp = min;
			}
			sum += itemTmp;
			++index;
		}

		if ("max".equals(symbol)) {
			return max + "";
		}
		if ("sum".equals(symbol)) {
			return sum + "";
		}
		if ("min".equals(symbol)) {
			return min + "";
		}
		if ("avg".equals(symbol)) {
			return (sum / index) + "";
		}
		return "";
	}
}
