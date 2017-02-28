<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>导出订单</title>
</head>

<body>
	<div class="page-header">
		<h1>导出</h1>
	</div>

	<div class="container-fluid">
		<a href='/ssm2/mainMenu.do'>return MainMenu</a>
		<div class="row-fluid">
			<div class="span12">
				<table>
					<tr>
						<td><form action="/ssm2/huihe/excommand.do">
								广告平台订单，包括淘宝和天猫<br/><br/>
							时间	<input name="date"  value="${date}" /><br/><br/>
							版本	<input name="version" value="${version}" />
								<input type="submit" value="运行" />
							</form></td>
					</tr>
				</table>
			</div>
			<hr>
			<table>
				<tr>
					<td><form action="/ssm2/huihe/excommandKid.do">
							包括淘宝天猫的指定广告分析订单<br/><br/>
							时间	<input name="date"  value="${date}" /><br/><br/>
							版本	<input name="version" value="${version}" />
								<input type="submit" value="运行" />
						</form></td>
				</tr>
			</table>
			<hr>
			<div class="span12">
				<table>
						<tr>
							${msg}
						</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>

