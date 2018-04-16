package cn.edu.hnie.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.hnie.system.dao.SysConfigDao;
import cn.edu.hnie.system.entity.SysConfig;
import cn.edu.hnie.system.service.SysConfigService;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {

	@Autowired
	private SysConfigDao systemConfigDao;

	@Override
	public List<SysConfig> queryAllSystemConfig() {
		return systemConfigDao.findAll();
	}

	/**
	 * <分页查询配置信息>
	 * 
	 * @param pageUtil
	 * @param map
	 * @return
	 */
	@Override
	public Page<SysConfig> getSystemConfigList(Page<SysConfig> pageUtil, Map<String, Object> map) {

		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
		// page.setOptimizeCountSql(false);

		// 不查询总记录数
		// page.setSearchCount(false);

		return pageUtil.setRecords(systemConfigDao.findAllByPage(map, pageUtil));
	}

	/**
	 * <一句话功能简述>
	 * 
	 * @param configName
	 * @return
	 */
	@Override
	public SysConfig getSystemConfigByName(String configName) {
		return systemConfigDao.findByConfigName(configName);
	}

	@Override
	public String getConfigValueByName(String configName) {
		SysConfig sc = getSystemConfigByName(configName);
		if (null != sc) {
			return sc.getConfigValue();
		} else {
			return null;
		}
	}

}
