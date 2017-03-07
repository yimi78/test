package com.koolbao.maptest.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: Test.java 
 * @Prject: test
 * @Package:  
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年2月24日 下午5:25:19 
 * @version: V1.0   
 */

/**
 * @ClassName: Test
 * @Description: TODO
 * @author: lhq
 * @date: 2017年2月24日 下午5:25:19
 */
public class Expr {

	List<Map> exprlists = null; // 规则集合体
	Gson gson = new Gson(); // gson
	Tables tables = new Tables();

	public java.util.Map<String, String> getTag(Map<String, Object> data) {
		if (exprlists == null) {
			return null;
		}
		java.util.Map<String, String> result = new HashMap<String, String>();
		String defaults = "";
		String tagName = "";
		ScriptEngine jse = new ScriptEngineManager()
				.getEngineByName("JavaScript");

		for (Map exprInfo : exprlists) {
			List<Map> exprList = (List<Map>) exprInfo.get("values");
			defaults = exprInfo.get("default") + "";
			tagName = exprInfo.get("name") + "";

			for (Map<String, String> expr : exprList) {
				// 判断是否含有参数，没有返回默认值

				String paramText = expr.get("param");
				if (StringUtils.isBlank(paramText)) {
					continue;
				}

				// 参数列表
				String[] paramlist = paramText.split(",");
				// 没有需要的参数
				boolean isBreak = false;
				for (String item : paramlist) {
					if (StringUtils.isBlank(item)
							|| getMapDate(data, item) == null) {
						isBreak = true;
						break;
					}
				}
				if (isBreak) {
					continue;
				}

				String condition = expr.get("condition");
				String resultExpr = expr.get("resultExpr");
				for (String item : paramlist) {
					String repetition = "\\{" + item + "\\}";
					condition = condition
							.replaceAll(repetition, data.get(item));
					resultExpr = resultExpr.replaceAll(repetition,
							data.get(item));
				}
				try {
					if ("true".equals(jse.eval(condition).toString())) {
						defaults = jse.eval(resultExpr).toString();
						break;
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(defaults)) {
				result.put(tagName, defaults);
			}
		}

		return result;
	}

	/**
	 * 转化规则文件
	 * 
	 * @Title: getExprDataMap
	 * @Description: TODO
	 * @param textexprlists
	 * @return
	 * @throws IOException
	 * @return: List<Object>
	 */
	public void getExprDataMap(List<String> textexprlists) {
		List<Map> xprlists = new ArrayList<Map>();
		Map<String, Object> expr = new HashMap<String, Object>();
		for (String item : textexprlists) {
			expr = gson.fromJson(item, expr.getClass());
			xprlists.add(expr);
		}
		this.exprlists = xprlists;
	}

	public Map<String, Object> getMapDate(Map<String, Object> data, String item) {
		try {
			String[] table = item.split("\\.");
			if (!"list".equals(tables.getType(table[0]))) {
				if (((Map) data.get(table[0])).containsKey(table[1])) {
					return data;
				}
			} else {
				List<Map> list = (List<Map>) data.get(table[0]);
				Iterator<Map> items = list.iterator();
				while (items.hasNext()) {
					Map maps = items.next();
					if (!maps.containsKey(table[1])) {
						items.remove();
					}
				}
				data.put(table[0], list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}
}
