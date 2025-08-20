package com.wzy.system.controller.exam;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.system.domain.exam.dto.ExamAddDTO;
import com.wzy.system.domain.exam.dto.ExamEditDTO;
import com.wzy.system.domain.exam.dto.ExamQueryDTO;
import com.wzy.system.domain.exam.dto.ExamQuestAddDTO;
import com.wzy.system.domain.exam.vo.ExamDetailVO;
import com.wzy.system.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    @Autowired
    private IExamService examService;

    //exam/list
    @GetMapping("/list")
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        return getTableDataInfo(examService.list(examQueryDTO));
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody ExamAddDTO examAddDTO) {
        return R.ok(examService.add(examAddDTO));
    }

    @PostMapping("/question/add")
    public R<Void> questionAdd(@RequestBody ExamQuestAddDTO examQuestAddDTO) {
        return toResult(examService.questionAdd(examQuestAddDTO));
    }

    @DeleteMapping("/question/delete")
    public R<Void> questionDelete(Long examId, Long questionId) {
        return toResult(examService.questionDelete(examId, questionId));
    }

    @GetMapping("/detail")
    public R<ExamDetailVO> detail(Long examId) {
        return R.ok(examService.detail(examId));
    }

    @PutMapping("/edit")
    public R<Void> edit(@RequestBody ExamEditDTO examEditDTO) {
        return toResult(examService.edit(examEditDTO));
    }

    @DeleteMapping("/delete")
    public R<Void> delete(Long examId) {
        return toResult(examService.delete(examId));
    }

    @PutMapping("/publish")
    public R<Void> publish(Long examId) {
        return toResult(examService.publish(examId));
    }

    @PutMapping("/cancelPublish")
    public R<Void> cancelPublish(Long examId) {
        return toResult(examService.cancelPublish(examId));
    }
}
