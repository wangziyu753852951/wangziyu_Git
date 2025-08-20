package com.wzy.system.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.system.domain.user.User;
import com.wzy.system.domain.user.dto.UserQueryDTO;
import com.wzy.system.domain.user.vo.UserVO;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<UserVO> selectUserList(UserQueryDTO userQueryDTO);
}
