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
package com.koolbao.maptest.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.google.gson.Gson;
import com.koolbao.maptest.Util.Expr;
import com.koolbao.maptest.Util.SqlFomat;
import com.koolbao.maptest.Util.Tables;

/**
 * @ClassName: Reduce
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:36:08 PM
 */
public class Reduce extends Reducer<Text, Text, Text, Text> {

	Expr expr = new Expr();
	SqlFomat sql = new SqlFomat();
	Gson gson = new Gson();
	Tables tables = new Tables();

	@SuppressWarnings("deprecation")
	protected void setup(Context context) throws IOException,
			InterruptedException {
		List<String> list = new ArrayList<String>();
		try {
			Path[] path = context.getLocalCacheFiles();
			// File file = new File(path);
			// list = FileUtils.readLines(file);
			list = IOUtils.readLines(FileSystem.get(context.getConfiguration())
					.open(path[0]));
			context.getCounter("规则条数", list.size() + "");
			expr.getExprDataMap(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String keys = key.toString(); // 用户id
		Map<String, Object> params = new HashMap<String, Object>();// 参数集
		Map<String, String> result = new HashMap<String, String>(); // 结果集
		result.put("key", keys);

		// 放入数据
		for (Text text : values) {
			Map<String, String> map = new HashMap<String, String>();
			map = gson.fromJson(text.toString(), map.getClass());
			String tableName = map.get("table");
			if ("list".equals(tables.getType(map))) {
				List<Map> table = new ArrayList<Map>();
				if (params.containsKey(tableName)) {
					table = (List<Map>) params.get(tableName);
					params.put(tableName, table);
				}
				table.add(map);
			}
			params.put(tableName, map);
		}

		// 进行统计
		for (Text text : values) {

			java.util.Map<String, String> ss = expr.getTag(null);

			if (ss == null || ss.isEmpty()) {
				context.getCounter("不符合的数据", "不符合的数据").increment(1);
				continue;
			}
			context.getCounter("符合的数据", "符合的数据").increment(1);
			result.putAll(ss);
		}
		if (result.size() > 1) {
			context.write(new Text(""), new Text(gson.toJson(result)));
		}
	}
}
