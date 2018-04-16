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
@TableName("user_role_def")
public class UserRoleDef extends Model<UserRoleDef> {

    private static final long serialVersionUID = 1L;

    @TableId("def_id")
	private Long defId;
	private String type;
	@TableField("role_id")
	private Long roleId;


	public Long getDefId() {
		return defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	protected Serializable pkVal() {
		return this.defId;
	}

	@Override
	public String toString() {
		return "UserRoleDef{" +
			", defId=" + defId +
			", type=" + type +
			", roleId=" + roleId +
			"}";
	}
}
