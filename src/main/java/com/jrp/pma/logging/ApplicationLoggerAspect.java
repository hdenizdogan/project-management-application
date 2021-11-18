package com.jrp.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.jrp.pma.controllers..*)")
    public void definePackagePointCuts(){
        //empty method just to name the location specified in the pointcut
    }

    @Around("definePackagePointCuts()")
    public Object logAround(ProceedingJoinPoint joinPoint){
        log.debug(" \n \n \n ");
        log.debug("************** Before Method Execution ************* \n {}.{}() with arguments[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        log.debug("_____________________________________________________________________ \n \n \n");
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.debug("************** After Method Execution ************* \n {}.{}() with arguments[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        return object;
    }
}
