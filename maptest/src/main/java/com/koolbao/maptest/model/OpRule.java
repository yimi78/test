/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: OpRule.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.model 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月8日 下午1:45:06 
 * @version: V1.0   
 */
package com.koolbao.maptest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 详细规则
 * 
 * @ClassName: OpRule
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月8日 下午1:45:06
 */
public class OpRule {

	public boolean isDt = false; // 规则
	public String params = ""; // 需要的参数
	public String rule = ""; // 规则
	public String judge = ""; // 判断
	public String dt = ""; // 时间
	public Date beginTime = null; // 时间
	public Date endTime = null; // 时间
	public String groupType = ""; // 操作类型
	public boolean result = false; // 操作类型

	// 返回结果
	public String getResult(ScriptEngine jse, Map<String, String> timeTag)
			throws ScriptException {
		String params = getParams();
		String rule = getRule();
		String jugde = getJudge();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 参数替换
		for (String item : params.split(",")) {
			if (timeTag.containsKey(item)) {
				rule = rule.replace(item, timeTag.get(item));
			}
		}

		if (StringUtils.isBlank(dt) || "null".equals(dt)) {
			return jse.eval(rule + getJudge()).toString();
		} else {
			int index = 0;
			double sum = 0;
			double max = 0;
			double min = 0;
			double tmp = 0;

			Date begin = getBeginTime();
			String time = "";

			while (!begin.after(getEndTime())) {
				String ruleTmp = rule;
				index++;
				time = sdf.format(begin);
				// 参数替换
				try {
					for (String item : params.split(",")) {
						if (timeTag.containsKey(item + time)) {
							ruleTmp = ruleTmp.replace(item,
									timeTag.get(item + time));
						}
					}
					tmp = (double) jse.eval(ruleTmp);
					if (max < tmp) {
						max = tmp;
					}
					if (min > tmp) {
						min = tmp;
					}
					sum += tmp;
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(rule + getJudge());
				}
				begin = DateUtils.addDays(begin, 1);
			}

			Object result = null;
			if ("sum".equals(getGroupType())) {
				result = jse.eval(sum + getJudge());
			} else if ("min".equals(getGroupType())) {
				result = jse.eval(min + getJudge());
			} else if ("max".equals(getGroupType())) {
				result = jse.eval(max + getJudge());
			} else if ("avg".equals(getGroupType())) {
				Double avg = sum / index;
				result = jse.eval(avg + getJudge());
			}

			return result.toString();
		}
	}

	/**
	 * 初始化
	 * 
	 * @Title:OpRule
	 * @Description:TODO
	 * @param map
	 */
	public OpRule(Map map) {
		this.setRule(map.get("rule") + "");
		this.setJudge(map.get("judge") + "");
		this.setDt(map.get("dt") + "");
		this.setParams(map.get("params") + "");
		this.setGroupType(map.get("groupType") + "");

		try {
			if (StringUtils.isNotBlank(dt) && !"null".equals(dt)) {
				isDt = true;
				if (dt.contains(":")) {
					String[] dts = this.dt.split(":");
					this.setBeginTime(DateUtils.parseDate(dts[0],
							new String[] { "yyyy-MM-dd" }));
					this.setEndTime(DateUtils.parseDate(dts[1],
							new String[] { "yyyy-MM-dd" }));
				} else {
					this.setBeginTime(DateUtils.parseDate(dt,
							new String[] { "yyyy-MM-dd" }));
					this.setEndTime(DateUtils.parseDate(dt,
							new String[] { "yyyy-MM-dd" }));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public boolean isDt() {
		return isDt;
	}

	public void setDt(boolean isDt) {
		this.isDt = isDt;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
