package cn.edu.hnie.zyjh.function.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hnie.common.base.BaseController;
import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.system.entity.SysConfig;

/**
 * 企业导师配置
 * 该功能由教务处管理人员配置，其他的角色只能查询，没有权限修改
 * @author wsq
 *
 */
@RestController
@RequestMapping("/company/teacher")
public class CompanyTeacherController extends BaseController {
	/**
	 * <查询企业导师信息>
	 * 
	 * @param params 查询条件
	 * @return 导师列表，同时返回分页参数
	 */
	@RequestMapping("/list")
	@RequiresPermissions("company:teacher:list")
	public R getCompanyTeacherList(@RequestParam Map<String, Object> params) {
		// 1.查询条件，从前端传入
		//Query query = new Query(params);
		
		// 2. 分页参数，查询企业人员表，注意人员的类型为企业导师
		//Page<SysConfig> pageUtil = new Page<SysConfig>(query.getPage(), query.getLimit());

		// 3.调用service层，根据条件查询企业导师信息，分页查询
		//pageUtil = sysConfigSrv.getSystemConfigList(pageUtil, params);
		
		// 4.把分页参数传回前端
		//return R.ok().put("page", pageUtil);
		
		return null;
	}
	
	/**
	 * <保存 系统配置>
	 * 
	 * @param systemConfig
	 */
	@RequestMapping("/save")
	@RequiresPermissions("company:teacher:save")
	public R save(@RequestBody SysConfig systemConfig) {
		// 1.判断主键是否存在，存在就是修改，否则就是报错
		// 1.1 企业导师来源于企业人员，由教务处管理人员从中选择有资质人的人作为导师
		
		// 2. 调用修改企业员工的service接口，更改类型
		
		// 3.在新增企业导师时，需要为企业导师增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id，其实就是菜单的Id,涉及到的表有sys_user,sys_user_role

		// 返回正确的的结果
		return R.ok();
	}
	
	/**
	 * <统计企业导师个数>
	 * @throws Exception 
	 */
	@RequestMapping("/count")
	public R count(@RequestParam Map<String, Object> params) throws Exception 
	{
		// 1.查询条件，从前端传入

	    // 3.调用service层，统计企业信息
		// 需要根据角色的类型来查询企业导师信息，在基类中获取当前登录的部门标识，另外，需要把学年作为查询条件
		// 后端判断，如果部门是一级部门，则不带部门条件查询，否则都需要带部门标识
		// 前端需要传入当前所处的角色的类型，需要和前端约定，比如：如果是教师，类型为1
		// 涉及到的用户有：学校管理人员，学院管理人员，学生，学校导师，企业导师，企业管理人员

		// 返回正确的的结果
		return R.ok();
	}
	
	/**
	 * <删除企业导师>
	 * 
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("company:teacher:delete")
	public R deleteById(@RequestBody Long id) {

		// 1.调用后端的修改接口，删除只修改导师的状态为无效
		// 2.支持同时删除多个导师，由前端控制传入一个id数组
		//sysConfigSrv.deleteBatchIds(Arrays.asList(ids));
		
		// 2. 调用修改企业员工的service接口，更改类型
		
		
		// 3.删除企业导师，对应的权限表、用户表等系统表数据中也要删除。涉及到的表有sys_user,sys_user_role

		// 返回正确的的结果
		return R.ok();
	}

}
