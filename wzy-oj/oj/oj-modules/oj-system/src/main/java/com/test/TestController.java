package com.test;

import com.test.domain.ValidationDto;
import com.wzy.common.redis.service.RedisService;
import com.wzy.system.domain.sysuser.SysUser;
import com.test.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: TestController
 * Description
 *
 * @Author wzy
 * @Create 2025/7/31 11:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private ITestService iTestService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/list")
    public List<?> list(){
        return iTestService.list();
    }

    @GetMapping("/add")
    public String add(){
        return iTestService.add();
    }

    @GetMapping("/log")
    public String log() {
        log.info("我是info级别的日志");
        log.error("我是error级别的日志");
        return "日志测试";
    }

    @GetMapping("redisAddAndGet")
    public String redisAddAndGet() {
        SysUser sysUser = new SysUser();
        sysUser.setUserAccount("redistest");
        redisService.setCacheObject("u",sysUser);
        SysUser us = redisService.getCacheObject("u",SysUser.class);
        return us.toString();
    }

    @RequestMapping("/validation")
    public String validation(@Validated ValidationDto validationDto){
        return "参数测试";
    }
}
