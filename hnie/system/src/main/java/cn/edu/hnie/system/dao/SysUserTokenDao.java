package cn.edu.hnie.system.dao;

import cn.edu.hnie.system.entity.SysUserToken;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 系统用户Token Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {

	SysUserToken queryByToken(String token);
    
}