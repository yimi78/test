package com.lhq.ssm2.test;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

import com.lhq.ssm2.service.sms_sendService ;

@ RunWith( SpringJUnit4ClassRunner.class )
@ ContextConfiguration( locations = { "classpath:spring-mybatis.xml" } )
/**
 *
 * @author 银毫
 *	@parame 注意事项 pwd的参数不能只是数字 要字符格式
 */
public class sms_pin_test {
	 @ Autowired
	 private sms_sendService sendService ;
	 private String          name = "yinhao" ;
	 private String          pwd  = "eee12334" ;
	 private String          tel  = "18757589409" ;

	 @ Test
	 public void sms_pin_test1( ) {
		  System.out.println( sendService.sms_send( name , pwd , tel ) ) ;
	 }
}
