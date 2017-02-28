package com.lhq.ssm2.controller ;

import java.io.File ;
import java.io.IOException ;
import java.io.OutputStream ;
import java.util.ArrayList ;
import java.util.Collections ;
import java.util.Comparator ;
import java.util.Date ;
import java.util.List ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import org.apache.commons.io.FileUtils ;
import org.apache.commons.lang3.StringUtils ;
import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.RequestMapping ;

import com.koolma.model.KidExInfo ;
import com.koolma.model.TradeSqlInfo ;

@ Controller
@ RequestMapping( "/filedownload" )
public class FileDownloadController {
	 
	 @ RequestMapping( "getFileList" )
	 public String execute( HttpServletRequest request , Model model ) throws Exception {
		  String fileType = request.getParameter( "fileType" ) ;
		  TradeSqlInfo tsi = new KidExInfo( new Date( ) ) ;
		  String path = "" ;
		  if ( StringUtils.isBlank( fileType ) || "0".equals( fileType ) ) {
				fileType = "0" ;
				path = tsi.downTrade ;
		  }
		  else if ( fileType.equals( "1" ) ) {
				path = ( new File( tsi.excelFilePath ) ).getParent( ) ;
		  }
		  else if ( fileType.equals( "2" ) ) {
				path = ( new File( tsi.sqlPath ) ).getParent( ) ;
		  }
		  
		  List<String> fileList = new ArrayList<String>( ) ;
		  
		  File fs = new File( path ) ;
		  String parentPath = fs.getAbsolutePath( ) ;
		  File de = new File( parentPath ) ;
		  fileList.add( "List" ) ;
		  for ( String item : fs.list( ) ) {
				fileList.add( parentPath + "/" + item ) ;
		  }
		  Com com = new Com( ) ;
		  Collections.sort( fileList , com ) ;
		  
		  model.addAttribute( "fileType" , fileType ) ;
		  model.addAttribute( "fileList" , fileList ) ;
		  return "filedownload/index" ;
	 }
	 
	 @ RequestMapping( "download" )
	 public void download( HttpServletRequest request , HttpServletResponse response , Model model ) throws Exception {
		  
		  String path = request.getParameter( "inputPath" ) ;
		  File file = new File( path ) ;
		  if ( file == null || !file.exists( ) ) { return ; }
		  OutputStream out = null ;
		  try {
				response.reset( ) ;
				response.setContentType( "application/octet-stream; charset=utf-8" ) ;
				response.setHeader( "Content-Disposition" , "attachment; filename=" + file.getName( ) ) ;
				out = response.getOutputStream( ) ;
				out.write( FileUtils.readFileToByteArray( file ) ) ;
				out.flush( ) ;
		  }
		  catch ( IOException e ) {
				e.printStackTrace( ) ;
		  }
		  finally {
				if ( out != null ) {
					 try {
						  out.close( ) ;
					 }
					 catch ( IOException e ) {
						  e.printStackTrace( ) ;
					 }
				}
		  }
	 }
	 
	 class Com implements Comparator {
		  @ Override
		  public int compare( Object o1 , Object o2 ) {
				// TODO Auto-generated method stub
				return o2.toString( ).compareTo( o1.toString( ) ) ;
		  }
		  
	 }
	 
}
