package com.epam.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Component
@PropertySource("classpath:aspect-config.properties")
public class ActivityLogger {

  private static boolean enabled;

  @Around(value = "@annotation(PerformanceMonitor)")
  public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    if (enabled) {
      long start = System.currentTimeMillis();
      Object proceed = joinPoint.proceed();
      long time = System.currentTimeMillis() - start;
      log.info("**************************************");
      log.info(joinPoint.getSignature().toShortString() + " executed in " + time + " ms!");
      log.info("**************************************");
      return proceed;
    }
    return joinPoint.proceed();
  }

  @Value("${enable.performance.monitor}")
  public void setEnabled(boolean value) {
    enabled = value;
  }
}
