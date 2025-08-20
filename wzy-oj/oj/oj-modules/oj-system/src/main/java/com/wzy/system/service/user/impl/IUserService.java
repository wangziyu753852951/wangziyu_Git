package com.wzy.system.service.user.impl;

import com.wzy.system.domain.user.dto.UserDTO;
import com.wzy.system.domain.user.dto.UserQueryDTO;
import com.wzy.system.domain.user.vo.UserVO;

import java.util.List;

public interface IUserService {

    List<UserVO> list(UserQueryDTO userQueryDTO);

    int updateStatus(UserDTO userDTO);
}