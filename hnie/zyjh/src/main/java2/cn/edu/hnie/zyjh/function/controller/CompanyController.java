package cn.edu.hnie.zyjh.function.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.hnie.common.annotation.Log;
import cn.edu.hnie.common.excel.imports.result.ExcelImportResult;
import cn.edu.hnie.common.utils.Query;
import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.common.utils.ShiroUtils;
import cn.edu.hnie.zyjh.base.BatchBaseController;
import cn.edu.hnie.zyjh.function.entity.InfCompany;
import cn.edu.hnie.zyjh.function.service.InfCompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController extends BatchBaseController {

	/**
	 * <查询企业信息>
	 * 
	 * @param params 查询条件
	 * @return 企业列表，同时返回分页参数
	 */
	@Autowired
	private InfCompanyService infCompanyService;
	@RequestMapping("/list")
	//@RequiresPermissions("company:list")
	public R getCompanyList(@RequestParam Map<String, Object> params) {
		// 1.查询条件，从前端传入  学年id
		 Query query = new Query(params);
		// 2. 分页参数，查询企业表
		Page<InfCompany> pageUtil = new Page<InfCompany>(query.getPage(), query.getLimit());
		

		// 3.调用service层，根据条件查询企业信息，分页查询
		// 需要根据角色的类型来查询企业信息，在基类中获取当前登录的部门标识，另外，需要把学年作为查询条件
		// 后端判断，如果部门是一级部门，则不带部门条件查询，否则都需要带部门标识
		// 前端需要传入当前所处的角色的类型，需要和前端约定，比如：如果是教师，类型为1
		pageUtil = infCompanyService.getCompanyList(pageUtil, params);

		// 4.把分页参数和查询到的列表传回前端
		return R.ok().put("page", pageUtil);

	}

	/**
	 * <批量导入学生配置>
	 * 
	 * @throws Exception
	 */
	@Log("批量导入企业配置")
	@RequestMapping("/batch")
	public R batch(HttpServletRequest request) throws Exception {
		// 1 调用基类的上传方法，把上传的excel转换成指定模版的对象
		// 1.1 获取经过转换的对象，catch下，看是否有异常，有异常的话转换成消息，返回到前端
		// 1.2 第二个参数是excel模版文件的路径，第三个文件模版中的Id,因为一个模版中可以映射成多个excel
		ExcelImportResult result = super.fileUpload(request, "company");
		//获取企业信息
		List<InfCompany> listBean = result.getListBean();

		infCompanyService.addCompanyBatch(listBean);
		// 2. 调用service层新增接口(批量接口，需要重新定义)
		// 批量导入企业信息，需要增加企业管理人员的用户和相关权限
		// 一个企业只能有一个企业管理人员

		// 返回正确的的结果
		return R.ok();
	}
	
	/**
	 * <下载批量导入企业信息的excel模版>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
		return super.downLoad(request, "company");
	}

	/**
	 * <查询企业的个数>
	 * 
	 * @throws Exception
	 */
	@Log("统计企业个数")
	@RequestMapping("/count")
	public R count(@RequestParam Map<String, Object> params) throws Exception {
//	public R count() throws Exception {
		// 1.查询条件，从前端传入
	
		// 3.调用service层，统计企业信息
		// 需要根据角色的类型来查询企业信息，在基类中获取当前登录的部门标识，另外，需要把学年作为查询条件
		Long id = ShiroUtils.getUser().getDeptId();
		// 后端判断，如果部门是一级部门，则不带部门条件查询，否则都需要带部门标识
		if(id!=1L){
			params.put("deptId", id);
		}
		Integer count = infCompanyService.getTotalCount(params);
		// 前端需要传入当前所处的角色的类型，需要和前端约定，比如：如果是教师，类型为1
		
		// 返回正确的的结果
		return R.ok().put("count", count);
	}

	/**
	 * <保存 系统配置>
	 * 
	 * @param systemConfig
	 */
	@Log("保存企业配置")
	@RequestMapping("/save")
	@RequiresPermissions("company:save")
	public R save(@RequestBody InfCompany company) {
		// 1.判断主键是否存在，存在就是修改，否则就是新增
		String id = company.getCompanyId();
		if(id==null){
			//保存企业
			// 1.1 新增企业信息，需要增加企业管理人员的用户和相关权限。新增的时候要把公司的id作为用户的部门标识存起来
			infCompanyService.addCompany(company);
		}else{
			// 1.2 修改企业信息，如果没有修改企业管理人员的名字，只是修改了email或者电话号码，则认为是同一个人，不重新配置权限
			infCompanyService.updateCompanyInfo(company);
			// 1.3 TODO:重新配置权限，需要先把老的权限删除，在新增新的权限
			// 1.4 根据公司的主键找到原来老的权限
			// 一个企业只能有一个企业管理人
		}
	
		
		// 2. 调用service层的修改或者新增接口

		// 返回正确的的结果
		return R.ok();
	}

	/**
	 * <删除企业>
	 * 
	 */
	@Log("删除企业")
	@RequestMapping("/delete")
	@RequiresPermissions("company:delete")
	public R deleteById(@RequestBody String[] ids) {

		// 1.调用后端的修改接口，删除只修改导师的状态为无效
		// 2.支持同时删除多个导师，由前端控制传入一个id数组
		infCompanyService.deleteBatchIds(Arrays.asList(ids));

		// 返回正确的的结果
		return R.ok();
	}
	
	@Log("查询企业的信息")
	@RequestMapping("/find")
	public R findById(@RequestBody String id){
		
		InfCompany company = infCompanyService.selectById(id);
		return R.ok().put("company", company);
		
	}
}
