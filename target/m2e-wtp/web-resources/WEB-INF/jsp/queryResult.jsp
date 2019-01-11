<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap -->
<link rel="stylesheet"
      href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<!-- 顶部导航栏 -->
<jsp:include page="../../public/nav-top.jsp"></jsp:include>
<!-- 栅格系统 平均分三列 -->
<div class="container" id="summary-container">

    <!-- 分割线暂时注释掉
    <hr class="divider"/> -->
    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-info">

                <!-- 利用一个栅格系统向右偏移完成布局 -->
                <c:choose>
                    <c:when test="${userList.size()==0 }">
                        <div align="center" style="padding-top: 20px"><font color="red">${q}</font>未查询到结果，请换个关键字试试！</div>
                    </c:when>
                    <c:otherwise>
                        <div align="center" style="padding-top: 20px">
                            查询<font color="red">${q}</font>关键字，约${resultTotal}条记录！
                        </div>
                        <c:forEach var="u" items="${userList }" varStatus="status">
                            <div class="panel-heading ">

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <b>
                                                    <a href="<%=path %>/user/showUser/${u.userId}">${u.username}</a>
                                                </b>
                                                <br/>
                                                    ${u.description}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-md-offset-2">
                                        <p class="text-muted text-right">
                                                ${u.password}
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <p class="text-right">
							<span class="label label-default">
							<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
							 ${u.password}
							</span>
                                </p>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


                <hr class="divider"/>
            </div>


        </div>
    </div>


    <!-- 分页 -->
    <nav class="text-right">
        <ul class="pagination pagination-sm">
            ${pageHtml}
        </ul>
    </nav>
    <!-- 分割线 -->
    <hr class="divider"/>
</div>

<!-- <footer> -->
<!--     <p class="text-center">© 2016 crossoverJie</p> -->
<!--     <p class="text-center"> -->
<!--     </p> -->
<!-- </footer> -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</body>
</html>