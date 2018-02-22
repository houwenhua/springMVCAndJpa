$(function(){
jQuery("#grid-table").jqGrid({  
        subGrid : false, // 此处设置为true时可以打开子项,子项用不到已删除  
        url : "",// 获取数据的地址  
        rownumbers : true,   
        datatype : "json",  
        mtype : "POST",  
        hidegrid : false,//收缩列表按钮  
        prmNames : {  
            page : "pageNumInput",  
            rows : "pageSize"  
        },// 重新定义分页信息  
        height : 385, // 表格高度  
        colNames : ['手机号', '意见内容','提交时间'],  
        colModel : [ {  
            name : 'phone',  
            index : 'phone',  
            width : 100,  
            editable : true,  
        }, {  
            name : 'idea',  
            index : 'idea',  
            width : 120,  
            editable : false,  
        }, {  
            name : 'timestamp',  
            index : 'timestamp',  
            width : 120,  
            editable : false,  
        }],  
        viewrecords : true,  
        rowNum : 20,  
        rowList : [20, 50, 100,200],  
        pager : pager_selector,  
        altRows : true,  
        multiselect : false,  
        multiboxonly : true,  
        emptyrecords : "无数据",  
        loadComplete : function(data) {  
            var table = this;  
            setTimeout(function() {  
                styleCheckbox(table);  
                updateActionIcons(table);  
                updatePagerIcons(table);  
                enableTooltips(table);  
            }, 0);  
            if (data.status == "success") {  
                $("#alert_success").show();  
                setTimeout(function() {  
                    $("#alert_success").hide();  
                }, 1000);  
            }  
  
  
        },  
        jsonReader : { // jsonReader来跟服务器端返回的数据做对应  
            root : "dataRows", // 包含实际数据的数组  
            page : "currPage", // 当前页  
            total : "totalPage",// 总页数  
            records : "totalCount", // 查询出的记录数  
            id : "id",  
            repeatitems : false  
        },  
        editurl : "",// nothing is saved  
        caption : ""  
    });  
})