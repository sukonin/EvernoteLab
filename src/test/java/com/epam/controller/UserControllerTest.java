package com.epam.controller;

import static org.junit.Assert.*;

import com.epam.config.AppInit;
import com.epam.config.ApplicationConfiguration;
import com.epam.config.SwaggerConfig;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
public class UserControllerTest {

  @Test
  public void getAllUsers() throws Exception {
  }

  @Test
  public void createUser() throws Exception {
  }

}