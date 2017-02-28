<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<script>
function selected(){
	var pcOrWapSelect = document.getElementById("pcOrWapSelect").value;
	var pcOrWap = document.getElementById("pcOrWap");
	var selectSelect = document.getElementById("selectSelect").value;
	var select = document.getElementById("select");

	if(pcOrWapSelect==null||pcOrWapSelect.length==0){
		pcOrWapSelect = "3";
	}
	if(selectSelect ==null||selectSelect.length==0){
		selectSelect = "0";
	}
	jsSelectItemByValue(pcOrWap,pcOrWapSelect);
	jsSelectItemByValue(select,selectSelect);
	
	// 初始化时间
	var beginDate = document.getElementById("beginDate").value;
	var endDate = document.getElementById("endDate").value;
	if(beginDate == null||beginDate.length==0 ){
		if(endDate == null||endDate.length==0 ){
			var myDate = new Date(); 
			myDate.setDate(myDate.getDate()-1);
			var year = myDate.getFullYear();
		    var month = myDate.getMonth();
		    if (month >= 0 && month <= 9) {
		        month = "0" + month;
		    }
		    var day =  myDate.getDate();
		    if (day >= 0 && day <= 9) {
		    	day = "0" + day;
		    }
		    var time = year + "-" + month+"-"+ day;
		    document.getElementById("beginDate").value = time;
		    document.getElementById("endDate").value = time;
		}
	}

}

function jsSelectItemByValue(objSelect, objItemText) { 
	//判断是否存在 
	var isExit = false; 
	for (var i = 0; i < objSelect.options.length; i++) { 
		if (objSelect.options[i].value == objItemText) { 
			objSelect.options[i].selected = true; 
			isExit = true; 
			break; 
		} 
	} 
}
</script>
<head>
<title>导出数据</title>
</head>

<body onload="selected()">

	<input type="hidden" id="pcOrWapSelect" value="${pcOrWap}" />
	<input type="hidden" id="selectSelect"  value="${select}" />
	<div class="page-header">
		<h1>店铺数据查询</h1>
	</div>

	<div class="container-fluid">
		<a href='/ssm2/mainMenu.do'>return MainMenu</a>

		<div class="row-fluid">
			<div class="span12">
				<form action="/ssm2/huihe/shopOptimize/shopOptimize.do">
					开始时间 <input name="beginDate"  id ="beginDate" value="${beginDate}" size='10' />
					结束时间 <input name="endDate" id ="endDate" value="${endDate}" size='10' />
					店铺id <input name="shopId" value="${shopId}" size='10' />
					渠道id <input name="taskId" value="${taskId}" size='10' /> 
					迁移渠道id <input name="newTaskId" value="${newTaskId}" size='10' /> 
					pc还是wap <select name="pcOrWap" id='pcOrWap' value="1">
									<option value="0">pc+wap</option>
									<option value="1">PC</option>
									<option value="3">all</option>
								</select>
					效果<select id="select" name="select" id="select"  >
							<option value="0">查询</option>
							<option value="1">到达优化</option>
							<option value="2">pc转无线</option>
							<option value="3">无线转pc</option>
							<option value="3">恢复</option>
						</select>
					<input type="submit" value="运行" />
				</form>

				<textarea>${shopOptimizeSql}</textarea>
				<table border="8">
					<tr heigh='100%'>
						<th>序号</th>
						<th>店铺名字</th>
						<th>店铺id</th>
						<th>pcOrWap</th>
						<th>日期</th>
						<th>浏览量</th>
						<th width='15px'>到达页浏览量</th>
						<th>点击数</th>
						<th width='15px'>汇合点击数</th>
						<th style='background-color: red'>到达率</th>
						<th width='15px'>带来访客数</th>
						<th style='background-color: red'>跳失率</th>
						<th width='15px'>拍下订单数</th>
						<th width='15px'>成交订单数</th>
						<th width='15px'>成交金额</th>
						<th width='15px'>成交转化率</th>
						<th style='background-color: red' width='15px'>人均访问深度</th>
						<th width='15px'>新增店铺收藏数</th>
						<th width='15px'>新增宝贝收藏数</th>
						<th width='15px'>加入购物车件数</th>
						<th width='15px'>拍下人数</th>
						<th width='15px'>拍下金额</th>
						<th width='15px'>成交用户数</th>
						<th>roi</th>
					</tr>
		  		<c:forEach var="state" items="${shopInfo}" varStatus="status" >
				 	<c:if test="${state.check == false}"> 
					 	<tr  style='background-color:red'>
					 </c:if>
					  <c:if test="${state.check == false}"> 
							<tr>
					  </c:if>
							<td>${status.index}</td>
							<td>${state.用户名称}</td>
							<td>${state.shop_id}</td>
							<td>${state.status}</td>
							<td>${state.column_name}</td>
							<td>${state.pv}</td>
							<c:if test="${state.landing_pv_check == 'false'}"> 
						 		<td style='background-color:red'>${state.landing_pv} </td>
						 	</c:if>
							<c:if test="${state.landing_pv_check != 'false'}"> 
						 		<td >${state.landing_pv} </td>
						 	</c:if>
						 	<c:if test="${state.jump_pv_check == 'false'}"> 
						 		<td style='background-color:red'>${state.jump_pv} </td>
						 	</c:if>
							<c:if test="${state.jump_pv_check != 'false'}"> 
						 		<td >${state.jump_pv}</td>
						 	</c:if>
						 	<td>${state.点击量}</td>
						 	<c:if test="${state.landing_pv_rate_check == 'false'}"> 
						 		<td style='background-color:red'>${state.landing_pv_rate} </td>
						 	</c:if>
							<c:if test="${state.landing_pv_rate_check != 'false'}"> 
						 		<td >${state.landing_pv_rate}</td>
						 	</c:if>
						 	<c:if test="${state.sv_check == 'false'}"> 
						 		<td style='background-color:red'>${state.sv}</td>
						 	</c:if>
							<c:if test="${state.sv_check != 'false'}"> 
						 		<td >${state.sv}</td>
						 	</c:if>
						 	<c:if test="${state.jump_rate_check == 'false'}"> 
						 		<td style='background-color:red'>${state.jump_rate}</td>
						 	</c:if>
							<c:if test="${state.jump_rate_check != 'false'}"> 
						 		<td >${state.jump_rate}</td>
						 	</c:if>
						 	<td>${state.pat_trades}</td>
							<td>${state.achieve_trades}</td>
							<td>${state.achieve_payment}</td>
							<td>${state.achieve_rate}</td>
							<c:if test="${state.avg_deep_check == 'false'}"> 
						 		<td style='background-color:red'>${state.avg_deep}</td>
						 	</c:if>
							<c:if test="${state.avg_deep_check != 'false'}"> 
						 		<td >${state.avg_deep}</td>
						 	</c:if>
							<td>${state.store_fov}</td>
							<td>${state.item_fov}</td>
							<td>${state.cart_user}</td>
							<td>${state.pat_users}</td>
							<td>${state.pat_payment}</td>
							<td>${state.achieve_users}</td>
							<td>${state.ROI}</td>
							</tr> 
					</c:forEach>
				</table>
			</div>
			<hr>
			<div class="span12">
			错误提示：
			<hr>
				${msg}
			<hr>
			</div>
			
		</div>
	</div>

</body>
</html>

