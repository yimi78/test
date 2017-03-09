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
package com.koolbao.maptest.tag.pretreatment.client;

import com.koolbao.maptest.tag.pretreatment.basic.BasicReduce;

/**
 * @ClassName: Reduce
 * @Description: TODO
 * @author: lhq
 * @date: Dec 22, 2016 3:36:08 PM
 */
public class Reduce extends BasicReduce {

	// Map<String, String> dictionary = new HashMap<String, String>();// 字段对应表
	// Gson gson = new Gson();
	//
	// /**
	// * 预处理,读取规则
	// */
	// @SuppressWarnings("deprecation")
	// protected void setup(Context context) throws IOException,
	// InterruptedException {
	// List<String> list = new ArrayList<String>();
	// try {
	// Path[] path = context.getLocalCacheFiles();
	// list = IOUtils.readLines(FileSystem.get(context.getConfiguration())
	// .open(path[0]));
	//
	// for (String item : list) {
	// if (StringUtils.isNoneBlank(item)) {
	// String[] cols = item.split(",");
	// dictionary.put(cols[0].toUpperCase(), cols[1]);
	// }
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// *
	// */
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// public void reduce(Text key, Iterable<Text> values, Context context)
	// throws IOException, InterruptedException {
	//
	// String userId = key.toString(); // 用户id
	// List<Map> result = new ArrayList<Map>();
	// Map<String, Object> tmp = new HashMap<String, Object>();
	// tmp.put("key", key.toString());
	// tmp.put("tagType", "client信息");
	//
	// for (Text item : values) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map = gson.fromJson(item.toString(), map.getClass());
	// Set<String> kes = map.keySet();
	// for (String keyTmp : kes) {
	// Map<String, Object> tmpinfo = new HashMap<String, Object>();
	// tmpinfo.putAll(tmp);
	// if (!"key".equals(keyTmp)) {
	// tmpinfo.put("tagName", keyTmp);
	// if (dictionary.containsKey(keyTmp)) {
	// tmpinfo.put("tagName", dictionary.get(keyTmp));
	// }
	// if (map.containsKey(keyTmp)) {
	// tmpinfo.put("tagValue", map.get(keyTmp));
	// }
	//
	// result.add(tmpinfo);
	// }
	// }
	// }
	// if (result.size() > 0) {
	// for (Map item : result) {
	// context.write(new Text(), new Text(gson.toJson(item)));
	// }
	// }
	//
	// }
}
