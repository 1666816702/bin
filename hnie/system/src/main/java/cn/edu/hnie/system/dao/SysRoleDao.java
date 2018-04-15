package cn.edu.hnie.system.dao;

import java.util.List;
import java.util.Map;

import cn.edu.hnie.system.entity.SysRole;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 角色 Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

	List<SysRole> queryPageList(Page<SysRole> page, Map<String, Object> map);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int deleteBatch(Object[] id);

}