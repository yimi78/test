<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<script type="text/javascript">
	function myfunction() {
		var converted = getNowFormatDate();
		var ul = document.getElementById('link');
		var lis = ul.getElementsByTagName('li');
		for (var i = 0; i < lis.length; i++) {
			var str = lis.item(i).innerHTML;
			str = str.replace("{date}", converted);
			str = str.replace("{date}", converted);
			lis.item(i).innerHTML = str;
			//alert(lis.item(i).innerHTML);
		}
	}

	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate() - 1;
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}
	window.onload = myfunction;
</script>
<title>帮助连接</title>

</head>

<body>
	<h3>
	帮助连接
	</h3>
	<ul id='link'>
		<li>查询店铺数据  | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/stats/query_store_stats?date={date}">http://dsp.sigma.huihex.com/dsp/sadmin/stats/query_store_stats?date={date}</a>
		</li>
		<br/>
		<li>查询店铺收藏数据 | <a target= "_blank"  href = "http://dsp.sigma.huihex.com/dsp/sadmin/stats/query_shop_collect_stats?date={date}&shop_id=62832501">http://dsp.sigma.huihex.com/dsp/sadmin/stats/query_shop_collect_stats?date={date}&shop_id=62832501
			</a></li>
		<br/>
			<li>查询所有汇合店铺   | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/stats/hdi_query_all_store_ids?date={date} ">http://dsp.sigma.huihex.com/dsp/sadmin/stats/hdi_query_all_store_ids?date={date} 
			</a></li>
		<br/>
			<li>优化店铺数据 | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/stats/amplify_effects?date={date}">http://dsp.sigma.huihex.com/dsp/sadmin/stats/amplify_effects?date={date}
			</a></li>
		<br/>
			<li>店铺sql | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/mv_zz_cur/build_query_sql">http://dsp.sigma.huihex.com/dsp/sadmin/mv_zz_cur/build_query_sql
			</a></li>
		<br/>
			<li>导入分配数据库 | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/effects_unkown_source_trades/import?date={date}">http://dsp.sigma.huihex.com/dsp/sadmin/effects_unkown_source_trades/import?date={date}
			</a></li>
		<br/>
		<br/>
			<li>订单分配系统 | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/effects_unkown_source_trades/allocation ">http://dsp.sigma.huihex.com/dsp/sadmin/effects_unkown_source_trades/allocation 
			</a></li>
		<br/>
			<li>自动屏蔽店铺网址 | <a target= "_blank"  href ="http://dsp.sigma.huihex.com/dsp/sadmin/stats/show_zhanwai_shop_data ">http://dsp.sigma.huihex.com/dsp/sadmin/stats/show_zhanwai_shop_data
			</a></li>
		<br/>
	</ul>
</body>
</html>
