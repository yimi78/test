package com.lhq.ssm2.controller ;

import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;

import org.apache.commons.lang3.StringUtils ;
import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.RequestMapping ;

import com.koolma.main.ExInfo ;
import com.koolma.main.ExKid ;
import com.lhq.ssm2.util.ParaUtil ;

/**
 * 导出订单
 * 
 * @ClassName: ExTradeController
 * @Description: TODO
 * @author: lhq
 * @date: Nov 25, 2016 11:21:42 AM
 */
@ Controller
@ RequestMapping( "/" )
public class ExTradeController {
	 
	 /**
	  * 广告平台数据导出
	  * 
	  * @Title: excommand
	  * @Description: TODO
	  * @param request
	  * @param model
	  * @return
	  * @return: String
	  */
	 @ RequestMapping( "/huihe/excommand" )
	 public String excommand( HttpServletRequest request , Model model ) {
		  String resultJsp = "huihe/exportTrade" ;
		  String date = request.getParameter( "date" ) ;
		  String version = request.getParameter( "version" ) ;
		  Map<String , String> param = ParaUtil.initParam( date , version ) ;
		  
		  if ( StringUtils.isNotBlank( param.get( "msg" ) ) ) {
				model.addAttribute( "mgs" , param.get( "msg" ) ) ;
				return resultJsp ;
		  }
		  
		  String[] args = ParaUtil.getArgs( param ) ;
		  ExInfo.main( args ) ;
		  model.addAttribute( "date" , date ) ;
		  model.addAttribute( "version" , version ) ;
		  model.addAttribute( "msg" , "运行完成" ) ;
		  return resultJsp ;
	 }
	 
	 /**
	  * 指定广告分析导出
	  * 
	  * @Title: excommandKid
	  * @Description: TODO
	  * @param request
	  * @param model
	  * @return
	  * @return: String
	  */
	 @ RequestMapping( "/huihe/excommandKid" )
	 public String excommandKid( HttpServletRequest request , Model model ) {
		  String resultJsp = "huihe/exportTrade" ;
		  String date = request.getParameter( "date" ) ;
		  String version = request.getParameter( "version" ) ;
		  Map<String , String> param = ParaUtil.initParam( date , version ) ;
		  
		  if ( StringUtils.isNotBlank( param.get( "msg" ) ) ) {
				model.addAttribute( "mgs" , param.get( "msg" ) ) ;
				return resultJsp ;
		  }
		  
		  String[] args = ParaUtil.getArgs( param ) ;
		  ExKid.main( args ) ;
		  model.addAttribute( "date" , date ) ;
		  model.addAttribute( "version" , version ) ;
		  model.addAttribute( "msg" , "运行完成" ) ;
		  return resultJsp ;
	 }
}