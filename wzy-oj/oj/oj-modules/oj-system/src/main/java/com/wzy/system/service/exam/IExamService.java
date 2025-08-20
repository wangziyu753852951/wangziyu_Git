package com.wzy.system.service.exam;

import com.wzy.system.domain.exam.dto.ExamAddDTO;
import com.wzy.system.domain.exam.dto.ExamEditDTO;
import com.wzy.system.domain.exam.dto.ExamQueryDTO;
import com.wzy.system.domain.exam.dto.ExamQuestAddDTO;
import com.wzy.system.domain.exam.vo.ExamDetailVO;
import com.wzy.system.domain.exam.vo.ExamVO;

import java.util.List;

/**
 * ClassName: IExamService
 * Description
 *
 * @Author wzy
 * @Create 2025/8/6 17:02
 * @Version 1.0
 */
public interface IExamService {

    List<ExamVO> list(ExamQueryDTO examQueryDTO);

    String add(ExamAddDTO examAddDTO);

    boolean questionAdd(ExamQuestAddDTO examQuestAddDTO);

    int questionDelete(Long examId, Long questionId);

    ExamDetailVO detail(Long examId);

    int edit(ExamEditDTO examEditDTO);

    int delete(Long examId);

    int publish(Long examId);

    int cancelPublish(Long examId);
}
