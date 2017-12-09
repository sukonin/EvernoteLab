package com.epam.services.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.User;
import java.util.Collections;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Test
  public void getAll() throws Exception {
    assertNotEquals(userService.getAll(), Collections.EMPTY_LIST);
  }

  @Test
  public void getById() throws Exception {
    User test = userService.getByEmail("user1@mail.ru");
    log.info(test.toString());
    User user = userService.getById(test.getId());
    assertNotNull(user);
  }

  @Test
  public void saveOrUpdate() throws Exception {
    User user = new User();
    userService.saveOrUpdate(user);
    assertNotNull(user.getId());
  }

  @Test
  public void delete() throws Exception {
    User test = userService.getByEmail("user2@mail.ru");
    userService.delete(test.getId());
    User deleted = userService.getByEmail("user2@mail.ru");
    assertNull(deleted);
  }


}