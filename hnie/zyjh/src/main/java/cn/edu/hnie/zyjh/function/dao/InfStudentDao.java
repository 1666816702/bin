package cn.edu.hnie.zyjh.function.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.hnie.zyjh.function.entity.InfStudent;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfStudentDao extends BaseMapper<InfStudent>{

	List<InfStudent> queryPageList(Page<InfStudent> page, Map<String, Object> map);

	List<InfStudent> queryList(Map<String, Object> map);
	
	
	int deleteBatch(Object[] id);	
	
	//查询卓越专业的数量
	int queryCollegeCount(Map<String, Object> params);

	void deleteUserAndUser();

	//查询卓越学生的数量
	int getTotalCount(Map<String, Object> params);

}