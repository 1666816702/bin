package cn.edu.hnie.zyjh.function.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.hnie.zyjh.function.dao.InfCompanyEmployeeDao;
import cn.edu.hnie.zyjh.function.entity.InfCompanyEmployee;

public class InfCompanyEmployeeServiceImpl{

	@Autowired
	private InfCompanyEmployeeDao companyEmployeeDao;

	//通过企业id查询企业导师-----》分页操作
	
	//根据企业导师id展示导师的信息
	
	public InfCompanyEmployee findEmployeeByEmployeeId(Long id){
		
		HashMap<String, Object> map = new HashMap<>();
		
		List<InfCompanyEmployee> Employees = companyEmployeeDao.query(map);
		
		if(Employees!=null&&Employees.size()!=0){
			return Employees.get(0);
		}
		return null;
	}
	
	public void infCompanyEmployeeDeleteById(Long id){
	//	companyEmployeeDao.deleteById(id);
	}

}
