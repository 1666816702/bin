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
@TableName("inf_company")
public class InfCompany extends Model<InfCompany> {

    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    @TableId("company_id")
	private String companyId;
    /**
     * 公司名称
     */
	private String name;
    /**
     * 公司类型
     */
	@TableField("company_type")
	private String companyType;
    /**
     * 主要业务
     */
	@TableField("main_business")
	private String mainBusiness;
    /**
     * 联系人 负责人
     */
	@TableField("contact_person")
	private String contactPerson;
    /**
     * 联系方式
     */
	@TableField("contact_tel")
	private String contactTel;
    /**
     * 公司位置
     */
	private String location;
    /**
     * 邮箱
     */
	@TableField("contact_email")
	private String contactEmail;
    /**
     * 创建时间
     */
	@TableField("create_agree_date")
	private Date createAgreeDate;
    /**
     * 评论
     */
	@TableField("cooperation_remark")
	private String cooperationRemark;
    /**
     * 学院，和部门一致
     */
	@TableField("dept_id")
	private Long deptId;
    /**
     * 有效期
     */
	@TableField("expiry_agree_date")
	private Date expiryAgreeDate;


	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Date getCreateAgreeDate() {
		return createAgreeDate;
	}

	public void setCreateAgreeDate(Date createAgreeDate) {
		this.createAgreeDate = createAgreeDate;
	}

	public String getCooperationRemark() {
		return cooperationRemark;
	}

	public void setCooperationRemark(String cooperationRemark) {
		this.cooperationRemark = cooperationRemark;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Date getExpiryAgreeDate() {
		return expiryAgreeDate;
	}

	public void setExpiryAgreeDate(Date expiryAgreeDate) {
		this.expiryAgreeDate = expiryAgreeDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.companyId;
	}

	@Override
	public String toString() {
		return "InfCompany{" +
			", companyId=" + companyId +
			", name=" + name +
			", companyType=" + companyType +
			", mainBusiness=" + mainBusiness +
			", contactPerson=" + contactPerson +
			", contactTel=" + contactTel +
			", location=" + location +
			", contactEmail=" + contactEmail +
			", createAgreeDate=" + createAgreeDate +
			", cooperationRemark=" + cooperationRemark +
			", deptId=" + deptId +
			", expiryAgreeDate=" + expiryAgreeDate +
			"}";
	}
}
