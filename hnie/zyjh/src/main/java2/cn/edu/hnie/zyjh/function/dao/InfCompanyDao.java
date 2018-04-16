package cn.edu.hnie.zyjh.function.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import  cn.edu.hnie.zyjh.function.entity.InfCompany;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */

public interface InfCompanyDao extends BaseMapper<InfCompany> {

	List<InfCompany> getCompanyList(Page<InfCompany> page, Map<String, Object> params);

	void updateById(Serializable id);

	Integer getTotalCount(Map<String, Object> params);
}