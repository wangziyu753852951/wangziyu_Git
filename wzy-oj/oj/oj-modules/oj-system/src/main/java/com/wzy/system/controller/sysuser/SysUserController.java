package com.wzy.system.controller.sysuser;

import com.wzy.common.core.constants.HttpConstants;
import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.vo.LoginUserVO;
import com.wzy.system.domain.sysuser.dto.LoginDTO;
import com.wzy.system.domain.sysuser.dto.SysUserSaveDTO;
import com.wzy.system.domain.sysuser.vo.SysUserVO;
import com.wzy.system.service.sysuser.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysUser")
@Tag(name = "管理员接口")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    //返回值要告诉登录成功还是失败 boolean true false int code 1成功 0失败
    //要告知失败的原因 String msg
    //请求方法特定 和 url sysuser/login
    @PostMapping("/login") //安全性
    @Operation(summary = "管理员登录",description = "根据账号密码进行管理员登录")
    @ApiResponse(responseCode = "1000",description = "操作成功")
    @ApiResponse(responseCode = "2000",description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3102",description = "用户不存在")
    @ApiResponse(responseCode = "3103",description = "用户名或密码错误")
    public R<String> login(@RequestBody LoginDTO loginDTO){
        return sysUserService.login(loginDTO.getUserAccount(),loginDTO.getPassword());
    }

    //接口地址：/system/sysUser/info
    @GetMapping("/info")
    public R<LoginUserVO> info(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return sysUserService.info(token);
    }

    @DeleteMapping("/logout")
    public R<Void> logout(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return toResult(sysUserService.logout(token));
    }

    @PostMapping("/add")
    @Operation(summary = "新增管理员",description = "根据提供的信息新增管理员")
    @ApiResponse(responseCode = "1000",description = "操作成功")
    @ApiResponse(responseCode = "2000",description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3101",description = "用户已存在")
    public R<Void> add(@RequestBody SysUserSaveDTO sysUserSaveDTO){
        return toResult(sysUserService.add(sysUserSaveDTO));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除⽤⼾", description = "通过⽤⼾id删除⽤⼾")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.PATH, description = "⽤⼾ID")
                    })
            @ApiResponse(responseCode = "1000", description = "成功删除⽤⼾")
            @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
            @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
            public R<Void> delete(@PathVariable Long userId) {
        return null;
    }

    @Operation(summary = "⽤⼾详情", description = "根据查询条件查询⽤⼾详情")
    @GetMapping("/detail")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.QUERY, description = "⽤⼾ID"),
            @Parameter(name = "sex", in = ParameterIn.QUERY, description = "⽤⼾性别")
                    })
            @ApiResponse(responseCode = "1000", description = "成功获取⽤⼾信息")
            @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
            @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
            public R<SysUserVO> detail(Long userId, @RequestParam(required = false)
            String sex) {
        return null;
    }
}

