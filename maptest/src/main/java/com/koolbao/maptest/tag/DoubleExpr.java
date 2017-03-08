package com.koolbao.maptest.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;

import com.google.gson.Gson;
import com.koolbao.maptest.model.DoubleRule;

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
public class DoubleExpr {
	public List<DoubleRule> doubleRuleLists = new ArrayList<DoubleRule>(); // 规则集合体
	Gson gson = new Gson(); // gson

	/**
	 * 得到标签
	 * 
	 * @Title: getTag
	 * @Description: TODO
	 * @param timeTag
	 * @param noTimeTag
	 * @return
	 * @return: Map<String,String>
	 */
	public List<Map> getTag(ScriptEngine jse, Map<String, String> timeTag) {
		if (doubleRuleLists == null) {
			return null;
		}

		List<Map> result = new ArrayList<Map>();

		for (DoubleRule exprInfo : doubleRuleLists) {
			if (exprInfo.getResult(jse, timeTag)) {
				result.add(exprInfo.getResult());
			}
		}
		return result;
	}
}
