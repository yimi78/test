package com.lhq.ssm2.controller ;

import java.io.File ;
import java.io.IOException ;
import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.Date ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;

import org.apache.commons.io.FileUtils ;
import org.apache.commons.lang3.StringUtils ;
import org.springframework.stereotype.Controller ;
import org.springframework.ui.ModelMap ;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RequestParam ;
import org.springframework.web.multipart.MultipartFile ;

import com.koolma.model.ADSqlInfo ;
import com.koolma.model.KidSqlInfo ;
import com.koolma.model.TradeSqlInfo ;
import com.lhq.ssm2.util.ParaUtil ;

@ Controller
@ RequestMapping( "/" )
public class UploadController {
	 
	 /**
	  * 上传指定广告的店铺
	  * 
	  * @Title: upload
	  * @Description: TODO
	  * @param file
	  * @param request
	  * @param model
	  * @return
	  * @return: String
	  */
	 @ RequestMapping( value = "/uploadTxt" )
	 public String uploadTxt( @ RequestParam( value = "file" , required = false ) MultipartFile file ,
				HttpServletRequest request , ModelMap model ) {
		  
		  String path = request.getSession( ).getServletContext( ).getRealPath( "upload" ) ;
		  String fileName = file.getOriginalFilename( ) ;
		  String msg = "" ;
		  
		  File targetFile = new File( path , fileName ) ;
		  if ( !targetFile.getParentFile( ).exists( ) ) {
				targetFile.getParentFile( ).mkdirs( ) ;
		  }
		  
		  // 保存
		  try {
				file.transferTo( targetFile ) ;
		  }
		  catch ( Exception e ) {
				e.printStackTrace( ) ;
		  }
		  
		  TradeSqlInfo tsi = new TradeSqlInfo( new Date( ) ) ;
		  File shopInfo = new File( tsi.kid_input_path ) ;
		  try {
				FileUtils.copyFile( targetFile , shopInfo ) ;
		  }
		  catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace( ) ;
				msg = ( "上传文件错误，错误信息" + e.getMessage( ) ) ;
				model.addAttribute( "msg" , msg ) ;
				return "fileupload/uploadTxt" ;
		  }
		  if ( shopInfo.exists( ) ) {
				msg = "上传文件成功！！" + shopInfo.getAbsolutePath( ) ;
		  }
		  else {
				msg = "上传文件不存在！！" + shopInfo.getAbsolutePath( ) ;
		  }
		  
		  model.addAttribute( "msg" , msg ) ;
		  return "fileupload/uploadTxt" ;
	 }
	 
	 /**
	  * 上传excel
	  * 
	  * @Title: toUploadExcel
	  * @Description: TODO
	  * @param file
	  * @param request
	  * @param model
	  * @return
	  * @return: String
	  */
	 @ RequestMapping( value = "/uploadExcel" )
	 public String uploadExcel( @ RequestParam( value = "file" , required = false ) MultipartFile file ,
				HttpServletRequest request , ModelMap model ) {
		  
		  SimpleDateFormat sd2 = new SimpleDateFormat( "yyyy-MM-dd" ) ;
		  String path = request.getSession( ).getServletContext( ).getRealPath( "upload" ) ;
		  String fileName = file.getOriginalFilename( ) ;
		  String date = request.getParameter( "date" ) ;
		  String version = request.getParameter( "version" ) ;
		  String isKid = request.getParameter( "isKid" ) ;
		  String result = "fileupload/uploadExcel" ;
		  model.addAttribute( "date" , date ) ;
		  model.addAttribute( version , version ) ;
		  
		  Map<String , String> param = ParaUtil.initParam( date , version ) ;
		  if ( StringUtils.isNotBlank( param.get( "msg" ) ) ) {
				model.addAttribute( "msg" , param.get( "msg" ) ) ;
				return result ;
		  }
		  String msg = "" ;
		  
		  File targetFile = new File( path , fileName ) ;
		  if ( !targetFile.getParentFile( ).exists( ) ) {
				targetFile.getParentFile( ).mkdirs( ) ;
		  }
		  
		  // 保存
		  try {
				file.transferTo( targetFile ) ;
		  }
		  catch ( Exception e ) {
				e.printStackTrace( ) ;
		  }
		  
		  Date times = null ;
		  try {
				times = sd2.parse( param.get( "start_date" ) ) ;
		  }
		  catch ( ParseException e1 ) {
				// TODO Auto-generated catch block
				e1.printStackTrace( ) ;
		  }
		  TradeSqlInfo tsi = null ;
		  if ( "n".equals( isKid ) ) {
				tsi = new ADSqlInfo( times , param.get( "add" ) ) ;
		  }
		  else {
				tsi = new KidSqlInfo( times , param.get( "add" ) ) ;
		  }
		  
		  try {
				FileUtils.copyFile( targetFile , new File( tsi.excelFilePath ) ) ;
				msg = "上传成功！！！文件地址" + tsi.excelFilePath ;
		  }
		  catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace( ) ;
				msg = ( "上传文件错误，错误信息" + e.getMessage( ) ) ;
		  }
		  model.addAttribute( "msg" , msg ) ;
		  return result ;
	 }
}