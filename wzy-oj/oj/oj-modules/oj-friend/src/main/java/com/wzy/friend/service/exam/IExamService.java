package com.wzy.friend.service.exam;

import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.friend.domain.exam.dto.ExamQueryDTO;
import com.wzy.friend.domain.exam.dto.ExamRankDTO;
import com.wzy.friend.domain.exam.vo.ExamVO;

import java.util.List;

/**
 * ClassName: IExamService
 * Description
 *
 * @Author wzy
 * @Create 2025/8/10 10:44
 * @Version 1.0
 */
public interface IExamService {

    List<ExamVO> list(ExamQueryDTO examQueryDTO);

    TableDataInfo redisList(ExamQueryDTO examQueryDTO);

    TableDataInfo rankList(ExamRankDTO examRankDTO);

    String getFirstQuestion(Long examId);

    String preQuestion(Long examId, Long questionId);

    String nextQuestion(Long examId, Long questionId);
}
