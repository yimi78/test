<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>文件下载</title>
</head>
<script>
function selected(){
	var fileTypeSelect = document.getElementById("fileTypeSelect").value;
	var fileType = document.getElementById("fileType");

	jsSelectItemByValue(fileType,fileTypeSelect);
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
<body onload="selected()">
	<div class="page-header">
		<h1>文件下载</h1>
	</div>

    <div class="container-fluid">
			<input type="hidden" id="fileTypeSelect" value="${fileType}" />
    		<div class="row-fluid">
			<div class="span12">
				文件下载
				<form action="/ssm2/filedownload/getFileList.do" >
					文件类型 <select name="fileType" id='fileType' >
								<option value="0">原始订单</option>
								<option value="1">生成订单</option>
								<option value="2">生成sql</option>
							</select>
			        <input type="submit" value="Submit" /> 
				</form>
			</div>
		</div>
	    <div class="row-fluid">
		    <div class="span6" style="text-align: center;">
		    	<table>
		    	<c:forEach var="b" items="${fileList}" varStatus="status" >
					<tr><td>
			   	 			<a href="/ssm2/filedownload/download.do?inputPath=${b}">${b}</a>
			    		</td>
			    	</tr>
				</c:forEach>

			    </table>
		    </div>
	    </div>
    </div>
</body>
</html>

