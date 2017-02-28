package com.lhq.ssm2.util ;

import java.util.Map ;
import java.util.Random ;

import org.apache.commons.lang3.StringUtils ;
import org.apache.commons.mail.EmailException ;
import org.apache.commons.mail.SimpleEmail ;

/**
 * 邮件工具类
 * 
 * @author lhq
 *
 */
public class EmailUtils implements Runnable {
	 
	 public void serviceSend( final String title , final String msg , String addresses , Map<String , String> map ) {
		  EmailUtils email = new EmailUtils( ) ;
		  email.serviceAddresses = addresses ;
		  email.serviceTitle = title ;
		  email.serviceMsg = msg ;
		  email.serviceSendAdd = "service@koolbao.com" ;
		  email.serviceSendpwd = "koolma2010" ;
		  email.serviceEncode = "utf-8" ;
		  email.serviceHostName = "smtp.exmail.qq.com" ;
		  email.serviceForm = "service@koolbao.com" ;
		  email.serviceSenderName = "酷宝数据" ;
		  email.map = map ;
		  Thread t1 = new Thread( email ) ;
		  t1.run( ) ;
		  // 发送方
	 }
	 
	 public void serviceSend( String title , String msg , String addresses , String serviceSendAdd ,
				String serviceSendpwd , String serviceEncode , String serviceHostName , String serviceForm ,
				String serviceSenderName , int servicePort , Map<String , String> map ) {
		  
		  EmailUtils email = new EmailUtils( ) ;
		  if ( StringUtils.isBlank( addresses ) ) {
				addresses = "" ;
		  }
		  if ( StringUtils.isBlank( title ) ) {
				title = "" ;
		  }
		  if ( StringUtils.isBlank( msg ) ) {
				msg = "" ;
		  }
		  if ( StringUtils.isBlank( serviceSendAdd ) ) {
				serviceSendAdd = "service@koolbao.com" ;
		  }
		  if ( StringUtils.isBlank( serviceSendpwd ) ) {
				serviceSendAdd = "koolma2010" ;
		  }
		  if ( StringUtils.isBlank( serviceEncode ) ) {
				serviceEncode = "utf-8" ;
		  }
		  if ( StringUtils.isBlank( serviceHostName ) ) {
				serviceHostName = "smtp.exmail.qq.com" ;
		  }
		  if ( StringUtils.isBlank( serviceForm ) ) {
				serviceForm = "service@koolbao.com" ;
		  }
		  if ( StringUtils.isBlank( serviceSenderName ) ) {
				serviceSenderName = "" ;
		  }
		  if ( servicePort == 0 ) {
				servicePort = 465 ;
		  }
		  email.map = map ;
		  email.serviceAddresses = addresses ;
		  email.serviceTitle = title ;
		  email.serviceMsg = msg ;
		  email.serviceSenderName = serviceSenderName ;
		  email.serviceSendAdd = serviceSendAdd ;
		  email.serviceSendpwd = serviceSendpwd ;
		  email.serviceEncode = serviceEncode ;
		  email.serviceHostName = serviceHostName ;
		  email.serviceForm = serviceForm ;
		  email.servicePort = servicePort ;
		  Thread t1 = new Thread( email ) ;
		  t1.run( ) ;
		  // 发送方
	 }
	 
	 private String					serviceAddresses  = "" ;
	 private String					serviceTitle		= "" ;
	 private String					serviceMsg		  = "" ;
	 private String					serviceSendAdd	 = "" ;
	 private String					serviceSendpwd	 = "" ;
	 private String					serviceEncode	  = "" ;
	 private String					serviceHostName	= "" ;
	 private String					serviceSenderName = "" ;
	 private String					serviceForm		 = "" ;
	 private Map<String , String> map					= null ;
	 private int						servicePort		 = 465 ;
	 
	 public void run( ) {
		  SimpleEmail simpleMail = new SimpleEmail( ) ;
		  simpleMail.setAuthentication( serviceSendAdd , serviceSendpwd ) ;
		  simpleMail.setSSL( true ) ;
		  simpleMail.setCharset( serviceEncode ) ;
		  simpleMail.setHostName( serviceHostName ) ; // 服务器
		  simpleMail.setSmtpPort( servicePort ) ;
		  simpleMail.setContent( serviceMsg , "text/html;charset=utf-8" ) ;
		  try {
				if ( StringUtils.isBlank( serviceSenderName ) ) {
					 simpleMail.setFrom( serviceForm ) ;
				}
				else {
					 simpleMail.setFrom( serviceForm , serviceSenderName ) ;
				}
				
				String[] email_addr = null ;
				if ( serviceAddresses.contains( ";" ) ) {
					 email_addr = serviceAddresses.split( ";" ) ;
				}
				else if ( serviceAddresses.contains( "," ) ) {
					 email_addr = serviceAddresses.split( "," ) ;
				}
				else {
					 email_addr = new String[] { serviceAddresses } ;
				}
				simpleMail.setCharset( serviceEncode ) ;
				simpleMail.setSubject( serviceTitle ) ;
				for ( String e : email_addr ) {
					 simpleMail.addTo( e ) ;
				}
				String msgs = "" ;
				boolean sendIsSuccess = false ;
				int maxCycNum = 10 ;
				int cycNum = 0 ;
				Exception es = null ;
				while ( !sendIsSuccess || cycNum > maxCycNum ) {
					 try {
						  cycNum++ ;
						  simpleMail.send( ) ;
						  sendIsSuccess = true ;
					 }
					 catch ( Exception e ) {
						  es = e ;
						  e.printStackTrace( ) ;
						  Random r = new Random( 10 ) ;
						  try {
								Thread.sleep( ( r.nextInt( 60 ) + 1 ) * 1000 ) ;
						  }
						  catch ( InterruptedException e1 ) {
								// TODO Auto-generated catch block
								e1.printStackTrace( ) ;
						  }
					 }
				}
				if ( cycNum >= maxCycNum ) {
					 map.put( "errInfo" , es.getMessage( ) ) ;
					 es.printStackTrace( ) ;
				}
				else {
					 map.remove( "errInfo" ) ;
				}
		  }
		  catch ( EmailException e ) {
				e.printStackTrace( ) ;
				map.put( "errInfo" , e.getMessage( ) ) ;
		  }
	 }
	 
	 public String getServiceAddresses( ) {
		  return serviceAddresses ;
	 }
	 
	 public void setServiceAddresses( String serviceAddresses ) {
		  this.serviceAddresses = serviceAddresses ;
	 }
	 
	 public String getServiceTitle( ) {
		  return serviceTitle ;
	 }
	 
	 public void setServiceTitle( String serviceTitle ) {
		  this.serviceTitle = serviceTitle ;
	 }
	 
	 public String getServiceMsg( ) {
		  return serviceMsg ;
	 }
	 
	 public void setServiceMsg( String serviceMsg ) {
		  this.serviceMsg = serviceMsg ;
	 }
	 
	 public String getServiceSendAdd( ) {
		  return serviceSendAdd ;
	 }
	 
	 public void setServiceSendAdd( String serviceSendAdd ) {
		  this.serviceSendAdd = serviceSendAdd ;
	 }
	 
	 public String getServiceSendpwd( ) {
		  return serviceSendpwd ;
	 }
	 
	 public void setServiceSendpwd( String serviceSendpwd ) {
		  this.serviceSendpwd = serviceSendpwd ;
	 }
	 
	 public String getServiceEncode( ) {
		  return serviceEncode ;
	 }
	 
	 public void setServiceEncode( String serviceEncode ) {
		  this.serviceEncode = serviceEncode ;
	 }
	 
	 public String getServiceHostName( ) {
		  return serviceHostName ;
	 }
	 
	 public void setServiceHostName( String serviceHostName ) {
		  this.serviceHostName = serviceHostName ;
	 }
	 
	 public String getServiceForm( ) {
		  return serviceForm ;
	 }
	 
	 public void setServiceForm( String serviceForm ) {
		  this.serviceForm = serviceForm ;
	 }
	 
	 public Map<String , String> getMap( ) {
		  return map ;
	 }
	 
	 public void setMap( Map<String , String> map ) {
		  this.map = map ;
	 }
	 
	 public int getServicePort( ) {
		  return servicePort ;
	 }
	 
	 public void setServicePort( int servicePort ) {
		  this.servicePort = servicePort ;
	 }
	 
	 public static void main( String[] args ) {
		  EmailUtils tt = new EmailUtils( ) ;
		  // tt.serviceSend("test", "test", "haoqi.liang@koolbao.com");
	 }
}
