<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Fileupload</title>
</head>

<body>
	<div class="page-header">
		<h1>Fileupload excel</h1>
	</div>

	<div class="container-fluid">
		<a href='/ssm2/mainMenu.do'>return MainMenu</a>
		<div class="row-fluid">
			<div class="span12">
				广告平台上传订单文件
				<form action="/ssm2/uploadExcel.do" 
					method="POST" enctype="multipart/form-data">
					File<input type="file" name="file" /><br /><br />
					 时间<input name="date" label="date" value="2015-09-05" />
					 版本<input name="version" label="版本" value="" /> 
					<input type="hidden" name="isKid" value="n" />
			        <input type="submit" value="Submit" /> 
				</form>
			</div>
		</div>
		<hr>
		<div class="row-fluid">
			<div class="span12">
				指定广告分析上传数据
				<form action="/ssm2/uploadExcel.do" 
					method="POST" enctype="multipart/form-data">
					File<input type="file" name="file" /><br /><br /> 
					时间<input name="date" label="date" value="2015-09-05" /> 
					版本<input name="version" label="版本" value="" />
					<input type="hidden" name="isKid" value="y" />
			        <input type="submit" value="Submit" /> 
				</form>
			</div>
		</div>
		<hr>
		<div class="span12">
			<table>
				<tr>
					<td>${msg}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>

