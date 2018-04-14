package cn.edu.hnie.zyjh.function.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.zyjh.function.entity.InfStudent;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfStudentService extends IService<InfStudent>{
	
	InfStudent queryStudentInfoById(Long id);

	Page<InfStudent> getSystemConfigList(Page<InfStudent> pageUtil, Map<String, Object> params);

	void deleteUserAndUser();

	void addUserAndRoleAndMenu(InfStudent student);

	boolean saveBatch(List<InfStudent> list);

	int getTotalCount(Map<String, Object> params);

	int queryCollegeCount(Map<String, Object> params);

	int getStudentCountAtCompany(Map<String, Object> params);
}
