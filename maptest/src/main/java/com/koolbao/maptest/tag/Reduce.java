/**   
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: Reduce.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest 
 * @Description: TODO
 * @author: lhq   
 * @date: Dec 22, 2016 3:36:08 PM 
 * @version: V1.0   
 */
package com.koolbao.maptest.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.google.gson.Gson;
import com.koolbao.maptest.Util.SqlFomat;
import com.koolbao.maptest.Util.Tables;
import com.koolbao.maptest.model.DoubleRule;

/**
 * @ClassName: Reduce
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:36:08 PM
 */
public class Reduce extends Reducer<Text, Text, Text, Text> {

	DoubleExpr doubleExpr = new DoubleExpr();
	SqlFomat sql = new SqlFomat();
	Gson gson = new Gson();
	Tables tables = new Tables();

	/**
	 * 预处理
	 */
	@SuppressWarnings("deprecation")
	protected void setup(Context context) throws IOException,
			InterruptedException {
		List<String> list = new ArrayList<String>();
		try {
			Path[] path = context.getLocalCacheFiles();
			list = IOUtils.readLines(FileSystem.get(context.getConfiguration())
					.open(path[0]));
			context.getCounter("规则条数", list.size() + "");

			Map<String, Object> expr = new HashMap<String, Object>();
			for (String item : list) {
				expr = gson.fromJson(item, expr.getClass());
				try {
					doubleExpr.doubleRuleLists.add(new DoubleRule(expr));
				} catch (Exception e) {
					context.getCounter("异常", "规则条数").increment(1);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String userId = key.toString(); // 用户id
		Map<String, String> timeTag = new HashMap<String, String>();
		ScriptEngine jse = new ScriptEngineManager()
				.getEngineByName("JavaScript");
		timeTag.put("key", userId);

		// 整理标签数据 tagType + tagname + dt,tagValue
		for (Text item : values) {
			Map<String, Object> map = new HashMap<String, Object>();
			map = gson.fromJson(item.toString(), map.getClass());
			if (map.containsKey("dt")) {
				timeTag.put("{" + map.get("tagType") + "." + map.get("tagname")
						+ "}", map.get("tagValue") + "");
			} else {
				timeTag.put("{" + map.get("tagType") + "." + map.get("tagname")
						+ "}" + map.get("dt"), map.get("tagValue") + "");
			}
		}

		List<Map> result = doubleExpr.getTag(jse, timeTag);// 结果集
		if (result.size() == 0) {
			return;
		}
		for (Map item : result) {
			context.write(new Text(), new Text(gson.toJson(item)));
		}

	}
}
