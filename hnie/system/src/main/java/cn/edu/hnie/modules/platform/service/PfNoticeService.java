package cn.edu.hnie.modules.platform.service;

import java.util.Map;

import cn.edu.hnie.modules.platform.entity.PfNotice;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author theodo
 * @since 2017-12-18
 */
public interface PfNoticeService extends IService<PfNotice> {

	Page<PfNotice> queryPageList(Page<PfNotice> pageUtil, Map<String, Object> map);

	void deleteBatch(Long[] noticeIds);
	
}
