$(function() {
	/***********激活年份下拉框**************/
	$("body").on("click", ".activation_input_title", function(event) {
		$(".activation_option_box").toggle();
		event.stopPropagation();
	})
	
	var Index_method = {
		/*******获取年份*******/
		getYear: function() {
			$.ajax({
				type:"POST",
				url:baseURL+"school/year/list",
				headers: {
					"token":token
				},
				async: false,
				dataType:"json",
				success:function(data) {
					$(".activation_option_box").html("");
					var data = data.yearList;
					data.forEach(function(items,index) {
						var startYear = items.startDate.slice(0,4);
						var endYear = items.endDate.slice(0,4) + "学年";
						var html = '<div class="activation_option">'+startYear+'-'+endYear+'</div>';
						$(".activation_option_box").append(html);
					})
					$(".activation_input_title").html(data[0].startDate.slice(0,4)+'-'+data[0].endDate.slice(0,4) + "学年")
					$("#now_semester_year").html(data[0].startDate.slice(0,4)+'-'+data[0].endDate.slice(0,4) + "学年");
				}
			})
		},
		/*********获取当前年以及激活年*********/
		getYearInit: function() {
			$.ajax({
				type:"POST",
				url:baseURL+"school/year/list",
				headers: {
					"token":token
				},
				async: false,
				dataType:"json",
				success:function(data) {
					var data = data.yearList;
					$("#now_semester_year").html(data[0].startDate.slice(0,4)+'-'+data[0].endDate.slice(0,4) + "学年");
				}
			})
		},
		
		/***************判断是否可激活**************/
		isActiva: function() {
			$.ajax({
				type:"POST",
				url:baseURL+"/school/year/active",
				headers:{
					"token":token
				},
				async: false,
				dataType:"json",
				success:function(data) {
					if(data.active == 0) {
						$(".prevent_active_div").show()
						$("#activation_btn").addClass("cant_active");
					}else{
						$(".prevent_active_div").hide();
					}
				}
			})
		}
	}
	
	Index_method.getYear();
	Index_method.isActiva();
	
	
	$("body").on("click", ".activation_option", function() {
		$(".activation_input_title").html($(this).html());
		$(".activation_option_box").hide();
	})
	$("body").on("mouseover", ".activation_option", function(){
		$(this).addClass("activation_optioned").siblings().removeClass("activation_optioned");
	})
	$("body").on("click", function() {
		$(".activation_option_box").hide();
	})
	
	/*****************学年激活弹窗*******************/
	$("body").on("click", ".activation_btn", function() {
		$('#start_time_input').datepicker({
	        needDay:true,
	        changeMonth: true, //显示月份
	        changeYear: true, //显示年份
	        minDate:new Date(),
	        showButtonPanel: false, //显示按钮
	        dateFormat: 'yy-mm-dd', //日期格式
	    });
		$("#end_time_input").datepicker({
	        needDay:true,
	        changeMonth: true, //显示月份
	        changeYear: true, //显示年份
	        showButtonPanel: false, //显示按钮
	        dateFormat: 'yy-mm-dd', //日期格式
	    });
		Index_method.getYearInit();
		var prev_year = $("#now_semester_year").html();
		var prev = parseInt(prev_year.slice(0,4))+1;
		var next = parseInt(prev_year.slice(5,9))+1;
		$("#next_active_year").html(prev+"-"+next+"学年")
	})
	
	var vm = new Vue({
		el:"#activation_btn",
		methods:{
			activationLayer:function() {
				var t0 = new Date();
				var t = t0.getMilliseconds();
				var Content = '<div class="layer_activation_box">' +
		        '<div class="now_semester_box">' +
		        '<div class="now_semester">当前学年：<span id="now_semester_year"></span></div>' +
		        '<div class="next_semester">激活学年：<span id="next_active_year">2017-2018学年</span></div>' +
		        '</div>' +
		        '<div class="select_time_input_box">' +
		        '<span class="select_time_left_write" v-model="startDate">开始日期：</span>' +
		        '<input type="text" id="start_time_input" class="select_time_input" readonly />' +
		        '</div>' +
		        '<div class="select_time_input_box">' +
		        '<span class="select_time_left_write" v-model="endDate">结束日期：</span>' +
		        '<input type="text" id="end_time_input" class="select_time_input" readonly />' +
		        '</div>' +
		        '<div class="select_time_input_box">' +
		        '<span class="select_time_left_write" v-model="captcha">验证码：</span>' +
		        '<input class="select_time_input verification_code_input" />' +
		        '<div id="change_verification_code_img" class="change_verification_code_img" @click="refreshCode">换一张</div>' +
		        '<div class="verification_code_img">' +
		        '<img id="img" src="http://localhost:8088/zyjh/school/year/captcha.jpg" />' +
		        '</div>' +
		        '</div>' +
		        '<div class="sure_and_cancel_btn">' +
		        '<div id="sure_to_active_btn" class="sure_to_active_btn" @click="Active">确定'+
		        '</div>'+
		        '<div class="cancel_to_active_btn">取消'+
		        '</div>'+
		        '</div>'+
		        '</div>';
				layer.open({
					type:1,
					title: "激活操作不可逆转，请谨慎操作",
	                area: ['500px', '400px'],
	                offset: '50px',
	                skin: 'layui-layer-molv',
	                shade:0,
	                content: Content,
	                success:function(layero,index){
	                	$(".sure_to_active_btn").click(function() {
	                		Index_method.getYear()
	                		layer.close(index);
	                		location.reload();
	                	})
	                	$(".cancel_to_active_btn").click(function() {
	                		layer.close(index);
	                	})
	                }
				})
			},
			
		}
	})
	
	$("body").on("click", ".verification_code_img, .change_verification_code_img", function() {
		changeImg();
	})
	
	//更换验证码
	function changeImg() {
		var t0 = new Date();
		var t = t0.getMilliseconds();
		$("#img").attr("src","http://localhost:8088/zyjh/school/year/captcha.jpg?t="+t);
	}
	
	/*****************确认激活学年*************************/
	$("body").on("click", ".sure_to_active_btn", function() {
		var schoolYearName = $("#next_active_year").html();
		var startDate = $("#start_time_input").val();
		var endDate = $("#end_time_input").val();
		var captcha = $(".verification_code_input").val();
		var data = "schoolYearName="+schoolYearName+"&startDate="+startDate+"&endDate="+endDate+"&captcha="+captcha;
		$.ajax({
			type:"POST",
			url:baseURL+"school/year/save",
			headers: {
				"token":token
			},
			data:data,
			async: false,
			dataType:"json",
			success:function(data) {
				console.log(data);
			}
		})
	})
})