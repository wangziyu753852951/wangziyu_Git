package com.wzy.friend.service.user;

import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.vo.LoginUserVO;
import com.wzy.friend.domain.user.dto.UserDTO;
import com.wzy.friend.domain.user.dto.UserUpdateDTO;
import com.wzy.friend.domain.user.vo.UserVO;

/**
 * ClassName: IUserService
 * Description
 *
 * @Author wzy
 * @Create 2025/8/9 17:20
 * @Version 1.0
 */
public interface IUserService {
    boolean sendCode(UserDTO userDTO);

    String codeLogin(String phone, String code);

    boolean logout(String token);

    R<LoginUserVO> info(String token);

    UserVO detail();

    int edit(UserUpdateDTO userUpdateDTO);

    int updateHeadImage(String headImage);
}
