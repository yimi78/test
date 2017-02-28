<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>上传店铺数据</title>
</head>

<body>
    <div class="page-header">
	    <h1>上传指定广告的店铺</h1>
    </div>

    <div class="container-fluid">
    	<a href='/ssm2/mainMenu.do'>return MainMenu</a>
	    <div class="row-fluid">
		    <div class="span12">
			    <form action="/ssm2/uploadTxt.do"  method="POST" enctype="multipart/form-data">
			   		File<input type="file" name="file" /><br/><br/>
			       <input type="submit" value="Submit" /> 
			    </form>
		    </div>
	    </div>
	    <hr>
	    <div class="span12">
				<table>
						<tr>
						<td>
							${msg}
						</td>
						</tr>
				</table>
		</div>
    </div>
</body>
</html>

