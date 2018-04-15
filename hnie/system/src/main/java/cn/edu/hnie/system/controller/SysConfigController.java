package cn.edu.hnie.system.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.hnie.common.annotation.Log;
import cn.edu.hnie.common.base.BaseController;
import cn.edu.hnie.common.utils.DateUtils;
import cn.edu.hnie.common.utils.Query;
import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.common.validator.ValidatorUtils;
import cn.edu.hnie.system.entity.SysConfig;
import cn.edu.hnie.system.service.SysConfigService;

/**
 * 系统参数配置
 * 
 * @author wsq
 *
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {

	@Autowired
	private SysConfigService sysConfigSrv;

	/**
	 * <查询系统配置信息>
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public R systemConfigList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		Page<SysConfig> pageUtil = new Page<SysConfig>(query.getPage(), query.getLimit());

		pageUtil = sysConfigSrv.getSystemConfigList(pageUtil, params);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * <进入编辑页面>
	 * 
	 * @param role
	 * @return
	 */
	@Log("修改角色")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfig role) {

		ValidatorUtils.validateEntity(role);

		sysConfigSrv.updateById(role);

		return R.ok();
	}

	/**
	 * <保存 系统配置>
	 * 
	 * @param systemConfig
	 */
	@Log("保存 系统配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfig systemConfig) {
		SysConfig config = null;
		if (null != systemConfig.getConfigId()) {
			config = sysConfigSrv.selectById(systemConfig.getConfigId());
			systemConfig.setUpdateTime(DateUtils.getSysDate());
			systemConfig.setCreateUser(getUserId());
			BeanUtils.copyProperties(systemConfig, config);
		} else {
			config = systemConfig;
			config.setCreateTime(DateUtils.getSysDate());
			config.setCreateUser(getUserId());
		}
		// 保存
		sysConfigSrv.insert(config);

		// 返回正确的的结果
		return R.ok();
	}

	/**
	 * <删除系统参数配置>
	 * 
	 */
	@Log("删除系统参数配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public R deleteByIds(@RequestBody Long[] ids) {

		sysConfigSrv.deleteBatchIds(Arrays.asList(ids));

		// 返回正确的的结果
		return R.ok();
	}
}
