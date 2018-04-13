package cn.edu.hnie.system.dao;

import java.util.List;
import java.util.Map;

import cn.edu.hnie.system.entity.SysDept;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 * 
 * @author theodo
 * @since 2017-10-28
 */
public interface SysDeptDao extends BaseMapper<SysDept> {

	List<SysDept> queryList(Map<String, Object> map);

	/**
	 * 查询子部门ID列表
	 * 
	 * @param parentId 上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

}