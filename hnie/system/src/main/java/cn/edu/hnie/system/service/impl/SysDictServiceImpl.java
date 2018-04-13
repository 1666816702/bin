package cn.edu.hnie.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.hnie.system.dao.SysDictDao;
import cn.edu.hnie.system.entity.SysDict;
import cn.edu.hnie.system.service.SysDictService;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDict> implements SysDictService {

	@Autowired
	private SysDictDao sysDictDao;

	@Override
	public List<SysDict> queryDict(Map<String, Object> argMap) {
		return sysDictDao.findList(argMap);
	}
}
