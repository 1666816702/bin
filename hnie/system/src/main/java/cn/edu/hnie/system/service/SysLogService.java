package cn.edu.hnie.system.service;

import java.util.Map;

import cn.edu.hnie.system.entity.SysLog;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysLogService extends IService<SysLog> {
	public Page<SysLog> selectPageList(Page<SysLog> page, Map<String, Object> map);
}
