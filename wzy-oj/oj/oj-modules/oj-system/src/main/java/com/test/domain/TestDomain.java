package com.test.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * ClassName: TestDomain
 * Description
 *
 * @Author wzy
 * @Create 2025/7/31 11:53
 * @Version 1.0
 */
@TableName("tb_test")
public class TestDomain {
    @TableId(type = IdType.ASSIGN_ID)
    private Long testId;

    private String title;

    private String content;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
