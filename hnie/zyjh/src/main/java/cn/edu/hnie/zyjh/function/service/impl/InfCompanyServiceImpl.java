package cn.edu.hnie.zyjh.function.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.hnie.zyjh.function.dao.InfCompanyDao;
import cn.edu.hnie.zyjh.function.entity.InfCompany;
import cn.edu.hnie.zyjh.function.service.InfCompanyService;

@Service
public class InfCompanyServiceImpl extends ServiceImpl<InfCompanyDao, InfCompany> implements InfCompanyService{

	@Autowired
	private InfCompanyDao companyDao;

	public Page<InfCompany> getCompanyList(Page<InfCompany> pageUtil, Map<String, Object> params) {
	
		List<InfCompany> list= companyDao.getCompanyList(pageUtil,params);
		pageUtil.setRecords(list);
		return pageUtil;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Autowired
	private InfCompanyDao infCompanyDao;

	//根据id查询删除一个company的记录
	public int deleteInfCompanyById(Long id) {
		//删除与他相关的企业双选记录
		
		//删除要删除企业的导师记录
		
		infCompanyDao.deleteById(id);
		
		//暂时为0
		return 0;
	}

	//根据id修改一个企业的信息
	public int updateCompanyInfo(InfCompany company) {
		int update = infCompanyDao.update(company);
		return update;
	}
	
	//根据id查询企业的详细信息
	public InfCompany queryInfCompanyById(Long id){
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		List<InfCompany> infCompanies = infCompanyDao.query(map);
		if(infCompanies!=null&&infCompanies.size()!=0){
			return infCompanies.get(0);
		}else{
			return null;
		}
	}
	
	//查询已经签约学院
	public  HashMap<String,Object> findCooperCollege(Long id){
		
		return null;
	}*/
}