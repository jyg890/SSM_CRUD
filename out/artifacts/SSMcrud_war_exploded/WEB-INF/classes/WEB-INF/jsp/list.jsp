<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>员工列表</title>
</head>
<%
pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<%--
在上面拿出项目路径  用EL表达式取出来
web的路径问题
不以/开始的相对路径  找资源  以当前资源的路径为基准  经常容易出问题
以/开始的相对路径，找资源  以服务器的路径为标准 http://localhost:3333--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<body>
<%--  搭建显示页面--%>
    <div class="container">
<%--        标题--%>
        <div class="row">
            <div class="col-md-12">
                <h1>SSM-CRUD</h1>
            </div>
        </div>
<%--    按钮--%>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary">新增</button>
                <button>删除</button>
            </div>
        </div>
<%--    表格数据--%>
        <div class="row"></div>
<%--    显示分页信息--%>
        <div class="row"></div>
    </div>
</body>
</html>
