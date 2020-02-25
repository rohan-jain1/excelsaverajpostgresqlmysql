<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3> : file upload</h3>
	
	select  a file to upload<br/>
	
	<form action = "fileupload" method="post"
	enctype="multipart/form-data">
	
		<input type="file" name="file" size="50" />
		
		<br/>
		
		<input type="submit" value="UploadFile" />
	</form>
	


</body>
</html>