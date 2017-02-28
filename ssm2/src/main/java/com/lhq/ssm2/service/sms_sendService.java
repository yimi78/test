package com.lhq.ssm2.service;

import org.springframework.stereotype.Service ;

@ Service( "sms_sendService" )
public interface sms_sendService {
	 // 短信验证码
	 public abstract String sms_send( String name , String pwd , String tel ) ;
}
