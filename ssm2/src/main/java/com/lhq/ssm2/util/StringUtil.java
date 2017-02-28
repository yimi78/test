package com.lhq.ssm2.util ;

import java.util.List ;

import org.apache.commons.lang3.StringUtils ;

public class StringUtil {
	 
	 public static void main( String[] args ) {
		  // TODO Auto-generated method stub
		  
	 }
	 
	 public static String trim( String txt ) {
		  if ( StringUtils.isBlank( txt ) || "null".equals( txt ) ) { return "" ; }
		  return txt.trim( ) ;
	 }
	 
	 /**
	  * list 转化为 文本，用于页面显示
	  * 
	  * @Title: infoCon
	  * @Description: TODO
	  * @param list
	  * @return
	  * @return: String
	  */
	 public static String infoCon( List<String> list ) {
		  String msg = "" ;
		  for ( String item : list ) {
				msg += ( item + "\r\n" ) ;
		  }
		  return msg ;
	 }
}