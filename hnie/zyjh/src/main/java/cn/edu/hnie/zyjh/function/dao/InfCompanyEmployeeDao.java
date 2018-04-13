package cn.edu.hnie.zyjh.function.dao;

import java.util.HashMap;
import java.util.List;

import cn.edu.hnie.zyjh.function.entity.InfCompanyEmployee;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfCompanyEmployeeDao {

	List<InfCompanyEmployee> query(HashMap<String, Object> map);

	int selectTotalCount(Long id);
	
}