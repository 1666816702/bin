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
@TableName("inf_com_teacher_real")
public class InfComTeacherReal extends Model<InfComTeacherReal> {

    private static final long serialVersionUID = 1L;

    @TableId("rel_id")
	private Long relId;
	@TableField("teacher_id")
	private Long teacherId;
	@TableField("company_id")
	private Long companyId;
	@TableField("school_year_id")
	private Long schoolYearId;
	private String status;
	@TableField("dept_id")
	private Long deptId;


	public Long getRelId() {
		return relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Long schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	protected Serializable pkVal() {
		return this.relId;
	}

	@Override
	public String toString() {
		return "InfComTeacherReal{" +
			", relId=" + relId +
			", teacherId=" + teacherId +
			", companyId=" + companyId +
			", schoolYearId=" + schoolYearId +
			", status=" + status +
			", deptId=" + deptId +
			"}";
	}
}
