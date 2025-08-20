package com.wzy.system.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.wzy.common.core.enums.ResultCode;
import com.wzy.common.security.exception.ServiceException;
import com.wzy.system.domain.user.User;
import com.wzy.system.domain.user.dto.UserDTO;
import com.wzy.system.domain.user.dto.UserQueryDTO;
import com.wzy.system.domain.user.vo.UserVO;
import com.wzy.system.manager.UserCacheManager;
import com.wzy.system.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCacheManager userCacheManager;

    @Override
    public List<UserVO> list(UserQueryDTO userQueryDTO) {
        PageHelper.startPage(userQueryDTO.getPageNum(), userQueryDTO.getPageSize());
        return userMapper.selectUserList(userQueryDTO);
    }

    @Override
    public int updateStatus(UserDTO userDTO) {
        User user = userMapper.selectById(userDTO.getUserId());
        if (user == null) {
            throw new ServiceException(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        user.setStatus(userDTO.getStatus());
        userCacheManager.updateStatus(user.getUserId(), userDTO.getStatus());
        return userMapper.updateById(user);
    }
}
