package com.koolbao.maptest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MrPartitioner extends Partitioner<Text, IntWritable> {

	private Configuration conf = null;

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public int getPartition(Text arg0, IntWritable arg1, int arg2) {
		return (int) (Math.random() % 10);
	}
}