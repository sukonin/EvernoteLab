package com.epam.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.User;
import com.epam.services.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = {ApplicationConfiguration.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

  @Autowired
  private UserService userService;
  @Autowired
  private WebApplicationContext wac;
  private MockMvc mvc;
  @Resource
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setupSpec() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(wac)
        .addFilter(springSecurityFilterChain)
        .build();
  }

  @Test
  public void createUser() throws Exception {
    User user = new User();
    user.setEmail("user@email.com");
    user.setUsername("username");
    user.setPassword("super_security");

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonObject = objectMapper.writeValueAsString(user);

    mvc.perform(post("/registration")
        .content(jsonObject)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void getAllUsers() throws Exception {
    mvc.perform(get("/users")
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void updateUser() throws Exception {

    User user = userService.getById(8L);
    ObjectMapper objectMapper = new ObjectMapper();
    user.setUsername("Another Name");
    user.setPassword("test");

    String json = objectMapper.writeValueAsString(user);

    mvc.perform(put("/users/8")
        .with(httpBasic("test","test"))
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }



}