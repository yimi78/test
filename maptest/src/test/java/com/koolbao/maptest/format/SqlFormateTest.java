/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: SqlFormateTest.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest.format 
 * @Description: TODO
 * @author: lhq   
 * @date: 2017年3月9日 下午12:01:23 
 * @version: V1.0   
 */
package com.koolbao.maptest.format;

import org.junit.Test;

import com.koolbao.maptest.Util.format.Transfer;

/**
 * @ClassName: SqlFormateTest
 * @Description: TODO
 * @author: lhq
 * @date: 2017年3月9日 下午12:01:23
 */
public class SqlFormateTest {

	@Test
	public void client() {
		String sqlPath = "input\\data\\client-2.sql";
		String outPath = "input\\pretreatment\\client\\client-2.sql";

		Transfer sf = new Transfer();
		sf.getDate(sqlPath, outPath);
	}

	@Test
	public void fundaccount() {
		String sqlPath = "input\\data\\fundaccount-2.sql";
		String outPath = "input\\pretreatment\\fundaccount\\fundaccount-2.sql";

		Transfer sf = new Transfer();
		sf.getDate(sqlPath, outPath);
	}

	@Test
	public void his_deliver() {
		String sqlPath = "input\\data\\his_deliver-2.sql";
		String outPath = "input\\pretreatment\\his_deliver\\his_deliver-2.sql";

		Transfer sf = new Transfer();
		sf.getDate(sqlPath, outPath);
	}
}
