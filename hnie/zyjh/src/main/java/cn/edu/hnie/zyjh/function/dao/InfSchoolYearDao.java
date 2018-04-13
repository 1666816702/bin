package cn.edu.hnie.zyjh.function.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.hnie.zyjh.function.entity.InfSchoolYear;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfSchoolYearDao extends BaseMapper<InfSchoolYear>{
	//激活学年
	void updateSchoolYear(InfSchoolYear schoolYear);
	//
	List<InfSchoolYear> queryList();
	void updateStatus();
}