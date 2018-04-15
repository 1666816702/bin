package cn.edu.hnie.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hnie.system.dao.SysLogDao;
import cn.edu.hnie.system.entity.SysLog;
import cn.edu.hnie.system.service.SysLogService;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public Page<SysLog> selectPageList(Page<SysLog> page, Map<String, Object> map) {
		page.setRecords(sysLogDao.selectPageList(page, map));
		return page;
	}
	
}
