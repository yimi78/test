package com.lhq.ssm2.util.bx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**   
 * Copyright © 2017 酷玛. All rights reserved.
 * 
 * @Title: GetUrl.java 
 * @Prject: kmUtilTrade
 * @Package:  
 * @Description: TODO
 * @author: lhq   
 * @date: Jan 3, 2017 6:50:03 PM 
 * @version: V1.0   
 */

/**
 * @ClassName: GetUrl
 * @Description: TODO
 * @author: lhq
 * @date: Jan 3, 2017 6:50:03 PM
 */
public class GetUrl {

	public  String HK(String data, String url) {
		try {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("uid", "hangzhou");
			String timestamp = String.valueOf(System.currentTimeMillis());
			pMap.put("timestamp", timestamp);// 交易时间戳,格林威治时间，精确到毫秒。
			String nonce = RandomStringUtils.randomAlphanumeric(20);
			pMap.put("nonce", nonce);// 随机数，长度20，数字+字母
			AESUtils aESUtils = new AESUtils();
			String sss = aESUtils
					.Encrypt(
							data,
							aESUtils.parseHexStr2Byte("B8BAE42824624664AFD6A3B36D64FEA6"),
							aESUtils.parseHexStr2Byte("653522CAC9EC494AA6052F4314C51B7C"));
			// System.out.println("*************加密后传输数据：*************" + sss);
			pMap.put("data", sss);
			String linkStr = new LinkUtil().getLinkString(pMap);//
			// 除去数组中的空值和签名参数，把数组所有元素，按照"参数=参数值"的模式用"&"字符拼接成字符串
			// System.out.println("请求前linkStr：" + linkStr);

			String signature = new MD5().sign(linkStr,
					"FEF305BE535847B48FB55BE534C2FAB1", "UTF-8");// 需要签名的字符串+密钥//
			// MD5加密//
			// 编码格式UTF-8
			// System.out.println("请求前加密签名" + signature);
			pMap.put("signature", signature);
			String reStr = postByProxy(url, pMap);
			return reStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "异常";
		}
	}

	public static void main(String[] args) {
		new GetUrl().run(args);
	}

	public  String run(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		String result = "";
		String data = "{\"appName\":\"\u674e\u56db\",\"appIdno\":\"620801199111295195\",\"appMobile\":\"15068050659\",\"product\":\"QP000587\"}";
		String url = "http://insurance.tobay.wang/web/hangzhou/effectInsurance";
		if (args.length == 1 && !"test".equals(args[0])) {
			data = args[0];
		}

		String s = HK(data, url);
		if (StringUtils.isBlank(s)) {
			System.out.println("返回为空，可能异常");
			result = "返回为空，可能异常";
		} else {
			System.out.println(s);
			result = s;
		}
		new HkLogUtils(sdf.format(new Date()),args[0],result).logHk();//记录日志
		return result;
	}

	public  String postByProxy(String url, Map<String, String> map) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			StringBuilder sb = new StringBuilder();
			Set<String> keyset = map.keySet();

			for (String key : keyset) {
				String value = map.get(key);
				sb.append(key).append("=").append(value).append("&");
			}
			sb.deleteCharAt(sb.length() - 1);

			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(sb);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;

	}
}
