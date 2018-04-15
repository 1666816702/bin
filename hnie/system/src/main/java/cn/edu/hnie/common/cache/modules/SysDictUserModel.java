package cn.edu.hnie.common.cache.modules;

import java.util.List;

import cn.edu.hnie.system.entity.SysDict;

public class SysDictUserModel {

	/**
	 * 数据字典基本信息
	 */
	private SysDict dict;

	/**
	 * 子数据字典
	 */
	private List<SysDictUserModel> childDicts;

	public SysDict getDict() {
		return dict;
	}

	public void setDict(SysDict dict) {
		this.dict = dict;
	}

	public List<SysDictUserModel> getChildDicts() {
		return childDicts;
	}

	public void setChildDicts(List<SysDictUserModel> childDicts) {
		this.childDicts = childDicts;
	}

}
