/**
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: Advertiser.java
 * @Prject: kmwz
 * @Package: com.koolbao.tax
 * @Description: TODO
 * @author: lhq
 * @date: Jun 8, 2016 4:19:31 PM
 * @version: V1.0
 */
package com.lhq.ssm2.controller ;

import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;

import javax.annotation.Resource ;
import javax.servlet.http.HttpServletRequest ;

import org.apache.commons.lang3.StringUtils ;
import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.RequestMapping ;

import com.koolbao.common.utils.Md5Utils ;
import com.koolbao.common.utils.huihex.HuiheUtils ;
import com.koolma.main.KmSql ;
import com.lhq.ssm2.service.ShopOptimizeService ;

/**
 * 店铺优化数据
 * 
 * @ClassName: Advertiser
 * @Description: TODO
 * @author: lhq
 * @date: Jun 8, 2016 4:19:31 PM
 */
@ Controller
@ RequestMapping( "/huihe/shopOptimize" )
public class ShopOptimizeController {
	 @ Resource
	 private ShopOptimizeService  shopOptimizeService ;
	 
	 // 系统默认参数
	 public static final String[] dev_info = new String[] { "PW" , "PC" , "WAP" } ;
	 public static final int		dev_pw	= 0 ;
	 public static final int		dev_pc	= 1 ;
	 public static final int		dev_wap  = 2 ;
	 public static final int		dev_all  = 3 ;
	 
	 public String					 posId	 = "" ;
	 
	 /**
	  * 获取店铺数据
	  * 
	  * @Title: distribute
	  * @Description: TODO
	  * @return
	  * @return: String
	  */
	 @ RequestMapping( "/shopOptimize" )
	 public String distribute( HttpServletRequest request , Model model ) {
		  List<Map<String , Object>> shopInfo = new ArrayList<Map<String , Object>>( ) ; // 店铺到达信息
		  String resultJsp = "/huihe/shopOptimize/shopOptimize" ;
		  if ( request.getParameter( "pcOrWap" ) == null ) { return resultJsp ; }
		  int pcOrWap = Integer.parseInt( request.getParameter( "pcOrWap" ) ) ;
		  int select = Integer.parseInt( request.getParameter( "select" ) ) ;
		  String beginDate = request.getParameter( "beginDate" ) ;
		  String endDate = request.getParameter( "endDate" ) ;
		  String shopId = request.getParameter( "shopId" ) ;
		  String taskId = request.getParameter( "taskId" ) ;
		  String shopOptimizeSql = "" ;
		  String msg = "" ;
		  
		  Map<String , Object> param = new HashMap<String , Object>( ) ;
		  
		  param.put( "pcOrWap" , pcOrWap ) ;
		  param.put( "select" , select ) ;
		  msg = shopOptimizeService.initParam( beginDate , endDate , shopId , taskId , param ) ;
		  beginDate = ( String ) param.get( "start_date" ) ;
		  endDate = ( String ) param.get( "end_date" ) ;
		  
		  String time = endDate ;
		  if ( StringUtils.isNotBlank( msg ) ) {
				model.addAttribute( "msg" , msg ) ;
				return resultJsp ;
		  }
		  
		  if ( select == 0 ) { // 查询
				// getShopData( ) ;
		  }
		  else if ( select == 1 ) { // 优化
		  
				List<String> list = null ;
				if ( pcOrWap == 1 ) {
					 list = optimizePC( time ) ;
				}
				else if ( pcOrWap == 2 ) {
					 list = optimizeWap( time ) ;
				}
				else {
					 list = getOptimizeSql( time ) ;
				}
				if ( list != null ) {
					 for ( String item : list ) {
						  shopOptimizeSql += ( item + "\r\n" ) ;
					 }
					 model.addAttribute( "shopOptimizeSql" , shopOptimizeSql ) ;
				}
		  }
		  else if ( select == 2 ) { // pc转无线
				// pcToWap();
		  }
		  else if ( select == 3 ) { // 无线转pc
				// wapToPc();
		  }
		  else if ( select == 4 ) { // 恢复
				if ( pcOrWap == 1 ) {
					 // pcRecover();
				}
				else if ( pcOrWap == 2 ) {
					 // wapRecover();
				}
				else {
					 // pcRecover();
					 // wapRecover();
				}
		  }
		  // 运行全部店铺
		  if ( StringUtils.isBlank( shopId ) ) {
				
				List<Map<String , String>> usersInfo = HuiheUtils.getUsersByNet( param.get( "end_date" ).toString( ) ) ;
				
				Map<String , Map<String , String>> usersInfoMap = new HashMap<String , Map<String , String>>( ) ;
				for ( Map<String , String> map : usersInfo ) {
					 
					 try {
						  param.put( "start_date" , param.get( "end_date" ) ) ;
						  param.put( "task_id" , map.get( "task_id" ) ) ;
						  param.put( "shop_id" , map.get( "shop_id" ) ) ;
						  param.put( "ext" , Md5Utils.create16Md5( map.get( "shop_id" ) ).substring( 14 ).toLowerCase( ) ) ;
						  param.put( "shop_name" , map.get( "用户名称" ) ) ;
						  if ( StringUtils.isBlank( map.get( "shop_id" ) ) ) {
								msg += ( "店铺：" + map.get( "用户名称" ) + ";" ) ;
								continue ;
						  }
						  shopInfo = getShopData( param , shopInfo ) ;
						  
						  usersInfoMap.put( param.get( "shop_name" ).toString( ) , map ) ;
					 }
					 catch ( Exception e ) {
						  msg += ( "店铺：" + map.get( "用户名称" ) + ";" ) ;
						  e.printStackTrace( ) ;
					 }
				}
				
				List<String> colList = new ArrayList<String>( ) ;
				colList.add( "用户名称" ) ;
				colList.add( "点击量" ) ;
				colList.add( "ROI" ) ;
				// 需要修改合并的 关键词
				shopOptimizeService.merge( shopInfo , usersInfoMap , colList ) ;
		  }
		  else {
				shopInfo = getShopData( param , shopInfo ) ;
		  }
		  
		  shopOptimizeService.check( shopInfo ) ;
		  
		  model.addAttribute( "msg" , msg ) ;
		  model.addAttribute( "shopInfo" , shopInfo ) ;
		  model.addAttribute( "taskId" , taskId ) ;
		  model.addAttribute( "shopId" , shopId ) ;
		  model.addAttribute( "beginDate" , beginDate ) ;
		  model.addAttribute( "endDate" , endDate ) ;
		  model.addAttribute( "select" , select ) ;
		  model.addAttribute( "pcOrWap" , pcOrWap ) ;
		  return resultJsp ;
	 }
	 
	 /**
	  * 
	  * 得到店铺到达数据
	  * 
	  * @Title:
	  * @Description: 调用参数
	  * @param txt
	  * @return: void
	  */
	 public List<Map<String , Object>> getShopData( Map<String , Object> params , List<Map<String , Object>> shopInfo ) {
		  
		  String pcOrWap = params.get( "pcOrWap" ) + "" ;
		  String shop_id = ( String ) params.get( "shop_id" ) ;
		  String shop_name = ( String ) params.get( "shop_name" ) ;
		  
		  Map<String , Object> param = new HashMap<String , Object>( ) ;
		  param.putAll( params ) ;
		  // 获取所有情况数据
		  if ( ( ShopOptimizeController.dev_all + "" ).equals( pcOrWap + "" ) ) {
				
				// 获取pc数据
				param.put( "pcOrWap" , ShopOptimizeController.dev_pw ) ;
				List<Map<String , Object>> shopInfoTmp = shopOptimizeService.getShopByDate( param ) ;
				for ( Map<String , Object> item : shopInfoTmp ) {
					 item.put( "status" , dev_info[ShopOptimizeController.dev_pw] ) ;
					 item.put( "shop_id" , shop_id ) ;
					 item.put( "shop_name" , shop_name ) ;
				}
				shopInfo.addAll( shopInfoTmp ) ;
				
				//
				param.put( "pcOrWap" , ShopOptimizeController.dev_pc ) ;
				shopInfoTmp = shopOptimizeService.getShopByDate( param ) ;
				for ( Map<String , Object> item : shopInfoTmp ) {
					 item.put( "status" , dev_info[ShopOptimizeController.dev_pc] ) ;
					 item.put( "shop_id" , shop_id ) ;
					 item.put( "shop_name" , shop_name ) ;
				}
				shopInfo.addAll( shopInfoTmp ) ;
				
				//
				param.put( "pcOrWap" , ShopOptimizeController.dev_wap ) ;
				shopInfoTmp = shopOptimizeService.getShopByDate( param ) ;
				for ( Map<String , Object> item : shopInfoTmp ) {
					 item.put( "status" , dev_info[ShopOptimizeController.dev_wap] ) ;
					 item.put( "shop_id" , shop_id ) ;
					 item.put( "shop_name" , shop_name ) ;
				}
				shopInfo.addAll( shopInfoTmp ) ;
		  }
		  else {
				List<Map<String , Object>> shopInfoTmp = shopOptimizeService.getShopByDate( param ) ;
				for ( Map<String , Object> item : shopInfoTmp ) {
					 item.put( "status" , dev_info[Integer.parseInt( param.get( "pcOrWap" ) + "" )] ) ;
					 item.put( "shop_id" , shop_id ) ;
					 item.put( "shop_name" , shop_name ) ;
				}
				shopInfo.addAll( shopInfoTmp ) ;
		  }
		  return shopInfo ;
		  
	 }
	 
	 /**
	  * pc数据转化为无线
	  * 
	  * @Title: pcToWap
	  * @Description: TODO
	  * @return
	  * @return: String
	  */
	 public String pcToWap( ) {
		  
		  return "" ;
	 }
	 
	 /**
	  * 无线转化为pc
	  * 
	  * @Title: wapToPc
	  * @Description: TODO
	  * @return
	  * @return: String
	  */
	 public String wapToPc( ) {
		  
		  return "" ;
	 }
	 
	 /**
	  * 优化pc
	  * 
	  * @Title: optimizePC
	  * @Description: TODO
	  * @return
	  * @return: String
	  */
	 public List<String> optimizePC( String time ) {
		  
		  List<String> sqls = getOptimizeSql( time ) ;
		  Iterator items = sqls.iterator( ) ;
		  while ( items.hasNext( ) ) {
				if ( items.next( ).toString( ).contains( "_wap" ) ) {
					 items.remove( ) ;
				}
		  }
		  return sqls ;
	 }
	 
	 /**
	  * 优化wap
	  * 
	  * @Title: optimizeWap
	  * @Description: TODO
	  * @return
	  * @return: String
	  */
	 public List<String> optimizeWap( String time ) {
		  
		  List<String> sqls = getOptimizeSql( time ) ;
		  Iterator items = sqls.iterator( ) ;
		  while ( items.hasNext( ) ) {
				if ( !items.next( ).toString( ).contains( "_wap" ) ) {
					 items.remove( ) ;
				}
		  }
		  return sqls ;
	 }
	 
	 /**
	  * 具体优化
	  * 
	  * @Title: optimize
	  * @Description: TODO
	  * @param select
	  * @return
	  * @return: String
	  */
	 public List<String> getOptimizeSql( String time ) {
		  // 默认的参数
		  
		  List<Map<String , String>> usersInfo = HuiheUtils.getUsersByNet( time ) ;
		  String msg = "" ;
		  Iterator items = usersInfo.iterator( ) ;
		  while ( items.hasNext( ) ) {
				Map<String , String> map = ( Map<String , String> ) items.next( ) ;
				if ( StringUtils.isBlank( map.get( "shop_id" ) ) ) {
					 msg += ( "店铺：" + map.get( "用户名称" ) + ";" ) ;
					 items.remove( ) ;
					 continue ;
				}
		  }
		  
		  KmSql km = new KmSql( ) ;
		  return km.run( time ) ;
	 }
	 
}
