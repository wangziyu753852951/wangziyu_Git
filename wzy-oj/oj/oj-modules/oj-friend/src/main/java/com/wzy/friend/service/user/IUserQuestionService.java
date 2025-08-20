package com.wzy.friend.service.user;

import com.bite.api.domain.vo.UserQuestionResultVO;
import com.wzy.common.core.domain.R;
import com.wzy.friend.domain.user.dto.UserSubmitDTO;

public interface IUserQuestionService {
    R<UserQuestionResultVO> submit(UserSubmitDTO submitDTO);

    boolean rabbitSubmit(UserSubmitDTO submitDTO);

    UserQuestionResultVO exeResult(Long examId, Long questionId, String currentTime);
}