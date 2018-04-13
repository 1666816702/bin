$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/config/list',
        datatype: "json",
        colModel: [			
			{ label: '参数ID', name: 'configId', index: "config_id", width: 45, key: true },
			{ label: '参数名称', name: 'configName', index: "config_name", width: 75 },
            { label: '参数值', name: 'configValue', width: 75 },
			{ label: '备注', name: 'configDesc', width: 100 },
			{ label: '创建时间', name: 'createTime', index: "create_time", width: 80}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rgapp',
	data:{
		q:{
			configName: null
		},
		showList: true,
		title:null,
		config:{
			configId:null,
            configName:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.config = {configName:null, configId:null};
		},
		update: function () {
			var configId = getSelectedRow();
			if(configId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";

            vm.getDept();
		},
		saveOrUpdate: function (event) {
			var url = vm.config.configId == null ? "sys/config/save" : "sys/config/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.config),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function () {
			var configIds = getSelectedRows();
			if(configIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/config/delete",
                    contentType: "application/json",
				    data: JSON.stringify(configIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getconfig: function(configId){
            $.get(baseURL + "sys/config/info/"+configId, function(r){
            	vm.config = r.config;

    		});
		},
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'configName': vm.q.configName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});