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
package com.koolbao.maptest.tag.pretreatment.basic;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BasicJob {

	public static void main(String[] args) {
		BasicJob hd = new BasicJob();
		try {
			hd.run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Object> init() {
		String dt = "20161010";
		String mapKey = "CLIENT_ID";
		String mapDtKey = "INIT_DATE";
		String outputKey = "BUSINESS_BALANCE,FARE0";

		String jobName = BasicJob.class.toString();
		String chachefile = "outResource/历史证券交收表";
		String tagType = "历史证券交收";
		String[] args = new String[] { "input\\pretreatment\\his_deliver",
				"input\\tag\\basis\\his_deliver\\" + dt };

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dt", dt);
		map.put("mapKey", mapKey);
		map.put("mapDtKey", mapDtKey);
		map.put("outputKey", outputKey);
		map.put("jobName", jobName);
		map.put("chachefile", chachefile);
		map.put("args", args);
		map.put("tagType", tagType);
		return map;
	}

	public void setJob(Job job) {
		job.setJarByClass(BasicJob.class);
		job.setMapperClass(BasicMaps.class);
		job.setReducerClass(BasicReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setNumReduceTasks(1);
		job.setOutputValueClass(Text.class);
	}

	public void run(String[] args) throws Exception {
		Map<String, Object> params = init();
		Date begin = new Date();
		String dt = params.get("dt").toString();
		String mapKey = params.get("mapKey").toString();
		String mapDtKey = params.get("mapDtKey").toString();
		String outputKey = params.get("outputKey").toString();
		String outputKeyX = params.get("outputKeyX").toString();
		String tagType = params.get("tagType").toString();
		String jobName = params.get("jobName").toString();
		String chachefile = params.get("chachefile").toString();
		args = (String[]) params.get("args");

		Configuration conf = new Configuration();
		if (StringUtils.isNotBlank(mapKey)) {
			conf.set("mapKey", mapKey);
		}
		if (StringUtils.isNotBlank(mapDtKey)) {
			conf.set("mapDtKey", mapDtKey);
		}
		if (StringUtils.isNotBlank(dt)) {
			conf.set("dt", dt);
		}
		if (StringUtils.isNotBlank(outputKey)) {
			conf.set("outputKey", outputKey);
		}
		if (StringUtils.isNotBlank(outputKeyX)) {
			conf.set("outputKeyX", outputKeyX);
		}
		if (StringUtils.isNotBlank(tagType)) {
			conf.set("tagType", tagType);
		}
		Job job = Job.getInstance(conf, jobName);
		setJob(job);

		if (StringUtils.isNotBlank(chachefile)) {
			job.addCacheFile(new URI(chachefile));
		}

		for (String file : args[0].split(",")) {
			FileInputFormat.addInputPath(job, new Path(file));
		}
		Path out = new Path(args[1]);
		if (FileSystem.get(conf).exists(out)) {
			FileSystem fs = FileSystem.get(conf);
			fs.delete(out);
		}
		FileSystem fileSystem = FileSystem.get(new Configuration());
		if (fileSystem.exists(out)) {
			fileSystem.delete(out, true);
		}
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		Date end = new Date();
		job.waitForCompletion(true);
		System.out.println((end.getTime() - begin.getTime()) / 1000);
	}
}