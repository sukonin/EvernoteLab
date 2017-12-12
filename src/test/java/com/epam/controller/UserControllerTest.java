package com.epam.controller;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.User;
import com.epam.services.impl.UserService;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
public class UserControllerTest {

  @Autowired
  UserController controller;
  @Autowired
  UserService userService;

  @Test
  public void test() {
    log.warning(controller.getAllUsers().toString());
  }

  @Test
  public void create(){
    User user = new User();
    user.setEmail("email");
    user.setUsername("username");
    controller.createUser(user);



  }

}