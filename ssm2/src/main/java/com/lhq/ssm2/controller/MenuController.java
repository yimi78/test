package com.lhq.ssm2.controller ;

import javax.servlet.http.HttpServletRequest ;

import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.RequestMapping ;

@ Controller
@ RequestMapping( "/" )
public class MenuController {
	 
	 @ RequestMapping( "mainMenu" )
	 public String toIndex( HttpServletRequest request , Model model ) {
		  
		  return "mainMenu" ;
	 }
	 
	 @ RequestMapping( "huihe/MainMenu" )
	 public String toHuihexIndex( HttpServletRequest request , Model model ) {
		  
		  return "/huihe/MainMenu" ;
	 }
	 
	 @ RequestMapping( "fileupload/uploadTxt" )
	 public String uploadTxt( HttpServletRequest request , Model model ) {
		  return "fileupload/uploadTxt" ;
	 }
	 
	 @ RequestMapping( "fileupload/toUploadExcel" )
	 public String toUploadExcel( HttpServletRequest request , Model model ) {
		  return "fileupload/uploadExcel" ;
	 }
	 
	 @ RequestMapping( "huihe/exportTrade" )
	 public String exportTrade( HttpServletRequest request , Model model ) {
		  return "huihe/exportTrade" ;
	 }
	 
	 @ RequestMapping( "toProSql" )
	 public String toProSql( HttpServletRequest request , Model model ) {
		  model.addAttribute( "isKid" , request.getParameter( "isKid" ) ) ;
		  return "huihe/ProSqlCommand" ;
	 }
	 
	 @ RequestMapping( "other" )
	 public String other( HttpServletRequest request , Model model ) {
		  model.addAttribute( "isKid" , request.getParameter( "isKid" ) ) ;
		  return "huihe/other" ;
	 }
	 
}