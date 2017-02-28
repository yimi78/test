package com.lhq.ssm2.util.bx;

public class HkEntity {
//	appName=%E9%97%AE%E9%97%AE&appIdno=339005199501301619&
//			appMobile=15068050659&product=QP000587
	private String appName ;
	private String appIdno ;
	private String appMobile ;
	private String product ;
	private String appSex ;
	private String inName ;
	private String inSex ;
	private String answer1 ;
	private String answer2 ;
	private String answer3 ;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppIdno() {
		return appIdno;
	}
	public void setAppIdno(String appIdno) {
		this.appIdno = appIdno;
	}
	public String getAppMobile() {
		return appMobile;
	}
	public void setAppMobile(String appMobile) {
		this.appMobile = appMobile;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getAppSex() {
		return appSex;
	}
	public void setAppSex(String appSex) {
		this.appSex = appSex;
	}
	public String getInName() {
		return inName;
	}
	public void setInName(String inName) {
		this.inName = inName;
	}
	public String getInSex() {
		return inSex;
	}
	public void setInSex(String inSex) {
		this.inSex = inSex;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	@Override
	public String toString() {
//		String data = "{\"appName\":\"\u674e\u56db\",\"appIdno\":\"620801199111295195\",\"appMobile\":\"15068050659\",\"product\":\"QP000587\"}";
		String data = "{\"appName\":\""+appName+"\",\"appIdno\":\""+appIdno+"\",\"appMobile\":\""+appMobile+"\",\"product\":\""+product+"\"}";
		return data;
	}
	
}
