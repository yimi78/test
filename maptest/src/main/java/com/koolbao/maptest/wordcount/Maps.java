/**   
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: Map.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest 
 * @Description: TODO
 * @author: lhq   
 * @date: Dec 22, 2016 3:35:26 PM 
 * @version: V1.0   
 */
package com.koolbao.maptest;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.google.gson.Gson;
import com.koolbao.maptest.Util.SqlFomat;
import com.koolbao.maptest.Util.Tables;

/**
 * @ClassName: Map
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:35:26 PM
 */
public class Maps extends Mapper<Object, Text, Text, Text> {

	SqlFomat sql = new SqlFomat();
	Gson gson = new Gson();
	Tables tables = new Tables();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		InputSplit inputSplit = (InputSplit) context.getInputSplit();
		String filename = ((FileSplit) inputSplit).getPath().getName();

		java.util.Map<String, String> map = sql.formate(value.toString());

		if (map == null) {
			return;
		}
		String keyValue = map.get("CLIENT_ID");
		map.put("table", filename);
		tables.getDate(map);
		context.write(new Text(keyValue), new Text(gson.toJson(map)));
	}
}
