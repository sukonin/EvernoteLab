package com.epam.aspect;

import com.epam.services.impl.UserService;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Component
@PropertySource("classpath:aspect-config.properties")
public class ActivityLogger {

  private static boolean enabled;
  private final UserService userService;

  @Autowired
  public ActivityLogger(UserService userService) {
    this.userService = userService;
  }


  @Around(value = "@annotation(PerformanceMonitor)")
  public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    String username = "Anonymous";

    Authentication authentication = SecurityContextHolder
        .getContext()
        .getAuthentication();

    if (authentication != null) {
      username = authentication.getPrincipal().toString();
    }

    if (enabled) {
      long start = System.currentTimeMillis();
      Object proceed = joinPoint.proceed();
      long time = System.currentTimeMillis() - start;
      log.info("**************************************");
      log.info(username + " user call method! "
          + joinPoint.getSignature().toShortString() + " executed in " + time + " ms!");
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
