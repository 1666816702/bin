package cn.edu.hnie.system.service;

import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.system.entity.SysUserToken;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysUserTokenService extends IService<SysUserToken> {
	SysUserToken queryByToken(String token);
	
	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);
}
