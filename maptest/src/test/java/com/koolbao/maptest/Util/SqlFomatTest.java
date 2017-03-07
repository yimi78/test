///**   
// * Copyright © 2017 酷玛. All rights reserved.
// * 
// * @Title: SqlFomat.java 
// * @Prject: maptest
// * @Package: com.koolbao.maptest.Util 
// * @Description: TODO
// * @author: lhq   
// * @date: 2017年3月2日 下午8:33:26 
// * @version: V1.0   
// */
//package com.koolbao.maptest.Util;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//
///**
// * @ClassName: SqlFomat
// * @Description: TODO
// * @author: lhq
// * @date: 2017年3月2日 下午8:33:26
// */
//public class SqlFomatTest {
//	/**
//	 * @Title: main
//	 * @Description: TODO
//	 * @param args
//	 * @return: void
//	 */
//	public void test() {
//		// TODO Auto-generated method stub
//		SqlFomat sq = new SqlFomat();
//
//		List<String> list = null;
//		try {
//			list = FileUtils.readLines(new File("input/22"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int index = 0;
//		for (String item : list) {
//			if (++index == 10000) {
//				break;
//			}
//
//			sq.formate(item);
//		}
//	}
// }
