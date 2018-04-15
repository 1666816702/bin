$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'company/list?schoolYearId='+1,
        hearders:{
        	"token":token
        },
        datatype: "json",
        colModel: [			
			{ label: '企业ID', classes:'row_style', name: 'companyId', index: "company_id", width: 45, key: true },
			{ label: '企业名称', classes:'row_style', name: 'name', width: 75 },
            { label: '企业性质', classes:'row_style', name: 'companyType', width: 75 },
			{ label: '主营业务', classes:'row_style', name: 'mainBusiness', width: 90 },
			{ label: '签订时间', classes:'row_style', name: 'createAgreeDate', width: 80 },
			{ label: '协议学院', classes:'row_style', name: 'deptName', width: 80 },
			{ label: '企业负责人', classes:'row_style', name: 'contactPerson', index: "create_time", width: 90},
			{ label: '操作', name: 'operation', width: 90,  formatter: function(value, row) {
				return '<span class="edit_btn">详情</span>'/*'<span class="edit_btn">编辑</span><span class="delete_btn">删除</span>'*/;
			}}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
        	root: "page.records",
            page: "page.current",
            total: "page.pages",
            records: "page.total"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	
        	var ids = jQuery("#jqGrid").jqGrid('getDataIDs');
        	for (var i = 0; i < ids.length; i++) {
	        	var id = ids[i];
	        	var DeleteBtn = "<a href='#' style='color:#f60' onclick='OpenAllocationDialog()' >Abolish</a>";
	        	var editBtn = "<a href='#' style='color:#f60' onclick='OpenAllocationDialog()' >Edit</a>";
	        	jQuery("#jqGrid").jqGrid('setRowData', ids[i], { Edit: editBtn, Delete: DeleteBtn });
        	}
        	
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
