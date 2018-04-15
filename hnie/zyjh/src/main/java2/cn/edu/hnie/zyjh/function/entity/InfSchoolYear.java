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
@TableName("inf_school_year")
public class InfSchoolYear extends Model<InfSchoolYear> {

    private static final long serialVersionUID = 1L;

    /**
     * 学年id
     */
    @TableId("school_year_id")
	private Long schoolYearId;
    /**
     * 开始时间
     */
	@TableField("start_date")
	private Date startDate;
    /**
     * 截止时间
     */
	@TableField("end_date")
	private Date endDate;
    /**
     * 状态
     */
	private String status;
    /**
     * 标记
     */
	private String remark;
	@TableField("create_time")
	private Date createTime;
	@TableField("school_year_name")
	private String schoolYearName;
	

	public String getSchoolYearName() {
		return schoolYearName;
	}

	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}

	public Long getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Long schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	protected Serializable pkVal() {
		return this.schoolYearId;
	}

	@Override
	public String toString() {
		return "InfSchoolYear{" +
			", schoolYearId=" + schoolYearId +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", status=" + status +
			", remark=" + remark +
			", createTime=" + createTime +
			"}";
	}
}
