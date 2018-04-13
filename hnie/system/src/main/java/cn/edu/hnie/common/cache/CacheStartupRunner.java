package cn.edu.hnie.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cn.edu.hnie.common.cache.modules.SysDictUserModel;
import cn.edu.hnie.common.utils.ValidateHelper;
import cn.edu.hnie.system.entity.SysConfig;
import cn.edu.hnie.system.entity.SysDict;
import cn.edu.hnie.system.service.SysConfigService;
import cn.edu.hnie.system.service.SysDictService;

/**
 * 服务启动执行
 *
 */
@Component
public class CacheStartupRunner implements CommandLineRunner {

	@Autowired
	private SysDictService sysDictService;

	@Autowired
	private SysConfigService sysConfigSrv;

	/**
	 * 数据字典
	 */
	private Map<String, SysDictUserModel> dictCache = new HashMap<String, SysDictUserModel>();

	/**
	 * 系统参数
	 */
	private Map<String, String> sysParamCache = new HashMap<String, String>();

	@Override
	public void run(String... args) throws Exception {

		// 初始化数据字典
		initDictCache();

		// 初始化系统参数
		initSysParamCache();
	}

	private void initSysParamCache() {

		List<SysConfig> configList = sysConfigSrv.queryAllSystemConfig();
		if (ValidateHelper.isNotEmptyList(configList)) {
			for (SysConfig o : configList) {
				sysParamCache.put(o.getConfigName(), o.getConfigValue());
			}
		}
	}

	private void initDictCache() {

		Map<String, Object> arg = new HashMap<String, Object>();

		arg.put("pId", "123");
		List<SysDict> pDictList = sysDictService.queryDict(arg);

		if (ValidateHelper.isNotEmptyList(pDictList)) {
			for (SysDict dict : pDictList) {
				SysDictUserModel sysDict = buildSysDictUserModel(dict);

				dictCache.put(dict.getLabel(), sysDict);
			}
		}

	}

	private SysDictUserModel buildSysDictUserModel(SysDict dict) {
		SysDictUserModel dictUserModel = new SysDictUserModel();
		dictUserModel.setDict(dict);

		// 获取数据字典项
		dictUserModel.setChildDicts(findChildDict(dict.getId()));
		return dictUserModel;
	}

	private List<SysDictUserModel> findChildDict(Long pId) {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("pId", pId);

		List<SysDict> dictList = sysDictService.queryDict(param);

		return buildChildDictSysList(dictList);
	}

	private List<SysDictUserModel> buildChildDictSysList(List<SysDict> cDictList) {

		if (ValidateHelper.isNotEmptyList(cDictList)) {
			List<SysDictUserModel> dictUserModelList = new ArrayList<SysDictUserModel>();
			for (SysDict dict : cDictList) {

				dictUserModelList.add(buildSysDictUserModel(dict));
			}

			return dictUserModelList;
		}

		return null;
	}
}
