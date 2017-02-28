package com.lhq.ssm2.util.shell ;

import java.io.BufferedReader ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.List ;

import com.koolma.stants.SystemStantsTrade ;

public class Command {
	 public static void main( String[] args ) throws Exception {
		  Command comd = new Command( ) ;
		  comd.run( args ) ;
	 }
	 
	 public List<String> run( String[] args ) {
		  if ( args == null || args.length == 0 || args[0].contains( "help" ) ) {
				System.out.println( "java tests /bin/sh -c \"ps -ef|grep printDate\"" ) ;
				return Arrays.asList( new String[] { "java tests /bin/sh -c \"ps -ef|grep printDate\"" } ) ;
		  }
		  else {
				return command( args ) ;
		  }
	 }
	 
	 public List<String> command( String[] cmds ) {
		  List<String> list = new ArrayList<String>( ) ;
		  String cmdTxt = "" ;
		  for ( String item : cmds ) {
				cmdTxt += ( item + " " ) ;
		  }
		  list.add( "命令:" + cmdTxt ) ;
		  if ( SystemStantsTrade.IS_WIND ) {
				list.add( "当前为winds,测试成功！" ) ;
				return list ;
		  }
		  try {
				Process pro = Runtime.getRuntime( ).exec( cmds ) ;
				
				InputStream in = pro.getInputStream( ) ;
				BufferedReader read = new BufferedReader( new InputStreamReader( in ) ) ;
				String line = null ;
				while ( ( line = read.readLine( ) ) != null ) {
					 if ( !line.trim( ).startsWith( "[DEBUG]" ) ) {
						  list.add( line ) ;
					 }
				}
				read.close( ) ;
				in.close( ) ;
				pro.waitFor( ) ;
				pro.destroy( ) ;
		  }
		  catch ( Exception e ) {
				System.out.println( "命令异常！" ) ;
				e.printStackTrace( ) ;
		  }
		  return list ;
	 }
}