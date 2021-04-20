<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>文件上传</title>
   </head>
  
   <body>
       <form action="${pageContext.request.contextPath}/servlet/uploadHandleServlet2" method ="post" enctype="multipart/form-data">
		           上传用户：<input type="text" name="username"><br/>
		         上传文件1：<input type="file" name="file1"><br/>
		         上传文件2：<input type="file" name="file2"><br/>
		          <input type="submit" value="提交">
       </form>
   </body>
</html>