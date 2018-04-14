package cn.edu.hnie.zyjh.function.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.zyjh.function.entity.InfCompanyEmployee;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfCompanyEmployeeService extends IService<InfCompanyEmployee>{
	
	public InfCompanyEmployee findEmployeeByEmployeeId(Long id);

	public Page<InfCompanyEmployee> getEmployeeList(Page<InfCompanyEmployee> pageUtil, Map<String, Object> params);

	public void addEmployee(InfCompanyEmployee infCompanyEmployee);

	public void addEmployeeBatch(List<InfCompanyEmployee> listBean);

	public Integer getTotalCount(Map<String, Object> param);

	public Integer getCompanyEmpCooperWithDept(Map<String, Object> param);
}
