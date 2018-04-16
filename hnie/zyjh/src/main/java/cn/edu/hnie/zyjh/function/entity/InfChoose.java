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
@TableName("inf_choose")
public class InfChoose extends Model<InfChoose> {

    private static final long serialVersionUID = 1L;

    @TableId("choose_id")
	private Long chooseId;
    /**
     * 从学生角度出发，选择企业，则src_id = student_id
            从企业角度出发，选择学生，则src_id = company_id
     */
	@TableField("src_id")
	private String srcId;
    /**
     * 和源Id的意思换过来
     */
	@TableField("dest_id")
	private String destId;
	@TableField("school_year_id")
	private Long schoolYearId;
    /**
     * 0: 初始化状态
                                                 1：第一次双选成功
                                                 2：第二次双选成功
                                                 3：失败
                                                 4：指定
     */
	private Integer status;
    /**
     * 源的类型
                                                      学生:src_type= 1
                                                      企业:src_type =2
                                                      老师：src_type=3
     */
	@TableField("src_type")
	private String srcType;
	private String remark;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("student_name")
	private String studentName;
	@TableField("company_name")
	private String companyName;
	@TableField("dept_id")
	private Long deptId;


	public Long getChooseId() {
		return chooseId;
	}

	public void setChooseId(Long chooseId) {
		this.chooseId = chooseId;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getDestId() {
		return destId;
	}

	public void setDestId(String destId) {
		this.destId = destId;
	}

	public Long getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Long schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	protected Serializable pkVal() {
		return this.chooseId;
	}

	@Override
	public String toString() {
		return "InfChoose{" +
			", chooseId=" + chooseId +
			", srcId=" + srcId +
			", destId=" + destId +
			", schoolYearId=" + schoolYearId +
			", status=" + status +
			", srcType=" + srcType +
			", remark=" + remark +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", studentName=" + studentName +
			", companyName=" + companyName +
			", deptId=" + deptId +
			"}";
	}
}
