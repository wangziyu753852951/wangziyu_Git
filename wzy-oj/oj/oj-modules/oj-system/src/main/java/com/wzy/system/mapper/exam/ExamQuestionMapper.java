package com.wzy.system.mapper.exam;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.system.domain.exam.ExamQuestion;
import com.wzy.system.domain.question.vo.QuestionVO;

import java.util.List;

/**
 * ClassName: ExamQuestionMapper
 * Description
 *
 * @Author wzy
 * @Create 2025/8/7 12:53
 * @Version 1.0
 */
public interface ExamQuestionMapper extends BaseMapper<ExamQuestion> {

    List<QuestionVO> selectExamQuestionList(Long examId);
}
