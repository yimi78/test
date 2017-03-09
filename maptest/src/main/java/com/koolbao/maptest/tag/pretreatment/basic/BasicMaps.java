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
package com.koolbao.maptest.tag.pretreatment.basic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.google.gson.Gson;
import com.koolbao.maptest.Util.format.SqlFomat;

/**
 * @ClassName: Map
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:35:26 PM
 */
public class BasicMaps extends Mapper<Object, Text, Text, Text> {

	SqlFomat sf = new SqlFomat();
	Gson gson = new Gson();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		InputSplit inputSplit = (InputSplit) context.getInputSplit();
		String filename = ((FileSplit) inputSplit).getPath().getName();

		Map<String, String> map = new HashMap<String, String>();
		try {
			map = sf.formate(value.toString());
		} catch (Exception e) {
			e.printStackTrace();
			context.getCounter("map异常数据", e.getMessage()).increment(1);
			return;
		}

		if (map == null) {
			return;
		}

		String mapKey = context.getConfiguration().get("mapKey");
		String mapDtKey = context.getConfiguration().get("mapDtKey");
		String dt = context.getConfiguration().get("dt");

		String userId = map.get(mapKey) + "";
		map.put("key", userId);

		String initDate = map.get(mapDtKey) + "";
		if (StringUtils.isNoneBlank(dt)) {
			if (initDate.equals(dt)) {
				context.write(new Text(userId), new Text(gson.toJson(map)));
			}
		} else {
			context.write(new Text(userId), new Text(gson.toJson(map)));
		}
	}
}
