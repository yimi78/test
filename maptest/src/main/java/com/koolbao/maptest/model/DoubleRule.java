/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: DoubleRule.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.model 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月8日 下午1:42:41 
 * @version: V1.0   
 */
package com.koolbao.maptest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;

/**
 * 标签二次规则
 * 
 * @ClassName: DoubleRule
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月8日 下午1:42:41
 */
public class DoubleRule {

	public String tagType = ""; // 标签组
	public String tagName = ""; // 标签名字
	public String tagValue = ""; // 标签值
	public String type = ""; // 结果类型
	public List<Map> values = null; // 具体规则
	public List<OpRule> rules = new ArrayList<OpRule>(); // 规则
	public Map<String, Object> resultMap = new HashMap<String, Object>();

	public DoubleRule(Map<String, Object> map) {
		this.setTagType(map.get("tagType") + "");
		this.setTagName(map.get("tagName") + "");
		this.setTagValue(map.get("tagValue") + "");
		this.setType(map.get("type") + "");
		this.setValues((List<Map>) map.get("values"));
		for (Map maps : values) {
			this.rules.add(new OpRule(maps));
		}
		resultMap.put("tagType", map.get("tagType"));
		resultMap.put("tagname", map.get("tagname"));
		resultMap.put("tagValue", map.get("tagValue"));
	}

	// 判断结果
	public boolean getResult(ScriptEngine jse, Map<String, String> timeTag) {
		resultMap.put("key", timeTag.get("key"));
		for (OpRule item : rules) {
			if (!item.getResult(jse, timeTag)) {
				return false;
			}
		}
		return true;
	}

	// 返回结果
	public Map<String, Object> getResult() {
		return resultMap;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Map> getValues() {
		return values;
	}

	public void setValues(List<Map> values) {
		this.values = values;
	}

	public List<OpRule> tagValue() {
		return rules;
	}

	public void setRules(List<OpRule> rules) {
		this.rules = rules;
	}

}
