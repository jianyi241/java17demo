package com.example.java17demo.aop;

import com.example.java17demo.annotation.Auth;
import com.example.java17demo.util.ResultMap;
import com.example.java17demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 定义业务层拦截器
 */

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @Pointcut("execution(public * com.example.java17demo..*.*(..))")
    public void Pointcut() {
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("Pointcut()")
    void beforeNotice(JoinPoint joinPoint) {
        log.info("beforeNotice ===> "+ joinPoint.getSignature()+"===="+joinPoint.toString());
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After("Pointcut()")
    void afterNotice(JoinPoint joinPoint){
        log.info("afterNotice ===> "+ joinPoint);
    }

    /**
     * 最终通知
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value="Pointcut()",returning="result")
    void afterReturningNotice(JoinPoint joinPoint, Object result){
        log.info("afterReturningNotice ===> "+ joinPoint.toString());
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value="Pointcut()",throwing="e")
    void afterReturningNotice(JoinPoint joinPoint, Exception e){
        log.info("afterReturningNotice ===>"+ e);
    }

    /**
     * 环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("Pointcut()")
    Object aroundNotice(ProceedingJoinPoint point) {
        Object result = null;
        log.info("\033[34mThe Logger Method ===> " + point.getSignature());
        log.info("\033[34mThe Logger Before ======>" + Arrays.toString(point.getArgs()));

//        // 获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        // 从获取RequestAttributes中获取HttpServletRequest的信息
//        assert requestAttributes != null;
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        assert request != null;
//        String token = request.getHeader("auth");

        // 获取 RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
// 从获取 RequestAttributes 中获取 HttpServletRequest 的信息
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        assert request != null;

// 获取所有 Cookies
        String token = TokenUtil.getToken(request);
        log.info("Token ===>{}", token);
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        try {
            Method method = methodSignature.getMethod(); // 通过反射获得该方法
            log.info("Method ===>{}", method); // 获得自定义注解上面的值
            Auth auth = method.getDeclaredAnnotation(Auth.class); // 获得该注解
            log.info("Annotation ===>{}", auth); // 获得自定义注解上面的值
            if (auth != null) {
                if (token == null) {
                    return new ResultMap(401, "未授权", "");
                }
                if (!token.isEmpty()) {
                    Map<String,Object> authInfo = TokenUtil.verify(token);
                    Boolean isLogin = (Boolean) authInfo.get("requireAuth");
                    if (!isLogin) {
                        log.info("\033[34mThe Logger After 401 ======");
                        return new ResultMap(401, "授权过期", "");
                    } else {
                        try {
                            result = point.proceed(); // 执行该方法
                        } catch (Throwable e) {
                            log.error(e.getLocalizedMessage());
                        }
                    }
                } else {
                    log.info("\033[34mThe Logger After 401 no token ======");
                    return new ResultMap(401, "未授权", "");
                }
            } else {
                try {
                    result = point.proceed(); // 执行该方法
                } catch (Throwable e) {
                    log.error(e.getLocalizedMessage());

                }
            }
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        log.info("\u001B[34mThe Logger After ======>{}", result);
        return result;
    }
}
