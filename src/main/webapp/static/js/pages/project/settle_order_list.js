$(function () {
    var _table = $('#table');
    _table.bootstrapTable();

    var $curP = $("#currPage"),
        $pageC = $("#pageCount");
    var curpage = parseInt($curP.val());
    var pageCount = parseInt($pageC.val());
    //翻页
    $(".zxf_pagediv").createPage({
        pageNum: pageCount,
        current: curpage,
        backfun: function(e) {
            //console.log(e);//回调
            // alert(e.current);
            $curP.val(e.current);
            $("#queryForm").submit();
        }
    });
})


var srhBtn = $("#srhBtn");
srhBtn.click(function () {
    $("#queryForm").submit();
});