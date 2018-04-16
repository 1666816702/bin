package cn.edu.hnie.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hnie.common.utils.Constant;
import cn.edu.hnie.system.dao.SysMenuDao;
import cn.edu.hnie.system.dao.SysUserDao;
import cn.edu.hnie.system.dao.SysUserTokenDao;
import cn.edu.hnie.system.entity.SysMenu;
import cn.edu.hnie.system.entity.SysUser;
import cn.edu.hnie.system.entity.SysUserToken;
import cn.edu.hnie.system.service.ShiroService;

@Service
public class ShiroServiceImpl implements ShiroService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserTokenDao sysUserTokenDao;

	@Override
	public Set<String> getUserPermissions(long userId) {
		List<String> permsList;

		// 系统管理员，拥有最高权限
		if (userId == Constant.SUPER_ADMIN) {
			List<SysMenu> menuList = sysMenuDao.queryList(new HashMap<String, Object>());
			permsList = new ArrayList<>(menuList.size());
			for (SysMenu menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = sysUserDao.queryAllPerms(userId);
		}
		// 用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		return permsSet;
	}

	@Override
	public SysUserToken queryByToken(String token) {
		return sysUserTokenDao.queryByToken(token);
	}

	@Override
	public SysUser queryUser(Long userId) {
		return sysUserDao.selectById(userId);
	}
}
