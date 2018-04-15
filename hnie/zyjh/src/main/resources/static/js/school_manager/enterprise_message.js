$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'company/list?schoolYearId='+1,
        hearders:{
        	"token":token
        },
        datatype: "json",
        colModel: [			
			{ label: '企业ID', name: 'companyId', index: "company_id", width: 45, key: true },
			{ label: '企业名称', name: 'name', width: 75 },
            { label: '企业性质', name: 'companyType', width: 75 },
			{ label: '主营业务', name: 'mainBusiness', width: 90 },
			{ label: '签订时间', name: 'contactPerson', width: 80 },
			/*{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},*/
			{ label: '协议学院', name: 'contactPerson', width: 80 },
			{ label: '企业负责人', name: 'contactTel', index: "create_time", width: 90},
			{ label: '操作', name: 'operation', width: 90,  formatter: function(value, row) {
				return '<span class="edit_btn">编辑</span><span class="delete_btn">删除</span>';
			}}
			/*{ name: 'Edit', index: 'Edit', sortable: false, align: "center", width: "60px" },

			{ name: 'Delete', index: 'Delete', sortable: false, align: "center", width: "60px" }*/
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
