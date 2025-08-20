package com.wzy.friend.domain.exam.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: ExamRankVO
 * Description
 *
 * @Author wzy
 * @Create 2025/8/9 17:59
 * @Version 1.0
 */
@Getter
@Setter
public class ExamRankVO {

    private Long userId;

    private String nickName;

    private int examRank;

    private int score;

}
