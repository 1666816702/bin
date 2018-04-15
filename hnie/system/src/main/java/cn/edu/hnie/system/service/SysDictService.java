package cn.edu.hnie.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.system.entity.SysDict;

public interface SysDictService extends IService<SysDict> {

	/**
	 * 查询数据字典
	 * 
	 * @param argMap 查询参数
	 * @return
	 */
	List<SysDict> queryDict(Map<String, Object> argMap);
}
