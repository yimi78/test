/**
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: ShopOptimizeService.java
 * @Prject: kmwz
 * @Package: com.service
 * @Description: TODO
 * @author: lhq
 * @date: Aug 3, 2016 1:49:31 AM
 * @version: V1.0
 */
package com.lhq.ssm2.service.impl;

import java.math.BigDecimal ;
import java.text.SimpleDateFormat ;
import java.util.Date ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils ;
import org.apache.commons.lang3.time.DateUtils ;
import org.springframework.stereotype.Service;

import com.koolbao.common.utils.Md5Utils;
import com.lhq.ssm2.IDao.TableZzSrcDao;
import com.lhq.ssm2.service.ShopOptimizeService;
import com.lhq.ssm2.util.Help;

/**
 * @ClassName: ShopOptimizeService
 * @Description: TODO
 * @author: lhq
 * @date: Aug 3, 2016 1:49:31 AM
 */
@Service("shopOptimizeService")
public class ShopOptimizeServiceImpl implements ShopOptimizeService {
	@Resource
	 private TableZzSrcDao tableZzSrcDao ;
	 
	 /**
	  * 按照时间获取数据
	  * 
	  * @Title: getShopByDate
	  * @Description: TODO
	  * @param map
	  * @return
	  * @return: List<Map<String,Object>>
	  */
	 public List<Map<String , Object>> getShopByDate( Map<String , Object> map ) {
		  return tableZzSrcDao.getShopByDate( map ) ;
	 }
	 
	 /**
	  * 初始化参数
	  * 
	  * @Title: initParam
	  * @Description: TODO
	  * @param beginDate
	  *            开始时间
	  * @param endDate
	  *            结束时间
	  * @param shopId
	  *            店铺id
	  * @param taskId
	  *            任务id
	  * @param param
	  *            参数集合
	  * @return
	  * @return: String 返回信息
	  */
	 public String initParam( String beginDate , String endDate , String shopId , String taskId ,
				Map<String , Object> param ) {
		  String msg = "参数错误" ;
		  SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" ) ;
		  if ( StringUtils.isNoneBlank( beginDate ) ) {
				try {
					 beginDate = beginDate.replaceAll( "-" , "-" ) ;
//					 beginDate = beginDate.replaceAll( "/" , "-" ) ;
					 sdf.parse( beginDate ) ;
					 param.put( "start_date" , beginDate ) ;
				}
				catch ( Exception e ) {
					 msg = beginDate + msg ;
					 return msg ;
				}
		  }
		  // else {
		  // beginDate = sdf.format( DateUtils.addDays( new Date( ) , -1 ) ) ;
		  // param.put( "start_date" , beginDate ) ;
		  // }
		  if ( StringUtils.isNoneBlank( endDate ) ) {
				try {
					 endDate = endDate.replaceAll( "-" , "-" ) ;
//					 endDate = endDate.replaceAll( "/" , "-" ) ;
					 sdf.parse( endDate ) ;
					 param.put( "end_date" , endDate ) ;
				}
				catch ( Exception e ) {
					 msg = endDate + msg ;
					 return msg ;
				}
		  }
		  // else {
		  // endDate = sdf.format( DateUtils.addDays( new Date( ) , -1 ) ) ;
		  // param.put( "end_date" , endDate ) ;
		  // }
		  if ( StringUtils.isBlank( endDate ) && StringUtils.isBlank( beginDate ) ) {
				endDate = sdf.format( DateUtils.addDays( new Date( ) , -1 ) ) ;
				param.put( "end_date" , endDate ) ;
				param.put( "start_date" , endDate ) ;
		  }
		  else if ( StringUtils.isBlank( endDate ) && StringUtils.isNotBlank( beginDate ) ) {
				param.put( "end_date" , beginDate ) ;
				param.put( "start_date" , beginDate ) ;
		  }
		  else if ( StringUtils.isNotBlank( endDate ) && StringUtils.isBlank( beginDate ) ) {
				param.put( "end_date" , endDate ) ;
				param.put( "start_date" , endDate ) ;
		  }
		  
		  if ( StringUtils.isNoneBlank( shopId ) ) {
				param.put( "shop_id" , shopId ) ;
				param.put( "ext" , Md5Utils.create16Md5( shopId ).substring( 14 ).toLowerCase( ) ) ;
		  }
		  else {
				// msg = "没有shopid" + msg;
				// return msg;
		  }
		  if ( StringUtils.isNoneBlank( taskId ) ) {
				param.put( "task_id" , taskId ) ;
		  }
		  else {
				// msg = "没有渠道号" + msg;
				// return msg;
		  }
		  param.put( "shop_name" , "" ) ;
		  msg = "" ;
		  return msg ;
	 }
	 
	 /**
	  * 验证数据
	  * 
	  * @Title: check
	  * @Description: TODO
	  * @param list
	  * @return
	  * @return: List<Map<String,Object>>
	  */
	 public List<Map<String , Object>> check( List<Map<String , Object>> list ) {
		  for ( Map<String , Object> map : list ) {
				if ( map == null ) {
					 continue ;
				}
				if ( map.get( "landing_pv_rate" ) == null
						  || ( ( BigDecimal ) map.get( "landing_pv_rate" ) ).doubleValue( ) < 0.6
						  || ( ( BigDecimal ) map.get( "landing_pv_rate" ) ).doubleValue( ) > 0.8 ) {
					 map.put( "landing_pv_rate_check" , "false" ) ;
				}
				if ( map.get( "avg_deep" ) == null || ( ( BigDecimal ) map.get( "avg_deep" ) ).doubleValue( ) > 3.0 ) {
					 map.put( "avg_deep_check" , "false" ) ;
				}
				if ( map.get( "jump_rate" ) == null || ( ( BigDecimal ) map.get( "jump_rate" ) ).doubleValue( ) > 90 ) {
					 map.put( "jump_rate_check" , "false" ) ;
				}
				if ( map.get( "landing_pv" ) == null
						  || map.get( "sv" ) == null
						  || ( ( BigDecimal ) map.get( "landing_pv" ) ).doubleValue( ) < ( ( BigDecimal ) map.get( "sv" ) )
									 .doubleValue( ) ) {
					 map.put( "landing_pv_check" , "false" ) ;
					 map.put( "sv_check" , "false" ) ;
				}
				
				if ( "PW".equals( map.get( "status" ) ) ) {
					 try {
						  Double huiheClick = Double.valueOf( map.get( "点击量" ).toString( ).trim( ) ) ;
						  Double koolbaoCkilc = ( ( BigDecimal ) map.get( "jump_pv" ) ).doubleValue( ) ;
						  if ( Math.abs( huiheClick - koolbaoCkilc ) / huiheClick > 0.05 ) {
								map.put( "jump_pv_check" , "false" ) ;
								map.put( "点击量_check" , "false" ) ;
						  }
					 }
					 catch ( Exception e ) {
						  e.printStackTrace( ) ;
					 }
				}
		  }
		  return list ;
	 }
	 
	 /**
	  * 补充毛尖数据
	  * 
	  * @Title: merge
	  * @Description: TODO
	  * @param shopInfo
	  *            店铺到达信息
	  * @param usersInfoMap
	  *            用户店铺信息
	  * @param adCol
	  *            需要添加的字段
	  * @return
	  * @return: List<Map<String,Object>>
	  */
	 public List<Map<String , Object>> merge( List<Map<String , Object>> shopInfo ,
				Map<String , Map<String , String>> usersInfoMap , List<String> adCol ) {
		  for ( Map<String , Object> item : shopInfo ) {
				String shopName = item.get( "shop_name" ) + "" ;
				if ( usersInfoMap.containsKey( shopName ) ) {
					 Map<String , String> usersInfoItem = usersInfoMap.get( shopName ) ;
					 for ( String col : adCol ) {
						  item.put( col , usersInfoItem.get( col ) ) ;
					 }
				}
		  }
		  
		  return shopInfo ;
	 }
	 
	 /**
	  * 按照时间获取点击数据
	  * 
	  * @Title: getShopByDate
	  * @Description: TODO
	  * @param map
	  * @return
	  * @return: List<Map<String,Object>>
	  */
	 public List<Map<String , Object>> getShopClick( Map<String , Object> map ) {
		  return tableZzSrcDao.getShopClick( map ) ;
	 }
	 
	 public List<Map<String , Object>> getShopWapOrPc( Map<String , Object> map ) {
		  return tableZzSrcDao.getShopWapOrPc( map ) ;
	 }
	 
	 /**
	  * 批量插入数据
	  * 
	  * @Title: insertShopInfo
	  * @Description: TODO
	  * @param map
	  * @return: void
	  */
	 public void insertShopInfo( Map<String , Object> map ) {
		  tableZzSrcDao.insertShopInfo( map ) ;
	 }
	 
	 /**
	  * 根据跳转变化流量
	  * 
	  * @Title: converParam
	  * @Description: TODO
	  * @param standard
	  * @param map
	  * @return
	  * @return: Map<String,String>
	  */
	 public Map<String , Object> converParam( Integer standard , Map<String , Object> map , String posId ) {
		  Map<String , Object> result = new HashMap<String , Object>( ) ;
		  Integer uv = ( Integer ) map.get( "uv" ) ;
		  Double bizProbability = Help.getProbability( ) ;
		  Double probability = ( uv + 0.0 ) / ( standard * bizProbability ) ;
		  
		  Integer pv = Help.waxNum( map.get( "pv" ) , probability ) ;
		  uv = Help.waxNum( map.get( "uv" ) , probability ) ;
		  Integer sv = Help.waxNum( map.get( "sv" ) , probability ) ;
		  Integer loss = Help.waxNum( map.get( "loss" ) , probability ) ;
		  Integer stay = Help.waxNum( map.get( "stay" ) , probability ) ;
		  String group_id = map.get( "group_id" ).toString( ) ;
		  String plan_id = map.get( "plan_id" ).toString( ) ;
		  String cur_id = map.get( "cur_id" ).toString( ) ;
		  String src_id = map.get( "task_id" ).toString( ) ;
		  String task_id = map.get( "task_id" ).toString( ) ;
		  String add_time = map.get( "add_time" ).toString( ) ;
		  String sub_time = map.get( "sub_time" ).toString( ) ;
		  
		  result.put( "pv" , pv ) ;
		  result.put( "uv" , uv ) ;
		  result.put( "sv" , sv ) ;
		  result.put( "loss" , pv ) ;
		  result.put( "count_sv" , uv ) ;
		  result.put( "count_sv" , uv ) ;
		  result.put( "add_time" , add_time ) ;
		  result.put( "sub_time" , sub_time ) ;
		  result.put( "task_id" , task_id ) ;
		  result.put( "src_id" , src_id ) ;
		  result.put( "group_id" , group_id ) ;
		  result.put( "plan_id" , plan_id ) ;
		  result.put( "group_id" , group_id ) ;
		  result.put( "cur_id" , cur_id ) ;
		  result.put( "pos_id" , posId ) ;
		  return result ;
	 }
	 
	 public TableZzSrcDao getTableZzSrcDao( ) {
		  return tableZzSrcDao ;
	 }
	 
	 public void setTableZzSrcDao( TableZzSrcDao tableZzSrcDao ) {
		  this.tableZzSrcDao = tableZzSrcDao ;
	 }
	 
}
