package cn.edu.hnie.zyjh.function.service;

import java.util.HashMap;
import java.util.List;

import	cn.edu.hnie.zyjh.function.entity.InfStudent;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-05
 */
public interface InfChooseService {
	public List<HashMap<String,Object>> querySuccess(int srcType,int status);

	public List<InfStudent> queryTwofailStudent();
	
	public void chooseEachOther(Long srcId,Long destId);
	
	public void chooseEachOther(Long srcId,Long[] destId);
	
	public void teacherSet(Long srcId,Long[] destId);
}
