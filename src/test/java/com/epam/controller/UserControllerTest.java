package com.epam.controller;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.config.AppInit;
import com.epam.config.ApplicationConfiguration;
import com.epam.config.SwaggerConfig;
import com.epam.model.User;
import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@Log
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void createUser() throws Exception {
    User user = new User();
    user.setEmail("test");
    user.setUsername("test");

    Gson gson = new Gson();
    String jsonObject = gson.toJson(user);


    String contentAsString = mvc.perform(post("/users")
        .content(jsonObject)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

/*    User user1 = gson.fromJson(contentAsString, User.class);
    assertNotNull(user1.getId());*/


  }

}