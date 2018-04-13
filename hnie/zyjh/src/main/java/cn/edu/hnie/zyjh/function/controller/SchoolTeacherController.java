package cn.edu.hnie.zyjh.function.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hnie.common.base.BaseController;
import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.system.entity.SysConfig;

@RestController
@RequestMapping("/school/teacher")
public class SchoolTeacherController extends BaseController
{
	/**
	 * <查询校内导师信息>
	 * 所有查询的地方，都需要带上学年，没有一一注明
	 * 
	 * @param params 查询条件
	 * @return 员工列表，同时返回分页参数
	 */
	@RequestMapping("/list")
	@RequiresPermissions("school:teacher:list")
	public R getSchoolTeacherList(@RequestParam Map<String, Object> params) {
		// 1.查询条件，从前端传入
		//Query query = new Query(params);
		
		// 2. 分页参数，查询校内导师
		//Page<SysConfig> pageUtil = new Page<SysConfig>(query.getPage(), query.getLimit());

		// 3.调用service层，根据条件查询校内导师信息，分页查询
		//pageUtil = sysConfigSrv.getSystemConfigList(pageUtil, params);
		
		// 4.把分页参数传回前端
		//return R.ok().put("page", pageUtil);
		
		return null;
	}
	
	/**
	 * <保存校内导师配置>
	 * 
	 * @param systemConfig
	 */
	@RequestMapping("/save")
	@RequiresPermissions("school:teacher:save")
	public R save(@RequestBody SysConfig systemConfig) {
		// 1.判断主键是否存在，存在就是修改，否则就是报错
		// 1.1 校内导师，需要新增权限(用户和角色都需要生存)
		
		// 2. 调用service层的修改或者新增接口
		// 2.1 工号作为userId和初始化密码
		
		// 3.在新增校内导师时，需要为校内导师增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id，其实就是菜单的Id

		// 返回正确的的结果
		return R.ok();
	}
	
	/**
	 * <统计学校导师个数>
	 * @throws Exception 
	 */
	@RequestMapping("/count")
	public R count(@RequestParam Map<String, Object> params) throws Exception 
	{
		// 1.查询条件，从前端传入

	    // 3.调用service层，统计企业信息
		// 需要根据角色的类型来查询学校导师信息，在基类中获取当前登录的部门标识，另外，需要把学年作为查询条件
		// 后端判断，如果部门是一级部门，则不带部门条件查询，否则都需要带部门标识
		// 前端需要传入当前所处的角色的类型，需要和前端约定，比如：如果是教师，类型为1
		// 涉及到的用户有：学校管理人员，学院管理人员，学生，企业导师，企业管理人员

		// 返回正确的的结果
		return R.ok();
	}
	
	/**
	 * <批量导入校内导师配置>
	 * 
	 */
	@RequestMapping("/batch")
	@RequiresPermissions("school:teacher:save")
	public R batch(HttpServletRequest request) 
	{
		// 1 调用基类的上传方法，把上传的excel转换成指定模版的对象
		// 1.1 获取经过转换的对象，catch下，看是否有异常，有异常的话转换成消息，返回到前端
		
		// 2. 调用service层新增接口(批量接口，需要重新定义)，先新增校内导师信息
		
		// 3.为校内导师增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id(user_role_def)，涉及到的表有sys_user,sys_user_role

		// 返回正确的的结果
		return R.ok();
	}
	
	/**
	 * <删除校内导师>
	 * 
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("school:teacher:delete")
	public R deleteByIds(@RequestBody Long id) {

		// 1.调用后端的修改接口，删除只修改导师的状态为无效
		
		// 1.1 删除校内导师，需要删除权限(用户和角色都需要删除)

		// 返回正确的的结果
		return R.ok();
	}
}
