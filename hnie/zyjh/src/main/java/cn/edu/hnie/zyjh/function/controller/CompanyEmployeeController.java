package cn.edu.hnie.zyjh.function.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hnie.common.annotation.Log;
import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.system.entity.SysConfig;
import cn.edu.hnie.zyjh.base.BatchBaseController;

@RestController
@RequestMapping("/company/employee")
public class CompanyEmployeeController extends BatchBaseController {
	/**
	 * <查询企业员工信息>
	 * 
	 * @param params 查询条件
	 * @return 员工列表，同时返回分页参数
	 */
	@RequestMapping("/list")
	@RequiresPermissions("company:employee:list")
	public R getCompanyEmployeeList(@RequestParam Map<String, Object> params) {
		// 1.查询条件，从前端传入
		// Query query = new Query(params);

		// 2. 分页参数，查询企业人员表。注意只查询当前用户归宿的部门签约企业的员工
		// 查询的时候最少要带上学年，后端在基类中获取部门标识，判断是否是一级部门，如果是不需作为查询条件，否则必须作为查询条件
		// 学校管理人员，学院管理人员，企业导师，企业管理人员，学校导师可能查询企业员工信息
		// Page<SysConfig> pageUtil = new Page<SysConfig>(query.getPage(), query.getLimit());

		// 3.调用service层，根据条件查询企业员工信息，分页查询
		// pageUtil = sysConfigSrv.getSystemConfigList(pageUtil, params);

		// 4.把分页参数传回前端
		// return R.ok().put("page", pageUtil);

		return null;
	}

	/**
	 * <批量导入学生配置>
	 * 
	 * @throws Exception
	 */
	@Log("批量导入企业员工")
	@RequestMapping("/batch")
	public R batch(HttpServletRequest request) throws Exception {
		// 1 调用基类的上传方法，把上传的excel转换成指定模版的对象
		// 1.1 获取经过转换的对象，catch下，看是否有异常，有异常的话转换成消息，返回到前端
		// 1.2第二参数是文件模版中的Id,因为一个模版中可以映射成多个excel
		super.fileUpload(request, "comEmployee");

		// 2. 调用service层新增接口(批量接口，需要重新定义)
		// 增加企业人员不需要增加用户和配置权限
		// 注意type=1

		// 返回正确的的结果
		return R.ok();
	}

	/**
	 * <保存 系统配置>
	 * 
	 * @param systemConfig
	 */
	@Log("保存企业员工配置")
	@RequestMapping("/save")
	@RequiresPermissions("company:employee:save")
	public R save(@RequestBody SysConfig systemConfig) {
		// 1.判断主键是否存在，存在就是修改，否则就是新增

		// 2. 调用service层的修改或者新增接口
		// 2.1 employee_id系统自动生成，且需要提交前获取
		// 2.2 employee_code，由前缀+employee_id，该Id作为用户的userId和初始化密码

		// 3.在新增企业员工时，需要为企业员工增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id(user_role_def)，涉及到的表有sys_user,sys_user_role

		// 返回正确的的结果
		return R.ok();
	}

	/**
	 * <删除企业员工>
	 * 
	 */
	@Log("删除企业员工")
	@RequestMapping("/delete")
	@RequiresPermissions("company:employee:delete")
	public R deleteByIds(@RequestBody Long[] ids) {

		// 1.调用后端的修改接口，删除只修改员工的状态为无效
		// 2.支持同时删除多个员工，由前端控制传入一个id数组
		// sysConfigSrv.deleteBatchIds(Arrays.asList(ids));

		// 1.1 企业员工的角色如果是管理人员，需要删除权限(用户和角色都需要生存)

		// 返回正确的的结果
		return R.ok();
	}
}
