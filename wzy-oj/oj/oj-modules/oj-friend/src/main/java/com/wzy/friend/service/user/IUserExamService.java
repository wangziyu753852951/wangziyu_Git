package com.wzy.friend.service.user;

import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.friend.domain.exam.dto.ExamQueryDTO;

public interface IUserExamService {

    int enter(String token, Long examId);

    TableDataInfo list(ExamQueryDTO examQueryDTO);
}