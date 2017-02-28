package com.lhq.ssm2.util.Sms_SendUtils ;

import org.springframework.stereotype.Controller ;

import com.lhq.ssm2.domain.PIN ;
import com.taobao.api.ApiException ;
import com.taobao.api.DefaultTaobaoClient ;
import com.taobao.api.TaobaoClient ;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest ;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse ;

/**
 *
 * @author 银毫
 * @param 短信签名默认是大鱼测试
 */
@ Controller( "Sms_SendUtil" )
public class Sms_SendUtil extends PIN {
	 public Sms_SendUtil( ) {
		  System.out.println( "88888888" ) ;
	 }
	 // public static void main( String[] args ) {
	 // String name = "ddd" ;
	 // String pwd = "dfdf" ;
	 // String tel = "18757589409" ;
	 // try {
	 //
	 // String a = new Sms_SendUtil( ).SendPIN( name , pwd , tel ) ;
	 // }
	 // catch ( ApiException e ) {
	 // // TODO 自动生成的 catch 块
	 // e.printStackTrace( ) ;
	 // }
	 // }
	 // PIN- personal identification number.
	 /**
	  *
	  * @param name
	  *            用户名
	  * @param pwd
	  *            登入密码
	  * @param tel
	  *            电话号码
	  * @return
	  * @throws ApiException
	  */

	 public String SendPIN( String name , String pwd , String tel ) throws ApiException {
		  TaobaoClient client = new DefaultTaobaoClient( PIN.URL , PIN.APPKEY , PIN.SECRET ) ;
		  AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest( ) ;
		  // req.setExtend( "yeqing" ) ; // 会员id
		  req.setSmsType( "normal" ) ;// 短信类型
		  // 短信签名，传入的短信签名必须是在阿里大于
		  req.setSmsFreeSignName( "大鱼测试" ) ;// 短信签名
		  req.setSmsParamString( "{name:"
					 + name
					 + ",pwd:"
					 + pwd
 + "}" ) ;// 短信模板变量，传参规则{"key":"value"}，
		  req.setRecNum( tel ) ; // 发送手机号码
		  req.setSmsTemplateCode( "SMS_24535036" ) ;// 短信模板id
		  // Sms_SendUtil.loger.info( "test" ) ;
		  AlibabaAliqinFcSmsNumSendResponse rsp = client.execute( req ) ;
		  System.out.println( rsp.getBody( ) ) ;
		  // Sms_SendUtil.loger.info( "test" ) ;
		  return "success" ;
	 }
	 // // PIN- personal identification number.
	 // public String SendPIN( String name , String pwd , String tel ) throws ApiException {
	 // TaobaoClient client = new DefaultTaobaoClient( url , appkey , secret ) ;
	 // AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest( ) ;
	 // // req.setExtend( "wewewe" ) ; // 会员id
	 // req.setSmsType( "normal" ) ;// 短信类型
	 // // 短信签名，传入的短信签名必须是在阿里大于
	 // req.setSmsFreeSignName( "大鱼测试" ) ;// 短信签名
	 // req.setSmsParamString( "{\"name\":\"ddd\",\"pwd\":\"test002\"}" ) ;// 短信模板变量，传参规则{"key":"value"}，
	 // req.setRecNum( "18757589409" ) ; // 发送手机号码
	 // req.setSmsTemplateCode( "SMS_24535036" ) ;// 短信模板id
	 // // Sms_SendUtil.loger.info( "test" ) ;
	 // AlibabaAliqinFcSmsNumSendResponse rsp = client.execute( req ) ;
	 // System.out.println( rsp.getBody( ) ) ;
	 // // Sms_SendUtil.loger.info( "test" ) ;
	 // return "success" ;
	 // }
}
