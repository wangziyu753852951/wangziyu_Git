package com.wzy.friend.mapper.message;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.friend.domain.message.MessageText;
import com.wzy.friend.domain.message.vo.MessageTextVO;

import java.util.List;

public interface MessageTextMapper extends BaseMapper<MessageText> {

    List<MessageTextVO> selectUserMsgList(Long userId);
}