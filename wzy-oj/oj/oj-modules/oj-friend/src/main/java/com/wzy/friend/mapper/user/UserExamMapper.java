package com.wzy.friend.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.friend.domain.exam.vo.ExamRankVO;
import com.wzy.friend.domain.exam.vo.ExamVO;
import com.wzy.friend.domain.user.UserExam;

import java.util.List;

/**
 * ClassName: UserExamMapper
 * Description
 *
 * @Author wzy
 * @Create 2025/8/9 17:54
 * @Version 1.0
 */
public interface UserExamMapper extends BaseMapper<UserExam> {

    List<ExamVO> selectUserExamList(Long userId);

    List<ExamRankVO> selectExamRankList(Long examId);

}