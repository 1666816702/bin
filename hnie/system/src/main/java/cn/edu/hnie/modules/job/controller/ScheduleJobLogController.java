package cn.edu.hnie.modules.job.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hnie.common.utils.Query;
import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.modules.job.entity.ScheduleJobLog;
import cn.edu.hnie.modules.job.service.ScheduleJobLogService;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 定时任务日志
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2016年12月1日 下午10:39:52
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		Page<ScheduleJobLog> pageUtil = new Page<ScheduleJobLog>(query.getPage(), query.getLimit());
		Page<ScheduleJobLog> page = scheduleJobLogService.queryPageList(pageUtil,query);
		
		return R.ok().put("page", page);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") Long logId){
		ScheduleJobLog log = scheduleJobLogService.selectById(logId);
		
		return R.ok().put("log", log);
	}
}
