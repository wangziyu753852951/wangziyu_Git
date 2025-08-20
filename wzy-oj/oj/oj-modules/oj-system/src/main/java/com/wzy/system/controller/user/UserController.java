package com.wzy.system.controller.user;

import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.system.domain.user.dto.UserDTO;
import com.wzy.system.domain.user.dto.UserQueryDTO;
import com.wzy.system.service.user.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;


    @GetMapping("/list")
    public TableDataInfo list(UserQueryDTO userQueryDTO) {
        return getTableDataInfo(userService.list(userQueryDTO));
    }

    @PutMapping("/updateStatus")
    //todo 拉黑：限制用户操作   解禁：放开对于用户限制
    //更新数据库中用户的状态信息。
    public R<Void> updateStatus(@RequestBody UserDTO userDTO) {
        return toResult(userService.updateStatus(userDTO));
    }
}
