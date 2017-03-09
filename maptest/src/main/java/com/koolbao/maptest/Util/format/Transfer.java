/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: Client.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.format 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月9日 上午11:07:57 
 * @version: V1.0   
 */
package com.koolbao.maptest.Util.format;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * sqldata格式化
 * 
 * @ClassName: Client
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月9日 上午11:07:57
 */
public class Transfer {

	public void getDate(String input, String output) {
		List<String> result = new ArrayList<String>();
		try {
			List<String> list = FileUtils.readLines(new File(input), "gbk");

			String tmp = "";
			for (String item : list) {
				if (StringUtils.isNoneBlank(item) && item.contains("insert")) {
					if (StringUtils.isNoneBlank(tmp)) {
						result.add(new String(tmp));
					}
					tmp = item;
				} else if (StringUtils.isNoneBlank(item)
						&& StringUtils.isNoneBlank(tmp)) {
					tmp += item;
				}
			}
			if (StringUtils.isNoneBlank(tmp)) {
				result.add(new String(tmp));
			}
			FileUtils.writeLines(new File(output), result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
