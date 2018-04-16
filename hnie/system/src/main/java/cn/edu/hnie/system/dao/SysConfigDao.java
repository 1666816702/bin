package cn.edu.hnie.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.edu.hnie.system.entity.SysConfig;

public interface SysConfigDao extends BaseMapper<SysConfig> {
	List<SysConfig> findAll();

	SysConfig findByConfigName(String configName);

	List<SysConfig> findAllByPage(Map<String, Object> map, Pagination pageUtil);
}
