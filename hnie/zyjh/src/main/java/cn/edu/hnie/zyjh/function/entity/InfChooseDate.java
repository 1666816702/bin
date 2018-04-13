package cn.edu.hnie.zyjh.function.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("inf_choose_date")
public class InfChooseDate extends Model<InfChooseDate> {

    private static final long serialVersionUID = 1L;

    @TableId("date_id")
	private Long dateId;
	private Date one;
	private Date two;
	private String status;


	public Long getDateId() {
		return dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	public Date getOne() {
		return one;
	}

	public void setOne(Date one) {
		this.one = one;
	}

	public Date getTwo() {
		return two;
	}

	public void setTwo(Date two) {
		this.two = two;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.dateId;
	}

	@Override
	public String toString() {
		return "InfChooseDate{" +
			", dateId=" + dateId +
			", one=" + one +
			", two=" + two +
			", status=" + status +
			"}";
	}
}
