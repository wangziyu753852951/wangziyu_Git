package com.wzy.friend.controller.user;

import com.wzy.common.core.constants.HttpConstants;
import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.friend.aspect.CheckUserStatus;
import com.wzy.friend.domain.exam.dto.ExamDTO;
import com.wzy.friend.domain.exam.dto.ExamQueryDTO;
import com.wzy.friend.service.user.IUserExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/exam")
public class UserExamController extends BaseController {

    @Autowired
    private IUserExamService userExamService;

    @CheckUserStatus
    @PostMapping("/enter")
    public R<Void> enter(@RequestHeader(HttpConstants.AUTHENTICATION) String token, @RequestBody ExamDTO examDTO) {
        return toResult(userExamService.enter(token, examDTO.getExamId()));
    }

    @GetMapping("/list")
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        return userExamService.list(examQueryDTO);
    }
}