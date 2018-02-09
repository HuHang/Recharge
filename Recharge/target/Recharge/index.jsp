<html>
<head>
    <meta charset="UTF-8">
    <title>导出</title>
</head>
<body>
<a href="/excel/export.do">Download</a><br/>
<a href="/api/v1/template?type=client">下载折扣导入模板</a>
<form action="/api/v1/import/clients" method="post" enctype="multipart/form-data">
    选择文件：<input name="file" type="file"/> <br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
