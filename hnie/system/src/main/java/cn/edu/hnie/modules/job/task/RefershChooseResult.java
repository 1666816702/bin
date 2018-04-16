package cn.edu.hnie.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("refershChooseResult")
public class RefershChooseResult {
	
	private Logger logger = LoggerFactory.getLogger(RefershChooseResult.class);
  
	public void oneTimeNode()
	{
		logger.info("刷新第一次双选的结果.....");
		
		// 按照时间节点过滤出第一次双选布成功的学生和企业
		
		// 把状态修改成无效状态
		
		// 把定时任务的状态修改成无效状态
	}
	
	public void twoTimeNode()
	{
		logger.info("刷新第二次双选的结果.....");
		
		// 按照时间节点过滤出第二次双选布成功的学生和企业
		
		// 把状态修改成无效状态
				
		// 把定时任务的状态修改成无效状态
	}
}
