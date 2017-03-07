///**   
// * Copyright © 2017 酷玛. All rights reserved.
// * 
// * @Title: ExprTest.java 
// * @Prject: maptest
// * @Package: com.koolbao.maptest.Util 
// * @Description: TODO
// * @author: lhq   
// * @date: 2017年3月2日 下午8:32:30 
// * @version: V1.0   
// */
//package com.koolbao.maptest.Util;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
//
//import org.junit.Test;
//
///**
// * @ClassName: ExprTest
// * @Description: TODO
// * @author: lhq
// * @date: 2017年3月2日 下午8:32:30
// */
//public class ExprTest {
//
//	@Test
//	public void test() throws IOException {
//		// TODO Auto-generated method stub
//		Map<String, String> datas = new HashMap<String, String>();
//		datas.put("age", "10");
//		datas.put("money", "60");
//		Expr tt = new Expr();
//		List<String> ss = tt.getTag(datas);
//		for (String item : ss) {
//			System.out.println(item);
//		}
//	}
//
//	public void test2() throws IOException {
//		// TODO Auto-generated method stub
//		ScriptEngine jse = new ScriptEngineManager()
//				.getEngineByName("JavaScript");
//		try {
//			System.out.println(jse.eval(
//					"19920822 > 19900101 &&  19920822 < 20000101").toString());
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
