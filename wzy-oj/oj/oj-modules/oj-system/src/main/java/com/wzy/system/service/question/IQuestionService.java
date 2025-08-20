package com.wzy.system.service.question;

import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.system.domain.question.dto.QuestionAddDTO;
import com.wzy.system.domain.question.dto.QuestionEditDTO;
import com.wzy.system.domain.question.dto.QuestionQueryDTO;
import com.wzy.system.domain.question.vo.QuestionDetailVO;
import com.wzy.system.domain.question.vo.QuestionVO;

import java.util.List;

public interface IQuestionService {
    List<QuestionVO> list(QuestionQueryDTO questionQueryDTO);

    boolean add(QuestionAddDTO questionAddDTO);

    QuestionDetailVO detail(Long questionId);

    int edit(QuestionEditDTO questionEditDTO);

    int delete(Long questionId);
}
