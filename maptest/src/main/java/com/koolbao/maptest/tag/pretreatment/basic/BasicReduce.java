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
package com.koolbao.maptest.tag.pretreatment.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.google.gson.Gson;
import com.koolbao.maptest.Util.Statistics;

/**
 * @ClassName: Reduce
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:36:08 PM
 */
public class BasicReduce extends Reducer<Text, Text, Text, Text> {

	Map<String, String> dictionary = new HashMap<String, String>();// 字段对应表
	Gson gson = new Gson();
	String[] outKey = null; // 需要输出的key
	String[] outKeyX = null; // 需要输出的key

	/**
	 * 预处理,读取规则
	 */
	@SuppressWarnings("deprecation")
	protected void setup(Context context) throws IOException,
			InterruptedException {
		List<String> list = new ArrayList<String>();
		try {
			// 设置导出数据
			String outputKey = (String) context.getConfiguration().get(
					"outputKey");
			String outputKeyX = (String) context.getConfiguration().get(
					"outputKeyX");
			if (StringUtils.isNotBlank(outputKey)) {
				outKey = outputKey.split(",");
			}
			if (StringUtils.isNotBlank(outputKeyX)) {
				outKeyX = outputKeyX.split(",");
			}

			Path[] path = context.getLocalCacheFiles();
			list = IOUtils.readLines(FileSystem.get(context.getConfiguration())
					.open(path[0]));

			for (String item : list) {
				if (StringUtils.isNoneBlank(item)) {
					String[] cols = item.split(",");
					dictionary.put(cols[0].toUpperCase(), cols[1]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String userId = key.toString(); // 用户id
		String tagType = context.getConfiguration().get("tagType");
		String dt = context.getConfiguration().get("dt");
		List<Map> result = new ArrayList<Map>();

		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("key", key.toString());
		tmp.put("tagType", tagType);
		tmp.put("dt", dt);

		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		for (Text item : values) {
			maps = gson.fromJson(item.toString(), maps.getClass());
			mapList.add(maps);
		}

		Statistics statistics = new Statistics();
		if (outKeyX != null && outKeyX.length > 0) {

			for (String outKeyXItem : outKeyX) {
				String statisticsResult = statistics.statistics(mapList,
						outKeyXItem);
				if (StringUtils.isNotBlank(statisticsResult)) {
					Map<String, Object> tmpinfo = new HashMap<String, Object>();
					tmpinfo.putAll(tmp);

					String keyTmp = outKeyXItem.split("#")[0].toUpperCase();
					tmpinfo.put("tagName", keyTmp);
					if (dictionary.containsKey(keyTmp)) {
						tmpinfo.put("tagName", dictionary.get(keyTmp));
					}
					tmpinfo.put("tagValue", statisticsResult);
					result.add(tmpinfo);
				}
			}
		}

		// 输出单个的
		for (Map<String, Object> map : mapList) {
			// 输出全部
			if ((outKey == null || outKey.length == 0)
					&& (outKeyX == null || outKeyX.length == 0)) {
				Set<String> kes = map.keySet();
				for (String keyTmp : kes) {
					Map<String, Object> tmpinfo = new HashMap<String, Object>();
					tmpinfo.putAll(tmp);
					if (!"key".equals(keyTmp)) {
						tmpinfo.put("tagName", keyTmp);
						if (dictionary.containsKey(keyTmp)) {
							tmpinfo.put("tagName", dictionary.get(keyTmp));
						}
						if (map.containsKey(keyTmp)) {
							tmpinfo.put("tagValue", map.get(keyTmp));
						}
						result.add(tmpinfo);
					}
				}
			} else if (outKey != null && outKey.length > 0) {
				// 输出指定的
				for (String keyTmp : outKey) {
					Map<String, Object> tmpinfo = new HashMap<String, Object>();
					tmpinfo.putAll(tmp);
					if (!"key".equals(keyTmp)) {
						tmpinfo.put("tagName", keyTmp);
						if (dictionary.containsKey(keyTmp)) {
							tmpinfo.put("tagName", dictionary.get(keyTmp));
						}
						if (map.containsKey(keyTmp)) {
							tmpinfo.put("tagValue", map.get(keyTmp));
						}
						result.add(tmpinfo);
					}
				}
			}
		}
		if (result.size() > 0) {
			for (Map item : result) {
				context.write(new Text(), new Text(gson.toJson(item)));
			}
		}
	}
}
