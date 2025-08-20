package com.wzy.judge.controller;

import com.bite.api.domain.dto.JudgeSubmitDTO;
import com.bite.api.domain.vo.UserQuestionResultVO;
import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.judge.service.IJudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/judge")
@Slf4j
public class JudgeController extends BaseController {

    @Autowired
    private IJudgeService judgeService;

    @PostMapping("/doJudgeJavaCode")
    public R<UserQuestionResultVO> doJudgeJavaCode(@RequestBody JudgeSubmitDTO judgeSubmitDTO) {
        return R.ok(judgeService.doJudgeJavaCode(judgeSubmitDTO));
    }
}
