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
package com.koolbao.maptest.tag;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DoubleTag {

	public static String path = "";

	public static void main(String[] args) throws Exception {

		args = new String[] { "tag", "output" };
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, DoubleTag.class.toString());
		job.setJarByClass(DoubleTag.class);
		job.setMapperClass(Maps.class);
		job.setReducerClass(Reduce.class);

		job.setOutputKeyClass(Text.class);
		job.setNumReduceTasks(1);
		job.setOutputValueClass(Text.class);
		// job.addCacheFile(new URI("outResource/DoubleTag"));
		FileInputFormat.addInputPath(job, new Path(args[0]));
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
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}