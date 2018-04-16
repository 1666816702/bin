package cn.edu.hnie.zyjh.function.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hnie.zyjh.function.dao.InfChooseDao;
import cn.edu.hnie.zyjh.function.entity.InfChoose;
import cn.edu.hnie.zyjh.function.entity.InfStudent;

/*
 * 双选的业务层
 */
@Service
public class InfChooseServiceImpl {

	@Autowired
	private InfChooseDao chooseDao;
	//学生选择企业
	public void chooseEachOther(String srcId,String destId){
		InfChoose choose = new InfChoose();
		choose.setSrcId(srcId);
		choose.setDestId(destId);
		choose.setSrcType("1");
		//choose.setStatus(ChooseType.STUDENT_TO_COMPANY);
		choose.setCreateTime(new Date());
		//chooseDao.save(choose);
	}
	//企业选择学生
	//由于企业可以一次性选择多个卓越班的学生
	public void chooseEachOther(String srcId,String[] destId){
		ArrayList<InfChoose> list = new ArrayList<>();
		for(int i = 0;i<destId.length;i++){
			InfChoose choose = new InfChoose();
			choose.setSrcId(srcId);
			choose.setDestId(destId[2]);
			choose.setSrcType("2");
		//	choose.setStatus(ChooseType.COMPANY_TO_STUDENT);
			choose.setCreateTime(new Date());
		}
	}
	//老师指定
	public void teacherSet(String srcId,String[] destId){
		ArrayList<InfChoose> list = new ArrayList<>();
		for(int i = 0;i<destId.length;i++){
			InfChoose choose = new InfChoose();
			choose.setSrcId(srcId);
			choose.setDestId(destId[2]);
			choose.setSrcType("3");
			//choose.setStatus(ChooseType.TEACHER_SET);
			choose.setCreateTime(new Date());
		}
	}
	
	//修改双选的记录
	
	//第一轮互选成功的记录
	public List<HashMap<String,Object>> querySuccess(int srcType,int status){
		HashMap<String, Object> map = new HashMap<>();
		map.put("srcType", srcType);
		map.put("status", status);
		List<HashMap<String,Object>> chooseMapList = chooseDao.querySuccess(map );
		return chooseMapList;
			
	}
	//查询两轮双选不成功的学生
	public List<InfStudent> queryTwofailStudent(){
		List<InfStudent> stuList = chooseDao.queryTwofailStudent();
		return stuList;
	}
	
	
}
