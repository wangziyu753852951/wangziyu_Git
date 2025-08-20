package com.wzy.friend.controller.user;

import com.wzy.common.core.constants.HttpConstants;
import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.vo.LoginUserVO;
import com.wzy.friend.domain.user.dto.UserDTO;
import com.wzy.friend.domain.user.dto.UserUpdateDTO;
import com.wzy.friend.domain.user.vo.UserVO;
import com.wzy.friend.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    //  /user/sendCode
    @PostMapping("sendCode")
    public R<Void> sendCode(@RequestBody UserDTO userDTO) {
        return toResult(userService.sendCode(userDTO)) ;
    }


    // /code/login
    //  post
    @PostMapping("/code/login")
    public R<String> codeLogin(@RequestBody UserDTO userDTO) {
        return R.ok(userService.codeLogin(userDTO.getPhone(), userDTO.getCode()));
    }

    @DeleteMapping("/logout")
    public R<Void> logout(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return toResult(userService.logout(token));
    }

    @GetMapping("/info")
    public R<LoginUserVO> info(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return userService.info(token);
    }

    @GetMapping("/detail")
    public R<UserVO> detail() {
        return R.ok(userService.detail());
    }

    @PutMapping("/edit")
    public R<Void> edit(@RequestBody UserUpdateDTO userUpdateDTO) {
        return toResult(userService.edit(userUpdateDTO));
    }

    @PutMapping("/head-image/update")
    public R<Void> updateHeadImage(@RequestBody UserUpdateDTO userUpdateDTO) {
        return toResult(userService.updateHeadImage(userUpdateDTO.getHeadImage()));
    }
}