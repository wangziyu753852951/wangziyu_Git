package com.wzy.friend.service.quesion.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.wzy.common.core.constants.Constants;
import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.friend.domain.question.Question;
import com.wzy.friend.domain.question.dto.QuestionQueryDTO;
import com.wzy.friend.domain.question.es.QuestionES;
import com.wzy.friend.domain.question.vo.QuestionDetailVO;
import com.wzy.friend.domain.question.vo.QuestionVO;
import com.wzy.friend.elasticsearch.QuestionRepository;
import com.wzy.friend.manager.QuestionCacheManager;
import com.wzy.friend.mapper.question.QuestionMapper;
import com.wzy.friend.mapper.user.UserSubmitMapper;
import com.wzy.friend.service.quesion.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserSubmitMapper userSubmitMapper;

    @Autowired
    private QuestionCacheManager questionCacheManager;

    @Override
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO) {
        long count = questionRepository.count();
        if (count <= 0) {
            refreshQuestion();
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(questionQueryDTO.getPageNum() - 1, questionQueryDTO.getPageSize(), sort);
        Integer difficulty = questionQueryDTO.getDifficulty();
        String keyword = questionQueryDTO.getKeyword();
        Page<QuestionES> questionESPage;
        if (difficulty == null && StrUtil.isEmpty(keyword)) {
            questionESPage = questionRepository.findAll(pageable);
        } else if (StrUtil.isEmpty(keyword)) {
            questionESPage = questionRepository.findQuestionByDifficulty(difficulty, pageable);
        } else if (difficulty == null) {
            questionESPage = questionRepository.findByTitleOrContent(keyword, keyword, pageable);
        } else {
            questionESPage = questionRepository.findByTitleOrContentAndDifficulty(keyword, keyword, difficulty, pageable);
        }
        long total = questionESPage.getTotalElements();
        if (total <= 0) {
            return TableDataInfo.empty();
        }
        List<QuestionES> questionESList = questionESPage.getContent();
        List<QuestionVO> questionVOList = BeanUtil.copyToList(questionESList, QuestionVO.class);
        return TableDataInfo.success(questionVOList, total);
    }

    @Override
    public List<QuestionVO> hotList() {
        Long total = questionCacheManager.getHostListSize();
        List<Long> hotQuestionIdList;
        if (total == null || total <= 0) {
            PageHelper.startPage(Constants.HOST_QUESTION_LIST_START, Constants.HOST_QUESTION_LIST_END);
            hotQuestionIdList = userSubmitMapper.selectHostQuestionList();
            questionCacheManager.refreshHotQuestionList(hotQuestionIdList);
        } else {
            hotQuestionIdList = questionCacheManager.getHostList();
        }
        return assembleQuestionVOList(hotQuestionIdList);
    }

    @Override
    public QuestionDetailVO detail(Long questionId) {
        QuestionES questionES = questionRepository.findById(questionId).orElse(null);
        QuestionDetailVO questionDetailVO = new QuestionDetailVO();
        if (questionES != null) {
            BeanUtil.copyProperties(questionES, questionDetailVO);
            return questionDetailVO;
        }
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            return null;
        }
        refreshQuestion();
        BeanUtil.copyProperties(question, questionDetailVO);
        return questionDetailVO;
    }

    @Override
    public String preQuestion(Long questionId) {
        Long listSize = questionCacheManager.getListSize();
        if (listSize == null || listSize <= 0) {
            questionCacheManager.refreshCache();
        }
        return questionCacheManager.preQuestion(questionId).toString();
    }

    @Override
    public String nextQuestion(Long questionId) {
        Long listSize = questionCacheManager.getListSize();
        if (listSize == null || listSize <= 0) {
            questionCacheManager.refreshCache();
        }
        return questionCacheManager.nextQuestion(questionId).toString();
    }

    private void refreshQuestion() {
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>());
        if (CollectionUtil.isEmpty(questionList)) {
            return;
        }
        List<QuestionES> questionESList = BeanUtil.copyToList(questionList, QuestionES.class);
        questionRepository.saveAll(questionESList);
    }

    private List<QuestionVO> assembleQuestionVOList(List<Long> hotQuestionIdList) {
        if (CollectionUtil.isEmpty(hotQuestionIdList)) {
            return new ArrayList<>();
        }
        List<QuestionVO> resultList = new ArrayList<>();
        for (Long questionId : hotQuestionIdList) {
            QuestionVO questionVO = new QuestionVO();
            QuestionDetailVO detail = detail(questionId);
            questionVO.setTitle(detail.getTitle());
            resultList.add(questionVO);
        }
        return resultList;
    }
}
