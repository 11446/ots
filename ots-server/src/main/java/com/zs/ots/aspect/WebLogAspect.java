package com.zs.ots.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 张帅
 * @date 2020/3/26 20:38
 * @description 统一日志处理切面
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(* com.zs.ots.controller.*.*(..))")
    public void webLog() {
    }

    @Before(value = "webLog()")
    public void beforeMethod(JoinPoint joinPoint) {
        LOGGER.info("准备执行" + joinPoint.getSignature().getName() + "方法");
    }

    @AfterReturning(value = "webLog()", returning = "result")
    public void afterReturning(Object result) {
        LOGGER.info("执行成功！返回结果为：" + result);
    }


}
