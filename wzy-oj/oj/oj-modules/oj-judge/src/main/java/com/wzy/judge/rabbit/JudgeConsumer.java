package com.wzy.judge.rabbit;

import com.bite.api.domain.dto.JudgeSubmitDTO;
import com.wzy.common.core.constants.RabbitMQConstants;
import com.wzy.judge.service.IJudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@Component
public class JudgeConsumer {

    @Autowired
    private IJudgeService judgeService;

    @RabbitListener(queues = RabbitMQConstants.OJ_WORK_QUEUE)
    public void consume(JudgeSubmitDTO judgeSubmitDTO) {
        log.info("收到消息为: {}", judgeSubmitDTO);
        judgeService.doJudgeJavaCode(judgeSubmitDTO);
    }
}
