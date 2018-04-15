package cn.edu.hnie.zyjh.function.entity;

import java.io.Serializable;

import java.util.Date;
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
@TableName("inf_student")
public class InfStudent extends Model<InfStudent> {

    private static final long serialVersionUID = 1L;

    @TableId("s_id")
	private String sId;
	@TableField("s_no")
	private Long sNo;
    /**
     * 学院，和部门一致
     */
	@TableField("dept_id")
	private Long deptId;
    /**
     * 班级，和部门一致
     */
	@TableField("class_id")
	private String classId;
	@TableField("s_name")
	private String sName;
	private String sex;
	private String nation;
	@TableField("local_institution")
	private String localInstitution;
	private Double credits;
	@TableField("contact_tel")
	private String contactTel;
	@TableField("school_year_id")
	private Long schoolYearId;
	@TableField("create_date")
	private Date createDate;


	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getLocalInstitution() {
		return localInstitution;
	}

	public void setLocalInstitution(String localInstitution) {
		this.localInstitution = localInstitution;
	}

	public Double getCredits() {
		return credits;
	}

	public void setCredits(Double credits) {
		this.credits = credits;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public Long getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Long schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.sId;
	}

	@Override
	public String toString() {
		return "InfStudent{" +
			", sId=" + sId +
			", sNo=" + sNo +
			", deptId=" + deptId +
			", classId=" + classId +
			", sName=" + sName +
			", sex=" + sex +
			", nation=" + nation +
			", localInstitution=" + localInstitution +
			", credits=" + credits +
			", contactTel=" + contactTel +
			", schoolYearId=" + schoolYearId +
			", createDate=" + createDate +
			"}";
	}
}
