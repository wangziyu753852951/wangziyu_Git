package com.test.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.test.domain.TestDomain;
import com.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: TestServiceImpl
 * Description
 *
 * @Author wzy
 * @Create 2025/7/31 11:22
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements ITestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<?> list() {
        return testMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public String add() {
        TestDomain testDomain = new TestDomain();
        testDomain.setTitle("测试");
        testDomain.setContent("生成测试");
        testMapper.insert(testDomain);
        return "测试添加数据";
    }


}

