package com.wzy.friend.service.quesion;

import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.friend.domain.question.dto.QuestionQueryDTO;
import com.wzy.friend.domain.question.vo.QuestionDetailVO;
import com.wzy.friend.domain.question.vo.QuestionVO;

import java.util.List;

public interface IQuestionService {

    TableDataInfo list(QuestionQueryDTO questionQueryDTO);

    List<QuestionVO> hotList();

    QuestionDetailVO detail(Long questionId);

    String preQuestion(Long questionId);

    String nextQuestion(Long questionId);
}
