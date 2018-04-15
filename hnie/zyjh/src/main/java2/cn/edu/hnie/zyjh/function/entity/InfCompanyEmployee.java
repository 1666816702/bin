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
@TableName("inf_company_employee")
public class InfCompanyEmployee extends Model<InfCompanyEmployee> {

    private static final long serialVersionUID = 1L;

    @TableId("employee_id")
	private Long employeeId;
	@TableField("company_id")
	private String companyId;
    /**
     * 1：企业导师  2：企业管理人员
     */
	private String type;
	@TableField("profe_year")
	private Integer profeYear;
    /**
     * 1:有效  2:无效
     */
	private String status;
	@TableField("school_year_id")
	private Long schoolYearId;
	private String name;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;


	public Long getTeacherId() {
		return employeeId;
	}

	public void setTeacherId(Long teacherId) {
		this.employeeId = employeeId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getProfeYear() {
		return profeYear;
	}

	public void setProfeYear(Integer profeYear) {
		this.profeYear = profeYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Long schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public String getName() {
		return name;
	}

	public void setExt1(String ext1) {
		this.name = name;
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

	protected Serializable pkVal() {
		return this.employeeId;
	}

	@Override
	public String toString() {
		return "InfCompanyEmployee{" +
			", employeeId=" + employeeId +
			", companyId=" + companyId +
			", type=" + type +
			", profeYear=" + profeYear +
			", status=" + status +
			", schoolYearId=" + schoolYearId +
			", ext1=" + name +
			", ext2=" + ext2 +
			", ext3=" + ext3 +
			", ext4=" + ext4 +
			", ext5=" + ext5 +
			"}";
	}
}
