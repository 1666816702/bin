package cn.edu.hnie.zyjh.function.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.hnie.common.exception.RRException;
import cn.edu.hnie.system.entity.SysConfig;
import cn.edu.hnie.system.entity.SysUser;
import cn.edu.hnie.system.service.SysUserService;
import cn.edu.hnie.zyjh.function.dao.InfStudentDao;
import cn.edu.hnie.zyjh.function.entity.InfStudent;
import cn.edu.hnie.zyjh.function.service.InfStudentService;

@Service
public class InfStudentServiceImpl extends ServiceImpl<InfStudentDao, InfStudent> implements InfStudentService {

	@Autowired
	private InfStudentDao infStudentDao;
	
	@Autowired
	private SysUserService userService;
	//根据id查询学生的信息
	public InfStudent queryStudentInfoById(Long id) {
		HashMap<String, Object> map = new HashMap<>();
		List<InfStudent> studentList = infStudentDao.selectByMap(map);
		if(studentList!=null&&studentList.size()!=0){
			return studentList.get(0);
		}else{
			return null;
		}
	}
	//查询卓越专业的数量
	//sql:select  count(distinct college_id)from inf_student
	public int queryCollegeCount(Map<String, Object> params){
		int count = infStudentDao.queryCollegeCount(params);
		return count;
	}

	//这是分页
	public Page<InfStudent> getSystemConfigList(Page<InfStudent> pageUtil, Map<String, Object> params) {
		List<InfStudent> list = infStudentDao.queryPageList(pageUtil, params);
		return pageUtil.setRecords(list);
	}
	@Override
	public void deleteUserAndUser() {
		infStudentDao.deleteUserAndUser();
		
	}
	// 3.在新增学生时，需要为学生增加用户角色、新增权限，使用默认的用户名和密码
	// 权限需要查询权限配置表，根据类型读取权限id，其实就是菜单的Id
	public void addUserAndRoleAndMenu(InfStudent student) {
		
		//新建用户
		SysUser user = new SysUser();
		user.setCreateTime(new Date());
		user.setUsername(student.getsNo().toString());
		user.setPassword(student.getsNo().toString());
		user.setEmail(student.getEmail());
		user.setMobile(student.getContactTel());
		user.setStatus(1);
		user.setDeptId(student.getDeptId());
		//设置权限
		List<Long> roleIdList = new ArrayList<>();
		//TODO  这个地方有待商榷
		roleIdList.add(1L);
		user.setRoleIdList(roleIdList);
		userService.save(user);
	}
	@Override
	public boolean saveBatch(List<InfStudent> list) {
		if(list==null){
			throw new RRException("数据异常");
		}
		if(list.size()==0){
			throw new RRException("添加学生数为0");
		}
		try{
			for (InfStudent infStudent : list) {
				//保存学生
				this.insert(infStudent);
				//设置权限
				this.addUserAndRoleAndMenu(infStudent);
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public int getTotalCount(Map<String, Object> params) {
		int count =  infStudentDao.getTotalCount(params);
		return count;
	}
	
	
	//数据导出
	
}
