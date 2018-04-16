package cn.edu.hnie.zyjh.function.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.hnie.zyjh.function.entity.InfCompanyEmployee;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfCompanyEmployeeDao extends BaseMapper<InfCompanyEmployee>{

	//List<InfCompanyEmployee> query(HashMap<String, Object> map);

	//int selectTotalCount(Long id);

	void updateByCompanyId(Serializable id);

	List<InfCompanyEmployee> queyPageList(Page<InfCompanyEmployee> pageUtil, Page<InfCompanyEmployee> pageUtil2);

	InfCompanyEmployee findEmployeeByEmployeeId(Long id);

	Long insertEmployee(InfCompanyEmployee infCompanyEmployee);

	//查询全校的导师的数量
	Integer getTotalCount(Map<String, Object> param);

	//查询与某一个学院企业导师的人数
	Integer getCompanyEmpCooperWithDept(Map<String, Object> param);
	
}