package com.sangeng.aspect;

import com.alibaba.fastjson.JSON;
import com.sangeng.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName logAspect
 * @Description TODO
 * @Date 2022/9/3 16:34
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.sangeng.annotation.SystemLog)")
    public void pt(){

    }
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed ;
        try {
            handleBefore(joinPoint);
            proceed = joinPoint.proceed();
            handleAfter(proceed);
        } finally {
            // 结束后执行
            log.info("=========End=========="+System.lineSeparator());
        }
        return proceed;
    }

    private void handleAfter(Object proceed) {
        log.info("Response          {}",JSON.toJSONString(proceed));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取request
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取被增强方法上的注解
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=======Start========");
        // 打印请求url
        log.info("请求路径         :{}",request.getRequestURI());
        // 打印描述信息
        log.info("描述信息         :{}",systemLog.businessName());
        // 打印Http method
        log.info("请求方式         :{}",request.getMethod());
        // 打印调用controller的全路径以及执行方法
        log.info("请求Controller的全路径以及执行方法  :{},{}",joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature)joinPoint.getSignature()).getName());
        // 打印请求的ip
        log.info("请求IP                 :{}",request.getRemoteHost());
        // 打印请求入参
        log.info("请求参数         :{}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog annotation = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return annotation;
    }
}

