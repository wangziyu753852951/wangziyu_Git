package com.wzy.friend.service.user;

import com.wzy.common.core.domain.PageQueryDTO;
import com.wzy.common.core.domain.TableDataInfo;

public interface IUserMessageService {
    TableDataInfo list(PageQueryDTO dto);
}
