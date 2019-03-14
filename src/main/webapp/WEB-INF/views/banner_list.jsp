<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>广告管理</title>

    <!-- CSS核心 -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/animate.min.css" rel="stylesheet">

    <!-- CSS插件 -->
    <link href="/static/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/static/css/plugins/pages/jquery.page.css" rel="stylesheet">
    <link href="/static/css/plugins/fileinput/fileinput.min.css" rel="stylesheet">


    <!-- CSS页面 -->
    <link href="/static/css/style.min.css" rel="stylesheet">


</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeIn">


    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>广告管理</h5>
                </div>

                <div class="ibox-content" style="padding-bottom:0;">
                    <div class="row">

                        <div class="ibox-btns" style="margin-top:10px;margin-left: 15px">
                            <button class="btn btn-primary btn-xs" type="button" id="addBtn" >新建banner图</button>
                        </div>
                        <form id = "listForm">
                            <input type="hidden" id="currPage" value="${bannerList.pageNum}">
                            <input type="hidden" id="pageCount" value="${bannerList.pageSize}">
                        </form>
                        <div class="col-sm-12" style="height: auto;">
                            <!-- 表格部分 开始 -->
                            <table id="table">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>标题</th>
                                    <th>图片</th>
                                    <th>链接</th>
                                    <th>管理</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${bannerList.list}" var="item">
                                        <tr>
                                            <td>${item.id}</td>
                                            <td>${item.title}</td>
                                            <td>${item.picPath}</td>
                                            <td>${item.picLink}</td>
                                            <td>
                                                <button class="btn btn-primary btn-xs" type="button" >编辑</button>
                                                <button class="btn btn-primary btn-xs" type="button" >删除</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- 表格部分 结束 -->
                            <div class="ibox-footer clear">
                                <div class="pull-right">
                                    <!--分页 开始-->
                                    <div class="tcdPageCode" id="tcdPagehide"></div>
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

<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true" >
    <div class="modal-dialog modal-lg" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">新建</h4>
            </div>
            <div class="modal-body">

                <!-- 查询部分 开始 -->
                <div class="ibox-content" style="padding:0; border-top:none;width: auto;">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" id="addBanner" action="/banner/add" enctype="multipart/form-data" method="post">
                                <table class="table table-bordered" style="margin-bottom: 0;">
                                    <tbody>
                                    <tr>
                                        <td class="input-group-addon" width="15%">
                                            标题
                                        </td>
                                        <td width="25%">
                                            <input type="text" name="title" class="form-control">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="15%">图片</td>
                                        <td width="25%">
                                            <%--<form class="form-horizontal validate[required]"  enctype="multipart/form-data" method="post"  id="uploadForm">--%>
                                                <input id="fileinput" name="bannerFile" type="file">
                                            <%--</form>--%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input-group-addon" width="15%">链接</td>
                                        <td>
                                            <input type="text" name="picLink" class="form-control">
                                        </td>
                                    </tr>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="panel-footer clear">
                                    <div class="pull-right">
                                        <button class="btn btn-primary btn-xs" type="button" id="addBannerBtn">
                                            新增
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 查询部分 结束 -->
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
<script src="/static/js/plugins/pages/jquery.page.js"></script>

<!-- JS页面 -->
<script src="/static/js/pages/project/banner.js"></script>

</body>
</html>

