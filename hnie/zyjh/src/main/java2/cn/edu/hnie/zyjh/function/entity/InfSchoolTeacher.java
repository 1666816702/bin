package cn.edu.hnie.zyjh.function.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-04-12
 */
@TableName("inf_school_teacher")
public class InfSchoolTeacher extends Model<InfSchoolTeacher> {

    private static final long serialVersionUID = 1L;

    /**
     * 在校老师的id
     */
    @TableId("teacher_id")
	private String teacherId;
    /**
     * 学院，和部门一致
     */
	@TableField("dept_id")
	private Long deptId;
    /**
     * 专业，和部门一致
     */
	@TableField("specialty_id")
	private String specialtyId;
    /**
     * 姓名
     */
	private String name;
    /**
     * 1:有效  2:无效
     */
	private String status;
    /**
     * 性别
     */
	private String sex;
    /**
     * 职称
     */
	@TableField("job_title")
	private String jobTitle;
    /**
     * 学历
     */
	private String degree;
	@TableField("school_year_id")
	private Long schoolYearId;
    /**
     * 扩展属性
     */
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;


	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(String specialtyId) {
		this.specialtyId = specialtyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Long getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Long schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	@Override
	protected Serializable pkVal() {
		return this.teacherId;
	}

	@Override
	public String toString() {
		return "InfSchoolTeacher{" +
			", teacherId=" + teacherId +
			", deptId=" + deptId +
			", specialtyId=" + specialtyId +
			", name=" + name +
			", status=" + status +
			", sex=" + sex +
			", jobTitle=" + jobTitle +
			", degree=" + degree +
			", schoolYearId=" + schoolYearId +
			", ext1=" + ext1 +
			", ext2=" + ext2 +
			", ext3=" + ext3 +
			", ext4=" + ext4 +
			", ext5=" + ext5 +
			"}";
	}
}
