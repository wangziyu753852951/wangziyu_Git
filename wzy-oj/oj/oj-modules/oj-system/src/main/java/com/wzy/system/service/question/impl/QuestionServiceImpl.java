package com.wzy.system.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzy.common.core.constants.Constants;
import com.wzy.common.core.domain.TableDataInfo;
import com.wzy.common.core.enums.ResultCode;
import com.wzy.common.security.exception.ServiceException;
import com.wzy.system.domain.question.Question;
import com.wzy.system.domain.question.dto.QuestionAddDTO;
import com.wzy.system.domain.question.dto.QuestionEditDTO;
import com.wzy.system.domain.question.dto.QuestionQueryDTO;
import com.wzy.system.domain.question.es.QuestionES;
import com.wzy.system.domain.question.vo.QuestionDetailVO;
import com.wzy.system.domain.question.vo.QuestionVO;
import com.wzy.system.elasticsearch.QuestionRepository;
import com.wzy.system.manager.QuestionCacheManager;
import com.wzy.system.mapper.question.QuestionMapper;
import com.wzy.system.service.question.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextListener;

import java.lang.management.BufferPoolMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionCacheManager questionCacheManager;

    //rrTTT   1821828072921452500
    @Override
    public List<QuestionVO> list(QuestionQueryDTO questionQueryDTO) {
        String excludeIdStr = questionQueryDTO.getExcludeIdStr();
        if (StrUtil.isNotEmpty(excludeIdStr)) {
            String[] excludeIdArr = excludeIdStr.split(Constants.SPLIT_SEM);
            Set<Long> excludeIdSet = Arrays.stream(excludeIdArr)
                    .map(Long::valueOf)
                    .collect(Collectors.toSet());
            questionQueryDTO.setExcludeIdSet(excludeIdSet);
        }
        PageHelper.startPage(questionQueryDTO.getPageNum(), questionQueryDTO.getPageSize());
        List<QuestionVO> questionVOList = questionMapper.selectQuestionList(questionQueryDTO);
        return questionVOList;
        //questionVOList == null || questionVOList.isEmpty()
//        if (CollectionUtil.isEmpty(questionVOList)) {
//            return TableDataInfo.empty();
//        }
//        new PageInfo<>(questionVOList).getTotal(); //获取符合查询条件的数据的总数
//        return TableDataInfo.success(questionVOList, questionVOList.size());
    }

    @Override
    public boolean add(QuestionAddDTO questionAddDTO) {
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>()
                .eq(Question::getTitle, questionAddDTO.getTitle()));
        if (CollectionUtil.isNotEmpty(questionList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        Question question = new Question();
        BeanUtil.copyProperties(questionAddDTO, question);
        int insert = questionMapper.insert(question);
        if (insert <= 0) {
            return false;
        }
        QuestionES questionES = new QuestionES();
        BeanUtil.copyProperties(question, questionES);
        questionRepository.save(questionES);
        questionCacheManager.addCache(question.getQuestionId());
        return true;
    }

    @Override
    public QuestionDetailVO detail(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        QuestionDetailVO questionDetailVO = new QuestionDetailVO();
        BeanUtil.copyProperties(question, questionDetailVO);
        return questionDetailVO;
    }

    @Override
    public int edit(QuestionEditDTO questionEditDTO) {
        Question oldQuestion = questionMapper.selectById(questionEditDTO.getQuestionId());
        if (oldQuestion == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        oldQuestion.setTitle(questionEditDTO.getTitle());
        oldQuestion.setDifficulty(questionEditDTO.getDifficulty());
        oldQuestion.setTimeLimit(questionEditDTO.getTimeLimit());
        oldQuestion.setSpaceLimit(questionEditDTO.getSpaceLimit());
        oldQuestion.setContent(questionEditDTO.getContent());
        oldQuestion.setQuestionCase(questionEditDTO.getQuestionCase());
        oldQuestion.setDefaultCode(questionEditDTO.getDefaultCode());
        oldQuestion.setMainFuc(questionEditDTO.getMainFuc());
        QuestionES questionES = new QuestionES();
        BeanUtil.copyProperties(oldQuestion, questionES);
        questionRepository.save(questionES);
        return questionMapper.updateById(oldQuestion);
    }

    @Override
    public int delete(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        questionRepository.deleteById(questionId);
        questionCacheManager.deleteCache(questionId);
        return questionMapper.deleteById(questionId);
    }
}