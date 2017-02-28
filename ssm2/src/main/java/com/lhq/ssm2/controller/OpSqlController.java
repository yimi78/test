/*
 * $Id$
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package com.lhq.ssm2.controller ;

import java.io.File ;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date ;
import java.util.List ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils ;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.RequestMapping ;

import com.koolma.main.Begin ;
import com.koolma.main.BeginKid ;
import com.koolma.main.Run ;
import com.koolma.model.ADSqlInfo ;
import com.koolma.model.KidExInfo ;
import com.koolma.model.KidSqlInfo;
import com.koolma.model.RunStatus ;
import com.koolma.model.TradeSqlInfo ;
import com.koolma.stants.SystemStantsTrade ;
import com.koolma.util.ErrorInfo;
import com.lhq.ssm2.util.ParaUtil ;
import com.lhq.ssm2.util.StringUtil ;
import com.lhq.ssm2.util.shell.CommandList ;

@ Controller
@Scope("prototype")
@ RequestMapping( "/" )
public class OpSqlController {
	 @ RequestMapping( "huihe/toProSql" )
	 public String toProSql( HttpServletRequest request , Model model ) {
		  String isKid = request.getParameter( "isKid" ) ;
		  String version = request.getParameter( "version" ) ;
		  String resultJsp = "huihe/ProSqlCommand" ;
		  String flage = "1" ;
		  
		  model.addAttribute( "isKid" , isKid ) ;
		  model.addAttribute( "version" , version ) ;
		  model.addAttribute( "flage" , flage ) ;
		  return resultJsp ;
	 }
	 
	 @ RequestMapping( "huihe/proSql.do" )
	 public String proSql( HttpServletRequest request , Model model ,HttpServletResponse res ) {
		 res.setContentType("text/html; charset=utf-8"); 
		  String date = request.getParameter( "date" ) ;
		  String date2 = request.getParameter( "date2" ) ;
		  String date3 = request.getParameter( "date3" ) ;
		  String isKid = request.getParameter( "isKid" ) ;
		  String version = request.getParameter( "version" ) ;
		  String resultJsp = "huihe/ProSqlCommand" ;
		  String flage = "1" ;
		  String msg = "" ;
		  
		  Map<String , String> param = ParaUtil.initParam( date , version ) ;
		  
		  if ( StringUtils.isNotBlank( param.get( "msg" ) ) ) {
				model.addAttribute( "msg" , param.get( "msg" ) ) ;
				return resultJsp ;
		  }
		 
		  Run run = null ;
		  String[] args = ParaUtil.getArgs( param ) ;
		  if ( "n".equals( isKid.toLowerCase( ) ) ) {
				run = new Begin( ) ;
		  }
		  else {
				run = new BeginKid( ) ;
		  }
		  RunStatus rs = run.run( args , res ) ;
		  try {
				msg += StringUtil.infoCon( ( List<String> ) rs.data ) ;
		  }
		  catch ( Exception e ) {
				
		  }
		  
		  model.addAttribute( "date" , date ) ;
		  model.addAttribute( "date2" , date2 ) ;
		  model.addAttribute( "date3" , date3 ) ;
		  model.addAttribute( "isKid" , isKid ) ;
		  model.addAttribute( "version" , version ) ;
		  model.addAttribute( "flage" , flage ) ;
		  model.addAttribute( "msg" , msg ) ;
		  return resultJsp ;
	 }
	 
	 @ RequestMapping( "huihe/runSQL" )
	 public String runSQL( HttpServletRequest request , Model model ,HttpServletResponse res) throws ParseException {
		  res.setContentType("text/html; charset=utf-8"); 
		  ErrorInfo errorInfo =new ErrorInfo();
		  errorInfo.setRes(res);
		  String date = request.getParameter( "date" ) ;
		  String date2 = request.getParameter( "date2" ) ;
		  String date3 = request.getParameter( "date3" ) ;
		  String isKid = request.getParameter( "isKid" ) ;
		  String version = request.getParameter( "version" ) ;
		  String resultJsp = "huihe/ProSqlCommand" ;
		  String flage = "2" ;
		  String msg = "" ;
		  
		  Map<String , String> param = ParaUtil.initParam( date2 , version ) ;
		  
		  TradeSqlInfo tsi = null ;
		  if ( "y".equals( isKid ) ) {
			  tsi = new KidSqlInfo( DateUtils.parseDate(date2,new String[] { "yyyy-MM-dd" }) , param.get( "add" ) ) ;
		  }
		  else {
			  tsi = new ADSqlInfo( DateUtils.parseDate(date2,new String[] { "yyyy-MM-dd" }) , param.get( "add" ) ) ;
		  }
		  
		  if ( !SystemStantsTrade.IS_WIND ) {
				
				File file = new File( tsi.sqlPath ) ;
				if ( !file.exists( ) ) {
					 msg += ( "sql文件不存在，请生成sql文件！" ) ;
					 errorInfo.print("sql："+tsi.sqlPath+ ">sql文件不存在，请生成sql文件！" );
					 return resultJsp ;
				}
		  }
		  CommandList cl = new CommandList( ) ;
		  errorInfo.print("开始执行sql："+tsi.sqlPath+"请耐心等待");
		  msg = StringUtil.infoCon( cl.runSQL( tsi.sqlPath ) ) ;
		  errorInfo.print("执行"+tsi.sqlPath+"结束,返回：(空为没有报错)\r\n["+msg+"]");
		  
		  model.addAttribute( "date" , date ) ;
		  model.addAttribute( "date2" , date2 ) ;
		  model.addAttribute( "date3" , date3 ) ;
		  model.addAttribute( "isKid" , isKid ) ;
		  model.addAttribute( "version" , version ) ;
		  model.addAttribute( "flage" , flage ) ;
		  model.addAttribute( "msg" , msg ) ;
		  return resultJsp ;
	 }
	 
	 @ RequestMapping( "huihe/restoreSQL" )
	 public String restoreSQL( HttpServletRequest request , Model model ,HttpServletResponse res) throws ParseException {
		  res.setContentType("text/html; charset=utf-8"); 
		  ErrorInfo errorInfo =new ErrorInfo();
		  errorInfo.setRes(res);
		  String date = request.getParameter( "date" ) ;
		  String date2 = request.getParameter( "date2" ) ;
		  String date3 = request.getParameter( "date3" ) ;
		  String isKid = request.getParameter( "isKid" ) ;
		  String version = request.getParameter( "version" ) ;
		  String resultJsp = "huihe/ProSqlCommand" ;
		  String flage = "3" ;
		  String msg = "" ;
		  
		  TradeSqlInfo tsi = null ;
		  if ( "y".equals( isKid ) ) {
				tsi = new KidExInfo( DateUtils.parseDate(date3,new String[] { "yyyy-MM-dd" }) ) ;
		  }
		  else {
				tsi = new ADSqlInfo( DateUtils.parseDate(date3,new String[] { "yyyy-MM-dd" }) ) ;
		  }
		  
		  if ( !SystemStantsTrade.IS_WIND ) {
				
				File file = new File( tsi.sqlPathRestore ) ;
				if ( !file.exists( ) ) {
					 msg += ( "sql文件不存在，请生成sql文件！" ) ;
					 errorInfo.print("sql："+tsi.sqlPathRestore+ ">sql文件不存在，请生成sql文件！" );
					 return resultJsp ;
				}
		  }
		  CommandList cl = new CommandList( ) ;
		  errorInfo.print("开始执行sql："+tsi.sqlPathRestore+"请耐心等待");
		  msg = StringUtil.infoCon( cl.runSQL( tsi.sqlPathRestore ) ) ;
		  errorInfo.print("执行"+tsi.sqlPathRestore+"结束,返回：(空为没有报错)\r\n["+msg+"]");
		  model.addAttribute( "date" , date ) ;
		  model.addAttribute( "date2" , date2 ) ;
		  model.addAttribute( "date3" , date3 ) ;
		  model.addAttribute( "isKid" , isKid ) ;
		  model.addAttribute( "version" , version ) ;
		  model.addAttribute( "flage" , flage ) ;
		  model.addAttribute( "msg" , msg ) ;
		  return resultJsp ;
	 }
	 
}
