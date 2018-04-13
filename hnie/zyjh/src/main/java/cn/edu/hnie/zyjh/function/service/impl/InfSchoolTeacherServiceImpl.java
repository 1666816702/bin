package cn.edu.hnie.zyjh.function.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hnie.zyjh.function.dao.InfSchoolTeacherDao;
import cn.edu.hnie.zyjh.function.entity.InfSchoolTeacher;
import cn.edu.hnie.zyjh.function.service.InfSchoolTeacherService;

@Service
public class InfSchoolTeacherServiceImpl{

	@Autowired
	private InfSchoolTeacherDao infSchoolTeacherDao;

	//已有添加一条信息
	
	//已有批量导入信息
	
	//已有查询总的记录数
	
	//已有分页
	
	//修改函数
	
	public int updateSchoolTeacherInfo(InfSchoolTeacher teacher){
		int i = infSchoolTeacherDao.updateTeacherInfo(teacher);
		return i;
	}
	
}
