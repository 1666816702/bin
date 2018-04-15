package cn.edu.hnie.zyjh.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("schoolYearTask")
public class SchoolYearTask {
	
	private Logger logger = LoggerFactory.getLogger(SchoolYearTask.class);
  
	public void refreshStatus()
	{
		logger.info("刷新学年的状态.....");
		
		// 读取学年表中所有有效的学年记录，根据status判断
		
		// 根据学年的配置，判断当前时间是否已经到学年的结束时间，如果已经到结束时间，把状态修改成无效
	}
}
