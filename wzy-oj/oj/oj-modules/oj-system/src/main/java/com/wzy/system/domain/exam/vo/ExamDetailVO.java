package com.wzy.system.domain.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wzy.system.domain.question.vo.QuestionVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: ExamDetailVO
 * Description
 *
 * @Author wzy
 * @Create 2025/8/6 17:48
 * @Version 1.0
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDetailVO {

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private List<QuestionVO> examQuestionList;
}