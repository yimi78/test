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
package com.koolbao.maptest.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.google.gson.Gson;

/**
 * @ClassName: Map
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:35:26 PM
 */
public class Maps extends Mapper<Object, Text, Text, Text> {

	Gson gson = new Gson();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		InputSplit inputSplit = (InputSplit) context.getInputSplit();
		String filename = ((FileSplit) inputSplit).getPath().getName();

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = gson.fromJson(value.toString(), map.getClass());
		} catch (Exception e) {
			e.printStackTrace();
			context.getCounter("map异常数据", e.getMessage()).increment(1);
			return;
		}

		if (map == null) {
			return;
		}

		String userId = map.get("key") + "";
		context.write(new Text(userId), new Text(gson.toJson(map)));
	}
}
