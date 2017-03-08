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
package com.koolbao.maptest.wordcount;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

	public static String path = "";

	public static void main(String[] args) throws Exception {

		System.setProperty("hadoop.home.dir",
				"C:\\development\\Development\\hadoop\\hadoop-2.6.0");
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(Maps.class);
		job.setReducerClass(Reduce.class);
		// job.setPartitionerClass(Class<? extends Partitioner> cls);
		job.setOutputKeyClass(Text.class);
		job.setNumReduceTasks(1);
		job.setOutputValueClass(Text.class);
		job.addCacheFile(new URI("outResource/rule#file"));
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