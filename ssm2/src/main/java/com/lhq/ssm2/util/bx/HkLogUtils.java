/**   
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: T.java 
 * @Prject: maptest
 * @Package: com.koolbao.maptest 
 * @Description: TODO
 * @author: lhq   
 * @date: Dec 27, 2016 5:56:01 PM 
 * @version: V1.0   
 */
package com.lhq.ssm2.util.bx;

import java.io.File;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;

public class HkLogUtils {
	private String fullFileName = "hkLog/hkLog";
	private String log = " {time} | {request} | {response}\n\r"
			+ "------------------------------------\n\r";
	
	private String time ;
	private String request ;
	private String response ;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public HkLogUtils( String time, String request, String response ){
		this.time = time;
		this.request = request;
		this.response = response;
	}
	public void logHk(){
		String log = this.log.replace("{time}", time);
		log = log.replace("{request}", request);
		log = log.replace("{response}", response);
		File file = new File(fullFileName);
		try {
			FileUtils.write(file, log, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
