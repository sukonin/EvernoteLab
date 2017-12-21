package com.epam.config.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.h2.server.web.WebServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApplication implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext)
      throws ServletException {

    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(WebConfig.class);
    servletContext.addListener(new ContextLoaderListener(ctx));


    ServletRegistration.Dynamic servlet = servletContext
        .addServlet("dispatcher", new DispatcherServlet(ctx));
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);

    servlet = servletContext.addServlet(
        "h2-console", new WebServlet());
    servlet.setLoadOnStartup(2);
    servlet.addMapping("/console/*");

    servletContext.addListener(requestContextListener());
  }

  @Bean
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }
}
