package cn.edu.hnie.zyjh.function.service.impl;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.hnie.system.dao.SysUserDao;
import cn.edu.hnie.system.entity.SysUser;
import cn.edu.hnie.zyjh.function.dao.InfCompanyEmployeeDao;
import cn.edu.hnie.zyjh.function.dao.UserRoleDefDao;
import cn.edu.hnie.zyjh.function.entity.InfCompanyEmployee;
import cn.edu.hnie.zyjh.function.service.InfCompanyEmployeeService;
import cn.edu.hnie.zyjh.util.PinYin4jUtils;

@Service
public class InfCompanyEmployeeServiceImpl extends ServiceImpl<InfCompanyEmployeeDao, InfCompanyEmployee> implements InfCompanyEmployeeService{

	@Autowired
	private InfCompanyEmployeeDao companyEmployeeDao;

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private UserRoleDefDao roleDefDao;
	public InfCompanyEmployee findEmployeeByEmployeeId(Long id) {
		
		InfCompanyEmployee employee = companyEmployeeDao.findEmployeeByEmployeeId(id);
		return employee;
	}

	//分页
	public Page<InfCompanyEmployee> getEmployeeList(Page<InfCompanyEmployee> pageUtil, Map<String, Object> params) {
		
		List<InfCompanyEmployee> list = companyEmployeeDao.queyPageList(pageUtil,pageUtil);
		return pageUtil.setRecords(list);
	}

	//添加一个企业导师
	public void addEmployee(InfCompanyEmployee infCompanyEmployee) {
	
		Long id = companyEmployeeDao.insertEmployee(infCompanyEmployee);
		// 2.1 employee_id系统自动生成，且需要提交前获取
		// 2.2 employee_code，由前缀+employee_id，该Id作为用户的userId和初始化密码
		// 3.在新增企业员工时，需要为企业员工增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id(user_role_def)，涉及到的表有sys_user,sys_user_role
		SysUser user = new SysUser();
		user.setCreateTime(new Date());
		user.setDeptId(null);
		user.setUserId(id);
		String username = PinYin4jUtils.stringArrayToString( PinYin4jUtils.getHeadByString(infCompanyEmployee.getName(), false));
		user.setUsername(username+id);
		user.setPassword(username+id);
		Long roleId = roleDefDao.findDelRole("2");
		List<Long> roleIdList = new ArrayList<>();
		roleIdList.add(roleId);
		user.setRoleIdList(roleIdList);
		sysUserDao.insert(user);
	}

	@Transactional
	public void addEmployeeBatch(List<InfCompanyEmployee> listBean) {
		try{
			for (InfCompanyEmployee employee : listBean) {
				this.addEmployee(employee);
			}
		}catch(Exception e){
			// TODO 带商量
		}
	}

	
	public Integer getTotalCount(Map<String, Object> param) {
		
		return companyEmployeeDao.getTotalCount(param);
	}

	@Override
	public Integer getCompanyEmpCooperWithDept(Map<String, Object> param) {
		return companyEmployeeDao.getCompanyEmpCooperWithDept(param);
	}	
}
