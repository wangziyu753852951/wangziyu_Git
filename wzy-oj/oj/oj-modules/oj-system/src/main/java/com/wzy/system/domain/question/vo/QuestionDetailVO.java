package com.wzy.system.domain.question.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: QuestionDetailVO
 * Description
 *
 * @Author wzy
 * @Create 2025/8/5 23:31
 * @Version 1.0
 */
@Getter
@Setter
public class QuestionDetailVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long questionId;

    private String title;

    private Integer difficulty;

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFuc;
}