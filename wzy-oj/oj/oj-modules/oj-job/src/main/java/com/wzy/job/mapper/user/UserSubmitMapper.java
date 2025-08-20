package com.wzy.job.mapper.user;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.job.domain.user.UserScore;
import com.wzy.job.domain.user.UserSubmit;

import java.util.List;
import java.util.Set;

public interface UserSubmitMapper extends BaseMapper<UserSubmit> {

    List<UserScore> selectUserScoreList(Set<Long> examIdSet);

    List<Long> selectHostQuestionList();
}
