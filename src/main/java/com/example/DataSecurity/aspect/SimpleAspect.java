package com.example.DataSecurity.aspect;

import com.example.DataSecurity.model.PortalUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class SimpleAspect {

    @Pointcut("execution(* com.example.DataSecurity..*(..))")
    public void anyMethod() { }

    @Before("anyMethod()")
    public void beforeAnyMethod(JoinPoint joinPoint){
        log.info("... before any method ..." +joinPoint.getSignature().getName());
    }

    @After("anyMethod()")
    public void afterAnyMethod(JoinPoint joinPoint){
        log.info("... after any method ..." +joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.DataSecurity.controller..*(..))")
    public void aroundAnyMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var start = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        System.out.println(proceedingJoinPoint.getSignature().getName() +" controller was running for "+(System.currentTimeMillis()-start)+"ms");
    }

    @Before("@annotation(aspectAnnotation) && args(portalUser,..)")
    public void beforeAnnotated(ExampleAspectAnnotation aspectAnnotation, PortalUser portalUser){
        System.out.println(portalUser.getPortalUserID());
        System.out.println(portalUser.getPortalUserEmail());
        System.out.println(portalUser.getPortalUserLogin());
        System.out.println(portalUser.getPortalUserPassword());
        System.out.println(portalUser.getPortalUserFirstName());
        System.out.println(portalUser.getPortalUserLastName());
    }
}
