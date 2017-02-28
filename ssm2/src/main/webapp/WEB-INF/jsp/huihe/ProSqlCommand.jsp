<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>操作sql</title>
</head>

<script type="text/javascript">
	function showBack(msg){
		 var msgTb = document.getElementById("msg");
		 msgTb.innerHTML =  msgTb.innerHTML + "<tr><td>"+msg+"<td><tr>";
	}
	function disp_confirm(bu, datename) {
		var date = document.getElementById(datename).value;
		var msg = "plase check the date: " + date;
		var r = confirm(msg)
		if (r == true) {
			document.getElementById(bu).submit();
		} else {

		}
	}
	function onload() {
		var date1 = document.getElementById("date1").value;
		if (date1 == null || date1.length == 0) {
			var myDate = new Date();
			myDate.setDate(myDate.getDate() - 1);
			var year = myDate.getFullYear();
			var month = myDate.getMonth();
			if (month >= 0 && month <= 9) {
				month = "0" + month;
			}
			var day = myDate.getDate();
			if (day >= 0 && day <= 9) {
				day = "0" + day;
			}
			var time = year + "-" + month + "-" + day;
			document.getElementById("date1").value = time;
		}
	}
</script>

<body onload="onload()">
	<hr>
	<div class="page-header">
		<h1>生成sql</h1>
	</div>

	<div class="container-fluid">
		<a href='/ssm2/mainMenu.do'>return MainMenu</a>
		<div class="row-fluid">
			<div class="span12">
				<table>
					<tr>
						<td>
							<form action="/ssm2/huihe/proSql.do" id='proSql' onsubmit="return false">
								生成sql<br/>
							时间<input name="date" id='date1'  value="${date}" /><br/>
							版本<input name="version"  value="${version}" /><br/>
								<input type="hidden" name="date2"  value="${date2}" />
								<input type="hidden" name="date3"  value="${date3}" />
								<input type="hidden" name="isKid"  value="${isKid}" />
								<font color="red"><c:if test="${flage=='1'}">runing</c:if></font>
								<input type="submit" cssClass="btn btn-primary" value="run" onclick="disp_confirm('proSql','date1')"/>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form action="/ssm2/huihe/runSQL.do" id='runSQL' onsubmit="return false">
								 执行sql<br/>
							时间<input name="date2" id='date2' value="${date2}" /><br/>
							版本<input name="version"  value="${version}" /><br/>
								<input type="hidden" name="date"  value="${date}" />
								<input type="hidden" name="date3"  value="${date3}" />
								<input type="hidden" name="isKid"  value="${isKid}" />
								<font color="red"><c:if test='${flage=="2"}'>runing</c:if></font>
								<input type="submit" cssClass="btn btn-primary" value="run" onclick="disp_confirm('runSQL','date2')"/>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form action="/ssm2/huihe/restoreSQL.do" id='restoreSQL' onsubmit="return false">
								执行恢复 sql<br/>
							时间<input name="date3" id='date3'  value="${date3}" /><br/>
							版本<input name="version"  value="${version}" /><br/>
								<input type="hidden" name="date"  value="${date}" />
								<input type="hidden" name="date2"  value="${date2}" />
								<input type="hidden" name="isKid"  value="${isKid}" />
								<font color="red"><c:if test='${flage=="3"}'>runing</c:if></font>
								<input type="submit" cssClass="btn btn-primary" value="run" onclick="disp_confirm('restoreSQL','date3')"/>
							</form>
						</td>
					</tr>
				</table>
			</div>
			<hr>
			<div class="span12">
				<table id="msg">
						<tr>
						<td>
							${msg}
						</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

