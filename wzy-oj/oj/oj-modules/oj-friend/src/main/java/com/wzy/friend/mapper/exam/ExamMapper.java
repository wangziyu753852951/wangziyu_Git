package com.wzy.friend.mapper.exam;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.friend.domain.exam.Exam;
import com.wzy.friend.domain.exam.dto.ExamQueryDTO;
import com.wzy.friend.domain.exam.vo.ExamVO;

import java.util.List;

public interface ExamMapper extends BaseMapper<Exam> {

    List<ExamVO> selectExamList(ExamQueryDTO examQueryDTO);

}