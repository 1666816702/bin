package cn.edu.hnie.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.hnie.system.entity.SysDict;

public interface SysDictDao extends BaseMapper<SysDict> {

	/**
	 * <查询所有的SystemDic>
	 * 
	 * @param argMap 查询参数
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<SysDict> findList(Map<String, Object> argMap);
}
