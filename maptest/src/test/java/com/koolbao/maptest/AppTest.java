package com.koolbao.maptest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		try {
			Gson gson = new Gson();
			Map<String, Object> map = new HashMap<String, Object>();

			List<String> k1List = FileUtils.readLines(new File(
					"input/tag/20170307/2"));
			List<String> k2List = FileUtils.readLines(new File(
					"input/tag/20170308/3"));
			List<String> pList = FileUtils.readLines(new File(
					"input/tag/people/4"));

			for (int i = 2; i < 200; i++) {
				String key = UUID.randomUUID().toString();
				Map<String, Object> k1map = gson.fromJson(k1List.get(0),
						map.getClass());
				k1map.put("key", key);
				k1map.put("tagValue", Math.round(Math.random() * 100));
				k1List.add(i, gson.toJson(k1map));

				Map<String, Object> k2map = gson.fromJson(k2List.get(0),
						map.getClass());
				k2map.put("key", key);
				k2map.put("tagValue", Math.round(Math.random() * 100));
				k2List.add(i, gson.toJson(k2map));

				Map<String, Object> pmap = gson.fromJson(pList.get(0),
						map.getClass());
				pmap.put("key", key);
				pmap.put("tagValue", Math.round(Math.random() * 100));
				pList.add(i, gson.toJson(pmap));
			}
			FileUtils.writeLines(new File("input/tag/20170307/2"), k1List);
			FileUtils.writeLines(new File("input/tag/20170308/3"), k2List);
			FileUtils.writeLines(new File("input/tag/people/4"), pList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
