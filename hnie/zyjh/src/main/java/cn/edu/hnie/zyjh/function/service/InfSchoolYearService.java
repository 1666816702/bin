package cn.edu.hnie.zyjh.function.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.hnie.zyjh.function.entity.InfSchoolYear;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfSchoolYearService  extends IService<InfSchoolYear>{
	//学年激活
	void activeSchoolYear(InfSchoolYear schoolYear);

	List<InfSchoolYear> queryList();
	
	//添加save
	
	Boolean save(InfSchoolYear year);

}
