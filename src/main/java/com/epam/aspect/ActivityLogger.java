package com.epam.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Component
@PropertySource("classpath:aspect-config.properties")
public class ActivityLogger {

  private static boolean enabled;

  @Pointcut("@annotation(PerformanceMonitor)&& if()")
  public static boolean pointCut() {
    return enabled;
  }

  @Around("pointCut()" )
  public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long time = System.currentTimeMillis() - start;
    log.info("**************************************");
    log.info(joinPoint.getSignature().toShortString() + " executed in " + time);
    log.info("**************************************");
    return proceed;
  }


  @Value("${enable.performance.monitor}")
  public void setEnabled(boolean value) {
    enabled = value;
  }


}
