<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />

<!--jqGrid 皮肤 -->
<link rel="stylesheet" href="assets/css/ui.jqgrid.min.css" />
<!-- ace styles 含jqGrid部分样式 -->
<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

<!-- 自定义样式， 用于覆盖ace.min.css中的部分样式，解决冻结列横向滚动条不显示以及错位问题 -->    
<style>
        .ui-jqgrid .ui-jqgrid-bdiv {
            border-top: 1px solid #E1E1E1;
            overflow-x: auto;
        }
        .frozen-div, .frozen-bdiv {
            background-color: #E4E6E9;/*与网页背景色一致*/
        }
</style>

</head>
<body>
	<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript"> 
    
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'data.json',
                datatype: "json",
                colNames: ['Category Name', 'Product Name', 'Country', 'Price', 'Quantity'],
                colModel: [
                    { label: 'Category Name', name: 'CategoryName', width: 100, frozen: true },
                    { label: 'Product Name', name: 'ProductName', width: 150, frozen: true },
                    { label: 'Country', name: 'Country', width: 200 },
                    { label: 'Price', name: 'Price', width: 250, sorttype: 'number' },
                    { label: 'Quantity', name: 'Quantity', width: 250, sorttype: 'integer' }                  
                ],
                loadonce: true,
                shrinkToFit: false, // 冻结列必须设置此属性为false，否则列会自适应宽度
                width: 780,
                height: 200,
                rowNum: 15,
                pager: "#jqGridPager"
            });

            $("#jqGrid").jqGrid("setFrozenColumns");
        });
        </script>
</body>
<script src="assets/js/jquery-2.1.4.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- jqGrid scripts -->
<script src="assets/js/jquery.jqGrid.min.js"></script>
<!-- jqGrid 本地化脚本 相关文件可从jqGrid官网下载 -->
<script src="assets/js/grid.locale-cn.js"></script>

<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<script src="js/student.js"></script>
</html>