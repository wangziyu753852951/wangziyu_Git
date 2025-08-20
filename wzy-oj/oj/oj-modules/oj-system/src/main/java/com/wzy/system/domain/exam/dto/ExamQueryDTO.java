package com.wzy.system.domain.exam.dto;

import com.wzy.common.core.domain.PageQueryDTO;
import com.wzy.system.domain.question.dto.QuestionQueryDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExamQueryDTO extends PageQueryDTO {
    private String title;

    private String startTime;

    private String endTime;
}
