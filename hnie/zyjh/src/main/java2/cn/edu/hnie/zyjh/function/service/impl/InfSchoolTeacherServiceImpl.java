package cn.edu.hnie.zyjh.function.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import cn.edu.hnie.system.dao.SysUserDao;
import cn.edu.hnie.system.entity.SysUser;
import cn.edu.hnie.system.service.SysUserService;
import cn.edu.hnie.zyjh.function.dao.InfSchoolTeacherDao;
import cn.edu.hnie.zyjh.function.dao.UserRoleDefDao;
import cn.edu.hnie.zyjh.function.entity.InfSchoolTeacher;
import cn.edu.hnie.zyjh.function.service.InfSchoolTeacherService;
import sun.tools.jconsole.Tab;

@Service
public class InfSchoolTeacherServiceImpl extends ServiceImpl<InfSchoolTeacherDao, InfSchoolTeacher> implements InfSchoolTeacherService{

	@Autowired
	private InfSchoolTeacherDao infSchoolTeacherDao;

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private UserRoleDefDao userRoleDefDao;
	//重写了一个根据id删除记录的方法
	//实则把老师的记录改成无效状态
	public boolean deleteById(Map<String, Object> param) {
		//修改学校的老师为无效状态
		int i = infSchoolTeacherDao.updateById(param);
		return i==0?false:true;
	}



	public int updateSchoolTeacherInfo(InfSchoolTeacher teacher){
		int i = infSchoolTeacherDao.updateTeacherInfo(teacher);
		return i;
	}



	@Transactional
	public void save(List<InfSchoolTeacher> listBean) {
		try{
			for (InfSchoolTeacher teacher : listBean) {
				this.save(teacher);
			}
		}catch(Exception e){
			
		}
	}

	public Page<InfSchoolTeacher> getSchoolTeacherList(Page<InfSchoolTeacher> pageUtil, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}



	public InfSchoolTeacher findTeacherById(Map<String, Object> param) {
		InfSchoolTeacher teacher = infSchoolTeacherDao.findTeacherById(param);
		return teacher;
	}


	@Transactional
	public void save(InfSchoolTeacher teacher) {

		infSchoolTeacherDao.save(teacher);
		// 2.1 工号作为userId和初始化密码
		SysUser user = new SysUser();
		user.setDeptId(teacher.getDeptId());
		user.setUsername(teacher.getTeacherId());
		user.setPassword(teacher.getTeacherId());
		List<Long> roleIdList = new ArrayList<>();
		//查询默认的权限
		userRoleDefDao.findDelRole("3");
		user.setRoleIdList(roleIdList );
		userService.save(user);
		// 3.在新增校内导师时，需要为校内导师增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id，其实就是菜单的Id
		// 返回正确的的结果
	}



	@Override
	public Integer searcherTeacherInCompany(Map<String, Object> params) {
		return infSchoolTeacherDao.searcherTeacherInCompany(params);
	}



	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		return infSchoolTeacherDao.getTotalCount(params);
	}
	
}
