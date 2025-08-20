package com.wzy.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ClassName: BaseEntity
 * Description
 *
 * @Author wzy
 * @Create 2025/7/31 14:11
 * @Version 1.0
 */
@Getter
@Setter
public class BaseEntity {
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updateTime;
}
