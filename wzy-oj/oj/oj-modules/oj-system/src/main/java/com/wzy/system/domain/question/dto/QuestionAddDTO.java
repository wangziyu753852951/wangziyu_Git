package com.wzy.system.domain.question.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: QuestionAddDTO
 * Description
 *
 * @Author wzy
 * @Create 2025/8/5 22:37
 * @Version 1.0
 */
@Getter
@Setter
public class QuestionAddDTO {
    private String title;

    private Integer difficulty;//枚举

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFuc;
}


