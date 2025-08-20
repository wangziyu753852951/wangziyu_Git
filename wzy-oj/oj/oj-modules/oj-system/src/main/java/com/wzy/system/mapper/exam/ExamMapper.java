package com.wzy.system.mapper.exam;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.system.domain.exam.Exam;
import com.wzy.system.domain.exam.dto.ExamQueryDTO;
import com.wzy.system.domain.exam.vo.ExamVO;


import java.util.List;

/**
 * ClassName: ExamMapper
 * Description
 *
 * @Author wzy
 * @Create 2025/8/6 17:40
 * @Version 1.0
 */
public interface ExamMapper extends BaseMapper<Exam> {

    List<ExamVO> selectExamList(ExamQueryDTO examQueryDTO);
}

