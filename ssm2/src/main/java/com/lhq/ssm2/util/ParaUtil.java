/**
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: ParaUtil.java
 * @Prject: ssm2
 * @Package: com.lhq.ssm2.util
 * @Description: TODO
 * @author: lhq
 * @date: Nov 25, 2016 11:26:40 AM
 * @version: V1.0
 */
package com.lhq.ssm2.util ;

import java.text.SimpleDateFormat ;
import java.util.Date ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.Map ;

import org.apache.commons.lang3.StringUtils ;

import com.koolbao.common.utils.ArgumentsAnalysisUtils ;

/**
 * @ClassName: ParaUtil
 * @Description: TODO
 * @author: lhq
 * @date: Nov 25, 2016 11:26:40 AM
 */
public class ParaUtil {
	 
	 /**
	  * @Title: main
	  * @Description: TODO
	  * @param args
	  * @return: void
	  */
	 public static void main( String[] args ) {
		  // TODO Auto-generated method stub
		  
	 }
	 
	 /**
	  * 参数初始化
	  * 
	  * @Title: initParam
	  * @Description: TODO
	  * @param time
	  * @param version
	  * @return: void
	  */
	 public static Map<String , String> initParam( String time , String version ) {
		  Map<String , String> map = new HashMap<String , String>( ) ;
		  String msg = "" ;
		  SimpleDateFormat sd2 = new SimpleDateFormat( "yyyy-MM-dd" ) ;
		  try {
				Date date = sd2.parse( time ) ;
				if ( StringUtils.isNotBlank( version ) ) {
					 ArgumentsAnalysisUtils autils = new ArgumentsAnalysisUtils( "站外订单明细" ) ;
					 Map<String , String> param = autils.parse( version.split( "," ) ) ;
					 map = param ;
				}
				map.put( "start_date" , time ) ;
				map.put( "end_date" , time ) ;
		  }
		  catch ( Exception e ) {
				// TODO Auto-generated catch block
				e.printStackTrace( ) ;
				msg = "参数转化错误：" + e.getMessage( ) ;
				map.put( "msg" , msg ) ;
		  }
		  
		  return map ;
	 }
	 
	 /**
	  * 将map转为数组
	  * 
	  * @Title: getAras
	  * @Description: TODO
	  * @param param
	  * @return
	  * @return: String[]
	  */
	 public static String[] getArgs( Map<String , String> param ) {
		  int size = param.keySet( ).size( ) ;
		  if ( param == null || size == 0 ) { return new String[0] ; }
		  String[] args = new String[size] ;
		  Iterator<String> items = param.keySet( ).iterator( ) ;
		  
		  int i = 0 ;
		  while ( items.hasNext( ) ) {
				String key = items.next( ) ;
				args[i++] = "--" + key + "=" + param.get( key ) ;
		  }
		  return args ;
	 }
}
