package com.lhq.ssm2.util.shell ;

import java.text.SimpleDateFormat ;
import java.util.List ;

public class CommandList {
	 
	 public SimpleDateFormat sd1	 = new SimpleDateFormat( "MMdd" ) ;
	 
	 public String[]			runSQL = { "/bin/sh" , "-c" ,
				"cd /root/koolbao_apps/sql/kmUtil/result;mysql -h121.199.20.217 -p3306 -uroot -pkoolma2010 < " } ;
	 
	 /**
	  * 运行sql
	  * 
	  * @param sql
	  *            sql文件名
	  * @return
	  */
	 public List<String> runSQL( String fileName ) {
		  Command comd = new Command( ) ;
		  int size = runSQL.length ;
		  runSQL[size - 1] = runSQL[size - 1] + fileName ;
		  
		  return comd.run( runSQL ) ;
	 }
	 
	 /**
	  * 测试
	  * 
	  * @param time
	  * @return
	  */
	 public List<String> exExcel( ) {
		  Command comd = new Command( ) ;
		  return comd.run( null ) ;
	 }
}
