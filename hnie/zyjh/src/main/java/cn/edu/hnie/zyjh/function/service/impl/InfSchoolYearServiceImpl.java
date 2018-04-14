package cn.edu.hnie.zyjh.function.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.hnie.zyjh.function.dao.InfSchoolYearDao;
import cn.edu.hnie.zyjh.function.entity.InfSchoolYear;
import cn.edu.hnie.zyjh.function.service.InfSchoolYearService;

@Service
public class InfSchoolYearServiceImpl extends ServiceImpl<InfSchoolYearDao, InfSchoolYear>implements InfSchoolYearService {

	
	@Autowired
	private InfSchoolYearDao infSchoolYearDao;
	
	//激活学期
	public void activeSchoolYear(InfSchoolYear schoolYear) {
		infSchoolYearDao.updateSchoolYear(schoolYear);	
	}

	//查询近五年的学年
	public List<InfSchoolYear> queryList() {
		
		return infSchoolYearDao.queryList();
	}

	@Transactional
	public Boolean save(InfSchoolYear year) {
		try{
			//更改一条记录
			infSchoolYearDao.updateStatus();
			//插入一条记录
			infSchoolYearDao.insert(year);
		
		}catch(Exception e){
			
		}
		return true;
	}
	
	//判断是否可以激活
	public Boolean isCanActive(){
		int count = infSchoolYearDao.findWantStop();
		return count==0? true:false;
		
	}
	

}
