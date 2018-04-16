package cn.edu.hnie.zyjh.function.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
import cn.edu.hnie.zyjh.function.entity.InfStudent;
import cn.edu.hnie.zyjh.function.service.InfStudentService;

@RestController
@RequestMapping("/student")
public class StudentController extends BatchBaseController{
	/**
	 * <查询学生信息>
	 * 所有查询的地方，都需要带上学年，没有一一注明
	 * 
	 * @param params 查询条件
	 * @return 学生列表，同时返回分页参数
	 */
	@Autowired
	private InfStudentService studentService;
	@RequestMapping("/list")
	@RequiresPermissions("school:student:list")
	public R getSchoolmployeeList(@RequestParam Map<String, Object> params) {
		// 1.查询条件，从前端传入
		Query query = new Query(params);
		
		// 2. 分页参数，查询学生
		// 是否是要根据权限查询学生列表，待确认
		Page<InfStudent> pageUtil = new Page<InfStudent>(query.getPage(), query.getLimit());

		// 3.调用service层，根据条件查询学生信息，分页查询
		pageUtil = studentService.getSystemConfigList(pageUtil, params);
		
		// 4.把分页参数传回前端
		return R.ok().put("page", pageUtil);
		
	}
	
	/**
	 * <保存学生配置>
	 * 
	 * @param systemConfig
	 */
	@Log("保存学生配置")
	@RequestMapping("/save")
	@RequiresPermissions("school:student:save")
	public R save(@RequestBody InfStudent student) {
		// 1.判断主键是否存在，存在就是修改，否则就是保存
		InfStudent infStudent = studentService.queryStudentInfoById(student.getsNo());
		// 1.1 学生，需要新增权限(用户和角色都需要生存)
		if(infStudent!=null){
			// 2. 调用service层的修改或者新增接口
			studentService.updateById(student);
			return R.ok("信息更改成功");
		}else{
			//新增学生信息
			studentService.insert(student);
			// 3.在新增学生时，需要为学生增加用户角色、新增权限，使用默认的用户名和密码
			// 权限需要查询权限配置表，根据类型读取权限id，其实就是菜单的Id
			studentService.addUserAndRoleAndMenu(student);
			// 返回正确的的结果
			return R.ok("添加成功");
		}
	}
	
	/**
	 * <批量导入学生配置>
	 * @throws Exception 
	 * 
	 */
	@Log("保存学生配置")
	@RequestMapping("/batch")
	@RequiresPermissions("school:student:save")
	public R batch(HttpServletRequest request) throws Exception 
	{
		// 1 调用基类的上传方法，把上传的excel转换成指定模版的对象
		// 1.1 获取经过转换的对象，catch下，看是否有异常，有异常的话转换成消息，返回到前端
		List<ExcelImportResult> fileList = (List<ExcelImportResult>) super.fileUpload(request, "student");
		
		List<InfStudent> list = fileList.get(0).getListBean();
		// 2. 调用service层新增接口(批量接口，需要重新定义)，先新增学生信息
		//boolean insertOrUpdateBatch = studentService.insertOrUpdateBatch(list);
		boolean r = studentService.saveBatch(list);
		// 3.为学生增加用户角色、新增权限，使用默认的用户名和密码
		// 权限需要查询权限配置表，根据类型读取权限id,user_role_def表，传入类型，获取role_id
		if(r){
			//TODO 判断问题
			return R.error("添加异常");
		}else{
			return R.ok();
		}
	}
	
	/**
	 * <统计卓越的学生个数>
	 * @throws Exception 
	 */
	@Log("统计学生个数")
	@RequestMapping("/count")
	public R count(@RequestParam Map<String, Object> params) throws Exception 
	{
		// 1.查询条件，从前端传入
		
	    // 3.调用service层，统计企业信息
		// 需要根据角色的类型来查询企业信息，在基类中获取当前登录的部门标识，另外，需要把学年作为查询条件
		// 后端判断，如果部门是一级部门，则不带部门条件查询，否则都需要带部门标识
		// 前端需要传入当前所处的角的类型，需要和前端约定，比如：如果是教师，类型为1
		// 返回正确的的结果
		Long deptId = ShiroUtils.getUser().getDeptId();
		if(deptId==null){
			int count = studentService.getStudentCountAtCompany(params);
			return R.ok().put("count", count);
		}
		if(deptId!=1L){
			params.put("deptId", deptId);
		}
		int count = studentService.getTotalCount(params);
		return R.ok().put("stuCount", count);
	}
	
	/**
	 * 统计卓越专业个数
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/specialty/count")
	public R getSpecialtyCount(@RequestParam Map<String, Object> params) {
		// 1.根据当前用户的部门标识查询子部门。如果是一级部门，查询所有的
		// 2.后端调用一个统计方法，映射SQL中的count，需要带上学年作为查询条件
		// 3.查询部门表，查询三级部门(学校为一级部门，学院二级部门，班级三级部门)
		Long deptId = ShiroUtils.getUser().getDeptId();
		if(deptId!=1L){
			params.put("deptId", deptId);
		}
		int count = studentService.queryCollegeCount(params);
		return R.ok().put("deptCount", count);
	}
	
	/**
	 * <删除学生>
	 * 
	 */
	@Log("删除学生")
	@RequestMapping("/delete")
	@RequiresPermissions("school:student:delete")
	public R deleteByIds(@RequestBody Long id) {

		// 1.调用后端的修改接口，删除只修改学生的状态为无效
		 studentService.deleteById(id);
		// 1.1 删除学生，需要删除权限(用户和角色都需要删除)
		 studentService.deleteUserAndUser();
		// 返回正确的的结果
		return R.ok();
	}
}
