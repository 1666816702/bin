<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Errors导入Excel</title>
    </head>
    <body>
        <div>
            <!--只有将表单的enctype设置为multipart/form-data，才能实现文件上传；表单enctype的默认值为：application/x-www-form-urlencoded-->
            <!--只有POST方法才会被解析为文件上传请求    -->
            <form action="/student/batch" method="post" enctype="multipart/form-data">
                <!-- 不知道为啥，type="file"这一标签必须同时添加name属性，否则在处理上传时，无法检测到上传文件 -->
                <input type="file" name="fileField"/>
                <input type="submit" value="上传"/>                 
            </form>
            <p><font color="red">上传文件大小限制在1M之内</font></p>
            <a href="./downloadFile/download" >下载</a>
        </div>
    </body>
</html>