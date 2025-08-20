package com.wzy.system.service.sysuser;

import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.vo.LoginUserVO;
import com.wzy.system.domain.sysuser.dto.SysUserSaveDTO;

public interface ISysUserService {
    R<String> login(String userAccount, String password);

    boolean logout(String token);

    int add(SysUserSaveDTO sysUserSaveDTO);

    R<LoginUserVO> info(String token);

}
