$(function () {
    var _table = $('#table');
    _table.bootstrapTable();

    var $curP = $("#currPage"),
        $pageC = $("#pageCount");
    var curpage = parseInt($curP.val());
    var pageCount = parseInt($pageC.val());
    var $tcdPage = $(".tcdPageCode");
    $tcdPage.createPage({
        pageCount: pageCount,
        current: curpage,
        backFn: function (p) {
            $curP.val(p);
            tableFun();
        }
    });
})


var addBtn = $("#addBtn");
addBtn.click(function () {
    $("#myModal").modal("show");
});

var addBannerBtn = $("#addBannerBtn");
addBannerBtn.click(function () {
    $("#addBanner").submit();
})