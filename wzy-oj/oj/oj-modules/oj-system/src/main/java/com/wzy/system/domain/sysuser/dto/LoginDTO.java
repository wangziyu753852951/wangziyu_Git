package com.wzy.system.domain.sysuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: LoginDTO
 * Description
 *
 * @Author wzy
 * @Create 2025/7/31 16:08
 * @Version 1.0
 */
@Getter
@Setter
public class LoginDTO {
    @Schema(description = "用户账号")
    private String userAccount;

    @Schema(description = "用户密码")
    private String password;
}
