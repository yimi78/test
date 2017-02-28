/**
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: Help.java
 * @Prject: kmwz
 * @Package: com.utils
 * @Description: TODO
 * @author: lhq
 * @date: Aug 3, 2016 8:12:17 PM
 * @version: V1.0
 */
package com.lhq.ssm2.util ;

import java.text.SimpleDateFormat ;
import java.util.Date ;

/**
 * @ClassName: Help
 * @Description: TODO
 * @author: lhq
 * @date: Aug 3, 2016 8:12:17 PM
 */
public class Help {
	 
	 public static SimpleDateFormat sdf	  = new SimpleDateFormat( "yyyy-MM-DD:HHmm" ) ;
	 public static String			  InserNo = "koolbao_auto_" ;
	 
	 /**
	  * @Title: main
	  * @Description: TODO
	  * @param args
	  * @return: void
	  */
	 public static void main( String[] args ) {
		  // TODO Auto-generated method stub
		  System.out.println( Math.random( ) * 5 ) ;
	 }
	 
	 /**
	  * 随机数，优化的效果
	  * 
	  * @Title: getProbability
	  * @Description: TODO
	  * @return
	  * @return: Double
	  */
	 public static Double getProbability( ) {
		  return ( ( Math.random( ) * 20 ) + 70 ) / 100 ;
	 }
	 
	 /**
	  * 转化数据
	  * 
	  * @Title: chan
	  * @Description: TODO
	  * @param obj
	  * @param probability
	  * @return
	  * @return: Integer
	  */
	 public static Integer waxNum( Object obj , Double probability ) {
		  Double result = ( ( Integer ) obj ) / probability ;
		  return result.intValue( ) ;
	 }
	 
	 /**
	  * 获取运行版本号
	  * 
	  * @Title: getInserNo
	  * @Description: TODO
	  * @return
	  * @return: String
	  */
	 public static String getInserNo( ) {
		  return InserNo + sdf.format( new Date( ) ) ;
	 }
	 
	 public static Integer waxNumAdd( Object obj , Double probability , Double litter ) {
		  Double result = ( ( Integer ) obj ) / probability ;
		  result = result * ( 1 + litter ) ;
		  return result.intValue( ) ;
	 }
	 
	 public static Integer waxNumSub( Object obj , Double probability , Double litter ) {
		  Double result = ( ( Integer ) obj ) / probability ;
		  result = result * ( 1 - litter ) ;
		  return result.intValue( ) ;
	 }
}
