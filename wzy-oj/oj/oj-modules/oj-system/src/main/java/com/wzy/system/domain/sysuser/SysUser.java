package com.wzy.system.domain.sysuser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wzy.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: SysUser
 * Description
 *
 * @Author wzy
 * @Create 2025/7/31 13:25
 * @Version 1.0
 */
@TableName("tb_sys_user")
@Getter
@Setter
@ToString
public class SysUser extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;//主键  不再使用auto_increment维护组件

    private String userAccount;

    private String password;

    private String nickName;




}
