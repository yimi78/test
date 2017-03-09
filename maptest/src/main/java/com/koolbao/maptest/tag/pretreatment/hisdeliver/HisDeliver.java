/**   
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: Test.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest 
 * @Description: TODO
 * @author: lhq   
 * @date: Dec 22, 2016 3:10:32 PM 
 * @version: V1.0   
 */
package com.koolbao.maptest.tag.pretreatment.hisdeliver;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.koolbao.maptest.tag.pretreatment.basic.BasicJob;

public class HisDeliver extends BasicJob {

	public static void main(String[] args) {
		HisDeliver hd = new HisDeliver();
		try {
			hd.run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setJob(Job job) {
		job.setJarByClass(BasicJob.class);
		job.setMapperClass(Maps.class);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setNumReduceTasks(1);
		job.setOutputValueClass(Text.class);
	}

	public Map<String, Object> init() {
		String dt = "20161010";
		String mapKey = "CLIENT_ID";
		String mapDtKey = "INIT_DATE";
		String outputKey = "";
		String outputKeyX = "BUSINESS_BALANCE#sum,FARE0#sum";

		String jobName = HisDeliver.class.toString();
		String chachefile = "outResource/历史证券交收表";
		String tagType = "历史证券交收";
		String[] args = new String[] { "input\\pretreatment\\his_deliver",
				"input\\tag\\basis\\his_deliver\\" + dt };

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dt", dt);
		map.put("mapKey", mapKey);
		map.put("mapDtKey", mapDtKey);
		map.put("outputKey", outputKey);
		map.put("outputKeyX", outputKeyX);
		map.put("jobName", jobName);
		map.put("chachefile", chachefile);
		map.put("args", args);
		map.put("tagType", tagType);
		return map;
	}

}