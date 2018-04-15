package cn.edu.hnie.zyjh.function.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.hnie.zyjh.function.entity.InfSchoolTeacher;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfSchoolTeacherDao extends BaseMapper<InfSchoolTeacher>{
	
	//修改校内导师的信息 学年id
	int updateTeacherInfo(InfSchoolTeacher teacher);

	//参数为  教师的id 当前操作的学年id
	int updateById(Map<String, Object> param);

	//根据teacherId schoolYearId查询
	InfSchoolTeacher findTeacherById(Map<String, Object> param);

	//保存校内导师的信息
	void save(InfSchoolTeacher teacher);

	//查询企业里面的校内导师的个数
	//参数为企业id 学年id
	Integer searcherTeacherInCompany(Map<String, Object> params);

	//教务处管理员、校内导师、学院管理人员、学生
	//参数为部门id 学年id
	Integer getTotalCount(Map<String, Object> params);
}