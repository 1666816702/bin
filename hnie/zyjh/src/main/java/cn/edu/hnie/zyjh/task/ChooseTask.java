package cn.edu.hnie.zyjh.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("chooseTask")
public class ChooseTask {

	private Logger logger = LoggerFactory.getLogger(ChooseTask.class);

	public void oneTimeNode() {
		logger.info("刷新第一次双选的结果.....");

		// 按照时间节点过滤出第一次双选布成功的学生和企业
		// 1.查询学年表，读取当前学年表中状态为有效的学年记录school_year_id
		// 1.1 根据学年表，查询inf_choose_date表，找到目前所有学院的双选时间配置,需要循环判断，因为每个学院的配置不一样
		// 2.根据学年Id查询inf_choose表，找到双选成功的个人和企业
		// 3.根据下面的sql，把符合条件的学生的双选和企业的双选找出
		// select a.src_id, a.dest_id from inf_choose a, inf_choose b where a.dest_id =
		// b.dest_id and a.src_type = '1'
		// and a.src_id = b.dest_id and b.src_type = '2'
		// and a.status = '0' and b.status = '0'and school_year_id = ${school_year_id} 
		// and dept_id = ${dept_id};

		// 4.在内存中判断，src_id相同的只能包含一次。这是因为每个学生最多只能选一个企业，可能存在多条匹配成功记录

		// 5.根据4中过滤出来的List<a.src_id, a.dest_id>批量修改inf_choose表中status=1
		// 这里是从学生的角度刷新双选情况
		// <foreach collection="list" item="item" index="index" open="" close=""
		// separator=";">
		// update inf_choose
		// <set>
		// status=${item.status}
		// </set>
		// where src_id = ${item.src_id} and dest_id = ${item.dest_id};
		// </foreach>

		// 6.根据4中过滤出来的List<a.src_id, a.dest_id>批量修改inf_choose表中status=1
		// 这里是从企业的角度刷新企业的双选情况
		// <foreach collection="list" item="item" index="index" open="" close=""
		// separator=";">
		// update inf_choose
		// <set>
		// status=${item.status}
		// </set>
		// where dest_id = ${item.src_id} and src_id = ${item.dest_id};
		// </foreach>

		// 7.把inf_choose表中剩余的状态为0的全部刷新成3
		// update inf_choose set status = '3' where school_year_id = ${school_year_id}
		// and dept_id = ${dept_id};
	}

	public void twoTimeNode() {
		logger.info("刷新第二次双选的结果.....");

		// 和上面逻辑完全一致，除了匹配成功的时候，status='3'
		// sql和上面完全复用，不重新写，在修改的时候，status通过动态参数的形式传进去
	}
}
