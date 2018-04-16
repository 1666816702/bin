package cn.edu.hnie.zyjh.function.dao;

import java.util.HashMap;
import java.util.List;

import cn.edu.hnie.zyjh.function.entity.InfChoose;
import  cn.edu.hnie.zyjh.function.entity.InfStudent;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfChooseDao{

	//查询默认情况或者是失败的学生
	List<InfStudent> queryTwofailStudent();

	//查询第一轮和第二轮双选成功的学生
	List<HashMap<String, Object>> querySuccess(HashMap<String, Object> map);

}