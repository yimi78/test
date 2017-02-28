package com.lhq.ssm2.service.impl ;

import javax.annotation.Resource ;

import org.springframework.stereotype.Service ;

import com.lhq.ssm2.service.sms_sendService ;
import com.lhq.ssm2.util.Sms_SendUtils.Sms_SendUtil ;
import com.taobao.api.ApiException ;

@ Service( "sms_sendServiceImpl" )
public class sms_sendServiceImpl implements sms_sendService {
	 @ Resource
	 private Sms_SendUtil sms ;
	 
	 // 短信验证实现
	 @ Override
	 public String sms_send( String name , String pwd , String tel ) {
		  String yinhao = null ;
		  try {
				yinhao = sms.SendPIN( name , pwd , tel ) ;
		  }
		  catch ( ApiException e ) {
				e.printStackTrace( ) ;
		  }
		  return yinhao ;
	 }

}
