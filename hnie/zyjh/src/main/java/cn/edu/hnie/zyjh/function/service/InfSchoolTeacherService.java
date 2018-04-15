package cn.edu.hnie.zyjh.function.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.zyjh.function.entity.InfSchoolTeacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfSchoolTeacherService extends IService<InfSchoolTeacher>{
	
	int updateSchoolTeacherInfo(InfSchoolTeacher teacher);

	boolean deleteById(Map<String, Object> param);

	void save(List<InfSchoolTeacher> listBean);

	Page<InfSchoolTeacher> getSchoolTeacherList(Page<InfSchoolTeacher> pageUtil, Map<String, Object> params);

	InfSchoolTeacher findTeacherById(Map<String, Object> param);

	//保存单个老师
	void save(InfSchoolTeacher teacher);

	//查询企业里面的校内导师的个数
	Integer searcherTeacherInCompany(Map<String, Object> params);

	Integer getTotalCount(Map<String, Object> params);
}
