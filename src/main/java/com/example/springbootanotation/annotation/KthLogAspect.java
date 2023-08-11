package com.example.springbootanotation.annotation;

import com.example.springbootanotation.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class KthLogAspect {

    @Pointcut("@annotation(com.example.springbootanotation.annotation.KthLog)")
    private void pointcut() {
    }

    @Around("pointcut() && @annotation(logger)")
    public Object advice(ProceedingJoinPoint joinPoint, KthLog logger) {

        System.out.println("["
                + joinPoint.getSignature().getDeclaringType().getSimpleName()
                + "][" + joinPoint.getSignature().getName()
                + "]-日志内容-[" + logger.value() + "]");

        Object result = null;

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Integer) {
                args[i] = (Integer) args[i] - 1;
                break;
            }
        }

        try {
            //获得方法的返回值
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if (result instanceof User) {
            User user = (User) result;
            user.setId(user.getId() + 1);
            return user;
        }
        return result;
    }
}
