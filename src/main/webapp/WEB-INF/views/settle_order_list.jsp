<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单注单</title>

    <!-- CSS核心 -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/animate.min.css" rel="stylesheet">

    <!-- CSS插件 -->
    <link href="/static/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <%--<link href="/static/css/plugins/pages/jquery.page.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="/static/css/zxf_page.css"/>
    <!-- CSS页面 -->
    <link href="/static/css/style.min.css" rel="stylesheet">


</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeIn">


    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>订单注单</h5>
                </div>

                <div class="ibox-content" style="padding-bottom:0;">
                    <div class="row">
                        <form class="form-horizontal" id="queryForm" name="selected" method="post" action="/order/settleList">
                            <input type="hidden" id="currPage" name = "pageNum" value="${pageNum}">
                            <input type="hidden" id="pageCount" name = "pageCount" value="${settleOrderList.pages}">
                            <table class="table table-bordered" style="margin-bottom: 0;">
                                <tbody>
                                <tr>
                                    <%--<td class="input-group-addon" width="20%">--%>
                                        <%--用户：--%>
                                    <%--</td>--%>
                                    <%--<td width="30%">--%>
                                        <%--<input type="text" class="form-control"  id="userName" name="userName">--%>
                                    <%--</td>--%>
                                    <td class="input-group-addon" width="15%">订单号：</td>
                                    <td width="30%">
                                        <input type="text" class="form-control" id="orderId" name="orderId" value="${orderId}">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="ibox-btns pull-right" style="margin-top:10px;margin-right: 15px;">
                                <button class="btn btn-primary btn-xs" type="button" id="srhBtn" >查询</button>
                            </div>

                        </form>
                        <div class="col-sm-12">
                            <!-- 表格部分 开始 -->
                            <table id="table">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>订单号</th>
                                    <th>注单号</th>
                                    <th>投入米粒</th>
                                    <th>收益米粒</th>
                                    <th>类型</th>
                                    <th>联赛名</th>
                                    <th>比赛名称</th>
                                    <th>玩法名称</th>
                                    <th>状态</th>
                                    <th>更新时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${settleOrderList.list}" var="item">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.orderId}</td>
                                        <td>${item.id}</td>
                                        <td>${item.orderAmount}</td>
                                        <td>${item.settleIncomeLose}</td>
                                        <td>${item.type}</td>
                                        <td>${item.eventType}</td>
                                        <td>${item.eventName}</td>
                                        <td>${item.playName}</td>
                                        <td>${item.status}</td>
                                        <td><fmt:formatDate value="${item.settleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <!-- 表格部分 结束 -->
                            <div class="ibox-footer clear">
                                <div class="pull-right">
                                    <!--分页 开始-->
                                    <div class="zxf_pagediv"></div>
                                    <!--分页 结束-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- JS核心 -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/content.min.js"></script>

<!-- JS插件 -->
<script src="/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--<script src="/static/js/plugins/pages/jquery.page.js"></script>--%>
<script type="text/javascript" src="/static/js/zxf_page.js"></script>
<!-- JS页面 -->
<script src="/static/js/pages/project/settle_order_list.js"></script>

</body>
</html>

