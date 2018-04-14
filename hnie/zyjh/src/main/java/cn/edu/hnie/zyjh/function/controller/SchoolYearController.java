package cn.edu.hnie.zyjh.function.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hnie.common.utils.R;
import cn.edu.hnie.common.utils.ShiroUtils;
import cn.edu.hnie.zyjh.function.dao.InfSchoolYearDao;
import cn.edu.hnie.zyjh.function.entity.InfSchoolYear;
import cn.edu.hnie.zyjh.function.entity.InfStudent;
import cn.edu.hnie.zyjh.function.service.InfSchoolYearService;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 学年配置
 * 
 * @author wsq
 * 
 */
@RestController
@RequestMapping("/school/year")
public class SchoolYearController extends ServiceImpl<InfSchoolYearDao, InfSchoolYear>{

	@Autowired
	private Producer producer;

	@Autowired
	private InfSchoolYearService schoolYearServiece;
	/**
	 * <查询所有已经配置的学年信息>
	 * 
	 * @return 学年信息
	 */
	@RequestMapping("/list")
	public R getSchoolYearList() {
		// 1.查询学年，是否返回所有的，待确认。返回近5年应该就可以了，不分页
	
		// 2.调用service层，查询学年信息
		List<InfSchoolYear> years =  schoolYearServiece.queryList();
		// 3.把分页参数传回前端
		System.out.println(ShiroUtils.getUser());
		return R.ok().put("yearList", years);
	}

	/**
	 * 生成验证码
	 * @param response 请求报文
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 保存学年信息
	 */
	@RequestMapping("/save")
	public R save(String schoolYearName, Date startDate, Date endDate, String captcha)
			throws IOException {
		// 获取session中的验证码
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		
		// 校验验证码是否和生成的一致
		if (!captcha.equalsIgnoreCase(kaptcha)) {
			return R.error("验证码不正确");
		}
	
		// 3.学年信息不允许修改，只能新增
		InfSchoolYear year = new InfSchoolYear();
		year.setCreateTime(new Date());
		year.setSchoolYearName(schoolYearName);
		year.setEndDate(endDate);
		year.setStartDate(startDate);
		// 4. 调用service层的新增接口
		Boolean r = false; 
		r = schoolYearServiece.save(year);
		// 5.返回正确的的结果，前端在调用该接口后需要刷新页面信息
		// 5.1 把当前学年切换成新增的学年
		// 5.2 在学年的下拉列表中新增一条记录
		if(r){
			return R.ok().put("msg", "激活成功");
		}else{
			return R.error().put("msg", "激活失败");
		}
	}
	//查询现在是那个学年  返回给前端
	
	/*public R findWhichYear(){
		InfSchoolYear year = schoolYearServiece.findWhichYear();
		return R.ok().put("", "");
	}*/
	@RequestMapping("/active")
	public R isActive(){
		Boolean active = schoolYearServiece.isCanActive();
		if(active){
			return R.ok().put("active", 1);
		}else{
			return R.error().put("active", 0);
		}
		
	}
}
