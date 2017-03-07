///**   
// * Copyright © 2017 酷玛. All rights reserved.
// * 
// * @Title: Files.java 
// * @Prject: maptest
// * @Package: com.koolbao.maptest.Util 
// * @Description: TODO
// * @author: lhq   
// * @date: 2017年3月2日 下午8:05:54 
// * @version: V1.0   
// */
//package com.koolbao.maptest.Util;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//
///**
// * @ClassName: Files
// * @Description: TODO
// * @author: lhq
// * @date: 2017年3月2日 下午8:05:54
// */
//public class Files {
//
//	/**
//	 * @Title: main
//	 * @Description: TODO
//	 * @param args
//	 * @return: void
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		String path = "C:\\Users\\lhq\\Desktop\\22";
//		try {
//			List<String> list = FileUtils.readLines(new File(path), "gbk");
//			List<String> result = new ArrayList<String>();
//
//			String text = "";
//			for (String item : list) {
//				if (item.contains("clientinfo")) {
//					if (StringUtils.isNotBlank(text)) {
//						result.add(text);
//					}
//					text = item;
//				} else if (StringUtils.isNotBlank(text)) {
//					text += item;
//				}
//			}
//			FileUtils.writeLines(new File("C:\\Users\\lhq\\Desktop\\33"),
//					result);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
