package com.wzy.system.controller.question;

import com.wzy.common.core.controller.BaseController;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.system.domain.question.dto.QuestionAddDTO;
import com.wzy.system.domain.question.dto.QuestionEditDTO;
import com.wzy.system.domain.question.dto.QuestionQueryDTO;
import com.wzy.system.domain.question.vo.QuestionDetailVO;
import com.wzy.system.service.question.IQuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/question")
@Tag(name = "题目管理接口")
public class QuestionController extends BaseController {

    @Autowired
    IQuestionService iQuestionService;

    @GetMapping("/list")
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO){
        return getTableDataInfo(iQuestionService.list(questionQueryDTO));
    }

    @PostMapping("/add")
    public R<Void> add(@RequestBody QuestionAddDTO questionAddDTO) {
        return toResult(iQuestionService.add(questionAddDTO));
    }

    @GetMapping("/detail")
    public R<QuestionDetailVO> detail(Long questionId) {
        return R.ok(iQuestionService.detail(questionId));
    }

    @PutMapping("/edit")
    public R<Void> edit(@RequestBody QuestionEditDTO questionEditDTO) {
        return toResult(iQuestionService.edit(questionEditDTO));
    }


    @DeleteMapping ("/delete")
    public R<Void> delete(Long questionId) {
        return toResult(iQuestionService.delete(questionId));
    }

}
