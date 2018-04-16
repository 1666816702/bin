package cn.edu.hnie.system.service;

import java.util.List;

import cn.edu.hnie.system.entity.SysUserRole;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysUserRoleService extends IService<SysUserRole> {
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void deleteByUserId(Long userId);
}
