package cn.edu.hnie.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.system.entity.SysConfig;

public interface SysConfigService extends IService<SysConfig> {

	/**
	 * 查询所有的系统参数
	 * 
	 * @return
	 */
	List<SysConfig> queryAllSystemConfig();

	/**
	 * 根据分页参数和条件查询系统参数
	 * 
	 * @param pageUtil
	 * @param map
	 * @return
	 */
	Page<SysConfig> getSystemConfigList(Page<SysConfig> pageUtil, Map<String, Object> map);

	SysConfig getSystemConfigByName(String configName);

	String getConfigValueByName(String configName);
}
