package com.lhq.ssm2.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.koolma.deleteTable.db_delete_2014before_test.threads.list_test;
import com.lhq.ssm2.util.bx.GetUrl;
import com.lhq.ssm2.util.bx.HkEntity;

@Controller
@RequestMapping("/")
public class HkController {
	@RequestMapping("/hk")
	@ResponseBody
	public String toIndex(HttpServletRequest request, Model model,
			String appName, String appIdno, String appMobile, String product,
			HkEntity hk) throws UnsupportedEncodingException {
		// http://121.196.234.84:8080/ssm2/hk
		// /hk.do?data="{\"appName\":\"\u95ee\u95ee\",\"appIdno\":\"339005199501301619\",
		// \"appMobile\":\"15068050659\",\"product\":\"QP000587\"}"
		String method = request.getMethod();
		if("get".equalsIgnoreCase(method)){
			appName = new String(request.getParameter("appName").getBytes("ISO-8859-1"),"utf-8");
			String answer1 = request.getParameter("answer1")==null?null:new String(request.getParameter("answer1").getBytes("ISO-8859-1"),"utf-8");
			String answer2 = request.getParameter("answer2")==null?null:new String(request.getParameter("answer2").getBytes("ISO-8859-1"),"utf-8");
			String answer3 = request.getParameter("answer3")==null?null:new String(request.getParameter("answer3").getBytes("ISO-8859-1"),"utf-8");
			String inName = request.getParameter("inName")==null?null:new String(request.getParameter("inName").getBytes("ISO-8859-1"),"utf-8");
			hk.setAppName(appName);
			hk.setAnswer1(answer1);
			hk.setAnswer2(answer2);
			hk.setAnswer3(answer3);
			hk.setInName(inName);
			hk.setProduct(product);
		}
		String json =new Gson().toJson(hk);
		String[] args = new String[1];
		args[0] = json;
		String result = new GetUrl().run(args);
		List<Object> list = new ArrayList<Object>();
		list.add(result);
		list.add(json);
		return result;
	}
}