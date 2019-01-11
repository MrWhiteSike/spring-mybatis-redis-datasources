<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>后台</title>
</head>
<body>
<shiro:hasRole name="admin">
    这是admin角色登录：<shiro:principal></shiro:principal>
</shiro:hasRole>

<shiro:hasPermission name="user:create">
    有user:create权限信息
</shiro:hasPermission>


<shiro:hasRole name="teacher">
    这是teacher角色登录：<shiro:principal></shiro:principal>
</shiro:hasRole>
<shiro:hasPermission name="student:create">
    有student:create权限信息
</shiro:hasPermission>
<br>
登录成功
</body>
</html>
