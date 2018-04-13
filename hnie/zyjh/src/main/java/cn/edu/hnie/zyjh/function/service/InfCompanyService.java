package cn.edu.hnie.zyjh.function.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.zyjh.function.entity.InfCompany;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfCompanyService extends IService<InfCompany>{

	Page<InfCompany> getCompanyList(Page<InfCompany> page, Map<String, Object> params);
	
}
